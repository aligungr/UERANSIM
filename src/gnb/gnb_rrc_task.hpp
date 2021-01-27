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

#include "gnb_nts.hpp"

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
    struct ASN_RRC_ULInformationTransfer;
}

namespace nr::gnb
{

class NgapTask;
class GnbMrTask;

class GnbRrcTask : public NtsTask
{
  private:
    TaskBase *base;
    std::unique_ptr<Logger> logger;

  public:
    explicit GnbRrcTask(TaskBase *base);
    ~GnbRrcTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    /* Handle NTS messages */
    void handleUplinkRrc(NwGnbUplinkRrc *msg);

    /* NAS transport */
    void handleDownlinkNasDelivery(NwDownlinkNasDelivery *msg);
    void deliverUplinkNas(int ueId, OctetString &&nasPdu);

    /* Procedure handlers */
    void receiveUplinkInformationTransfer(int ueId, ASN_RRC_ULInformationTransfer *msg);
    void receiveRrcSetupRequest(int ueId, ASN_RRC_RRCSetupRequest *msg);

    /* RRC channel send message */
    void sendRrcMessage(int ueId, ASN_RRC_BCCH_BCH_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_BCCH_DL_SCH_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_DL_CCCH_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_DL_DCCH_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_PCCH_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_UL_CCCH_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_UL_CCCH1_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_UL_DCCH_Message *msg);

    /* RRC channel receive message */
    void receiveRrcMessage(int ueId, ASN_RRC_BCCH_BCH_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_BCCH_DL_SCH_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_DL_CCCH_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_DL_DCCH_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_PCCH_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_UL_CCCH_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_UL_CCCH1_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_UL_DCCH_Message *msg);
};

} // namespace nr::gnb
