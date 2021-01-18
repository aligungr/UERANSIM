//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_encode.hpp"
#include "gnb_ngap_task.hpp"
#include "gnb_ngap_utils.hpp"
#include "gnb_gtp_task.hpp"

#include <ASN_NGAP_AssociatedQosFlowItem.h>
#include <ASN_NGAP_AssociatedQosFlowList.h>
#include <ASN_NGAP_GTPTunnel.h>
#include <ASN_NGAP_PDUSessionResourceFailedToSetupItemSURes.h>
#include <ASN_NGAP_PDUSessionResourceSetupItemSUReq.h>
#include <ASN_NGAP_PDUSessionResourceSetupItemSURes.h>
#include <ASN_NGAP_PDUSessionResourceSetupRequest.h>
#include <ASN_NGAP_PDUSessionResourceSetupRequestTransfer.h>
#include <ASN_NGAP_PDUSessionResourceSetupResponse.h>
#include <ASN_NGAP_PDUSessionResourceSetupResponseTransfer.h>
#include <ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer.h>
#include <ASN_NGAP_ProtocolIE-Field.h>
#include <ASN_NGAP_QosFlowPerTNLInformationItem.h>
#include <ASN_NGAP_QosFlowPerTNLInformationList.h>
#include <ASN_NGAP_QosFlowSetupRequestItem.h>
#include <ASN_NGAP_QosFlowSetupRequestList.h>

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
                logger->err(
                    "Unable to decode a PDU session resource setup request transfer. Ignoring the relevant item");
                asn::Free(asn_DEF_ASN_NGAP_PDUSessionResourceSetupRequestTransfer, transfer);
                continue;
            }

            auto *resource = new PduSessionResource(ue->ctxId, static_cast<int>(item->pDUSessionID));

            auto *ie = asn::ngap::GetProtocolIe(transfer, ASN_NGAP_ProtocolIE_ID_id_PDUSessionAggregateMaximumBitRate);
            if (ie)
            {
                resource->sessionAmbr.dlAmbr =
                    asn::GetUnsigned64(ie->PDUSessionAggregateMaximumBitRate.pDUSessionAggregateMaximumBitRateDL);
                resource->sessionAmbr.ulAmbr =
                    asn::GetUnsigned64(ie->PDUSessionAggregateMaximumBitRate.pDUSessionAggregateMaximumBitRateUL);
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

            auto error = setupPduSessionResource(resource);
            if (error.has_value())
            {
                auto *tr = asn::New<ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer>();
                ngap_utils::ToCauseAsn_Ref(error.value(), tr->cause);

                OctetString encodedTr =
                    ngap_encode::EncodeS(asn_DEF_ASN_NGAP_PDUSessionResourceSetupUnsuccessfulTransfer, tr);
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
                asn::SetBitString(upInfo.choice.gTPTunnel->transportLayerAddress, resource->downTunnel.address);
                asn::SetOctetString(upInfo.choice.gTPTunnel->gTP_TEID, resource->downTunnel.teid);

                OctetString encodedTr =
                    ngap_encode::EncodeS(asn_DEF_ASN_NGAP_PDUSessionResourceSetupResponseTransfer, tr);
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
        logger->info("%d PDU session resource is established for UE %d", failedList.size(), ue->ctxId);
    else if (successList.empty())
        logger->err("%d PDU session resource establishment was failed for UE %d", failedList.size(), ue->ctxId);
    else
        logger->err("PDU session establishment is partially successful for UE %d, success: %d, failed: %d",
                    successList.size(), failedList.size());
}

std::optional<NgapCause> NgapTask::setupPduSessionResource(PduSessionResource *resource)
{
    if (resource->sessionType != PduSessionType::IPv4)
    {
        logger->err("PDU session resource could not setup: Only IPv4 is supported");
        return NgapCause::RadioNetwork_unspecified;
    }

    if (resource->upTunnel.address.length() == 0)
    {
        logger->err("PDU session resource could not setup: Uplink TNL information is missing");
        return NgapCause::Protocol_transfer_syntax_error;
    }

    if (resource->qosFlows == nullptr || resource->qosFlows->list.count == 0)
    {
        logger->err("PDU session resource could not setup: QoS flow list is null or empty");
        return NgapCause::Protocol_semantic_error;
    }

    resource->downTunnel.address = utils::IpToOctetString(base->config->gtpIp);
    resource->downTunnel.teid = ++downlinkTeidCounter;

    base->gtpTask->push(new NwPduSessionResourceCreate(resource));
    return {};
}

} // namespace nr::gnb