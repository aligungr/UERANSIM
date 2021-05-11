//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ctl_task.hpp"

#include <utils/common.hpp>

static constexpr const size_t MAX_PDU_COUNT = 128;

static constexpr const int TIMER_ID_ACK_CONTROL = 1;
static constexpr const int TIMER_ID_ACK_SEND = 2;

static constexpr const int TIMER_PERIOD_ACK_CONTROL = 3000;
static constexpr const int TIMER_PERIOD_ACK_SEND = 2250;

namespace nr::ue
{

RlsControlTask::RlsControlTask(TaskBase *base, uint64_t sti) : m_udpTask{}, m_pduMap{}, m_sti{sti}, m_pendingAck{}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "rls-ctl");
}

void RlsControlTask::initialize(RlsUdpTask *udpTask)
{
    m_udpTask = udpTask;
}

void RlsControlTask::onStart()
{
    setTimer(TIMER_ID_ACK_CONTROL, TIMER_PERIOD_ACK_CONTROL);
    setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
}

void RlsControlTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_RLS_TO_RLS: {
        auto *w = dynamic_cast<NwUeRlsToRls *>(msg);
        switch (w->present)
        {
        case NwUeRlsToRls::SIGNAL_CHANGED:
            handleSignalChange(w->cellId, w->dbm);
            break;
        case NwUeRlsToRls::RECEIVE_RLS_MESSAGE:
            handleRlsMessage(w->cellId, *w->msg);
            break;
        case NwUeRlsToRls::UPLINK_DATA:
            handleUplinkDataDelivery(w->cellId, w->psi, std::move(w->data));
            break;
        case NwUeRlsToRls::UPLINK_RRC:
            handleUplinkRrcDelivery(w->cellId, w->pduId, w->rrcChannel, std::move(w->data));
            break;
        default:
            m_logger->unhandledNts(msg);
            break;
        }
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        if (w->timerId == TIMER_ID_ACK_CONTROL)
        {
            setTimer(TIMER_ID_ACK_CONTROL, TIMER_PERIOD_ACK_CONTROL);
            onAckControlTimerExpired();
        }
        else if (w->timerId == TIMER_ID_ACK_SEND)
        {
            setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
            onAckSendTimerExpired();
        }
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }

    delete msg;
}

void RlsControlTask::onQuit()
{
}

void RlsControlTask::handleRlsMessage(int cellId, rls::RlsMessage &msg)
{
    if (msg.msgType == rls::EMessageType::PDU_TRANSMISSION_ACK)
    {
        auto &m = (rls::RlsPduTransmissionAck &)msg;
        for (auto pduId : m.pduIds)
            m_pduMap.erase(pduId);
    }
    else if (msg.msgType == rls::EMessageType::PDU_TRANSMISSION)
    {
        auto &m = (rls::RlsPduTransmission &)msg;
        if (m.pduId != 0)
            m_pendingAck[cellId].push_back(m.pduId);

        if (m.pduType == rls::EPduType::DATA)
        {
            // NOTE: Data packet may be received from a cell other than serving cell
            //  (This is not a problem for RRC, but for DATA), normally we should avoid this, no need for now.
            (void)cellId;

            int psi = static_cast<int>(m.payload);

            // TODO: send to upper layer [PSI, DATA]
        }
        else if (m.pduType == rls::EPduType::RRC)
        {
            auto rrcChannel = static_cast<rrc::RrcChannel>(m.payload);

            // TODO: send to upper layer [rrcChannel, DATA]
        }
        else
        {
            // TODO: log
        }
    }
    else
    {
        // TODO: log
    }
}

void RlsControlTask::handleSignalChange(int cellId, int dbm)
{
    // TODO transparently send to the RRC
}

void RlsControlTask::handleUplinkRrcDelivery(int cellId, uint32_t pduId, rrc::RrcChannel channel, OctetString &&data)
{
    if (pduId != 0)
    {
        if (m_pduMap.count(pduId))
        {
            // TODO: issue RLF

            m_pduMap.clear();
            return;
        }

        if (m_pduMap.size() > MAX_PDU_COUNT)
        {
            // TODO: issue RLF

            m_pduMap.clear();
            return;
        }

        m_pduMap[pduId].pdu = data.copy();
        m_pduMap[pduId].rrcChannel = channel;
        m_pduMap[pduId].sentTime = utils::CurrentTimeMillis();
    }

    rls::RlsPduTransmission msg{m_sti};
    msg.pduType = rls::EPduType::RRC;
    msg.pdu = std::move(data);
    msg.payload = static_cast<uint32_t>(channel);
    msg.pduId = pduId;

    m_udpTask->send(cellId, msg);
}

void RlsControlTask::handleUplinkDataDelivery(int cellId, int psi, OctetString &&data)
{
    rls::RlsPduTransmission msg{m_sti};
    msg.pduType = rls::EPduType::DATA;
    msg.pdu = std::move(data);
    msg.payload = static_cast<uint32_t>(psi);
    msg.pduId = 0;

    m_udpTask->send(cellId, msg);
}

void RlsControlTask::onAckControlTimerExpired()
{
    // TODO
}

void RlsControlTask::onAckSendTimerExpired()
{
    auto copy = m_pendingAck;
    m_pendingAck.clear();

    for (auto &item : copy)
    {
        rls::RlsPduTransmissionAck msg{m_sti};
        msg.pduIds = std::move(item.second);

        m_udpTask->send(item.first, msg);
    }
}

} // namespace nr::ue
