//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "ctl_layer.hpp"

namespace nr::ue
{

class RlsControlTask : public NtsTask
{
  private:
    std::unique_ptr<RlsCtlLayer> layer;
    NtsTask *mainTask;
    RlsUdpTask *udpTask;

  public:
    explicit RlsControlTask(TaskBase *base, RlsSharedContext *shCtx);
    ~RlsControlTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  public:
    void initialize(NtsTask *mainTask, RlsUdpTask *udpTask);
};

} // namespace nr::ue