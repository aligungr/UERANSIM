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
#include <unordered_map>
#include <vector>

#include <gnb/types.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>

namespace nr::gnb
{

class GnbAppTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    GnbStatusInfo m_statusInfo;

    friend class GnbCmdHandler;

  public:
    explicit GnbAppTask(TaskBase *base);
    ~GnbAppTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::gnb
