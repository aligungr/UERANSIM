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

#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>

#include <asn/rrc/ASN_RRC_InitialUE-Identity.h>

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
    struct ASN_RRC_RRCRelease;
    struct ASN_RRC_Paging;
    struct ASN_RRC_MIB;
    struct ASN_RRC_SIB1;
}

namespace nr::ue
{

class UeRrcTask : public NtsTask
{
  private:
    TaskBase *m_base;
    std::unique_ptr<Logger> m_logger;
    int64_t m_startedTime;
    ERrcState m_state;
    std::unordered_map<int, UeCellDesc> m_cellDesc{};

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
    void handleDownlinkRrc(int cellId, rrc::RrcChannel channel, const OctetString &pdu);
    void deliverInitialNas(OctetString &&nasPdu, long establishmentCause);
    void deliverUplinkNas(OctetString &&nasPdu);

    void receiveRrcSetup(const ASN_RRC_RRCSetup &msg);
    void receiveRrcReject(const ASN_RRC_RRCReject &msg);
    void receiveRrcRelease(const ASN_RRC_RRCRelease &msg);
    void receiveDownlinkInformationTransfer(const ASN_RRC_DLInformationTransfer &msg);
    void receivePaging(const ASN_RRC_Paging &msg);

    void handleRadioLinkFailure();

    /* RRC Message Transmission and Receive */
    void sendRrcMessage(ASN_RRC_UL_CCCH_Message *msg);
    void sendRrcMessage(ASN_RRC_UL_CCCH1_Message *msg);
    void sendRrcMessage(ASN_RRC_UL_DCCH_Message *msg);
    void receiveRrcMessage(int cellId, ASN_RRC_BCCH_BCH_Message *msg);
    void receiveRrcMessage(int cellId, ASN_RRC_BCCH_DL_SCH_Message *msg);
    void receiveRrcMessage(int cellId, ASN_RRC_DL_CCCH_Message *msg); // TODO
    void receiveRrcMessage(int cellId, ASN_RRC_DL_DCCH_Message *msg); //  ..
    void receiveRrcMessage(int cellId, ASN_RRC_PCCH_Message *msg);    //  Broadcast vs için camped olmayanları ignore

    /* Service Access Point */
    void handleRlsSapMessage(NwUeRlsToRrc &msg);
    void handleNasSapMessage(NwUeNasToRrc &msg);

    /* State Management */
    void triggerCycle();
    void performCycle();

    /* Idle Mode Operations */
    void performCellSelection();
    bool lookForSuitableCell(CurrentCellInfo &cellInfo, CellSelectionReport &report);
    bool lookForAcceptableCell(CurrentCellInfo &cellInfo, CellSelectionReport &report);

    /* Cell Management */
    void handleCellSignalChange(int cellId, int dbm);
    void notifyCellDetected(int cellId, int dbm);
    void notifyCellLost(int cellId);
    bool hasSignalToCell(int cellId);
    void updateAvailablePlmns();

    /* System Information */
    void receiveMib(int cellId, const ASN_RRC_MIB &msg);
    void receiveSib1(int cellId, const ASN_RRC_SIB1 &msg);
};

} // namespace nr::ue
