//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_app_task.hpp"
#include "gnb_ngap_encode.hpp"
#include "gnb_ngap_task.hpp"
#include "gnb_ngap_utils.hpp"
#include "gnb_nts.hpp"
#include "gnb_rrc_task.hpp"
#include "gnb_sctp_task.hpp"

#include <asn_ngap.hpp>
#include <asn_utils.hpp>

#include <ASN_NGAP_AMF-UE-NGAP-ID.h>
#include <ASN_NGAP_InitiatingMessage.h>
#include <ASN_NGAP_NGAP-PDU.h>
#include <ASN_NGAP_ProtocolIE-Field.h>
#include <ASN_NGAP_RAN-UE-NGAP-ID.h>
#include <ASN_NGAP_SuccessfulOutcome.h>
#include <ASN_NGAP_UnsuccessfulOutcome.h>
#include <ASN_NGAP_UserLocationInformation.h>
#include <ASN_NGAP_UserLocationInformationNR.h>

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
        logger->err("NGAP PDU ASN constraint validation failed");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    ssize_t encoded;
    uint8_t *buffer;
    if (!ngap_encode::Encode(asn_DEF_ASN_NGAP_NGAP_PDU, pdu, encoded, buffer))
        logger->err("NGAP APER encoding failed");
    else
    {
        auto *msg = new NwSctpSendMessage(amf->ctxId, 0, buffer, 0, static_cast<size_t>(encoded));
        base->sctpTask->push(msg);

        if (base->nodeListener)
        {
            std::string xer = ngap_encode::EncodeXer(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
            if (xer.length() > 0)
            {
                base->nodeListener->onSend(app::NodeType::GNB, base->config->name, app::NodeType::AMF, amf->amfName,
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
            asn::ngap::AddProtocolIeIfUsable(*pdu, asn_DEF_ASN_NGAP_AMF_UE_NGAP_ID,
                                             ASN_NGAP_ProtocolIE_ID_id_AMF_UE_NGAP_ID, ASN_NGAP_Criticality_reject,
                                             [ue](void *mem) {
                                                 auto &id = *reinterpret_cast<ASN_NGAP_AMF_UE_NGAP_ID_t *>(mem);
                                                 asn::SetSigned64(ue->amfUeNgapId, id);
                                             });
        }

        asn::ngap::AddProtocolIeIfUsable(
            *pdu, asn_DEF_ASN_NGAP_RAN_UE_NGAP_ID, ASN_NGAP_ProtocolIE_ID_id_RAN_UE_NGAP_ID,
            ASN_NGAP_Criticality_reject,
            [ue](void *mem) { *reinterpret_cast<ASN_NGAP_RAN_UE_NGAP_ID_t *>(mem) = ue->ranUeNgapId; });

        asn::ngap::AddProtocolIeIfUsable(
            *pdu, asn_DEF_ASN_NGAP_UserLocationInformation, ASN_NGAP_ProtocolIE_ID_id_UserLocationInformation,
            ASN_NGAP_Criticality_ignore, [this](void *mem) {
                auto *loc = reinterpret_cast<ASN_NGAP_UserLocationInformation *>(mem);
                loc->present = ASN_NGAP_UserLocationInformation_PR_userLocationInformationNR;
                loc->choice.userLocationInformationNR = asn::New<ASN_NGAP_UserLocationInformationNR>();

                auto &nr = loc->choice.userLocationInformationNR;
                nr->timeStamp = asn::New<ASN_NGAP_TimeStamp_t>();

                ngap_utils::ToPlmnAsn_Ref(base->config->plmn, nr->nR_CGI.pLMNIdentity);
                asn::SetBitStringLong<36>(base->config->nci, nr->nR_CGI.nRCellIdentity);
                ngap_utils::ToPlmnAsn_Ref(base->config->plmn, nr->tAI.pLMNIdentity);
                asn::SetOctetString3(nr->tAI.tAC, octet3{base->config->tac});
                asn::SetOctetString4(*nr->timeStamp, octet4{utils::CurrentTimeStamp().seconds32()});
            });
    }

    /* Encode and send the PDU */

    char errorBuffer[1024];
    size_t len;

    if (asn_check_constraints(&asn_DEF_ASN_NGAP_NGAP_PDU, pdu, errorBuffer, &len) != 0)
    {
        logger->err("NGAP PDU ASN constraint validation failed");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    ssize_t encoded;
    uint8_t *buffer;
    if (!ngap_encode::Encode(asn_DEF_ASN_NGAP_NGAP_PDU, pdu, encoded, buffer))
        logger->err("NGAP APER encoding failed");
    else
    {
        auto *msg = new NwSctpSendMessage(amf->ctxId, 0, buffer, 0, static_cast<size_t>(encoded));
        base->sctpTask->push(msg);

        if (base->nodeListener)
        {
            std::string xer = ngap_encode::EncodeXer(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
            if (xer.length() > 0)
            {
                base->nodeListener->onSend(app::NodeType::GNB, base->config->name, app::NodeType::AMF, amf->amfName,
                                           app::ConnectionType::NGAP, xer);
            }
        }
    }

    asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
}

void NgapTask::handleSctpMessage(NwSctpClientReceive *msg)
{
    auto *amf = findAmfContext(msg->clientId);
    if (amf == nullptr)
    {
        delete msg;
        return;
    }

    auto *pdu = ngap_encode::Decode<ASN_NGAP_NGAP_PDU>(asn_DEF_ASN_NGAP_NGAP_PDU, msg->buffer, msg->length);
    if (pdu == nullptr)
    {
        logger->err("APER decoding failed for SCTP message");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        sendErrorIndication(msg->clientId, NgapCause::Protocol_transfer_syntax_error);
        delete msg;
        return;
    }

    if (base->nodeListener)
    {
        std::string xer = ngap_encode::EncodeXer(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        if (xer.length() > 0)
        {
            base->nodeListener->onReceive(app::NodeType::GNB, base->config->name, app::NodeType::AMF, amf->amfName,
                                          app::ConnectionType::NGAP, xer);
        }
    }

    if (!handleSctpStreamId(amf->ctxId, msg->stream, *pdu))
    {
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        delete msg;
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
        default:
            logger->err("Unhandled NGAP initiating-message received (%d)", value.present);
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
            logger->err("Unhandled NGAP successful-outcome received (%d)", value.present);
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
            logger->err("Unhandled NGAP unsuccessful-outcome received (%d)", value.present);
            break;
        }
    }
    else
    {
        logger->warn("Empty NGAP PDU ignored");
    }

    asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
    delete msg;
}

bool NgapTask::handleSctpStreamId(int amfId, int stream, const ASN_NGAP_NGAP_PDU &pdu)
{
    if (base->config->ignoreStreamIds)
        return true;

    auto *ptr =
        asn::ngap::FindProtocolIeInPdu(pdu, asn_DEF_ASN_NGAP_UE_NGAP_IDs, ASN_NGAP_ProtocolIE_ID_id_UE_NGAP_IDs);
    if (ptr != nullptr)
    {
        if (stream == 0)
        {
            logger->err("Received stream number == 0 in UE-associated signalling");
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
            logger->err("received stream number is inconsistent. received %d, expected :%d", stream,
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
                logger->err("Received stream number == 0 in UE-associated signalling");
                sendErrorIndication(amfId, NgapCause::Protocol_unspecified);
                return false;
            }

            long id = static_cast<long>(*reinterpret_cast<ASN_NGAP_RAN_UE_NGAP_ID_t *>(ptr));
            auto *ue = findUeByRanId(id);
            if (ue == nullptr)
                return false;

            if (ue->downlinkStream == 0)
                ue->downlinkStream = stream;
            else if (ue->downlinkStream != stream)
            {
                logger->err("received stream number is inconsistent. received %d, expected :%d", stream,
                            ue->downlinkStream);
                sendErrorIndication(amfId, NgapCause::Protocol_unspecified);
                return false;
            }
        }
        else
        {
            if (stream != 0)
            {
                logger->err("Received stream number != 0 in non-UE-associated signalling");
                sendErrorIndication(amfId, NgapCause::Protocol_unspecified);
                return false;
            }
        }
    }

    return true;
}

} // namespace nr::gnb