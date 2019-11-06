package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.bits.Bit4;
import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.eap.EAPDecoder;
import com.runsim.backend.protocols.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.protocols.nas.ielements.InformationElement;
import com.runsim.backend.protocols.nas.ielements.InformationElement1;
import com.runsim.backend.protocols.nas.ielements.InformationElement4;
import com.runsim.backend.protocols.nas.ielements.InformationElement6;
import com.runsim.backend.protocols.nas.messages.NasMessage;

public class DecodeUtils {
    public static InformationElement decodeInformationElement(OctetInputStream stream, Class<? extends InformationElement> clazz, boolean ieiPresent) {
        try {
            InformationElement instance = clazz.getConstructor().newInstance();
            return instance.decodeIE(stream, ieiPresent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends InformationElement1> T ie1(int octet, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeIE1(new Bit4(octet & 0b1111));
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

    public static <T extends InformationElement4> T ie4(OctetInputStream stream, boolean ieiPresent, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeIE(stream, ieiPresent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeBCDString(OctetInputStream stream, int length, boolean skipFirst) {
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
        return new String(arr);
    }

    public static <T extends NasMessage> T nasMessage(OctetInputStream stream, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeMessage(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ExtensibleAuthenticationProtocol decodeExtensibleAuthenticationProtocol(OctetInputStream stream) {
        var length = stream.readOctet2();
        var eapDecoder = new EAPDecoder(stream);
        return eapDecoder.decodeEAP();
    }
}
