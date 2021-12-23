//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "udp_task.hpp"

#include <cmath>
#include <cstdint>
#include <cstring>
#include <set>

#include <gnb/nts.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/libc_error.hpp>

static constexpr const int BUFFER_SIZE = 16384;

static constexpr const int LOOP_PERIOD = 1000;
static constexpr const int RECEIVE_TIMEOUT = 200;
static constexpr const int HEARTBEAT_THRESHOLD = 2000; // (LOOP_PERIOD + RECEIVE_TIMEOUT)'dan büyük olmalı

static constexpr const int MIN_ALLOWED_DBM = -120;

static int EstimateSimulatedDbm(const Vector3 &myPos, const Vector3 &uePos)
{
    int deltaX = myPos.x - uePos.x;
    int deltaY = myPos.y - uePos.y;
    int deltaZ = myPos.z - uePos.z;

    int distance = static_cast<int>(std::sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ));
    if (distance == 0)
        return -1; // 0 may be confusing for people
    return -distance;
}

namespace nr::gnb
{

RlsUdpTask::RlsUdpTask(TaskBase *base, uint64_t sti, Vector3 phyLocation)
    : m_server{}, m_ctlTask{}, m_sti{sti}, m_phyLocation{phyLocation}, m_lastLoop{}, m_stiToUe{}, m_ueMap{}, m_newIdCounter{}
{
    m_logger = base->logBase->makeUniqueLogger("rls-udp");

    try
    {
        m_server = new udp::UdpServer(base->config->linkIp, cons::RadioLinkPort);
    }
    catch (const LibError &e)
    {
        m_logger->err("RLS failure [%s]", e.what());
        quit();
        return;
    }
}

void RlsUdpTask::onStart()
{
}

void RlsUdpTask::onLoop()
{
    auto current = utils::CurrentTimeMillis();
    if (current - m_lastLoop > LOOP_PERIOD)
    {
        m_lastLoop = current;
        heartbeatCycle(current);
    }

    uint8_t buffer[BUFFER_SIZE];
    InetAddress peerAddress;

    int size = m_server->Receive(buffer, BUFFER_SIZE, RECEIVE_TIMEOUT, peerAddress);
    if (size > 0)
    {
        auto rlsMsg = rls::DecodeRlsMessage(OctetView{buffer, static_cast<size_t>(size)});
        if (rlsMsg == nullptr)
            m_logger->err("Unable to decode RLS message");
        else
            receiveRlsPdu(peerAddress, std::move(rlsMsg));
    }
}

void RlsUdpTask::onQuit()
{
    delete m_server;
}

void RlsUdpTask::receiveRlsPdu(const InetAddress &addr, std::unique_ptr<rls::RlsMessage> &&msg)
{
    if (msg->msgType == rls::EMessageType::HEARTBEAT)
    {
        int dbm = EstimateSimulatedDbm(m_phyLocation, ((const rls::RlsHeartBeat &)*msg).simPos);
        if (dbm < MIN_ALLOWED_DBM)
        {
            // if the simulated signal strength is such low, then ignore this message
            return;
        }

        if (m_stiToUe.count(msg->sti))
        {
            int ueId = m_stiToUe[msg->sti];
            m_ueMap[ueId].address = addr;
            m_ueMap[ueId].lastSeen = utils::CurrentTimeMillis();
        }
        else
        {
            int ueId = ++m_newIdCounter;

            m_stiToUe[msg->sti] = ueId;
            m_ueMap[ueId].address = addr;
            m_ueMap[ueId].lastSeen = utils::CurrentTimeMillis();

            auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::SIGNAL_DETECTED);
            w->ueId = ueId;
            m_ctlTask->push(std::move(w));
        }

        rls::RlsHeartBeatAck ack{m_sti};
        ack.dbm = dbm;

        sendRlsPdu(addr, ack);
        return;
    }

    if (!m_stiToUe.count(msg->sti))
    {
        // if no HB received yet, and the message is not HB, then ignore the message
        return;
    }

    auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::RECEIVE_RLS_MESSAGE);
    w->ueId = m_stiToUe[msg->sti];
    w->msg = std::move(msg);
    m_ctlTask->push(std::move(w));
}

void RlsUdpTask::sendRlsPdu(const InetAddress &addr, const rls::RlsMessage &msg)
{
    OctetString stream;
    rls::EncodeRlsMessage(msg, stream);

    m_server->Send(addr, stream.data(), static_cast<size_t>(stream.length()));
}

void RlsUdpTask::heartbeatCycle(int64_t time)
{
    std::set<int> lostUeId{};
    std::set<uint64_t> lostSti{};

    for (auto &item : m_ueMap)
    {
        if (time - item.second.lastSeen > HEARTBEAT_THRESHOLD)
        {
            lostUeId.insert(item.first);
            lostSti.insert(item.second.sti);
        }
    }

    for (uint64_t sti : lostSti)
        m_stiToUe.erase(sti);

    for (int ueId : lostUeId)
        m_ueMap.erase(ueId);

    for (int ueId : lostUeId)
    {
        auto w = std::make_unique<NmGnbRlsToRls>(NmGnbRlsToRls::SIGNAL_LOST);
        w->ueId = ueId;
        m_ctlTask->push(std::move(w));
    }
}

void RlsUdpTask::initialize(NtsTask *ctlTask)
{
    m_ctlTask = ctlTask;
}

void RlsUdpTask::send(int ueId, const rls::RlsMessage &msg)
{
    if (ueId == 0)
    {
        for (auto &ue : m_ueMap)
            send(ue.first, msg);
        return;
    }

    if (!m_ueMap.count(ueId))
    {
        // ignore the message
        return;
    }

    sendRlsPdu(m_ueMap[ueId].address, msg);
}

} // namespace nr::gnb
