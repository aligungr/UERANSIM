//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "types.hpp"
#include <utils/printer.hpp>

namespace nr::ue
{

NasTimers::NasTimers()
    : t3346(3346, true, INT32_MAX), t3396(3396, false, INT32_MAX), t3444(3444, true, 12 * 60 * 60),
      t3445(3445, true, 12 * 60 * 60), t3502(3502, true, 12 * 60), t3510(3510, true, 15), t3511(3511, true, 10),
      t3512(3512, true, 54 * 60), t3516(3516, true, 30), t3517(3517, true, 15), t3519(3519, true, 60),
      t3520(3520, true, 15), t3521(3521, true, 15), t3525(3525, true, 60), t3540(3540, true, 10),
      t3584(3584, false, INT32_MAX), t3585(3585, false, INT32_MAX)
{
}

Json ToJson(const ECmState &state)
{
    switch (state)
    {
    case ECmState::CM_IDLE:
        return "CM-IDLE";
    case ECmState::CM_CONNECTED:
        return "CM-CONNECTED";
    default:
        return "?";
    }
}

Json ToJson(const ERmState &state)
{
    switch (state)
    {
    case ERmState::RM_DEREGISTERED:
        return "RM-DEREGISTERED";
    case ERmState::RM_REGISTERED:
        return "RM-REGISTERED";
    default:
        return "?";
    }
}

Json ToJson(const EMmState &state)
{
    switch (state)
    {
    case EMmState::MM_NULL:
        return "MM-NULL";
    case EMmState::MM_DEREGISTERED:
        return "MM-DEREGISTERED";
    case EMmState::MM_REGISTERED_INITIATED:
        return "MM-REGISTER-INITIATED";
    case EMmState::MM_REGISTERED:
        return "MM-REGISTERED";
    case EMmState::MM_DEREGISTERED_INITIATED:
        return "MM-DEREGISTER-INITIATED";
    case EMmState::MM_SERVICE_REQUEST_INITIATED:
        return "MM-SERVICE-REQUEST-INITIATED";
    default:
        return "?";
    }
}

Json ToJson(const ERrcState &state)
{
    switch (state)
    {
    case ERrcState::RRC_IDLE:
        return "RRC-IDLE";
    case ERrcState::RRC_CONNECTED:
        return "RRC-CONNECTED";
    case ERrcState::RRC_INACTIVE:
        return "RRC-INACTIVE";
    default:
        return "?";
    }
}

Json ToJson(const EMmSubState &state)
{
    switch (state)
    {
    case EMmSubState::MM_NULL_PS:
        return "MM-NULL";
    case EMmSubState::MM_DEREGISTERED_PS:
        return "MM-DEREGISTERED/PS";
    case EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE:
        return "MM-DEREGISTERED/NORMAL-SERVICE";
    case EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE:
        return "MM-DEREGISTERED/LIMITED-SERVICE";
    case EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION:
        return "MM-DEREGISTERED/ATTEMPTING-REGISTRATION";
    case EMmSubState::MM_DEREGISTERED_PLMN_SEARCH:
        return "MM-DEREGISTERED/PLMN-SEARCH";
    case EMmSubState::MM_DEREGISTERED_NO_SUPI:
        return "MM-DEREGISTERED/NO-SUPI";
    case EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE:
        return "MM-DEREGISTERED/NO-CELL-AVAILABLE";
    case EMmSubState::MM_DEREGISTERED_ECALL_INACTIVE:
        return "MM-DEREGISTERED/ECALL-INACTIVE";
    case EMmSubState::MM_DEREGISTERED_INITIAL_REGISTRATION_NEEDED:
        return "MM-DEREGISTERED/INITIAL-REGISTRATION-NEEDED";
    case EMmSubState::MM_REGISTERED_INITIATED_PS:
        return "MM-REGISTER-INITIATED";
    case EMmSubState::MM_REGISTERED_PS:
        return "MM-REGISTERED/PS";
    case EMmSubState::MM_REGISTERED_NORMAL_SERVICE:
        return "MM-REGISTERED/NORMAL-SERVICE";
    case EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE:
        return "MM-REGISTERED/NON-ALLOWED-SERVICE";
    case EMmSubState::MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE:
        return "MM-REGISTERED/ATTEMPTING-REGISTRATION-UPDATE";
    case EMmSubState::MM_REGISTERED_LIMITED_SERVICE:
        return "MM-REGISTERED/LIMITED-SERVICE";
    case EMmSubState::MM_REGISTERED_PLMN_SEARCH:
        return "MM-REGISTERED/PLMN-SEARCH";
    case EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE:
        return "MM-REGISTERED/NO-CELL-AVAILABLE";
    case EMmSubState::MM_REGISTERED_UPDATE_NEEDED:
        return "MM-REGISTERED/UPDATE-NEEDED";
    case EMmSubState::MM_DEREGISTERED_INITIATED_PS:
        return "MM-DEREGISTER-INITIATED";
    case EMmSubState::MM_SERVICE_REQUEST_INITIATED_PS:
        return "MM-SERVICE-REQUEST-INITIATED";
    default:
        return "?";
    }
}

Json ToJson(const EServiceReqCause &v)
{
    switch (v)
    {
    case EServiceReqCause::IDLE_PAGING:
        return "IDLE-PAGING";
    case EServiceReqCause::CONNECTED_3GPP_NOTIFICATION_N3GPP:
        return "CONNECTED-3GPP-NOTIFICATION-N3GPP";
    case EServiceReqCause::IDLE_UPLINK_SIGNAL_PENDING:
        return "IDLE-UPLINK-SIGNAL-PENDING";
    case EServiceReqCause::IDLE_UPLINK_DATA_PENDING:
        return "IDLE-UPLINK-DATA-PENDING";
    case EServiceReqCause::CONNECTED_UPLINK_DATA_PENDING:
        return "CONNECTED-UPLINK-DATA-PENDING";
    case EServiceReqCause::NON_3GPP_AS_ESTABLISHED:
        return "NON-3GPP-AS-ESTABLISHED";
    case EServiceReqCause::IDLE_3GPP_NOTIFICATION_N3GPP:
        return "IDLE-3GPP-NOTIFICATION-N3GPP";
    case EServiceReqCause::EMERGENCY_FALLBACK:
        return "EMERGENCY-FALLBACK";
    case EServiceReqCause::FALLBACK_INDICATION:
        return "FALLBACK-INDICATION";
    default:
        return "?";
    }
}

Json ToJson(const ERegUpdateCause &v)
{
    switch (v)
    {
    case ERegUpdateCause::ENTER_UNLISTED_TRACKING_AREA:
        return "ENTER-UNLISTED-TRACKING-AREA";
    case ERegUpdateCause::T3512_EXPIRY:
        return "T3512-EXPIRY";
    case ERegUpdateCause::CONFIGURATION_UPDATE:
        return "CONFIGURATION-UPDATE";
    case ERegUpdateCause::PAGING_OR_NOTIFICATION:
        return "PAGING-OR-NOTIFICATION";
    case ERegUpdateCause::INTER_SYSTEM_CHANGE_S1_TO_N1:
        return "INTER-SYSTEM-CHANGE-S1-TO-N1";
    case ERegUpdateCause::CONNECTION_RECOVERY:
        return "CONNECTION-RECOVERY";
    case ERegUpdateCause::FALLBACK_INDICATION:
        return "FALLBACK-INDICATION";
    case ERegUpdateCause::MM_OR_S1_CAPABILITY_CHANGE:
        return "MM-OR-S1-CAPABILITY-CHANGE";
    case ERegUpdateCause::USAGE_SETTING_CHANGE:
        return "USAGE-SETTING_CHANGE";
    case ERegUpdateCause::SLICE_CHANGE:
        return "SLICE-CHANGE";
    case ERegUpdateCause::DRX_CHANGE:
        return "DRX-CHANGE";
    case ERegUpdateCause::EMERGENCY_CASE:
        return "EMERGENCY-CASE";
    case ERegUpdateCause::SMS_OVER_NAS_CHANGE:
        return "SMS-OVER-NAS-CHANGE";
    case ERegUpdateCause::PS_STATUS_INFORM:
        return "PS-STATUS-INFORM";
    case ERegUpdateCause::RADIO_CAP_CHANGE:
        return "RADIO-CAP-CHANGE";
    case ERegUpdateCause::NEW_LADN_NEEDED:
        return "NEW-LADN-NEEDED";
    case ERegUpdateCause::MICO_MODE_CHANGE:
        return "MICO-MODE-CHANGE";
    case ERegUpdateCause::ENTER_EQUIVALENT_PLMN_CELL:
        return "ENTER-EQUIVALENT-PLMN-CELL";
    case ERegUpdateCause::RESTRICTED_SERVICE_AREA:
        return "RESTRICTED-SERVICE-AREA";
    case ERegUpdateCause::TAI_CHANGE_IN_ATT_UPD:
        return "TAI-CHANGE-IN-ATT-UPD";
    case ERegUpdateCause::PLMN_CHANGE_IN_ATT_UPD:
        return "PLMN-CHANGE-IN-ATT-UPD";
    case ERegUpdateCause::T3346_EXPIRY_IN_ATT_UPD:
        return "T3346-EXPIRY-IN-ATT-UPD";
    case ERegUpdateCause::T3502_EXPIRY_IN_ATT_UPD:
        return "T3502-EXPIRY-IN-ATT-UPD";
    case ERegUpdateCause::T3511_EXPIRY_IN_ATT_UPD:
        return "T3511-EXPIRY-IN-ATT-UPD";
    default:
        return "?";
    }
}

Json ToJson(const NasTimers &v)
{
    return Json::Obj({
        {"T3346", ToJson(v.t3346)},
        {"T3396", ToJson(v.t3396)},
        {"T3444", ToJson(v.t3444)},
        {"T3445", ToJson(v.t3445)},
        {"T3502", ToJson(v.t3502)},
        {"T3510", ToJson(v.t3510)},
        {"T3511", ToJson(v.t3511)},
        {"T3512", ToJson(v.t3512)},
        {"T3516", ToJson(v.t3516)},
        {"T3517", ToJson(v.t3517)},
        {"T3519", ToJson(v.t3519)},
        {"T3520", ToJson(v.t3520)},
        {"T3521", ToJson(v.t3521)},
        {"T3525", ToJson(v.t3525)},
        {"T3540", ToJson(v.t3540)},
        {"T3584", ToJson(v.t3584)},
        {"T3585", ToJson(v.t3585)},
    });
}

Json ToJson(const E5UState &state)
{
    switch (state)
    {
    case E5UState::U1_UPDATED:
        return "5U1-UPDATED";
    case E5UState::U2_NOT_UPDATED:
        return "5U2-NOT-UPDATED";
    case E5UState::U3_ROAMING_NOT_ALLOWED:
        return "5U3-ROAMING-NOT-ALLOWED";
    default:
        return "?";
    }
}

Json ToJson(const EPsState &state)
{
    switch (state)
    {
    case EPsState::INACTIVE:
        return "PS-INACTIVE";
    case EPsState::ACTIVE_PENDING:
        return "PS-ACTIVE-PENDING";
    case EPsState::ACTIVE:
        return "PS-ACTIVE";
    case EPsState::INACTIVE_PENDING:
        return "PS-INACTIVE-PENDING";
    case EPsState::MODIFICATION_PENDING:
        return "PS-MODIFICATION-PENDING";
    default:
        return "?";
    }
}

bool ActiveCellInfo::hasValue() const
{
    return cellId != 0;
}

Plmn UeSharedContext::getCurrentPlmn()
{
    return currentCell.get<Plmn>([](auto &value) { return value.plmn; });
}

Tai UeSharedContext::getCurrentTai()
{
    Tai tai;
    currentCell.access([&tai](auto &value) {
        tai.plmn = value.plmn;
        tai.tac = value.tac;
    });
    return tai;
}

bool UeSharedContext::hasActiveCell()
{
    return getCurrentTai().hasValue();
}

RrcTimers::RrcTimers() : t300(300, false, 1)
{
}

} // namespace nr::ue
