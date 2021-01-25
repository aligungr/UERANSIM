//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <nas_timer.hpp>
#include <network.hpp>
#include <nts.hpp>
#include <octet_string.hpp>
#include <rrc.hpp>
#include <urs_rls.hpp>
#include <utility>

namespace nr::ue
{

struct NwDownlinkNasDelivery : NtsMessage
{
    OctetString nasPdu;

    explicit NwDownlinkNasDelivery(OctetString &&nasPdu)
        : NtsMessage(NtsMessageType::UE_DOWNLINK_NAS_DELIVERY), nasPdu(std::move(nasPdu))
    {
    }
};

struct NwUplinkNasDelivery : NtsMessage
{
    OctetString nasPdu;

    explicit NwUplinkNasDelivery(OctetString &&nasPdu)
        : NtsMessage(NtsMessageType::UE_UPLINK_NAS_DELIVERY), nasPdu(std::move(nasPdu))
    {
    }
};

struct NwNasTimerExpire : NtsMessage
{
    nas::NasTimer *timer;

    explicit NwNasTimerExpire(nas::NasTimer *timer) : NtsMessage(NtsMessageType::NAS_TIMER_EXPIRE), timer(timer)
    {
    }
};

struct NwPlmnSearchRequest : NtsMessage
{
    NwPlmnSearchRequest() : NtsMessage(NtsMessageType::UE_MR_PLMN_SEARCH_REQUEST)
    {
    }
};

struct NwPlmnSearchResponse : NtsMessage
{
    std::string gnbName;

    explicit NwPlmnSearchResponse(std::string gnbName)
        : NtsMessage(NtsMessageType::UE_MR_PLMN_SEARCH_RESPONSE), gnbName(std::move(gnbName))
    {
    }
};

struct NwPerformMmCycle : NtsMessage
{
    NwPerformMmCycle() : NtsMessage(NtsMessageType::NAS_PERFORM_MM_CYCLE)
    {
    }
};

struct NwUeUplinkRrc : NtsMessage
{
    rrc::RrcChannel channel;
    OctetString rrcPdu;

    NwUeUplinkRrc(rrc::RrcChannel channel, OctetString &&rrcPdu)
        : NtsMessage(NtsMessageType::UE_MR_UPLINK_RRC), channel(channel), rrcPdu(std::move(rrcPdu))
    {
    }
};

struct NwUeDownlinkRrc : NtsMessage
{
    rrc::RrcChannel channel;
    OctetString rrcPdu;

    NwUeDownlinkRrc(rrc::RrcChannel channel, OctetString &&rrcPdu)
        : NtsMessage(NtsMessageType::UE_MR_DOWNLINK_RRC), channel(channel), rrcPdu(std::move(rrcPdu))
    {
    }
};

struct NwRlsConnected : NtsMessage
{
    std::string gnbName;

    explicit NwRlsConnected(std::string gnbName)
        : NtsMessage(NtsMessageType::UE_RLS_CONNECTED), gnbName(std::move(gnbName))
    {
    }
};

struct NwRlsReleased : NtsMessage
{
    rls::ECause cause;

    explicit NwRlsReleased(rls::ECause cause) : NtsMessage(NtsMessageType::UE_RLS_RELEASED), cause(cause)
    {
    }
};

struct NwRlsSearchFailure : NtsMessage
{
    rls::ECause cause;

    explicit NwRlsSearchFailure(rls::ECause cause) : NtsMessage(NtsMessageType::UE_RLS_SEARCH_FAILURE), cause(cause)
    {
    }
};

struct NwRlsStartWaitingTimer : NtsMessage
{
    int period;

    explicit NwRlsStartWaitingTimer(int period) : NtsMessage(NtsMessageType::UE_RLS_START_WAITING_TIMER), period(period)
    {
    }
};

struct NwDownlinkPayload : NtsMessage
{
    rls::EPayloadType type;
    OctetString payload;

    NwDownlinkPayload(rls::EPayloadType type, OctetString &&payload)
        : NtsMessage(NtsMessageType::UE_RLS_DOWNLINK_PAYLOAD), type(type), payload(std::move(payload))
    {
    }
};

struct NwRlsSendPdu : NtsMessage
{
    InetAddress address;
    OctetString pdu;

    NwRlsSendPdu(const InetAddress &address, OctetString &&pdu)
        : NtsMessage(NtsMessageType::UE_RLS_SEND_PDU), address(address), pdu(std::move(pdu))
    {
    }
};

} // namespace nr::ue