//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "ctl_task.hpp"

#include <stdexcept>
#include <utils/common.hpp>

static constexpr const size_t MAX_PDU_COUNT = 4096;
static constexpr const int MAX_PDU_TTL = 3000;

static constexpr const int TIMER_ID_ACK_CONTROL = 1;
static constexpr const int TIMER_ID_ACK_SEND = 2;

static constexpr const int TIMER_PERIOD_ACK_CONTROL = 1500;
static constexpr const int TIMER_PERIOD_ACK_SEND = 2250;

namespace nr::gnb
{

RlsControlTask::RlsControlTask(TaskBase *base, uint64_t sti)
    : m_sti{sti}, m_mainTask{}, m_udpTask{}, m_pduMap{}, m_pendingAck{}
{
    m_logger = base->logBase->makeUniqueLogger("rls-ctl");
}

void RlsControlTask::initialize(NtsTask *mainTask, RlsUdpTask *udpTask)
{
    m_mainTask = mainTask;
    m_udpTask = udpTask;
}

void RlsControlTask::onStart()
{
    setTimer(TIMER_ID_ACK_CONTROL, TIMER_PERIOD_ACK_CONTROL);
    setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
}

void RlsControlTask::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_RLS_TO_RLS: {
        auto &w = dynamic_cast<NmGnbRlsToRls &>(*msg);
        switch (w.present)
        {
        case NmGnbRlsToRls::SIGNAL_DETECTED:
            handleSignalDetected(w.ueId);
            break;
        case NmGnbRlsToRls::SIGNAL_LOST:
            handleSignalLost(w.ueId);
            break;
        case NmGnbRlsToRls::RECEIVE_RLS_MESSAGE:
            handleRlsMessage(w.ueId, *w.msg);
            break;
        case NmGnbRlsToRls::DOWNLINK_DATA:
            handleDownlinkDataDelivery(w.ueId, w.psi, std::move(w.data));
            break;
        case NmGnbRlsToRls::DOWNLINK_RRC:
            handleDownlinkRrcDelivery(w.ueId, w.pduId, w.rrcChannel, std::move(w.data));
            break;
        default:
            m_logger->unhandledNts(*msg);
            break;
        }
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto &w = dynamic_cast<NmTimerExpired &>(*msg);
        if (w.timerId == TIMER_ID_ACK_CONTROL)
        {
            setTimer(TIMER_ID_ACK_CONTROL, TIMER_PERIOD_ACK_CONTROL);
            onAckControlTimerExpired();
        }
        else if (w.timerId == TIMER_ID_ACK_SEND)
        {
            setTimer(TIMER_ID_ACK_SEND, TIMER_PERIOD_ACK_SEND);
            onAckSendTimerExpired();
        }
        break;
    }
    default:
        m_logger->unhandledNts(*msg);
        break;
    }
}

void RlsControlTask::onQuit()
{
}

void RlsControlTask::handleSignalDetected(int ueId)
{
    auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::SIGNAL_DETECTED);
    w->ueId = ueId;
    m_mainTask->push(std::move(w));
}

void RlsControlTask::handleSignalLost(int ueId)
{
    auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::SIGNAL_LOST);
    w->ueId = ueId;
    m_mainTask->push(std::move(w));
}

void RlsControlTask::handleRlsMessage(int ueId, rls::RlsMessage &msg)
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
            m_pendingAck[ueId].push_back(m.pduId);

        if (m.pduType == rls::EPduType::DATA)
        {
            auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::UPLINK_DATA);
            w->ueId = ueId;
            w->psi = static_cast<int>(m.payload);
            w->data = std::move(m.pdu);
            m_mainTask->push(std::move(w));
        }
        else if (m.pduType == rls::EPduType::RRC)
        {
            auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::UPLINK_RRC);
            w->ueId = ueId;
            w->rrcChannel = static_cast<rrc::RrcChannel>(m.payload);
            w->data = std::move(m.pdu);
            m_mainTask->push(std::move(w));
        }
        else
        {
            m_logger->err("Unhandled RLS PDU type");
        }
    }
    else
    {
        m_logger->err("Unhandled RLS message type");
    }
}

void RlsControlTask::handleDownlinkRrcDelivery(int ueId, uint32_t pduId, rrc::RrcChannel channel, OctetString &&data)
{
    if (ueId == 0 && pduId != 0)
    {
        // PDU ID must be not set in case of broadcast
        throw std::runtime_error("");
    }

    if (pduId != 0)
    {
        if (m_pduMap.count(pduId))
        {
            m_pduMap.clear();

            auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::RADIO_LINK_FAILURE);
            w->rlfCause = rls::ERlfCause::PDU_ID_EXISTS;
            m_mainTask->push(std::move(w));
            return;
        }

        if (m_pduMap.size() > MAX_PDU_COUNT)
        {
            m_pduMap.clear();

            auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::RADIO_LINK_FAILURE);
            w->rlfCause = rls::ERlfCause::PDU_ID_FULL;
            m_mainTask->push(std::move(w));
            return;
        }

        m_pduMap[pduId].endPointId = ueId;
        m_pduMap[pduId].id = pduId;
        m_pduMap[pduId].pdu = data.copy();
        m_pduMap[pduId].rrcChannel = channel;
        m_pduMap[pduId].sentTime = utils::CurrentTimeMillis();
    }

    rls::RlsPduTransmission msg{m_sti};
    msg.pduType = rls::EPduType::RRC;
    msg.pdu = std::move(data);
    msg.payload = static_cast<uint32_t>(channel);
    msg.pduId = pduId;

    m_udpTask->send(ueId, msg);
}

void RlsControlTask::handleDownlinkDataDelivery(int ueId, int psi, OctetString &&data)
{
    rls::RlsPduTransmission msg{m_sti};
    msg.pduType = rls::EPduType::DATA;
    msg.pdu = std::move(data);
    msg.payload = static_cast<uint32_t>(psi);
    msg.pduId = 0;

    m_udpTask->send(ueId, msg);
}

void RlsControlTask::onAckControlTimerExpired()
{
    int64_t current = utils::CurrentTimeMillis();

    std::vector<uint32_t> transmissionFailureIds;
    std::vector<rls::PduInfo> transmissionFailures;

    for (auto &pdu : m_pduMap)
    {
        auto delta = current - pdu.second.sentTime;
        if (delta > MAX_PDU_TTL)
        {
            transmissionFailureIds.push_back(pdu.first);
            transmissionFailures.push_back(std::move(pdu.second));
        }
    }

    for (auto id : transmissionFailureIds)
        m_pduMap.erase(id);

    if (!transmissionFailures.empty())
    {
        auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::TRANSMISSION_FAILURE);
        w->pduList = std::move(transmissionFailures);
        m_mainTask->push(std::move(w));
    }
}

void RlsControlTask::onAckSendTimerExpired()
{
    auto copy = m_pendingAck;
    m_pendingAck.clear();

    for (auto &item : copy)
    {
        if (!item.second.empty())
            continue;

        rls::RlsPduTransmissionAck msg{m_sti};
        msg.pduIds = std::move(item.second);

        m_udpTask->send(item.first, msg);
    }
}

} // namespace nr::gnb
