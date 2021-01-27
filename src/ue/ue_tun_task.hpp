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
#include <unordered_map>
#include <vector>

#include "ue_nts.hpp"
#include "ue_types.hpp"

namespace nr::ue
{

class TunTask : public NtsTask
{
  private:
    TaskBase *base;
    int psi;
    int fd;
    ScopedThread *receiver;

  public:
    explicit TunTask(TaskBase *taskBase, int psi, int fd);
    ~TunTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::ue
