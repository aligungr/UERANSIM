//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <nas_timer.hpp>
#include <nts.hpp>
#include <octet_string.hpp>
#include <rrc.hpp>
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
        : NtsMessage(NtsMessageType::UE_MR_UPLINK_RRC), channel(channel), rrcPdu(std::move(rrcPdu))
    {
    }
};

} // namespace nr::ue