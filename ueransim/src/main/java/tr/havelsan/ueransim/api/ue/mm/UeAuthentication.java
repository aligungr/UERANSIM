/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.api.ue.mm;

import threegpp.milenage.MilenageResult;
import threegpp.milenage.biginteger.BigIntegerBufferFactory;
import threegpp.milenage.cipher.Ciphers;
import tr.havelsan.ueransim.SendingMessage;
import tr.havelsan.ueransim.api.Messaging;
import tr.havelsan.ueransim.core.NasSecurityContext;
import tr.havelsan.ueransim.core.SimulationContext;
import tr.havelsan.ueransim.enums.AutnValidationRes;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.eap.*;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfSecurityContext;
import tr.havelsan.ueransim.nas.impl.ies.IEAuthenticationResponseParameter;
import tr.havelsan.ueransim.nas.impl.ies.IEEapMessage;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.ngap2.NgapBuilder;
import tr.havelsan.ueransim.ngap2.NgapCriticality;
import tr.havelsan.ueransim.ngap2.NgapProcedure;
import tr.havelsan.ueransim.structs.UeData;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class UeAuthentication {

    private static final boolean IGNORE_CONTROLS_FAILURES = false;
    private static final boolean USE_SQN_HACK = true; // todo

    public static void handleAuthenticationRequest(SimulationContext ctx, AuthenticationRequest message) {
        if (message.eapMessage != null) {
            handleAuthenticationRequestEap(ctx, message);
        } else {
            handleAuthenticationRequest5gAka(ctx, message);
        }
    }

    private static void handleAuthenticationRequestEap(SimulationContext ctx, AuthenticationRequest message) {
        Logging.funcIn("Handling: EAP AKA' Authentication Request");

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

            Logging.debug(Tag.VALUE, "received at_rand: %s", receivedRand);
            Logging.debug(Tag.VALUE, "received at_mac: %s", receivedMac);
            Logging.debug(Tag.VALUE, "received at_autn: %s", receivedAutn);
        }

        // Derive keys
        {
            if (USE_SQN_HACK) {
                Logging.warning(Tag.CONFIG, "USE_SQN_HACK: %s", USE_SQN_HACK);
            }
            if (IGNORE_CONTROLS_FAILURES) {
                Logging.warning(Tag.CONFIG, "IGNORE_CONTROLS_FAILURES: %s", IGNORE_CONTROLS_FAILURES);
            }

            if (USE_SQN_HACK) {
                ctx.ueData.sqn = OctetString.xor(receivedAutn.substring(0, 6),
                        calculateMilenage(ctx.ueData, receivedRand).get(MilenageResult.AK));
            }

            var milenage = calculateMilenage(ctx.ueData, receivedRand);
            res = milenage.get(MilenageResult.RES);
            var ck = milenage.get(MilenageResult.CK);
            var ik = milenage.get(MilenageResult.IK);
            milenageAk = milenage.get(MilenageResult.AK);
            milenageMac = milenage.get(MilenageResult.MAC_A);

            var sqnXorAk = OctetString.xor(ctx.ueData.sqn, milenageAk);
            var ckPrimeIkPrime = UeKeyManagement.calculateCkPrimeIkPrime(ck, ik, ctx.ueData.snn, sqnXorAk);
            var ckPrime = ckPrimeIkPrime[0];
            var ikPrime = ckPrimeIkPrime[1];

            mk = UeKeyManagement.calculateMk(ckPrime, ikPrime, ctx.ueData.supi);
            kaut = mk.substring(16, 32);

            Logging.debug(Tag.VALUE, "ueData.sqn: %s", ctx.ueData.sqn);
            Logging.debug(Tag.VALUE, "ueData.op: %s", ctx.ueData.op);
            Logging.debug(Tag.VALUE, "ueData.K: %s", ctx.ueData.key);
            Logging.debug(Tag.VALUE, "ueData.supi: %s", ctx.ueData.supi);
            Logging.debug(Tag.VALUE, "ueData.snn: %s", ctx.ueData.snn);
            Logging.debug(Tag.VALUE, "calculated res: %s", res);
            Logging.debug(Tag.VALUE, "calculated ck: %s", ck);
            Logging.debug(Tag.VALUE, "calculated ik: %s", ik);
            Logging.debug(Tag.VALUE, "calculated milenageAk: %s", milenageAk);
            Logging.debug(Tag.VALUE, "calculated milenageMac: %s", milenageMac);
            Logging.debug(Tag.VALUE, "calculated ckPrime: %s", ckPrime);
            Logging.debug(Tag.VALUE, "calculated ikPrime: %s", ikPrime);
            Logging.debug(Tag.VALUE, "calculated kaut: %s", kaut);
        }

        // Control received KDF
        {
            if (!IGNORE_CONTROLS_FAILURES && receivedKdf != 1) {
                ueRejectionTimers.run();

                var eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_AUTHENTICATION_REJECT);
                var response = new AuthenticationReject(new IEEapMessage(eapResponse));
                Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
            }
        }

        // Control received SSN
        {
            // todo
        }

        // Control received AUTN
        {
            var autnCheck = UeAuthentication.validateAutn(milenageAk, milenageMac, receivedAutn);
            Logging.debug(Tag.VALUE, "autnCheck: %s", autnCheck);

            if (autnCheck != AutnValidationRes.OK) {
                EapAkaPrime eapResponse = null;

                if (autnCheck == AutnValidationRes.MAC_FAILURE) {
                    eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_AUTHENTICATION_REJECT);
                } else if (autnCheck == AutnValidationRes.SYNCHRONISATION_FAILURE) {
                    // todo
                    //eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_SYNCHRONIZATION_FAILURE);
                    //eapResponse.attributes.putAuts(...);

                    Logging.warning(Tag.NOT_IMPL_YET, "feature not implemented yet: SYNCHRONISATION_FAILURE in AUTN validation for EAP AKA'");
                } else {
                    eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CLIENT_ERROR);
                    eapResponse.attributes.putClientErrorCode(0);
                }

                if (!IGNORE_CONTROLS_FAILURES && eapResponse != null) {
                    ueRejectionTimers.run();

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
                Logging.error(Tag.RSLT, "AT_MAC failure in EAP AKA'. expected: %s received: %s",
                        expectedMac, receivedMac);

                if (!IGNORE_CONTROLS_FAILURES) {
                    ueRejectionTimers.run();

                    var eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CLIENT_ERROR);
                    eapResponse.attributes.putClientErrorCode(0);

                    var response = new AuthenticationReject(new IEEapMessage(eapResponse));
                    Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
                    return;
                }
            }
        }

        // Create new partial native NAS security context and continue key derivation
        {
            var kAusf = UeKeyManagement.calculateKAusfForEapAkaPrime(mk);
            Logging.debug(Tag.VALUE, "kAusf: %s", kAusf);

            ctx.nonCurrentNsc = new NasSecurityContext(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT,
                    message.ngKSI.nasKeySetIdentifier);
            ctx.nonCurrentNsc.keys.rand = receivedRand;
            ctx.nonCurrentNsc.keys.res = res;
            ctx.nonCurrentNsc.keys.resStar = null;
            ctx.nonCurrentNsc.keys.kAusf = kAusf;

            UeKeyManagement.deriveKeysSeafAmf(ctx.ueData, ctx.nonCurrentNsc);
        }

        // Send Response
        {
            var akaPrimeResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id, ESubType.AKA_CHALLENGE);
            akaPrimeResponse.attributes = new EapAttributes();
            akaPrimeResponse.attributes.putRes(res);
            akaPrimeResponse.attributes.putMac(new OctetString(new byte[16])); // Dummy mac for now
            akaPrimeResponse.attributes.putKdf(1);

            // Calculate and put mac value
            var sendingMac = UeKeyManagement.calculateMacForEapAkaPrime(kaut, akaPrimeResponse);
            akaPrimeResponse.attributes.putMac(sendingMac);

            Logging.debug(Tag.VALUE, "sending eap at_mac: %s", sendingMac);

            var response = new AuthenticationResponse();
            response.eapMessage = new IEEapMessage(akaPrimeResponse);

            Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
        }

        Logging.funcOut();
    }

    private static void handleAuthenticationRequest5gAka(SimulationContext ctx, AuthenticationRequest request) {
        Logging.funcIn("Handling: 5G AKA Authentication Request");

        NasMessage response = null;

        if (USE_SQN_HACK) {
            Logging.warning(Tag.CONFIG, "USE_SQN_HACK: %s", USE_SQN_HACK);
        }
        if (IGNORE_CONTROLS_FAILURES) {
            Logging.warning(Tag.CONFIG, "IGNORE_CONTROLS_FAILURES: %s", IGNORE_CONTROLS_FAILURES);
        }

        var rand = request.authParamRAND.value;
        var autn = request.authParamAUTN.value;

        Logging.debug(Tag.VALUE, "received rand: %s", rand);
        Logging.debug(Tag.VALUE, "received autn: %s", autn);

        if (USE_SQN_HACK) {
            ctx.ueData.sqn = OctetString.xor(autn.substring(0, 6),
                    calculateMilenage(ctx.ueData, rand).get(MilenageResult.AK));
        }

        var milenage = calculateMilenage(ctx.ueData, rand);
        var res = milenage.get(MilenageResult.RES);
        var ck = milenage.get(MilenageResult.CK);
        var ik = milenage.get(MilenageResult.IK);
        var ckik = OctetString.concat(ck, ik);
        var milenageAk = milenage.get(MilenageResult.AK);
        var milenageMac = milenage.get(MilenageResult.MAC_A);
        var sqnXorAk = OctetString.xor(ctx.ueData.sqn, milenageAk);
        var snn = ctx.ueData.snn;

        Logging.debug(Tag.VALUE, "calculated res: %s", res);
        Logging.debug(Tag.VALUE, "calculated ck: %s", ck);
        Logging.debug(Tag.VALUE, "calculated ik: %s", ik);
        Logging.debug(Tag.VALUE, "calculated milenageAk: %s", milenageAk);
        Logging.debug(Tag.VALUE, "calculated milenageMac: %s", milenageMac);
        Logging.debug(Tag.VALUE, "used snn: %s", snn);
        Logging.debug(Tag.VALUE, "used sqn: %s", ctx.ueData.sqn);

        var autnCheck = UeAuthentication.validateAutn(milenageAk, milenageMac, autn);
        Logging.debug(Tag.VALUE, "autnCheck: %s", autnCheck);

        if (IGNORE_CONTROLS_FAILURES || autnCheck == AutnValidationRes.OK) {

            // Create new partial native NAS security context and continue with key derivation
            ctx.nonCurrentNsc = new NasSecurityContext(ETypeOfSecurityContext.NATIVE_SECURITY_CONTEXT,
                    request.ngKSI.nasKeySetIdentifier);
            ctx.nonCurrentNsc.keys.rand = rand;
            ctx.nonCurrentNsc.keys.res = res;
            ctx.nonCurrentNsc.keys.resStar = UeKeyManagement.calculateResStar(ckik, snn, rand, res);
            ctx.nonCurrentNsc.keys.kAusf = UeKeyManagement.calculateKAusfFor5gAka(ck, ik, snn, sqnXorAk);

            UeKeyManagement.deriveKeysSeafAmf(ctx.ueData, ctx.nonCurrentNsc);

            // Prepare response
            response = new AuthenticationResponse(
                    new IEAuthenticationResponseParameter(ctx.nonCurrentNsc.keys.resStar), null);

        } else if (autnCheck == AutnValidationRes.MAC_FAILURE) {
            response = new AuthenticationFailure(EMmCause.MAC_FAILURE);
        } else if (autnCheck == AutnValidationRes.SYNCHRONISATION_FAILURE) {
            Logging.error(Tag.NOT_IMPL_YET, "SYNCHRONISATION_FAILURE case not implemented yet in AUTN validation");
        } else {
            response = new AuthenticationFailure(EMmCause.UNSPECIFIED_PROTOCOL_ERROR);
        }

        if (response != null) {
            Messaging.send(ctx, new SendingMessage(new NgapBuilder(NgapProcedure.UplinkNASTransport, NgapCriticality.IGNORE), response));
        }

        Logging.funcOut();
    }

    private static AutnValidationRes validateAutn(OctetString ak, OctetString mac, OctetString autn) {
        // Decode AUTN
        var receivedSQNxorAK = autn.substring(0, 6);
        var receivedSQN = OctetString.xor(receivedSQNxorAK, ak);
        var receivedAMF = autn.substring(6, 2);
        var receivedMAC = autn.substring(8, 8);

        // Check MAC
        if (!receivedMAC.equals(mac)) {
            Logging.error(Tag.RSLT, "AUTN validation MAC mismatch. expected: %s received: %s", mac, receivedMAC);
            return AutnValidationRes.MAC_FAILURE;
        }

        // TS 33.501: An ME accessing 5G shall check during authentication that the "separation bit" in the AMF field
        // of AUTN is set to 1. The "separation bit" is bit 0 of the AMF field of AUTN.
        if (!BitString.from(receivedAMF).getB(0)) {
            Logging.error(Tag.RSLT, "AUTN validation SEP-BIT failure. expected: 0, received: %s", mac, receivedAMF);
            return AutnValidationRes.AMF_SEPARATION_BIT_FAILURE;
        }

        // Verify that the received sequence number SQN is in the correct range
        if (!checkSqn(receivedSQN)) {
            Logging.error(Tag.RSLT, "AUTN validation SQN not acceptable: %s", mac, receivedSQN);
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
        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.SUCCESS)) {
                UeAuthentication.handleEapSuccessMessage(ctx, message.eapMessage.eap);
            } else if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {
                UeAuthentication.handleEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Logging.warning(Tag.PROC, "network sent EAP with type of %s in AuthenticationResult, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }
    }

    public static void handleAuthenticationResponse(SimulationContext ctx, AuthenticationResponse message) {
        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.RESPONSE)) {
                UeAuthentication.handleEapResponseMessage(ctx, message.eapMessage.eap);
            } else {
                Logging.warning(Tag.PROC, "network sent EAP with type of %s in AuthenticationResponse, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }
    }

    public static void handleAuthenticationReject(SimulationContext ctx, AuthenticationReject message) {
        if (message.eapMessage != null) {
            if (message.eapMessage.eap.code.equals(Eap.ECode.FAILURE)) {

                ctx.ueData.storedGuti = null;
                ctx.ueData.taiList = null;
                ctx.ueData.lastVisitedRegisteredTai = null;
                ctx.currentNsc = null;
                ctx.nonCurrentNsc = null;

                UeAuthentication.handleEapFailureMessage(ctx, message.eapMessage.eap);
            } else {
                Logging.warning(Tag.PROC, "network sent EAP with type of %s in AuthenticationReject, ignoring EAP IE.",
                        message.eapMessage.eap.code.name());
            }
        }
    }

    public static void handleEapSuccessMessage(SimulationContext ctx, Eap eap) {
        Logging.funcIn("Handling: EAP-Success contained in received message");

        // do nothing

        Logging.funcOut();
    }


    public static void handleEapFailureMessage(SimulationContext ctx, Eap eap) {
        Logging.funcIn("Handling: EAP-Failure contained in received message");

        Logging.debug(Tag.PROC, "Deleting non-current NAS security context");
        ctx.nonCurrentNsc = null;

        Logging.funcOut();
    }

    public static void handleEapResponseMessage(SimulationContext ctx, Eap eap) {
        Logging.funcIn("Handling: EAP contained in received message");

        if (eap instanceof EapAkaPrime) {
            var akaPrime = (EapAkaPrime) eap;

        } else if (eap instanceof EapIdentity) {
            Logging.error(Tag.NOT_IMPL_YET, "EapIdentity handling not implemented yet");
        } else if (eap instanceof EapNotification) {
            Logging.error(Tag.NOT_IMPL_YET, "EapIdentity handling not implemented yet");
        } else {
            Logging.warning(Tag.PROC, "Network sent EAP with type: %s. Message ignoring.", eap.EAPType.name());
        }

        Logging.funcOut();
    }
}
