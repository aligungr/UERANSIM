//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <thread>
#include <unordered_map>
#include <vector>

#include <gnb/nts.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>

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
    struct ASN_RRC_RRCSetupComplete;
    struct ASN_RRC_ULInformationTransfer;
}

namespace nr::gnb
{

class NgapTask;

class GnbRrcTask : public NtsTask
{
  private:
    TaskBase *m_base;
    GnbConfig *m_config;
    std::unique_ptr<Logger> m_logger;

    std::unordered_map<int, RrcUeContext *> m_ueCtx;
    int m_tidCounter;

    bool m_isBarred = true;
    bool m_cellReserved = false;
    UacAiBarringSet m_aiBarringSet = {};
    bool m_intraFreqReselectAllowed = true;

    friend class GnbCmdHandler;

  public:
    explicit GnbRrcTask(TaskBase *base);
    ~GnbRrcTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    /* Management */
    int getNextTid();

    /* Handlers */
    void handleUplinkRrc(int ueId, rrc::RrcChannel channel, const OctetString &rrcPdu);
    void handleDownlinkNasDelivery(int ueId, const OctetString &nasPdu);
    void deliverUplinkNas(int ueId, OctetString &&nasPdu);
    void releaseConnection(int ueId);
    void handleRadioLinkFailure(int ueId);
    void handlePaging(const asn::Unique<ASN_NGAP_FiveG_S_TMSI> &tmsi,
                      const asn::Unique<ASN_NGAP_TAIListForPaging> &taiList);

    void receiveUplinkInformationTransfer(int ueId, const ASN_RRC_ULInformationTransfer &msg);

    /* RRC channel send message */
    void sendRrcMessage(ASN_RRC_BCCH_BCH_Message *msg);
    void sendRrcMessage(ASN_RRC_BCCH_DL_SCH_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_DL_CCCH_Message *msg);
    void sendRrcMessage(int ueId, ASN_RRC_DL_DCCH_Message *msg);
    void sendRrcMessage(ASN_RRC_PCCH_Message *msg);

    /* RRC channel receive message */
    void receiveRrcMessage(int ueId, ASN_RRC_BCCH_BCH_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_UL_CCCH_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_UL_CCCH1_Message *msg);
    void receiveRrcMessage(int ueId, ASN_RRC_UL_DCCH_Message *msg);

    /* System Information Broadcast related */
    void onBroadcastTimerExpired();
    void triggerSysInfoBroadcast();

    /* Service Access Point */
    void handleRlsSapMessage(NmGnbRlsToRrc &msg);

    /* UE Management */
    RrcUeContext *createUe(int id);
    RrcUeContext *tryFindUe(int id);
    RrcUeContext *findUe(int id);

    /* Connection Control */
    void receiveRrcSetupRequest(int ueId, const ASN_RRC_RRCSetupRequest &msg);
    void receiveRrcSetupComplete(int ueId, const ASN_RRC_RRCSetupComplete &msg);
};

} // namespace nr::gnb
