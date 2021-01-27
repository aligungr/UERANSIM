//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_mr_task.hpp"
#include "ue_nts.hpp"

#include <common.hpp>
#include <constants.hpp>

static const int TIMER_ID_RLS_WAITING_TIMER = 1;
static const int TIMER_ID_RLS_HEARTBEAT = 2;
static const int PLMN_SEARCH_FAILED_PRINT_THRESHOLD = 10000;

namespace nr::ue
{

ue::UeMrTask::UeMrTask(TaskBase *base) : base{base}, udpTask{}, rlsEntity{}, lastPlmnSearchFailurePrinted{}
{
    logger = base->logBase->makeUniqueLogger("ue-mr");
}

void UeMrTask::onStart()
{
    udpTask = new udp::UdpServerTask(this);

    std::vector<InetAddress> gnbSearchList{};
    for (auto &ip : base->config->gnbSearchList)
        gnbSearchList.emplace_back(ip, cons::PortalPort);

    rlsEntity = new UeRls(base->config->getNodeName(), gnbSearchList, base->logBase->makeUniqueLogger("ue-rls"), this);

    udpTask->start();

    setTimer(TIMER_ID_RLS_HEARTBEAT, rls::Constants::HB_PERIOD_UE_TO_GNB);
}

void UeMrTask::onQuit()
{
    delete rlsEntity;

    udpTask->quit();
    delete udpTask;
}

void UeMrTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_MR_PLMN_SEARCH_REQUEST: {
        rlsEntity->startGnbSearch();
        delete msg;
        break;
    }
    case NtsMessageType::UE_RLS_SEND_PDU: {
        auto *w = dynamic_cast<NwRlsSendPdu *>(msg);
        udpTask->send(w->address, w->pdu);
        delete w;
        break;
    }
    case NtsMessageType::UE_RLS_START_WAITING_TIMER: {
        auto *w = dynamic_cast<NwRlsStartWaitingTimer *>(msg);
        setTimer(TIMER_ID_RLS_WAITING_TIMER, w->period);
        delete w;
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        if (w->timerId == TIMER_ID_RLS_WAITING_TIMER)
        {
            rlsEntity->onWaitingTimerExpire();
        }
        else if (w->timerId == TIMER_ID_RLS_HEARTBEAT)
        {
            setTimer(TIMER_ID_RLS_HEARTBEAT, rls::Constants::HB_PERIOD_UE_TO_GNB);
            rlsEntity->onHeartbeat();
        }
        delete w;
        break;
    }
    case NtsMessageType::UE_RLS_SEARCH_FAILURE: {
        auto *w = dynamic_cast<NwRlsSearchFailure *>(msg);
        long current = utils::CurrentTimeMillis();
        if (current - lastPlmnSearchFailurePrinted > PLMN_SEARCH_FAILED_PRINT_THRESHOLD)
        {
            logger->err("PLMN search failed (%s)", rls::CauseToString(w->cause));
            lastPlmnSearchFailurePrinted = current;
        }
        delete w;
        break;
    }
    case NtsMessageType::UE_RLS_RELEASED: {
        auto *w = dynamic_cast<NwRlsReleased *>(msg);
        logger->info("UE RLS released (%s)", rls::CauseToString(w->cause));
        delete w;
        break;
    }
    case NtsMessageType::UDP_SERVER_RECEIVE: {
        auto *w = dynamic_cast<udp::NwUdpServerReceive *>(msg);
        rlsEntity->onReceive(w->fromAddress, w->packet);
        delete w;
        break;
    }
    case NtsMessageType::UE_RLS_CONNECTED: {
        auto *w = dynamic_cast<NwRlsConnected *>(msg);
        base->rrcTask->push(new NwPlmnSearchResponse(std::move(w->gnbName)));
        delete w;
        break;
    }
    case NtsMessageType::UE_MR_UPLINK_RRC: {
        auto *w = dynamic_cast<NwUeUplinkRrc *>(msg);

        // Append channel information
        OctetString stream{};
        stream.appendOctet(static_cast<int>(w->channel));
        stream.append(w->rrcPdu);

        rlsEntity->onUplinkDelivery(rls::EPayloadType::RRC, std::move(stream));
        delete w;
        break;
    }
    case NtsMessageType::UE_MR_UPLINK_DATA: {
        auto *w = dynamic_cast<NwUeUplinkData *>(msg);

        // Append PDU session information
        OctetString stream{};
        stream.appendOctet4(w->psi);
        stream.append(w->data);

        rlsEntity->onUplinkDelivery(rls::EPayloadType::DATA, std::move(stream));
        delete w;
        break;
    }
    case NtsMessageType::UE_RLS_DOWNLINK_PAYLOAD: {
        auto *w = dynamic_cast<NwDownlinkPayload *>(msg);
        receiveDownlinkPayload(w->type, std::move(w->payload));
        delete w;
        break;
    }
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

void UeMrTask::receiveDownlinkPayload(rls::EPayloadType type, OctetString &&payload)
{
    if (type == rls::EPayloadType::RRC)
    {
        auto ch = static_cast<rrc::RrcChannel>(payload.getI(0));
        OctetString rrcPayload = payload.subCopy(1);
        base->rrcTask->push(new NwUeDownlinkRrc(ch, std::move(rrcPayload)));
    }
    else if (type == rls::EPayloadType::DATA)
    {
        int psi = payload.get4I(0);
        OctetString dataPayload = payload.subCopy(4);
        base->appTask->push(new NwUeDownlinkData(psi, std::move(dataPayload)));
    }
}

} // namespace nr::ue
