#pragma once

#include <cstdint>
#include <unordered_map>
#include <vector>

#include <lib/rls/rls_pdu.hpp>
#include <lib/udp/server.hpp>
#include <lib/udp/server_task.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class RlsUdpLayer
{
  private:
    struct CellInfo
    {
        InetAddress address;
        int64_t lastSeen{};
        int dbm{};
        int cellId{};
    };

  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    std::unique_ptr<udp::UdpServerTask> m_server;
    std::vector<InetAddress> m_searchSpace;
    std::unordered_map<uint64_t, CellInfo> m_cells;
    std::unordered_map<int, uint64_t> m_cellIdToSti;
    int64_t m_lastLoop;
    Vector3 m_simPos;
    int m_cellIdCounter;

    friend class UeCmdHandler;

  public:
    explicit RlsUdpLayer(TaskBase *base);
    ~RlsUdpLayer() = default;

  private:
    void sendRlsPdu(const InetAddress &address, const rls::RlsMessage &msg);
    void onSignalChangeOrLost(int cellId);
    void heartbeatCycle(uint64_t time, const Vector3 &simPos);

  public:
    void onStart();
    void onQuit();
    void checkHeartbeat();
    void send(int cellId, const rls::RlsMessage &msg);
    void receiveRlsPdu(const InetAddress &address, std::unique_ptr<rls::RlsMessage> &&msg);
};

} // namespace nr::ue
