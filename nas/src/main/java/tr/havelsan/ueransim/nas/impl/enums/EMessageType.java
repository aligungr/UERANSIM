/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EMessageType extends ProtocolEnum {

    /* Message Types for Mobility Management Messages */

    public static final EMessageType REGISTRATION_REQUEST
            = new EMessageType(0b01000001, "Registration request");
    public static final EMessageType REGISTRATION_ACCEPT
            = new EMessageType(0b01000010, "Registration accept");
    public static final EMessageType REGISTRATION_COMPLETE
            = new EMessageType(0b01000011, "Registration complete");
    public static final EMessageType REGISTRATION_REJECT
            = new EMessageType(0b01000100, "Registration reject");
    public static final EMessageType DEREGISTRATION_REQUEST_UE_ORIGINATING
            = new EMessageType(0b01000101, "Deregistration request (UE originating)");
    public static final EMessageType DEREGISTRATION_ACCEPT_UE_ORIGINATING
            = new EMessageType(0b01000110, "Deregistration accept (UE originating)");
    public static final EMessageType DEREGISTRATION_REQUEST_UE_TERMINATED
            = new EMessageType(0b01000111, "Deregistration request (UE terminated)");
    public static final EMessageType DEREGISTRATION_ACCEPT_UE_TERMINATED
            = new EMessageType(0b01001000, "Deregistration accept (UE terminated)");

    public static final EMessageType SERVICE_REQUEST
            = new EMessageType(0b01001100, "Service request");
    public static final EMessageType SERVICE_REJECT
            = new EMessageType(0b01001101, "Service reject");
    public static final EMessageType SERVICE_ACCEPT
            = new EMessageType(0b01001110, "Service accept");

    public static final EMessageType CONFIGURATION_UPDATE_COMMAND
            = new EMessageType(0b01010100, "Configuration update command");
    public static final EMessageType CONFIGURATION_UPDATE_COMPLETE
            = new EMessageType(0b01010101, "Configuration update complete");
    public static final EMessageType AUTHENTICATION_REQUEST
            = new EMessageType(0b01010110, "Authentication request");
    public static final EMessageType AUTHENTICATION_RESPONSE
            = new EMessageType(0b01010111, "Authentication response");
    public static final EMessageType AUTHENTICATION_REJECT
            = new EMessageType(0b01011000, "Authentication reject");
    public static final EMessageType AUTHENTICATION_FAILURE
            = new EMessageType(0b01011001, "Authentication failure");
    public static final EMessageType AUTHENTICATION_RESULT
            = new EMessageType(0b01011010, "Authentication result");
    public static final EMessageType IDENTITY_REQUEST
            = new EMessageType(0b01011011, "Identity request");
    public static final EMessageType IDENTITY_RESPONSE
            = new EMessageType(0b01011100, "Identity response");
    public static final EMessageType SECURITY_MODE_COMMAND
            = new EMessageType(0b01011101, "Security mode command");
    public static final EMessageType SECURITY_MODE_COMPLETE
            = new EMessageType(0b01011110, "Security mode complete");
    public static final EMessageType SECURITY_MODE_REJECT
            = new EMessageType(0b01011111, "Security mode reject");

    public static final EMessageType FIVEG_MM_STATUS
            = new EMessageType(0b01100100, "5GMM status");
    public static final EMessageType NOTIFICATION
            = new EMessageType(0b01100101, "Notification");
    public static final EMessageType NOTIFICATION_RESPONSE
            = new EMessageType(0b01100110, "Notification response");
    public static final EMessageType UL_NAS_TRANSPORT
            = new EMessageType(0b01100111, "UL NAS transport");
    public static final EMessageType DL_NAS_TRANSPORT
            = new EMessageType(0b01101000, "DL NAS transport");

    /* Message Types for Session Management Messages */

    public static final EMessageType PDU_SESSION_ESTABLISHMENT_REQUEST
            = new EMessageType(0b11000001, "PDU session establishment request");
    public static final EMessageType PDU_SESSION_ESTABLISHMENT_ACCEPT
            = new EMessageType(0b11000010, "PDU session establishment accept");
    public static final EMessageType PDU_SESSION_ESTABLISHMENT_REJECT
            = new EMessageType(0b11000011, "PDU session establishment reject");

    public static final EMessageType PDU_SESSION_AUTHENTICATION_COMMAND
            = new EMessageType(0b11000101, "PDU session authentication command");
    public static final EMessageType PDU_SESSION_AUTHENTICATION_COMPLETE
            = new EMessageType(0b11000110, "PDU session authentication complete");
    public static final EMessageType PDU_SESSION_AUTHENTICATION_RESULT
            = new EMessageType(0b11000111, "PDU session authentication result");

    public static final EMessageType PDU_SESSION_MODIFICATION_REQUEST
            = new EMessageType(0b11001001, "PDU session modification request");
    public static final EMessageType PDU_SESSION_MODIFICATION_REJECT
            = new EMessageType(0b11001010, "PDU session modification reject");
    public static final EMessageType PDU_SESSION_MODIFICATION_COMMAND
            = new EMessageType(0b11001011, "PDU session modification command");
    public static final EMessageType PDU_SESSION_MODIFICATION_COMPLETE
            = new EMessageType(0b11001100, "PDU session modification complete");
    public static final EMessageType PDU_SESSION_MODIFICATION_COMMAND_REJECT
            = new EMessageType(0b11001101, "PDU session modification command reject");

    public static final EMessageType PDU_SESSION_RELEASE_REQUEST
            = new EMessageType(0b11010001, "PDU session release request");
    public static final EMessageType PDU_SESSION_RELEASE_REJECT
            = new EMessageType(0b11010010, "PDU session release reject");
    public static final EMessageType PDU_SESSION_RELEASE_COMMAND
            = new EMessageType(0b11010011, "PDU session release command");
    public static final EMessageType PDU_SESSION_RELEASE_COMPLETE
            = new EMessageType(0b11010100, "PDU session release complete");

    public static final EMessageType FIVEG_SM_STATUS
            = new EMessageType(0b11010110, "5GSM status");

    private EMessageType(int value, String name) {
        super(value, name);
    }

    public static EMessageType fromValue(int value) {
        return fromValueGeneric(EMessageType.class, value, null);
    }

    /**
     * Returns true if the message type is Mobility Management, or returns false if the message is Session Management.
     */
    public boolean isMobilityManagement() {
        return (value >> 7 & 1) == 0;
    }
}
