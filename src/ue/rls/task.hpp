//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <lib/rrc/rrc.hpp>
#include <lib/udp/server_task.hpp>
#include <lib/rls/rls_pdu.hpp>
#include <memory>
#include <optional>
#include <thread>
#include <ue/types.hpp>
#include <unordered_map>
#include <utils/common_types.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::ue
{

class UeRlsTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    udp::UdpServerTask *m_udpTask;

    std::vector<InetAddress> m_cellSearchSpace;
    std::unordered_map<GlobalNci, UeCellMeasurement> m_pendingMeasurements;
    std::unordered_map<GlobalNci, UeCellMeasurement> m_activeMeasurements;
    bool m_pendingPlmnResponse;
    int64_t m_measurementPeriod;

    uint64_t m_sti;
    std::optional<UeCellInfo> m_servingCell;

    friend class UeCmdHandler;

  public:
    explicit UeRlsTask(TaskBase *base);
    ~UeRlsTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private: /* Base */
    void slowDownMeasurements();

  private: /* Transport */
    void receiveRlsMessage(const InetAddress &address, rls::RlsMessage &msg);
    void sendRlsMessage(const InetAddress &address, const rls::RlsMessage &msg);
    void deliverUplinkPdu(rls::EPduType pduType, OctetString &&pdu, OctetString &&payload);
    void deliverDownlinkPdu(rls::RlsPduDelivery &msg);

  private: /* Measurement */
    void onMeasurement();
    void receiveCellInfoResponse(const rls::RlsCellInfoResponse &msg);
    void onCoverageChange(const std::vector<GlobalNci> &entered, const std::vector<GlobalNci> &exited);
    void plmnSearchRequested();
    void handleCellSelectionCommand(const GlobalNci &cellId, bool isSuitable);
};

} // namespace nr::ue