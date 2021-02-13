//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <asn/rrc/ASN_RRC_RRCSetupRequest-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer.h>
#include <rrc/encode.hpp>
#include <ue/app/task.hpp>
#include <ue/mr/task.hpp>
#include <ue/nas/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

UeRrcTask::UeRrcTask(TaskBase *base) : m_base{base}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "rrc");

    m_state = ERrcState::RRC_IDLE;
}

void UeRrcTask::onStart()
{
    m_logger->debug("RRC layer started");
}

void UeRrcTask::onQuit()
{
    // TODO
}

void UeRrcTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_MR_TO_RRC: {
        auto *w = dynamic_cast<NwUeMrToRrc *>(msg);
        switch (w->present)
        {
        case NwUeMrToRrc::PLMN_SEARCH_RESPONSE: {
            auto *nw = new NwUeRrcToNas(NwUeRrcToNas::PLMN_SEARCH_RESPONSE);
            nw->gnbName = std::move(w->gnbName);
            m_base->nasTask->push(nw);
            break;
        }
        case NwUeMrToRrc::PLMN_SEARCH_FAILURE: {
            m_base->nasTask->push(new NwUeRrcToNas(NwUeRrcToNas::PLMN_SEARCH_FAILURE));
            break;
        }
        case NwUeMrToRrc::RRC_PDU_DELIVERY: {
            handleDownlinkRrc(w->channel, w->pdu);
            break;
        }
        }
        break;
    }
    case NtsMessageType::UE_NAS_TO_RRC: {
        auto *w = dynamic_cast<NwUeNasToRrc *>(msg);
        switch (w->present)
        {
        case NwUeNasToRrc::PLMN_SEARCH_REQUEST: {
            m_base->mrTask->push(new NwUeRrcToMr(NwUeRrcToMr::PLMN_SEARCH_REQUEST));
            break;
        }
        case NwUeNasToRrc::INITIAL_NAS_DELIVERY:
            deliverInitialNas(std::move(w->nasPdu), w->rrcEstablishmentCause);
            break;
        case NwUeNasToRrc::UPLINK_NAS_DELIVERY:
            deliverUplinkNas(std::move(w->nasPdu));
            break;
        }
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }

    delete msg;
}

} // namespace nr::ue