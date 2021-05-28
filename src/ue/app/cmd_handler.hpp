//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <ue/nts.hpp>
#include <ue/types.hpp>

namespace nr::ue
{

class UeCmdHandler
{
  private:
    TaskBase *m_base;

  public:
    explicit UeCmdHandler(TaskBase *base) : m_base(base)
    {
    }

    void handleCmd(NmUeCliCommand &msg);

  private:
    void pauseTasks();
    void unpauseTasks();
    bool isAllPaused();

  private:
    void handleCmdImpl(NmUeCliCommand &msg);

  private:
    void sendResult(const InetAddress &address, const std::string &output);
    void sendError(const InetAddress &address, const std::string &output);
};

} // namespace nr::ue
