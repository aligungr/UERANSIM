package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.OctetInputStream;
import com.runsim.backend.protocols.bits.Bit3;
import com.runsim.backend.protocols.eap.EAPDecoder;
import com.runsim.backend.protocols.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.protocols.exceptions.InvalidValueException;
import com.runsim.backend.protocols.exceptions.NotImplementedException;
import com.runsim.backend.protocols.octets.Octet;
import com.runsim.backend.protocols.octets.OctetString;

public class NASDecoder {
    private final OctetInputStream data;

    public NASDecoder(byte[] data) {
        this(new OctetInputStream(data));
    }

    public NASDecoder(OctetInputStream data) {
        this.data = data;
    }

    public NASMessage decodeNAS() {
        var epd = decodeExtendedProtocolDiscriminator();
        if (epd.equals(ExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES)) {
            return decodeSessionManagementMessage();
        } else {
            return decodeMobilityManagementMessage();
        }
    }

    private ExtendedProtocolDiscriminator decodeExtendedProtocolDiscriminator() {
        var epd = ExtendedProtocolDiscriminator.fromValue(data.readOctet());
        if (epd == null) throw new InvalidValueException(ExtendedProtocolDiscriminator.class);
        return epd;
    }

    private NASMessage decodeSessionManagementMessage() {
        throw new NotImplementedException("session management messages not implemented yet");
    }

    private NASMessage decodeMobilityManagementMessage() {
        var sht = decodeSecurityHeaderType();
        if (!sht.equals(SecurityHeaderType.NOT_PROTECTED))
            throw new NotImplementedException("security protected 5GS MM NAS messages not implemented yet");

        return decodePlainNasMessage(ExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES, sht);
    }

    private SecurityHeaderType decodeSecurityHeaderType() {
        int value = data.readOctet();
        value &= 0xF;
        var sht = SecurityHeaderType.fromValue(value);
        if (sht == null) throw new InvalidValueException(SecurityHeaderType.class);
        return sht;
    }

    private PlainNASMessage decodePlainNasMessage(ExtendedProtocolDiscriminator epd, SecurityHeaderType sht) {
        PlainNASMessage message;

        var messageType = decodeMessageType();
        if (messageType.equals(MessageType.AUTHENTICATION_REQUEST)) {
            message = decodeAuthenticationRequest();
        } else {
            throw new NotImplementedException("message type not implemented yet");
        }

        message.extendedProtocolDiscriminator = epd;
        message.securityHeaderType = sht;
        message.messageType = messageType;
        return message;
    }

    private MessageType decodeMessageType() {
        var mt = MessageType.fromValue(data.readOctet());
        if (mt == null) throw new InvalidValueException(MessageType.class);
        return mt;
    }

    private AuthenticationRequest decodeAuthenticationRequest() {
        var req = new AuthenticationRequest();
        req.ngKSI = decodeNasKeySetIdentifier();
        req.abba = decodeABBA();

        while (data.hasNext()) {
            int type = data.readOctet();
            switch (type) {
                case 0x21:
                    throw new NotImplementedException("RAND not implemented yet.");
                case 0x20:
                    throw new NotImplementedException("AUTN not implemented yet.");
                case 0x78:
                    req.eap = decodeExtensibleAuthenticationProtocol();
                    break;
                default:
                    throw new InvalidValueException("Authentication request IEI is invalid: " + type);
            }
        }
        return req;
    }

    private NASKeySetIdentifier decodeNasKeySetIdentifier() {
        int value = data.readOctet();
        int tscValue = (value >> 3) & 1;
        int identifierValue = value & 0b111;

        var res = new NASKeySetIdentifier();
        res.tsc = TypeOfSecurityContext.fromValue(tscValue);
        if (res.tsc == null)
            throw new InvalidValueException(TypeOfSecurityContext.class);
        res.nasKeySetIdentifier = new Bit3(identifierValue);
        return res;
    }

    private ABBA decodeABBA() {
        var abba = new ABBA();
        abba.length = new Octet(data.readOctet());
        abba.contents = new OctetString(data.readOctets(abba.length.intValue));
        return abba;
    }

    private ExtensibleAuthenticationProtocol decodeExtensibleAuthenticationProtocol() {
        var length = data.readOctet2();
        var eapDecoder = new EAPDecoder(data);
        return eapDecoder.decodeEAP();
    }
}