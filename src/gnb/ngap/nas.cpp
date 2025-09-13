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

#include <gnb/rrc/task.hpp>

#include <asn/ngap/ASN_NGAP_DownlinkNASTransport.h>
#include <asn/ngap/ASN_NGAP_InitialUEMessage.h>
#include <asn/ngap/ASN_NGAP_InitiatingMessage.h>
#include <asn/ngap/ASN_NGAP_NASNonDeliveryIndication.h>
#include <asn/ngap/ASN_NGAP_NGAP-PDU.h>
#include <asn/ngap/ASN_NGAP_ProtocolIE-Field.h>
#include <asn/ngap/ASN_NGAP_RerouteNASRequest.h>
#include <asn/ngap/ASN_NGAP_UplinkNASTransport.h>

namespace nr::gnb
{

void NgapTask::handleInitialNasTransport(int ueId, const OctetString &nasPdu, int64_t rrcEstablishmentCause,
                                         const std::optional<GutiMobileIdentity> &sTmsi)
{
    m_logger->debug("Initial NAS message received from UE[%d]", ueId);

    if (m_ueCtx.count(ueId))
    {
        m_logger->err("UE context[%d] already exists", ueId);
        return;
    }

    createUeContext(ueId);

    auto *ueCtx = findUeContext(ueId);
    if (ueCtx == nullptr)
        return;
    auto *amfCtx = findAmfContext(ueCtx->associatedAmfId);
    if (amfCtx == nullptr)
        return;

    if (amfCtx->state != EAmfState::CONNECTED)
    {
        m_logger->err("Initial NAS transport failure. AMF is not in connected state.");
        return;
    }

    amfCtx->nextStream = (amfCtx->nextStream + 1) % amfCtx->association.outStreams;
    if ((amfCtx->nextStream == 0) && (amfCtx->association.outStreams > 1))
        amfCtx->nextStream += 1;
    ueCtx->uplinkStream = amfCtx->nextStream;

    std::vector<ASN_NGAP_InitialUEMessage_IEs *> ies;

    auto *ieEstablishmentCause = asn::New<ASN_NGAP_InitialUEMessage_IEs>();
    ieEstablishmentCause->id = ASN_NGAP_ProtocolIE_ID_id_RRCEstablishmentCause;
    ieEstablishmentCause->criticality = ASN_NGAP_Criticality_ignore;
    ieEstablishmentCause->value.present = ASN_NGAP_InitialUEMessage_IEs__value_PR_RRCEstablishmentCause;
    ieEstablishmentCause->value.choice.RRCEstablishmentCause = rrcEstablishmentCause;
    ies.push_back(ieEstablishmentCause);

    auto *ieCtxRequest = asn::New<ASN_NGAP_InitialUEMessage_IEs>();
    ieCtxRequest->id = ASN_NGAP_ProtocolIE_ID_id_UEContextRequest;
    ieCtxRequest->criticality = ASN_NGAP_Criticality_ignore;
    ieCtxRequest->value.present = ASN_NGAP_InitialUEMessage_IEs__value_PR_UEContextRequest;
    ieCtxRequest->value.choice.UEContextRequest = ASN_NGAP_UEContextRequest_requested;
    ies.push_back(ieCtxRequest);

    auto *ieNasPdu = asn::New<ASN_NGAP_InitialUEMessage_IEs>();
    ieNasPdu->id = ASN_NGAP_ProtocolIE_ID_id_NAS_PDU;
    ieNasPdu->criticality = ASN_NGAP_Criticality_reject;
    ieNasPdu->value.present = ASN_NGAP_InitialUEMessage_IEs__value_PR_NAS_PDU;
    asn::SetOctetString(ieNasPdu->value.choice.NAS_PDU, nasPdu);
    ies.push_back(ieNasPdu);

    if (sTmsi)
    {
        auto *ieTmsi = asn::New<ASN_NGAP_InitialUEMessage_IEs>();
        ieTmsi->id = ASN_NGAP_ProtocolIE_ID_id_FiveG_S_TMSI;
        ieTmsi->criticality = ASN_NGAP_Criticality_reject;
        ieTmsi->value.present = ASN_NGAP_InitialUEMessage_IEs__value_PR_FiveG_S_TMSI;

        asn::SetBitStringInt<10>(sTmsi->amfSetId, ieTmsi->value.choice.FiveG_S_TMSI.aMFSetID);
        asn::SetBitStringInt<6>(sTmsi->amfPointer, ieTmsi->value.choice.FiveG_S_TMSI.aMFPointer);
        asn::SetOctetString4(ieTmsi->value.choice.FiveG_S_TMSI.fiveG_TMSI, sTmsi->tmsi);
        ies.push_back(ieTmsi);
    }

    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_InitialUEMessage>(ies);
    sendNgapUeAssociated(ueId, pdu);
}

void NgapTask::deliverDownlinkNas(int ueId, OctetString &&nasPdu)
{
    auto w = std::make_unique<NmGnbNgapToRrc>(NmGnbNgapToRrc::NAS_DELIVERY);
    w->ueId = ueId;
    w->pdu = std::move(nasPdu);
    m_base->rrcTask->push(std::move(w));
}

void NgapTask::handleUplinkNasTransport(int ueId, const OctetString &nasPdu)
{
    auto *ue = findUeContext(ueId);
    if (ue == nullptr)
        return;

    auto *ieNasPdu = asn::New<ASN_NGAP_UplinkNASTransport_IEs>();
    ieNasPdu->id = ASN_NGAP_ProtocolIE_ID_id_NAS_PDU;
    ieNasPdu->criticality = ASN_NGAP_Criticality_reject;
    ieNasPdu->value.present = ASN_NGAP_UplinkNASTransport_IEs__value_PR_NAS_PDU;
    asn::SetOctetString(ieNasPdu->value.choice.NAS_PDU, nasPdu);

    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_UplinkNASTransport>({ieNasPdu});
    sendNgapUeAssociated(ueId, pdu);
}

void NgapTask::sendNasNonDeliveryIndication(int ueId, const OctetString &nasPdu, NgapCause cause)
{
    m_logger->debug("Sending non-delivery indication for UE[%d]", ueId);

    auto *ieNasPdu = asn::New<ASN_NGAP_NASNonDeliveryIndication_IEs>();
    ieNasPdu->id = ASN_NGAP_ProtocolIE_ID_id_NAS_PDU;
    ieNasPdu->criticality = ASN_NGAP_Criticality_ignore;
    ieNasPdu->value.present = ASN_NGAP_NASNonDeliveryIndication_IEs__value_PR_NAS_PDU;
    asn::SetOctetString(ieNasPdu->value.choice.NAS_PDU, nasPdu);

    auto *ieCause = asn::New<ASN_NGAP_NASNonDeliveryIndication_IEs>();
    ieCause->id = ASN_NGAP_ProtocolIE_ID_id_Cause;
    ieCause->criticality = ASN_NGAP_Criticality_ignore;
    ieCause->value.present = ASN_NGAP_NASNonDeliveryIndication_IEs__value_PR_Cause;
    ngap_utils::ToCauseAsn_Ref(cause, ieCause->value.choice.Cause);

    auto *pdu = asn::ngap::NewMessagePdu<ASN_NGAP_NASNonDeliveryIndication>({ieNasPdu, ieCause});
    sendNgapUeAssociated(ueId, pdu);
}

void NgapTask::receiveDownlinkNasTransport(int amfId, ASN_NGAP_DownlinkNASTransport *msg)
{
    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPair(msg));
    if (ue == nullptr)
        return;

    auto *ieNasPdu = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_NAS_PDU);
    if (ieNasPdu)
        deliverDownlinkNas(ue->ctxId, asn::GetOctetString(ieNasPdu->NAS_PDU));
}

void NgapTask::receiveRerouteNasRequest(int amfId, ASN_NGAP_RerouteNASRequest *msg)
{
    m_logger->debug("Reroute NAS request received");

    auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPair(msg));
    if (ue == nullptr)
        return;

    auto *ieNgapMessage = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_NGAP_Message);
    auto *ieAmfSetId = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMFSetID);
    auto *ieAllowedNssai = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AllowedNSSAI);

    auto ngapPdu = asn::New<ASN_NGAP_NGAP_PDU>();
    ngapPdu->present = ASN_NGAP_NGAP_PDU_PR_initiatingMessage;
    ngapPdu->choice.initiatingMessage = asn::New<ASN_NGAP_InitiatingMessage>();
    ngapPdu->choice.initiatingMessage->procedureCode = ASN_NGAP_ProcedureCode_id_InitialUEMessage;
    ngapPdu->choice.initiatingMessage->criticality = ASN_NGAP_Criticality_ignore;
    ngapPdu->choice.initiatingMessage->value.present = ASN_NGAP_InitiatingMessage__value_PR_InitialUEMessage;

    auto *initialUeMessage = &ngapPdu->choice.initiatingMessage->value.choice.InitialUEMessage;

    if (!ngap_encode::DecodeInPlace(asn_DEF_ASN_NGAP_InitialUEMessage, ieNgapMessage->OCTET_STRING, &initialUeMessage))
    {
        m_logger->err("APER decoding failed in Reroute NAS Request");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, ngapPdu);
        sendErrorIndication(amfId, NgapCause::Protocol_transfer_syntax_error);
        return;
    }

    if (ieAllowedNssai)
    {
        auto *oldAllowedNssai = asn::ngap::GetProtocolIe(initialUeMessage, ASN_NGAP_ProtocolIE_ID_id_AllowedNSSAI);
        if (oldAllowedNssai)
            asn::DeepCopy(asn_DEF_ASN_NGAP_AllowedNSSAI, ieAllowedNssai->AllowedNSSAI, &oldAllowedNssai->AllowedNSSAI);
        else
        {
            auto *newAllowedNssai = asn::New<ASN_NGAP_InitialUEMessage_IEs>();
            newAllowedNssai->id = ASN_NGAP_ProtocolIE_ID_id_AllowedNSSAI;
            newAllowedNssai->criticality = ASN_NGAP_Criticality_reject;
            newAllowedNssai->value.present = ASN_NGAP_InitialUEMessage_IEs__value_PR_AllowedNSSAI;

            asn::ngap::AddProtocolIe(*initialUeMessage, newAllowedNssai);
        }
    }

    auto *newAmf = selectNewAmfForReAllocation(ue->ctxId, amfId, asn::GetBitStringInt<10>(ieAmfSetId->AMFSetID));
    if (newAmf == nullptr)
    {
        m_logger->err("AMF selection for re-allocation failed. Could not find a suitable AMF.");
        return;
    }

    sendNgapUeAssociated(ue->ctxId, ngapPdu);
}

} // namespace nr::gnb
