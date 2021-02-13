//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
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
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_STATUS_UPDATE: {
        auto *w = dynamic_cast<NwGnbStatusUpdate *>(msg);
        switch (w->what)
        {
        case NwGnbStatusUpdate::NGAP_IS_UP:
            m_statusInfo.isNgapUp = w->isNgapUp;
            break;
        }
        delete w;
        break;
    }
    case NtsMessageType::GNB_CLI_COMMAND: {
        auto *w = dynamic_cast<NwGnbCliCommand *>(msg);
        switch (w->cmd->present)
        {
        case app::GnbCliCommand::STATUS: {
            w->sendResult(m_statusInfo.toString());
            delete w;
            break;
        }
        case app::GnbCliCommand::INFO: {
            w->sendResult(m_base->config->toString());
            delete w;
            break;
        }
        case app::GnbCliCommand::AMF_LIST:
        case app::GnbCliCommand::AMF_STATUS: {
            m_base->ngapTask->push(w);
            break;
        }
        default: {
            delete w;
            break;
        }
        }
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        delete msg;
        break;
    }
}

void GnbAppTask::onQuit()
{
}

} // namespace nr::gnb
