//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "layer.hpp"

namespace nr::ue
{

class UeRrcTask : public NtsTask
{
  private:
    std::unique_ptr<UeRrcLayer> layer;

    friend class UeCmdHandler;

  public:
    explicit UeRrcTask(TaskBase *base);
    ~UeRrcTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::ue
