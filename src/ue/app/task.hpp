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
#include <ue/tun/task.hpp>
#include <ue/types.hpp>
#include <unordered_map>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::ue
{

class UeAppTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    std::array<TunTask *, 16> m_tunTasks{};
    ECmState m_cmState{};

    friend class UeCmdHandler;

  public:
    explicit UeAppTask(TaskBase *base);
    ~UeAppTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    void receiveStatusUpdate(NmUeStatusUpdate &msg);
    void setupTunInterface(const PduSession *pduSession);
};

} // namespace nr::ue
