//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>
#include <ue/nts.hpp>

namespace nr::ue
{

void UeRrcTask::handleRlsSapMessage(NwUeRlsToRrc &msg)
{
    switch (msg.present)
    {
    case NwUeRlsToRrc::SIGNAL_CHANGED: {
        handleCellSignalChange(msg.cellId, msg.dbm);
        break;
    }
    case NwUeRlsToRrc::DOWNLINK_RRC_DELIVERY: {
        handleDownlinkRrc(msg.cellId, msg.channel, msg.pdu);
        break;
    }
    case NwUeRlsToRrc::RADIO_LINK_FAILURE: {
        handleRadioLinkFailure(msg.rlfCause);
        break;
    }
    }
}

void UeRrcTask::handleNasSapMessage(NwUeNasToRrc &msg)
{
    switch (msg.present)
    {
    case NwUeNasToRrc::UPLINK_NAS_DELIVERY: {
        deliverUplinkNas(msg.pduId, std::move(msg.nasPdu));
        break;
    }
    case NwUeNasToRrc::LOCAL_RELEASE_CONNECTION: {
        // TODO
        // m_state = ERrcState::RRC_IDLE;
        // m_base->nasTask->push(new NwUeRrcToNas(NwUeRrcToNas::RRC_CONNECTION_RELEASE));
        // m_base->rlsTask->push(new NwUeRrcToRls(NwUeRrcToRls::RESET_STI));
        break;
    }
    case NwUeNasToRrc::RRC_NOTIFY: {
        triggerCycle();
        break;
    }
    }
}

} // namespace nr::ue