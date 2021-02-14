//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "cmd_handler.hpp"

#include <ue/app/task.hpp>
#include <ue/mr/task.hpp>
#include <ue/nas/task.hpp>
#include <ue/rrc/task.hpp>
#include <ue/tun/task.hpp>
#include <utils/common.hpp>
#include <utils/printer.hpp>

#define PAUSE_CONFIRM_TIMEOUT 3000
#define PAUSE_POLLING 10

namespace nr::ue
{

void UeCmdHandler::PauseTasks(TaskBase &base)
{
    base.mrTask->requestPause();
    base.nasTask->requestPause();
    base.rrcTask->requestPause();
}

void UeCmdHandler::UnpauseTasks(TaskBase &base)
{
    base.mrTask->requestUnpause();
    base.nasTask->requestUnpause();
    base.rrcTask->requestUnpause();
}

bool UeCmdHandler::IsAllPaused(TaskBase &base)
{
    if (!base.mrTask->isPauseConfirmed())
        return false;
    if (!base.nasTask->isPauseConfirmed())
        return false;
    if (!base.rrcTask->isPauseConfirmed())
        return false;
    return true;
}

void UeCmdHandler::HandleCmd(TaskBase &base, NwUeCliCommand &msg)
{
    PauseTasks(base);

    uint64_t currentTime = utils::CurrentTimeMillis();
    uint64_t endTime = currentTime + PAUSE_CONFIRM_TIMEOUT;

    bool isPaused = false;
    while (currentTime < endTime)
    {
        currentTime = utils::CurrentTimeMillis();
        if (IsAllPaused(base))
        {
            isPaused = true;
            break;
        }
        utils::Sleep(PAUSE_POLLING);
    }

    if (!isPaused)
    {
        msg.sendError("UE is unable process command due to pausing timeout");
    }
    else
    {
        HandleCmdImpl(base, msg);
    }

    UnpauseTasks(base);
}

void UeCmdHandler::HandleCmdImpl(TaskBase &base, NwUeCliCommand &msg)
{
    switch (msg.cmd->present)
    {
    case app::UeCliCommand::STATUS: {
        std::vector<Json> pduSessions{};
        int index = 0;
        for (auto &pduSession : base.appTask->m_statusInfo.pduSessions)
        {
            if (pduSession.has_value())
            {
                pduSessions.push_back(
                    Json::Obj({{"id", index}, {"type", pduSession->type}, {"address", pduSession->address}}));
            }
            index++;
        }

        Json json = Json::Obj({
            {"cm-state", ToJson(base.nasTask->mm->m_cmState)},
            {"rm-state", ToJson(base.nasTask->mm->m_rmState)},
            {"mm-state", ToJson(base.nasTask->mm->m_mmSubState)},
            {"sim-inserted", base.nasTask->mm->m_validSim},
            {"stored-suci", ToJson(base.nasTask->mm->m_storedSuci)},
            {"stored-guti", ToJson(base.nasTask->mm->m_storedGuti)},
            {"pdu-sessions", Json::Arr(std::move(pduSessions))},
        });
        msg.sendResult(json.dumpYaml());
        break;
    }
    case app::UeCliCommand::INFO: {
        msg.sendResult(ToJson(*base.config).dumpYaml());
        break;
    }
    case app::UeCliCommand::TIMERS: {
        msg.sendResult(ToJson(base.nasTask->timers).dumpYaml());
        break;
    }
    }
}

} // namespace nr::ue
