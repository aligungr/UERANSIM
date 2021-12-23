//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <asn/rrc/ASN_RRC_RRCSetupRequest-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer.h>
#include <lib/rrc/encode.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/task.hpp>
#include <ue/rls/task.hpp>
#include <utils/common.hpp>

static constexpr const int TIMER_ID_MACHINE_CYCLE = 1;
static constexpr const int TIMER_PERIOD_MACHINE_CYCLE = 2500;

namespace nr::ue
{

UeRrcTask::UeRrcTask(TaskBase *base) : m_base{base}, m_timers{}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "rrc");

    m_startedTime = utils::CurrentTimeMillis();
    m_state = ERrcState::RRC_IDLE;
    m_establishmentCause = ASN_RRC_EstablishmentCause_mt_Access;
}

void UeRrcTask::onStart()
{
    triggerCycle();

    setTimer(TIMER_ID_MACHINE_CYCLE, TIMER_PERIOD_MACHINE_CYCLE);
}

void UeRrcTask::onQuit()
{
    // TODO
}

void UeRrcTask::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_NAS_TO_RRC: {
        handleNasSapMessage(dynamic_cast<NmUeNasToRrc &>(*msg));
        break;
    }
    case NtsMessageType::UE_RLS_TO_RRC: {
        handleRlsSapMessage(dynamic_cast<NmUeRlsToRrc &>(*msg));
        break;
    }
    case NtsMessageType::UE_RRC_TO_RRC: {
        auto &w = dynamic_cast<NmUeRrcToRrc &>(*msg);
        switch (w.present)
        {
        case NmUeRrcToRrc::TRIGGER_CYCLE:
            performCycle();
            break;
        }
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto &w = dynamic_cast<NmTimerExpired &>(*msg);
        if (w.timerId == TIMER_ID_MACHINE_CYCLE)
        {
            setTimer(TIMER_ID_MACHINE_CYCLE, TIMER_PERIOD_MACHINE_CYCLE);
            performCycle();
        }
        break;
    }
    default:
        m_logger->unhandledNts(*msg);
        break;
    }
}

} // namespace nr::ue