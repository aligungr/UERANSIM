//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_mr_task.hpp"
#include "gnb_mr_rls.hpp"
#include "gnb_nts.hpp"

#include <constants.hpp>

static const int TIMER_ID_RLS_HEARTBEAT = 1;

namespace nr::gnb
{

GnbMrTask::GnbMrTask(TaskBase *base) : base{base}, udpTask{}, rlsEntity{}, ueMap{}
{
    logger = base->logBase->makeUniqueLogger("gnb-mr");
}

void GnbMrTask::onStart()
{
    rlsEntity = new GnbRls(base->config->name, base->logBase->makeUniqueLogger("gnb-rls"), this);

    udpTask = new udp::UdpServerTask(base->config->portalIp, cons::PortalPort, this);
    udpTask->start();

    setTimer(TIMER_ID_RLS_HEARTBEAT, rls::Constants::HB_PERIOD_UE_TO_GNB);
}

void GnbMrTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_MR_DOWNLINK_RRC: {
        auto *w = dynamic_cast<NwGnbDownlinkRrc *>(msg);

        // Append channel information
        OctetString stream{};
        stream.appendOctet(static_cast<int>(w->channel));
        stream.append(w->rrcPdu);

        rlsEntity->downlinkPayloadDelivery(w->ueId, rls::EPayloadType::RRC, std::move(stream));
        delete w;
        break;
    }
    case NtsMessageType::GNB_MR_DOWNLINK_DATA: {
        auto *w = dynamic_cast<NwDownlinkData *>(msg);

        // Append PDU session information
        OctetString stream{};
        stream.appendOctet4(static_cast<int>(w->pduSessionId));
        stream.append(w->data);

        rlsEntity->downlinkPayloadDelivery(w->ueId, rls::EPayloadType::DATA, std::move(stream));
        delete w;
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        if (w->timerId == TIMER_ID_RLS_HEARTBEAT)
        {
            setTimer(TIMER_ID_RLS_HEARTBEAT, rls::Constants::HB_PERIOD_GNB_TO_UE);
            rlsEntity->onHeartbeat();
        }
        delete w;
        break;
    }
    case NtsMessageType::UDP_SERVER_RECEIVE: {
        auto *w = dynamic_cast<udp::NwUdpServerReceive *>(msg);
        rlsEntity->onReceive(w->fromAddress, w->packet);
        delete w;
        break;
    }
    case NtsMessageType::GNB_RLS_UE_CONNECTED: {
        auto *w = dynamic_cast<NwUeConnected *>(msg);
        onUeConnected(w->ue, w->name);
        delete w;
        break;
    }
    case NtsMessageType::GNB_RLS_UE_RELEASED: {
        auto *w = dynamic_cast<NwUeReleased *>(msg);
        onUeReleased(w->ue, w->cause);
        delete w;
        break;
    }
    case NtsMessageType::GNB_RLS_UPLINK_PAYLOAD: {
        auto *w = dynamic_cast<NwUplinkPayload *>(msg);
        receiveUplinkPayload(w->ue, w->type, std::move(w->payload));
        delete w;
        break;
    }
    case NtsMessageType::GNB_RLS_SEND_PDU: {
        auto *w = dynamic_cast<NwRlsSendPdu *>(msg);
        udpTask->send(w->address, w->pdu);
        delete w;
        break;
    }
    case NtsMessageType::GNB_MR_N1_IS_READY: {
        rlsEntity->setN1IsReady(true);
        delete msg;
        break;
    }
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

    delete rlsEntity;

    udpTask->quit();
    delete udpTask;
}

void GnbMrTask::onUeConnected(int ue, const std::string &name)
{
    ueMap[ue] = {};
    ueMap[ue].ueId = ue;
    ueMap[ue].name = name;

    logger->info("New UE connected to gNB. Total number of UEs is now: %d", ueMap.size());
}

void GnbMrTask::onUeReleased(int ue, rls::ECause cause)
{
    ueMap.erase(ue);
    logger->info("A UE disconnected from gNB. Total number of UEs is now: %d", ueMap.size());
}

void GnbMrTask::receiveUplinkPayload(int ue, rls::EPayloadType type, OctetString &&payload)
{
    if (type == rls::EPayloadType::RRC)
    {
        auto ch = static_cast<rrc::RrcChannel>(payload.getI(0));
        OctetString rrcPayload = payload.subCopy(1);
        base->rrcTask->push(new NwGnbUplinkRrc(ue, ch, std::move(rrcPayload)));
    }
    else if (type == rls::EPayloadType::DATA)
    {
        int psi = payload.get4I(0);
        OctetString dataPayload = payload.subCopy(4);
        base->gtpTask->push(new NwUplinkData(ue, psi, std::move(dataPayload)));
    }
}

} // namespace nr::gnb
