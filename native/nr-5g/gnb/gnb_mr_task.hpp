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

#include "gnb_nts.hpp"

namespace nr::gnb
{

class GnbMrTask : public NtsTask
{
  private:
    TaskBase *base;
    std::unique_ptr<Logger> logger;

  public:
    explicit GnbMrTask(TaskBase *base);
    ~GnbMrTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::gnb