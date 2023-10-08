//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

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

class RlsControlTask : public NtsTask
{
  private:
    std::unique_ptr<Logger> m_logger;
    RlsSharedContext *m_shCtx;
    int m_servingCell;
    NtsTask *m_mainTask;
    RlsUdpTask *m_udpTask;
    std::unordered_map<uint32_t, rls::PduInfo> m_pduMap;
    std::unordered_map<int, std::vector<uint32_t>> m_pendingAck;

  public:
    explicit RlsControlTask(TaskBase *base, RlsSharedContext *shCtx);
    ~RlsControlTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  public:
    void initialize(NtsTask *mainTask, RlsUdpTask *udpTask);

  private:
    void handleRlsMessage(int cellId, rls::RlsMessage &msg);
    void handleSignalChange(int cellId, int dbm);
    void handleUplinkRrcDelivery(int cellId, uint32_t pduId, rrc::RrcChannel channel, OctetString &&data);
    void handleUplinkDataDelivery(int psi, OctetString &&data);
    void onAckControlTimerExpired();
    void onAckSendTimerExpired();
};

} // namespace nr::ue