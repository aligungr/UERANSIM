//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <thread>
#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <unordered_map>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::ue
{

class TunTask : public NtsTask
{
  private:
    TaskBase *m_base;
    int m_psi;
    int m_fd;
    ScopedThread *m_receiver;

    friend class UeCmdHandler;

  public:
    explicit TunTask(TaskBase *taskBase, int psi, int fd);
    ~TunTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::ue
