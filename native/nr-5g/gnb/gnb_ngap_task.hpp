//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <logger.hpp>
#include <nts.hpp>
#include <optional>
#include <unordered_map>

#include "gnb_config.hpp"
#include "gnb_ngap_types.hpp"
#include "gnb_rrc_task.hpp"
#include "gnb_sctp_task.hpp"

struct ASN_NGAP_NGAP_PDU;
struct ASN_NGAP_NGSetupResponse;
struct ASN_NGAP_NGSetupFailure;
struct ASN_NGAP_ErrorIndication;
struct ASN_NGAP_DownlinkNASTransport;
struct ASN_NGAP_RerouteNASRequest;

namespace nr::gnb
{

class NgapTask : public NtsTask
{
  private:
    GnbConfig *config;
    std::unique_ptr<logger::Logger> logger;

    std::unordered_map<int, NgapAmfContext *> amfContexts;
    std::unordered_map<int, NgapUeContext *> ueContexts;
    long ueNgapIdCounter;

    int waitingSctpClients;

    SctpTask *sctpTask;
    GnbRrcTask *rrcTask;

  public:
    explicit NgapTask(GnbConfig *config, logger::LogBase &loggerBase);
    ~NgapTask() override = default;
    void setExternalTasks(SctpTask *sctpTask, GnbRrcTask *rrcTask);

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    /* Utility functions */
    void createAmfContext(const GnbAmfConfig &config);
    NgapAmfContext *findAmfContext(int ctxId);
    void createUeContext(int ueId);
    NgapUeContext *findUeContext(int ctxId);
    NgapUeContext *findUeByRanId(long ranUeNgapId);
    NgapUeContext *findUeByAmfId(long amfUeNgapId);
    NgapUeContext *findUeByNgapIdPair(const NgapIdPair &idPair);
    NgapAmfContext *selectNewAmfForReAllocation(int initiatedAmfId, int amfSetId);

    /* Interface management */
    void sendNgSetupRequest(int amfId);
    void sendErrorIndication(int amfId, NgapCause cause = NgapCause::CauseProtocol_unspecified, int ueId = 0);
    void receiveNgSetupResponse(int amfId, ASN_NGAP_NGSetupResponse *msg);
    void receiveNgSetupFailure(int amfId, ASN_NGAP_NGSetupFailure *msg);
    void receiveErrorIndication(int amfId, ASN_NGAP_ErrorIndication *msg);
    void receiveAssociationSetup(NwSctpAssociationSetup *msg);

    /* Message transport */
    void sendNgapNonUe(int amfId, ASN_NGAP_NGAP_PDU *pdu);
    void sendNgapUeAssociated(int ueId, ASN_NGAP_NGAP_PDU *pdu);
    void receiveSctpMessage(NwSctpClientReceive *msg);

    /* NAS transport */
    void receiveInitialNasTransport(NwInitialNasTransport *msg);
    void receiveUplinkNasTransport(int ueId, const OctetString &nasPdu);
    void receiveDownlinkNasTransport(ASN_NGAP_DownlinkNASTransport *msg);
    void deliverDownlinkNas(int ueId, OctetString &&nasPdu);
    void sendNasNonDeliveryIndication(int ueId, const OctetString &nasPdu, NgapCause cause);
    void receiveRerouteNasRequest(int amfId, ASN_NGAP_RerouteNASRequest *msg);
};

} // namespace nr::gnb