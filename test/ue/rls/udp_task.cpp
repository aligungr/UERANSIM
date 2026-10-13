//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstdint>
#include <memory>
#include <string>
#include <vector>

#include <ext/doctest/doctest.h>
#include <lib/rls/rls_pdu.hpp>
#include <ue/nts.hpp>
#include <ue/rls/udp_task.hpp>
#include <ue/types.hpp>
#include <utils/constants.hpp>
#include <utils/logger.hpp>
#include <utils/network.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>

using namespace nr::ue;

namespace
{

// Stands in for the RLS control task: it never runs, and the test takes what the UDP task hands over to it.
class ControlTaskStub : public NtsTask
{
  public:
    std::unique_ptr<NmUeRlsToRls> next()
    {
        auto msg = poll();
        if (!msg)
            return nullptr;
        REQUIRE_EQ(msg->msgType, NtsMessageType::UE_RLS_TO_RLS);
        return std::unique_ptr<NmUeRlsToRls>{static_cast<NmUeRlsToRls *>(msg.release())};
    }

  protected:
    void onStart() override
    {
    }

    void onLoop() override
    {
    }

    void onQuit() override
    {
    }
};

// The RLS UDP task of a UE with 127.0.0.1 in its gnbSearchList. The task is never started; the tests hand it what its
// socket would have received.
struct UdpTaskFixture
{
    const InetAddress gnbAddress{"127.0.0.1", cons::RadioLinkPort};
    const InetAddress otherAddress{"127.0.0.2", cons::RadioLinkPort};
    const uint64_t gnbSti = 0x1234;

    LogBase logBase{""};
    UeConfig config{};
    TaskBase base{};
    RlsSharedContext shCtx{};
    ControlTaskStub ctlTask{};
    std::unique_ptr<RlsUdpTask> udpTask{};

    UdpTaskFixture()
    {
        base.config = &config;
        base.logBase = &logBase;
        udpTask = std::make_unique<RlsUdpTask>(&base, &shCtx, std::vector<std::string>{"127.0.0.1"});
        udpTask->initialize(&ctlTask);
    }
};

std::unique_ptr<rls::RlsMessage> HeartBeatAck(uint64_t sti, int dbm)
{
    auto msg = std::make_unique<rls::RlsHeartBeatAck>(sti);
    msg->dbm = dbm;
    return msg;
}

std::unique_ptr<rls::RlsMessage> RrcPdu(uint64_t sti)
{
    auto msg = std::make_unique<rls::RlsPduTransmission>(sti);
    msg->pduType = rls::EPduType::RRC;
    msg->pduId = 1;
    msg->pdu = OctetString::FromHex("00");
    return msg;
}

} // namespace

TEST_CASE_FIXTURE(UdpTaskFixture, "ue/rls/udp_task: a heartbeat ack from a gNB in gnbSearchList makes it a cell")
{
    udpTask->receiveRlsPdu(gnbAddress, HeartBeatAck(gnbSti, -50));

    auto signal = ctlTask.next();
    REQUIRE(signal);
    CHECK_EQ(signal->present, NmUeRlsToRls::SIGNAL_CHANGED);
    CHECK_EQ(signal->dbm, -50);

    udpTask->receiveRlsPdu(gnbAddress, RrcPdu(gnbSti));

    auto received = ctlTask.next();
    REQUIRE(received);
    CHECK_EQ(received->present, NmUeRlsToRls::RECEIVE_RLS_MESSAGE);
    CHECK_EQ(received->cellId, signal->cellId);
}

TEST_CASE_FIXTURE(UdpTaskFixture, "ue/rls/udp_task: a heartbeat ack from anywhere else is ignored")
{
    // From another host, and from the gNB's host but another port.
    udpTask->receiveRlsPdu(otherAddress, HeartBeatAck(gnbSti, -1));
    udpTask->receiveRlsPdu(InetAddress{"127.0.0.1", 5000}, HeartBeatAck(gnbSti, -1));
    CHECK_FALSE(ctlTask.next());

    // So no cell takes what that sender goes on to send.
    udpTask->receiveRlsPdu(otherAddress, RrcPdu(gnbSti));
    CHECK_FALSE(ctlTask.next());
}

TEST_CASE_FIXTURE(UdpTaskFixture, "ue/rls/udp_task: a cell's messages are taken only from its own address")
{
    udpTask->receiveRlsPdu(gnbAddress, HeartBeatAck(gnbSti, -50));
    REQUIRE(ctlTask.next());

    udpTask->receiveRlsPdu(otherAddress, RrcPdu(gnbSti));
    CHECK_FALSE(ctlTask.next());
}
