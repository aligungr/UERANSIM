//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "mm.hpp"

#include <utils/common.hpp>

#include <ue/app/state_learner.hpp>

namespace nr::ue
{

// check if the message is a replayed message
OctetString retransbuf = OctetString();

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
        if (msg.nasPdu.length() == 0)
        {
            m_logger->err("Empty NAS PDU received, ignore it");
            break;
        }
        
        if (retransbuf.length() == 0 || (retransbuf != msg.nasPdu))
        {
            
            OctetView buffer{msg.nasPdu};
            auto nasMessage = nas::DecodeNasMessage(buffer);
            if (nasMessage != nullptr)
                receiveNasMessage(*nasMessage);
            
            if (!state_learner->is_ignored)
            {
                retransbuf = msg.nasPdu.copy();
                m_logger->warn("NAS message received and stored in retransbuf");
            }
            else
            {
                state_learner->is_ignored = false;
                m_logger->warn("NAS message ignored for replay detection");
            }
        }
        else
        {
            m_logger->warn("Replayed NAS message detected, discarding the message");
        }

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
