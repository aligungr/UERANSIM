//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "udp_task.hpp"

#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class RlsControlTask : public NtsTask
{
  private:
    std::unique_ptr<Logger> m_logger;
    RlsUdpTask *m_udpTask;

  public:
    explicit RlsControlTask(TaskBase *base);
    ~RlsControlTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  public:
    void initialize(RlsUdpTask *udpTask);
};

} // namespace nr::ue