//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <utils/common.hpp>

static constexpr const int64_t SERVICE_REQUEST_NEEDED_FOR_DATA_THRESHOLD = 1000;

namespace nr::ue
{

void NasMm::handleRrcEvent(const NwUeRrcToNas &msg)
{
    switch (msg.present)
    {
    case NwUeRrcToNas::RRC_CONNECTION_SETUP: {
        handleRrcConnectionSetup();
        break;
    }
    case NwUeRrcToNas::PLMN_SEARCH_RESPONSE: {
        handlePlmnSearchResponse(msg.measurements);
        break;
    }
    case NwUeRrcToNas::NAS_DELIVERY: {
        OctetView buffer{msg.nasPdu};
        auto nasMessage = nas::DecodeNasMessage(buffer);
        if (nasMessage != nullptr)
            receiveNasMessage(*nasMessage);
        break;
    }
    case NwUeRrcToNas::RRC_CONNECTION_RELEASE: {
        handleRrcConnectionRelease();
        break;
    }
    case NwUeRrcToNas::RADIO_LINK_FAILURE: {
        handleRadioLinkFailure();
        break;
    }
    case NwUeRrcToNas::SERVING_CELL_CHANGE: {
        handleServingCellChange(msg.servingCell);
        break;
    }
    case NwUeRrcToNas::PAGING: {
        handlePaging(msg.pagingTmsi);
        break;
    }
    }
}

void NasMm::handleNasEvent(const NwUeNasToNas &msg)
{
    switch (msg.present)
    {
    case NwUeNasToNas::PERFORM_MM_CYCLE:
        performMmCycle();
        break;
    case NwUeNasToNas::NAS_TIMER_EXPIRE:
        onTimerExpire(*msg.timer);
        break;
    default:
        break;
    }
}

bool NasMm::isRegistered()
{
    return m_rmState == ERmState::RM_REGISTERED;
}

bool NasMm::isRegisteredForEmergency()
{
    return isRegistered() && m_registeredForEmergency;
}

void NasMm::serviceNeededForUplinkData()
{
    auto currentTime = utils::CurrentTimeMillis();
    if (currentTime - m_lastTimeServiceReqNeededIndForData > SERVICE_REQUEST_NEEDED_FOR_DATA_THRESHOLD)
    {
        sendServiceRequest(m_cmState == ECmState::CM_CONNECTED ? EServiceReqCause::CONNECTED_UPLINK_DATA_PENDING
                                                               : EServiceReqCause::IDLE_UPLINK_DATA_PENDING);

        m_lastTimeServiceReqNeededIndForData = currentTime;
    }
}

} // namespace nr::ue
