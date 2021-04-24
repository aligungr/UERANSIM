//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <gnb/nts.hpp>
#include <gnb/rls/task.hpp>
#include <lib/rrc/encode.hpp>

#include <asn/rrc/ASN_RRC_DLInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_DLInformationTransfer.h>

namespace nr::gnb
{

GnbRrcTask::GnbRrcTask(TaskBase *base) : m_base{base}, m_ueCtx{}, m_tidCounter{}
{
    m_logger = base->logBase->makeUniqueLogger("rrc");
}

void GnbRrcTask::onStart()
{
}

void GnbRrcTask::onQuit()
{
    // todo
}

void GnbRrcTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::GNB_RLS_TO_RRC: {
        auto *w = dynamic_cast<NwGnbRlsToRrc *>(msg);
        switch (w->present)
        {
        case NwGnbRlsToRrc::RRC_PDU_DELIVERY: {
            handleUplinkRrc(w->ueId, w->channel, w->pdu);
            break;
        }
        case NwGnbRlsToRrc::SIGNAL_LOST: {
            handleRadioLinkFailure(w->ueId);
            break;
        }
        }
        break;
    }
    case NtsMessageType::GNB_NGAP_TO_RRC: {
        auto *w = dynamic_cast<NwGnbNgapToRrc *>(msg);
        switch (w->present)
        {
        case NwGnbNgapToRrc::RADIO_POWER_ON: {
            m_base->rlsTask->push(new NwGnbRrcToRls(NwGnbRrcToRls::RADIO_POWER_ON));
            break;
        }
        case NwGnbNgapToRrc::NAS_DELIVERY: {
            handleDownlinkNasDelivery(w->ueId, w->pdu);
            break;
        }
        case NwGnbNgapToRrc::AN_RELEASE: {
            releaseConnection(w->ueId);
            break;
        }
        case NwGnbNgapToRrc::PAGING:
            handlePaging(w->uePagingTmsi, w->taiListForPaging);
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

} // namespace nr::gnb
