//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "rls.hpp"
#include <memory>
#include <thread>
#include <udp/server_task.hpp>
#include <ue/types.hpp>
#include <unordered_map>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

namespace nr::ue
{

class UeMrTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    udp::UdpServerTask *m_udpTask;
    UeRls *m_rlsEntity;

    long m_lastPlmnSearchFailurePrinted;

    friend class UeCmdHandler;

  public:
    explicit UeMrTask(TaskBase *base);
    ~UeMrTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    void receiveDownlinkPayload(rls::EPayloadType type, OctetString &&payload);
};

} // namespace nr::ue