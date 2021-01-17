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

#include "gnb_config.hpp"

namespace nr::gnb
{

class GtpTask : public NtsTask
{
  private:
    GnbConfig *config;
    std::unique_ptr<logger::Logger> logger;
    nr::udp::UdpServerTask *udpServer;

  public:
    explicit GtpTask(GnbConfig *config, logger::LogBase &loggerBase);
    ~GtpTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::gnb
