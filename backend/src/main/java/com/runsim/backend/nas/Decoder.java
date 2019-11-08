package com.runsim.backend.nas;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.NasValue;
import com.runsim.backend.nas.core.ies.InformationElement;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.nas.impl.enums.EIdentityType;
import com.runsim.backend.nas.impl.enums.ESupiFormat;
import com.runsim.backend.nas.impl.ies.*;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.bits.Bit4;

public class Decoder {

    public static <T extends NasValue> T nasValue(OctetInputStream stream, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decode(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends InformationElement1> T ie1(int halfOctet, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeIE1(new Bit4(halfOctet & 0xF));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends InformationElement> T ie2346(OctetInputStream stream, boolean ieiPresent, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeIE(stream, ieiPresent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static IE5gsMobileIdentity mobileIdentity(OctetInputStream stream, boolean iePresent) {
        int length = stream.readOctet2I();

        int flags = stream.peekOctetI();

        var typeOfIdentity = EIdentityType.fromValue(flags & 0b111);
        int isEven = (flags >> 3) & 0b1;

        if (typeOfIdentity.equals(EIdentityType.SUCI)) {
            return suciMobileIdentity(stream, length, isEven == 0);
        } else if (typeOfIdentity.equals(EIdentityType.IMEI)) {
            return new IEImeiMobileIdentity().decodeMobileIdentity(stream, length, isEven == 0);
        } else if (typeOfIdentity.equals(EIdentityType.GUTI)) {
            return new IE5gGutiMobileIdentity().decodeMobileIdentity(stream, length, isEven == 0);
        } else {
            throw new NotImplementedException("type of identity not implemented yet: " + typeOfIdentity.name);
        }
    }

    public static IESuciMobileIdentity suciMobileIdentity(OctetInputStream stream, int length, boolean isEven) {
        int flags = stream.readOctetI();

        var supiFormat = ESupiFormat.fromValue((flags >> 4) & 0b111);

        if (supiFormat.equals(ESupiFormat.IMSI))
            return new IEImsiMobileIdentity().decodeMobileIdentity(stream, length - 1, isEven);
        if (supiFormat.equals(ESupiFormat.NETWORK_SPECIFIC_IDENTIFIER))
            return new IENsaMobileIdentity().decodeMobileIdentity(stream, length - 1, isEven);
        throw new InvalidValueException(ESupiFormat.class);
    }

    public static String bcdString(OctetInputStream stream, int length, boolean skipFirst) {
        if (length == 0)
            return "";

        final var digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?', '?', '?', '?', '?', '?'};
        int offset = 0, i = 0;
        char[] arr = new char[length * 2];

        while (offset < length) {
            int octet = stream.readOctetI();
            if (!skipFirst) {
                arr[i] = digits[octet & 0x0f];
                i++;
            }
            skipFirst = false;
            octet = octet >> 4;

            if (offset == length - 1 && octet == 0x0f)
                break;
            arr[i] = digits[octet & 0x0f];
            i++;
            offset++;
        }
        return new String(arr, 0, i);
    }

    public static <T extends NasMessage> T nasMessage(OctetInputStream stream, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeMessage(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ExtensibleAuthenticationProtocol eap(OctetInputStream stream) {
        var length = stream.readOctet2();
        var eapDecoder = new EapDecoder(stream);
        return eapDecoder.decodeEAP();
    }

    public static NasMessage nasPdu(byte[] data) {
        return new NasDecoder(data).decodeNAS();
    }
}
