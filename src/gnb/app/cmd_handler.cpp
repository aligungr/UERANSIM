//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "cmd_handler.hpp"

#include <gnb/app/task.hpp>
#include <gnb/gtp/task.hpp>
#include <gnb/mr/task.hpp>
#include <gnb/ngap/task.hpp>
#include <gnb/rrc/task.hpp>
#include <gnb/sctp/task.hpp>
#include <utils/common.hpp>
#include <utils/printer.hpp>

#define PAUSE_CONFIRM_TIMEOUT 3000
#define PAUSE_POLLING 10

namespace nr::gnb
{

void GnbCmdHandler::PauseTasks(TaskBase &base)
{
    base.gtpTask->requestPause();
    base.mrTask->requestPause();
    base.ngapTask->requestPause();
    base.rrcTask->requestPause();
    base.sctpTask->requestPause();
}

void GnbCmdHandler::UnpauseTasks(TaskBase &base)
{
    base.gtpTask->requestUnpause();
    base.mrTask->requestUnpause();
    base.ngapTask->requestUnpause();
    base.rrcTask->requestUnpause();
    base.sctpTask->requestUnpause();
}

bool GnbCmdHandler::IsAllPaused(TaskBase &base)
{
    if (!base.gtpTask->isPauseConfirmed())
        return false;
    if (!base.mrTask->isPauseConfirmed())
        return false;
    if (!base.ngapTask->isPauseConfirmed())
        return false;
    if (!base.rrcTask->isPauseConfirmed())
        return false;
    if (!base.sctpTask->isPauseConfirmed())
        return false;
    return true;
}

void GnbCmdHandler::HandleCmd(TaskBase &base, NwGnbCliCommand &msg)
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
        msg.sendError("gNB is unable process command due to pausing timeout");
    }
    else
    {
        HandleCmdImpl(base, msg);
    }

    UnpauseTasks(base);
}

void GnbCmdHandler::HandleCmdImpl(TaskBase &base, NwGnbCliCommand &msg)
{
    switch (msg.cmd->present)
    {
    case app::GnbCliCommand::STATUS: {
        msg.sendResult(ToJson(base.appTask->m_statusInfo).dumpYaml());
        break;
    }
    case app::GnbCliCommand::INFO: {
        msg.sendResult(ToJson(*base.config).dumpYaml());
        break;
    }
    case app::GnbCliCommand::AMF_LIST: {
        Json json = Json::Arr({});
        for (auto &amf : base.ngapTask->m_amfCtx)
            json.push(Json::Obj({{"id", amf.first}}));
        msg.sendResult(json.dumpYaml());
        break;
    }
    case app::GnbCliCommand::AMF_INFO: {
        if (base.ngapTask->m_amfCtx.count(msg.cmd->amfId) == 0)
            msg.sendError("AMF not found with given ID");
        else
        {
            auto amf = base.ngapTask->m_amfCtx[msg.cmd->amfId];
            msg.sendResult(ToJson(*amf).dumpYaml());
        }
        break;
    }
    case app::GnbCliCommand::UE_LIST: {
        Json json = Json::Arr({});
        for (auto &ue : base.ngapTask->m_ueCtx)
        {
            json.push(Json::Obj({
                {"ue-name", base.mrTask->m_ueMap[ue.first].name},
                {"ran-ngap-id", ue.second->ranUeNgapId},
                {"amf-ngap-id", ue.second->amfUeNgapId},
            }));
        }
        msg.sendResult(json.dumpYaml());
        break;
    }
    case app::GnbCliCommand::UE_COUNT: {
        msg.sendResult(std::to_string(base.ngapTask->m_ueCtx.size()));
        break;
    }
    }
}

} // namespace nr::gnb