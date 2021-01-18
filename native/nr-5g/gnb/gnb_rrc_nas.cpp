//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_task.hpp"
#include "gnb_rrc_task.hpp"

#include <rrc_encode.hpp>

#include <ASN_RRC_DL-DCCH-Message.h>
#include <ASN_RRC_DLInformationTransfer-IEs.h>
#include <ASN_RRC_DLInformationTransfer.h>

namespace nr::gnb
{

void GnbRrcTask::handleDownlinkNasDelivery(NwDownlinkNasDelivery *msg)
{
    auto *pdu = asn::New<ASN_RRC_DL_DCCH_Message>();
    pdu->message.present = ASN_RRC_DL_DCCH_MessageType_PR_c1;
    pdu->message.choice.c1->present = ASN_RRC_DL_DCCH_MessageType__c1_PR_dlInformationTransfer;

    auto &c1 = pdu->message.choice.c1->choice.dlInformationTransfer->criticalExtensions;
    c1.present = ASN_RRC_DLInformationTransfer__criticalExtensions_PR_dlInformationTransfer;
    c1.choice.dlInformationTransfer = asn::New<ASN_RRC_DLInformationTransfer_IEs>();
    c1.choice.dlInformationTransfer->dedicatedNAS_Message = asn::New<ASN_RRC_DedicatedNAS_Message_t>();
    asn::SetOctetString(*c1.choice.dlInformationTransfer->dedicatedNAS_Message, msg->nasPdu);

    sendRrcMessage(msg->ueId, pdu);
    delete msg;
}

void GnbRrcTask::deliverUplinkNas(int ueId, OctetString &&nasPdu)
{
    base->ngapTask->push(new NwUplinkNasDelivery(ueId, std::move(nasPdu)));
}

} // namespace nr::gnb