//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "encode.hpp"
#include "task.hpp"
#include "utils.hpp"
#include <asn/ngap/ASN_NGAP_ProtocolIE-ID.h>
#include <cstdio>
#include <gnb/rrc/task.hpp>

#include <asn/ngap/ASN_NGAP_HandoverCommand.h>
#include <asn/ngap/ASN_NGAP_HandoverCommandTransfer.h>
#include <asn/ngap/ASN_NGAP_HandoverPreparationFailure.h>
#include <asn/ngap/ASN_NGAP_HandoverRequest.h>
#include <asn/ngap/ASN_NGAP_HandoverRequestAcknowledge.h>
#include <asn/ngap/ASN_NGAP_HandoverRequired.h>
#include <asn/ngap/ASN_NGAP_NR-CGI.h>
#include <asn/ngap/ASN_NGAP_ProtocolIE-Field.h>

#include <asn/ngap/ASN_NGAP_GlobalGNB-ID.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceItemHORqd.h>
#include <gnb/gtp/task.hpp>

#include <asn/ngap/ASN_NGAP_NGRAN-CGI.h>
#include <asn/ngap/ASN_NGAP_SourceNGRANNode-ToTargetNGRANNode-TransparentContainer.h>
#include <asn/ngap/ASN_NGAP_SourceToTarget-TransparentContainer.h>
#include <asn/ngap/ASN_NGAP_TargetRANNodeID.h>
#include <asn/rrc/ASN_RRC_HandoverPreparationInformation-IEs.h>
#include <asn/rrc/ASN_RRC_HandoverPreparationInformation.h>
#include <asn/rrc/ASN_RRC_UE-CapabilityRAT-Container.h>
#include <lib/rrc/encode.hpp>

#include <asn/ngap/ASN_NGAP_DirectForwardingPathAvailability.h>
#include <asn/ngap/ASN_NGAP_ErrorIndication.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupItemHOReq.h>
#include <asn/ngap/ASN_NGAP_ProtocolIE-Field.h>

#include <asn/ngap/ASN_NGAP_HandoverRequestAcknowledgeTransfer.h>
#include <asn/ngap/ASN_NGAP_HandoverResourceAllocationUnsuccessfulTransfer.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceInformationItem.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceInformationList.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupRequestTransfer.h>
#include <asn/ngap/ASN_NGAP_QosFlowIdentifier.h>
#include <asn/ngap/ASN_NGAP_QosFlowInformationItem.h>
#include <asn/ngap/ASN_NGAP_QosFlowInformationList.h>

#include <asn/ngap/ASN_NGAP_GTPTunnel.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceAdmittedItem.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceAdmittedList.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceFailedToSetupItemHOAck.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceFailedToSetupListHOAck.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceHandoverList.h>
#include <asn/ngap/ASN_NGAP_QosFlowItemWithDataForwarding.h>
#include <asn/ngap/ASN_NGAP_QosFlowSetupRequestItem.h>
#include <asn/ngap/ASN_NGAP_QosFlowSetupRequestList.h>

#include <asn/ngap/ASN_NGAP_LastVisitedCellInformation.h>
#include <asn/ngap/ASN_NGAP_LastVisitedCellItem.h>
#include <asn/ngap/ASN_NGAP_LastVisitedNGRANCellInformation.h>

#include <asn/ngap/ASN_NGAP_AllowedNSSAI-Item.h>
#include <asn/ngap/ASN_NGAP_TargetNGRANNode-ToSourceNGRANNode-TransparentContainer.h>
#include <asn/rrc/ASN_RRC_HandoverCommand.h>
#include <asn/rrc/ASN_RRC_RRCReconfiguration-IEs.h>
#include <asn/rrc/ASN_RRC_RRCReconfiguration.h>

#include <asn/ngap/ASN_NGAP_HandoverNotify.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceHandoverItem.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupItemCxtReq.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupRequest.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupResponse.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSwitchedItem.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceToBeSwitchedDLItem.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceToBeSwitchedDLList.h>
#include <asn/ngap/ASN_NGAP_PathSwitchRequest.h>
#include <asn/ngap/ASN_NGAP_PathSwitchRequestAcknowledge.h>
#include <asn/ngap/ASN_NGAP_PathSwitchRequestAcknowledgeTransfer.h>
#include <asn/ngap/ASN_NGAP_PathSwitchRequestTransfer.h>
#include <asn/ngap/ASN_NGAP_QosFlowAcceptedList.h>
#include <asn/ngap/ASN_NGAP_QosFlowToBeForwardedItem.h>
#include <asn/ngap/ASN_NGAP_QosFlowToBeForwardedList.h>
#include <asn/ngap/ASN_NGAP_UPTransportLayerInformation.h>

#include <gnb/rls/task.hpp>

#include <array>
#include <cstdint>

namespace nr::gnb
{

void NgapTask::sendHandoverRequired(int ueId, int gnbTargetID)
{
    auto *ueCtx = findUeByRanId(ueId);
    if (ueCtx == nullptr)
    {
        m_logger->err("Could not find UE context[%d]", ueId);
        return;
    }

    if (ueCtx->pduSessions.empty())
    {
        m_logger->err("No PDU session found for UE[%d]", ueId);
        return;
    }

    auto *amfCtx = findAmfContext(ueCtx->associatedAmfId);
    if (amfCtx == nullptr)
    {
        m_logger->err("Could not find AMF context[%d]", ueCtx->associatedAmfId);
        return;
    }

    // Message type: Handover
    std::vector<ASN_NGAP_HandoverRequiredIEs *> ies;

    // Handover Type
    auto *ieHandoverType = asn::New<ASN_NGAP_HandoverRequiredIEs>();
    ieHandoverType->id = ASN_NGAP_ProtocolIE_ID_id_HandoverType;
    ieHandoverType->criticality = ASN_NGAP_Criticality_reject;
    ieHandoverType->value.present = ASN_NGAP_HandoverRequiredIEs__value_PR_HandoverType;
    ieHandoverType->value.choice.HandoverType = ASN_NGAP_HandoverType_intra5gs;
    ies.push_back(ieHandoverType);

    // Cause
    auto *ieCause = asn::New<ASN_NGAP_HandoverRequiredIEs>();
    ieCause->id = ASN_NGAP_ProtocolIE_ID_id_Cause;
    ieCause->criticality = ASN_NGAP_Criticality_ignore;
    ieCause->value.present = ASN_NGAP_HandoverRequiredIEs__value_PR_Cause;
    ngap_utils::ToCauseAsn_Ref(NgapCause::RadioNetwork_unspecified, ieCause->value.choice.Cause);
    ies.push_back(ieCause);

    // Target ID
    auto *ieTargetId = asn::New<ASN_NGAP_HandoverRequiredIEs>();
    ieTargetId->id = ASN_NGAP_ProtocolIE_ID_id_TargetID;
    ieTargetId->criticality = ASN_NGAP_Criticality_reject;
    ieTargetId->value.present = ASN_NGAP_HandoverRequiredIEs__value_PR_TargetID;

    ieTargetId->value.choice.TargetID.present = ASN_NGAP_TargetID_PR_targetRANNodeID;
    ieTargetId->value.choice.TargetID.choice.targetRANNodeID = asn::New<ASN_NGAP_TargetRANNodeID>();
    ieTargetId->value.choice.TargetID.choice.targetRANNodeID->globalRANNodeID.present =
        ASN_NGAP_GlobalRANNodeID_PR_globalGNB_ID;

    auto *globalGnbId = asn::New<ASN_NGAP_GlobalGNB_ID>();
    globalGnbId->gNB_ID.present = ASN_NGAP_GNB_ID_PR_gNB_ID;
    asn::SetBitString(globalGnbId->gNB_ID.choice.gNB_ID, octet4{gnbTargetID << (32 - m_base->config->gnbIdLength)},
                      static_cast<size_t>(m_base->config->gnbIdLength));
    asn::SetOctetString3(globalGnbId->pLMNIdentity, ngap_utils::PlmnToOctet3(m_base->config->plmn));

    ieTargetId->value.choice.TargetID.choice.targetRANNodeID->globalRANNodeID.choice.globalGNB_ID = globalGnbId;

    asn::SetOctetString3(ieTargetId->value.choice.TargetID.choice.targetRANNodeID->selectedTAI.pLMNIdentity,
                         ngap_utils::PlmnToOctet3(m_base->config->plmn));
    asn::SetOctetString3(ieTargetId->value.choice.TargetID.choice.targetRANNodeID->selectedTAI.tAC,
                         octet3{m_base->config->tac});

    ies.push_back(ieTargetId);

    // PDU Session Resource List
    auto *iePduSessionList = asn::New<ASN_NGAP_HandoverRequiredIEs>();
    iePduSessionList->id = ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceListHORqd;
    iePduSessionList->criticality = ASN_NGAP_Criticality_reject;
    iePduSessionList->value.present = ASN_NGAP_HandoverRequiredIEs__value_PR_PDUSessionResourceListHORqd;

    for (int psi : ueCtx->pduSessions)
    {
        auto *sessionItem = asn::New<ASN_NGAP_PDUSessionResourceItemHORqd>();
        sessionItem->pDUSessionID = static_cast<ASN_NGAP_PDUSessionID_t>(psi);
        asn::SetOctetString1(sessionItem->handoverRequiredTransfer,
                             static_cast<uint8_t>(ASN_NGAP_DirectForwardingPathAvailability_direct_path_available));
        asn::SequenceAdd(iePduSessionList->value.choice.PDUSessionResourceListHORqd, sessionItem);
        // Handover Required Transfer
    }

    ies.push_back(iePduSessionList);

    // Source To Target Transparent Container

    auto *ieSourceToTargetTransparentContainer = asn::New<ASN_NGAP_HandoverRequiredIEs>();
    ieSourceToTargetTransparentContainer->id = ASN_NGAP_ProtocolIE_ID_id_SourceToTarget_TransparentContainer;
    ieSourceToTargetTransparentContainer->criticality = ASN_NGAP_Criticality_reject;
    ieSourceToTargetTransparentContainer->value.present =
        ASN_NGAP_HandoverRequiredIEs__value_PR_SourceToTarget_TransparentContainer;

    // Source NG-RAN Node to Target NG-RAN Node Transparent Container
    auto *container = asn::New<ASN_NGAP_SourceNGRANNode_ToTargetNGRANNode_TransparentContainer>();

    // Sub-Field 1 : RRC
    auto *handoverPreparationInfos = asn::New<ASN_RRC_HandoverPreparationInformation>();
    handoverPreparationInfos->criticalExtensions.present =
        ASN_RRC_HandoverPreparationInformation__criticalExtensions_PR_c1;
    handoverPreparationInfos->criticalExtensions.choice.c1 =
        asn::New<ASN_RRC_HandoverPreparationInformation::ASN_RRC_HandoverPreparationInformation__criticalExtensions::
                     ASN_RRC_HandoverPreparationInformation__ASN_RRC_criticalExtensions_u::
                         ASN_RRC_HandoverPreparationInformation__criticalExtensions__c1>();
    handoverPreparationInfos->criticalExtensions.choice.c1->present =
        ASN_RRC_HandoverPreparationInformation__criticalExtensions__c1_PR_handoverPreparationInformation;

    // rRCContainer core message
    handoverPreparationInfos->criticalExtensions.choice.c1->choice.handoverPreparationInformation =
        asn::New<ASN_RRC_HandoverPreparationInformation_IEs>();
    auto *hpi_ies = handoverPreparationInfos->criticalExtensions.choice.c1->choice
                        .handoverPreparationInformation; // copy of the pointer

    auto *ratItem = asn::New<ASN_RRC_UE_CapabilityRAT_Container>();
    ratItem->rat_Type = ASN_RRC_RAT_Type_nr;
    ratItem->ue_CapabilityRAT_Container.size = 4;
    ratItem->ue_CapabilityRAT_Container.buf = (uint8_t *)calloc(1, 4);
    if (ratItem->ue_CapabilityRAT_Container.buf == nullptr)
    {
        m_logger->err("Failed to allocate memory for ue_CapabilityRAT_Container");
        return;
    }
    ratItem->ue_CapabilityRAT_Container.buf[0] = 0xde;
    ratItem->ue_CapabilityRAT_Container.buf[1] = 0xad;
    ratItem->ue_CapabilityRAT_Container.buf[2] = 0xbe;
    ratItem->ue_CapabilityRAT_Container.buf[3] = 0xef;
    // Buffer result = ad be ef → random valid syntax for testing

    ASN_SEQUENCE_ADD(&hpi_ies->ue_CapabilityRAT_List.list, ratItem);

    OctetString handoverEncode =
        rrc::encode::EncodeS(asn_DEF_ASN_RRC_HandoverPreparationInformation, handoverPreparationInfos);

    if (handoverEncode.length() == 0)
    {
        m_logger->err("HandoverPreparationInformation encoding failed");
        return;
    }
    asn::Free(asn_DEF_ASN_RRC_HandoverPreparationInformation, handoverPreparationInfos);

    asn::SetOctetString(container->rRCContainer, handoverEncode);

    // === Sub-field 3 : targetCell_ID ===
    container->targetCell_ID.present = ASN_NGAP_NGRAN_CGI_PR_nR_CGI;
    container->targetCell_ID.choice.nR_CGI = asn::New<ASN_NGAP_NR_CGI>();

    auto *nrCgi = container->targetCell_ID.choice.nR_CGI;

    // ==== PLMN ID (static and defined in our yaml) ====
    uint8_t plmnId[3] = {0x00, 0xf1, 0x10}; // MCC=001, MNC=01

    // ==== NCI (NR Cell Identity) dynamically determined based on the targetGnbID ====
    uint64_t nci = static_cast<uint64_t>(gnbTargetID) & 0xFFFFFFFFF; // 36 bits

    // === PLMN ID ===
    nrCgi->pLMNIdentity.size = 3;
    nrCgi->pLMNIdentity.buf = (uint8_t *)calloc(1, 3);
    memcpy(nrCgi->pLMNIdentity.buf, plmnId, 3);

    // === NR Cell Identity (NCI) ===
    uint8_t *buf = (uint8_t *)calloc(1, 5);
    for (int i = 0; i < 5; ++i)
        buf[4 - i] = (nci >> (8 * i)) & 0xFF;

    nrCgi->nRCellIdentity.buf = buf;
    nrCgi->nRCellIdentity.size = 5;
    nrCgi->nRCellIdentity.bits_unused = 4; // 36 bits used, 4 bits unused

    if (container->targetCell_ID.choice.nR_CGI->nRCellIdentity.buf == nullptr)
    {
        m_logger->err("Failed to allocate memory for nRCellIdentity");
        return;
    }

    // Sub-field 4 : LastVisitedCell

    auto *cellItem = asn::New<ASN_NGAP_LastVisitedCellItem>();
    cellItem->lastVisitedCellInformation.present = ASN_NGAP_LastVisitedCellInformation_PR_nGRANCell;
    cellItem->lastVisitedCellInformation.choice.nGRANCell = asn::New<ASN_NGAP_LastVisitedNGRANCellInformation_t>();

    auto *ngran = cellItem->lastVisitedCellInformation.choice.nGRANCell;

    // === NR-CGI ===
    ngran->globalCellID.present = ASN_NGAP_NGRAN_CGI_PR_nR_CGI;
    ngran->globalCellID.choice.nR_CGI = asn::New<ASN_NGAP_NR_CGI>();

    // === PLMN ID ===
    asn::SetOctetString3(ngran->globalCellID.choice.nR_CGI->pLMNIdentity,
                         ngap_utils::PlmnToOctet3(m_base->config->plmn));

    // === NR Cell Identity (36 bits of 40, bits_unused = 4) ===
    uint64_t fullNci = m_base->config->nci;
    buf = (uint8_t *)calloc(1, 5);
    for (int i = 0; i < 5; ++i)
    {
        buf[4 - i] = (fullNci >> (8 * i)) & 0xFF;
    }
    ngran->globalCellID.choice.nR_CGI->nRCellIdentity.buf = buf;
    ngran->globalCellID.choice.nR_CGI->nRCellIdentity.size = 5;
    ngran->globalCellID.choice.nR_CGI->nRCellIdentity.bits_unused = 4;

    // === Cell type + time ===
    ngran->cellType.cellSize = ASN_NGAP_CellSize_medium;
    ngran->timeUEStayedInCell = 42;

    ngran->timeUEStayedInCellEnhancedGranularity = nullptr;
    ngran->hOCauseValue = nullptr;
    ngran->iE_Extensions = nullptr;

    ASN_SEQUENCE_ADD(&container->uEHistoryInformation.list, cellItem);

    OctetString containerEncoded =
        ngap_encode::EncodeS(asn_DEF_ASN_NGAP_SourceNGRANNode_ToTargetNGRANNode_TransparentContainer, container);
    std::stringstream ss;
    asn::SetOctetString(ieSourceToTargetTransparentContainer->value.choice.SourceToTarget_TransparentContainer,
                        containerEncoded);
    ies.push_back(ieSourceToTargetTransparentContainer);
    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_HandoverRequired>(ies);

    m_logger->debug("Sending Handover Required request for UE[%d] to AMF[%d]", ueId, ueCtx->associatedAmfId);
    sendNgapUeAssociated(ueId, pdu);
}

void NgapTask::receiveHandoverRequest(int amfId, ASN_NGAP_HandoverRequest *msg)
{
    m_logger->debug("Handover request message received from AMF");

    auto *reqIe = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMF_UE_NGAP_ID);
    if (!reqIe)
    {
        m_logger->err("Missing AMF_UE_NGAP_ID in HandoverRequest");
        return;
    }

    int ueId = m_base->rlsTask->udpTask()->reserveNewUeId();
    m_logger->debug("UE ID reserved: %d", ueId);
    int32_t sst;
    auto *ieAllowedNssai = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AllowedNSSAI);
    if (ieAllowedNssai && ieAllowedNssai->AllowedNSSAI.list.count > 0)
    {
        const auto &sstOctet = ieAllowedNssai->AllowedNSSAI.list.array[0]->s_NSSAI.sST;
        if (sstOctet.size > 0)
        {
            sst = static_cast<int32_t>(sstOctet.buf[0]);
        }
    }

    // Check if the UE context already exists on target gNB
    // If it does, we need to update the context with the new information
    auto *ue = findUeContext(ueId);
    if (ue)
    {
        m_logger->warn("UE[%d] already exists — skipping handover creation", ueId);
    }
    else
    {
        m_logger->debug("Creating placeholder for pending handover, UE[%d]", ueId);

        createUeContext(ueId, sst);
        ue = findUeContext(ueId);
        if (!ue)
        {
            m_logger->err("Failed to create placeholder UE[%d] during HandoverRequest", ueId);
            return;
        }

        ue->associatedAmfId = amfId;
        ue->amfUeNgapId = static_cast<int>(asn::GetUnsigned64(reqIe->AMF_UE_NGAP_ID));
        ue->ranUeNgapId = ueId;
    }

    auto *amfCtx = findAmfContext(amfId);
    if (!amfCtx)
    {
        m_logger->err("AMF context not found with id: %d", amfId);
        return;
    }
    // adding Ue Bit rate informations to Ue context
    reqIe = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_UEAggregateMaximumBitRate);
    if (reqIe)
    {
        ue->ueAmbr.dlAmbr = asn::GetUnsigned64(reqIe->UEAggregateMaximumBitRate.uEAggregateMaximumBitRateDL) / 8ull;
        ue->ueAmbr.ulAmbr = asn::GetUnsigned64(reqIe->UEAggregateMaximumBitRate.uEAggregateMaximumBitRateUL) / 8ull;
    }

    // sourceToTargetTransparentContainer
    reqIe = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_SourceToTarget_TransparentContainer);

    // notify gtp task for new Ue
    auto w = std::make_unique<NmGnbNgapToGtp>(NmGnbNgapToGtp::UE_CONTEXT_UPDATE);
    w->update = std::make_unique<GtpUeContextUpdate>(true, ueId, ue->ueAmbr);
    m_base->gtpTask->push(std::move(w));
    std::vector<ASN_NGAP_HandoverRequestAcknowledgeIEs *> responseIes;

    for (int psiOld : ue->pduSessions)
    {
        auto wRel = std::make_unique<NmGnbNgapToGtp>(NmGnbNgapToGtp::SESSION_RELEASE);
        wRel->ueId = ueId;
        wRel->psi = psiOld;
        m_base->gtpTask->push(std::move(wRel));
    }
    // Handover PDU Session Resource Allocation

    std::vector<ASN_NGAP_PDUSessionResourceAdmittedItem *> successList;
    std::vector<ASN_NGAP_PDUSessionResourceFailedToSetupItemHOAck *> failedList;

    reqIe = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceSetupListHOReq);
    if (reqIe)
    {
        auto &list = reqIe->PDUSessionResourceSetupListHOReq.list;
        for (int i = 0; i < list.count; i++)
        {
            auto &item = list.array[i];
            auto *transfer = ngap_encode::Decode<ASN_NGAP_PDUSessionResourceSetupRequestTransfer>(
                asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, item->handoverRequestTransfer);
            if (transfer == nullptr)
            {
                m_logger->err(
                    "Unable to decode a PDU Session Resource Setup Request Transfer. Ignoring the relevant item");
                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, transfer);
                continue;
            }
            // Ressource allocation for each PDU Session
            auto *resource = new PduSessionResource(ue->ctxId, static_cast<int>(item->pDUSessionID));
            m_logger->debug("New PDU res UE=%d PSI=%d", resource->ueId, resource->psi);

            m_logger->debug("GTP session created for UE=%d PSI=%d", resource->ueId, resource->psi);

            auto *ie = asn::ngap::GetProtocolIe(transfer, ASN_NGAP_ProtocolIE_ID_id_PDUSessionAggregateMaximumBitRate);
            if (ie)
            {
                resource->sessionAmbr.dlAmbr =
                    asn::GetUnsigned64(ie->PDUSessionAggregateMaximumBitRate.pDUSessionAggregateMaximumBitRateDL) /
                    8ull;
                resource->sessionAmbr.ulAmbr =
                    asn::GetUnsigned64(ie->PDUSessionAggregateMaximumBitRate.pDUSessionAggregateMaximumBitRateUL) /
                    8ull;
            }
            ie = asn::ngap::GetProtocolIe(transfer, ASN_NGAP_ProtocolIE_ID_id_DataForwardingNotPossible);
            if (ie)
                resource->dataForwardingNotPossible = true;

            ie = asn::ngap::GetProtocolIe(transfer, ASN_NGAP_ProtocolIE_ID_id_PDUSessionType);
            if (ie)
                resource->sessionType = ngap_utils::PduSessionTypeFromAsn(ie->PDUSessionType);

            ie = asn::ngap::GetProtocolIe(transfer, ASN_NGAP_ProtocolIE_ID_id_UL_NGU_UP_TNLInformation);
            if (ie)
            {
                resource->upTunnel.teid =
                    (uint32_t)asn::GetOctet4(ie->UPTransportLayerInformation.choice.gTPTunnel->gTP_TEID);
                resource->upTunnel.address =
                    asn::GetOctetString(ie->UPTransportLayerInformation.choice.gTPTunnel->transportLayerAddress);
            }

            ie = asn::ngap::GetProtocolIe(transfer, ASN_NGAP_ProtocolIE_ID_id_QosFlowSetupRequestList);
            if (ie)
            {
                auto *ptr = asn::New<ASN_NGAP_QosFlowSetupRequestList_t>();
                asn::DeepCopy(asn_DEF_ASN_NGAP_QosFlowSetupRequestList, ie->QosFlowSetupRequestList, ptr);
                resource->qosFlows = asn::WrapUnique(ptr, asn_DEF_ASN_NGAP_QosFlowSetupRequestList);
            }
            /* ------------------------------------------------------------------
             * Calls the internal function that actually prepares the resource:
             *  - allocates a DL TEID (resource->downTunnel.*)
             *  - leaves the UL TEID (resource->upTunnel.*) from the transfer unchanged
             * ---------------------------------------------------------------- */

            auto error = setupPduSessionResource(ue, resource);
            m_logger->debug("ctx[%d] now has %zu PSI", ue->ctxId, ue->pduSessions.size());
            if (error.has_value())
            {
                /* ----------- branch "alloc error" ----------- */
                m_logger->debug("HandoverResourceAllocationUnsuccessfulTransfer for UE[%d] PSI[%d]", resource->ueId,
                                resource->psi);

                auto *tr = asn::New<ASN_NGAP_HandoverResourceAllocationUnsuccessfulTransfer>();
                ngap_utils::ToCauseAsn_Ref(error.value(), tr->cause);

                OctetString encodedTr =
                    ngap_encode::EncodeS(asn_DEF_ASN_NGAP_HandoverResourceAllocationUnsuccessfulTransfer, tr);
                if (encodedTr.length() == 0)
                    throw std::runtime_error("HandoverResourceAllocationUnsuccessfulTransfer encoding failed");

                asn::Free(asn_DEF_ASN_NGAP_HandoverResourceAllocationUnsuccessfulTransfer, tr);

                auto *res = asn::New<ASN_NGAP_PDUSessionResourceFailedToSetupItemHOAck>();
                res->pDUSessionID = resource->psi;
                asn::SetOctetString(res->handoverResourceAllocationUnsuccessfulTransfer, encodedTr);
                failedList.push_back(res);

                delete resource;
                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, transfer);
            }
            else
            {
                /* ----------- success branch ----------- */
                auto *tr = asn::New<ASN_NGAP_HandoverRequestAcknowledgeTransfer>();

                /* tunnel DL (to UE) = downTunnel */
                auto &dlInfo = tr->dL_NGU_UP_TNLInformation;
                dlInfo.present = ASN_NGAP_UPTransportLayerInformation_PR_gTPTunnel;
                dlInfo.choice.gTPTunnel = asn::New<ASN_NGAP_GTPTunnel>();
                asn::SetBitString(dlInfo.choice.gTPTunnel->transportLayerAddress, resource->downTunnel.address);
                asn::SetOctetString4(dlInfo.choice.gTPTunnel->gTP_TEID, (octet4)resource->downTunnel.teid);

                /* forwarding tunnel (source gNB → target gNB) = upTunnel */
                auto &fwdInfo = tr->dLForwardingUP_TNLInformation = asn::New<ASN_NGAP_UPTransportLayerInformation>();
                fwdInfo->present = ASN_NGAP_UPTransportLayerInformation_PR_gTPTunnel;
                fwdInfo->choice.gTPTunnel = asn::New<ASN_NGAP_GTPTunnel>();
                asn::SetBitString(fwdInfo->choice.gTPTunnel->transportLayerAddress, resource->upTunnel.address);
                asn::SetOctetString4(fwdInfo->choice.gTPTunnel->gTP_TEID, (octet4)resource->upTunnel.teid);

                /* QoS flows list accepted */
                for (int i = 0; i < resource->qosFlows->list.count; ++i)
                {
                    auto *qItem = asn::New<ASN_NGAP_QosFlowItemWithDataForwarding>();
                    qItem->qosFlowIdentifier = resource->qosFlows->list.array[i]->qosFlowIdentifier;
                    ASN_SEQUENCE_ADD(&tr->qosFlowSetupResponseList.list, qItem);
                }

                /* transfer encoding */
                OctetString encodedTr = ngap_encode::EncodeS(asn_DEF_ASN_NGAP_HandoverRequestAcknowledgeTransfer, tr);
                if (encodedTr.length() == 0)
                    throw std::runtime_error("HandoverRequestAcknowledgeTransfer encoding failed");
                asn::Free(asn_DEF_ASN_NGAP_HandoverRequestAcknowledgeTransfer, tr);

                /*  « admitted » list */
                auto *res = asn::New<ASN_NGAP_PDUSessionResourceAdmittedItem>();
                res->pDUSessionID = static_cast<ASN_NGAP_PDUSessionID_t>(resource->psi);
                asn::SetOctetString(res->handoverRequestAcknowledgeTransfer, encodedTr);
                successList.push_back(res);

                /* ASN.1 transfer freed; the PDU session will remain managed by GTP */
                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, transfer);
            }
        }
    }

    if (!successList.empty())
    {
        auto *ie = asn::New<ASN_NGAP_HandoverRequestAcknowledgeIEs>();
        ie->id = ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceAdmittedList;
        ie->criticality = ASN_NGAP_Criticality_ignore;
        ie->value.present = ASN_NGAP_HandoverRequestAcknowledgeIEs__value_PR_PDUSessionResourceAdmittedList;

        for (auto &item : successList)
            asn::SequenceAdd(ie->value.choice.PDUSessionResourceAdmittedList, item);

        responseIes.push_back(ie);
    }

    if (!failedList.empty())
    {
        auto *ie = asn::New<ASN_NGAP_HandoverRequestAcknowledgeIEs>();
        ie->id = ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceFailedToSetupListHOAck;
        ie->criticality = ASN_NGAP_Criticality_ignore;
        ie->value.present = ASN_NGAP_HandoverRequestAcknowledgeIEs__value_PR_PDUSessionResourceFailedToSetupListHOAck;

        for (auto &item : failedList)
            asn::SequenceAdd(ie->value.choice.PDUSessionResourceFailedToSetupListHOAck, item);

        responseIes.push_back(ie);
    }

    // TargetToSource_TransparentContainer
    auto *ieTargetToSourceTransparentContainer = asn::New<ASN_NGAP_HandoverRequestAcknowledgeIEs>();
    ieTargetToSourceTransparentContainer->id = ASN_NGAP_ProtocolIE_ID_id_TargetToSource_TransparentContainer;
    ieTargetToSourceTransparentContainer->criticality = ASN_NGAP_Criticality_reject;
    ieTargetToSourceTransparentContainer->value.present =
        ASN_NGAP_HandoverRequestAcknowledgeIEs__value_PR_TargetToSource_TransparentContainer;

    // 1. The octetString will contain the targetGNB ID
    OctetString cellIdEncoded;

    // The container is 4 bytes long, the last byte being the target GNB ID
    cellIdEncoded.appendOctet4(
        static_cast<octet4>(m_base->config->getGnbId()));

    // 2. ASN NGAP Container structure
    auto *rrcContainer = asn::New<ASN_NGAP_TargetNGRANNode_ToSourceNGRANNode_TransparentContainer>();
    asn::SetOctetString(rrcContainer->rRCContainer, cellIdEncoded);

    // 3. Encoding
    OctetString encodedContainer =
        ngap_encode::EncodeS(asn_DEF_ASN_NGAP_TargetNGRANNode_ToSourceNGRANNode_TransparentContainer, rrcContainer);
    asn::Free(asn_DEF_ASN_NGAP_TargetNGRANNode_ToSourceNGRANNode_TransparentContainer, rrcContainer);

    // 4. Binding encoded container to the ASN.1 structure
    asn::SetOctetString(ieTargetToSourceTransparentContainer->value.choice.TargetToSource_TransparentContainer,
                        encodedContainer);

    responseIes.push_back(ieTargetToSourceTransparentContainer);
    m_logger->debug("Sending handover request ACK to AMF");
    auto *response = asn::ngap::NewMessagePdu<ASN_NGAP_HandoverRequestAcknowledge>(responseIes);
    sendNgapUeAssociated(ue->ctxId, response);
}

void NgapTask::receiveHandoverCommand(int amfId, ASN_NGAP_HandoverCommand *msg)
{
    m_logger->debug("Handover Command message received from AMF[%d]", amfId);
    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPair(msg));
    if (ue == nullptr)
    {
        m_logger->debug("Cannot find UE context[%d]", ue->ctxId);
        return;
    }

    // extracting information from targetToSourceTransparentContainer
    auto reqIe = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_TargetToSource_TransparentContainer);
    if (reqIe)
    {
        auto containerBytes = asn::GetOctetString(reqIe->TargetToSource_TransparentContainer);
        auto w = std::make_unique<NmGnbNgapToRrc>(NmGnbNgapToRrc::HANDOVER);
        w->ueId = ue->ctxId;
        w->rrcContainer = std::move(containerBytes);

        m_base->rrcTask->push(std::move(w));
    }
    else
    {
        m_logger->err("Missing TargetToSource_TransparentContainer IE");
    }
}

void NgapTask::handleHandoverConfirm(int ueId)
{

    sendHandoverNotify(ueId);
}

void NgapTask::sendHandoverNotify(int ueId)
{
    m_logger->debug("Sending Handover Notify message to AMF");

    auto *ueCtx = findUeByRanId(ueId);
    if (ueCtx == nullptr)
    {
        m_logger->err("Could not find UE context[%d]", ueId);
        return;
    }

    auto *amfCtx = findAmfContext(ueCtx->associatedAmfId);
    if (amfCtx == nullptr)
    {
        m_logger->err("Could not find AMF context[%d]", ueCtx->associatedAmfId);
        return;
    }

    std::vector<ASN_NGAP_HandoverNotifyIEs *> ies;
    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_HandoverNotify>(ies);
    sendNgapUeAssociated(ueCtx->ctxId, pdu);
}

void NgapTask::receiveHandoverPreparationFailure(ASN_NGAP_HandoverPreparationFailure *msg)
{
    auto *ie = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_Cause);
    if (ie)
        m_logger->err("Handover procedure failure. Cause: %s", ngap_utils::CauseToString(ie->Cause).c_str());
    else
        m_logger->err("Handover procedure failure.");
}

} // namespace nr::gnb