//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "encode.hpp"
#include "task.hpp"
#include "utils.hpp"

#include <gnb/app/task.hpp>
#include <gnb/nts.hpp>
#include <gnb/sctp/task.hpp>
#include <lib/asn/ngap.hpp>
#include <lib/asn/utils.hpp>

#include <asn/ngap/ASN_NGAP_AMF-UE-NGAP-ID.h>
#include <asn/ngap/ASN_NGAP_InitiatingMessage.h>
#include <asn/ngap/ASN_NGAP_NGAP-PDU.h>
#include <asn/ngap/ASN_NGAP_ProtocolIE-Field.h>
#include <asn/ngap/ASN_NGAP_RAN-UE-NGAP-ID.h>
#include <asn/ngap/ASN_NGAP_SuccessfulOutcome.h>
#include <asn/ngap/ASN_NGAP_UnsuccessfulOutcome.h>
#include <asn/ngap/ASN_NGAP_UserLocationInformation.h>
#include <asn/ngap/ASN_NGAP_UserLocationInformationNR.h>

static e_ASN_NGAP_Criticality FindCriticalityOfUserIe(ASN_NGAP_NGAP_PDU *pdu, ASN_NGAP_ProtocolIE_ID_t ieId)
{
    auto procedureCode =
        pdu->present == ASN_NGAP_NGAP_PDU_PR_initiatingMessage   ? pdu->choice.initiatingMessage->procedureCode
        : pdu->present == ASN_NGAP_NGAP_PDU_PR_successfulOutcome ? pdu->choice.successfulOutcome->procedureCode
                                                                 : pdu->choice.unsuccessfulOutcome->procedureCode;

    if (ieId == ASN_NGAP_ProtocolIE_ID_id_UserLocationInformation)
    {
        return procedureCode == ASN_NGAP_ProcedureCode_id_InitialUEMessage ? ASN_NGAP_Criticality_reject
                                                                           : ASN_NGAP_Criticality_ignore;
    }

    if (ieId == ASN_NGAP_ProtocolIE_ID_id_RAN_UE_NGAP_ID || ieId == ASN_NGAP_ProtocolIE_ID_id_AMF_UE_NGAP_ID)
    {
        if (procedureCode == ASN_NGAP_ProcedureCode_id_RerouteNASRequest)
        {
            return ieId == ASN_NGAP_ProtocolIE_ID_id_RAN_UE_NGAP_ID ? ASN_NGAP_Criticality_reject
                                                                    : ASN_NGAP_Criticality_ignore;
        }

        if (pdu->present == ASN_NGAP_NGAP_PDU_PR_initiatingMessage)
        {
            if (procedureCode == ASN_NGAP_ProcedureCode_id_UEContextReleaseRequest ||
                procedureCode == ASN_NGAP_ProcedureCode_id_HandoverPreparation)
                return ASN_NGAP_Criticality_reject;
        }

        if (procedureCode == ASN_NGAP_ProcedureCode_id_PDUSessionResourceNotify ||
            procedureCode == ASN_NGAP_ProcedureCode_id_PDUSessionResourceModifyIndication ||
            procedureCode == ASN_NGAP_ProcedureCode_id_RRCInactiveTransitionReport ||
            procedureCode == ASN_NGAP_ProcedureCode_id_HandoverNotification ||
            procedureCode == ASN_NGAP_ProcedureCode_id_PathSwitchRequest ||
            procedureCode == ASN_NGAP_ProcedureCode_id_HandoverCancel ||
            procedureCode == ASN_NGAP_ProcedureCode_id_UplinkRANStatusTransfer ||
            procedureCode == ASN_NGAP_ProcedureCode_id_InitialUEMessage ||
            procedureCode == ASN_NGAP_ProcedureCode_id_DownlinkNASTransport ||
            procedureCode == ASN_NGAP_ProcedureCode_id_UplinkNASTransport ||
            procedureCode == ASN_NGAP_ProcedureCode_id_NASNonDeliveryIndication ||
            procedureCode == ASN_NGAP_ProcedureCode_id_UplinkUEAssociatedNRPPaTransport ||
            procedureCode == ASN_NGAP_ProcedureCode_id_UplinkNonUEAssociatedNRPPaTransport ||
            procedureCode == ASN_NGAP_ProcedureCode_id_CellTrafficTrace ||
            procedureCode == ASN_NGAP_ProcedureCode_id_TraceStart ||
            procedureCode == ASN_NGAP_ProcedureCode_id_DeactivateTrace ||
            procedureCode == ASN_NGAP_ProcedureCode_id_TraceFailureIndication ||
            procedureCode == ASN_NGAP_ProcedureCode_id_LocationReport ||
            procedureCode == ASN_NGAP_ProcedureCode_id_LocationReportingControl ||
            procedureCode == ASN_NGAP_ProcedureCode_id_LocationReportingFailureIndication ||
            procedureCode == ASN_NGAP_ProcedureCode_id_UERadioCapabilityInfoIndication)
            return ASN_NGAP_Criticality_reject;
    }

    return ASN_NGAP_Criticality_ignore;
}

namespace nr::gnb
{

void NgapTask::sendNgapNonUe(int associatedAmf, ASN_NGAP_NGAP_PDU *pdu)
{
    auto *amf = findAmfContext(associatedAmf);
    if (amf == nullptr)
    {
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    char errorBuffer[1024];
    size_t len;

    if (asn_check_constraints(&asn_DEF_ASN_NGAP_NGAP_PDU, pdu, errorBuffer, &len) != 0)
    {
        m_logger->err("NGAP PDU ASN constraint validation failed");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    ssize_t encoded;
    uint8_t *buffer;
    if (!ngap_encode::Encode(asn_DEF_ASN_NGAP_NGAP_PDU, pdu, encoded, buffer))
        m_logger->err("NGAP APER encoding failed");
    else
    {
        auto msg = std::make_unique<NmGnbSctp>(NmGnbSctp::SEND_MESSAGE);
        msg->clientId = amf->ctxId;
        msg->stream = 0;
        msg->buffer = UniqueBuffer{buffer, static_cast<size_t>(encoded)};
        m_base->sctpTask->push(std::move(msg));

        if (m_base->nodeListener)
        {
            std::string xer = ngap_encode::EncodeXer(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
            if (xer.length() > 0)
            {
                m_base->nodeListener->onSend(app::NodeType::GNB, m_base->config->name, app::NodeType::AMF, amf->amfName,
                                             app::ConnectionType::NGAP, xer);
            }
        }
    }

    asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
}

void NgapTask::sendNgapUeAssociated(int ueId, ASN_NGAP_NGAP_PDU *pdu)
{
    /* Find UE and AMF contexts */

    auto *ue = findUeContext(ueId);
    if (ue == nullptr)
    {
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    auto *amf = findAmfContext(ue->associatedAmfId);
    if (amf == nullptr)
    {
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    /* Insert UE-related information elements */
    {
        if (ue->amfUeNgapId > 0)
        {
            asn::ngap::AddProtocolIeIfUsable(
                *pdu, asn_DEF_ASN_NGAP_AMF_UE_NGAP_ID, ASN_NGAP_ProtocolIE_ID_id_AMF_UE_NGAP_ID,
                FindCriticalityOfUserIe(pdu, ASN_NGAP_ProtocolIE_ID_id_AMF_UE_NGAP_ID), [ue](void *mem) {
                    auto &id = *reinterpret_cast<ASN_NGAP_AMF_UE_NGAP_ID_t *>(mem);
                    asn::SetSigned64(ue->amfUeNgapId, id);
                });
        }

        asn::ngap::AddProtocolIeIfUsable(
            *pdu, asn_DEF_ASN_NGAP_RAN_UE_NGAP_ID, ASN_NGAP_ProtocolIE_ID_id_RAN_UE_NGAP_ID,
            FindCriticalityOfUserIe(pdu, ASN_NGAP_ProtocolIE_ID_id_RAN_UE_NGAP_ID),
            [ue](void *mem) { *reinterpret_cast<ASN_NGAP_RAN_UE_NGAP_ID_t *>(mem) = ue->ranUeNgapId; });

        asn::ngap::AddProtocolIeIfUsable(
            *pdu, asn_DEF_ASN_NGAP_UserLocationInformation, ASN_NGAP_ProtocolIE_ID_id_UserLocationInformation,
            FindCriticalityOfUserIe(pdu, ASN_NGAP_ProtocolIE_ID_id_UserLocationInformation), [this](void *mem) {
                auto *loc = reinterpret_cast<ASN_NGAP_UserLocationInformation *>(mem);
                loc->present = ASN_NGAP_UserLocationInformation_PR_userLocationInformationNR;
                loc->choice.userLocationInformationNR = asn::New<ASN_NGAP_UserLocationInformationNR>();

                auto &nr = loc->choice.userLocationInformationNR;
                nr->timeStamp = asn::New<ASN_NGAP_TimeStamp_t>();

                ngap_utils::ToPlmnAsn_Ref(m_base->config->plmn, nr->nR_CGI.pLMNIdentity);
                asn::SetBitStringLong<36>(m_base->config->nci, nr->nR_CGI.nRCellIdentity);
                ngap_utils::ToPlmnAsn_Ref(m_base->config->plmn, nr->tAI.pLMNIdentity);
                asn::SetOctetString3(nr->tAI.tAC, octet3{m_base->config->tac});
                asn::SetOctetString4(*nr->timeStamp, octet4{utils::CurrentTimeStamp().seconds32()});
            });
    }

    /* Encode and send the PDU */

    char errorBuffer[1024];
    size_t len;

    if (asn_check_constraints(&asn_DEF_ASN_NGAP_NGAP_PDU, pdu, errorBuffer, &len) != 0)
    {
        m_logger->err("NGAP PDU ASN constraint validation failed");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    ssize_t encoded;
    uint8_t *buffer;
    if (!ngap_encode::Encode(asn_DEF_ASN_NGAP_NGAP_PDU, pdu, encoded, buffer))
        m_logger->err("NGAP APER encoding failed");
    else
    {
        auto msg = std::make_unique<NmGnbSctp>(NmGnbSctp::SEND_MESSAGE);
        msg->clientId = amf->ctxId;
        msg->stream = ue->uplinkStream;
        msg->buffer = UniqueBuffer{buffer, static_cast<size_t>(encoded)};
        m_base->sctpTask->push(std::move(msg));

        if (m_base->nodeListener)
        {
            std::string xer = ngap_encode::EncodeXer(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
            if (xer.length() > 0)
            {
                m_base->nodeListener->onSend(app::NodeType::GNB, m_base->config->name, app::NodeType::AMF, amf->amfName,
                                             app::ConnectionType::NGAP, xer);
            }
        }
    }

    asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
}

void NgapTask::handleSctpMessage(int amfId, uint16_t stream, const UniqueBuffer &buffer)
{
    auto *amf = findAmfContext(amfId);
    if (amf == nullptr)
        return;

    auto *pdu = ngap_encode::Decode<ASN_NGAP_NGAP_PDU>(asn_DEF_ASN_NGAP_NGAP_PDU, buffer.data(), buffer.size());
    if (pdu == nullptr)
    {
        m_logger->err("APER decoding failed for SCTP message");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        sendErrorIndication(amfId, NgapCause::Protocol_transfer_syntax_error);
        return;
    }

    if (m_base->nodeListener)
    {
        std::string xer = ngap_encode::EncodeXer(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        if (xer.length() > 0)
        {
            m_base->nodeListener->onReceive(app::NodeType::GNB, m_base->config->name, app::NodeType::AMF, amf->amfName,
                                            app::ConnectionType::NGAP, xer);
        }
    }

    if (!handleSctpStreamId(amf->ctxId, stream, *pdu))
    {
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    if (pdu->present == ASN_NGAP_NGAP_PDU_PR_initiatingMessage)
    {
        auto value = pdu->choice.initiatingMessage->value;
        switch (value.present)
        {
        case ASN_NGAP_InitiatingMessage__value_PR_ErrorIndication:
            receiveErrorIndication(amf->ctxId, &value.choice.ErrorIndication);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_InitialContextSetupRequest:
            receiveInitialContextSetup(amf->ctxId, &value.choice.InitialContextSetupRequest);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_RerouteNASRequest:
            receiveRerouteNasRequest(amf->ctxId, &value.choice.RerouteNASRequest);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_UEContextReleaseCommand:
            receiveContextRelease(amf->ctxId, &value.choice.UEContextReleaseCommand);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_UEContextModificationRequest:
            receiveContextModification(amf->ctxId, &value.choice.UEContextModificationRequest);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_PDUSessionResourceSetupRequest:
            receiveSessionResourceSetupRequest(amf->ctxId, &value.choice.PDUSessionResourceSetupRequest);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_DownlinkNASTransport:
            receiveDownlinkNasTransport(amf->ctxId, &value.choice.DownlinkNASTransport);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_AMFConfigurationUpdate:
            receiveAmfConfigurationUpdate(amf->ctxId, &value.choice.AMFConfigurationUpdate);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_OverloadStart:
            receiveOverloadStart(amf->ctxId, &value.choice.OverloadStart);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_OverloadStop:
            receiveOverloadStop(amf->ctxId, &value.choice.OverloadStop);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_PDUSessionResourceReleaseCommand:
            receiveSessionResourceReleaseCommand(amf->ctxId, &value.choice.PDUSessionResourceReleaseCommand);
            break;
        case ASN_NGAP_InitiatingMessage__value_PR_Paging:
            receivePaging(amf->ctxId, &value.choice.Paging);
            break;
        default:
            m_logger->err("Unhandled NGAP initiating-message received (%d)", value.present);
            break;
        }
    }
    else if (pdu->present == ASN_NGAP_NGAP_PDU_PR_successfulOutcome)
    {
        auto value = pdu->choice.successfulOutcome->value;
        switch (value.present)
        {
        case ASN_NGAP_SuccessfulOutcome__value_PR_NGSetupResponse:
            receiveNgSetupResponse(amf->ctxId, &value.choice.NGSetupResponse);
            break;
        default:
            m_logger->err("Unhandled NGAP successful-outcome received (%d)", value.present);
            break;
        }
    }
    else if (pdu->present == ASN_NGAP_NGAP_PDU_PR_unsuccessfulOutcome)
    {
        auto value = pdu->choice.unsuccessfulOutcome->value;
        switch (value.present)
        {
        case ASN_NGAP_UnsuccessfulOutcome__value_PR_NGSetupFailure:
            receiveNgSetupFailure(amf->ctxId, &value.choice.NGSetupFailure);
            break;
        default:
            m_logger->err("Unhandled NGAP unsuccessful-outcome received (%d)", value.present);
            break;
        }
    }
    else
    {
        m_logger->warn("Empty NGAP PDU ignored");
    }

    asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
}

bool NgapTask::handleSctpStreamId(int amfId, int stream, const ASN_NGAP_NGAP_PDU &pdu)
{
    if (m_base->config->ignoreStreamIds)
        return true;

    auto *ptr =
        asn::ngap::FindProtocolIeInPdu(pdu, asn_DEF_ASN_NGAP_UE_NGAP_IDs, ASN_NGAP_ProtocolIE_ID_id_UE_NGAP_IDs);
    if (ptr != nullptr)
    {
        if (stream == 0)
        {
            m_logger->err("Received stream number == 0 in UE-associated signalling");
            sendErrorIndication(amfId, NgapCause::Protocol_unspecified);
            return false;
        }

        auto &ids = *reinterpret_cast<ASN_NGAP_UE_NGAP_IDs *>(ptr);
        auto *ue = findUeByNgapIdPair(amfId, ngap_utils::FindNgapIdPairFromAsnNgapIds(ids));
        if (ue == nullptr)
            return false;

        if (ue->downlinkStream == 0)
            ue->downlinkStream = stream;
        else if (ue->downlinkStream != stream)
        {
            m_logger->err("received stream number is inconsistent. received %d, expected :%d", stream,
                          ue->downlinkStream);
            sendErrorIndication(amfId, NgapCause::Protocol_unspecified);
            return false;
        }
    }
    else
    {
        ptr = asn::ngap::FindProtocolIeInPdu(pdu, asn_DEF_ASN_NGAP_RAN_UE_NGAP_ID,
                                             ASN_NGAP_ProtocolIE_ID_id_RAN_UE_NGAP_ID);
        if (ptr != nullptr)
        {
            if (stream == 0)
            {
                m_logger->err("Received stream number == 0 in UE-associated signalling");
                sendErrorIndication(amfId, NgapCause::Protocol_unspecified);
                return false;
            }

            auto id = static_cast<int64_t>(*reinterpret_cast<ASN_NGAP_RAN_UE_NGAP_ID_t *>(ptr));
            auto *ue = findUeByRanId(id);
            if (ue == nullptr)
                return false;

            if (ue->downlinkStream == 0)
                ue->downlinkStream = stream;
            else if (ue->downlinkStream != stream)
            {
                m_logger->err("received stream number is inconsistent. received %d, expected :%d", stream,
                              ue->downlinkStream);
                sendErrorIndication(amfId, NgapCause::Protocol_unspecified);
                return false;
            }
        }
        else
        {
            if (stream != 0)
            {
                m_logger->err("Received stream number != 0 in non-UE-associated signalling");
                sendErrorIndication(amfId, NgapCause::Protocol_unspecified);
                return false;
            }
        }
    }

    return true;
}

} // namespace nr::gnb