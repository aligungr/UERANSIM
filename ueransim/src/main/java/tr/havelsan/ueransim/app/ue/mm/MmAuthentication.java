/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.ue.mm;

import threegpp.milenage.MilenageResult;
import threegpp.milenage.biginteger.BigIntegerBufferFactory;
import threegpp.milenage.cipher.Ciphers;
import tr.havelsan.ueransim.app.common.configs.UeConfig;
import tr.havelsan.ueransim.app.common.enums.EAutnValidationRes;
import tr.havelsan.ueransim.app.common.simctx.UeSimContext;
import tr.havelsan.ueransim.app.ue.nas.NasSecurityContext;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.eap.*;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IEAuthenticationResponseParameter;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class MmAuthentication {

    private static final boolean IGNORE_CONTROLS_FAILURES = false;
    private static final boolean USE_SQN_HACK = true; // todo

    public static void receiveAuthenticationRequest(UeSimContext ctx, AuthenticationRequest message) {
        if (message.eapMessage != null) {
            receiveAuthenticationRequestEap(ctx, message);
        } else {
            receiveAuthenticationRequest5gAka(ctx, message);
        }
    }

    private static void receiveAuthenticationRequestEap(UeSimContext ctx, AuthenticationRequest message) {
        Log.funcIn("Handling: EAP AKA' Authentication Request");

        OctetString receivedRand, receivedMac, receivedAutn, milenageAk, milenageMac, res, mk, kaut;
        EapAkaPrime receivedEap;
        int receivedKdf;

        Runnable ueRejectionTimers = () -> {
            ctx.ueTimers.t3520.start();

            ctx.ueTimers.t3510.stop();
            ctx.ueTimers.t3517.stop();
            ctx.ueTimers.t3521.stop();
        };

        ctx.ueTimers.t3520.stop();

        // Read EAP-AKA' request
        {
            receivedEap = (EapAkaPrime) message.eapMessage.eap;
            receivedRand = receivedEap.attributes.getRand();
            receivedMac = receivedEap.attributes.getMac();
            receivedAutn = receivedEap.attributes.getAutn();
            receivedKdf = receivedEap.attributes.getKdf();

            Log.debug(Tag.VALUE, "received at_rand: %s", receivedRand);
            Log.debug(Tag.VALUE, "received at_mac: %s", receivedMac);
            Log.debug(Tag.VALUE, "received at_autn: %s", receivedAutn);
        }

        // Derive keys
        {
            if (USE_SQN_HACK) {
                //Log.warning(Tag.CONFIG, "USE_SQN_HACK: %s", USE_SQN_HACK);
            }
            if (IGNORE_CONTROLS_FAILURES) {
                Log.warning(Tag.CONFIG, "IGNORE_CONTROLS_FAILURES: %s", IGNORE_CONTROLS_FAILURES);
            }

            if (USE_SQN_HACK) {
                ctx.ueData.sqn = OctetString.xor(receivedAutn.substring(0, 6),
                        calculateMilenage(ctx.ueConfig, new OctetString("000000000000"), receivedRand).get(MilenageResult.AK));
            }

            var milenage = calculateMilenage(ctx.ueConfig, ctx.ueData.sqn, receivedRand);
            res = milenage.get(MilenageResult.RES);
            var ck = milenage.get(MilenageResult.CK);
            var ik = milenage.get(MilenageResult.IK);
            milenageAk = milenage.get(MilenageResult.AK);
            milenageMac = milenage.get(MilenageResult.MAC_A);

            var sqnXorAk = OctetString.xor(ctx.ueData.sqn, milenageAk);
            var ckPrimeIkPrime = MmKeyManagement.calculateCkPrimeIkPrime(ck, ik, ctx.ueConfig.snn, sqnXorAk);
            var ckPrime = ckPrimeIkPrime[0];
            var ikPrime = ckPrimeIkPrime[1];

            mk = MmKeyManagement.calculateMk(ckPrime, ikPrime, ctx.ueConfig.supi);
            kaut = mk.substring(16, 32);

            Log.debug(Tag.VALUE, "ueData.sqn: %s", ctx.ueData.sqn);
            Log.debug(Tag.VALUE, "ueData.op: %s", ctx.ueConfig.op);
            Log.debug(Tag.VALUE, "ueData.K: %s", ctx.ueConfig.key);
            Log.debug(Tag.VALUE, "ueData.supi: %s", ctx.ueConfig.supi);
            Log.debug(Tag.VALUE, "ueData.snn: %s", ctx.ueConfig.snn);
            Log.debug(Tag.VALUE, "calculated res: %s", res);
            Log.debug(Tag.VALUE, "calculated ck: %s", ck);
            Log.debug(Tag.VALUE, "calculated ik: %s", ik);
            Log.debug(Tag.VALUE, "calculated milenageAk: %s", milenageAk);
            Log.debug(Tag.VALUE, "calculated milenageMac: %s", milenageMac);
            Log.debug(Tag.VALUE, "calculated ckPrime: %s", ckPrime);
            Log.debug(Tag.VALUE, "calculated ikPrime: %s", ikPrime);
            Log.debug(Tag.VALUE, "calculated kaut: %s", kaut);
        }

        // Control received KDF
        {
            if (!IGNORE_CONTROLS_FAILURES && receivedKdf != 1) {
                ueRejectionTimers.run();

                var eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_AUTHENTICATION_REJECT);
                var response = new AuthenticationReject(new IEEapMessage(eapResponse));
                MobilityManagement.sendMm(ctx, response);
                return;
            }
        }

        // Control received SSN
        {
            // todo
        }

        // Control received AUTN
        {
            var autnCheck = MmAuthentication.validateAutn(ctx, milenageAk, milenageMac, receivedAutn);
            Log.debug(Tag.VALUE, "autnCheck: %s", autnCheck);

            if (autnCheck != EAutnValidationRes.OK) {
                EapAkaPrime eapResponse = null;

                if (autnCheck == EAutnValidationRes.MAC_FAILURE) {
                    eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_AUTHENTICATION_REJECT);
                } else if (autnCheck == EAutnValidationRes.SYNCHRONISATION_FAILURE) {
                    // todo
                    //eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_SYNCHRONIZATION_FAILURE);
                    //eapResponse.attributes.putAuts(...);

                    Log.warning(Tag.NIMPL, "feature not implemented yet: SYNCHRONISATION_FAILURE in AUTN validation for EAP AKA'");
                } else {
                    eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CLIENT_ERROR);
                    eapResponse.attributes.putClientErrorCode(0);
                }

                if (!IGNORE_CONTROLS_FAILURES && eapResponse != null) {
                    ueRejectionTimers.run();

                    var response = new AuthenticationReject(new IEEapMessage(eapResponse));
                    MobilityManagement.sendMm(ctx, response);
                }
                return;
            }
        }

        // Control received AT_MAC
        {
            var expectedMac = MmKeyManagement.calculateMacForEapAkaPrime(kaut, receivedEap);
            if (!expectedMac.equals(receivedMac)) {
                Log.error(Tag.FLOW, "AT_MAC failure in EAP AKA'. expected: %s received: %s",
                        expectedMac, receivedMac);

                if (!IGNORE_CONTROLS_FAILURES) {
                    ueRejectionTimers.run();

                    var eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CLIENT_ERROR);
                    eapResponse.attributes.putClientErrorCode(0);

                    var response = new AuthenticationReject(new IEEapMessage(eapResponse));
                    MobilityManagement.sendMm(ctx, response);
                    return;
                }
            }
        }

        // Create new partial native NAS security context and continue key derivation
        {
            var kAusf = MmKeyManagement.calculateKAusfForEapAkaPrime(mk);
            Log.debug(Tag.VALUE, "kAusf: %s", kAusf);

            ctx.nonCurrentNsCtx = new NasSecurityContext(ctx, ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT,
                    message.ngKSI.nasKeySetIdentifier);
            ctx.nonCurrentNsCtx.keys.rand = receivedRand;
            ctx.nonCurrentNsCtx.keys.res = res;
            ctx.nonCurrentNsCtx.keys.resStar = null;
            ctx.nonCurrentNsCtx.keys.kAusf = kAusf;
            ctx.nonCurrentNsCtx.keys.abba = message.abba.contents;

            MmKeyManagement.deriveKeysSeafAmf(ctx.ueConfig, ctx.nonCurrentNsCtx);
            Log.debug(Tag.VALUE, "kSeaf: %s", ctx.nonCurrentNsCtx.keys.kSeaf);
            Log.debug(Tag.VALUE, "kAmf: %s", ctx.nonCurrentNsCtx.keys.kAmf);
        }

        // Send Response
        {
            var akaPrimeResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CHALLENGE);
            akaPrimeResponse.attributes = new EapAttributes();
            akaPrimeResponse.attributes.putRes(res);
            akaPrimeResponse.attributes.putMac(new OctetString(new byte[16])); // Dummy mac for now
            akaPrimeResponse.attributes.putKdf(1);

            // Calculate and put mac value
            var sendingMac = MmKeyManagement.calculateMacForEapAkaPrime(kaut, akaPrimeResponse);
            akaPrimeResponse.attributes.putMac(sendingMac);

            Log.debug(Tag.VALUE, "sending eap at_mac: %s", sendingMac);

            var response = new AuthenticationResponse();
            response.eapMessage = new IEEapMessage(akaPrimeResponse);

            MobilityManagement.sendMm(ctx, response);
        }

        Log.funcOut();
    }

    private static void receiveAuthenticationRequest5gAka(UeSimContext ctx, AuthenticationRequest request) {
        Log.funcIn("Handling: 5G AKA Authentication Request");

        PlainMmMessage response = null;

        if (USE_SQN_HACK) {
            //Log.warning(Tag.CONFIG, "USE_SQN_HACK: %s", USE_SQN_HACK);
        }
        if (IGNORE_CONTROLS_FAILURES) {
            Log.warning(Tag.CONFIG, "IGNORE_CONTROLS_FAILURES: %s", IGNORE_CONTROLS_FAILURES);
        }

        var rand = request.authParamRAND.value;
        var autn = request.authParamAUTN.value;

        Log.debug(Tag.VALUE, "received rand: %s", rand);
        Log.debug(Tag.VALUE, "received autn: %s", autn);

        if (USE_SQN_HACK) {
            ctx.ueData.sqn = OctetString.xor(autn.substring(0, 6),
                    calculateMilenage(ctx.ueConfig, new OctetString("000000000000"), rand).get(MilenageResult.AK));
        }

        var milenage = calculateMilenage(ctx.ueConfig, ctx.ueData.sqn, rand);
        var res = milenage.get(MilenageResult.RES);
        var ck = milenage.get(MilenageResult.CK);
        var ik = milenage.get(MilenageResult.IK);
        var ckik = OctetString.concat(ck, ik);
        var milenageAk = milenage.get(MilenageResult.AK);
        var milenageMac = milenage.get(MilenageResult.MAC_A);
        var sqnXorAk = OctetString.xor(ctx.ueData.sqn, milenageAk);
        var snn = ctx.ueConfig.snn;

        Log.debug(Tag.VALUE, "calculated res: %s", res);
        Log.debug(Tag.VALUE, "calculated ck: %s", ck);
        Log.debug(Tag.VALUE, "calculated ik: %s", ik);
        Log.debug(Tag.VALUE, "calculated milenageAk: %s", milenageAk);
        Log.debug(Tag.VALUE, "calculated milenageMac: %s", milenageMac);
        Log.debug(Tag.VALUE, "used snn: %s", snn);
        Log.debug(Tag.VALUE, "used sqn: %s", ctx.ueData.sqn);

        var autnCheck = validateAutn(ctx, milenageAk, milenageMac, autn);
        Log.debug(Tag.VALUE, "autnCheck: %s", autnCheck);

        if (IGNORE_CONTROLS_FAILURES || autnCheck == EAutnValidationRes.OK) {

            // Create new partial native NAS security context and continue with key derivation
            ctx.nonCurrentNsCtx = new NasSecurityContext(ctx, ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT,
                    request.ngKSI.nasKeySetIdentifier);
            ctx.nonCurrentNsCtx.keys.rand = rand;
            ctx.nonCurrentNsCtx.keys.res = res;
            ctx.nonCurrentNsCtx.keys.resStar = MmKeyManagement.calculateResStar(ckik, snn, rand, res);
            ctx.nonCurrentNsCtx.keys.kAusf = MmKeyManagement.calculateKAusfFor5gAka(ck, ik, snn, sqnXorAk);
            ctx.nonCurrentNsCtx.keys.abba = request.abba.contents;

            MmKeyManagement.deriveKeysSeafAmf(ctx.ueConfig, ctx.nonCurrentNsCtx);

            // Prepare response
            response = new AuthenticationResponse(
                    new IEAuthenticationResponseParameter(ctx.nonCurrentNsCtx.keys.resStar), null);

        } else if (autnCheck == EAutnValidationRes.MAC_FAILURE) {
            response = new AuthenticationFailure(EMmCause.MAC_FAILURE);
        } else if (autnCheck == EAutnValidationRes.SYNCHRONISATION_FAILURE) {
            Log.error(Tag.NIMPL, "SYNCHRONISATION_FAILURE case not implemented yet in AUTN validation");
        } else {
            response = new AuthenticationFailure(EMmCause.UNSPECIFIED_PROTOCOL_ERROR);
        }

        if (response != null) {
            MobilityManagement.sendMm(ctx, response);
        }

        Log.funcOut();
    }

    private static EAutnValidationRes validateAutn(UeSimContext ctx, OctetString ak, OctetString mac, OctetString autn) {
        // Decode AUTN
        var receivedSQNxorAK = autn.substring(0, 6);
        var receivedSQN = OctetString.xor(receivedSQNxorAK, ak);
        var receivedAMF = autn.substring(6, 2);
        var receivedMAC = autn.substring(8, 8);

        // Check MAC
        if (!receivedMAC.equals(mac)) {
            Log.error(Tag.FLOW, "AUTN validation MAC mismatch. expected: %s received: %s", mac, receivedMAC);
            return EAutnValidationRes.MAC_FAILURE;
        }

        // TS 33.501: An ME accessing 5G shall check during authentication that the "separation bit" in the AMF field
        // of AUTN is set to 1. The "separation bit" is bit 0 of the AMF field of AUTN.
        if (!BitString.from(receivedAMF).getB(0)) {
            Log.error(Tag.FLOW, "AUTN validation SEP-BIT failure. expected: 0, received: %s", mac, receivedAMF);
            return EAutnValidationRes.AMF_SEPARATION_BIT_FAILURE;
        }

        // Verify that the received sequence number SQN is in the correct range
        if (!checkSqn(receivedSQN)) {
            Log.error(Tag.FLOW, "AUTN validation SQN not acceptable: %s", mac, receivedSQN);
            return EAutnValidationRes.SYNCHRONISATION_FAILURE;
        }

        return EAutnValidationRes.OK;
    }

    private static Map<MilenageResult, OctetString> calculateMilenage(UeConfig ueConfig, OctetString sqn, OctetString rand) {
        var factory = BigIntegerBufferFactory.getInstance();
        var cipher = Ciphers.createRijndaelCipher(ueConfig.key.toByteArray());
        byte[] opc = threegpp.milenage.Milenage.calculateOPc(ueConfig.op.toByteArray(), cipher, factory);
        var milenage = new threegpp.milenage.Milenage<>(opc, cipher, factory);
        try {
            var calc = milenage.calculateAll(rand.toByteArray(), sqn.toByteArray(),
                    ueConfig.amf.toByteArray(), Executors.newCachedThreadPool());
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

    public static void receiveAuthenticationResult(UeSimContext ctx, AuthenticationResult message) {
        Log.funcIn("Handling: Authentication Result");

        if (message.abba != null) {
            ctx.nonCurrentNsCtx.keys.abba = message.abba.contents;
        }

        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.SUCCESS)) {
                MmAuthentication.receiveEapSuccessMessage(ctx, message.eapMessage.eap);
            } else if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {
                MmAuthentication.receiveEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Log.warning(Tag.FLOW, "network sent EAP with type of %s in AuthenticationResult, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }

        Log.funcOut();
    }

    public static void receiveAuthenticationResponse(UeSimContext ctx, AuthenticationResponse message) {
        Log.funcIn("Handling: Authentication Result");

        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.RESPONSE)) {
                MmAuthentication.receiveEapResponseMessage(ctx, message.eapMessage.eap);
            } else {
                Log.warning(Tag.FLOW, "network sent EAP with type of %s in AuthenticationResponse, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }

        Log.funcOut();
    }

    public static void receiveAuthenticationReject(UeSimContext ctx, AuthenticationReject message) {
        Log.error(Tag.PROC, "Authentication Reject received.");

        Log.funcIn("Handling: Authentication Reject");

        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {

                ctx.mmCtx.storedGuti = null;
                ctx.mmCtx.taiList = null;
                ctx.mmCtx.lastVisitedRegisteredTai = null;
                ctx.currentNsCtx = null;
                ctx.nonCurrentNsCtx = null;

                MmAuthentication.receiveEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Log.warning(Tag.FLOW, "network sent EAP with type of %s in AuthenticationReject, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }

        Log.funcOut();
    }

    public static void receiveEapSuccessMessage(UeSimContext ctx, Eap eap) {
        Log.funcIn("Handling: EAP-Success contained in received message");

        // do nothing

        Log.funcOut();
    }


    public static void receiveEapFailureMessage(UeSimContext ctx, Eap eap) {
        Log.funcIn("Handling: EAP-Failure contained in received message");

        Log.debug(Tag.FLOW, "Deleting non-current NAS security context");
        ctx.nonCurrentNsCtx = null;

        Log.funcOut();
    }

    public static void receiveEapResponseMessage(UeSimContext ctx, Eap eap) {
        Log.funcIn("Handling: EAP contained in received message");

        if (eap instanceof EapAkaPrime) {
            var akaPrime = (EapAkaPrime) eap;

        } else if (eap instanceof EapIdentity) {
            Log.error(Tag.NIMPL, "EapIdentity handling not implemented yet");
        } else if (eap instanceof EapNotification) {
            Log.error(Tag.NIMPL, "EapIdentity handling not implemented yet");
        } else {
            Log.warning(Tag.FLOW, "Network sent EAP with type: %s. Message ignoring.", eap.EAPType.name());
        }

        Log.funcOut();
    }
}
