//
// Created by ali on 10.05.2021.
//

#include "udp_task.hpp"

#include <cstdint>
#include <cstring>
#include <set>

#include <ue/nts.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>

static constexpr const int BUFFER_SIZE = 16384;
static constexpr const int LOOP_PERIOD = 1000;
static constexpr const int HEARTBEAT_THRESHOLD = 2000;

namespace nr::ue
{

RlsUdpTask::RlsUdpTask(TaskBase *base, uint64_t sti, const std::vector<std::string> &searchSpace)
    : m_ctlTask{}, m_sti{sti}, m_searchSpace{}, m_cells{}, m_lastLoop{}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "rls-udp");

    m_server = new udp::UdpServer();

    for (auto &ip : searchSpace)
        m_searchSpace.emplace_back(ip, cons::PortalPort);

    m_simPos = Vector3{};
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
        heartbeatCycle(current, m_simPos);
    }

    uint8_t buffer[BUFFER_SIZE];
    InetAddress peerAddress;

    int size = m_server->Receive(buffer, BUFFER_SIZE, LOOP_PERIOD, peerAddress);
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

void RlsUdpTask::sendRlsPdu(const InetAddress &addr, const rls::RlsMessage &msg)
{
    OctetString stream;
    rls::EncodeRlsMessage(msg, stream);

    m_server->Send(addr, stream.data(), static_cast<size_t>(stream.length()));
}

void RlsUdpTask::send(uint64_t sti, const rls::RlsMessage &msg)
{
    if (m_cells.count(sti))
        sendRlsPdu(m_cells[sti].address, msg);
}

void RlsUdpTask::receiveRlsPdu(const InetAddress &addr, std::unique_ptr<rls::RlsMessage> &&msg)
{
    if (msg->msgType == rls::EMessageType::HEARTBEAT_ACK)
    {
        int oldDbm = INT32_MIN;
        if (m_cells.count(msg->sti))
            oldDbm = m_cells[msg->sti].dbm;

        m_cells[msg->sti].address = addr;
        m_cells[msg->sti].lastSeen = utils::CurrentTimeMillis();

        int newDbm = ((const rls::RlsHeartBeatAck &)msg).dbm;
        m_cells[msg->sti].dbm = newDbm;

        if (oldDbm != newDbm)
            onSignalChangeOrLost(msg->sti);
        return;
    }

    if (!m_cells.count(msg->sti))
    {
        // if no HB-ACK received yet, and the message is not HB-ACK, then ignore the message
        return;
    }

    auto *w = new NwRlsToRls(NwRlsToRls::RECEIVE_RLS_MESSAGE);
    w->msg = std::move(msg);
    m_ctlTask->push(w);
}

void RlsUdpTask::onSignalChangeOrLost(uint64_t sti)
{
    auto *w = new NwRlsToRls(NwRlsToRls::SIGNAL_CHANGED);
    w->sti = sti;
    w->dbm = m_cells.count(sti) ? m_cells[sti].dbm : INT32_MIN;
    m_ctlTask->push(w);
}

void RlsUdpTask::heartbeatCycle(uint64_t time, const Vector3 &simPos)
{
    std::set<uint64_t> stiToRemove;

    for (auto &cell : m_cells)
    {
        auto delta = time - cell.second.lastSeen;
        if (delta > HEARTBEAT_THRESHOLD)
            stiToRemove.insert(cell.first);
    }

    for (auto sti : stiToRemove)
        m_cells.erase(sti);

    for (auto sti : stiToRemove)
        onSignalChangeOrLost(sti);

    for (auto &addr : m_searchSpace)
    {
        rls::RlsHeartBeat msg{m_sti};
        msg.simPos = simPos;
        sendRlsPdu(addr, msg);
    }
}

void RlsUdpTask::initialize(NtsTask *ctlTask)
{
    m_ctlTask = ctlTask;
}

} // namespace nr::ue
