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
#include <utility>

namespace nr::ue
{

struct NwDownlinkNasDelivery : NtsMessage
{
    OctetString nasPdu;

    explicit NwDownlinkNasDelivery(OctetString &&nasPdu)
        : NtsMessage(NtsMessageType::NGAP_DOWNLINK_NAS_DELIVERY), nasPdu(std::move(nasPdu))
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

} // namespace nr::ue