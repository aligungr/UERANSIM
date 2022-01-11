//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "types.hpp"

#include <lib/app/cli_base.hpp>
#include <lib/app/cli_cmd.hpp>
#include <utils/network.hpp>

namespace nr::ue
{

class UeCmdHandler
{
  private:
    UeTask *m_ue;

  public:
    explicit UeCmdHandler(UeTask *ue) : m_ue(ue)
    {
    }

  public:
    void onStart();
    void receiveCmd(const InetAddress &address, const uint8_t *buffer, size_t size);

  private:
    void handleCmd(const InetAddress &address, std::unique_ptr<app::UeCliCommand> &&cmd);
    void sendMessage(const app::CliMessage &msg);
    void sendResult(const InetAddress &address, const std::string &output);
    void sendError(const InetAddress &address, const std::string &output);
};

} // namespace nr::ue
