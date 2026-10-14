//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <atomic>
#include <cstdint>
#include <memory>
#include <mutex>
#include <string>
#include <thread>
#include <vector>

#include <lib/app/monitor.hpp>
#include <lib/nas/msg.hpp>
#include <test/support/fake_clock.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/task.hpp>
#include <ue/nts.hpp>
#include <ue/rrc/task.hpp>
#include <ue/types.hpp>
#include <utils/logger.hpp>
#include <utils/octet_string.hpp>

// The NAS task of one UE, switched on and run on the test's thread, with time on a FakeClock. The RRC and app tasks of
// the UE never run: what NAS sends them waits for the test, which plays the RRC and the network.
//
// The exception is the UAC check, whose answer NAS waits for in the middle of a procedure; a thread of the harness
// gives it the way the RRC would.
class UeNasHarness : private app::INodeListener
{
  public:
    // The UE of config/custom-ue.yaml.
    static nr::ue::UeConfig DefaultConfig();

    explicit UeNasHarness(nr::ue::UeConfig config = DefaultConfig());
    ~UeNasHarness();

    UeNasHarness(const UeNasHarness &) = delete;
    UeNasHarness &operator=(const UeNasHarness &) = delete;

    /* What the RRC tells NAS. Each of these lets NAS handle everything it leads to before returning. */

    // A cell of the home PLMN became the UE's serving cell.
    void selectCell(ECellCategory category = ECellCategory::SUITABLE_CELL, int tac = 1);
    void connectionSetup();
    void connectionRelease();
    // A downlink NAS message arrived.
    void receive(const nas::NasMessage &msg);
    void receive(const OctetString &nasPdu);

    // Moves time on by ms, and lets NAS handle what falls due on the way.
    void advance(int64_t ms);

    // What NAS has sent the RRC since the last call, oldest first. UAC checks are among them, already answered.
    std::vector<std::unique_ptr<nr::ue::NmUeNasToRrc>> takeToRrc();
    // The uplink NAS messages among what NAS has sent the RRC since the last call, decoded.
    std::vector<std::unique_ptr<nas::NasMessage>> takeUplinkNas();

    // What the UAC checks of NAS are answered from now on.
    void setUacResult(nr::ue::EUacResult result);

    // The states NAS reported last, as ToJson() writes them.
    [[nodiscard]] const std::string &mmState() const;
    [[nodiscard]] const std::string &mmSubState() const;
    [[nodiscard]] const std::string &cmState() const;

  private:
    void deliver(std::unique_ptr<nr::ue::NmUeRrcToNas> msg);
    void runNas();
    void collectForRrc();
    void answerUac();

    void onConnected(app::NodeType, const std::string &, app::NodeType, const std::string &) override
    {
    }
    void onReceive(app::NodeType, const std::string &, app::NodeType, const std::string &, app::ConnectionType,
                   std::string) override
    {
    }
    void onSend(app::NodeType, const std::string &, app::NodeType, const std::string &, app::ConnectionType,
                std::string) override
    {
    }
    void onSwitch(app::NodeType, const std::string &, app::StateType stateType, const std::string &,
                  const std::string &toState) override;

  private:
    FakeClock m_clock;
    LogBase m_logBase;
    nr::ue::UeConfig m_config;
    nr::ue::TaskBase m_base;
    std::unique_ptr<nr::ue::UeAppTask> m_appTask;
    std::unique_ptr<nr::ue::UeRrcTask> m_rrcTask;
    std::unique_ptr<nr::ue::NasTask> m_nasTask;

    // Held while a message for the RRC is taken and handled, by the test's thread or by the UAC thread.
    std::mutex m_rrcMutex;
    std::vector<std::unique_ptr<nr::ue::NmUeNasToRrc>> m_toRrc;
    std::atomic<nr::ue::EUacResult> m_uacResult{nr::ue::EUacResult::ALLOWED};
    std::atomic_bool m_stopping{};
    std::thread m_uacThread;

    std::string m_mmState;
    std::string m_mmSubState;
    std::string m_cmState;
};
