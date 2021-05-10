//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <unordered_map>
#include <vector>

#include <lib/rls/rls_pdu.hpp>
#include <lib/udp/server.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class RlsUdpTask : public NtsTask
{
  private:
    struct CellInfo
    {
        InetAddress address;
        int64_t lastSeen{};
        int dbm{};
    };

  private:
    std::unique_ptr<Logger> m_logger;
    NtsTask *m_ctlTask;
    udp::UdpServer *m_server;
    uint64_t m_sti;
    std::vector<InetAddress> m_searchSpace;
    std::unordered_map<uint64_t, CellInfo> m_cells;
    int64_t m_lastLoop;
    Vector3 m_simPos;

  public:
    explicit RlsUdpTask(TaskBase *base, uint64_t sti, const std::vector<std::string> &searchSpace);
    ~RlsUdpTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    void sendRlsPdu(const InetAddress &addr, const rls::RlsMessage &msg);
    void receiveRlsPdu(const InetAddress &addr, std::unique_ptr<rls::RlsMessage> &&msg);
    void onSignalChangeOrLost(uint64_t sti);
    void heartbeatCycle(uint64_t time, const Vector3 &simPos);

  public:
    void initialize(NtsTask *ctlTask);
    void send(uint64_t sti, const rls::RlsMessage &msg);
};

} // namespace nr::ue
