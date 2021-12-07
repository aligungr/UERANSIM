//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <set>

#include <lib/app/monitor.hpp>
#include <lib/asn/utils.hpp>
#include <utils/common_types.hpp>
#include <utils/logger.hpp>
#include <utils/network.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>

#include <asn/ngap/ASN_NGAP_QosFlowSetupRequestList.h>
#include <asn/rrc/ASN_RRC_InitialUE-Identity.h>

namespace nr::gnb
{

class GnbAppTask;
class GtpTask;
class NgapTask;
class GnbRrcTask;
class GnbRlsTask;
class SctpTask;

enum class EAmfState
{
    NOT_CONNECTED = 0,
    WAITING_NG_SETUP,
    CONNECTED
};

struct SctpAssociation
{
    int associationId{};
    int inStreams{};
    int outStreams{};
};

struct Guami
{
    Plmn plmn{};
    int amfRegionId{}; // 8-bit
    int amfSetId{};    // 10-bit
    int amfPointer{};  // 6-bit
};

struct ServedGuami
{
    Guami guami{};
    std::string backupAmfName{};
};

// TODO: update cli and json for overload related types

enum class EOverloadAction
{
    UNSPECIFIED_OVERLOAD,
    REJECT_NON_EMERGENCY_MO_DATA,
    REJECT_SIGNALLING,
    ONLY_EMERGENCY_AND_MT,
    ONLY_HIGH_PRI_AND_MT,
};

enum class EOverloadStatus
{
    NOT_OVERLOADED,
    OVERLOADED
};

struct OverloadInfo
{
    struct Indication
    {
        // Reduce the signalling traffic by the indicated percentage
        int loadReductionPerc{};

        // If reduction percentage is not present, this action shall be used
        EOverloadAction action{};
    };

    EOverloadStatus status{};
    Indication indication{};
};

struct NgapAmfContext
{
    int ctxId{};
    SctpAssociation association{};
    int nextStream{}; // next available SCTP stream for uplink
    std::string address{};
    uint16_t port{};
    std::string amfName{};
    int64_t relativeCapacity{};
    EAmfState state{};
    OverloadInfo overloadInfo{};
    std::vector<ServedGuami *> servedGuamiList{};
    std::vector<PlmnSupport *> plmnSupportList{};
};

struct RlsUeContext
{
    const int ueId;
    uint64_t sti{};
    InetAddress addr{};
    int64_t lastSeen{};

    explicit RlsUeContext(int ueId) : ueId(ueId)
    {
    }
};

struct AggregateMaximumBitRate
{
    uint64_t dlAmbr{};
    uint64_t ulAmbr{};
};

struct NgapUeContext
{
    const int ctxId{};

    int64_t amfUeNgapId = -1; // -1 if not assigned
    int64_t ranUeNgapId{};
    int associatedAmfId{};
    int uplinkStream{};
    int downlinkStream{};
    AggregateMaximumBitRate ueAmbr{};
    std::set<int> pduSessions{};

    explicit NgapUeContext(int ctxId) : ctxId(ctxId)
    {
    }
};

struct RrcUeContext
{
    const int ueId{};

    int64_t initialId = -1; // 39-bit value, or -1
    bool isInitialIdSTmsi{}; // TMSI-part-1 or a random value
    int64_t establishmentCause{};
    std::optional<GutiMobileIdentity> sTmsi{};

    explicit RrcUeContext(const int ueId) : ueId(ueId)
    {
    }
};

struct NgapIdPair
{
    std::optional<int64_t> amfUeNgapId{};
    std::optional<int64_t> ranUeNgapId{};

    NgapIdPair() : amfUeNgapId{}, ranUeNgapId{}
    {
    }

    NgapIdPair(const std::optional<int64_t> &amfUeNgapId, const std::optional<int64_t> &ranUeNgapId)
        : amfUeNgapId(amfUeNgapId), ranUeNgapId(ranUeNgapId)
    {
    }
};

enum class NgapCause
{
    RadioNetwork_unspecified = 0,
    RadioNetwork_txnrelocoverall_expiry,
    RadioNetwork_successful_handover,
    RadioNetwork_release_due_to_ngran_generated_reason,
    RadioNetwork_release_due_to_5gc_generated_reason,
    RadioNetwork_handover_cancelled,
    RadioNetwork_partial_handover,
    RadioNetwork_ho_failure_in_target_5GC_ngran_node_or_target_system,
    RadioNetwork_ho_target_not_allowed,
    RadioNetwork_tngrelocoverall_expiry,
    RadioNetwork_tngrelocprep_expiry,
    RadioNetwork_cell_not_available,
    RadioNetwork_unknown_targetID,
    RadioNetwork_no_radio_resources_available_in_target_cell,
    RadioNetwork_unknown_local_UE_NGAP_ID,
    RadioNetwork_inconsistent_remote_UE_NGAP_ID,
    RadioNetwork_handover_desirable_for_radio_reason,
    RadioNetwork_time_critical_handover,
    RadioNetwork_resource_optimisation_handover,
    RadioNetwork_reduce_load_in_serving_cell,
    RadioNetwork_user_inactivity,
    RadioNetwork_radio_connection_with_ue_lost,
    RadioNetwork_radio_resources_not_available,
    RadioNetwork_invalid_qos_combination,
    RadioNetwork_failure_in_radio_interface_procedure,
    RadioNetwork_interaction_with_other_procedure,
    RadioNetwork_unknown_PDU_session_ID,
    RadioNetwork_unkown_qos_flow_ID,
    RadioNetwork_multiple_PDU_session_ID_instances,
    RadioNetwork_multiple_qos_flow_ID_instances,
    RadioNetwork_encryption_and_or_integrity_protection_algorithms_not_supported,
    RadioNetwork_ng_intra_system_handover_triggered,
    RadioNetwork_ng_inter_system_handover_triggered,
    RadioNetwork_xn_handover_triggered,
    RadioNetwork_not_supported_5QI_value,
    RadioNetwork_ue_context_transfer,
    RadioNetwork_ims_voice_eps_fallback_or_rat_fallback_triggered,
    RadioNetwork_up_integrity_protection_not_possible,
    RadioNetwork_up_confidentiality_protection_not_possible,
    RadioNetwork_slice_not_supported,
    RadioNetwork_ue_in_rrc_inactive_state_not_reachable,
    RadioNetwork_redirection,
    RadioNetwork_resources_not_available_for_the_slice,
    RadioNetwork_ue_max_integrity_protected_data_rate_reason,
    RadioNetwork_release_due_to_cn_detected_mobility,
    RadioNetwork_n26_interface_not_available,
    RadioNetwork_release_due_to_pre_emption,
    RadioNetwork_multiple_location_reporting_reference_ID_instances,

    Transport_transport_resource_unavailable = 100,
    Transport_unspecified,

    Nas_normal_release = 200,
    Nas_authentication_failure,
    Nas_deregister,
    Nas_unspecified,

    Protocol_transfer_syntax_error = 300,
    Protocol_abstract_syntax_error_reject,
    Protocol_abstract_syntax_error_ignore_and_notify,
    Protocol_message_not_compatible_with_receiver_state,
    Protocol_semantic_error,
    Protocol_abstract_syntax_error_falsely_constructed_message,
    Protocol_unspecified,

    Misc_control_processing_overload = 400,
    Misc_not_enough_user_plane_processing_resources,
    Misc_hardware_failure,
    Misc_om_intervention,
    Misc_unknown_PLMN,
};

struct GtpTunnel
{
    uint32_t teid{};
    OctetString address{};
};

struct PduSessionResource
{
    const int ueId;
    const int psi;

    AggregateMaximumBitRate sessionAmbr{};
    bool dataForwardingNotPossible{};
    PduSessionType sessionType = PduSessionType::UNSTRUCTURED;
    GtpTunnel upTunnel{};
    GtpTunnel downTunnel{};
    asn::Unique<ASN_NGAP_QosFlowSetupRequestList> qosFlows{};

    PduSessionResource(const int ueId, const int psi) : ueId(ueId), psi(psi)
    {
    }
};

struct GnbStatusInfo
{
    bool isNgapUp{};
};

struct GtpUeContext
{
    const int ueId;
    AggregateMaximumBitRate ueAmbr{};

    explicit GtpUeContext(const int ueId) : ueId(ueId)
    {
    }
};

struct GtpUeContextUpdate
{
    bool isCreate{};
    int ueId{};
    AggregateMaximumBitRate ueAmbr{};

    GtpUeContextUpdate(bool isCreate, int ueId, const AggregateMaximumBitRate &ueAmbr)
        : isCreate(isCreate), ueId(ueId), ueAmbr(ueAmbr)
    {
    }
};

struct GnbAmfConfig
{
    std::string address{};
    uint16_t port{};
};

struct GnbConfig
{
    /* Read from config file */
    int64_t nci{};     // 36-bit
    int gnbIdLength{}; // 22..32 bit
    Plmn plmn{};
    int tac{};
    NetworkSlice nssai{};
    std::vector<GnbAmfConfig> amfConfigs{};
    std::string linkIp{};
    std::string ngapIp{};
    std::string gtpIp{};
    std::optional<std::string> gtpAdvertiseIp{};
    bool ignoreStreamIds{};

    /* Assigned by program */
    std::string name{};
    EPagingDrx pagingDrx{};
    Vector3 phyLocation{};

    [[nodiscard]] inline uint32_t getGnbId() const
    {
        return static_cast<uint32_t>((nci & 0xFFFFFFFFFLL) >> (36LL - static_cast<int64_t>(gnbIdLength)));
    }

    [[nodiscard]] inline int getCellId() const
    {
        return static_cast<int>(nci & static_cast<uint64_t>((1 << (36 - gnbIdLength)) - 1));
    }
};

struct TaskBase
{
    GnbConfig *config{};
    LogBase *logBase{};
    app::INodeListener *nodeListener{};
    NtsTask *cliCallbackTask{};

    GnbAppTask *appTask{};
    GtpTask *gtpTask{};
    NgapTask *ngapTask{};
    GnbRrcTask *rrcTask{};
    SctpTask *sctpTask{};
    GnbRlsTask *rlsTask{};
};

Json ToJson(const GnbStatusInfo &v);
Json ToJson(const GnbConfig &v);
Json ToJson(const NgapAmfContext &v);
Json ToJson(const EAmfState &v);
Json ToJson(const EPagingDrx &v);
Json ToJson(const SctpAssociation &v);
Json ToJson(const ServedGuami &v);
Json ToJson(const Guami &v);

} // namespace nr::gnb