package com.runsim.backend.nas;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.ies.InformationElement;
import com.runsim.backend.nas.core.ies.InformationElement1;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.eap.EAP;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.enums.ESupiFormat;
import com.runsim.backend.nas.impl.ies.IEImsiMobileIdentity;
import com.runsim.backend.nas.impl.ies.IENsaMobileIdentity;
import com.runsim.backend.nas.impl.ies.IESuciMobileIdentity;
import com.runsim.backend.nas.impl.messages.*;
import com.runsim.backend.utils.OctetInputStream;
import com.runsim.backend.utils.bits.Bit4;

public class NasDecoder {

    public static NasMessage nasPdu(byte[] data) {
        return nasPdu(new OctetInputStream(data));
    }

    public static NasMessage nasPdu(OctetInputStream stream) {
        NasMessage nasMessage;

        var epd = EExtendedProtocolDiscriminator.fromValue(stream.readOctetI());
        var sht = ESecurityHeaderType.fromValue(stream.readOctetI() & 0xF);

        if (epd == null) throw new InvalidValueException(EExtendedProtocolDiscriminator.class);
        if (sht == null) throw new InvalidValueException(ESecurityHeaderType.class);

        if (epd.equals(EExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES)) {
            throw new NotImplementedException("session management messages not implemented yet");
        } else {
            PlainNasMessage plainNasMessage = decodePlainNasMessage(stream);
            if (sht.equals(ESecurityHeaderType.NOT_PROTECTED)) {
                nasMessage = plainNasMessage;
            } else {
                throw new NotImplementedException("security protected 5GS NAS messages not implemented yet");
            }
        }

        nasMessage.extendedProtocolDiscriminator = epd;
        nasMessage.securityHeaderType = sht;

        return nasMessage;
    }

    private static PlainNasMessage decodePlainNasMessage(OctetInputStream stream) {
        PlainNasMessage message;

        var messageType = EMessageType.fromValue(stream.readOctetI());
        if (messageType == null) throw new InvalidValueException(EMessageType.class);

        if (messageType.equals(EMessageType.AUTHENTICATION_REQUEST)) {
            message = NasDecoder.nasMessage(stream, AuthenticationRequest.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_REQUEST)) {
            message = NasDecoder.nasMessage(stream, RegistrationRequest.class);
        } else if (messageType.equals(EMessageType.AUTHENTICATION_RESPONSE)) {
            message = NasDecoder.nasMessage(stream, AuthenticationResponse.class);
        } else if (messageType.equals(EMessageType.IDENTITY_REQUEST)) {
            message = NasDecoder.nasMessage(stream, IdentityRequest.class);
        } else if (messageType.equals(EMessageType.IDENTITY_RESPONSE)) {
            message = NasDecoder.nasMessage(stream, IdentityResponse.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_ACCEPT)) {
            message = NasDecoder.nasMessage(stream, RegistrationAccept.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_COMPLETE)) {
            message = NasDecoder.nasMessage(stream, RegistrationComplete.class);
        } else if (messageType.equals(EMessageType.AUTHENTICATION_RESULT)) {
            message = NasDecoder.nasMessage(stream, AuthenticationResult.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_REJECT)) {
            message = NasDecoder.nasMessage(stream, RegistrationReject.class);
        } else if (messageType.equals(EMessageType.AUTHENTICATION_FAILURE)) {
            message = NasDecoder.nasMessage(stream, AuthenticationFailure.class);
        } else if (messageType.equals(EMessageType.AUTHENTICATION_REJECT)) {
            message = NasDecoder.nasMessage(stream, AuthenticationReject.class);
        } else if (messageType.equals(EMessageType.DEREGISTRATION_ACCEPT_UE_ORIGINATING)) {
            message = NasDecoder.nasMessage(stream, DeRegistrationAcceptUeOriginating.class);
        } else if (messageType.equals(EMessageType.DEREGISTRATION_ACCEPT_UE_TERMINATED)) {
            message = NasDecoder.nasMessage(stream, DeRegistrationAcceptUeTerminated.class);
        } else if (messageType.equals(EMessageType.DEREGISTRATION_REQUEST_UE_ORIGINATING)) {
            message = NasDecoder.nasMessage(stream, DeRegistrationRequestUeOriginating.class);
        } else if (messageType.equals(EMessageType.DEREGISTRATION_REQUEST_UE_TERMINATED)) {
            message = NasDecoder.nasMessage(stream, DeRegistrationRequestUeTerminated.class);
        } else {
            throw new NotImplementedException("message type not implemented yet: " + messageType.name());
        }

        message.messageType = messageType;
        return message;
    }

    private static <T extends NasMessage> T nasMessage(OctetInputStream stream, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeMessage(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ....
     *
     * @param length length is the octet length
     */
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

    public static <T extends InformationElement1> T ie1(int halfOctet, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeIE1(new Bit4(halfOctet & 0xF));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends InformationElement> T ie2346(OctetInputStream stream, Class<T> clazz) {
        try {
            T instance = clazz.getConstructor().newInstance();
            return (T) instance.decodeIE(stream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static EAP eap(OctetInputStream stream) {
        //var length = stream.readOctet2();
        return EapDecoder.eapPdu(stream);
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
}