//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"

#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>
#include <ue/nts.hpp>

#include <asn/rrc/ASN_RRC_DLInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_DLInformationTransfer.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer.h>

namespace nr::ue
{

void UeRrcTask::deliverUplinkNas(uint32_t pduId, OctetString &&nasPdu)
{
    if (!m_base->shCtx.currentCell.get<bool>([](auto &value) { return value.hasValue(); }))
    {
        m_logger->err("Uplink NAS delivery failed. No active cell");
        return;
    }

    if (nasPdu.length() == 0)
        return;

    if (m_state == ERrcState::RRC_IDLE)
    {
        startConnectionEstablishment(std::move(nasPdu));
        return;
    }
    else if (m_state == ERrcState::RRC_INACTIVE)
    {
        // TODO
        return;
    }

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
    asn::Free(asn_DEF_ASN_RRC_UL_DCCH_Message, pdu);
}

void UeRrcTask::receiveDownlinkInformationTransfer(const ASN_RRC_DLInformationTransfer &msg)
{
    OctetString nasPdu =
        asn::GetOctetString(*msg.criticalExtensions.choice.dlInformationTransfer->dedicatedNAS_Message);

    auto m = std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::NAS_DELIVERY);
    m->nasPdu = std::move(nasPdu);
    m_base->nasTask->push(std::move(m));
}

} // namespace nr::ue