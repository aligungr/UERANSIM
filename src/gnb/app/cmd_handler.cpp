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
        msg.sendResult(base.appTask->m_statusInfo.toString());
        break;
    }
    case app::GnbCliCommand::INFO: {
        msg.sendResult(base.config->toString());
        break;
    }
    case app::GnbCliCommand::AMF_LIST: {
        std::stringstream ss{};
        for (auto &amf : base.ngapTask->m_amfCtx)
            ss << "- ID[" << amf.first << "]\n";
        utils::Trim(ss);
        msg.sendResult(ss.str());
        break;
    }
    case app::GnbCliCommand::AMF_INFO: {
        if (base.ngapTask->m_amfCtx.count(msg.cmd->amfId) == 0)
            msg.sendError("AMF not found with given ID");
        else
        {
            auto amf = base.ngapTask->m_amfCtx[msg.cmd->amfId];

            Printer printer{};
            printer.appendKeyValue({
                {"address", amf->address},
                {"port", std::to_string(amf->port)},
                {"name", amf->amfName},
                {"capacity", std::to_string(amf->relativeCapacity)},
                {"state", EnumToString(amf->state)},
            });
            printer.trim();
            msg.sendResult(printer.makeString());
        }
        break;
    }
    }
}

} // namespace nr::gnb