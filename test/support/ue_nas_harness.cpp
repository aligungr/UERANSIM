//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "ue_nas_harness.hpp"

#include <algorithm>
#include <chrono>
#include <stdexcept>
#include <unordered_set>

#include <lib/nas/encode.hpp>
#include <test/support/nts_driver.hpp>
#include <utils/octet_view.hpp>

using namespace nr::ue;

UeConfig UeNasHarness::DefaultConfig()
{
    UeConfig config{};
    config.supi = Supi::Parse("imsi-286010000000001");
    config.hplmn.mcc = 286;
    config.hplmn.mnc = 1;
    config.protectionScheme = 0;
    config.homeNetworkPublicKeyId = 1;
    config.routingIndicator = "0000";
    config.key = OctetString::FromHex("465B5CE8B199B49FAA5F0A2EE238A6BC");
    config.opC = OctetString::FromHex("E8ED289DEBA952E4283B54E88E6183CA");
    config.opType = OpType::OP;
    config.amf = OctetString::FromHex("8000");
    config.imei = "356938035643803";
    config.imeiSv = "4370816125816151";
    config.supportedAlgs.nia1 = config.supportedAlgs.nia2 = config.supportedAlgs.nia3 = true;
    config.supportedAlgs.nea1 = config.supportedAlgs.nea2 = config.supportedAlgs.nea3 = true;
    config.integrityMaxRate.uplinkFull = config.integrityMaxRate.downlinkFull = true;

    SingleSlice slice{};
    slice.sst = 1;
    slice.sd = octet3{1};
    config.configuredNssai.slices.push_back(slice);
    config.defaultConfiguredNssai.slices.push_back(slice);

    // Without the PDU session of the file, so that NAS asks for none unless a test gives it one.
    return config;
}

UeNasHarness::UeNasHarness(UeConfig config) : m_logBase{""}, m_config{std::move(config)}, m_base{}
{
    m_base.config = &m_config;
    m_base.logBase = &m_logBase;
    m_base.nodeListener = this;

    m_appTask = std::make_unique<UeAppTask>(&m_base);
    m_rrcTask = std::make_unique<UeRrcTask>(&m_base);
    m_nasTask = std::make_unique<NasTask>(&m_base);
    m_base.appTask = m_appTask.get();
    m_base.rrcTask = m_rrcTask.get();
    m_base.nasTask = m_nasTask.get();

    // NasMm starts in these, without reporting them.
    m_mmState = ToJson(EMmState::MM_DEREGISTERED).str();
    m_mmSubState = ToJson(EMmSubState::MM_DEREGISTERED_PS).str();
    m_cmState = ToJson(ECmState::CM_IDLE).str();

    m_uacThread = std::thread{[this]() { answerUac(); }};

    NtsTaskDriver::start(*m_nasTask);
    runNas();
}

UeNasHarness::~UeNasHarness()
{
    m_stopping = true;
    m_uacThread.join();

    NtsTaskDriver::quit(*m_nasTask);
}

void UeNasHarness::selectCell(ECellCategory category, int tac)
{
    // What the RRC does on selecting a cell (UeRrcTask::performCellSelection()), with the home PLMN in coverage.
    auto previous = m_base.shCtx.currentCell.get();

    ActiveCellInfo cell{};
    cell.cellId = previous.cellId + 1;
    cell.category = category;
    cell.plmn = m_config.hplmn;
    cell.tac = tac;

    m_base.shCtx.availablePlmns.set(std::unordered_set<Plmn>{m_config.hplmn});
    m_base.shCtx.currentCell.set(cell);

    auto msg = std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::ACTIVE_CELL_CHANGED);
    msg->previousTai = Tai{previous.plmn, previous.tac};
    deliver(std::move(msg));
}

void UeNasHarness::connectionSetup()
{
    deliver(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::RRC_CONNECTION_SETUP));
}

void UeNasHarness::connectionRelease()
{
    deliver(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::RRC_CONNECTION_RELEASE));
}

void UeNasHarness::receive(const nas::NasMessage &msg)
{
    OctetString pdu;
    nas::EncodeNasMessage(msg, pdu);
    receive(pdu);
}

void UeNasHarness::receive(const OctetString &nasPdu)
{
    auto msg = std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::NAS_DELIVERY);
    msg->nasPdu = nasPdu.copy();
    deliver(std::move(msg));
}

void UeNasHarness::advance(int64_t ms)
{
    // From one timer of the NAS task to the next, so that each falls due when, and in the order, it would. A timer is
    // due once the time is past its end, hence the extra millisecond.
    int64_t end = m_clock.now() + ms;
    while (m_clock.now() < end)
    {
        m_clock.advance(std::min(NtsTaskDriver::nextTimerIn(*m_nasTask) + 1, end - m_clock.now()));
        runNas();
    }
}

std::vector<std::unique_ptr<NmUeNasToRrc>> UeNasHarness::takeToRrc()
{
    std::lock_guard<std::mutex> lock{m_rrcMutex};
    collectForRrc();

    auto taken = std::move(m_toRrc);
    m_toRrc.clear();
    return taken;
}

std::vector<std::unique_ptr<nas::NasMessage>> UeNasHarness::takeUplinkNas()
{
    std::vector<std::unique_ptr<nas::NasMessage>> uplink;
    for (auto &msg : takeToRrc())
    {
        if (msg->present == NmUeNasToRrc::UPLINK_NAS_DELIVERY)
            uplink.push_back(nas::DecodeNasMessage(OctetView{msg->nasPdu}));
    }
    return uplink;
}

void UeNasHarness::setUacResult(EUacResult result)
{
    m_uacResult = result;
}

const std::string &UeNasHarness::mmState() const
{
    return m_mmState;
}

const std::string &UeNasHarness::mmSubState() const
{
    return m_mmSubState;
}

const std::string &UeNasHarness::cmState() const
{
    return m_cmState;
}

void UeNasHarness::deliver(std::unique_ptr<NmUeRrcToNas> msg)
{
    m_nasTask->push(std::move(msg));
    runNas();
}

void UeNasHarness::runNas()
{
    // NAS keeps sending itself messages (MM cycles, timer expiries); this many in a row means it never settles.
    static constexpr int LIMIT = 10'000;

    for (int i = 0; NtsTaskDriver::runOnce(*m_nasTask); i++)
    {
        if (i == LIMIT)
            throw std::runtime_error("NAS did not settle after " + std::to_string(LIMIT) + " messages");
    }
}

// Called with m_rrcMutex held.
void UeNasHarness::collectForRrc()
{
    while (auto msg = NtsTaskDriver::take(*m_rrcTask))
    {
        // Nothing but NAS sends the RRC task of the harness anything.
        if (msg->msgType != NtsMessageType::UE_NAS_TO_RRC)
            continue;

        std::unique_ptr<NmUeNasToRrc> w{static_cast<NmUeNasToRrc *>(msg.release())};
        if (w->present == NmUeNasToRrc::PERFORM_UAC && !w->uacCtl->isExpiredForProducer())
        {
            auto output = std::make_unique<UacOutput>();
            output->res = m_uacResult;
            w->uacCtl->notifyProcessed(std::move(output));
        }
        m_toRrc.push_back(std::move(w));
    }
}

void UeNasHarness::answerUac()
{
    while (!m_stopping)
    {
        {
            std::lock_guard<std::mutex> lock{m_rrcMutex};
            collectForRrc();
        }
        std::this_thread::sleep_for(std::chrono::microseconds(100));
    }
}

void UeNasHarness::onSwitch(app::NodeType, const std::string &, app::StateType stateType, const std::string &,
                            const std::string &toState)
{
    if (stateType == app::StateType::MM)
        m_mmState = toState;
    else if (stateType == app::StateType::MM_SUB)
        m_mmSubState = toState;
    else if (stateType == app::StateType::CM)
        m_cmState = toState;
}
