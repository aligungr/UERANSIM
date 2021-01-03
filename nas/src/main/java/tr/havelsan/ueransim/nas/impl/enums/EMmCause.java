/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.nas.impl.enums;

import tr.havelsan.ueransim.nas.core.ProtocolEnum;

public class EMmCause extends ProtocolEnum {
    public static final EMmCause ILLEGAL_UE = new EMmCause(0b00000011, "Illegal UE");
    public static final EMmCause PEI_NOT_ACCEPTED = new EMmCause(0b00000101, "PEI not accepted");
    public static final EMmCause ILLEGAL_ME = new EMmCause(0b00000110, "Illegal ME");
    public static final EMmCause FIVEG_SERVICES_NOT_ALLOWED = new EMmCause(0b00000111, "5GS services not allowed");
    public static final EMmCause UE_IDENTITY_CANNOT_BE_DERIVED_FROM_NETWORK = new EMmCause(0b00001001, "UE identity cannot be derived by the network");
    public static final EMmCause IMPLICITY_DEREGISTERED = new EMmCause(0b00001010, "Implicitly de-registered");
    public static final EMmCause PLMN_NOT_ALLOWED = new EMmCause(0b00001011, "PLMN not allowed");
    public static final EMmCause TA_NOT_ALLOWED = new EMmCause(0b00001100, "Tracking area not allowed");
    public static final EMmCause ROAMING_NOT_ALLOWED_IN_TA = new EMmCause(0b00001101, "Roaming not allowed in this tracking area");
    public static final EMmCause NO_SUITIBLE_CELLS_IN_TA = new EMmCause(0b00001111, "No suitable cells in tracking area");
    public static final EMmCause MAC_FAILURE = new EMmCause(0b00010100, "MAC failure");
    public static final EMmCause SYNCH_FAILURE = new EMmCause(0b00010101, "Synch failure");
    public static final EMmCause CONGESTION = new EMmCause(0b00010110, "Congestion");
    public static final EMmCause UE_SECURITY_CAP_MISMATCH = new EMmCause(0b00010111, "UE security capabilities mismatch");
    public static final EMmCause SEC_MODE_REJECTED_UNSPECIFIED = new EMmCause(0b00011000, "Security mode rejected, unspecified");
    public static final EMmCause NON_5G_AUTHENTICATION_UNACCEPTABLE = new EMmCause(0b00011010, "Non-5G authentication unacceptable");
    public static final EMmCause N1_MODE_NOT_ALLOWED = new EMmCause(0b00011011, "N1 mode not allowed");
    public static final EMmCause RESTRICTED_NOT_SERVICE_AREA = new EMmCause(0b00011100, "Restricted service area");
    public static final EMmCause LADN_NOT_AVAILABLE = new EMmCause(0b00101011, "LADN not available");
    public static final EMmCause MAX_PDU_SESSIONS_REACHED = new EMmCause(0b01000001, "Maximum number of PDU sessions reached");
    public static final EMmCause INSUFFICIENT_RESOURCES_FOR_SLICE_AND_DNN = new EMmCause(0b01000011, "Insufficient resources for specific slice and DNN");
    public static final EMmCause INSUFFICIENT_RESOURCES_FOR_SLICE = new EMmCause(0b01000101, "Insufficient resources for specific slice");
    public static final EMmCause NGKSI_ALREADY_IN_USE = new EMmCause(0b01000111, "ngKSI already in use");
    public static final EMmCause NON_3GPP_ACCESS_TO_CN_NOT_ALLOWED = new EMmCause(0b01001000, "Non-3GPP access to 5GCN not allowed");
    public static final EMmCause SERVING_NETWORK_NOT_AUTHORIZED = new EMmCause(0b01001001, "Serving network not authorized");
    public static final EMmCause PAYLOAD_NOT_FORWARDED = new EMmCause(0b01011010, "Payload was not forwarded");
    public static final EMmCause DNN_NOT_SUPPORTED_OR_NOT_SUBSCRIBED = new EMmCause(0b01011011, "DNN not supported or not subscribed in the slice");
    public static final EMmCause INSUFFICIENT_USER_PLANE_RESOURCES = new EMmCause(0b01011100, "Insufficient user-plane resources for the PDU session");
    public static final EMmCause SEMANTICALLY_INCORRECT_MESSAGE = new EMmCause(0b01011111, "Semantically incorrect message");
    public static final EMmCause INVALID_MANDATORY_INFORMATION = new EMmCause(0b01100000, "Invalid mandatory information");
    public static final EMmCause MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED = new EMmCause(0b01100001, "Message type non-existent or not implemented");
    public static final EMmCause MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE = new EMmCause(0b01100010, "Message type not compatible with the protocol state");
    public static final EMmCause INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED = new EMmCause(0b01100011, "Information element non-existent or not implemented");
    public static final EMmCause CONDITIONAL_IE_ERROR = new EMmCause(0b01100100, "Conditional IE error");
    public static final EMmCause MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE = new EMmCause(0b01100101, "Message not compatible with the protocol state");
    public static final EMmCause UNSPECIFIED_PROTOCOL_ERROR = new EMmCause(0b01101111, "Protocol error, unspecified");

    private EMmCause(int value, String name) {
        super(value, name);
    }

    public static EMmCause fromValue(int value) {
        return fromValueGeneric(EMmCause.class, value, UNSPECIFIED_PROTOCOL_ERROR);
    }
}
