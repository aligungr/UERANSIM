//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <asn/utils/utils.hpp>
#include <rrc/encode.hpp>
#include <ue/nas/task.hpp>
#include <ue/nts.hpp>
#include <utils/common.hpp>

#include <asn/rrc/ASN_RRC_DLInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_DLInformationTransfer.h>
#include <asn/rrc/ASN_RRC_RRCSetup-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetup.h>
#include <asn/rrc/ASN_RRC_RRCSetupComplete-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupComplete.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest.h>
#include <asn/rrc/ASN_RRC_UL-DCCH-Message.h>
#include <asn/rrc/ASN_RRC_UL-DCCH-MessageType.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer.h>

namespace nr::ue
{

void UeRrcTask::deliverInitialNas(OctetString &&nasPdu, long establishmentCause)
{
    if (m_state != ERrcState::RRC_IDLE)
    {
        m_logger->warn("Initial NAS delivery while not in RRC_IDLE, treating as uplink delivery");
        deliverUplinkNas(std::move(nasPdu));
        return;
    }

    m_logger->debug("Sending RRC Setup Request");

    auto *pdu = asn::New<ASN_RRC_UL_CCCH_Message>();
    pdu->message.present = ASN_RRC_UL_CCCH_MessageType_PR_c1;
    pdu->message.choice.c1 = asn::NewFor(pdu->message.choice.c1);
    pdu->message.choice.c1->present = ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcSetupRequest;

    m_initialId.present = ASN_RRC_InitialUE_Identity_PR_randomValue;
    asn::SetBitStringLong<39>(utils::Random64(), m_initialId.choice.randomValue);

    auto &r = pdu->message.choice.c1->choice.rrcSetupRequest = asn::New<ASN_RRC_RRCSetupRequest>();
    r->rrcSetupRequest.establishmentCause = establishmentCause;
    asn::SetSpareBits<1>(r->rrcSetupRequest.spare);
    asn::DeepCopy(asn_DEF_ASN_RRC_InitialUE_Identity, m_initialId, &r->rrcSetupRequest.ue_Identity);

    // TODO: Start T300

    m_initialNasPdu = std::move(nasPdu);
    m_lastSetupReq = ERrcLastSetupRequest::SETUP_REQUEST;
    sendRrcMessage(pdu);
}

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

void UeRrcTask::receiveRrcSetup(const ASN_RRC_RRCSetup &msg)
{
    if (m_lastSetupReq != ERrcLastSetupRequest::SETUP_REQUEST)
    {
        // TODO
        return;
    }

    m_state = ERrcState::RRC_CONNECTED;

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
    m_initialNasPdu = {};

    m_logger->info("RRC connection established");

    m_base->nasTask->push(new NwUeRrcToNas(NwUeRrcToNas::RRC_CONNECTION_SETUP));

    sendRrcMessage(pdu);
}

void UeRrcTask::receiveRrcReject(const ASN_RRC_RRCReject &msg)
{
    // TODO
    m_logger->err("RRC Reject received");
}

void UeRrcTask::receiveDownlinkInformationTransfer(const ASN_RRC_DLInformationTransfer &msg)
{
    OctetString nasPdu =
        asn::GetOctetString(*msg.criticalExtensions.choice.dlInformationTransfer->dedicatedNAS_Message);

    auto *nw = new NwUeRrcToNas(NwUeRrcToNas::NAS_DELIVERY);
    nw->nasPdu = std::move(nasPdu);
    m_base->nasTask->push(nw);
}

} // namespace nr::ue