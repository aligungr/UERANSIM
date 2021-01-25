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
#include <vector>

#include "ue_mr_rls.hpp"
#include "ue_types.hpp"

namespace nr::ue
{

class UeMrTask : public NtsTask
{
  private:
    TaskBase *base;
    std::unique_ptr<Logger> logger;

    udp::UdpServerTask *udpTask;
    UeRls *rlsEntity;

    long lastPlmnSearchFailurePrinted;

  public:
    explicit UeMrTask(TaskBase *base);
    ~UeMrTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    void receiveDownlinkPayload(rls::EPayloadType type, OctetString &&payload);
};

} // namespace nr::ue