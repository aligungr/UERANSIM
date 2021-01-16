//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_task.hpp"
#include <convert.hpp>

namespace nr::gnb
{

NgapTask::NgapTask(GnbConfig *config, logger::LogBase &loggerBase)
    : config{config}, ueNgapIdCounter{}, waitingSctpClients{}, sctpTask{}, rrcTask{}
{
    logger = loggerBase.makeUniqueLogger("ngap");
}

void NgapTask::setExternalTasks(SctpTask *sctp, GnbRrcTask *rrc)
{
    this->sctpTask = sctp;
    this->rrcTask = rrc;
}

void NgapTask::onStart()
{
    logger->debug("NGAP layer has been started");

    for (auto &amfConfig : config->amfConfigs)
        createAmfContext(amfConfig);
    if (amfContexts.empty())
        logger->warn("No AMF configuration is provided");

    for (auto &amfCtx : amfContexts)
    {
        sctpTask->push(new NwSctpConnectionRequest(amfCtx.second->ctxId, config->ngapIp, 0, amfCtx.second->address,
                                                   amfCtx.second->port, sctp::PayloadProtocolId::NGAP, this));
        waitingSctpClients++;
    }
}

void NgapTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::SCTP_ASSOCIATION_SETUP:
        receiveAssociationSetup(dynamic_cast<NwSctpAssociationSetup *>(msg));
        break;
    case NtsMessageType::SCTP_CLIENT_RECEIVE:
        receiveSctpMessage(dynamic_cast<NwSctpClientReceive *>(msg));
        break;
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

void NgapTask::onQuit()
{
    // TODO
}

} // namespace nr::gnb
