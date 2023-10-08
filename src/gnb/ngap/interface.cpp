//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"
#include "utils.hpp"

#include <algorithm>

#include <gnb/app/task.hpp>
#include <gnb/rrc/task.hpp>
#include <gnb/sctp/task.hpp>

#include <asn/ngap/ASN_NGAP_AMFConfigurationUpdate.h>
#include <asn/ngap/ASN_NGAP_AMFConfigurationUpdateFailure.h>
#include <asn/ngap/ASN_NGAP_AMFName.h>
#include <asn/ngap/ASN_NGAP_BroadcastPLMNItem.h>
#include <asn/ngap/ASN_NGAP_ErrorIndication.h>
#include <asn/ngap/ASN_NGAP_GlobalGNB-ID.h>
#include <asn/ngap/ASN_NGAP_InitiatingMessage.h>
#include <asn/ngap/ASN_NGAP_NGAP-PDU.h>
#include <asn/ngap/ASN_NGAP_NGSetupRequest.h>
#include <asn/ngap/ASN_NGAP_OverloadStartNSSAIItem.h>
#include <asn/ngap/ASN_NGAP_PLMNSupportItem.h>
#include <asn/ngap/ASN_NGAP_ProtocolIE-Field.h>
#include <asn/ngap/ASN_NGAP_ServedGUAMIItem.h>
#include <asn/ngap/ASN_NGAP_SliceSupportItem.h>
#include <asn/ngap/ASN_NGAP_SupportedTAItem.h>

namespace nr::gnb
{

template <typename T>
static void AssignDefaultAmfConfigs(NgapAmfContext *amf, T *msg)
{
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
                plmnSupport->sliceSupportList.slices.push_back(ngap_utils::SliceSupportFromAsn(ssItem));
            });
            amf->plmnSupportList.push_back(plmnSupport);
        });
    }
}

void NgapTask::handleAssociationSetup(int amfId, int ascId, int inCount, int outCount)
{
    auto *amf = findAmfContext(amfId);
    if (amf != nullptr)
    {
        amf->association.associationId = amfId;
        amf->association.inStreams = inCount;
        amf->association.outStreams = outCount;

        sendNgSetupRequest(amf->ctxId);
    }
}

void NgapTask::handleAssociationShutdown(int amfId)
{
    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    m_logger->err("Association terminated for AMF[%d]", amfId);
    m_logger->debug("Removing AMF context[%d]", amfId);

    amf->state = EAmfState::NOT_CONNECTED;

    auto w = std::make_unique<NmGnbSctp>(NmGnbSctp::CONNECTION_CLOSE);
    w->clientId = amfId;
    m_base->sctpTask->push(std::move(w));

    deleteAmfContext(amfId);
}

void NgapTask::sendNgSetupRequest(int amfId)
{
    m_logger->debug("Sending NG Setup Request");

    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    amf->state = EAmfState::WAITING_NG_SETUP;

    // TODO: this procedure also re-initialises the NGAP UE-related contexts (if any)
    //  and erases all related signalling connections in the two nodes like an NG Reset procedure would do.
    //  More on 38.413 8.7.1.1

    auto *globalGnbId = asn::New<ASN_NGAP_GlobalGNB_ID>();
    globalGnbId->gNB_ID.present = ASN_NGAP_GNB_ID_PR_gNB_ID;
    auto gnbIdLength = m_base->config->gnbIdLength;
    auto bitsToShift = 32 - gnbIdLength;
    asn::SetBitString(globalGnbId->gNB_ID.choice.gNB_ID, octet4{m_base->config->getGnbId() << bitsToShift},
                      static_cast<size_t>(gnbIdLength));
    asn::SetOctetString3(globalGnbId->pLMNIdentity, ngap_utils::PlmnToOctet3(m_base->config->plmn));

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
    asn::SetPrintableString(ieRanNodeName->value.choice.RANNodeName, m_base->config->name);

    auto *broadcastPlmn = asn::New<ASN_NGAP_BroadcastPLMNItem>();
    asn::SetOctetString3(broadcastPlmn->pLMNIdentity, ngap_utils::PlmnToOctet3(m_base->config->plmn));
    for (auto &nssai : m_base->config->nssai.slices)
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
    asn::SetOctetString3(supportedTa->tAC, octet3{m_base->config->tac});
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
    iePagingDrx->value.choice.PagingDRX = ngap_utils::PagingDrxToAsn(m_base->config->pagingDrx);

    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_NGSetupRequest>(
        {ieGlobalGnbId, ieRanNodeName, ieSupportedTaList, iePagingDrx});

    sendNgapNonUe(amfId, pdu);
}

void NgapTask::receiveNgSetupResponse(int amfId, ASN_NGAP_NGSetupResponse *msg)
{
    m_logger->debug("NG Setup Response received");

    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    AssignDefaultAmfConfigs(amf, msg);

    amf->state = EAmfState::CONNECTED;
    m_logger->info("NG Setup procedure is successful");

    if (!m_isInitialized && std::all_of(m_amfCtx.begin(), m_amfCtx.end(),
                                        [](auto &amfCtx) { return amfCtx.second->state == EAmfState::CONNECTED; }))
    {
        m_isInitialized = true;

        auto update = std::make_unique<NmGnbStatusUpdate>(NmGnbStatusUpdate::NGAP_IS_UP);
        update->isNgapUp = true;
        m_base->appTask->push(std::move(update));

        m_base->rrcTask->push(std::make_unique<NmGnbNgapToRrc>(NmGnbNgapToRrc::RADIO_POWER_ON));
    }
}

void NgapTask::receiveNgSetupFailure(int amfId, ASN_NGAP_NGSetupFailure *msg)
{
    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    amf->state = EAmfState::WAITING_NG_SETUP;

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_Cause);
    if (ie)
        m_logger->err("NG Setup procedure is failed. Cause: %s", ngap_utils::CauseToString(ie->Cause).c_str());
    else
        m_logger->err("NG Setup procedure is failed.");
}

void NgapTask::receiveErrorIndication(int amfId, ASN_NGAP_ErrorIndication *msg)
{
    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
    {
        m_logger->err("Error indication received with not found AMF context");
        return;
    }

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_Cause);
    if (ie)
        m_logger->err("Error indication received. Cause: %s", ngap_utils::CauseToString(ie->Cause).c_str());
    else
        m_logger->err("Error indication received.");
}

void NgapTask::sendErrorIndication(int amfId, NgapCause cause, int ueId)
{
    auto ieCause = asn::New<ASN_NGAP_ErrorIndicationIEs>();
    ieCause->id = ASN_NGAP_ProtocolIE_ID_id_Cause;
    ieCause->criticality = ASN_NGAP_Criticality_ignore;
    ieCause->value.present = ASN_NGAP_ErrorIndicationIEs__value_PR_Cause;
    ngap_utils::ToCauseAsn_Ref(cause, ieCause->value.choice.Cause);

    m_logger->warn("Sending an error indication with cause: %s",
                   ngap_utils::CauseToString(ieCause->value.choice.Cause).c_str());

    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_ErrorIndication>({ieCause});

    if (ueId > 0)
        sendNgapUeAssociated(ueId, pdu);
    else
        sendNgapNonUe(amfId, pdu);
}

void NgapTask::receiveAmfConfigurationUpdate(int amfId, ASN_NGAP_AMFConfigurationUpdate *msg)
{
    m_logger->debug("AMF configuration update received");

    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    bool tnlModified = false;

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMF_TNLAssociationToAddList);
    if (ie && ie->AMF_TNLAssociationToAddList.list.count > 0)
        tnlModified = true;

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMF_TNLAssociationToRemoveList);
    if (ie && ie->AMF_TNLAssociationToRemoveList.list.count > 0)
        tnlModified = true;

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMF_TNLAssociationToUpdateList);
    if (ie && ie->AMF_TNLAssociationToUpdateList.list.count > 0)
        tnlModified = true;

    // TODO: AMF TNL modification is not supported
    if (tnlModified)
    {
        m_logger->err("TNL modification is not supported, rejecting AMF configuration update");

        auto *ieCause = asn::New<ASN_NGAP_AMFConfigurationUpdateFailureIEs>();
        ieCause->id = ASN_NGAP_ProtocolIE_ID_id_Cause;
        ieCause->criticality = ASN_NGAP_Criticality_ignore;
        ieCause->value.present = ASN_NGAP_AMFConfigurationUpdateFailureIEs__value_PR_Cause;
        ngap_utils::ToCauseAsn_Ref(NgapCause::Transport_unspecified, ieCause->value.choice.Cause);

        auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_AMFConfigurationUpdateFailure>({ieCause});
        sendNgapNonUe(amfId, pdu);
    }
    else
    {
        AssignDefaultAmfConfigs(amf, msg);

        auto *ieList = asn::New<ASN_NGAP_AMFConfigurationUpdateAcknowledgeIEs>();
        ieList->id = ASN_NGAP_ProtocolIE_ID_id_AMF_TNLAssociationSetupList;
        ieList->criticality = ASN_NGAP_Criticality_ignore;
        ieList->value.present = ASN_NGAP_AMFConfigurationUpdateAcknowledgeIEs__value_PR_AMF_TNLAssociationSetupList;

        auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_AMFConfigurationUpdateAcknowledge>({ieList});
        sendNgapNonUe(amfId, pdu);
    }
}

void NgapTask::receiveOverloadStart(int amfId, ASN_NGAP_OverloadStart *msg)
{
    m_logger->debug("AMF overload start received");

    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    amf->overloadInfo = {};
    amf->overloadInfo.status = EOverloadStatus::OVERLOADED;

    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMFOverloadResponse);
    if (ie && ie->OverloadResponse.present == ASN_NGAP_OverloadResponse_PR_overloadAction)
    {
        switch (ie->OverloadResponse.choice.overloadAction)
        {
        case ASN_NGAP_OverloadAction_reject_non_emergency_mo_dt:
            amf->overloadInfo.indication.action = EOverloadAction::REJECT_NON_EMERGENCY_MO_DATA;
            break;
        case ASN_NGAP_OverloadAction_reject_rrc_cr_signalling:
            amf->overloadInfo.indication.action = EOverloadAction::REJECT_SIGNALLING;
            break;
        case ASN_NGAP_OverloadAction_permit_emergency_sessions_and_mobile_terminated_services_only:
            amf->overloadInfo.indication.action = EOverloadAction::ONLY_EMERGENCY_AND_MT;
            break;
        case ASN_NGAP_OverloadAction_permit_high_priority_sessions_and_mobile_terminated_services_only:
            amf->overloadInfo.indication.action = EOverloadAction::ONLY_HIGH_PRI_AND_MT;
            break;
        default:
            m_logger->warn("AMF overload action [%d] could not understand",
                           (int)ie->OverloadResponse.choice.overloadAction);
            break;
        }
    }

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMFTrafficLoadReductionIndication);
    if (ie)
        amf->overloadInfo.indication.loadReductionPerc = static_cast<int>(ie->TrafficLoadReductionIndication);

    ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_OverloadStartNSSAIList);
    if (ie)
    {
        // TODO
        /*asn::ForeachItem(ie->OverloadStartNSSAIList, [](auto &item) {
            item.sliceOverloadList;
        });*/
    }
}

void NgapTask::receiveOverloadStop(int amfId, ASN_NGAP_OverloadStop *msg)
{
    m_logger->debug("AMF overload stop received");

    // TODO
}

} // namespace nr::gnb