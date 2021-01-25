//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_rrc_task.hpp"

#include <asn_utils.hpp>
#include <rrc_encode.hpp>

#include <ASN_RRC_ULInformationTransfer-IEs.h>
#include <ASN_RRC_ULInformationTransfer.h>

namespace nr::ue
{

void UeRrcTask::deliverUplinkNas(OctetString &&nasPdu)
{
    auto *pdu = asn::New<ASN_RRC_UL_DCCH_Message>();
    pdu->message.present = ASN_RRC_UL_DCCH_MessageType_PR_c1;
    pdu->message.choice.c1 =
        asn::New<ASN_RRC_UL_DCCH_MessageType::ASN_RRC_UL_DCCH_MessageType_u::ASN_RRC_UL_DCCH_MessageType__c1>();
    pdu->message.choice.c1->present = ASN_RRC_UL_DCCH_MessageType__c1_PR_ulInformationTransfer;
    pdu->message.choice.c1->choice.ulInformationTransfer = asn::New<ASN_RRC_ULInformationTransfer>();

    auto &c1 = pdu->message.choice.c1->choice.ulInformationTransfer->criticalExtensions;
    c1.present = ASN_RRC_ULInformationTransfer__criticalExtensions_PR_ulInformationTransfer;
    c1.choice.ulInformationTransfer = asn::New<ASN_RRC_ULInformationTransfer_IEs>();
    c1.choice.ulInformationTransfer->dedicatedNAS_Message = asn::New<ASN_RRC_DedicatedNAS_Message_t>();

    asn::SetOctetString(*c1.choice.ulInformationTransfer->dedicatedNAS_Message, nasPdu);

    sendRrcMessage(pdu);
}

} // namespace nr::ue