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
        var epd = decodeExtendedProtocolDiscriminator();
        if (epd.equals(EExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES)) {
            return decodeSessionManagementMessage();
        } else {
            return decodeMobilityManagementMessage();
        }
    }

    private EExtendedProtocolDiscriminator decodeExtendedProtocolDiscriminator() {
        var epd = EExtendedProtocolDiscriminator.fromValue(data.readOctetI());
        if (epd == null) throw new InvalidValueException(EExtendedProtocolDiscriminator.class);
        return epd;
    }

    private NasMessage decodeSessionManagementMessage() {
        throw new NotImplementedException("session management messages not implemented yet");
    }

    private NasMessage decodeMobilityManagementMessage() {
        var sht = decodeSecurityHeaderType();
        if (!sht.equals(ESecurityHeaderType.NOT_PROTECTED))
            throw new NotImplementedException("security protected 5GS MM NAS messages not implemented yet");

        return decodePlainNasMessage(EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES, sht);
    }

    private ESecurityHeaderType decodeSecurityHeaderType() {
        int value = data.readOctetI();
        value &= 0xF;
        var sht = ESecurityHeaderType.fromValue(value);
        if (sht == null) throw new InvalidValueException(ESecurityHeaderType.class);
        return sht;
    }

    private PlainNasMessage decodePlainNasMessage(EExtendedProtocolDiscriminator epd, ESecurityHeaderType sht) {
        PlainNasMessage message;

        var messageType = decodeMessageType();
        if (messageType.equals(EMessageType.AUTHENTICATION_REQUEST)) {
            message = ProtocolDecoder.nasMessage(data, AuthenticationRequest.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_REQUEST)) {
            message = ProtocolDecoder.nasMessage(data, RegistrationRequest.class);
        } else if (messageType.equals(EMessageType.AUTHENTICATION_RESPONSE)) {
            message = ProtocolDecoder.nasMessage(data, AuthenticationResponse.class);
        } else if (messageType.equals(EMessageType.IDENTITY_REQUEST)) {
            message = ProtocolDecoder.nasMessage(data, IdentityRequest.class);
        } else if (messageType.equals(EMessageType.IDENTITY_RESPONSE)) {
            message = ProtocolDecoder.nasMessage(data, IdentityResponse.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_ACCEPT)) {
            message = ProtocolDecoder.nasMessage(data, RegistrationAccept.class);
        } else if (messageType.equals(EMessageType.REGISTRATION_COMPLETE)) {
            message = ProtocolDecoder.nasMessage(data, RegistrationComplete.class);
        } else {
            throw new NotImplementedException("message type not implemented yet: " + messageType.name);
        }

        message.extendedProtocolDiscriminator = epd;
        message.securityHeaderType = sht;
        message.messageType = messageType;
        return message;
    }

    private EMessageType decodeMessageType() {
        var mt = EMessageType.fromValue(data.readOctetI());
        if (mt == null) throw new InvalidValueException(EMessageType.class);
        return mt;
    }
}