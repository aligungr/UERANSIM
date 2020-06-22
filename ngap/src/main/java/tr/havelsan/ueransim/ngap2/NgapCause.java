/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Ali Güngör (aligng1620@gmail.com)
 */

package tr.havelsan.ueransim.ngap2;

import tr.havelsan.ueransim.ngap.ngap_ies.*;

public enum NgapCause {
    RADIO_NETWORK_UNSPECIFIED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_unspecified),
    RADIO_NETWORK_TXNRELOCOVERALL_EXPIRY(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_txnrelocoverall_expiry),
    RADIO_NETWORK_SUCCESSFUL_HANDOVER(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_successful_handover),
    RADIO_NETWORK_RELEASE_DUE_TO_NGRAN_GENERATED_REASON(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_release_due_to_ngran_generated_reason),
    RADIO_NETWORK_RELEASE_DUE_TO_5GC_GENERATED_REASON(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_release_due_to_5gc_generated_reason),
    RADIO_NETWORK_HANDOVER_CANCELLED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_handover_cancelled),
    RADIO_NETWORK_PARTIAL_HANDOVER(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_partial_handover),
    RADIO_NETWORK_HO_FAILURE_IN_TARGET_5GC_NGRAN_NODE_OR_TARGET_SYSTEM(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_ho_failure_in_target_5GC_ngran_node_or_target_system),
    RADIO_NETWORK_HO_TARGET_NOT_ALLOWED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_ho_target_not_allowed),
    RADIO_NETWORK_TNGRELOCOVERALL_EXPIRY(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_tngrelocoverall_expiry),
    RADIO_NETWORK_TNGRELOCPREP_EXPIRY(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_tngrelocprep_expiry),
    RADIO_NETWORK_CELL_NOT_AVAILABLE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_cell_not_available),
    RADIO_NETWORK_UNKNOWN_TARGETID(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_unknown_targetID),
    RADIO_NETWORK_NO_RADIO_RESOURCES_AVAILABLE_IN_TARGET_CELL(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_no_radio_resources_available_in_target_cell),
    RADIO_NETWORK_UNKNOWN_LOCAL_UE_NGAP_ID(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_unknown_local_UE_NGAP_ID),
    RADIO_NETWORK_INCONSISTENT_REMOTE_UE_NGAP_ID(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_inconsistent_remote_UE_NGAP_ID),
    RADIO_NETWORK_HANDOVER_DESIRABLE_FOR_RADIO_REASON(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_handover_desirable_for_radio_reason),
    RADIO_NETWORK_TIME_CRITICAL_HANDOVER(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_time_critical_handover),
    RADIO_NETWORK_RESOURCE_OPTIMISATION_HANDOVER(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_resource_optimisation_handover),
    RADIO_NETWORK_REDUCE_LOAD_IN_SERVING_CELL(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_reduce_load_in_serving_cell),
    RADIO_NETWORK_USER_INACTIVITY(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_user_inactivity),
    RADIO_NETWORK_RADIO_CONNECTION_WITH_UE_LOST(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_radio_connection_with_ue_lost),
    RADIO_NETWORK_RADIO_RESOURCES_NOT_AVAILABLE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_radio_resources_not_available),
    RADIO_NETWORK_INVALID_QOS_COMBINATION(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_invalid_qos_combination),
    RADIO_NETWORK_FAILURE_IN_RADIO_INTERFACE_PROCEDURE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_failure_in_radio_interface_procedure),
    RADIO_NETWORK_INTERACTION_WITH_OTHER_PROCEDURE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_interaction_with_other_procedure),
    RADIO_NETWORK_UNKNOWN_PDU_SESSION_ID(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_unknown_PDU_session_ID),
    RADIO_NETWORK_UNKOWN_QOS_FLOW_ID(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_unkown_qos_flow_ID),
    RADIO_NETWORK_MULTIPLE_PDU_SESSION_ID_INSTANCES(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_multiple_PDU_session_ID_instances),
    RADIO_NETWORK_MULTIPLE_QOS_FLOW_ID_INSTANCES(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_multiple_qos_flow_ID_instances),
    RADIO_NETWORK_ENCRYPTION_AND_OR_INTEGRITY_PROTECTION_ALGORITHMS_NOT_SUPPORTED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_encryption_and_or_integrity_protection_algorithms_not_supported),
    RADIO_NETWORK_NG_INTRA_SYSTEM_HANDOVER_TRIGGERED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_ng_intra_system_handover_triggered),
    RADIO_NETWORK_NG_INTER_SYSTEM_HANDOVER_TRIGGERED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_ng_inter_system_handover_triggered),
    RADIO_NETWORK_XN_HANDOVER_TRIGGERED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_xn_handover_triggered),
    RADIO_NETWORK_NOT_SUPPORTED_5QI_VALUE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_not_supported_5QI_value),
    RADIO_NETWORK_UE_CONTEXT_TRANSFER(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_ue_context_transfer),
    RADIO_NETWORK_IMS_VOICE_EPS_FALLBACK_OR_RAT_FALLBACK_TRIGGERED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_ims_voice_eps_fallback_or_rat_fallback_triggered),
    RADIO_NETWORK_UP_INTEGRITY_PROTECTION_NOT_POSSIBLE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_up_integrity_protection_not_possible),
    RADIO_NETWORK_UP_CONFIDENTIALITY_PROTECTION_NOT_POSSIBLE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_up_confidentiality_protection_not_possible),
    RADIO_NETWORK_SLICE_NOT_SUPPORTED(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_slice_not_supported),
    RADIO_NETWORK_UE_IN_RRC_INACTIVE_STATE_NOT_REACHABLE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_ue_in_rrc_inactive_state_not_reachable),
    RADIO_NETWORK_REDIRECTION(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_redirection),
    RADIO_NETWORK_RESOURCES_NOT_AVAILABLE_FOR_THE_SLICE(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_resources_not_available_for_the_slice),
    RADIO_NETWORK_UE_MAX_INTEGRITY_PROTECTED_DATA_RATE_REASON(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_ue_max_integrity_protected_data_rate_reason),
    RADIO_NETWORK_RELEASE_DUE_TO_CN_DETECTED_MOBILITY(Cause.ASN_radioNetwork, CauseRadioNetwork.ASN_release_due_to_cn_detected_mobility),

    TRANSPORT_RESOURCE_UNAVAILABLE(Cause.ASN_transport, CauseTransport.ASN_transport_resource_unavailable),
    TRANSPORT_UNSPECIFIED(Cause.ASN_transport, CauseTransport.ASN_unspecified),

    NAS_NORMAL_RELEASE(Cause.ASN_nas, CauseNas.ASN_normal_release),
    NAS_AUTHENTICATION_FAILURE(Cause.ASN_nas, CauseNas.ASN_authentication_failure),
    NAS_DEREGISTER(Cause.ASN_nas, CauseNas.ASN_deregister),
    NAS_UNSPECIFIED(Cause.ASN_nas, CauseNas.ASN_unspecified),

    PROTOCOL_TRANSFER_SYNTAX_ERROR(Cause.ASN_protocol, CauseProtocol.ASN_transfer_syntax_error),
    PROTOCOL_ABSTRACT_SYNTAX_ERROR_REJECT(Cause.ASN_protocol, CauseProtocol.ASN_abstract_syntax_error_reject),
    PROTOCOL_ABSTRACT_SYNTAX_ERROR_IGNORE_AND_NOTIFY(Cause.ASN_protocol, CauseProtocol.ASN_abstract_syntax_error_ignore_and_notify),
    PROTOCOL_MESSAGE_NOT_COMPATIBLE_WITH_RECEIVER_STATE(Cause.ASN_protocol, CauseProtocol.ASN_message_not_compatible_with_receiver_state),
    PROTOCOL_SEMANTIC_ERROR(Cause.ASN_protocol, CauseProtocol.ASN_semantic_error),
    PROTOCOL_ABSTRACT_SYNTAX_ERROR_FALSELY_CONSTRUCTED_MESSAGE(Cause.ASN_protocol, CauseProtocol.ASN_abstract_syntax_error_falsely_constructed_message),
    PROTOCOL_UNSPECIFIED(Cause.ASN_protocol, CauseProtocol.ASN_unspecified),

    MISC_control_processing_overload(Cause.ASN_misc, CauseMisc.ASN_control_processing_overload),
    MISC_not_enough_user_plane_processing_resources(Cause.ASN_misc, CauseMisc.ASN_not_enough_user_plane_processing_resources),
    MISC_hardware_failure(Cause.ASN_misc, CauseMisc.ASN_hardware_failure),
    MISC_om_intervention(Cause.ASN_misc, CauseMisc.ASN_om_intervention),
    MISC_unknown_PLMN(Cause.ASN_misc, CauseMisc.ASN_unknown_PLMN),
    MISC_unspecified(Cause.ASN_misc, CauseMisc.ASN_unspecified);

    private final short fieldNumber;
    private final int asnValue;

    NgapCause(short fieldNumber, int asnValue) {
        this.fieldNumber = fieldNumber;
        this.asnValue = asnValue;
    }

    public short getFieldNumber() {
        return fieldNumber;
    }

    public int getAsnValue() {
        return asnValue;
    }
}

