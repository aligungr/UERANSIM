package com.runsim.backend.protocols.nas;

import com.runsim.backend.protocols.core.ProtocolEnum;

public class MessageType extends ProtocolEnum {

    /* Message Types for Mobility Management Messages */

    public static final MessageType REGISTRATION_REQUEST
            = new MessageType(0b01000001, "Registration request");
    public static final MessageType REGISTRATION_ACCEPT
            = new MessageType(0b01000010, "Registration accept");
    public static final MessageType REGISTRATION_COMPLETE
            = new MessageType(0b01000011, "Registration complete");
    public static final MessageType REGISTRATION_REJECT
            = new MessageType(0b01000100, "Registration reject");
    public static final MessageType DEREGISTRATION_REQUEST_UE_ORIGINATING
            = new MessageType(0b01000101, "Deregistration request (UE originating)");
    public static final MessageType DEREGISTRATION_ACCEPT_UE_ORIGINATING
            = new MessageType(0b01000110, "Deregistration accept (UE originating)");
    public static final MessageType DEREGISTRATION_REQUEST_UE_TERMINATED
            = new MessageType(0b01000111, "Deregistration request (UE terminated)");
    public static final MessageType DEREGISTRATION_ACCEPT_UE_TERMINATED
            = new MessageType(0b01001000, "Deregistration accept (UE terminated)");

    public static final MessageType SERVICE_REQUEST
            = new MessageType(0b01001100, "Service request");
    public static final MessageType SERVICE_REJECT
            = new MessageType(0b01001101, "Service reject");
    public static final MessageType SERVICE_ACCEPT
            = new MessageType(0b01001110, "Service accept");

    public static final MessageType CONFIGURATION_UPDATE_COMMAND
            = new MessageType(0b01010100, "Configuration update command");
    public static final MessageType CONFIGURATION_UPDATE_COMPLETE
            = new MessageType(0b01010101, "Configuration update complete");
    public static final MessageType AUTHENTICATION_REQUEST
            = new MessageType(0b01010110, "Authentication request");
    public static final MessageType AUTHENTICATION_RESPONSE
            = new MessageType(0b01010111, "Authentication response");
    public static final MessageType AUTHENTICATION_REJECT
            = new MessageType(0b01011000, "Authentication reject");
    public static final MessageType AUTHENTICATION_FAILURE
            = new MessageType(0b01011001, "Authentication failure");
    public static final MessageType AUTHENTICATION_RESULT
            = new MessageType(0b01011010, "Authentication result");
    public static final MessageType IDENTITY_REQUEST
            = new MessageType(0b01011011, "Identity request");
    public static final MessageType IDENTITY_RESPONSE
            = new MessageType(0b01011100, "Identity response");
    public static final MessageType SECURITY_MODE_COMMAND
            = new MessageType(0b01011101, "Security mode command");
    public static final MessageType SECURITY_MODE_COMPLETE
            = new MessageType(0b01011110, "Security mode complete");
    public static final MessageType SECURITY_MODE_REJECT
            = new MessageType(0b01011111, "Security mode reject");

    public static final MessageType FIVEG_MM_STATUS
            = new MessageType(0b01100100, "5GMM status");
    public static final MessageType NOTIFICATION
            = new MessageType(0b01100101, "Notification");
    public static final MessageType NOTIFICATION_RESPONSE
            = new MessageType(0b01100110, "Notification response");
    public static final MessageType UL_NAS_TRANSPORT
            = new MessageType(0b01100111, "UL NAS transport");
    public static final MessageType DL_NAS_TRANSPORT
            = new MessageType(0b01101000, "DL NAS transport");

    /* Message Types for Session Management Messages */

    public static final MessageType SESSION_ESTABLISHMENT_REQUEST
            = new MessageType(0b11000001, "PDU session establishment request");
    public static final MessageType SESSION_ESTABLISHMENT_ACCEPT
            = new MessageType(0b11000010, "PDU session establishment accept");
    public static final MessageType SESSION_ESTABLISHMENT_REJECT
            = new MessageType(0b11000011, "PDU session establishment reject");

    public static final MessageType SESSION_AUTHENTICATION_COMMAND
            = new MessageType(0b11000101, "PDU session authentication command");
    public static final MessageType SESSION_AUTHENTICATION_COMPLETE
            = new MessageType(0b11000110, "PDU session authentication complete");
    public static final MessageType SESSION_AUTHENTICATION_RESULT
            = new MessageType(0b11000111, "PDU session authentication result");

    public static final MessageType SESSION_MODIFICATION_REQUEST
            = new MessageType(0b11001001, "PDU session modification request");
    public static final MessageType SESSION_MODIFICATION_REJECT
            = new MessageType(0b11001010, "PDU session modification reject");
    public static final MessageType SESSION_MODIFICATION_COMMAND
            = new MessageType(0b11001011, "PDU session modification command");
    public static final MessageType SESSION_MODIFICATION_COMPLETE
            = new MessageType(0b11001100, "PDU session modification complete");
    public static final MessageType SESSION_MODIFICATION_COMMAND_REJECT
            = new MessageType(0b11001101, "PDU session modification command reject");

    public static final MessageType SESSION_RELEASE_REQUEST
            = new MessageType(0b11010001, "PDU session release request");
    public static final MessageType SESSION_RELEASE_REJECT
            = new MessageType(0b11010010, "PDU session release reject");
    public static final MessageType SESSION_RELEASE_COMMAND
            = new MessageType(0b11010011, "PDU session release command");
    public static final MessageType SESSION_RELEASE_COMPLETE
            = new MessageType(0b11010100, "PDU session release complete");

    public static final MessageType FIVEG_SM_STATUS
            = new MessageType(0b11010110, "5GSM status");


    private MessageType(int value, String name) {
        super(value, name);
    }

    public static MessageType fromValue(int value) {
        return fromValueGeneric(MessageType.class, value, null);
    }
}
