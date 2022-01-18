#include "ctl_layer.hpp"

#include <ue/task.hpp>

static constexpr const size_t MAX_PDU_COUNT = 128;
static constexpr const int MAX_PDU_TTL = 3000;

namespace nr::ue
{

RlsCtlLayer::RlsCtlLayer(UeTask *ue) : m_ue{ue}, m_servingCell{}, m_pduMap{}, m_pendingAck{}, m_cBuffer{8192}
{
    m_logger = ue->logBase->makeUniqueLogger(ue->config->getLoggerPrefix() + "rls-ctl");
}

void RlsCtlLayer::handleRlsMessage(int cellId, rls::EMessageType msgType, uint8_t *buffer, size_t size)
{
    if (msgType == rls::EMessageType::PDU_TRANSMISSION_ACK)
    {
        OctetView view = rls::DecodePduTransmissionAck(buffer, size);
        while (view.hasNext())
            m_pduMap.erase(view.read4UI());
    }
    else if (msgType == rls::EMessageType::PDU_TRANSMISSION)
    {
        rls::EPduType pduType;
        uint32_t pduId;
        uint32_t payload;
        const uint8_t *pduData;
        size_t pduLength;

        rls::DecodePduTransmission(buffer, size, pduType, pduId, payload, pduData, pduLength);

        if (pduId != 0)
            m_pendingAck[cellId].push_back(pduId);

        if (pduType == rls::EPduType::DATA)
        {
            if (cellId != m_servingCell)
            {
                // NOTE: Data packet may be received from a cell other than serving cell
                //  Ignore the packet if this is the case. Other cell can only send RRC, but not DATA
                return;
            }

            m_ue->nas->handleDownlinkDataRequest(static_cast<int>(payload), pduData, pduLength);
        }
        else if (pduType == rls::EPduType::RRC)
            m_ue->rrc->handleDownlinkRrc(cellId, static_cast<rrc::RrcChannel>(payload), pduData, pduLength);
        else
            m_logger->err("Unhandled RLS PDU type");
    }
    else
    {
        m_logger->err("Unhandled RLS message type");
    }
}

void RlsCtlLayer::handleUplinkRrcDelivery(int cellId, uint32_t pduId, rrc::RrcChannel channel, OctetString &&data)
{
    if (pduId != 0)
    {
        if (m_pduMap.count(pduId))
        {
            m_pduMap.clear();
            declareRadioLinkFailure(rls::ERlfCause::PDU_ID_EXISTS);
            return;
        }

        if (m_pduMap.size() > MAX_PDU_COUNT)
        {
            m_pduMap.clear();
            declareRadioLinkFailure(rls::ERlfCause::PDU_ID_FULL);
            return;
        }

        m_pduMap[pduId].endPointId = cellId;
        m_pduMap[pduId].id = pduId;
        m_pduMap[pduId].rrcChannel = channel;
        m_pduMap[pduId].sentTime = utils::CurrentTimeMillis();
    }

    m_cBuffer.reset();
    m_cBuffer.setCmSize(static_cast<size_t>(data.length()));
    std::memcpy(m_cBuffer.cmAddress(), data.data(), m_cBuffer.cmSize());
    rls::EncodePduTransmission(m_cBuffer, m_ue->shCtx.sti, rls::EPduType::RRC, static_cast<uint32_t>(channel), pduId);
    m_ue->rlsUdp->send(cellId, m_cBuffer);
}

void RlsCtlLayer::handleUplinkDataDelivery(int psi, CompoundBuffer &buffer)
{
    rls::EncodePduTransmission(buffer, m_ue->shCtx.sti, rls::EPduType::DATA, static_cast<uint32_t>(psi), 0);
    m_ue->rlsUdp->send(m_servingCell, buffer);
}

void RlsCtlLayer::onAckControlTimerExpired()
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
            transmissionFailures.push_back(pdu.second);
        }
    }

    for (auto id : transmissionFailureIds)
        m_pduMap.erase(id);

    if (!transmissionFailures.empty())
        m_logger->err("transmission failure [%d]", transmissionFailures.size());
}

void RlsCtlLayer::onAckSendTimerExpired()
{
    for (auto &item : m_pendingAck)
    {
        if (!item.second.empty())
            continue;

        rls::EncodePduTransmissionAck(m_cBuffer, m_ue->shCtx.sti, item.second);
        m_ue->rlsUdp->send(item.first, m_cBuffer);
    }
    m_pendingAck.clear();
}

void RlsCtlLayer::assignCurrentCell(int cellId)
{
    m_servingCell = cellId;
}

void RlsCtlLayer::declareRadioLinkFailure(rls::ERlfCause cause)
{
    m_ue->rrc->handleRadioLinkFailure(cause);
}

} // namespace nr::ue