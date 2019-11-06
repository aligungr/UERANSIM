package com.runsim.backend.nas;

import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.core.ies.InformationElement4;
import com.runsim.backend.nas.core.ies.InformationElement6;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.core.messages.NasValue;
import com.runsim.backend.nas.eap.ExtensibleAuthenticationProtocol;
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

    public static <T extends InformationElement4> T ie4(OctetInputStream stream, boolean ieiPresent, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeIE(stream, ieiPresent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends InformationElement6> T ie6(OctetInputStream stream, boolean ieiPresent, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeIE(stream, ieiPresent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
