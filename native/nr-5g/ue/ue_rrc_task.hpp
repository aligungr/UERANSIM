//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <logger.hpp>
#include <memory>
#include <nts.hpp>
#include <thread>
#include <unordered_map>
#include <vector>

#include "ue_nts.hpp"
#include "ue_types.hpp"

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
}

namespace nr::ue
{

class UeRrcTask : public NtsTask
{
  private:
    TaskBase *base;
    std::unique_ptr<Logger> logger;

  public:
    explicit UeRrcTask(TaskBase *base);
    ~UeRrcTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    /* Handle NTS messages */
    void handleDownlinkRrc(NwUeDownlinkRrc *msg);

    /* NAS transport */
    void deliverUplinkNas(OctetString &&nasPdu);

    /* Procedure handlers */
    void receiveDownlinkInformationTransfer(ASN_RRC_DLInformationTransfer *msg);

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
