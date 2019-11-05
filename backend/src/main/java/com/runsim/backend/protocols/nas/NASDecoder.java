package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.bits.Bit3;
import com.runsim.backend.protocols.core.OctetInputStream;
import com.runsim.backend.protocols.eap.EAPDecoder;
import com.runsim.backend.protocols.eap.ExtensibleAuthenticationProtocol;
import com.runsim.backend.protocols.exceptions.InvalidValueException;
import com.runsim.backend.protocols.exceptions.NotImplementedException;

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
        var epd = ExtendedProtocolDiscriminator.fromValue(data.readOctetI());
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
        int value = data.readOctetI();
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
        } else if (messageType.equals(MessageType.REGISTRATION_REQUEST)) {
            message = decodeRegistrationRequest();
        } else {
            throw new NotImplementedException("message type not implemented yet: " + messageType.name);
        }

        message.extendedProtocolDiscriminator = epd;
        message.securityHeaderType = sht;
        message.messageType = messageType;
        return message;
    }

    private MessageType decodeMessageType() {
        var mt = MessageType.fromValue(data.readOctetI());
        if (mt == null) throw new InvalidValueException(MessageType.class);
        return mt;
    }

    private AuthenticationRequest decodeAuthenticationRequest() {
        var req = new AuthenticationRequest();
        req.ngKSI = decodeNasKeySetIdentifier();
        req.abba = decodeABBA();

        while (data.hasNext()) {
            int type = data.readOctetI();
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

    private NASKeySetIdentifier decodeNasKeySetIdentifierFromValue(int value) {
        int tscValue = (value >> 3) & 1;
        int identifierValue = value & 0b111;

        var res = new NASKeySetIdentifier();
        res.tsc = TypeOfSecurityContext.fromValue(tscValue);
        if (res.tsc == null)
            throw new InvalidValueException(TypeOfSecurityContext.class);
        res.nasKeySetIdentifier = new Bit3(identifierValue);
        return res;
    }

    private NASKeySetIdentifier decodeNasKeySetIdentifier() {
        return decodeNasKeySetIdentifierFromValue(data.readOctetI());
    }

    private ABBA decodeABBA() {
        var abba = new ABBA();
        abba.length = data.readOctet();
        abba.contents = data.readOctetString(abba.length.intValue);
        return abba;
    }

    private ExtensibleAuthenticationProtocol decodeExtensibleAuthenticationProtocol() {
        var length = data.readOctet2();
        var eapDecoder = new EAPDecoder(data);
        return eapDecoder.decodeEAP();
    }

    private RegistrationRequest decodeRegistrationRequest() {
        var req = new RegistrationRequest();

        int flags = data.readOctetI();

        int msb = (flags & 0b11110000) >> 4;
        int lsb = flags & 0b00001111;

        req.registrationType = decodeRegistrationTypeFromValue(lsb);
        req.nasKeySetIdentifier = decodeNasKeySetIdentifierFromValue(msb);
        req.mobileIdentity = decodeMobileIdentity();

        while (data.hasNext()) {
            int iei = data.readOctetI();
            // TODO: Specte iei olarak "C-", "B-", "9-" yazanlar ihmal edildi.
            switch (iei) {
                case 0x10:
                    throw new NotImplementedException("5GMM capability not implemented yet");
                case 0x2E:
                    req.ueSecurityCapability = decodeUESecurityCapability();
                    break;
                case 0x2F:
                    throw new NotImplementedException("not implemented yet: Requested NSSAI");
                case 0x52:
                    throw new NotImplementedException("not implemented yet: Last visited registered TAI");
                case 0x17:
                    throw new NotImplementedException("not implemented yet: S1 UE network capability");
                case 0x40:
                    throw new NotImplementedException("not implemented yet: Uplink data status");
                case 0x50:
                    throw new NotImplementedException("not implemented yet: PDU session status");
                case 0x2B:
                    throw new NotImplementedException("not implemented yet: UE status");
                case 0x77:
                    throw new NotImplementedException("not implemented yet: Additional GUTI");
                case 0x25:
                    throw new NotImplementedException("not implemented yet: Allowed PDU session status");
                case 0x18:
                    throw new NotImplementedException("not implemented yet: UE's usage setting");
                case 0x51:
                    throw new NotImplementedException("not implemented yet: Requested DRX parameters");
                case 0x70:
                    throw new NotImplementedException("not implemented yet: EPS NAS message container");
                case 0x7E:
                    throw new NotImplementedException("not implemented yet: LADN indication");
                case 0x7B:
                    throw new NotImplementedException("not implemented yet: Payload container");
                case 0x53:
                    throw new NotImplementedException("not implemented yet: 5GS update type");
                case 0x71:
                    throw new NotImplementedException("not implemented yet: NAS message container");
                default:
                    throw new InvalidValueException("iei is invalid: " + iei);
            }
        }

        return req;
    }

    private FiveGSRegistrationType decodeRegistrationTypeFromValue(int value) {
        var regType = new FiveGSRegistrationType();
        regType.followOnRequestPending = FOR.fromValue((value >> 3) & 0b1);
        regType.registrationType = RegistrationType.fromValue(value & 0b111);

        if (regType.followOnRequestPending == null) throw new InvalidValueException(FOR.class);
        if (regType.registrationType == null) throw new InvalidValueException(RegistrationType.class);

        return regType;
    }

    private FiveGSMobileIdentity decodeMobileIdentity() {
        int length = data.readOctet2I();
        int flags = data.peekOctetI();

        var typeOfIdentity = TypeOfIdentity.fromValue(flags & 0b111);
        if (typeOfIdentity == null) {
            // 3GPP 24501 15.2.0, 9.11.3.3:
            // "All other values are unused and shall be interpreted as "SUCI", if received by the UE."
            typeOfIdentity = TypeOfIdentity.SUCI;
        }

        if (typeOfIdentity.equals(TypeOfIdentity.SUCI)) {
            return decodeSUCI(length);
        } else {
            throw new NotImplementedException("type of identity not implemented yet: " + typeOfIdentity.name);
        }
    }

    private SUCIMobileIdentity decodeSUCI(int length) {
        int flags = data.readOctetI();

        var supiFormat = SUPIFormat.fromValue((flags >> 4) & 0b111);
        if (supiFormat == null) {
            // 3GPP 24501 15.2.0, p. 345
            // All other values are interpreted as IMSI by this version of the protocol.
            supiFormat = SUPIFormat.IMSI;
        }

        if (supiFormat.equals(SUPIFormat.IMSI))
            return decodeIMSI(length - 1);
        if (supiFormat.equals(SUPIFormat.NETWORK_SPECIFIC_IDENTIFIER))
            return decodeNetworkSpecificIdentifier(length - 1);
        throw new InvalidValueException(SUPIFormat.class);
    }

    private IMSIMobileIdentity decodeIMSI(int length) {
        var result = new IMSIMobileIdentity();

        /* Decode MCC */
        int octet1 = data.readOctetI();
        int mcc1 = octet1 & 0b1111;
        int mcc2 = (octet1 >> 4) & 0b1111;
        int octet2 = data.readOctetI();
        int mcc3 = octet2 & 0b1111;
        int mcc = 100 * mcc1 + 10 * mcc2 + mcc3;
        result.mobileCountryCode = MobileCountryCode.fromValue(mcc);

        /* Decode MNC */
        int mnc3 = (octet2 >> 4) & 0b1111;
        int octet3 = data.readOctetI();
        int mnc2 = octet3 & 0b1111;
        int mnc1 = (octet3 >> 4) & 0b1111;
        int mnc = 10 * mnc1 + mnc2;
        boolean longMnc = false;
        if ((mnc3 != 0xf) || (octet1 == 0xff && octet2 == 0xff && octet3 == 0xff)) {
            longMnc = true;
            mnc = 10 * mnc + mnc3;
        }
        if (longMnc) {
            result.mobileNetworkCode = MobileNetworkCode3.fromValue(mcc * 1000 + mnc);
        } else {
            result.mobileNetworkCode = MobileNetworkCode2.fromValue(mcc * 100 + mnc);
        }

        /* Decode routing indicator */
        int riLen = data.peekOctetI(1) == 0xFF ? 1 : 2;
        result.routingIndicator = decodeBCDString(riLen, false);
        if (riLen == 1) data.readOctet();

        /* Decode protection schema id */
        result.protectionSchemaId = ProtectionSchemeIdentifier.fromValue(data.readOctetI() & 0b1111);

        /* Decode home network public key identifier */
        result.homeNetworkPublicKeyIdentifier = new HomeNetworkPKI(data.readOctet());

        /* Decode schema output */
        String schemaOutput;
        if (result.protectionSchemaId.equals(ProtectionSchemeIdentifier.NULL_SCHEMA)) {
            result.schemaOutput = decodeBCDString(length - 7, false);
        } else {
            var range = data.readOctetString(length - 7);
            result.schemaOutput = range.toHexString();
        }

        return result;
    }

    private String decodeBCDString(int length, boolean skipFirst) {
        final var digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?', '?', '?', '?', '?', '?'};
        int offset = 0, i = 0;
        char[] arr = new char[length * 2];

        while (offset < length) {
            int octet = data.readOctetI();
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

    private NetworkSpecificIdentifierMobileIdentity decodeNetworkSpecificIdentifier(int length) {
        throw new NotImplementedException("NetworkSpecificIdentifier not implemented yet");
    }

    private UESecurityCapability decodeUESecurityCapability() {
        int length = data.readOctetI();

        var cap = new UESecurityCapability();
/*
        cap.SUPPORTED_5G_EA0 = new Bit(data.peekBit());
        cap.SUPPORTED_128_5G_EA1 = new Bit(data.peekBit());
        cap.SUPPORTED_128_5G_EA2 = new Bit(data.peekBit());
        cap.SUPPORTED_128_5G_EA3 = new Bit(data.peekBit());
        cap.SUPPORTED_5G_EA4 = new Bit(data.peekBit());
        cap.SUPPORTED_5G_EA5 = new Bit(data.peekBit());
        cap.SUPPORTED_5G_EA6 = new Bit(data.peekBit());
        cap.SUPPORTED_5G_EA7 = new Bit(data.peekBit());

        cap.SUPPORTED_5G_IA0 = new Bit(data.peekBit());
        cap.SUPPORTED_128_5G_IA1 = new Bit(data.peekBit());
        cap.SUPPORTED_128_5G_IA2 = new Bit(data.peekBit());
        cap.SUPPORTED_128_5G_IA3 = new Bit(data.peekBit());
        cap.SUPPORTED_5G_IA4 = new Bit(data.peekBit());
        cap.SUPPORTED_5G_IA5 = new Bit(data.peekBit());
        cap.SUPPORTED_5G_IA6 = new Bit(data.peekBit());
        cap.SUPPORTED_5G_IA7 = new Bit(data.peekBit());

        cap.SUPPORTED_EEA0 = new Bit(data.peekBit());
        cap.SUPPORTED_128_EEA1 = new Bit(data.peekBit());
        cap.SUPPORTED_128_EEA2 = new Bit(data.peekBit());
        cap.SUPPORTED_128_EEA3 = new Bit(data.peekBit());
        cap.SUPPORTED_EEA4 = new Bit(data.peekBit());
        cap.SUPPORTED_EEA5 = new Bit(data.peekBit());
        cap.SUPPORTED_EEA6 = new Bit(data.peekBit());
        cap.SUPPORTED_EEA7 = new Bit(data.peekBit());

        cap.SUPPORTED_EIA0 = new Bit(data.peekBit());
        cap.SUPPORTED_128_EIA1 = new Bit(data.peekBit());
        cap.SUPPORTED_128_EIA2 = new Bit(data.peekBit());
        cap.SUPPORTED_128_EIA3 = new Bit(data.peekBit());
        cap.SUPPORTED_EIA4 = new Bit(data.peekBit());
        cap.SUPPORTED_EIA5 = new Bit(data.peekBit());
        cap.SUPPORTED_EIA6 = new Bit(data.peekBit());
        cap.SUPPORTED_EIA7 = new Bit(data.peekBit());
*/
        throw new NotImplementedException("decodeUESecurityCapability not implemented yet");
    }
}