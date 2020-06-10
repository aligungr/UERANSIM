package tr.havelsan.ueransim.api;

import threegpp.milenage.MilenageResult;
import threegpp.milenage.biginteger.BigIntegerBufferFactory;
import threegpp.milenage.cipher.Ciphers;
import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.core.UeData;
import tr.havelsan.ueransim.enums.AutnValidationRes;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.eap.ESubType;
import tr.havelsan.ueransim.nas.eap.Eap;
import tr.havelsan.ueransim.nas.eap.EapAkaPrime;
import tr.havelsan.ueransim.nas.eap.EapAttributes;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.ies.IEAuthenticationResponseParameter;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class UeAuthentication {

    public static void handleAuthenticationRequest(SimulationContext ctx, AuthenticationRequest message) {
        if (message.eapMessage != null) {
            handleEapAkaPrime(ctx, message);
        } else {
            handle5gAka(ctx, message);
        }
    }

    private static void handleEapAkaPrime(SimulationContext ctx, AuthenticationRequest message) {
        OctetString receivedRand, receivedMac, receivedAutn;
        EapAkaPrime receivedEap;

        OctetString milenageAk, milenageMac;

        OctetString res, mk, kaut;

        // Read EAP-AKA' request
        {
            receivedEap = (EapAkaPrime) message.eapMessage.eap;
            receivedRand = receivedEap.attributes.getRand();
            receivedMac = receivedEap.attributes.getMac();
            receivedAutn = receivedEap.attributes.getAutn();
        }

        // Derive keys
        {
            var snn = ctx.ueData.snn;
            var sqn = ctx.ueData.sqn;
            var supi = ctx.ueData.supi;

            var milenage = calculateMilenage(ctx.ueData, receivedRand);
            res = milenage.get(MilenageResult.RES);
            var ck = milenage.get(MilenageResult.CK);
            var ik = milenage.get(MilenageResult.IK);
            milenageAk = milenage.get(MilenageResult.AK);
            milenageMac = milenage.get(MilenageResult.MAC_A);

            var sqnXorAk = OctetString.xor(sqn, milenageAk);
            var ckPrimeIkPrime = UeKeyManagement.calculateCkPrimeIkPrime(ck, ik, snn, sqnXorAk);
            var ckPrime = ckPrimeIkPrime[0];
            var ikPrime = ckPrimeIkPrime[1];

            mk = UeKeyManagement.calculateMk(ckPrime, ikPrime, supi);
            kaut = mk.substring(16, 32);
        }

        // Control received AUTN
        {
            var autnCheck = UeAuthentication.validateAutn(milenageAk, milenageMac, receivedAutn);
            if (autnCheck != AutnValidationRes.OK) {
                EapAkaPrime eapResponse = null;

                if (autnCheck == AutnValidationRes.MAC_FAILURE) {
                    eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_AUTHENTICATION_REJECT);

                    Console.println(Color.YELLOW, "MAC_FAILURE in AUTN validation for EAP AKA'");
                } else if (autnCheck == AutnValidationRes.SYNCHRONISATION_FAILURE) {
                    // todo
                    //eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_SYNCHRONIZATION_FAILURE);
                    //eapResponse.attributes.putAuts(...);

                    Console.println(Color.YELLOW, "feature not implemented yet: SYNCHRONISATION_FAILURE in AUTN validation for EAP AKA'");
                } else {
                    eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CLIENT_ERROR);
                    eapResponse.attributes.putClientErrorCode(0);

                    Console.println(Color.YELLOW, "general failure in AUTN validation for EAP AKA^");
                }

                if (eapResponse != null) {
                    var response = new AuthenticationReject(new IEEapMessage(eapResponse));
                    Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
                }
                return;
            }
        }

        // Control received AT_MAC
        {
            var expectedMac = UeKeyManagement.calculateMacForEapAkaPrime(kaut, receivedEap);
            if (!expectedMac.equals(receivedMac)) {
                Console.println(Color.YELLOW, "AT_MAC failure in EAP AKA'. expected: %s received: %s",
                        expectedMac, receivedMac);

                var eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CLIENT_ERROR);
                eapResponse.attributes.putClientErrorCode(0);

                var response = new AuthenticationReject(new IEEapMessage(eapResponse));
                Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
                return;
            }
        }

        // Continue key derivation
        {
            var kAusf = UeKeyManagement.calculateKAusfForEapAkaPrime(mk);

            ctx.nasSecurityContext.keys.rand = receivedRand;
            ctx.nasSecurityContext.keys.res = res;
            ctx.nasSecurityContext.keys.resStar = null;
            ctx.nasSecurityContext.keys.kAusf = kAusf;
            UeKeyManagement.deriveKeysSeafAmf(ctx);
        }

        // Send Response
        {
            var akaPrimeResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CHALLENGE);
            akaPrimeResponse.attributes = new EapAttributes();
            akaPrimeResponse.attributes.putRes(res);
            akaPrimeResponse.attributes.putMac(new OctetString(new byte[16])); // Dummy mac for now
            //akaPrimeResponse.attributes.putKdf(new OctetString("0001"));

            // Calculate and put mac value
            var sendingMac = UeKeyManagement.calculateMacForEapAkaPrime(kaut, akaPrimeResponse);
            akaPrimeResponse.attributes.putMac(sendingMac);

            var response = new AuthenticationResponse();
            response.eapMessage = new IEEapMessage(akaPrimeResponse);

            Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
        }
    }

    private static void handle5gAka(SimulationContext ctx, AuthenticationRequest request) {
        NasMessage response = null;

        var rand = request.authParamRAND.value;
        var milenage = calculateMilenage(ctx.ueData, rand);
        var res = milenage.get(MilenageResult.RES);
        var ck = milenage.get(MilenageResult.CK);
        var ik = milenage.get(MilenageResult.IK);
        var ckik = OctetString.concat(ck, ik);
        var ak = milenage.get(MilenageResult.AK);
        var mac = milenage.get(MilenageResult.MAC_A);

        var autnCheck = UeAuthentication.validateAutn(ak, mac, request.authParamAUTN.value);
        if (autnCheck == AutnValidationRes.OK) {

            // Derive keys
            var snn = ctx.ueData.snn;
            var sqnXorAk = OctetString.xor(ctx.ueData.sqn, ak);
            ctx.nasSecurityContext.keys.rand = rand;
            ctx.nasSecurityContext.keys.res = res;
            ctx.nasSecurityContext.keys.resStar = UeKeyManagement.calculateResStar(ckik, snn, rand, res);
            ctx.nasSecurityContext.keys.kAusf = UeKeyManagement.calculateKAusfFor5gAka(ck, ik, snn, sqnXorAk);
            UeKeyManagement.deriveKeysSeafAmf(ctx);

            // Prepare response
            response = new AuthenticationResponse(
                    new IEAuthenticationResponseParameter(ctx.nasSecurityContext.keys.resStar), null);

        } else if (autnCheck == AutnValidationRes.MAC_FAILURE) {
            response = new AuthenticationFailure(EMmCause.MAC_FAILURE);
            Console.println(Color.YELLOW, "MAC_FAILURE in AUTN validation for 5G AKA");
        } else if (autnCheck == AutnValidationRes.SYNCHRONISATION_FAILURE) {
            // todo
            Console.println(Color.YELLOW, "SYNCHRONISATION_FAILURE case not implemented yet in AUTN validation");
        } else {
            // Other errors
            response = new AuthenticationFailure(EMmCause.UNSPECIFIED_PROTOCOL_ERROR);
            Console.println(Color.YELLOW, "Unspecified error in AUTN validation for 5G AKA");
        }

        if (response != null) {
            Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
        }
    }

    private static AutnValidationRes validateAutn(OctetString ak, OctetString mac, OctetString autn) {
        // Decode AUTN
        var receivedSQNxorAK = autn.substring(0, 6);
        var receivedSQN = OctetString.xor(receivedSQNxorAK, ak);
        var receivedAMF = autn.substring(6, 2);
        var receivedMAC = autn.substring(8, 8);

        // Check MAC
        if (!receivedMAC.equals(mac)) {
            return AutnValidationRes.MAC_FAILURE;
        }

        // TS 33.501: An ME accessing 5G shall check during authentication that the "separation bit" in the AMF field
        // of AUTN is set to 1. The "separation bit" is bit 0 of the AMF field of AUTN.
        if (!BitString.from(receivedAMF).getB(0)) {
            return AutnValidationRes.AMF_SEPARATION_BIT_FAILURE;
        }

        // Verify that the received sequence number SQN is in the correct range
        if (!checkSqn(receivedSQN)) {
            return AutnValidationRes.SYNCHRONISATION_FAILURE;
        }

        return AutnValidationRes.OK;
    }

    private static Map<MilenageResult, OctetString> calculateMilenage(UeData ueData, OctetString rand) {
        var factory = BigIntegerBufferFactory.getInstance();
        var cipher = Ciphers.createRijndaelCipher(ueData.key.toByteArray());
        byte[] opc = threegpp.milenage.Milenage.calculateOPc(ueData.op.toByteArray(), cipher, factory);
        var milenage = new threegpp.milenage.Milenage<>(opc, cipher, factory);
        try {
            var calc = milenage.calculateAll(rand.toByteArray(), ueData.sqn.toByteArray(), ueData.amf.toByteArray(), Executors.newCachedThreadPool());
            var res = new HashMap<MilenageResult, OctetString>();
            for (var entry : calc.entrySet()) {
                res.put(entry.getKey(), new OctetString(entry.getValue()));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean checkSqn(OctetString sqn) {
        // TODO:
        //  Verify the freshness of sequence numbers to determine whether the specified sequence number is
        //  in the correct range and acceptable by the USIM. See 3GPP TS 33.102, Annex C.2.
        return true;
    }

    public static void handleAuthenticationResult(SimulationContext ctx, AuthenticationResult message) {
        Console.println(Color.BLUE, "Authentication result received");
    }
}
