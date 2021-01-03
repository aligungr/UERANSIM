/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class ESmCause extends ProtocolEnum {
    public static final ESmCause INSUFFICIENT_RESOURCES
            = new ESmCause(0b00011010, "Insufficient resources");
    public static final ESmCause MISSING_OR_UNKNOWN_DNN
            = new ESmCause(0b00011011, "Missing or unknown DNN");
    public static final ESmCause UNKNOWN_PDU_SESSION_TYPE
            = new ESmCause(0b00011100, "Unknown PDU session type");
    public static final ESmCause USER_AUTHENTICATION_OR_AUTHORIZATION_FAILED
            = new ESmCause(0b00011101, "User authentication or authorization failed");
    public static final ESmCause REQUEST_REJECTED_UNSPECIFIED
            = new ESmCause(0b00011111, "Request rejected, unspecified");
    public static final ESmCause SERVICE_OPTION_TEMPORARILY_OUT_OF_ORDER
            = new ESmCause(0b00100010, "Service option temporarily out of order");
    public static final ESmCause PTI_ALREADY_IN_USE
            = new ESmCause(0b00100011, "PTI already in use");
    public static final ESmCause REGULAR_DEACTIVATION
            = new ESmCause(0b00100100, "Regular deactivation");
    public static final ESmCause REACTIVATION_REQUESTED
            = new ESmCause(0b00100111, "Reactivation requested");
    public static final ESmCause INVALID_PDU_SESSION_IDENTITY
            = new ESmCause(0b00101011, "Invalid PDU session identity");
    public static final ESmCause SEMANTIC_ERRORS_IN_PACKET_FILTERS
            = new ESmCause(0b00101100, "Semantic errors in packet filter(s)");
    public static final ESmCause SYNTACTICAL_ERROR_IN_PACKET_FILTERS
            = new ESmCause(0b00101101, "Syntactical error in packet filter(s)");
    public static final ESmCause OUT_OF_LADN_SERVICE_AREA
            = new ESmCause(0b00100110, "Out of LADN service area");
    public static final ESmCause PTI_MISMATCH
            = new ESmCause(0b00101111, "PTI mismatch");
    public static final ESmCause PDU_SESSION_TYPE_IPV4_ONLY_ALLOWED
            = new ESmCause(0b00110010, "PDU session type IPv4 only allowed");
    public static final ESmCause PDU_SESSION_TYPE_IPV6_ONLY_ALLOWED
            = new ESmCause(0b00110011, "PDU session type IPv6 only allowed");
    public static final ESmCause PDU_SESSION_DOES_NOT_EXIST
            = new ESmCause(0b00110110, "PDU session does not exist");
    public static final ESmCause INSUFFICIENT_RESOURCES_FOR_SPECIFIC_SLICE_AND_DNN
            = new ESmCause(0b01000011, "Insufficient resources for specific slice and DNN");
    public static final ESmCause NOT_SUPPORTED_SSC_MODE
            = new ESmCause(0b01000100, "Not supported SSC mode");
    public static final ESmCause INSUFFICIENT_RESOURCES_FOR_SPECIFIC_SLICE
            = new ESmCause(0b01000101, "Insufficient resources for specific slice");
    public static final ESmCause MISSING_OR_UNKNOWN_DNN_IN_A_SLICE
            = new ESmCause(0b01000110, "Missing or unknown DNN in a slice");
    public static final ESmCause INVALID_PTI_VALUE
            = new ESmCause(0b01010001, "Invalid PTI value");
    public static final ESmCause MAXIMUM_DATA_RATE_PER_UE_FOR_USER_PLANE_INTEGRITY_PROTECTION_IS_TOO_LOW
            = new ESmCause(0b01010010, "Maximum data rate per UE for user-plane integrity protection is too low");
    public static final ESmCause SEMANTIC_ERROR_IN_THE_QOS_OPERATION
            = new ESmCause(0b01010011, "Semantic error in the QoS operation");
    public static final ESmCause SYNTACTICAL_ERROR_IN_THE_QOS_OPERATION
            = new ESmCause(0b01010100, "Syntactical error in the QoS operation");
    public static final ESmCause SEMANTICALLY_INCORRECT_MESSAGE
            = new ESmCause(0b01011111, "Semantically incorrect message");
    public static final ESmCause INVALID_MANDATORY_INFORMATION
            = new ESmCause(0b01100000, "Invalid mandatory information");
    public static final ESmCause MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED
            = new ESmCause(0b01100001, "Message type non-existent or not implemented");
    public static final ESmCause MESSAGE_TYPE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE
            = new ESmCause(0b01100010, "Message type not compatible with the protocol state");
    public static final ESmCause INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED
            = new ESmCause(0b01100011, "Information element non-existent or not implemented");
    public static final ESmCause CONDITIONAL_IE_ERROR
            = new ESmCause(0b01100100, "Conditional IE error");
    public static final ESmCause MESSAGE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE
            = new ESmCause(0b01100101, "Message not compatible with the protocol state");
    public static final ESmCause PROTOCOL_ERROR_UNSPECIFIED
            = new ESmCause(0b01101111, "Protocol error, unspecified");

    private ESmCause(int value, String name) {
        super(value, name);
    }

    public static ESmCause fromValue(int value) {
        // Any other value received by the UE shall be treated as 0010 0010, "service option temporarily out of order".
        // Any other value received by the network shall be treated as 0110 1111, "protocol error, unspecified".
        // 3GPP TS 24501-f21, 9.11.4.2.1
        return fromValueGeneric(ESmCause.class, value, PROTOCOL_ERROR_UNSPECIFIED);
    }
}
