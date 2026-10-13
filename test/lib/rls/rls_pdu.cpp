//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <cstdint>
#include <memory>
#include <type_traits>
#include <vector>

#include <ext/doctest/doctest.h>
#include <lib/rls/rls_pdu.hpp>
#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

static std::unique_ptr<rls::RlsMessage> EncodeAndDecode(const rls::RlsMessage &msg)
{
    OctetString stream;
    rls::EncodeRlsMessage(msg, stream);
    return rls::DecodeRlsMessage(OctetView{stream});
}

TEST_CASE("lib/rls/rls_pdu: a message can be deleted through RlsMessage")
{
    // The decoder hands every message out as a std::unique_ptr<RlsMessage>, and the NTS messages keep it that way.
    CHECK(std::has_virtual_destructor<rls::RlsMessage>::value);
}

// Each decoded message is also deleted through the base class; ASan checks that this frees it as what it is.
TEST_CASE("lib/rls/rls_pdu: every message type survives encoding and decoding")
{
    const uint64_t sti = 0x0102030405060708;

    SUBCASE("heartbeat")
    {
        rls::RlsHeartBeat msg{sti};
        msg.simPos = Vector3{1, -2, 3};

        auto decoded = EncodeAndDecode(msg);
        REQUIRE(decoded);
        REQUIRE_EQ(decoded->msgType, rls::EMessageType::HEARTBEAT);
        CHECK_EQ(decoded->sti, sti);
        auto &heartbeat = static_cast<const rls::RlsHeartBeat &>(*decoded);
        CHECK_EQ(heartbeat.simPos.x, 1);
        CHECK_EQ(heartbeat.simPos.y, -2);
        CHECK_EQ(heartbeat.simPos.z, 3);
    }

    SUBCASE("heartbeat ack")
    {
        rls::RlsHeartBeatAck msg{sti};
        msg.dbm = -42;

        auto decoded = EncodeAndDecode(msg);
        REQUIRE(decoded);
        REQUIRE_EQ(decoded->msgType, rls::EMessageType::HEARTBEAT_ACK);
        CHECK_EQ(decoded->sti, sti);
        CHECK_EQ(static_cast<const rls::RlsHeartBeatAck &>(*decoded).dbm, -42);
    }

    SUBCASE("PDU transmission")
    {
        rls::RlsPduTransmission msg{sti};
        msg.pduType = rls::EPduType::DATA;
        msg.pduId = 7;
        msg.payload = 9;
        msg.pdu = OctetString::FromHex("0A0B0C");

        auto decoded = EncodeAndDecode(msg);
        REQUIRE(decoded);
        REQUIRE_EQ(decoded->msgType, rls::EMessageType::PDU_TRANSMISSION);
        CHECK_EQ(decoded->sti, sti);
        auto &transmission = static_cast<const rls::RlsPduTransmission &>(*decoded);
        CHECK_EQ(transmission.pduType, rls::EPduType::DATA);
        CHECK_EQ(transmission.pduId, 7u);
        CHECK_EQ(transmission.payload, 9u);
        CHECK_EQ(transmission.pdu.toHexString(), "0A0B0C");
    }

    SUBCASE("PDU transmission ack")
    {
        rls::RlsPduTransmissionAck msg{sti};
        msg.pduIds = {1, 2, 3};

        auto decoded = EncodeAndDecode(msg);
        REQUIRE(decoded);
        REQUIRE_EQ(decoded->msgType, rls::EMessageType::PDU_TRANSMISSION_ACK);
        CHECK_EQ(decoded->sti, sti);
        CHECK_EQ(static_cast<const rls::RlsPduTransmissionAck &>(*decoded).pduIds, std::vector<uint32_t>{1, 2, 3});
    }
}
