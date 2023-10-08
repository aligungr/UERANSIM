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

#include <set>
#include <stdexcept>

#include <gnb/gtp/task.hpp>

#include <asn/ngap/ASN_NGAP_AssociatedQosFlowItem.h>
#include <asn/ngap/ASN_NGAP_AssociatedQosFlowList.h>
#include <asn/ngap/ASN_NGAP_GTPTunnel.h>
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
#include <asn/ngap/ASN_NGAP_QosFlowPerTNLInformationItem.h>
#include <asn/ngap/ASN_NGAP_QosFlowPerTNLInformationList.h>
#include <asn/ngap/ASN_NGAP_QosFlowSetupRequestItem.h>
#include <asn/ngap/ASN_NGAP_QosFlowSetupRequestList.h>

namespace nr::gnb
{

void NgapTask::receiveSessionResourceSetupRequest(int amfId, ASN_NGAP_PDUSessionResourceSetupRequest *msg)
{
    std::vector<ASN_NGAP_PDUSessionResourceSetupItemSURes *> successList;
    std::vector<ASN_NGAP_PDUSessionResourceFailedToSetupItemSURes *> failedList;

    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPair(msg));
    if (ue == nullptr)
        return;

    auto *ieList = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_PDUSessionResourceSetupListSUReq);
    if (ieList)
    {
        auto &list = ieList->PDUSessionResourceSetupListSUReq.list;
        for (int i = 0; i < list.count; i++)
        {
            auto &item = list.array[i];
            auto *transfer = ngap_encode::Decode<ASN_NGAP_PDUSessionResourceSetupRequestTransfer>(
                asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, item->pDUSessionResourceSetupRequestTransfer);
            if (transfer == nullptr)
            {
                m_logger->err(
                    "Unable to decode a PDU session resource setup request transfer. Ignoring the relevant item");
                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, transfer);
                continue;
            }

            auto *resource = new PduSessionResource(ue->ctxId, static_cast<int>(item->pDUSessionID));

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
                auto *ptr = asn::New<ASN_NGAP_QosFlowSetupRequestList>();
                asn::DeepCopy(asn_DEF_ASN_NGAP_QosFlowSetupRequestList, ie->QosFlowSetupRequestList, ptr);

                resource->qosFlows = asn::WrapUnique(ptr, asn_DEF_ASN_NGAP_QosFlowSetupRequestList);
            }

            auto error = setupPduSessionResource(ue, resource);
            if (error.has_value())
            {
                auto *tr = asn::New<ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer>();
                ngap_utils::ToCauseAsn_Ref(error.value(), tr->cause);

                OctetString encodedTr =
                    ngap_encode::EncodeS(asn_DEF_ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer, tr);

                if (encodedTr.length() == 0)
                    throw std::runtime_error("PDUSessionResourceSetupUnsuccessfulTransfer encoding failed");

                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer, tr);

                auto *res = asn::New<ASN_NGAP_PDUSessionResourceFailedToSetupItemSURes>();
                res->pDUSessionID = resource->psi;
                asn::SetOctetString(res->pDUSessionResourceSetupUnsuccessfulTransfer, encodedTr);

                failedList.push_back(res);
            }
            else
            {
                if (item->pDUSessionNAS_PDU)
                    deliverDownlinkNas(ue->ctxId, asn::GetOctetString(*item->pDUSessionNAS_PDU));

                auto *tr = asn::New<ASN_NGAP_PDUSessionResourceSetupResponseTransfer>();

                auto &qosList = resource->qosFlows->list;
                for (int iQos = 0; iQos < qosList.count; iQos++)
                {
                    auto *associatedQosFlowItem = asn::New<ASN_NGAP_AssociatedQosFlowItem>();
                    associatedQosFlowItem->qosFlowIdentifier = qosList.array[iQos]->qosFlowIdentifier;
                    asn::SequenceAdd(tr->dLQosFlowPerTNLInformation.associatedQosFlowList, associatedQosFlowItem);
                }

                auto &upInfo = tr->dLQosFlowPerTNLInformation.uPTransportLayerInformation;
                upInfo.present = ASN_NGAP_UPTransportLayerInformation_PR_gTPTunnel;
                upInfo.choice.gTPTunnel = asn::New<ASN_NGAP_GTPTunnel>();
                asn::SetBitString(upInfo.choice.gTPTunnel->transportLayerAddress, resource->downTunnel.address);
                asn::SetOctetString4(upInfo.choice.gTPTunnel->gTP_TEID, (octet4)resource->downTunnel.teid);

                OctetString encodedTr =
                    ngap_encode::EncodeS(asn_DEF_ASN_NGAP_PDUSessionResourceSetupResponseTransfer, tr);

                if (encodedTr.length() == 0)
                    throw std::runtime_error("PDUSessionResourceSetupResponseTransfer encoding failed");

                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupResponseTransfer, tr);

                auto *res = asn::New<ASN_NGAP_PDUSessionResourceSetupItemSURes>();
                res->pDUSessionID = resource->psi;
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
    if (resource->sessionType != PduSessionType::IPv4)
    {
        m_logger->err("PDU session resource could not setup: Only IPv4 is supported");
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