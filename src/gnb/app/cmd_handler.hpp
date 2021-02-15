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

namespace nr::gnb
{

class GnbCmdHandler
{
  private:
    static void PauseTasks(TaskBase &base);
    static void UnpauseTasks(TaskBase &base);
    static bool IsAllPaused(TaskBase &base);

  public:
    static void HandleCmd(TaskBase &base, NwGnbCliCommand &msg);

  private:
    static void HandleCmdImpl(TaskBase &base, NwGnbCliCommand &msg);
};

} // namespace nr::gnb
