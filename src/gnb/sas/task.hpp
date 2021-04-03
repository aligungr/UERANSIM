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
#include <urs/rls/gnb_entity.hpp>
#include <urs/sas_pdu.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::gnb
{

class GnbSasTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    udp::UdpServerTask *m_udpTask;

    friend class GnbCmdHandler;

  public:
    explicit GnbSasTask(TaskBase *base);
    ~GnbSasTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private: /* Transport */
    void receiveSasMessage(const InetAddress &addr, const sas::SasMessage &msg);
    void sendSasMessage(const InetAddress &addr, const sas::SasMessage &msg);

  private: /* Handler */
    void handleCellInfoRequest(const InetAddress &addr, const sas::SasCellInfoRequest &msg);
};

} // namespace nr::gnb