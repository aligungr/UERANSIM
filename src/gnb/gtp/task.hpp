//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "utils.hpp"

#include <memory>
#include <thread>
#include <unordered_map>
#include <vector>

#include <gnb/nts.hpp>
#include <lib/udp/server_task.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>

namespace nr::gnb
{

class GtpTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    udp::UdpServerTask *m_udpServer;
    std::unordered_map<int, std::unique_ptr<GtpUeContext>> m_ueContexts;
    std::unique_ptr<IRateLimiter> m_rateLimiter;
    std::unordered_map<uint64_t, std::unique_ptr<PduSessionResource>> m_pduSessions;
    PduSessionTree m_sessionTree;

    friend class GnbCmdHandler;

  public:
    explicit GtpTask(TaskBase *base);
    ~GtpTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    void handleUdpReceive(const udp::NwUdpServerReceive &msg);
    void handleUeContextUpdate(const GtpUeContextUpdate &msg);
    void handleSessionCreate(PduSessionResource *session);
    void handleSessionRelease(int ueId, int psi);
    void handleUeContextDelete(int ueId);
    void handleUplinkData(int ueId, int psi, OctetString &&data);

    void updateAmbrForUe(int ueId);
    void updateAmbrForSession(uint64_t pduSession);
};

} // namespace nr::gnb
