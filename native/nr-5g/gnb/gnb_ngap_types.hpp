//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <string>

namespace nr::gnb
{

enum class EAmfState
{
    NOT_CONNECTED,
    WAITING_NG_SETUP,
    CONNECTED
};

struct SctpAssociation
{
    int associationId;
};

struct Guami
{
    Plmn plmn;
    int amfRegionId; // 8-bit
    int amfSetId;    // 10-bit
    int amfPointer;  // 6-bit
};

struct ServedGuami
{
    Guami guami;
    std::string backupAmfName;
};

struct NgapAmfContext
{
    int ctxId;
    SctpAssociation association;
    int nextStream; // next available SCTP stream for uplink
    std::string address;
    uint16_t port;
    std::string amfName;
    long relativeCapacity;
    EAmfState state;
    std::vector<ServedGuami *> servedGuamiList;
    std::vector<PlmnSupport *> plmnSupportList;
};

struct NgapUeContext
{
    int ctxId;

    int64_t amfUeNgapId; // -1 if not assigned
    int64_t ranUeNgapId;
    int associatedAmfId;
    int uplinkStream;
    int downlinkStream;
};

enum class NgapCause
{
    CauseRadioNetwork_unspecified = 0,
    CauseRadioNetwork_txnrelocoverall_expiry,
    CauseRadioNetwork_successful_handover,
    CauseRadioNetwork_release_due_to_ngran_generated_reason,
    CauseRadioNetwork_release_due_to_5gc_generated_reason,
    CauseRadioNetwork_handover_cancelled,
    CauseRadioNetwork_partial_handover,
    CauseRadioNetwork_ho_failure_in_target_5GC_ngran_node_or_target_system,
    CauseRadioNetwork_ho_target_not_allowed,
    CauseRadioNetwork_tngrelocoverall_expiry,
    CauseRadioNetwork_tngrelocprep_expiry,
    CauseRadioNetwork_cell_not_available,
    CauseRadioNetwork_unknown_targetID,
    CauseRadioNetwork_no_radio_resources_available_in_target_cell,
    CauseRadioNetwork_unknown_local_UE_NGAP_ID,
    CauseRadioNetwork_inconsistent_remote_UE_NGAP_ID,
    CauseRadioNetwork_handover_desirable_for_radio_reason,
    CauseRadioNetwork_time_critical_handover,
    CauseRadioNetwork_resource_optimisation_handover,
    CauseRadioNetwork_reduce_load_in_serving_cell,
    CauseRadioNetwork_user_inactivity,
    CauseRadioNetwork_radio_connection_with_ue_lost,
    CauseRadioNetwork_radio_resources_not_available,
    CauseRadioNetwork_invalid_qos_combination,
    CauseRadioNetwork_failure_in_radio_interface_procedure,
    CauseRadioNetwork_interaction_with_other_procedure,
    CauseRadioNetwork_unknown_PDU_session_ID,
    CauseRadioNetwork_unkown_qos_flow_ID,
    CauseRadioNetwork_multiple_PDU_session_ID_instances,
    CauseRadioNetwork_multiple_qos_flow_ID_instances,
    CauseRadioNetwork_encryption_and_or_integrity_protection_algorithms_not_supported,
    CauseRadioNetwork_ng_intra_system_handover_triggered,
    CauseRadioNetwork_ng_inter_system_handover_triggered,
    CauseRadioNetwork_xn_handover_triggered,
    CauseRadioNetwork_not_supported_5QI_value,
    CauseRadioNetwork_ue_context_transfer,
    CauseRadioNetwork_ims_voice_eps_fallback_or_rat_fallback_triggered,
    CauseRadioNetwork_up_integrity_protection_not_possible,
    CauseRadioNetwork_up_confidentiality_protection_not_possible,
    CauseRadioNetwork_slice_not_supported,
    CauseRadioNetwork_ue_in_rrc_inactive_state_not_reachable,
    CauseRadioNetwork_redirection,
    CauseRadioNetwork_resources_not_available_for_the_slice,
    CauseRadioNetwork_ue_max_integrity_protected_data_rate_reason,
    CauseRadioNetwork_release_due_to_cn_detected_mobility,
    CauseRadioNetwork_n26_interface_not_available,
    CauseRadioNetwork_release_due_to_pre_emption,
    CauseRadioNetwork_multiple_location_reporting_reference_ID_instances,

    CauseTransport_transport_resource_unavailable = 100,
    CauseTransport_unspecified,

    CauseNas_normal_release = 200,
    CauseNas_authentication_failure,
    CauseNas_deregister,
    CauseNas_unspecified,

    CauseProtocol_transfer_syntax_error = 300,
    CauseProtocol_abstract_syntax_error_reject,
    CauseProtocol_abstract_syntax_error_ignore_and_notify,
    CauseProtocol_message_not_compatible_with_receiver_state,
    CauseProtocol_semantic_error,
    CauseProtocol_abstract_syntax_error_falsely_constructed_message,
    CauseProtocol_unspecified,

    CauseMisc_control_processing_overload = 400,
    CauseMisc_not_enough_user_plane_processing_resources,
    CauseMisc_hardware_failure,
    CauseMisc_om_intervention,
    CauseMisc_unknown_PLMN,
};

} // namespace nr::gnb