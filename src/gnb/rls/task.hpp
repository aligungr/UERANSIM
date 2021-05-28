//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "ctl_task.hpp"
#include "udp_task.hpp"

#include <memory>
#include <thread>
#include <unordered_map>
#include <vector>

#include <gnb/nts.hpp>
#include <gnb/types.hpp>
#include <lib/rls/rls_pdu.hpp>
#include <lib/udp/server_task.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>

namespace nr::gnb
{

class GnbRlsTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    RlsUdpTask *m_udpTask;
    RlsControlTask *m_ctlTask;

    uint64_t m_sti;

    friend class GnbCmdHandler;

  public:
    explicit GnbRlsTask(TaskBase *base);
    ~GnbRlsTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::gnb
