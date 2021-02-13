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

    UeStatusInfo m_statusInfo;
    TunTask *m_tunTasks[16];

    friend class UeCmdHandler;

  public:
    explicit UeAppTask(TaskBase *base);
    ~UeAppTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    void receiveStatusUpdate(NwUeStatusUpdate &msg);
    void setupTunInterface(const PduSession *pduSession);
};

} // namespace nr::ue
