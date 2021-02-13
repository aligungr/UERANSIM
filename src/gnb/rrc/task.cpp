//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <asn/rrc/ASN_RRC_DLInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_DLInformationTransfer.h>
#include <gnb/mr/task.hpp>
#include <gnb/nts.hpp>
#include <rrc/encode.hpp>

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
    case NtsMessageType::GNB_MR_TO_RRC: {
        auto *w = dynamic_cast<NwGnbMrToRrc *>(msg);
        switch (w->present)
        {
        case NwGnbMrToRrc::RRC_PDU_DELIVERY: {
            handleUplinkRrc(w->ueId, w->channel, w->pdu);
            break;
        }
        }
        break;
    }
    case NtsMessageType::GNB_NGAP_TO_RRC: {
        auto *w = dynamic_cast<NwGnbNgapToRrc *>(msg);
        switch (w->present)
        {
        case NwGnbNgapToRrc::NGAP_LAYER_INITIALIZED: {
            m_base->mrTask->push(new NwGnbRrcToMr(NwGnbRrcToMr::NGAP_LAYER_INITIALIZED));
            break;
        }
        case NwGnbNgapToRrc::NAS_DELIVERY: {
            handleDownlinkNasDelivery(w->ueId, w->pdu);
            break;
        }
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
