//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "nts.hpp"
#include "types.hpp"

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
    void handleCmd(NmUeCliCommand &msg);

  private:
    void sendResult(const InetAddress &address, const std::string &output);
    void sendError(const InetAddress &address, const std::string &output);
};

} // namespace nr::ue
