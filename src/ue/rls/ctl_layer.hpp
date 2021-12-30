#pragma once

#include "udp_task.hpp"

#include <unordered_map>
#include <vector>

#include <lib/rrc/rrc.hpp>
#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class RlsCtlLayer
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    RlsSharedContext *m_shCtx;
    int m_servingCell;
    RlsUdpTask *m_udpTask;
    std::unordered_map<uint32_t, rls::PduInfo> m_pduMap;
    std::unordered_map<int, std::vector<uint32_t>> m_pendingAck;

  public:
    explicit RlsCtlLayer(TaskBase *base, RlsSharedContext *shCtx);
    ~RlsCtlLayer() = default;

  public:
    void onStart(RlsUdpTask *udpTask);
    void onQuit();
    void onAckControlTimerExpired();
    void onAckSendTimerExpired();
    void handleUplinkDataDelivery(int psi, OctetString &&data);
    void handleUplinkRrcDelivery(int cellId, uint32_t pduId, rrc::RrcChannel channel, OctetString &&data);
    void assignCurrentCell(int cellId);
    void handleRlsMessage(int cellId, rls::RlsMessage &msg);
    void declareRadioLinkFailure(rls::ERlfCause cause);
};

} // namespace nr::ue