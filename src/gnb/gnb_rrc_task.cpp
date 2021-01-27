//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_rrc_task.hpp"
#include "gnb_nts.hpp"

#include <rrc_encode.hpp>

#include <ASN_RRC_BCCH-BCH-Message.h>
#include <ASN_RRC_BCCH-DL-SCH-Message.h>
#include <ASN_RRC_DL-CCCH-Message.h>
#include <ASN_RRC_DL-DCCH-Message.h>
#include <ASN_RRC_PCCH-Message.h>
#include <ASN_RRC_UL-CCCH-Message.h>
#include <ASN_RRC_UL-CCCH1-Message.h>
#include <ASN_RRC_UL-DCCH-Message.h>

namespace nr::gnb
{

GnbRrcTask::GnbRrcTask(TaskBase *base) : base{base}
{
    logger = base->logBase->makeUniqueLogger("gnb-rrc");
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
    case NtsMessageType::NGAP_DOWNLINK_NAS_DELIVERY:
        handleDownlinkNasDelivery(dynamic_cast<NwDownlinkNasDelivery *>(msg));
        break;
    case NtsMessageType::GNB_MR_UPLINK_RRC:
        handleUplinkRrc(dynamic_cast<NwGnbUplinkRrc *>(msg));
        break;
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

void GnbRrcTask::handleUplinkRrc(NwGnbUplinkRrc *msg)
{
    switch (msg->channel)
    {
    case rrc::RrcChannel::BCCH_BCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_BCCH_BCH_Message>(asn_DEF_ASN_RRC_BCCH_BCH_Message, msg->rrcPdu);
        if (pdu == nullptr)
            logger->err("RRC BCCH-BCH PDU decoding failed.");
        else
            receiveRrcMessage(msg->ueId, pdu);
        asn::Free(asn_DEF_ASN_RRC_BCCH_BCH_Message, pdu);
        break;
    }
    case rrc::RrcChannel::BCCH_DL_SCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_BCCH_DL_SCH_Message>(asn_DEF_ASN_RRC_BCCH_DL_SCH_Message, msg->rrcPdu);
        if (pdu == nullptr)
            logger->err("RRC BCCH-DL-SCH PDU decoding failed.");
        else
            receiveRrcMessage(msg->ueId, pdu);
        asn::Free(asn_DEF_ASN_RRC_BCCH_DL_SCH_Message, pdu);
        break;
    };
    case rrc::RrcChannel::DL_CCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_DL_CCCH_Message>(asn_DEF_ASN_RRC_DL_CCCH_Message, msg->rrcPdu);
        if (pdu == nullptr)
            logger->err("RRC DL-CCCH PDU decoding failed.");
        else
            receiveRrcMessage(msg->ueId, pdu);
        asn::Free(asn_DEF_ASN_RRC_DL_CCCH_Message, pdu);
        break;
    };
    case rrc::RrcChannel::DL_DCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_DL_DCCH_Message>(asn_DEF_ASN_RRC_DL_DCCH_Message, msg->rrcPdu);
        if (pdu == nullptr)
            logger->err("RRC DL-DCCH PDU decoding failed.");
        else
            receiveRrcMessage(msg->ueId, pdu);
        asn::Free(asn_DEF_ASN_RRC_DL_DCCH_Message, pdu);
        break;
    };
    case rrc::RrcChannel::PCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_PCCH_Message>(asn_DEF_ASN_RRC_PCCH_Message, msg->rrcPdu);
        if (pdu == nullptr)
            logger->err("RRC PCCH PDU decoding failed.");
        else
            receiveRrcMessage(msg->ueId, pdu);
        asn::Free(asn_DEF_ASN_RRC_PCCH_Message, pdu);
        break;
    };
    case rrc::RrcChannel::UL_CCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_UL_CCCH_Message>(asn_DEF_ASN_RRC_UL_CCCH_Message, msg->rrcPdu);
        if (pdu == nullptr)
            logger->err("RRC UL-CCCH PDU decoding failed.");
        else
            receiveRrcMessage(msg->ueId, pdu);
        asn::Free(asn_DEF_ASN_RRC_UL_CCCH_Message, pdu);
        break;
    };
    case rrc::RrcChannel::UL_CCCH1: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_UL_CCCH1_Message>(asn_DEF_ASN_RRC_UL_CCCH1_Message, msg->rrcPdu);
        if (pdu == nullptr)
            logger->err("RRC UL-CCCH1 PDU decoding failed.");
        else
            receiveRrcMessage(msg->ueId, pdu);
        asn::Free(asn_DEF_ASN_RRC_UL_CCCH1_Message, pdu);
        break;
    };
    case rrc::RrcChannel::UL_DCCH: {
        auto *pdu = rrc::encode::Decode<ASN_RRC_UL_DCCH_Message>(asn_DEF_ASN_RRC_UL_DCCH_Message, msg->rrcPdu);
        if (pdu == nullptr)
            logger->err("RRC UL-DCCH PDU decoding failed.");
        else
            receiveRrcMessage(msg->ueId, pdu);
        asn::Free(asn_DEF_ASN_RRC_UL_DCCH_Message, pdu);
        break;
    };
    }

    delete msg;
}

} // namespace nr::gnb
