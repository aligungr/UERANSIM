//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_task.hpp"
#include "gnb_app_task.hpp"
#include "gnb_gtp_task.hpp"
#include <common.hpp>

namespace nr::gnb
{

NgapTask::NgapTask(GnbConfig *config, logger::LogBase &loggerBase)
    : config{config}, ueNgapIdCounter{}, waitingSctpClients{}, downlinkTeidCounter{}, sctpTask{}, rrcTask{}, gtpTask{}
{
    logger = loggerBase.makeUniqueLogger("ngap");
}

void NgapTask::setExternalTasks(SctpTask *sctp, GnbRrcTask *rrc, GtpTask *gtp, GnbAppTask *app)
{
    this->sctpTask = sctp;
    this->rrcTask = rrc;
    this->gtpTask = gtp;
    this->appTask = app;
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
        handleAssociationSetup(dynamic_cast<NwSctpAssociationSetup *>(msg));
        break;
    case NtsMessageType::SCTP_CLIENT_RECEIVE:
        handleSctpMessage(dynamic_cast<NwSctpClientReceive *>(msg));
        break;
    case NtsMessageType::NGAP_UPLINK_NAS_DELIVERY:
        deliverUplinkNas(dynamic_cast<NwUplinkNasDelivery *>(msg));
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
