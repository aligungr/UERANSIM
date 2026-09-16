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
#include <map>
#include <memory>
#include <set>
#include <stdexcept>
#include <vector>
#include <gnb/gtp/task.hpp>
#include <asn/ngap/ASN_NGAP_AssociatedQosFlowItem.h>
#include <asn/ngap/ASN_NGAP_AssociatedQosFlowList.h>
#include <asn/ngap/ASN_NGAP_Dynamic5QIDescriptor.h>
#include <asn/ngap/ASN_NGAP_GTPTunnel.h>
#include <asn/ngap/ASN_NGAP_NonDynamic5QIDescriptor.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceFailedToSetupItemSURes.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceReleaseCommand.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceReleaseResponse.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceReleaseResponseTransfer.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceReleasedItemRelRes.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupItemSUReq.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupItemSURes.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupRequest.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupRequestTransfer.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupResponse.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupResponseTransfer.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer.h>
#include <asn/ngap/ASN_NGAP_PDUSessionResourceToReleaseItemRelCmd.h>
#include <asn/ngap/ASN_NGAP_ProtocolIE-Field.h>
#include <asn/ngap/ASN_NGAP_QosCharacteristics.h>
#include <asn/ngap/ASN_NGAP_QosFlowLevelQosParameters.h>
#include <asn/ngap/ASN_NGAP_QosFlowListWithCause.h>
#include <asn/ngap/ASN_NGAP_QosFlowPerTNLInformationItem.h>
#include <asn/ngap/ASN_NGAP_QosFlowPerTNLInformationList.h>
#include <asn/ngap/ASN_NGAP_QosFlowSetupRequestItem.h>
#include <asn/ngap/ASN_NGAP_QosFlowSetupRequestList.h>
#include <asn/ngap/ASN_NGAP_QosFlowWithCauseItem.h>

namespace nr::gnb
{

// Tells if the given standardised 5QI value is mapped to a GBR or to a delay critical GBR resource type.
// (See 3GPP TS 23.501, Table 5.7.4-1)
static bool IsGbrFiveQi(int64_t fiveQi)
{
    return (fiveQi >= 1 && fiveQi <= 4) || (fiveQi >= 65 && fiveQi <= 67) || (fiveQi >= 71 && fiveQi <= 76) ||
           (fiveQi >= 82 && fiveQi <= 85);
}

// Tells if the QoS flow described by the given QoS Flow Level QoS Parameters IE is a GBR QoS flow. For a dynamic
// 5QI, the Delay Critical and the Averaging Window IEs are signalled only for the GBR QoS flows.
// (See 3GPP TS 38.413, 9.3.1.12 and 9.3.1.19)
static bool IsGbrQosFlow(const ASN_NGAP_QosFlowLevelQosParameters &qosParameters)
{
    const auto &characteristics = qosParameters.qosCharacteristics;

    if (characteristics.present == ASN_NGAP_QosCharacteristics_PR_nonDynamic5QI)
        return characteristics.choice.nonDynamic5QI != nullptr &&
               IsGbrFiveQi(characteristics.choice.nonDynamic5QI->fiveQI);

    if (characteristics.present == ASN_NGAP_QosCharacteristics_PR_dynamic5QI)
    {
        const auto *descriptor = characteristics.choice.dynamic5QI;
        if (descriptor == nullptr)
            return false;
        if (descriptor->delayCritical != nullptr || descriptor->averagingWindow != nullptr)
            return true;
        return descriptor->fiveQI != nullptr && IsGbrFiveQi(*descriptor->fiveQI);
    }

    return false;
}

void NgapTask::receiveSessionResourceSetupRequest(int amfId, ASN_NGAP_PDUSessionResourceSetupRequest *msg)
{
    std::vector<ASN_NGAP_PDUSessionResourceSetupItemSURes *> successList;
    std::vector<ASN_NGAP_PDUSessionResourceFailedToSetupItemSURes *> failedList;

    auto addFailedItem = [&](int psi, NgapCause cause) {
        auto *tr = asn::New<ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer>();

        ngap_utils::ToCauseAsn_Ref(cause, tr->cause);

        OctetString encodedTr = ngap_encode::EncodeS(asn_DEF_ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer, tr);

        if (encodedTr.length() == 0)
            throw std::runtime_error("PDUSessionResourceSetupUnsuccessfulTransfer encoding failed");

        asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer, tr);

        auto *res = asn::New<ASN_NGAP_PDUSessionResourceFailedToSetupItemSURes>();
        res->pDUSessionID = psi;
        asn::SetOctetString(res->pDUSessionResourceSetupUnsuccessfulTransfer, encodedTr);

        failedList.push_back(res);
    };

    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPair(msg));
    if (ue == nullptr)
        return;

    auto *ieList = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceSetupListSUReq);

    if (ieList)
    {
        auto &list = ieList->PDUSessionResourceSetupListSUReq.list;

        std::map<int, int> psiCounts{};
        std::set<int> reportedDuplicates{};

        for (int i = 0; i < list.count; i++)
        {
            auto *item = list.array[i];
            if (item == nullptr)
                continue;

            psiCounts[static_cast<int>(item->pDUSessionID)]++;
        }

        for (int i = 0; i < list.count; i++)
        {
            auto *item = list.array[i];
            if (item == nullptr)
                continue;

            int psi = static_cast<int>(item->pDUSessionID);

            if (psiCounts[psi] > 1)
            {
                // The duplicated instances identify a single PDU session, therefore it is reported only once
                if (reportedDuplicates.insert(psi).second)
                {
                    m_logger->err("PDU session resource setup failed: duplicate PDU Session ID[%d] in setup request",
                                  psi);
                    addFailedItem(psi, NgapCause::RadioNetwork_multiple_PDU_session_ID_instances);
                }
                continue;
            }

            if (ue->pduSessions.count(psi) != 0)
            {
                m_logger->err("PDU session resource setup failed: PDU Session ID[%d] is already active", psi);
                addFailedItem(psi, NgapCause::Protocol_message_not_compatible_with_receiver_state);
                continue;
            }

            auto *transfer = ngap_encode::Decode<ASN_NGAP_PDUSessionResourceSetupRequestTransfer>(
                asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, item->pDUSessionResourceSetupRequestTransfer);
            if (transfer == nullptr)
            {
                m_logger->err(
                    "Unable to decode a PDU session resource setup request transfer. Ignoring the relevant item");
                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, transfer);
                continue;
            }

            auto resource = std::make_unique<PduSessionResource>(ue->ctxId, psi);

            auto *ie = asn::ngap::GetProtocolIe(transfer, ASN_NGAP_ProtocolIE_ID_id_PDUSessionAggregateMaximumBitRate);
            bool sessionAmbrPresent = ie != nullptr;
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

            std::vector<int> failedQosFlows{};
            bool nonGbrQosFlowPresent = false;

            ie = asn::ngap::GetProtocolIe(transfer, ASN_NGAP_ProtocolIE_ID_id_QosFlowSetupRequestList);
            if (ie)
            {
                auto &requestedFlows = ie->QosFlowSetupRequestList.list;
                auto *ptr = asn::New<ASN_NGAP_QosFlowSetupRequestList>();

                for (int iQos = 0; iQos < requestedFlows.count; iQos++)
                {
                    auto *qosFlow = requestedFlows.array[iQos];
                    if (qosFlow == nullptr)
                        continue;

                    int qfi = static_cast<int>(qosFlow->qosFlowIdentifier);
                    bool isGbrQosFlow = IsGbrQosFlow(qosFlow->qosFlowLevelQosParameters);

                    if (isGbrQosFlow && qosFlow->qosFlowLevelQosParameters.gBR_QosInformation == nullptr)
                    {
                        m_logger->err("QoS flow[%d] of PDU session[%d] could not setup: GBR QoS information is missing "
                                      "for a GBR QoS flow",
                                      qfi, psi);
                        failedQosFlows.push_back(qfi);
                        continue;
                    }

                    if (!isGbrQosFlow)
                        nonGbrQosFlowPresent = true;

                    auto *qosFlowCopy = asn::New<ASN_NGAP_QosFlowSetupRequestItem>();
                    asn::DeepCopy(asn_DEF_ASN_NGAP_QosFlowSetupRequestItem, *qosFlow, qosFlowCopy);
                    asn::SequenceAdd(*ptr, qosFlowCopy);
                }

                resource->qosFlows = asn::WrapUnique(ptr, asn_DEF_ASN_NGAP_QosFlowSetupRequestList);
            }

            if (nonGbrQosFlowPresent && !sessionAmbrPresent)
            {
                m_logger->err("PDU session resource setup failed: PDU session AMBR is missing for PDU Session ID[%d] "
                              "having Non-GBR QoS flow(s)",
                              psi);
                addFailedItem(psi, NgapCause::Protocol_semantic_error);
                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, transfer);
                continue;
            }

            auto *resourcePtr = resource.get();
            auto error = setupPduSessionResource(ue, resourcePtr);

            if (error.has_value())
            {
                addFailedItem(resourcePtr->psi, error.value());
            }
            else
            {
                resource.release();

                if (item->pDUSessionNAS_PDU)
                    deliverDownlinkNas(ue->ctxId, asn::GetOctetString(*item->pDUSessionNAS_PDU));

                auto *tr = asn::New<ASN_NGAP_PDUSessionResourceSetupResponseTransfer>();
                auto &qosList = resourcePtr->qosFlows->list;

                for (int iQos = 0; iQos < qosList.count; iQos++)
                {
                    auto *associatedQosFlowItem = asn::New<ASN_NGAP_AssociatedQosFlowItem>();
                    associatedQosFlowItem->qosFlowIdentifier = qosList.array[iQos]->qosFlowIdentifier;
                    asn::SequenceAdd(tr->dLQosFlowPerTNLInformation.associatedQosFlowList, associatedQosFlowItem);
                }

                if (!failedQosFlows.empty())
                {
                    tr->qosFlowFailedToSetupList = asn::New<ASN_NGAP_QosFlowListWithCause>();

                    for (int qfi : failedQosFlows)
                    {
                        auto *failedQosFlowItem = asn::New<ASN_NGAP_QosFlowWithCauseItem>();
                        failedQosFlowItem->qosFlowIdentifier = qfi;
                        ngap_utils::ToCauseAsn_Ref(NgapCause::Protocol_semantic_error, failedQosFlowItem->cause);
                        asn::SequenceAdd(*tr->qosFlowFailedToSetupList, failedQosFlowItem);
                    }
                }

                auto &upInfo = tr->dLQosFlowPerTNLInformation.uPTransportLayerInformation;
                upInfo.present = ASN_NGAP_UPTransportLayerInformation_PR_gTPTunnel;
                upInfo.choice.gTPTunnel = asn::New<ASN_NGAP_GTPTunnel>();

                asn::SetBitString(upInfo.choice.gTPTunnel->transportLayerAddress, resourcePtr->downTunnel.address);
                asn::SetOctetString4(upInfo.choice.gTPTunnel->gTP_TEID, (octet4)resourcePtr->downTunnel.teid);

                OctetString encodedTr =
                    ngap_encode::EncodeS(asn_DEF_ASN_NGAP_PDUSessionResourceSetupResponseTransfer, tr);

                if (encodedTr.length() == 0)
                    throw std::runtime_error("PDUSessionResourceSetupResponseTransfer encoding failed");

                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupResponseTransfer, tr);

                auto *res = asn::New<ASN_NGAP_PDUSessionResourceSetupItemSURes>();
                res->pDUSessionID = resourcePtr->psi;
                asn::SetOctetString(res->pDUSessionResourceSetupResponseTransfer, encodedTr);

                successList.push_back(res);
            }

            asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, transfer);
        }
    }

    auto *ieNasPdu = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_NAS_PDU);
    if (ieNasPdu)
        deliverDownlinkNas(ue->ctxId, asn::GetOctetString(ieNasPdu->NAS_PDU));

    std::vector<ASN_NGAP_PDUSessionResourceSetupResponseIEs *> responseIes;

    if (!successList.empty())
    {
        auto *ie = asn::New<ASN_NGAP_PDUSessionResourceSetupResponseIEs>();
        ie->id = ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceSetupListSURes;
        ie->criticality = ASN_NGAP_Criticality_ignore;
        ie->value.present = ASN_NGAP_PDUSessionResourceSetupResponseIEs__value_PR_PDUSessionResourceSetupListSURes;

        for (auto &item : successList)
            asn::SequenceAdd(ie->value.choice.PDUSessionResourceSetupListSURes, item);

        responseIes.push_back(ie);
    }

    if (!failedList.empty())
    {
        auto *ie = asn::New<ASN_NGAP_PDUSessionResourceSetupResponseIEs>();
        ie->id = ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceFailedToSetupListSURes;
        ie->criticality = ASN_NGAP_Criticality_ignore;
        ie->value.present =
            ASN_NGAP_PDUSessionResourceSetupResponseIEs__value_PR_PDUSessionResourceFailedToSetupListSURes;

        for (auto &item : failedList)
            asn::SequenceAdd(ie->value.choice.PDUSessionResourceFailedToSetupListSURes, item);

        responseIes.push_back(ie);
    }

    auto *respPdu = asn::ngap::NewMessagePdu<ASN_NGAP_PDUSessionResourceSetupResponse>(responseIes);
    sendNgapUeAssociated(ue->ctxId, respPdu);

    if (failedList.empty())
        m_logger->info("PDU session resource(s) setup for UE[%d] count[%d]", ue->ctxId,
                       static_cast<int>(successList.size()));
    else if (successList.empty())
        m_logger->err("PDU session resource(s) setup was failed for UE[%d] count[%d]", ue->ctxId,
                      static_cast<int>(failedList.size()));
    else
        m_logger->err("PDU session establishment is partially successful for UE[%d], success[%d], failed[%d]",
                      static_cast<int>(successList.size()), static_cast<int>(failedList.size()));
}

std::optional<NgapCause> NgapTask::setupPduSessionResource(NgapUeContext *ue, PduSessionResource *resource)
{
    if (ue->pduSessions.count(resource->psi) != 0)
    {
        m_logger->err("PDU session resource could not setup: PDU Session ID[%d] is already active", resource->psi);
        return NgapCause::Protocol_message_not_compatible_with_receiver_state;
    }

    if (resource->sessionType != PduSessionType::IPv4 && resource->sessionType != PduSessionType::IPv6 &&
        resource->sessionType != PduSessionType::IPv4v6)
    {
        m_logger->err("PDU session resource could not setup: PDU session type is not supported");
        return NgapCause::RadioNetwork_unspecified;
    }

    if (resource->upTunnel.address.length() == 0)
    {
        m_logger->err("PDU session resource could not setup: Uplink TNL information is missing");
        return NgapCause::Protocol_transfer_syntax_error;
    }

    if (resource->qosFlows == nullptr || resource->qosFlows->list.count == 0)
    {
        m_logger->err("PDU session resource could not setup: QoS flow list is null or empty");
        return NgapCause::Protocol_semantic_error;
    }

    std::string gtpIp = m_base->config->gtpAdvertiseIp.value_or(m_base->config->gtpIp);

    resource->downTunnel.address = utils::IpToOctetString(gtpIp);
    resource->downTunnel.teid = ++m_downlinkTeidCounter;

    auto w = std::make_unique<NmGnbNgapToGtp>(NmGnbNgapToGtp::SESSION_CREATE);
    w->resource = resource;
    m_base->gtpTask->push(std::move(w));

    ue->pduSessions.insert(resource->psi);

    return {};
}

void NgapTask::receiveSessionResourceReleaseCommand(int amfId, ASN_NGAP_PDUSessionResourceReleaseCommand *msg)
{
    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPair(msg));
    if (ue == nullptr)
        return;

    std::set<int> psIds{};

    auto *ieReq = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceToReleaseListRelCmd);
    if (ieReq)
    {
        auto &list = ieReq->PDUSessionResourceToReleaseListRelCmd.list;

        for (int i = 0; i < list.count; i++)
        {
            auto &item = list.array[i];
            if (item)
                psIds.insert(static_cast<int>(item->pDUSessionID));
        }
    }

    ieReq = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_NAS_PDU);
    if (ieReq)
        deliverDownlinkNas(ue->ctxId, asn::GetOctetString(ieReq->NAS_PDU));

    auto *ieResp = asn::New<ASN_NGAP_PDUSessionResourceReleaseResponseIEs>();
    ieResp->id = ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceReleasedListRelRes;
    ieResp->criticality = ASN_NGAP_Criticality_ignore;
    ieResp->value.present =
        ASN_NGAP_PDUSessionResourceReleaseResponseIEs__value_PR_PDUSessionResourceReleasedListRelRes;

    // Perform release
    for (auto &psi : psIds)
    {
        auto w = std::make_unique<NmGnbNgapToGtp>(NmGnbNgapToGtp::SESSION_RELEASE);
        w->ueId = ue->ctxId;
        w->psi = psi;
        m_base->gtpTask->push(std::move(w));

        ue->pduSessions.erase(psi);
    }

    for (auto &psi : psIds)
    {
        auto *tr = asn::New<ASN_NGAP_PDUSessionResourceReleaseResponseTransfer>();

        OctetString encodedTr = ngap_encode::EncodeS(asn_DEF_ASN_NGAP_PDUSessionResourceReleaseResponseTransfer, tr);

        if (encodedTr.length() == 0)
            throw std::runtime_error("PDUSessionResourceReleaseResponseTransfer encoding failed");

        asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceReleaseResponseTransfer, tr);

        auto *item = asn::New<ASN_NGAP_PDUSessionResourceReleasedItemRelRes>();
        item->pDUSessionID = static_cast<ASN_NGAP_PDUSessionID_t>(psi);
        asn::SetOctetString(item->pDUSessionResourceReleaseResponseTransfer, encodedTr);

        asn::SequenceAdd(ieResp->value.choice.PDUSessionResourceReleasedListRelRes, item);
    }

    auto *respPdu = asn::ngap::NewMessagePdu<ASN_NGAP_PDUSessionResourceReleaseResponse>({ieResp});
    sendNgapUeAssociated(ue->ctxId, respPdu);

    m_logger->info("PDU session resource(s) released for UE[%d] count[%d]", ue->ctxId, static_cast<int>(psIds.size()));
}

} // namespace nr::gnb