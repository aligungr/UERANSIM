//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <optional>
#include <rrc/rrc.hpp>
#include <thread>
#include <udp/server_task.hpp>
#include <ue/types.hpp>
#include <unordered_map>
#include <urs/sra_pdu.hpp>
#include <utils/common_types.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::ue
{

class UeSraTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    udp::UdpServerTask *m_udpTask;

    std::vector<InetAddress> m_cellSearchSpace;
    std::unordered_map<GlobalNci, UeCellMeasurement> m_pendingMeasurements;
    std::unordered_map<GlobalNci, UeCellMeasurement> m_activeMeasurements;

    uint64_t m_sti;
    std::optional<UeCellInfo> m_servingCell;

    friend class UeCmdHandler;

  public:
    explicit UeSraTask(TaskBase *base);
    ~UeSraTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private: /* Transport */
    void receiveSraMessage(const InetAddress &address, const sra::SraMessage &msg);
    void sendSraMessage(const InetAddress &address, const sra::SraMessage &msg);
    void deliverUplinkPdu(sra::EPduType pduType, OctetString &&pdu, OctetString &&payload);
    void deliverUplinkRrc(rrc::RrcChannel channel, OctetString &&pdu);

  private: /* Measurement */
    void onMeasurement();
    void receiveCellInfoResponse(const sra::SraCellInfoResponse &msg);
    void onCoverageChange(const std::vector<GlobalNci> &entered, const std::vector<GlobalNci> &exited);
    void plmnSearchRequested();
    void handleCellSelectionCommand(const GlobalNci &cellId, bool isSuitable);
};

} // namespace nr::ue