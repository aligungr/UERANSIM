//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "ctl_layer.hpp"
#include "udp_layer.hpp"

#include <memory>
#include <optional>
#include <thread>
#include <unordered_map>
#include <vector>

#include <lib/rls/rls_pdu.hpp>
#include <lib/rrc/rrc.hpp>
#include <lib/udp/server_task.hpp>
#include <ue/types.hpp>
#include <utils/common_types.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class UeRlsTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    std::unique_ptr<RlsUdpLayer> m_udpLayer;
    std::unique_ptr<RlsCtlLayer> m_ctlLayer;

    friend class UeCmdHandler;

  public:
    explicit UeRlsTask(TaskBase *base);
    ~UeRlsTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  public:
    RlsCtlLayer& ctl();
    RlsUdpLayer& udp();
};

} // namespace nr::ue