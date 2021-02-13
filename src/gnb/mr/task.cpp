//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include "rls.hpp"
#include <gnb/nts.hpp>
#include <gnb/rrc/task.hpp>
#include <gnb/gtp/task.hpp>
#include <utils/constants.hpp>
#include <utils/libc_error.hpp>

static const int TIMER_ID_RLS_HEARTBEAT = 1;

namespace nr::gnb
{

GnbMrTask::GnbMrTask(TaskBase *base) : m_base{base}, m_udpTask{}, m_rlsEntity{}, m_ueMap{}
{
    m_logger = m_base->logBase->makeUniqueLogger("mr");
}

void GnbMrTask::onStart()
{
    m_rlsEntity = new GnbRls(m_base->config->name, m_base->logBase->makeUniqueLogger("rls"), this);

    try
    {
        m_udpTask = new udp::UdpServerTask(m_base->config->portalIp, cons::PortalPort, this);
        m_udpTask->start();
    }
    catch (const LibError &e)
    {
        m_logger->err("MR failure [%s]", e.what());
        quit();
        return;
    }

    setTimer(TIMER_ID_RLS_HEARTBEAT, rls::Constants::HB_PERIOD_UE_TO_GNB);
}

void GnbMrTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_MR_TO_MR: {
        auto *w = dynamic_cast<NwGnbMrToMr *>(msg);
        switch (w->present)
        {
        case NwGnbMrToMr::UE_CONNECTED: {
            onUeConnected(w->ue, w->name);
            break;
        }
        case NwGnbMrToMr::UE_RELEASED: {
            onUeReleased(w->ue, w->cause);
            break;
        }
        case NwGnbMrToMr::SEND_OVER_UDP: {
            m_udpTask->send(w->address, w->pdu);
            break;
        }
        case NwGnbMrToMr::RECEIVE_OVER_UDP: {
            receiveUplinkPayload(w->ue, w->type, std::move(w->pdu));
            break;
        }
        }
        break;
    }
    case NtsMessageType::GNB_GTP_TO_MR: {
        auto *w = dynamic_cast<NwGnbGtpToMr *>(msg);
        switch (w->present)
        {
        case NwGnbGtpToMr::DATA_PDU_DELIVERY: {
            OctetString stream{};
            stream.appendOctet4(static_cast<int>(w->pduSessionId));
            stream.append(w->data);

            m_rlsEntity->downlinkPayloadDelivery(w->ueId, rls::EPayloadType::DATA, std::move(stream));
            break;
        }
        }
        break;
    }
    case NtsMessageType::GNB_RRC_TO_MR: {
        auto *w = dynamic_cast<NwGnbRrcToMr *>(msg);
        switch (w->present)
        {
        case NwGnbRrcToMr::RRC_PDU_DELIVERY: {
            OctetString stream{};
            stream.appendOctet(static_cast<int>(w->channel));
            stream.append(w->pdu);

            m_rlsEntity->downlinkPayloadDelivery(w->ueId, rls::EPayloadType::RRC, std::move(stream));
            break;
        }
        case NwGnbRrcToMr::NGAP_LAYER_INITIALIZED: {
            m_rlsEntity->setAcceptConnections(true);
            break;
        }
        }
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        if (w->timerId == TIMER_ID_RLS_HEARTBEAT)
        {
            setTimer(TIMER_ID_RLS_HEARTBEAT, rls::Constants::HB_PERIOD_GNB_TO_UE);
            m_rlsEntity->onHeartbeat();
        }
        break;
    }
    case NtsMessageType::UDP_SERVER_RECEIVE: {
        auto *w = dynamic_cast<udp::NwUdpServerReceive *>(msg);
        m_rlsEntity->onReceive(w->fromAddress, w->packet);
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }

    delete msg;
}

void GnbMrTask::onQuit()
{
    delete m_rlsEntity;

    if (m_udpTask != nullptr)
        m_udpTask->quit();
    delete m_udpTask;
}

void GnbMrTask::onUeConnected(int ue, const std::string &name)
{
    m_ueMap[ue] = {};
    m_ueMap[ue].ueId = ue;
    m_ueMap[ue].name = name;

    m_logger->info("New UE connected to gNB. Total number of UEs is now: %d", m_ueMap.size());
}

void GnbMrTask::onUeReleased(int ue, rls::ECause cause)
{
    m_ueMap.erase(ue);
    m_logger->info("A UE disconnected from gNB. Total number of UEs is now: %d", m_ueMap.size());
}

void GnbMrTask::receiveUplinkPayload(int ue, rls::EPayloadType type, OctetString &&payload)
{
    if (type == rls::EPayloadType::RRC)
    {
        auto *nw = new NwGnbMrToRrc(NwGnbMrToRrc::RRC_PDU_DELIVERY);
        nw->ueId = ue;
        nw->channel = static_cast<rrc::RrcChannel>(payload.getI(0));
        nw->pdu = payload.subCopy(1);
        m_base->rrcTask->push(nw);
    }
    else if (type == rls::EPayloadType::DATA)
    {
        int psi = payload.get4I(0);
        OctetString dataPayload = payload.subCopy(4);

        auto *w = new NwGnbMrToGtp(NwGnbMrToGtp::UPLINK_DELIVERY);
        w->ueId = ue;
        w->pduSessionId = psi;
        w->data = std::move(dataPayload);
        m_base->gtpTask->push(w);
    }
}

} // namespace nr::gnb
