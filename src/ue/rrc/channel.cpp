//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <rrc/encode.hpp>
#include <ue/mr/task.hpp>

#include <asn/rrc/ASN_RRC_RRCReject.h>
#include <asn/rrc/ASN_RRC_RRCSetup.h>
#include <asn/rrc/ASN_RRC_UL-CCCH-Message.h>
#include <asn/rrc/ASN_RRC_UL-DCCH-Message.h>

namespace nr::ue
{

void UeRrcTask::handleDownlinkRrc(rrc::RrcChannel channel, const OctetString &rrcPdu)
{
    switch (channel)
    {
    case rrc::RrcChannel::BCCH_BCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_BCCH_BCH_Message>(asn_DEF_ASN_RRC_BCCH_BCH_Message, rrcPdu);
        if (pdu == nullptr)
            m_logger->err("RRC BCCH-BCH PDU decoding failed.");
        else
            receiveRrcMessage(pdu);
        asn::Free(asn_DEF_ASN_RRC_BCCH_BCH_Message, pdu);
        break;
    }
    case rrc::RrcChannel::BCCH_DL_SCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_BCCH_DL_SCH_Message>(asn_DEF_ASN_RRC_BCCH_DL_SCH_Message, rrcPdu);
        if (pdu == nullptr)
            m_logger->err("RRC BCCH-DL-SCH PDU decoding failed.");
        else
            receiveRrcMessage(pdu);
        asn::Free(asn_DEF_ASN_RRC_BCCH_DL_SCH_Message, pdu);
        break;
    }
    case rrc::RrcChannel::DL_CCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_DL_CCCH_Message>(asn_DEF_ASN_RRC_DL_CCCH_Message, rrcPdu);
        if (pdu == nullptr)
            m_logger->err("RRC DL-CCCH PDU decoding failed.");
        else
            receiveRrcMessage(pdu);
        asn::Free(asn_DEF_ASN_RRC_DL_CCCH_Message, pdu);
        break;
    }
    case rrc::RrcChannel::DL_DCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_DL_DCCH_Message>(asn_DEF_ASN_RRC_DL_DCCH_Message, rrcPdu);
        if (pdu == nullptr)
            m_logger->err("RRC DL-DCCH PDU decoding failed.");
        else
            receiveRrcMessage(pdu);
        asn::Free(asn_DEF_ASN_RRC_DL_DCCH_Message, pdu);
        break;
    };
    case rrc::RrcChannel::PCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_PCCH_Message>(asn_DEF_ASN_RRC_PCCH_Message, rrcPdu);
        if (pdu == nullptr)
            m_logger->err("RRC PCCH PDU decoding failed.");
        else
            receiveRrcMessage(pdu);
        asn::Free(asn_DEF_ASN_RRC_PCCH_Message, pdu);
        break;
    }
    case rrc::RrcChannel::UL_CCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_UL_CCCH_Message>(asn_DEF_ASN_RRC_UL_CCCH_Message, rrcPdu);
        if (pdu == nullptr)
            m_logger->err("RRC UL-CCCH PDU decoding failed.");
        else
            receiveRrcMessage(pdu);
        asn::Free(asn_DEF_ASN_RRC_UL_CCCH_Message, pdu);
        break;
    }
    case rrc::RrcChannel::UL_CCCH1: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_UL_CCCH1_Message>(asn_DEF_ASN_RRC_UL_CCCH1_Message, rrcPdu);
        if (pdu == nullptr)
            m_logger->err("RRC UL-CCCH1 PDU decoding failed.");
        else
            receiveRrcMessage(pdu);
        asn::Free(asn_DEF_ASN_RRC_UL_CCCH1_Message, pdu);
        break;
    }
    case rrc::RrcChannel::UL_DCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_UL_DCCH_Message>(asn_DEF_ASN_RRC_UL_DCCH_Message, rrcPdu);
        if (pdu == nullptr)
            m_logger->err("RRC UL-DCCH PDU decoding failed.");
        else
            receiveRrcMessage(pdu);
        asn::Free(asn_DEF_ASN_RRC_UL_DCCH_Message, pdu);
        break;
    }
    }
}

void UeRrcTask::sendRrcMessage(ASN_RRC_BCCH_BCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_BCCH_BCH_Message, msg);
    if (pdu.length() == 0)
    {
        m_logger->err("RRC BCCH-BCH encoding failed.");
        return;
    }

    auto *nw = new NwUeRrcToMr(NwUeRrcToMr::RRC_PDU_DELIVERY);
    nw->channel = rrc::RrcChannel::BCCH_BCH;
    nw->pdu = std::move(pdu);
    m_base->mrTask->push(nw);
}

void UeRrcTask::sendRrcMessage(ASN_RRC_BCCH_DL_SCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_BCCH_DL_SCH_Message, msg);
    if (pdu.length() == 0)
    {
        m_logger->err("RRC BCCH-DL-SCH encoding failed.");
        return;
    }

    auto *nw = new NwUeRrcToMr(NwUeRrcToMr::RRC_PDU_DELIVERY);
    nw->channel = rrc::RrcChannel::BCCH_DL_SCH;
    nw->pdu = std::move(pdu);
    m_base->mrTask->push(nw);
}

void UeRrcTask::sendRrcMessage(ASN_RRC_DL_CCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_DL_CCCH_Message, msg);
    if (pdu.length() == 0)
    {
        m_logger->err("RRC DL-CCCH encoding failed.");
        return;
    }

    auto *nw = new NwUeRrcToMr(NwUeRrcToMr::RRC_PDU_DELIVERY);
    nw->channel = rrc::RrcChannel::DL_CCCH;
    nw->pdu = std::move(pdu);
    m_base->mrTask->push(nw);
}

void UeRrcTask::sendRrcMessage(ASN_RRC_DL_DCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_DL_DCCH_Message, msg);
    if (pdu.length() == 0)
    {
        m_logger->err("RRC DL-DCCH encoding failed.");
        return;
    }

    auto *nw = new NwUeRrcToMr(NwUeRrcToMr::RRC_PDU_DELIVERY);
    nw->channel = rrc::RrcChannel::DL_DCCH;
    nw->pdu = std::move(pdu);
    m_base->mrTask->push(nw);
}

void UeRrcTask::sendRrcMessage(ASN_RRC_PCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_PCCH_Message, msg);
    if (pdu.length() == 0)
    {
        m_logger->err("RRC PCCH encoding failed.");
        return;
    }

    auto *nw = new NwUeRrcToMr(NwUeRrcToMr::RRC_PDU_DELIVERY);
    nw->channel = rrc::RrcChannel::PCCH;
    nw->pdu = std::move(pdu);
    m_base->mrTask->push(nw);
}

void UeRrcTask::sendRrcMessage(ASN_RRC_UL_CCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_UL_CCCH_Message, msg);
    if (pdu.length() == 0)
    {
        m_logger->err("RRC UL-CCCH encoding failed.");
        return;
    }

    auto *nw = new NwUeRrcToMr(NwUeRrcToMr::RRC_PDU_DELIVERY);
    nw->channel = rrc::RrcChannel::UL_CCCH;
    nw->pdu = std::move(pdu);
    m_base->mrTask->push(nw);
}

void UeRrcTask::sendRrcMessage(ASN_RRC_UL_CCCH1_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_UL_CCCH1_Message, msg);
    if (pdu.length() == 0)
    {
        m_logger->err("RRC UL-CCCH1 encoding failed.");
        return;
    }

    auto *nw = new NwUeRrcToMr(NwUeRrcToMr::RRC_PDU_DELIVERY);
    nw->channel = rrc::RrcChannel::UL_CCCH1;
    nw->pdu = std::move(pdu);
    m_base->mrTask->push(nw);
}

void UeRrcTask::sendRrcMessage(ASN_RRC_UL_DCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_UL_DCCH_Message, msg);
    if (pdu.length() == 0)
    {
        m_logger->err("RRC UL-DCCH encoding failed.");
        return;
    }

    auto *nw = new NwUeRrcToMr(NwUeRrcToMr::RRC_PDU_DELIVERY);
    nw->channel = rrc::RrcChannel::UL_DCCH;
    nw->pdu = std::move(pdu);
    m_base->mrTask->push(nw);
}

void UeRrcTask::receiveRrcMessage(ASN_RRC_BCCH_BCH_Message *msg)
{
    // TODO
}

void UeRrcTask::receiveRrcMessage(ASN_RRC_BCCH_DL_SCH_Message *msg)
{
    // TODO
}

void UeRrcTask::receiveRrcMessage(ASN_RRC_DL_CCCH_Message *msg)
{
    if (msg->message.present != ASN_RRC_DL_CCCH_MessageType_PR_c1)
        return;

    auto &c1 = msg->message.choice.c1;
    switch (c1->present)
    {
    case ASN_RRC_DL_CCCH_MessageType__c1_PR_rrcReject:
        receiveRrcReject(*c1->choice.rrcReject);
        break;
    case ASN_RRC_DL_CCCH_MessageType__c1_PR_rrcSetup:
        receiveRrcSetup(*c1->choice.rrcSetup);
        break;
    default:
        break;
    }
}

void UeRrcTask::receiveRrcMessage(ASN_RRC_DL_DCCH_Message *msg)
{
    if (msg->message.present != ASN_RRC_DL_DCCH_MessageType_PR_c1)
        return;

    auto &c1 = msg->message.choice.c1;
    switch (c1->present)
    {
    case ASN_RRC_DL_DCCH_MessageType__c1_PR_dlInformationTransfer: {
        receiveDownlinkInformationTransfer(*c1->choice.dlInformationTransfer);
        break;
    default:
        break;
    }
    }
}

void UeRrcTask::receiveRrcMessage(ASN_RRC_PCCH_Message *msg)
{
    // TODO
}

void UeRrcTask::receiveRrcMessage(ASN_RRC_UL_CCCH_Message *msg)
{
    if (msg->message.present != ASN_RRC_UL_CCCH_MessageType_PR_c1)
        return;

    auto &c1 = msg->message.choice.c1;
    switch (c1->present)
    {
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_NOTHING:
        break;
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcSetupRequest:
        break; // todo
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcResumeRequest:
        break; // todo
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcReestablishmentRequest:
        break; // todo
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcSystemInfoRequest:
        break; // todo
    }
}

void UeRrcTask::receiveRrcMessage(ASN_RRC_UL_CCCH1_Message *msg)
{
    // TODO
}

void UeRrcTask::receiveRrcMessage(ASN_RRC_UL_DCCH_Message *msg)
{
}

} // namespace nr::ue
