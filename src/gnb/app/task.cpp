//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"
#include "cmd_handler.hpp"

#include <gnb/nts.hpp>

namespace nr::gnb
{

GnbAppTask::GnbAppTask(TaskBase *base) : m_base{base}, m_statusInfo{}
{
    m_logger = m_base->logBase->makeUniqueLogger("app");
}

void GnbAppTask::onStart()
{
}

void GnbAppTask::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_STATUS_UPDATE: {
        auto& w = dynamic_cast<NmGnbStatusUpdate &>(*msg);
        switch (w.what)
        {
        case NmGnbStatusUpdate::NGAP_IS_UP:
            m_statusInfo.isNgapUp = w.isNgapUp;
            break;
        }
        break;
    }
    case NtsMessageType::GNB_CLI_COMMAND: {
        auto& w = dynamic_cast<NmGnbCliCommand &>(*msg);
        GnbCmdHandler handler{m_base};
        handler.handleCmd(w);
        break;
    }
    default:
        m_logger->unhandledNts(*msg);
        break;
    }
}

void GnbAppTask::onQuit()
{
}

} // namespace nr::gnb
