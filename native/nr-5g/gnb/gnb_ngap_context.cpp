//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_task.hpp"
#include "gnb_ngap_utils.hpp"
#include "gnb_gtp_task.hpp"

#include <ASN_NGAP_AMF-UE-NGAP-ID.h>
#include <ASN_NGAP_InitialContextSetupRequest.h>
#include <ASN_NGAP_InitialContextSetupResponse.h>
#include <ASN_NGAP_ProtocolIE-Field.h>
#include <ASN_NGAP_UE-NGAP-ID-pair.h>
#include <ASN_NGAP_UE-NGAP-IDs.h>
#include <ASN_NGAP_UEAggregateMaximumBitRate.h>
#include <ASN_NGAP_UEContextModificationRequest.h>
#include <ASN_NGAP_UEContextModificationResponse.h>
#include <ASN_NGAP_UEContextReleaseCommand.h>
#include <ASN_NGAP_UEContextReleaseComplete.h>
#include <ASN_NGAP_UESecurityCapabilities.h>

namespace nr::gnb
{

void NgapTask::receiveInitialContextSetup(int amfId, ASN_NGAP_InitialContextSetupRequest *msg)
{
    logger->debug("Initial Context Setup Request received");

    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPair(msg));
    if (ue == nullptr)
        return;

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_UEAggregateMaximumBitRate);
    if (ie)
    {
        ue->ueAmbr.dlAmbr = asn::GetUnsigned64(ie->UEAggregateMaximumBitRate.uEAggregateMaximumBitRateDL);
        ue->ueAmbr.ulAmbr = asn::GetUnsigned64(ie->UEAggregateMaximumBitRate.uEAggregateMaximumBitRateUL);
    }

    auto *response = asn::ngap::NewMessagePdu<ASN_NGAP_InitialContextSetupResponse>({});
    sendNgapUeAssociated(ue->ctxId, response);

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_NAS_PDU);
    if (ie)
        deliverDownlinkNas(ue->ctxId, asn::GetOctetString(ie->NAS_PDU));

    base->gtpTask->push(new NwUeContextUpdate(ue->ctxId, true, ue->ueAmbr));
}

void NgapTask::receiveContextRelease(int amfId, ASN_NGAP_UEContextReleaseCommand *msg)
{
    logger->debug("UE Context Release Command received");

    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPairFromUeNgapIds(msg));
    if (ue == nullptr)
        return;

    // todo: NG-RAN node shall release all related signalling and user data transport resources
    // ...

    auto *response = asn::ngap::NewMessagePdu<ASN_NGAP_UEContextReleaseComplete>({});
    sendNgapUeAssociated(ue->ctxId, response);

    deleteUeContext(ue->ctxId);
}

void NgapTask::receiveContextModification(int amfId, ASN_NGAP_UEContextModificationRequest *msg)
{
    logger->debug("UE Context Modification Request received");

    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPair(msg));
    if (ue == nullptr)
        return;

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_UEAggregateMaximumBitRate);
    if (ie)
    {
        ue->ueAmbr.dlAmbr = asn::GetUnsigned64(ie->UEAggregateMaximumBitRate.uEAggregateMaximumBitRateDL);
        ue->ueAmbr.ulAmbr = asn::GetUnsigned64(ie->UEAggregateMaximumBitRate.uEAggregateMaximumBitRateUL);
    }

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_NewAMF_UE_NGAP_ID);
    if (ie)
    {
        int64_t old = ue->amfUeNgapId;
        ue->amfUeNgapId = asn::GetSigned64(ie->AMF_UE_NGAP_ID_1);
        logger->debug("AMF-UE-NGAP-ID changed from %ld to %ld", old, ue->amfUeNgapId);
    }

    auto *response = asn::ngap::NewMessagePdu<ASN_NGAP_UEContextModificationResponse>({});
    sendNgapUeAssociated(ue->ctxId, response);

    base->gtpTask->push(new NwUeContextUpdate(ue->ctxId, false, ue->ueAmbr));
}

} // namespace nr::gnb