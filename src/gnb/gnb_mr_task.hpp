//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <logger.hpp>
#include <memory>
#include <nts.hpp>
#include <thread>
#include <udp_server_task.hpp>
#include <unordered_map>
#include <urs_rls_gnb_entity.hpp>
#include <vector>

#include "gnb_mr_rls.hpp"
#include "gnb_nts.hpp"

namespace nr::gnb
{

class GnbMrTask : public NtsTask
{
  private:
    TaskBase *base;
    std::unique_ptr<Logger> logger;

    udp::UdpServerTask *udpTask;
    GnbRls *rlsEntity;

    std::unordered_map<int, MrUeContext> ueMap;

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