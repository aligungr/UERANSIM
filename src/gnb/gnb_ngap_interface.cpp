//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_app_task.hpp"
#include "gnb_ngap_task.hpp"
#include "gnb_ngap_utils.hpp"

#include <ASN_NGAP_AMFName.h>
#include <ASN_NGAP_BroadcastPLMNItem.h>
#include <ASN_NGAP_ErrorIndication.h>
#include <ASN_NGAP_GlobalGNB-ID.h>
#include <ASN_NGAP_InitiatingMessage.h>
#include <ASN_NGAP_NGAP-PDU.h>
#include <ASN_NGAP_NGSetupRequest.h>
#include <ASN_NGAP_PLMNSupportItem.h>
#include <ASN_NGAP_ProtocolIE-Field.h>
#include <ASN_NGAP_ServedGUAMIItem.h>
#include <ASN_NGAP_SliceSupportItem.h>
#include <ASN_NGAP_SupportedTAItem.h>

namespace nr::gnb
{

void NgapTask::handleAssociationSetup(NwSctpAssociationSetup *msg)
{
    logger->debug("SCTP association setup received ascId[%d]", msg->associationId);

    auto *amf = findAmfContext(msg->clientId);
    if (amf != nullptr)
    {
        amf->association.associationId = msg->associationId;
        amf->association.inStreams = msg->inStreams;
        amf->association.outStreams = msg->outStreams;

        waitingSctpClients--;
        if (waitingSctpClients == 0)
        {
            auto *update = new NwGnbStatusUpdate(NwGnbStatusUpdate::INITIAL_SCTP_ESTABLISHED);
            update->isInitialSctpEstablished = true;
            base->appTask->push(update);

            base->mrTask->push(new NwGnbN1Ready());
        }

        sendNgSetupRequest(amf->ctxId);

        delete msg;
    }
}

void NgapTask::sendNgSetupRequest(int amfId)
{
    logger->debug("Sending NG Setup Request");

    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    amf->state = EAmfState::WAITING_NG_SETUP;

    // TODO: this procedure also re-initialises the NGAP UE-related contexts (if any)
    //  and erases all related signalling connections in the two nodes like an NG Reset procedure would do.
    //  More on 38.413 8.7.1.1

    auto *globalGnbId = asn::New<ASN_NGAP_GlobalGNB_ID>();
    globalGnbId->gNB_ID.present = ASN_NGAP_GNB_ID_PR_gNB_ID;
    asn::SetBitString(globalGnbId->gNB_ID.choice.gNB_ID, octet4{base->config->getGnbId()});
    asn::SetOctetString3(globalGnbId->pLMNIdentity, ngap_utils::PlmnToOctet3(base->config->plmn));

    auto *ieGlobalGnbId = asn::New<ASN_NGAP_NGSetupRequestIEs>();
    ieGlobalGnbId->id = ASN_NGAP_ProtocolIE_ID_id_GlobalRANNodeID;
    ieGlobalGnbId->criticality = ASN_NGAP_Criticality_reject;
    ieGlobalGnbId->value.present = ASN_NGAP_NGSetupRequestIEs__value_PR_GlobalRANNodeID;
    ieGlobalGnbId->value.choice.GlobalRANNodeID.present = ASN_NGAP_GlobalRANNodeID_PR_globalGNB_ID;
    ieGlobalGnbId->value.choice.GlobalRANNodeID.choice.globalGNB_ID = globalGnbId;

    auto *ieRanNodeName = asn::New<ASN_NGAP_NGSetupRequestIEs>();
    ieRanNodeName->id = ASN_NGAP_ProtocolIE_ID_id_RANNodeName;
    ieRanNodeName->criticality = ASN_NGAP_Criticality_ignore;
    ieRanNodeName->value.present = ASN_NGAP_NGSetupRequestIEs__value_PR_RANNodeName;
    asn::SetPrintableString(ieRanNodeName->value.choice.RANNodeName, base->config->name);

    auto *broadcastPlmn = asn::New<ASN_NGAP_BroadcastPLMNItem>();
    asn::SetOctetString3(broadcastPlmn->pLMNIdentity, ngap_utils::PlmnToOctet3(base->config->plmn));
    for (auto &nssai : base->config->nssais)
    {
        auto *item = asn::New<ASN_NGAP_SliceSupportItem>();
        asn::SetOctetString1(item->s_NSSAI.sST, static_cast<uint8_t>(nssai.sst));
        if (nssai.sd.has_value())
        {
            item->s_NSSAI.sD = asn::New<ASN_NGAP_SD_t>();
            asn::SetOctetString3(*item->s_NSSAI.sD, octet3{nssai.sd.value()});
        }
        asn::SequenceAdd(broadcastPlmn->tAISliceSupportList, item);
    }

    auto *supportedTa = asn::New<ASN_NGAP_SupportedTAItem>();
    asn::SetOctetString3(supportedTa->tAC, octet3{base->config->tac});
    asn::SequenceAdd(supportedTa->broadcastPLMNList, broadcastPlmn);

    auto *ieSupportedTaList = asn::New<ASN_NGAP_NGSetupRequestIEs>();
    ieSupportedTaList->id = ASN_NGAP_ProtocolIE_ID_id_SupportedTAList;
    ieSupportedTaList->criticality = ASN_NGAP_Criticality_reject;
    ieSupportedTaList->value.present = ASN_NGAP_NGSetupRequestIEs__value_PR_SupportedTAList;
    asn::SequenceAdd(ieSupportedTaList->value.choice.SupportedTAList, supportedTa);

    auto *iePagingDrx = asn::New<ASN_NGAP_NGSetupRequestIEs>();
    iePagingDrx->id = ASN_NGAP_ProtocolIE_ID_id_DefaultPagingDRX;
    iePagingDrx->criticality = ASN_NGAP_Criticality_ignore;
    iePagingDrx->value.present = ASN_NGAP_NGSetupRequestIEs__value_PR_PagingDRX;
    iePagingDrx->value.choice.PagingDRX = ngap_utils::PagingDrxToAsn(base->config->pagingDrx);

    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_NGSetupRequest>(
        {ieGlobalGnbId, ieRanNodeName, ieSupportedTaList, iePagingDrx});

    sendNgapNonUe(amfId, pdu);
}

void NgapTask::receiveNgSetupResponse(int amfId, ASN_NGAP_NGSetupResponse *msg)
{
    logger->debug("NG Setup Response received");

    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMFName);
    if (ie)
        amf->amfName = asn::GetPrintableString(ie->AMFName);

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_RelativeAMFCapacity);
    if (ie)
        amf->relativeCapacity = ie->RelativeAMFCapacity;

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_ServedGUAMIList);
    if (ie)
    {
        utils::ClearAndDelete(amf->servedGuamiList);

        asn::ForeachItem(ie->ServedGUAMIList, [amf](ASN_NGAP_ServedGUAMIItem &item) {
            auto servedGuami = new ServedGuami();
            if (item.backupAMFName)
                servedGuami->backupAmfName = asn::GetPrintableString(*item.backupAMFName);
            ngap_utils::GuamiFromAsn_Ref(item.gUAMI, servedGuami->guami);
            amf->servedGuamiList.push_back(servedGuami);
        });
    }

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_PLMNSupportList);
    if (ie)
    {
        utils::ClearAndDelete(amf->plmnSupportList);

        asn::ForeachItem(ie->PLMNSupportList, [amf](ASN_NGAP_PLMNSupportItem &item) {
            auto plmnSupport = new PlmnSupport();
            ngap_utils::PlmnFromAsn_Ref(item.pLMNIdentity, plmnSupport->plmn);
            asn::ForeachItem(item.sliceSupportList, [plmnSupport](ASN_NGAP_SliceSupportItem &ssItem) {
                plmnSupport->sliceSupportList.push_back(ngap_utils::SliceSupportFromAsn_Unique(ssItem));
            });
            amf->plmnSupportList.push_back(plmnSupport);
        });
    }

    amf->state = EAmfState::CONNECTED;
    logger->info("NG Setup procedure is successful");
}

void NgapTask::receiveNgSetupFailure(int amfId, ASN_NGAP_NGSetupFailure *msg)
{
    logger->debug("NG Setup Failure received");

    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    amf->state = EAmfState::WAITING_NG_SETUP;

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_Cause);
    if (ie)
        logger->err("NG Setup procedure is failed. Cause: %s", ngap_utils::CauseToString(ie->Cause).c_str());
    else
        logger->err("NG Setup procedure is failed.");
}

void NgapTask::receiveErrorIndication(int amfId, ASN_NGAP_ErrorIndication *msg)
{
    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
    {
        logger->err("Error indication received with not found AMF context");
        return;
    }

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_Cause);
    if (ie)
        logger->err("Error indication received. Cause: %s", ngap_utils::CauseToString(ie->Cause).c_str());
    else
        logger->err("Error indication received.");
}

void NgapTask::sendErrorIndication(int amfId, NgapCause cause, int ueId)
{
    auto ieCause = asn::New<ASN_NGAP_ErrorIndicationIEs>();
    ieCause->id = ASN_NGAP_ProtocolIE_ID_id_Cause;
    ieCause->criticality = ASN_NGAP_Criticality_ignore;
    ieCause->value.present = ASN_NGAP_ErrorIndicationIEs__value_PR_Cause;
    ngap_utils::ToCauseAsn_Ref(cause, ieCause->value.choice.Cause);

    logger->debug("Sending an error indication with cause: %s",
                  ngap_utils::CauseToString(ieCause->value.choice.Cause).c_str());

    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_ErrorIndication>({ieCause});

    if (ueId > 0)
        sendNgapUeAssociated(ueId, pdu);
    else
        sendNgapNonUe(amfId, pdu);
}

} // namespace nr::gnb