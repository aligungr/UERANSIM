/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.ue.nas.sec;

import tr.havelsan.ueransim.app.common.NasCount;
import tr.havelsan.ueransim.app.common.enums.EConnectionIdentifier;
import tr.havelsan.ueransim.crypto.*;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.core.messages.SecuredMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.EMessageType;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.console.Log;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NasEncryption {

    // todo: make directions again

    //======================================================================================================
    //                                          ENCRYPTION
    //======================================================================================================

    public static SecuredMmMessage encrypt(NasMessage plainNasMessage, NasSecurityContext securityContext) {
        EMessageType msgType;
        if (plainNasMessage instanceof PlainMmMessage) {
            msgType = ((PlainMmMessage) plainNasMessage).messageType;
        } else if (plainNasMessage instanceof PlainSmMessage) {
            msgType = ((PlainSmMessage) plainNasMessage).messageType;
        } else {
            throw new IncorrectImplementationException();
        }

        return encrypt(NasEncoder.nasPdu(plainNasMessage), msgType, securityContext);
    }

    private static SecuredMmMessage encrypt(byte[] plainNasMessage, EMessageType messageType, NasSecurityContext securityContext) {
        var sht = makeSecurityHeaderType(securityContext, messageType);
        var count = securityContext.uplinkCount;
        var cnId = securityContext.connectionIdentifier;
        var intKey = securityContext.keys.kNasInt;
        var encKey = securityContext.keys.kNasEnc;
        var intAlg = securityContext.selectedAlgorithms.integrity;
        var encAlg = securityContext.selectedAlgorithms.ciphering;

        var encryptedData = encryptData(encAlg, count, cnId, plainNasMessage, encKey);
        var mac = computeMac(intAlg, count, cnId, true, intKey, encryptedData.toByteArray());

        var secured = new SecuredMmMessage();
        secured.securityHeaderType = sht;
        secured.messageAuthenticationCode = mac;
        secured.sequenceNumber = count.sqn;
        secured.plainNasMessage = encryptedData;

        securityContext.countOnEncrypt();

        return secured;
    }

    private static OctetString encryptData(ETypeOfCipheringAlgorithm alg, NasCount count, EConnectionIdentifier cnId,
                                           byte[] data, OctetString key) {
        Bit5 bearer = new Bit5(cnId.intValue());
        Bit direction = Bit.ZERO;
        BitString message = BitString.from(data);

        byte[] result;

        if (alg.equals(ETypeOfCipheringAlgorithm.EA0)) {
            result = data;
        } else if (alg.equals(ETypeOfCipheringAlgorithm.EA1_128)) {
            result = NEA1_128.encrypt(count.toOctet4(), bearer, direction, message, key).toByteArray();
        } else if (alg.equals(ETypeOfCipheringAlgorithm.EA2_128)) {
            result = NEA2_128.encrypt(count.toOctet4(), bearer, direction, message, key).toByteArray();
        } else if (alg.equals(ETypeOfCipheringAlgorithm.EA3_128)) {
            result = NEA3_128.encrypt(count.toOctet4(), bearer, direction, message, key).toByteArray();
        } else {
            result = null;
        }

        if (result == null) {
            throw new RuntimeException("invalid ciphering alg");
        } else {
            return new OctetString(result);
        }
    }

    //======================================================================================================
    //                                          DECRYPTION
    //======================================================================================================

    public static NasMessage decrypt(SecuredMmMessage protectedNasMessage, NasSecurityContext securityContext) {
        var estimatedCount = securityContext.estimatedDownlinkCount(protectedNasMessage.sequenceNumber);

        var cnId = securityContext.connectionIdentifier;
        var intKey = securityContext.keys.kNasInt;
        var encKey = securityContext.keys.kNasEnc;
        var intAlg = securityContext.selectedAlgorithms.integrity;
        var encAlg = securityContext.selectedAlgorithms.ciphering;

        var mac = computeMac(intAlg, estimatedCount, cnId, false, intKey, protectedNasMessage.plainNasMessage.toByteArray());

        Log.debug(Tag.VALUE, "computed mac: %s", mac);
        Log.debug(Tag.VALUE, "mac received in message: %s", protectedNasMessage.messageAuthenticationCode);

        if (!mac.equals(protectedNasMessage.messageAuthenticationCode)) {
            if (!intAlg.equals(ETypeOfIntegrityProtectionAlgorithm.IA0)) {
                return null;
            }
        }

        securityContext.updateDownlinkCount(estimatedCount);

        var decryptedData = decryptData(encAlg, estimatedCount, cnId, encKey, protectedNasMessage.securityHeaderType,
                protectedNasMessage.plainNasMessage.toByteArray());

        return NasDecoder.nasPdu(decryptedData);
    }

    private static OctetString decryptData(ETypeOfCipheringAlgorithm alg, NasCount count, EConnectionIdentifier cnId,
                                           OctetString key, ESecurityHeaderType sht, byte[] data) {
        if (!sht.isCiphered()) {
            return new OctetString(data);
        }

        Bit5 bearer = new Bit5(cnId.intValue());
        Bit direction = Bit.ONE;
        BitString message = BitString.from(data);

        byte[] res = null;

        if (alg.equals(ETypeOfCipheringAlgorithm.EA0)) {
            res = data;
        }
        if (alg.equals(ETypeOfCipheringAlgorithm.EA1_128)) {
            res = NEA1_128.decrypt(count.toOctet4(), bearer, direction, message, key).toByteArray();
        }
        if (alg.equals(ETypeOfCipheringAlgorithm.EA2_128)) {
            res = NEA2_128.decrypt(count.toOctet4(), bearer, direction, message, key).toByteArray();
        }
        if (alg.equals(ETypeOfCipheringAlgorithm.EA3_128)) {
            res = NEA3_128.decrypt(count.toOctet4(), bearer, direction, message, key).toByteArray();
        }

        if (res == null) {
            throw new RuntimeException("invalid ciphering alg");
        }
        return new OctetString(res);
    }

    //======================================================================================================
    //                                          COMMON
    //======================================================================================================

    public static Octet4 computeMac(ETypeOfIntegrityProtectionAlgorithm alg, NasCount count, EConnectionIdentifier cnId,
                                    boolean isUplink, OctetString key, byte[] plainMessage) {
        var data = OctetString.concat(new OctetString(count.sqn), new OctetString(plainMessage));

        Log.debug(Tag.VALUE, "alg: %s", alg);

        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA0)) {
            return new Octet4(0);
        }

        Bit5 bearer = new Bit5(cnId.intValue());
        Bit direction = new Bit(isUplink ? 0 : 1);
        BitString message = BitString.from(data);

        Log.debug(Tag.VALUE, "count: %s", count.toOctet4());
        Log.debug(Tag.VALUE, "bearer: %s", bearer);
        Log.debug(Tag.VALUE, "direction: %s", direction);
        Log.debug(Tag.VALUE, "message: %s", message.toHexString(false));
        Log.debug(Tag.VALUE, "key: %s", key);

        Octet4 res = null;

        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA1_128)) {
            res = NIA1_128.computeMac(count.toOctet4(), bearer, direction, message, key);
        }
        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA2_128)) {
            res = NIA2_128.computeMac(count.toOctet4(), bearer, direction, message, key);
        }
        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA3_128)) {
            res = NIA3_128.computeMac(count.toOctet4(), bearer, direction, message, key);
        }

        if (res == null) {
            throw new RuntimeException("invalid integrity alg");
        }

        return res;
    }

    private static ESecurityHeaderType makeSecurityHeaderType(NasSecurityContext securityContext, EMessageType messageType) {
        var encKey = securityContext.keys.kNasEnc;
        var intKey = securityContext.keys.kNasInt;

        boolean ciphered = encKey != null && encKey.length > 0;
        boolean integrityProtected = intKey != null && intKey.length > 0;

        if (!ciphered && !integrityProtected) {
            return ESecurityHeaderType.NOT_PROTECTED;
        }

        if (messageType.equals(EMessageType.SECURITY_MODE_COMPLETE)) {
            return ESecurityHeaderType.INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT;
        }

        // SecurityModeCommand message is always sent by network, so this should be not the case actually.
        // if (messageType.equals(EMessageType.SECURITY_MODE_COMMAND)) {
        //     return ESecurityHeaderType.INTEGRITY_PROTECTED_WITH_NEW_SECURITY_CONTEXT;
        // }

        return ciphered ? ESecurityHeaderType.INTEGRITY_PROTECTED_AND_CIPHERED : ESecurityHeaderType.INTEGRITY_PROTECTED;
    }
}
