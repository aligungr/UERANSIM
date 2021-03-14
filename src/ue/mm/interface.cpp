//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <utils/common.hpp>

namespace nr::ue
{

void NasMm::handleRrcEvent(const NwUeRrcToNas &msg)
{
    switch (msg.present)
    {
    case NwUeRrcToNas::RRC_CONNECTION_SETUP: {
        handleRrcConnectionSetup();
        break;
    }
    case NwUeRrcToNas::PLMN_SEARCH_RESPONSE: {
        handlePlmnSearchResponse(msg.gnbName);
        break;
    }
    case NwUeRrcToNas::PLMN_SEARCH_FAILURE: {
        handlePlmnSearchFailure();
        break;
    }
    case NwUeRrcToNas::NAS_DELIVERY: {
        OctetView buffer{msg.nasPdu};
        auto nasMessage = nas::DecodeNasMessage(buffer);
        if (nasMessage != nullptr)
            receiveNasMessage(*nasMessage);
        break;
    }
    case NwUeRrcToNas::RRC_CONNECTION_RELEASE: {
        handleRrcConnectionRelease();
        break;
    }
    case NwUeRrcToNas::RADIO_LINK_FAILURE: {
        handleRadioLinkFailure();
        break;
    }
    }
}

void NasMm::handleNasEvent(const NwUeNasToNas &msg)
{
    switch (msg.present)
    {
    case NwUeNasToNas::NAS_TIMER_EXPIRE:
        onTimerExpire(*msg.timer);
        break;
    default:
        break;
    }
}

} // namespace nr::ue
