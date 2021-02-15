//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <asn/rrc/ASN_RRC_InitialUE-Identity.h>
#include <memory>
#include <thread>
#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <unordered_map>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <vector>

extern "C"
{
    struct ASN_RRC_BCCH_BCH_Message;
    struct ASN_RRC_BCCH_DL_SCH_Message;
    struct ASN_RRC_DL_CCCH_Message;
    struct ASN_RRC_DL_DCCH_Message;
    struct ASN_RRC_PCCH_Message;
    struct ASN_RRC_UL_CCCH_Message;
    struct ASN_RRC_UL_CCCH1_Message;
    struct ASN_RRC_UL_DCCH_Message;

    struct ASN_RRC_RRCSetupRequest;
    struct ASN_RRC_DLInformationTransfer;
    struct ASN_RRC_ULInformationTransfer;
    struct ASN_RRC_RRCSetup;
    struct ASN_RRC_RRCReject;
}

namespace nr::ue
{

class UeRrcTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;

    ERrcState m_state;
    ERrcLastSetupRequest m_lastSetupReq{};

    ASN_RRC_InitialUE_Identity_t m_initialId{};
    OctetString m_initialNasPdu{};

    friend class UeCmdHandler;

  public:
    explicit UeRrcTask(TaskBase *base);
    ~UeRrcTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    /* Handlers */
    void handleDownlinkRrc(rrc::RrcChannel channel, const OctetString& pdu);
    void deliverInitialNas(OctetString &&nasPdu, long establishmentCause);
    void deliverUplinkNas(OctetString &&nasPdu);

    void receiveRrcSetup(const ASN_RRC_RRCSetup &msg);
    void receiveRrcReject(const ASN_RRC_RRCReject &msg);
    void receiveDownlinkInformationTransfer(const ASN_RRC_DLInformationTransfer &msg);

    /* RRC channel send message */
    void sendRrcMessage(ASN_RRC_BCCH_BCH_Message *msg);
    void sendRrcMessage(ASN_RRC_BCCH_DL_SCH_Message *msg);
    void sendRrcMessage(ASN_RRC_DL_CCCH_Message *msg);
    void sendRrcMessage(ASN_RRC_DL_DCCH_Message *msg);
    void sendRrcMessage(ASN_RRC_PCCH_Message *msg);
    void sendRrcMessage(ASN_RRC_UL_CCCH_Message *msg);
    void sendRrcMessage(ASN_RRC_UL_CCCH1_Message *msg);
    void sendRrcMessage(ASN_RRC_UL_DCCH_Message *msg);

    /* RRC channel receive message */
    void receiveRrcMessage(ASN_RRC_BCCH_BCH_Message *msg);
    void receiveRrcMessage(ASN_RRC_BCCH_DL_SCH_Message *msg);
    void receiveRrcMessage(ASN_RRC_DL_CCCH_Message *msg);
    void receiveRrcMessage(ASN_RRC_DL_DCCH_Message *msg);
    void receiveRrcMessage(ASN_RRC_PCCH_Message *msg);
    void receiveRrcMessage(ASN_RRC_UL_CCCH_Message *msg);
    void receiveRrcMessage(ASN_RRC_UL_CCCH1_Message *msg);
    void receiveRrcMessage(ASN_RRC_UL_DCCH_Message *msg);
};

} // namespace nr::ue
