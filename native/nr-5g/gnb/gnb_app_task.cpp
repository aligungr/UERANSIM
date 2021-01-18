//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_app_task.hpp"
#include "gnb_nts.hpp"

namespace nr::gnb
{

GnbAppTask::GnbAppTask(TaskBase *base) : base(base), statusInfo{}
{
    logger = base->logBase->makeUniqueLogger("gnb-app");
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
        auto *m = dynamic_cast<NwGnbStatusUpdate *>(msg);
        switch (m->what)
        {
        case NwGnbStatusUpdate::INITIAL_SCTP_ESTABLISHED:
            statusInfo.isInitialSctpEstablished = m->isInitialSctpEstablished;
            break;
        }
        break;
    }
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

void GnbAppTask::onQuit()
{
}

} // namespace nr::gnb
