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

void UeCmdHandler::sendResult(const InetAddress &address, const std::string &output)
{
    m_base->cliCallbackTask->push(new app::NwCliSendResponse(address, output, false));
}

void UeCmdHandler::sendError(const InetAddress &address, const std::string &output)
{
    m_base->cliCallbackTask->push(new app::NwCliSendResponse(address, output, true));
}

void UeCmdHandler::pauseTasks()
{
    m_base->mrTask->requestPause();
    m_base->nasTask->requestPause();
    m_base->rrcTask->requestPause();
}

void UeCmdHandler::unpauseTasks()
{
    m_base->mrTask->requestUnpause();
    m_base->nasTask->requestUnpause();
    m_base->rrcTask->requestUnpause();
}

bool UeCmdHandler::isAllPaused()
{
    if (!m_base->mrTask->isPauseConfirmed())
        return false;
    if (!m_base->nasTask->isPauseConfirmed())
        return false;
    if (!m_base->rrcTask->isPauseConfirmed())
        return false;
    return true;
}

void UeCmdHandler::handleCmd(NwUeCliCommand &msg)
{
    pauseTasks();

    uint64_t currentTime = utils::CurrentTimeMillis();
    uint64_t endTime = currentTime + PAUSE_CONFIRM_TIMEOUT;

    bool isPaused = false;
    while (currentTime < endTime)
    {
        currentTime = utils::CurrentTimeMillis();
        if (isAllPaused())
        {
            isPaused = true;
            break;
        }
        utils::Sleep(PAUSE_POLLING);
    }

    if (!isPaused)
    {
        sendError(msg.address, "UE is unable process command due to pausing timeout");
    }
    else
    {
        handleCmdImpl(msg);
    }

    unpauseTasks();
}

void UeCmdHandler::handleCmdImpl(NwUeCliCommand &msg)
{
    switch (msg.cmd->present)
    {
    case app::UeCliCommand::STATUS: {
        std::vector<Json> pduSessions{};
        int index = 0;
        for (auto &pduSession : m_base->appTask->m_pduSessions)
        {
            if (pduSession.has_value())
            {
                pduSessions.push_back(
                    Json::Obj({{"id", index}, {"type", pduSession->type}, {"address", pduSession->address}}));
            }
            index++;
        }

        Json json = Json::Obj({
            {"cm-state", ToJson(m_base->nasTask->mm->m_cmState)},
            {"rm-state", ToJson(m_base->nasTask->mm->m_rmState)},
            {"mm-state", ToJson(m_base->nasTask->mm->m_mmSubState)},
            {"sim-inserted", m_base->nasTask->mm->m_validSim},
            {"stored-suci", ToJson(m_base->nasTask->mm->m_storedSuci)},
            {"stored-guti", ToJson(m_base->nasTask->mm->m_storedGuti)},
            {"pdu-sessions", Json::Arr(std::move(pduSessions))},
        });
        sendResult(msg.address, json.dumpYaml());
        break;
    }
    case app::UeCliCommand::INFO: {
        sendResult(msg.address, ToJson(*m_base->config).dumpYaml());
        break;
    }
    case app::UeCliCommand::TIMERS: {
        sendResult(msg.address, ToJson(m_base->nasTask->timers).dumpYaml());
        break;
    }
    case app::UeCliCommand::DE_REGISTER: {
        m_base->nasTask->mm->sendDeregistration(msg.cmd->isSwitchOff ? nas::ESwitchOff::SWITCH_OFF
                                                                    : nas::ESwitchOff::NORMAL_DE_REGISTRATION,
                                               msg.cmd->dueToDisable5g);
        if (!msg.cmd->isSwitchOff)
            sendResult(msg.address, "De-registration procedure triggered");
        else
            sendResult(msg.address, "De-registration procedure triggered. UE device will be switched off.");
        break;
    }
    }
}

} // namespace nr::ue
