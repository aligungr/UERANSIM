//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>
#include <ue/nts.hpp>

#include <asn/rrc/ASN_RRC_RRCSetupRequest-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest.h>

namespace nr::ue
{

static ASN_RRC_UL_CCCH_Message *ConstructSetupRequest(ASN_RRC_InitialUE_Identity_t initialUeId,
                                                      ASN_RRC_EstablishmentCause_t establishmentCause)
{
    auto *pdu = asn::New<ASN_RRC_UL_CCCH_Message>();
    pdu->message.present = ASN_RRC_UL_CCCH_MessageType_PR_c1;
    pdu->message.choice.c1 = asn::NewFor(pdu->message.choice.c1);
    pdu->message.choice.c1->present = ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcSetupRequest;

    auto &r = pdu->message.choice.c1->choice.rrcSetupRequest = asn::New<ASN_RRC_RRCSetupRequest>();
    asn::DeepCopy(asn_DEF_ASN_RRC_InitialUE_Identity, initialUeId, &r->rrcSetupRequest.ue_Identity);
    r->rrcSetupRequest.establishmentCause = establishmentCause;
    asn::SetSpareBits<1>(r->rrcSetupRequest.spare);

    return pdu;
}

void UeRrcTask::startConnectionSetup(OctetString &&nasPdu)
{
    // TODO: if it's already in progress

    if (m_initialId.present == ASN_RRC_InitialUE_Identity_PR_NOTHING)
    {
        m_initialId.present = ASN_RRC_InitialUE_Identity_PR_randomValue;
        asn::SetBitStringLong<39>(static_cast<int64_t>(utils::Random64()), m_initialId.choice.randomValue);
    }

    m_initialNasPdu = std::move(nasPdu);

    int activeCell = m_base->shCtx.currentCell.get<int>([](auto &item) { return item.cellId; });

    m_logger->debug("Sending RRC Setup Request");

    auto *rrcSetupRequest = ConstructSetupRequest(m_initialId, ASN_RRC_EstablishmentCause_mo_Data);
    sendRrcMessage(activeCell, rrcSetupRequest);
    asn::Free(asn_DEF_ASN_RRC_UL_CCCH_Message, rrcSetupRequest);
}

} // namespace nr::ue