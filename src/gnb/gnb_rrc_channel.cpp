//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_mr_task.hpp"
#include "gnb_rrc_task.hpp"

#include <rrc_encode.hpp>

#include <ASN_RRC_UL-CCCH-Message.h>
#include <ASN_RRC_UL-DCCH-Message.h>

namespace nr::gnb
{

void GnbRrcTask::sendRrcMessage(int ueId, ASN_RRC_BCCH_BCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_BCCH_BCH_Message, msg);
    if (pdu.length() == 0)
    {
        logger->err("RRC BCCH-BCH encoding failed.");
        return;
    }
    base->mrTask->push(new NwGnbDownlinkRrc(ueId, rrc::RrcChannel::BCCH_BCH, std::move(pdu)));
}

void GnbRrcTask::sendRrcMessage(int ueId, ASN_RRC_BCCH_DL_SCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_BCCH_DL_SCH_Message, msg);
    if (pdu.length() == 0)
    {
        logger->err("RRC BCCH-DL-SCH encoding failed.");
        return;
    }
    base->mrTask->push(new NwGnbDownlinkRrc(ueId, rrc::RrcChannel::BCCH_DL_SCH, std::move(pdu)));
}

void GnbRrcTask::sendRrcMessage(int ueId, ASN_RRC_DL_CCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_DL_CCCH_Message, msg);
    if (pdu.length() == 0)
    {
        logger->err("RRC DL-CCCH encoding failed.");
        return;
    }
    base->mrTask->push(new NwGnbDownlinkRrc(ueId, rrc::RrcChannel::DL_CCCH, std::move(pdu)));
}

void GnbRrcTask::sendRrcMessage(int ueId, ASN_RRC_DL_DCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_DL_DCCH_Message, msg);
    if (pdu.length() == 0)
    {
        logger->err("RRC DL-DCCH encoding failed.");
        return;
    }
    base->mrTask->push(new NwGnbDownlinkRrc(ueId, rrc::RrcChannel::DL_DCCH, std::move(pdu)));
}

void GnbRrcTask::sendRrcMessage(int ueId, ASN_RRC_PCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_PCCH_Message, msg);
    if (pdu.length() == 0)
    {
        logger->err("RRC PCCH encoding failed.");
        return;
    }
    base->mrTask->push(new NwGnbDownlinkRrc(ueId, rrc::RrcChannel::PCCH, std::move(pdu)));
}

void GnbRrcTask::sendRrcMessage(int ueId, ASN_RRC_UL_CCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_UL_CCCH_Message, msg);
    if (pdu.length() == 0)
    {
        logger->err("RRC UL-CCCH encoding failed.");
        return;
    }
    base->mrTask->push(new NwGnbDownlinkRrc(ueId, rrc::RrcChannel::UL_CCCH, std::move(pdu)));
}

void GnbRrcTask::sendRrcMessage(int ueId, ASN_RRC_UL_CCCH1_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_UL_CCCH1_Message, msg);
    if (pdu.length() == 0)
    {
        logger->err("RRC UL-CCCH1 encoding failed.");
        return;
    }
    base->mrTask->push(new NwGnbDownlinkRrc(ueId, rrc::RrcChannel::UL_CCCH1, std::move(pdu)));
}

void GnbRrcTask::sendRrcMessage(int ueId, ASN_RRC_UL_DCCH_Message *msg)
{
    OctetString pdu = rrc::encode::EncodeS(asn_DEF_ASN_RRC_UL_DCCH_Message, msg);
    if (pdu.length() == 0)
    {
        logger->err("RRC UL-DCCH encoding failed.");
        return;
    }
    base->mrTask->push(new NwGnbDownlinkRrc(ueId, rrc::RrcChannel::UL_DCCH, std::move(pdu)));
}

void GnbRrcTask::receiveRrcMessage(int ueId, ASN_RRC_BCCH_BCH_Message *msg)
{
    // TODO
}

void GnbRrcTask::receiveRrcMessage(int ueId, ASN_RRC_BCCH_DL_SCH_Message *msg)
{
    // TODO
}

void GnbRrcTask::receiveRrcMessage(int ueId, ASN_RRC_DL_CCCH_Message *msg)
{
    // TODO
}

void GnbRrcTask::receiveRrcMessage(int ueId, ASN_RRC_DL_DCCH_Message *msg)
{
    // TODO
}

void GnbRrcTask::receiveRrcMessage(int ueId, ASN_RRC_PCCH_Message *msg)
{
    // TODO
}

void GnbRrcTask::receiveRrcMessage(int ueId, ASN_RRC_UL_CCCH_Message *msg)
{
    if (msg->message.present != ASN_RRC_UL_CCCH_MessageType_PR_c1)
        return;

    auto &c1 = msg->message.choice.c1;
    switch (c1->present)
    {
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_NOTHING:
        return;
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcSetupRequest:
        receiveRrcSetupRequest(ueId, c1->choice.rrcSetupRequest);
        break;
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcResumeRequest:
        break; // todo
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcReestablishmentRequest:
        break; // todo
    case ASN_RRC_UL_CCCH_MessageType__c1_PR_rrcSystemInfoRequest:
        break; // todo
    }
}

void GnbRrcTask::receiveRrcMessage(int ueId, ASN_RRC_UL_CCCH1_Message *msg)
{
    // TODO
}

void GnbRrcTask::receiveRrcMessage(int ueId, ASN_RRC_UL_DCCH_Message *msg)
{
    if (msg->message.present != ASN_RRC_UL_DCCH_MessageType_PR_c1)
        return;

    auto &c1 = msg->message.choice.c1;
    switch (c1->present)
    {
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_NOTHING:
        return;
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_measurementReport:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_rrcReconfigurationComplete:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_rrcSetupComplete:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_rrcReestablishmentComplete:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_rrcResumeComplete:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_securityModeComplete:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_securityModeFailure:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_ulInformationTransfer:
        receiveUplinkInformationTransfer(ueId, c1->choice.ulInformationTransfer);
        break;
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_locationMeasurementIndication:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_ueCapabilityInformation:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_counterCheckResponse:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_ueAssistanceInformation:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_failureInformation:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_ulInformationTransferMRDC:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_scgFailureInformation:
        break; // TODO
    case ASN_RRC_UL_DCCH_MessageType__c1_PR_scgFailureInformationEUTRA:
        break; // TODO
    }
}

} // namespace nr::gnb