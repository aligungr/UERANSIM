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

void NasMm::handleRrcEvent(const NmUeRrcToNas &msg)
{
    if (m_mmState == EMmState::MM_NULL)
        return;

    switch (msg.present)
    {
    case NmUeRrcToNas::RRC_CONNECTION_SETUP: {
        handleRrcConnectionSetup();
        break;
    }
    case NmUeRrcToNas::NAS_DELIVERY: {
        OctetView buffer{msg.nasPdu};
        auto nasMessage = nas::DecodeNasMessage(buffer);
        if (nasMessage != nullptr)
            receiveNasMessage(*nasMessage);
        break;
    }
    case NmUeRrcToNas::RRC_CONNECTION_RELEASE: {
        handleRrcConnectionRelease();
        break;
    }
    case NmUeRrcToNas::RADIO_LINK_FAILURE: {
        handleRadioLinkFailure();
        break;
    }
    case NmUeRrcToNas::PAGING: {
        handlePaging(msg.pagingTmsi);
        break;
    }
    case NmUeRrcToNas::NAS_NOTIFY: {
        triggerMmCycle();
        break;
    }
    case NmUeRrcToNas::ACTIVE_CELL_CHANGED: {
        handleActiveCellChange(msg.previousTai);
        break;
    }
    case NmUeRrcToNas::RRC_ESTABLISHMENT_FAILURE: {
        handleRrcEstablishmentFailure();
        break;
    }
    case NmUeRrcToNas::RRC_FALLBACK_INDICATION: {
        handleRrcFallbackIndication();
        break;
    }
    }
}

void NasMm::handleNasEvent(const NmUeNasToNas &msg)
{
    if (m_mmState == EMmState::MM_NULL)
        return;

    switch (msg.present)
    {
    case NmUeNasToNas::PERFORM_MM_CYCLE:
        performMmCycle();
        break;
    case NmUeNasToNas::NAS_TIMER_EXPIRE:
        onTimerExpire(*msg.timer);
        break;
    default:
        break;
    }
}

} // namespace nr::ue
