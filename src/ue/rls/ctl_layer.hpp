//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <unordered_map>
#include <vector>

#include <lib/rls/rls_base.hpp>
#include <lib/rrc/rrc.hpp>
#include <ue/types.hpp>
#include <utils/compound_buffer.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class RlsCtlLayer
{
  private:
    UeTask *m_ue;
    std::unique_ptr<Logger> m_logger;
    int m_servingCell;
    std::unordered_map<uint32_t, rls::PduInfo> m_pduMap;
    std::unordered_map<int, std::vector<uint32_t>> m_pendingAck;
    CompoundBuffer m_cBuffer;

  public:
    explicit RlsCtlLayer(UeTask *ue);
    ~RlsCtlLayer() = default;

  private:
    void declareRadioLinkFailure(rls::ERlfCause cause);

  public:
    void onAckControlTimerExpired();
    void onAckSendTimerExpired();
    void handleRlsMessage(int cellId, rls::EMessageType msgType, uint8_t *buffer, size_t size);
    void assignCurrentCell(int cellId);
    void handleUplinkRrcDelivery(int cellId, uint32_t pduId, rrc::RrcChannel channel, OctetString &&data);
    void handleUplinkDataDelivery(int psi, CompoundBuffer &buffer);
};

} // namespace nr::ue