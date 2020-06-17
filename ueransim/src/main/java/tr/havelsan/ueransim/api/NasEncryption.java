package tr.havelsan.ueransim.api;

import tr.havelsan.ueransim.core.NasSecurityContext;
import tr.havelsan.ueransim.crypto.*;
import tr.havelsan.ueransim.nas.NasDecoder;
import tr.havelsan.ueransim.nas.NasEncoder;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.SecuredMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.utils.Logging;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NasEncryption {

    public static SecuredMmMessage encrypt(NasMessage plainNasMessage, NasSecurityContext securityContext) {
        return encrypt(NasEncoder.nasPdu(plainNasMessage), securityContext);
    }

    private static SecuredMmMessage encrypt(byte[] plainNasMessage, NasSecurityContext securityContext) {
        var secured = new SecuredMmMessage();
        secured.securityHeaderType = getSecurityHeaderType(securityContext);
        secured.messageAuthenticationCode = computeMac(securityContext.uplinkCount.sqn, new OctetString(plainNasMessage), securityContext, true);
        secured.sequenceNumber = securityContext.uplinkCount.sqn;
        secured.plainNasMessage = new OctetString(encryptData(plainNasMessage, securityContext));
        return secured;
    }

    public static NasMessage decrypt(SecuredMmMessage protectedNasMessage, NasSecurityContext securityContext) {
        Logging.funcIn("NasEncryption.decrypt");

        var mac = computeMac(protectedNasMessage.sequenceNumber, protectedNasMessage.plainNasMessage,
                securityContext, false);

        Logging.debug(Tag.VALUE, "computed mac: %s", mac);
        Logging.debug(Tag.VALUE, "mac received in message: %s", protectedNasMessage.messageAuthenticationCode);

        if (!mac.equals(protectedNasMessage.messageAuthenticationCode)) {
            if (!securityContext.selectedAlgorithms.integrity.equals(ETypeOfIntegrityProtectionAlgorithm.IA0)) {
                Logging.funcOut();
                return null;
            }
        }

        securityContext.downlinkCount.sqn = protectedNasMessage.sequenceNumber;

        byte[] decrypted = decryptData(protectedNasMessage, securityContext);

        Logging.funcOut();
        return NasDecoder.nasPdu(decrypted);
    }

    private static byte[] decryptData(SecuredMmMessage protectedNasMessage, NasSecurityContext securityContext) {
        Logging.funcIn("NasEncryption.decryptData");

        securityContext.countOnDecrypt(protectedNasMessage.sequenceNumber);

        var sht = getSecurityHeaderType(securityContext);
        if (!sht.isCiphered()) {
            Logging.funcOut();
            return copy(protectedNasMessage.plainNasMessage.toByteArray());
        }

        Octet4 count = securityContext.downlinkCount.toOctet4();
        Bit5 bearer = new Bit5(securityContext.connectionIdentifier.intValue());
        Bit direction = Bit.ONE;
        BitString message = BitString.from(protectedNasMessage.plainNasMessage.toByteArray());
        OctetString key = securityContext.keys.kNasEnc;

        var alg = securityContext.selectedAlgorithms.ciphering;
        if (alg.equals(ETypeOfCipheringAlgorithm.EA0)) {
            Logging.funcOut();
            return copy(protectedNasMessage.plainNasMessage.toByteArray());
        }
        if (alg.equals(ETypeOfCipheringAlgorithm.EA1_128)) {
            Logging.funcOut();
            return NEA1_128.decrypt(count, bearer, direction, message, key).toByteArray();
        }
        if (alg.equals(ETypeOfCipheringAlgorithm.EA2_128)) {
            Logging.funcOut();
            return NEA2_128.decrypt(count, bearer, direction, message, key).toByteArray();
        }
        if (alg.equals(ETypeOfCipheringAlgorithm.EA3_128)) {
            Logging.funcOut();
            return NEA3_128.decrypt(count, bearer, direction, message, key).toByteArray();
        }

        Logging.funcOut();
        throw new RuntimeException("invalid ciphering alg");
    }

    private static Octet4 computeMac(Octet sqn, OctetString plainMessage, NasSecurityContext securityContext, boolean isUplink) {
        Logging.funcIn("Computing Mac");

        var data = OctetString.concat(new OctetString(sqn), plainMessage);

        var alg = securityContext.selectedAlgorithms.integrity;

        Logging.debug(Tag.VALUE, "alg: %s", alg);

        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA0)) {
            Logging.funcOut();
            return new Octet4(0);
        }

        Octet4 count = securityContext.uplinkCount.toOctet4();
        Bit5 bearer = new Bit5(securityContext.connectionIdentifier.intValue());
        Bit direction = new Bit(isUplink ? 0 : 1);
        BitString message = BitString.from(data);
        OctetString key = securityContext.keys.kNasInt;

        Logging.debug(Tag.VALUE, "count: %s", count);
        Logging.debug(Tag.VALUE, "bearer: %s", bearer);
        Logging.debug(Tag.VALUE, "direction: %s", direction);
        Logging.debug(Tag.VALUE, "message: %s", message.toHexString(false));
        Logging.debug(Tag.VALUE, "key: %s", key);

        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA1_128)) {
            Logging.funcOut();
            return NIA1_128.computeMac(count, bearer, direction, message, key);
        }
        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA2_128)) {
            Logging.funcOut();
            return NIA2_128.computeMac(count, bearer, direction, message, key);
        }
        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA3_128)) {
            Logging.funcOut();
            return NIA3_128.computeMac(count, bearer, direction, message, key);
        }

        Logging.funcOut();
        throw new RuntimeException("invalid integrity alg");
    }

    private static ESecurityHeaderType getSecurityHeaderType(NasSecurityContext securityContext) {
        var encKey = securityContext.keys.kNasEnc;
        var intKey = securityContext.keys.kNasInt;

        boolean ciphered = encKey != null && encKey.length > 0;
        boolean integrityProtected = intKey != null && intKey.length > 0;
        boolean isNew = securityContext.isNew;

        if (!ciphered && !integrityProtected) {
            return ESecurityHeaderType.NOT_PROTECTED;
        }
        if (ciphered) {
            return isNew ? ESecurityHeaderType.INTEGRITY_PROTECTED_AND_CIPHERED_WITH_SECURITY_CONTEXT :
                    ESecurityHeaderType.INTEGRITY_PROTECTED_AND_CIPHERED;
        }
        return isNew ? ESecurityHeaderType.INTEGRITY_PROTECTED_WITH_SECURITY_CONTEXT :
                ESecurityHeaderType.INTEGRITY_PROTECTED;
    }

    private static byte[] encryptData(byte[] data, NasSecurityContext securityContext) {
        var sht = getSecurityHeaderType(securityContext);
        if (!sht.isCiphered()) {
            return copy(data);
        }

        Octet4 count = securityContext.uplinkCount.toOctet4();
        Bit5 bearer = new Bit5(securityContext.connectionIdentifier.intValue());
        Bit direction = Bit.ZERO;
        BitString message = BitString.from(data);
        OctetString key = securityContext.keys.kNasEnc;

        byte[] result;

        var alg = securityContext.selectedAlgorithms.ciphering;
        if (alg.equals(ETypeOfCipheringAlgorithm.EA0)) {
            result = copy(data);
        } else if (alg.equals(ETypeOfCipheringAlgorithm.EA1_128)) {
            result = NEA1_128.encrypt(count, bearer, direction, message, key).toByteArray();
        } else if (alg.equals(ETypeOfCipheringAlgorithm.EA2_128)) {
            result = NEA2_128.encrypt(count, bearer, direction, message, key).toByteArray();
        } else if (alg.equals(ETypeOfCipheringAlgorithm.EA3_128)) {
            result = NEA3_128.encrypt(count, bearer, direction, message, key).toByteArray();
        } else {
            result = null;
        }

        securityContext.countOnEncrypt();

        if (result == null) {
            throw new RuntimeException("invalid ciphering alg");
        } else {
            return result;
        }
    }

    private static byte[] copy(byte[] data) {
        byte[] b = new byte[data.length];
        System.arraycopy(data, 0, b, 0, data.length);
        return b;
    }
}
