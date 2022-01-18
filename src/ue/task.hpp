//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "types.hpp"

#include <memory>
#include <optional>
#include <thread>
#include <unordered_map>
#include <vector>

#include <lib/rls/rls_pdu.hpp>
#include <lib/rrc/rrc.hpp>
#include <lib/udp/server_task.hpp>
#include <ue/nas/layer.hpp>
#include <ue/rls/ctl_layer.hpp>
#include <ue/rls/udp_layer.hpp>
#include <ue/rrc/layer.hpp>
#include <ue/tun/layer.hpp>
#include <utils/common_types.hpp>
#include <utils/compound_buffer.hpp>
#include <utils/fd_base.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class RlsUdpLayer;
class RlsCtlLayer;
class UeRrcLayer;
class NasLayer;
class TunLayer;
class UeCmdHandler;

class UeTask
{
  private:
    int64_t m_timerL3MachineCycle;
    int64_t m_timerL3Timer;
    int64_t m_timerRlsAckControl;
    int64_t m_timerRlsAckSend;
    int64_t m_timerSwitchOff;

  private:
    bool m_immediateCycle;
    std::unique_ptr<uint8_t[]> m_buffer;
    std::unique_ptr<UeCmdHandler> m_cmdHandler;
    CompoundBuffer m_cBuffer;

  public:
    std::unique_ptr<UeConfig> config;
    std::unique_ptr<LogBase> logBase;
    std::unique_ptr<FdBase> fdBase;
    UeSharedContext shCtx;

  public:
    std::unique_ptr<RlsUdpLayer> rlsUdp;
    std::unique_ptr<RlsCtlLayer> rlsCtl;
    std::unique_ptr<UeRrcLayer> rrc;
    std::unique_ptr<NasLayer> nas;
    std::unique_ptr<TunLayer> tun;

  public:
    explicit UeTask(std::unique_ptr<UeConfig> &&config);
    ~UeTask();

  public:
    void onStart();
    bool onLoop();
    void onQuit();

  public:
    void triggerCycle();
    void triggerSwitchOff();

  private:
    bool checkTimers();
};

} // namespace nr::ue