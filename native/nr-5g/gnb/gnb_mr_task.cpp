//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_mr_task.hpp"

namespace nr::gnb
{

GnbMrTask::GnbMrTask(TaskBase *base) : base{base}
{
    logger = base->logBase->makeUniqueLogger("gnb-mr");
}

void GnbMrTask::onStart()
{
}

void GnbMrTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_MR_UPLINK_RRC:
        // todo push to rrc task
        break;
    case NtsMessageType::GNB_MR_DOWNLINK_RRC:
        // todo send to related UE
        break;
    case NtsMessageType::GNB_MR_UPLINK_DATA:
        // todo push to gtp task
        break;
    case NtsMessageType::GNB_MR_DOWNLINK_DATA:
        // todo: push to related UE
        break;
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

void GnbMrTask::onQuit()
{
    logger->debug("MR task is quiting");
    logger->flush();
}

} // namespace nr::gnb
