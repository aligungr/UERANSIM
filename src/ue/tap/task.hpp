//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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

class TapTask : public NtsTask
{
  private:
    TaskBase *m_base;
    int m_psi;
    int m_fd;
    ScopedThread *m_receiver;

    friend class UeCmdHandler;

  public:
    explicit TapTask(TaskBase *taskBase, int psi, int fd);
    ~TapTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::ue
