package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.crypto.*;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.SecuredMmMessage;
import tr.havelsan.ueransim.nas.exceptions.NasSecurityException;
import tr.havelsan.ueransim.nas.impl.enums.ESecurityHeaderType;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfCipheringAlgorithm;
import tr.havelsan.ueransim.nas.impl.enums.ETypeOfIntegrityProtectionAlgorithm;
import tr.havelsan.ueransim.utils.bits.Bit;
import tr.havelsan.ueransim.utils.bits.Bit5;
import tr.havelsan.ueransim.utils.bits.BitString;
import tr.havelsan.ueransim.utils.octets.Octet4;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NasEncryption {

    public static SecuredMmMessage encrypt(NasMessage plainNasMessage, NasSecurityContext securityContext, boolean isUplink) throws NasSecurityException {
        return encrypt(NasEncoder.nasPdu(plainNasMessage), securityContext, isUplink);
    }

    public static SecuredMmMessage encrypt(byte[] plainNasMessage, NasSecurityContext securityContext, boolean isUplink) throws NasSecurityException {
        if (securityContext == null) {
            throw new NasSecurityException(NasSecurityException.NO_SECURITY_CONTEXT);
        }

        var secured = new SecuredMmMessage();
        secured.securityHeaderType = getSecurityHeaderType(securityContext);
        secured.messageAuthenticationCode = computeMac(plainNasMessage, securityContext, isUplink);
        secured.sequenceNumber = securityContext.getCount(isUplink).sqn;
        secured.plainNasMessage = new OctetString(encryptData(plainNasMessage, securityContext, isUplink));
        return secured;
    }

    public static NasMessage decrypt(SecuredMmMessage protectedNasMessage, NasSecurityContext securityContext, boolean isUplink) throws NasSecurityException {
        if (securityContext == null) {
            throw new NasSecurityException(NasSecurityException.NO_SECURITY_CONTEXT);
        }

        var mac = computeMac(protectedNasMessage.plainNasMessage.toByteArray(), securityContext, isUplink);
        if (!mac.equals(protectedNasMessage.messageAuthenticationCode)) {
            if (!securityContext.selectedAlgorithms.integrity.equals(ETypeOfIntegrityProtectionAlgorithm.IA0)) {
                throw new NasSecurityException(NasSecurityException.MAC_MISMATCH);
            }
        }
        byte[] decrypted = decryptData(protectedNasMessage, securityContext, isUplink);
        return NasDecoder.nasPdu(decrypted);
    }

    private static byte[] decryptData(SecuredMmMessage protectedNasMessage, NasSecurityContext securityContext, boolean isUplink) throws NasSecurityException {
        securityContext.countOnDecrypt(protectedNasMessage.sequenceNumber, isUplink);

        var sht = getSecurityHeaderType(securityContext);
        if (!sht.isCiphered()) {
            return copy(protectedNasMessage.plainNasMessage.toByteArray());
        }

        Octet4 count = getCount(securityContext, isUplink);
        Bit5 bearer = new Bit5(securityContext.connectionIdentifier.intValue());
        Bit direction = new Bit(isUplink ? 0 : 1);
        BitString message = BitString.from(protectedNasMessage.plainNasMessage.toByteArray());
        OctetString key = securityContext.keyNasInt;

        var alg = securityContext.selectedAlgorithms.ciphering;
        if (alg.equals(ETypeOfCipheringAlgorithm.EA1_128)) {
            return NEA1_128.decrypt(count, bearer, direction, message, key).toByteArray();
        }
        if (alg.equals(ETypeOfCipheringAlgorithm.EA2_128)) {
            return NEA2_128.decrypt(count, bearer, direction, message, key).toByteArray();
        }
        if (alg.equals(ETypeOfCipheringAlgorithm.EA3_128)) {
            return NEA3_128.decrypt(count, bearer, direction, message, key).toByteArray();
        }

        throw new NasSecurityException(NasSecurityException.INVALID_CIPHERING_ALG, alg.name());
    }

    private static Octet4 computeMac(byte[] data, NasSecurityContext securityContext, boolean isUplink) throws NasSecurityException {
        var alg = securityContext.selectedAlgorithms.integrity;
        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA0)) {
            return new Octet4(0);
        }

        Octet4 count = getCount(securityContext, isUplink);
        Bit5 bearer = new Bit5(securityContext.connectionIdentifier.intValue());
        Bit direction = new Bit(isUplink ? 0 : 1);
        BitString message = BitString.from(data);
        OctetString key = securityContext.keyNasInt;

        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA1_128)) {
            return NIA1_128.computeMac(count, bearer, direction, message, key);
        }
        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA2_128)) {
            return NIA2_128.computeMac(count, bearer, direction, message, key);
        }
        if (alg.equals(ETypeOfIntegrityProtectionAlgorithm.IA3_128)) {
            return NIA3_128.computeMac(count, bearer, direction, message, key);
        }

        throw new NasSecurityException(NasSecurityException.INVALID_INTEGRITY_ALG, alg.name());
    }

    private static Octet4 getCount(NasSecurityContext securityContext, boolean isUplink) {
        var lc = securityContext.getCount(isUplink);

        long value = 0;
        value |= lc.overflow.longValue();
        value <<= 8;
        value |= lc.sqn.longValue();
        return new Octet4(value);
    }

    private static ESecurityHeaderType getSecurityHeaderType(NasSecurityContext securityContext) {
        var encKey = securityContext.keyNasEnc;
        var intKey = securityContext.keyNasInt;

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

    private static byte[] encryptData(byte[] data, NasSecurityContext securityContext, boolean isUplink) throws NasSecurityException {
        var sht = getSecurityHeaderType(securityContext);
        if (!sht.isCiphered()) {
            return copy(data);
        }

        Octet4 count = getCount(securityContext, isUplink);
        Bit5 bearer = new Bit5(securityContext.connectionIdentifier.intValue());
        Bit direction = new Bit(isUplink ? 0 : 1);
        BitString message = BitString.from(data);
        OctetString key = securityContext.keyNasInt;

        byte[] result;

        var alg = securityContext.selectedAlgorithms.ciphering;
        if (alg.equals(ETypeOfCipheringAlgorithm.EA1_128)) {
            result = NEA1_128.encrypt(count, bearer, direction, message, key).toByteArray();
        } else if (alg.equals(ETypeOfCipheringAlgorithm.EA2_128)) {
            result = NEA2_128.encrypt(count, bearer, direction, message, key).toByteArray();
        } else if (alg.equals(ETypeOfCipheringAlgorithm.EA3_128)) {
            result = NEA3_128.encrypt(count, bearer, direction, message, key).toByteArray();
        } else {
            result = null;
        }

        securityContext.countOnEncrypt(isUplink);

        if (result == null) {
            throw new NasSecurityException(NasSecurityException.INVALID_CIPHERING_ALG, alg.name());
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
