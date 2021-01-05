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
#include <unordered_map>

#include "gnb_config.hpp"
#include "gnb_ngap_types.hpp"
#include "gnb_sctp_task.hpp"

struct ASN_NGAP_NGAP_PDU;
struct ASN_NGAP_NGSetupResponse;
struct ASN_NGAP_NGSetupFailure;
struct ASN_NGAP_ErrorIndication;

namespace nr::gnb
{

class NgapTask : public NtsTask
{
  private:
    GnbConfig *config;
    std::unique_ptr<logger::Logger> logger;
    std::unordered_map<int, NgapAmfContext *> amfContexts;
    SctpTask *sctpTask;
    int waitingSctpClients;

  public:
    explicit NgapTask(GnbConfig *config, logger::LogBase &loggerBase);
    ~NgapTask() override = default;
    void setExternalTasks(SctpTask *sctpTask);

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    /* Utility functions */
    void createAmfContext(const GnbAmfConfig &config);
    NgapAmfContext *findAmfContext(int ctxId);

    /* Interface management */
    void sendNgSetupRequest(int amfId);
    void receiveNgSetupResponse(int amfId, ASN_NGAP_NGSetupResponse *msg);
    void receiveNgSetupFailure(int amfId, ASN_NGAP_NGSetupFailure *msg);
    void receiveErrorIndication(int amfId, ASN_NGAP_ErrorIndication *msg);
    void receiveAssociationSetup(NwSctpAssociationSetup *msg);

    /* Message transport */
    void sendNgapNonUe(int amfId, ASN_NGAP_NGAP_PDU *pdu);
    void sendNgapUeAssociated(int ueId, ASN_NGAP_NGAP_PDU *pdu);
};

} // namespace nr::gnb