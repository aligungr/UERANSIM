package com.runsim.backend.nas;

import com.runsim.backend.nas.exceptions.InvalidValueException;
import com.runsim.backend.nas.exceptions.NotImplementedException;
import com.runsim.backend.nas.types.*;

public class NasPduParser {
    private final NasInputStream data;

    public NasPduParser(byte[] data) {
        this.data = new NasInputStream(data);
    }

    public NasMessage parseNasMessage() {
        var epd = parseExtendedProtocolDiscriminator();
        if (epd.equals(ExtendedProtocolDiscriminator.SESSION_MANAGEMENT_MESSAGES)) {
            return parseSessionManagementMessage();
        } else {
            return parseMobilityManagementMessage();
        }
    }

    private ExtendedProtocolDiscriminator parseExtendedProtocolDiscriminator() {
        var epd = ExtendedProtocolDiscriminator.fromValue(data.readOctet());
        if (epd == null) throw new InvalidValueException(ExtendedProtocolDiscriminator.class);
        return epd;
    }

    private NasMessage parseSessionManagementMessage() {
        throw new NotImplementedException("session management messages not implemented yet");
    }

    private NasMessage parseMobilityManagementMessage() {
        var sht = parseSecurityHeaderType();
        if (!sht.equals(SecurityHeaderType.NOT_PROTECTED))
            throw new NotImplementedException("security protected 5GS MM NAS messages not implemented yet");

        return parsePlainNasMessage(ExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES, sht);
    }

    private SecurityHeaderType parseSecurityHeaderType() {
        int value = data.readOctet();
        value &= 0xF; // Bits 1-4 are security header type, bits 5-8 are spare bits.
        var sht = SecurityHeaderType.fromValue(value);
        if (sht == null) throw new InvalidValueException(SecurityHeaderType.class);
        return sht;
    }

    private PlainNasMessage parsePlainNasMessage(ExtendedProtocolDiscriminator epd, SecurityHeaderType sht) {
        var plainNasMessage = new PlainNasMessage();
        plainNasMessage.setExtendedProtocolDiscriminator(epd);
        plainNasMessage.setSecurityHeaderType(sht);

        var messageType = parseMessageType();
        plainNasMessage.setMessageType(messageType);

        throw new NotImplementedException("plain nas messages not implemented yet");
        // return plainNasMessage;
    }

    private MessageType parseMessageType() {
        var mt = MessageType.fromValue(data.readOctet());
        if (mt == null) throw new InvalidValueException(MessageType.class);
        return mt;
    }
}