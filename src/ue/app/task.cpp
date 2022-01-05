//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include "cmd_handler.hpp"
#include <ue/tun/config.hpp>

namespace nr::ue
{

UeAppTask::UeAppTask(TaskBase *base) : m_base{base}
{
    m_logger = m_base->logBase->makeUniqueLogger(m_base->config->getLoggerPrefix() + "app");
}

void UeAppTask::onStart()
{
}

void UeAppTask::onQuit()
{
}

void UeAppTask::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_CLI_COMMAND: {
        auto &w = dynamic_cast<NmUeCliCommand &>(*msg);
        UeCmdHandler handler{m_base};
        handler.handleCmd(w);
        break;
    }
    default:
        m_logger->unhandledNts(*msg);
        break;
    }
}

} // namespace nr::ue
