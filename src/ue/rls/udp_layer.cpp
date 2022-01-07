#include "udp_layer.hpp"

#include <cstdint>
#include <stdexcept>

#include <ue/task.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>

static constexpr const int SEND_BUFFER = 16384;
static constexpr const int LOOP_PERIOD = 1000;
static constexpr const int HEARTBEAT_THRESHOLD = 2000; // (LOOP_PERIOD + RECEIVE_TIMEOUT)'dan büyük olmalı

namespace nr::ue
{

RlsUdpLayer::RlsUdpLayer(UeTask *ue)
    : m_ue{ue}, m_sendBuffer{new uint8_t[SEND_BUFFER]}, m_searchSpace{}, m_cells{}, m_cellIdToSti{}, m_lastLoop{},
      m_cellIdCounter{}
{
    m_logger = ue->logBase->makeUniqueLogger(ue->config->getLoggerPrefix() + "rls-udp");

    for (auto &ip : ue->config->gnbSearchList)
        m_searchSpace.emplace_back(ip, cons::RadioLinkPort);

    m_simPos = Vector3{};

    m_ue->fdBase->allocate(FdBase::RLS_IP4, Socket::CreateUdp4().getFd());
    m_ue->fdBase->allocate(FdBase::RLS_IP6, Socket::CreateUdp6().getFd());
}

RlsUdpLayer::~RlsUdpLayer() = default;

void RlsUdpLayer::checkHeartbeat()
{
    auto current = utils::CurrentTimeMillis();
    if (current - m_lastLoop > LOOP_PERIOD)
    {
        m_lastLoop = current;
        heartbeatCycle(current, m_simPos);
    }
}

void RlsUdpLayer::sendRlsPdu(const InetAddress &address, const rls::RlsMessage &msg)
{
    int n = rls::EncodeRlsMessage(msg, m_sendBuffer.get());

    int version = address.getIpVersion();
    if (version != 4 && version != 6)
        throw std::runtime_error{"UdpServer::Send failure: Invalid IP version"};

    m_ue->fdBase->sendTo(version == 4 ? FdBase::RLS_IP4 : FdBase::RLS_IP6, m_sendBuffer.get(), static_cast<size_t>(n),
                         address);
}

void RlsUdpLayer::send(int cellId, const rls::RlsMessage &msg)
{
    if (m_cellIdToSti.count(cellId))
    {
        auto sti = m_cellIdToSti[cellId];
        sendRlsPdu(m_cells[sti].address, msg);
    }
}

void RlsUdpLayer::receiveRlsPdu(const InetAddress &addr, uint8_t *buffer, size_t size)
{
    auto msg = rls::DecodeRlsMessage(OctetView{buffer, size});
    if (msg == nullptr)
    {
        m_logger->err("Unable to decode RLS message");
        return;
    }

    if (msg->msgType == rls::EMessageType::HEARTBEAT_ACK)
    {
        if (!m_cells.count(msg->sti))
        {
            m_cells[msg->sti].cellId = ++m_cellIdCounter;
            m_cellIdToSti[m_cells[msg->sti].cellId] = msg->sti;
        }

        int oldDbm = INT32_MIN;
        if (m_cells.count(msg->sti))
            oldDbm = m_cells[msg->sti].dbm;

        m_cells[msg->sti].address = addr;
        m_cells[msg->sti].lastSeen = utils::CurrentTimeMillis();

        int newDbm = ((const rls::RlsHeartBeatAck &)*msg).dbm;
        m_cells[msg->sti].dbm = newDbm;

        if (oldDbm != newDbm)
            onSignalChangeOrLost(m_cells[msg->sti].cellId);
        return;
    }

    if (!m_cells.count(msg->sti))
    {
        // if no HB-ACK received yet, and the message is not HB-ACK, then ignore the message
        return;
    }

    m_ue->rlsCtl->handleRlsMessage(m_cells[msg->sti].cellId, *msg);
}

void RlsUdpLayer::onSignalChangeOrLost(int cellId)
{
    int dbm = INT32_MIN;
    if (m_cellIdToSti.count(cellId))
    {
        auto sti = m_cellIdToSti[cellId];
        dbm = m_cells[sti].dbm;
    }

    m_ue->rrc->handleCellSignalChange(cellId, dbm);
}

void RlsUdpLayer::heartbeatCycle(uint64_t time, const Vector3 &simPos)
{
    std::vector<int> toRemove;

    for (auto &cell : m_cells)
    {
        auto delta = time - cell.second.lastSeen;
        if (delta > HEARTBEAT_THRESHOLD)
            toRemove.push_back(cell.second.cellId);
    }

    for (auto cell : toRemove)
    {
        m_cells.erase(m_cellIdToSti[cell]);
        m_cellIdToSti.erase(cell);
    }

    for (auto cell : toRemove)
        onSignalChangeOrLost(cell);

    for (auto &address : m_searchSpace)
    {
        rls::RlsHeartBeat msg{m_ue->shCtx.sti};
        msg.simPos = simPos;
        sendRlsPdu(address, msg);
    }
}

} // namespace nr::ue
