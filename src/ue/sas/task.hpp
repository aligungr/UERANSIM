//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <thread>
#include <udp/server_task.hpp>
#include <ue/types.hpp>
#include <unordered_map>
#include <urs/sas_pdu.hpp>
#include <utils/common_types.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::ue
{

class UeSasTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    udp::UdpServerTask *m_udpTask;

    std::vector<InetAddress> m_cellSearchSpace;
    std::unordered_map<GlobalNci, UeCellMeasurement> m_pendingMeasurements;
    std::unordered_map<GlobalNci, UeCellMeasurement> m_activeMeasurements;

    friend class UeCmdHandler;

  public:
    explicit UeSasTask(TaskBase *base);
    ~UeSasTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private: /* Transport */
    void receiveSasMessage(const InetAddress &address, const sas::SasMessage &msg);
    void sendSasMessage(const InetAddress &address, const sas::SasMessage &msg);

  private: /* Measurement */
    void onMeasurement();
    void receiveCellInfoResponse(const sas::SasCellInfoResponse &msg);
    void onCoverageChange(const std::vector<GlobalNci> &entered, const std::vector<GlobalNci> &exited);
    void plmnSearchRequested();

  private: /* Connection */
    void handleCellSelectionCommand(const GlobalNci &cellId, bool isSuitable);
};

} // namespace nr::ue