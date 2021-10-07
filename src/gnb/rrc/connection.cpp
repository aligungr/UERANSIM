//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <gnb/ngap/task.hpp>
#include <lib/rrc/encode.hpp>

#include <asn/ngap/ASN_NGAP_FiveG-S-TMSI.h>
#include <asn/rrc/ASN_RRC_BCCH-BCH-Message.h>
#include <asn/rrc/ASN_RRC_BCCH-DL-SCH-Message.h>
#include <asn/rrc/ASN_RRC_CellGroupConfig.h>
#include <asn/rrc/ASN_RRC_DL-CCCH-Message.h>
#include <asn/rrc/ASN_RRC_DL-DCCH-Message.h>
#include <asn/rrc/ASN_RRC_DLInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_DLInformationTransfer.h>
#include <asn/rrc/ASN_RRC_PCCH-Message.h>
#include <asn/rrc/ASN_RRC_Paging.h>
#include <asn/rrc/ASN_RRC_PagingRecord.h>
#include <asn/rrc/ASN_RRC_PagingRecordList.h>
#include <asn/rrc/ASN_RRC_RRCRelease-IEs.h>
#include <asn/rrc/ASN_RRC_RRCRelease.h>
#include <asn/rrc/ASN_RRC_RRCSetup-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetup.h>
#include <asn/rrc/ASN_RRC_RRCSetupComplete-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupComplete.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest.h>
#include <asn/rrc/ASN_RRC_UL-CCCH-Message.h>
#include <asn/rrc/ASN_RRC_UL-CCCH1-Message.h>
#include <asn/rrc/ASN_RRC_UL-DCCH-Message.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer.h>

namespace nr::gnb
{

void GnbRrcTask::receiveRrcSetupRequest(int ueId, const ASN_RRC_RRCSetupRequest &msg)
{
    auto *ue = tryFindUe(ueId);
    if (ue)
    {
        // TODO: handle this more properly
        m_logger->warn("Discarding RRC Setup Request, UE context already exists");
        return;
    }

    if (msg.rrcSetupRequest.ue_Identity.present == ASN_RRC_InitialUE_Identity_PR_ng_5G_S_TMSI_Part1)
    {
        m_logger->err("RRC Setup Request with TMSI not implemented yet");
        return;
    }

    if (msg.rrcSetupRequest.ue_Identity.present != ASN_RRC_InitialUE_Identity_PR_randomValue)
    {
        m_logger->err("Bad constructed RRC message ignored");
        return;
    }

    int64_t initialRandomId = asn::GetBitStringLong<39>(msg.rrcSetupRequest.ue_Identity.choice.randomValue);
    if (tryFindByInitialRandomId(initialRandomId) != nullptr)
    {
        m_logger->err("Initial random ID conflict [%ld], discarding RRC Setup Request", initialRandomId);
        return;
    }

    ue = createUe(ueId);
    ue->initialRandomId = initialRandomId;
    ue->establishmentCause = msg.rrcSetupRequest.establishmentCause;

    // Prepare RRC Setup
    auto *pdu = asn::New<ASN_RRC_DL_CCCH_Message>();
    pdu->message.present = ASN_RRC_DL_CCCH_MessageType_PR_c1;
    pdu->message.choice.c1 = asn::NewFor(pdu->message.choice.c1);
    pdu->message.choice.c1->present = ASN_RRC_DL_CCCH_MessageType__c1_PR_rrcSetup;
    auto &rrcSetup = pdu->message.choice.c1->choice.rrcSetup = asn::New<ASN_RRC_RRCSetup>();
    rrcSetup->rrc_TransactionIdentifier = getNextTid();
    rrcSetup->criticalExtensions.present = ASN_RRC_RRCSetup__criticalExtensions_PR_rrcSetup;
    auto &rrcSetupIEs = rrcSetup->criticalExtensions.choice.rrcSetup = asn::New<ASN_RRC_RRCSetup_IEs>();

    ASN_RRC_CellGroupConfig masterCellGroup{};
    masterCellGroup.cellGroupId = 0;

    asn::SetOctetString(rrcSetupIEs->masterCellGroup,
                        rrc::encode::EncodeS(asn_DEF_ASN_RRC_CellGroupConfig, &masterCellGroup));

    m_logger->info("RRC Setup for UE[%d]", ueId);
    sendRrcMessage(ueId, pdu);
}

void GnbRrcTask::receiveRrcSetupComplete(int ueId, const ASN_RRC_RRCSetupComplete &msg)
{
    auto *ue = findUe(ueId);
    if (!ue)
        return;

    auto setupComplete = msg.criticalExtensions.choice.rrcSetupComplete;

    auto *w = new NmGnbRrcToNgap(NmGnbRrcToNgap::INITIAL_NAS_DELIVERY);
    w->ueId = ueId;
    w->pdu = asn::GetOctetString(setupComplete->dedicatedNAS_Message);
    w->rrcEstablishmentCause = ue->establishmentCause;
    w->sTmsi = std::nullopt; // TODO

    m_base->ngapTask->push(w);
}

} // namespace nr::gnb
