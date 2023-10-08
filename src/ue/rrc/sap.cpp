//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"

#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>
#include <ue/nts.hpp>
#include <ue/rls/task.hpp>

namespace nr::ue
{

void UeRrcTask::handleRlsSapMessage(NmUeRlsToRrc &msg)
{
    switch (msg.present)
    {
    case NmUeRlsToRrc::SIGNAL_CHANGED: {
        handleCellSignalChange(msg.cellId, msg.dbm);
        break;
    }
    case NmUeRlsToRrc::DOWNLINK_RRC_DELIVERY: {
        handleDownlinkRrc(msg.cellId, msg.channel, msg.pdu);
        break;
    }
    case NmUeRlsToRrc::RADIO_LINK_FAILURE: {
        handleRadioLinkFailure(msg.rlfCause);
        break;
    }
    }
}

void UeRrcTask::handleNasSapMessage(NmUeNasToRrc &msg)
{
    switch (msg.present)
    {
    case NmUeNasToRrc::UPLINK_NAS_DELIVERY: {
        deliverUplinkNas(msg.pduId, std::move(msg.nasPdu));
        break;
    }
    case NmUeNasToRrc::LOCAL_RELEASE_CONNECTION: {
        // TODO: handle treat barred
        (void)msg.treatBarred;

        switchState(ERrcState::RRC_IDLE);
        m_base->rlsTask->push(std::make_unique<NmUeRrcToRls>(NmUeRrcToRls::RESET_STI));
        m_base->nasTask->push(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::RRC_CONNECTION_RELEASE));
        break;
    }
    case NmUeNasToRrc::RRC_NOTIFY: {
        triggerCycle();
        break;
    }
    case NmUeNasToRrc::PERFORM_UAC: {
        if (!msg.uacCtl->isExpiredForProducer())
            performUac(msg.uacCtl);
        break;
    }
    }
}

} // namespace nr::ue