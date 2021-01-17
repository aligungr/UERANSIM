//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_gtp_task.hpp"

namespace nr::gnb
{

GtpTask::GtpTask(GnbConfig *config, logger::LogBase &loggerBase) : config(config), udpServer{}
{
    logger = loggerBase.makeUniqueLogger("gtp");
}

void GtpTask::onStart()
{
    logger->debug("GTP layer has been started");

    udpServer = new udp::UdpServerTask(config->gtpIp, 2152, this);
    udpServer->start();
}

void GtpTask::onQuit()
{
    udpServer->quit();
    delete udpServer;
}

void GtpTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UDP_SERVER_RECEIVE:
        break; // TODO
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

} // namespace nr::gnb
