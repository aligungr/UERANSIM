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

UeRrcTask::UeRrcTask(TaskBase *base)
{
    layer = std::make_unique<UeRrcLayer>(base);
}

void UeRrcTask::onStart()
{
    layer->onStart();

    setTimer(TIMER_ID_MACHINE_CYCLE, TIMER_PERIOD_MACHINE_CYCLE);
}

void UeRrcTask::onQuit()
{
    layer->onQuit();
}

void UeRrcTask::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    if (msg->msgType == NtsMessageType::UE_CYCLE_REQUIRED)
    {
        layer->performCycle();
    }
    else if (msg->msgType == NtsMessageType::TIMER_EXPIRED)
    {
        auto &w = dynamic_cast<NmTimerExpired &>(*msg);
        if (w.timerId == TIMER_ID_MACHINE_CYCLE)
        {
            setTimer(TIMER_ID_MACHINE_CYCLE, TIMER_PERIOD_MACHINE_CYCLE);
            layer->performCycle();
        }
    }
    else if (msg->msgType == NtsMessageType::UE_NAS_TO_RRC)
    {
        layer->handleNasSapMessage(dynamic_cast<NmUeNasToRrc &>(*msg));
    }
    else if (msg->msgType == NtsMessageType::UE_RLS_TO_RRC)
    {
        layer->handleRlsSapMessage(dynamic_cast<NmUeRlsToRrc &>(*msg));
    }
}

} // namespace nr::ue