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
#include "gnb_sctp_task.hpp"

#include <common.hpp>

namespace nr::gnb
{

NgapTask::NgapTask(TaskBase *base) : base{base}, ueNgapIdCounter{}, waitingSctpClients{}, downlinkTeidCounter{}
{
    logger = base->logBase->makeUniqueLogger("ngap");
}

void NgapTask::onStart()
{
    logger->debug("NGAP layer has been started");

    for (auto &amfConfig : base->config->amfConfigs)
        createAmfContext(amfConfig);
    if (amfContexts.empty())
        logger->warn("No AMF configuration is provided");

    for (auto &amfCtx : amfContexts)
    {
        base->sctpTask->push(new NwSctpConnectionRequest(amfCtx.second->ctxId, base->config->ngapIp, 0, amfCtx.second->address,
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
    for (auto &i : ueContexts)
        delete i.second;
    for (auto &i : amfContexts)
        delete i.second;
    ueContexts.clear();
    amfContexts.clear();
}

} // namespace nr::gnb
