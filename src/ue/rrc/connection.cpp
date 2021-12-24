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
#include <utils/random.hpp>

#include <asn/rrc/ASN_RRC_RRCSetup-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetup.h>
#include <asn/rrc/ASN_RRC_RRCSetupComplete-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupComplete.h>
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

void UeRrcTask::startConnectionEstablishment(OctetString &&nasPdu)
{
    /* Check the protocol state */
    if (m_state != ERrcState::RRC_IDLE)
    {
        m_logger->err("RRC establishment could not start, UE not in RRC-IDLE state");
        handleEstablishmentFailure();
        return;
    }

    /* Check the current cell */
    int activeCell = m_base->shCtx.currentCell.get<int>([](auto &item) { return item.cellId; });
    if (activeCell == 0)
    {
        m_logger->err("RRC establishment could not start, no active cell");
        handleEstablishmentFailure();
        return;
    }

    /* Handle Initial UE Identity (S-TMSI or 39-bit random value) */
    std::optional<GutiMobileIdentity> gutiOrTmsi = m_base->shCtx.providedGuti.get();
    if (!gutiOrTmsi)
        gutiOrTmsi = m_base->shCtx.providedTmsi.get();

    if (gutiOrTmsi)
    {
        m_initialId.present = ASN_RRC_InitialUE_Identity_PR_ng_5G_S_TMSI_Part1;
        asn::SetBitStringLong<39>(static_cast<int64_t>(gutiOrTmsi->tmsi) |
                                      (static_cast<int64_t>((gutiOrTmsi->amfPointer & 0b1111111)) << 39ull),
                                  m_initialId.choice.ng_5G_S_TMSI_Part1);
    }
    else
    {
        m_initialId.present = ASN_RRC_InitialUE_Identity_PR_randomValue;
        asn::SetBitStringLong<39>(Random::Mixed(m_base->config->getNodeName()).nextL(), m_initialId.choice.randomValue);
    }

    /* Set the Initial NAS PDU */
    m_initialNasPdu = std::move(nasPdu);

    /* Send the message */
    m_logger->debug("Sending RRC Setup Request");

    auto *rrcSetupRequest =
        ConstructSetupRequest(m_initialId, static_cast<ASN_RRC_EstablishmentCause_t>(m_establishmentCause));
    sendRrcMessage(activeCell, rrcSetupRequest);
    asn::Free(asn_DEF_ASN_RRC_UL_CCCH_Message, rrcSetupRequest);
}

void UeRrcTask::receiveRrcSetup(int cellId, const ASN_RRC_RRCSetup &msg)
{
    if (!isActiveCell(cellId))
        return;

    if (m_lastSetupReq != ERrcLastSetupRequest::SETUP_REQUEST)
    {
        // TODO
        return;
    }

    auto *pdu = asn::New<ASN_RRC_UL_DCCH_Message>();
    pdu->message.present = ASN_RRC_UL_DCCH_MessageType_PR_c1;
    pdu->message.choice.c1 = asn::NewFor(pdu->message.choice.c1);
    pdu->message.choice.c1->present = ASN_RRC_UL_DCCH_MessageType__c1_PR_rrcSetupComplete;

    auto &setupComplete = pdu->message.choice.c1->choice.rrcSetupComplete = asn::New<ASN_RRC_RRCSetupComplete>();
    setupComplete->rrc_TransactionIdentifier = msg.rrc_TransactionIdentifier;
    setupComplete->criticalExtensions.present = ASN_RRC_RRCSetupComplete__criticalExtensions_PR_rrcSetupComplete;

    auto &ies = setupComplete->criticalExtensions.choice.rrcSetupComplete = asn::New<ASN_RRC_RRCSetupComplete_IEs>();
    ies->selectedPLMN_Identity = 1;
    asn::SetOctetString(ies->dedicatedNAS_Message, m_initialNasPdu);

    /* Send S-TMSI if available */
    std::optional<GutiMobileIdentity> gutiOrTmsi = m_base->shCtx.providedGuti.get();
    if (!gutiOrTmsi)
        gutiOrTmsi = m_base->shCtx.providedTmsi.get();
    if (gutiOrTmsi)
    {
        auto &sTmsi = setupComplete->criticalExtensions.choice.rrcSetupComplete->ng_5G_S_TMSI_Value =
            asn::New<ASN_RRC_RRCSetupComplete_IEs::ASN_RRC_RRCSetupComplete_IEs__ng_5G_S_TMSI_Value>();
        sTmsi->present = ASN_RRC_RRCSetupComplete_IEs__ng_5G_S_TMSI_Value_PR_ng_5G_S_TMSI;
        asn::SetBitStringLong<48>(gutiOrTmsi->toTmsiValue(), sTmsi->choice.ng_5G_S_TMSI);
    }

    m_initialNasPdu = {};
    sendRrcMessage(pdu);
    asn::Free(asn_DEF_ASN_RRC_UL_DCCH_Message, pdu);

    m_logger->info("RRC connection established");
    switchState(ERrcState::RRC_CONNECTED);
    m_base->nasTask->push(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::RRC_CONNECTION_SETUP));
}

void UeRrcTask::receiveRrcReject(int cellId, const ASN_RRC_RRCReject &msg)
{
    if (!isActiveCell(cellId))
        return;

    m_logger->err("RRC Reject received");

    handleEstablishmentFailure();
}

void UeRrcTask::receiveRrcRelease(const ASN_RRC_RRCRelease &msg)
{
    m_logger->debug("RRC Release received");
    m_state = ERrcState::RRC_IDLE;
    m_base->nasTask->push(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::RRC_CONNECTION_RELEASE));
}

void UeRrcTask::handleEstablishmentFailure()
{
    m_base->nasTask->push(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::RRC_ESTABLISHMENT_FAILURE));
}

} // namespace nr::ue
