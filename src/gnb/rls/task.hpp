//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <gnb/nts.hpp>
#include <gnb/types.hpp>
#include <memory>
#include <thread>
#include <udp/server_task.hpp>
#include <unordered_map>
#include <urs/rls_pdu.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::gnb
{

class GnbRlsTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    udp::UdpServerTask *m_udpTask;

    bool m_powerOn;
    uint64_t m_sti;
    std::unordered_map<int, std::unique_ptr<RlsUeContext>> m_ueCtx;
    std::unordered_map<uint64_t, int> m_stiToUeId;
    int m_ueIdCounter;

    friend class GnbCmdHandler;

  public:
    explicit GnbRlsTask(TaskBase *base);
    ~GnbRlsTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private: /* Transport */
    void receiveRlsMessage(const InetAddress &addr, rls::RlsMessage &msg);
    void sendRlsMessage(int ueId, const rls::RlsMessage &msg);

  private: /* Handler */
    void handleCellInfoRequest(int ueId, const rls::RlsCellInfoRequest &msg);
    void handleUplinkPduDelivery(int ueId, rls::RlsPduDelivery &msg);
    void handleDownlinkDelivery(int ueId, rls::EPduType pduType, OctetString &&pdu, OctetString &&payload);

  private: /* UE Management */
    int updateUeInfo(const InetAddress &addr, uint64_t sti);
    void onPeriodicLostControl();
};

} // namespace nr::gnb