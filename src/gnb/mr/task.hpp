//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "rls.hpp"
#include <gnb/nts.hpp>
#include <gnb/types.hpp>
#include <memory>
#include <thread>
#include <udp/server_task.hpp>
#include <unordered_map>
#include <urs/rls/gnb_entity.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::gnb
{

class GnbMrTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    udp::UdpServerTask *m_udpTask;
    GnbRls *m_rlsEntity;
    std::unordered_map<int, MrUeContext> m_ueMap;

    friend class GnbCmdHandler;

  public:
    explicit GnbMrTask(TaskBase *base);
    ~GnbMrTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    void onUeConnected(int ue, const std::string &name);
    void onUeReleased(int ue, rls::ECause cause);
    void receiveUplinkPayload(int ue, rls::EPayloadType type, OctetString &&payload);
};

} // namespace nr::gnb