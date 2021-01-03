/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas;

import tr.havelsan.ueransim.nas.core.ies.InformationElement;
import tr.havelsan.ueransim.nas.core.ies.InformationElement1;
import tr.havelsan.ueransim.nas.core.messages.NasMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainMmMessage;
import tr.havelsan.ueransim.nas.core.messages.PlainSmMessage;
import tr.havelsan.ueransim.nas.core.messages.SecuredMmMessage;
import tr.havelsan.ueransim.nas.impl.enums.*;
import tr.havelsan.ueransim.nas.impl.messages.*;
import tr.havelsan.ueransim.utils.OctetInputStream;
import tr.havelsan.ueransim.utils.Utils;
import tr.havelsan.ueransim.utils.bits.Bit4;
import tr.havelsan.ueransim.utils.exceptions.ReservedOrInvalidValueException;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class NasDecoder {

    public static NasMessage nasPdu(byte[] data) {
        if (data == null) {
            return null;
        }
        return nasPdu(new OctetInputStream(data));
    }

    public static NasMessage nasPdu(String hex) {
        return nasPdu(Utils.hexStringToByteArray(hex));
    }

    public static NasMessage nasPdu(OctetString data) {
        if (data == null) {
            return null;
        }
        return nasPdu(data.toByteArray());
    }

    public static NasMessage nasPdu(OctetInputStream stream) {
        NasMessage nasMessage;

        var epd = EExtendedProtocolDiscriminator.fromValue(stream.readOctetI());

        if (epd.equals(EExtendedProtocolDiscriminator.MOBILITY_MANAGEMENT_MESSAGES)) {
            var sht = ESecurityHeaderType.fromValue(stream.readOctetI());
            if (sht.equals(ESecurityHeaderType.NOT_PROTECTED)) {
                var messageType = EMessageType.fromValue(stream.readOctetI());

                PlainMmMessage plainMmMessage = decodePlainMmMessage(stream, messageType);
                plainMmMessage.securityHeaderType = sht;
                plainMmMessage.messageType = messageType;

                nasMessage = plainMmMessage;
            } else {
                nasMessage = decodeSecurityProtectedNasMessage(stream, epd, sht);
            }
        } else {
            var pduSessionId = EPduSessionIdentity.fromValue(stream.readOctetI());
            var pti = EProcedureTransactionIdentity.fromValue(stream.readOctetI());
            var messageType = EMessageType.fromValue(stream.readOctetI());

            PlainSmMessage plainSmMessage = decodePlainSmMessage(stream, messageType);
            plainSmMessage.pduSessionId = pduSessionId;
            plainSmMessage.pti = pti;
            plainSmMessage.messageType = messageType;

            nasMessage = plainSmMessage;
        }

        nasMessage.extendedProtocolDiscriminator = epd;
        return nasMessage;
    }

    private static PlainMmMessage decodePlainMmMessage(OctetInputStream stream, EMessageType messageType) {
        PlainMmMessage message;

        if (messageType.equals(EMessageType.AUTHENTICATION_REQUEST)) {
            message = new AuthenticationRequest();
        } else if (messageType.equals(EMessageType.REGISTRATION_REQUEST)) {
            message = new RegistrationRequest();
        } else if (messageType.equals(EMessageType.AUTHENTICATION_RESPONSE)) {
            message = new AuthenticationResponse();
        } else if (messageType.equals(EMessageType.IDENTITY_REQUEST)) {
            message = new IdentityRequest();
        } else if (messageType.equals(EMessageType.IDENTITY_RESPONSE)) {
            message = new IdentityResponse();
        } else if (messageType.equals(EMessageType.REGISTRATION_ACCEPT)) {
            message = new RegistrationAccept();
        } else if (messageType.equals(EMessageType.REGISTRATION_COMPLETE)) {
            message = new RegistrationComplete();
        } else if (messageType.equals(EMessageType.AUTHENTICATION_RESULT)) {
            message = new AuthenticationResult();
        } else if (messageType.equals(EMessageType.REGISTRATION_REJECT)) {
            message = new RegistrationReject();
        } else if (messageType.equals(EMessageType.AUTHENTICATION_FAILURE)) {
            message = new AuthenticationFailure();
        } else if (messageType.equals(EMessageType.AUTHENTICATION_REJECT)) {
            message = new AuthenticationReject();
        } else if (messageType.equals(EMessageType.DEREGISTRATION_ACCEPT_UE_ORIGINATING)) {
            message = new DeRegistrationAcceptUeOriginating();
        } else if (messageType.equals(EMessageType.DEREGISTRATION_ACCEPT_UE_TERMINATED)) {
            message = new DeRegistrationAcceptUeTerminated();
        } else if (messageType.equals(EMessageType.DEREGISTRATION_REQUEST_UE_ORIGINATING)) {
            message = new DeRegistrationRequestUeOriginating();
        } else if (messageType.equals(EMessageType.DEREGISTRATION_REQUEST_UE_TERMINATED)) {
            message = new DeRegistrationRequestUeTerminated();
        } else if (messageType.equals(EMessageType.SERVICE_REQUEST)) {
            message = new ServiceRequest();
        } else if (messageType.equals(EMessageType.SERVICE_REJECT)) {
            message = new ServiceReject();
        } else if (messageType.equals(EMessageType.SERVICE_ACCEPT)) {
            message = new ServiceAccept();
        } else if (messageType.equals(EMessageType.CONFIGURATION_UPDATE_COMMAND)) {
            message = new ConfigurationUpdateCommand();
        } else if (messageType.equals(EMessageType.CONFIGURATION_UPDATE_COMPLETE)) {
            message = new ConfigurationUpdateComplete();
        } else if (messageType.equals(EMessageType.SECURITY_MODE_COMMAND)) {
            message = new SecurityModeCommand();
        } else if (messageType.equals(EMessageType.SECURITY_MODE_COMPLETE)) {
            message = new SecurityModeComplete();
        } else if (messageType.equals(EMessageType.SECURITY_MODE_REJECT)) {
            message = new SecurityModeReject();
        } else if (messageType.equals(EMessageType.FIVEG_MM_STATUS)) {
            message = new FiveGMmStatus();
        } else if (messageType.equals(EMessageType.NOTIFICATION)) {
            message = new Notification();
        } else if (messageType.equals(EMessageType.NOTIFICATION_RESPONSE)) {
            message = new NotificationResponse();
        } else if (messageType.equals(EMessageType.UL_NAS_TRANSPORT)) {
            message = new UlNasTransport();
        } else if (messageType.equals(EMessageType.DL_NAS_TRANSPORT)) {
            message = new DlNasTransport();
        } else {
            throw new ReservedOrInvalidValueException("Message Type", messageType);
        }

        message = message.decodeMessage(stream);

        return message;
    }

    private static PlainSmMessage decodePlainSmMessage(OctetInputStream stream, EMessageType messageType) {
        PlainSmMessage message;

        if (messageType.equals(EMessageType.PDU_SESSION_ESTABLISHMENT_REQUEST)) {
            message = new PduSessionEstablishmentRequest();
        } else if (messageType.equals(EMessageType.PDU_SESSION_ESTABLISHMENT_ACCEPT)) {
            message = new PduSessionEstablishmentAccept();
        } else if (messageType.equals(EMessageType.PDU_SESSION_ESTABLISHMENT_REJECT)) {
            message = new PduSessionEstablishmentReject();
        } else if (messageType.equals(EMessageType.PDU_SESSION_AUTHENTICATION_COMMAND)) {
            message = new PduSessionAuthenticationCommand();
        } else if (messageType.equals(EMessageType.PDU_SESSION_AUTHENTICATION_COMPLETE)) {
            message = new PduSessionAuthenticationComplete();
        } else if (messageType.equals(EMessageType.PDU_SESSION_AUTHENTICATION_RESULT)) {
            message = new PduSessionAuthenticationResult();
        } else if (messageType.equals(EMessageType.PDU_SESSION_MODIFICATION_REQUEST)) {
            message = new PduSessionModificationRequest();
        } else if (messageType.equals(EMessageType.PDU_SESSION_MODIFICATION_REJECT)) {
            message = new PduSessionModificationReject();
        } else if (messageType.equals(EMessageType.PDU_SESSION_MODIFICATION_COMMAND)) {
            message = new PduSessionModificationCommand();
        } else if (messageType.equals(EMessageType.PDU_SESSION_MODIFICATION_COMPLETE)) {
            message = new PduSessionModificationComplete();
        } else if (messageType.equals(EMessageType.PDU_SESSION_MODIFICATION_COMMAND_REJECT)) {
            message = new PduSessionModificationCommandReject();
        } else if (messageType.equals(EMessageType.PDU_SESSION_RELEASE_REQUEST)) {
            message = new PduSessionReleaseRequest();
        } else if (messageType.equals(EMessageType.PDU_SESSION_RELEASE_REJECT)) {
            message = new PduSessionReleaseReject();
        } else if (messageType.equals(EMessageType.PDU_SESSION_RELEASE_COMMAND)) {
            message = new PduSessionReleaseCommand();
        } else if (messageType.equals(EMessageType.PDU_SESSION_RELEASE_COMPLETE)) {
            message = new PduSessionReleaseComplete();
        } else if (messageType.equals(EMessageType.FIVEG_SM_STATUS)) {
            message = new FiveGSmStatus();
        } else {
            throw new ReservedOrInvalidValueException("Message Type", messageType);
        }

        message = message.decodeMessage(stream);
        return message;
    }

    private static SecuredMmMessage decodeSecurityProtectedNasMessage(OctetInputStream stream, EExtendedProtocolDiscriminator epd, ESecurityHeaderType sht) {
        var message = new SecuredMmMessage();
        message.extendedProtocolDiscriminator = epd;
        message.securityHeaderType = sht;
        message.messageAuthenticationCode = stream.readOctet4();
        message.sequenceNumber = stream.readOctet();
        message.plainNasMessage = stream.readOctetString();
        return message;
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
}