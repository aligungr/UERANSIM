package com.runsim.backend.nas;

import com.runsim.backend.exceptions.InvalidValueException;
import com.runsim.backend.exceptions.NotImplementedException;
import com.runsim.backend.nas.core.messages.NasMessage;
import com.runsim.backend.nas.core.messages.PlainNasMessage;
import com.runsim.backend.nas.impl.enums.EExtendedProtocolDiscriminator;
import com.runsim.backend.nas.impl.enums.EMessageType;
import com.runsim.backend.nas.impl.enums.ESecurityHeaderType;
import com.runsim.backend.nas.impl.messages.*;
import com.runsim.backend.utils.OctetInputStream;

/* package-private */
class NasDecoder {
    private final OctetInputStream data;

    public NasDecoder(byte[] data) {
        this(new OctetInputStream(data));
    }

    public NasDecoder(OctetInputStream data) {
        this.data = data;
    }

    public NasMessage decodeNAS() {
        NasMessage nasMessage;

        var epd = EExtendedProtocolDiscriminator.fromValue(data.readOctetI());
        var sht = ESecurityHeaderType.fromValue(data.readOctetI() & 0xF);

        if (epd == null) throw new InvalidValueException(EExtendedProtocolDiscriminator.class);
        if (sht == null) throw new InvalidValueException(ESecurityHeaderType.class);

        if (epd.equals(EExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES)) {
            throw new NotImplementedException("session management messages not implemented yet");
        } else {
            PlainNasMessage plainNasMessage = decodePlainNasMessage();
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

    private PlainNasMessage decodePlainNasMessage() {
        PlainNasMessage message;

        var messageType = EMessageType.fromValue(data.readOctetI());
        if (messageType == null) throw new InvalidValueException(EMessageType.class);

        if (messageType.equals(EMessageType.AUTHENTICATION_REQUEST)) {
            message = Decoder.nasMessage(data, AuthenticationRequest.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_REQUEST)) {
            message = Decoder.nasMessage(data, RegistrationRequest.class);
        } else if (messageType.equals(EMessageType.AUTHENTICATION_RESPONSE)) {
            message = Decoder.nasMessage(data, AuthenticationResponse.class);
        } else if (messageType.equals(EMessageType.IDENTITY_REQUEST)) {
            message = Decoder.nasMessage(data, IdentityRequest.class);
        } else if (messageType.equals(EMessageType.IDENTITY_RESPONSE)) {
            message = Decoder.nasMessage(data, IdentityResponse.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_ACCEPT)) {
            message = Decoder.nasMessage(data, RegistrationAccept.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_COMPLETE)) {
            message = Decoder.nasMessage(data, RegistrationComplete.class);
        } else if (messageType.equals(EMessageType.AUTHENTICATION_RESULT)) {
            message = Decoder.nasMessage(data, AuthenticationResult.class);
        } else {
            throw new NotImplementedException("message type not implemented yet: " + messageType.name);
        }

        message.messageType = messageType;
        return message;
    }
}