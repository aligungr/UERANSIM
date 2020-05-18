package tr.havelsan.ueransim.api;

import threegpp.milenage.MilenageResult;
import threegpp.milenage.biginteger.BigIntegerBufferFactory;
import threegpp.milenage.cipher.Ciphers;
import tr.havelsan.ueransim.contexts.SimulationContext;
import tr.havelsan.ueransim.contexts.UeData;
import tr.havelsan.ueransim.crypto.KDF;
import tr.havelsan.ueransim.enums.AutnValidationRes;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMmCause;
import tr.havelsan.ueransim.nas.impl.ies.IEAuthenticationResponseParameter;
import tr.havelsan.ueransim.nas.impl.messages.AuthenticationFailure;
import tr.havelsan.ueransim.nas.impl.messages.AuthenticationRequest;
import tr.havelsan.ueransim.nas.impl.messages.AuthenticationResponse;
import tr.havelsan.ueransim.utils.Color;
import tr.havelsan.ueransim.utils.Console;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class UeAuthentication {

    public static NasMessage handleAuthenticationRequest(SimulationContext ctx, AuthenticationRequest message) {
        // Handle EAP-AKA'
        if (message.eapMessage != null) {
            Console.println(Color.YELLOW, "EAP-AKA' not implemented yet");
            return null;
        }

        // Handle 5G-AKA

        //var ngKsi = message.ngKSI;
        var autnCheck = UeAuthentication.validateAutn(ctx.ueData, message.authParamRAND.value,
                message.authParamAUTN.value);
        switch (autnCheck) {
            case OK: {
                var resStar = UeAuthentication.calculateResStar(ctx.ueData, message.authParamRAND.value);
                return new AuthenticationResponse(new IEAuthenticationResponseParameter(resStar), null);
            }
            case MAC_FAILURE: {
                // todo
                Console.println(Color.YELLOW, "MAC_FAILURE case not implemented yet in AUTN validation");
                return null;
            }
            case SYNCHRONISATION_FAILURE: {
                // todo
                Console.println(Color.YELLOW, "SYNCHRONISATION_FAILURE case not implemented yet in AUTN validation");
                return null;
            }
            default: {
                return new AuthenticationFailure(EMmCause.UNSPECIFIED_PROTOCOL_ERROR);
            }
        }
    }

    public static AutnValidationRes validateAutn(UeData ueData, OctetString rand, OctetString autn) {
        var milenage = calculateMilenage(ueData, rand);

        // Decode AUTN
        var receivedSQNxorAK = autn.substring(0, 6);
        var receivedSQN = OctetString.xor(receivedSQNxorAK, milenage.get(MilenageResult.AK));
        var receivedAMF = autn.substring(6, 2);
        var receivedMAC = autn.substring(8, 8);

        // Check MAC
        if (!receivedMAC.equals(milenage.get(MilenageResult.MAC_A))) {
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

    public static OctetString calculateResStar(UeData ueData, OctetString rand) {
        var milenage = calculateMilenage(ueData, rand);
        var res = milenage.get(MilenageResult.RES);
        var ck = milenage.get(MilenageResult.CK);
        var ik = milenage.get(MilenageResult.IK);
        return KDF.calculateResStar(OctetString.concat(ck, ik), ueData.ssn, rand, res);
    }
}
