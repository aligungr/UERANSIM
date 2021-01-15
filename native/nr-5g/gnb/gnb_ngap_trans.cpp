//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_task.hpp"
#include "gnb_nts.hpp"

#include <asn_ngap.hpp>
#include <asn_utils.hpp>

#include <ASN_NGAP_InitiatingMessage.h>
#include <ASN_NGAP_NGAP-PDU.h>
#include <ASN_NGAP_SuccessfulOutcome.h>
#include <ASN_NGAP_UnsuccessfulOutcome.h>

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

    auto res = asn_encode_to_new_buffer(nullptr, ATS_ALIGNED_CANONICAL_PER, &asn_DEF_ASN_NGAP_NGAP_PDU, pdu);

    if (res.buffer == nullptr || res.result.encoded < 0)
        logger->err("NGAP APER encoding failed");
    else
    {
        auto *msg = new NwSctpSendMessage(amf->ctxId, 0, reinterpret_cast<uint8_t *>(res.buffer), 0,
                                          static_cast<size_t>(res.result.encoded));
        sctpTask->push(msg);
        logger->debug("Non-UE-associated NGAP PDU with length %d is sent to SCTP layer", res.result.encoded);

        // todo: trigger OnSend
    }

    asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
}

void NgapTask::sendNgapUeAssociated(int ueId, ASN_NGAP_NGAP_PDU *pdu)
{
    // TODO
}

void NgapTask::receiveSctpMessage(NwSctpClientReceive *msg)
{
    auto *amf = findAmfContext(msg->clientId);
    if (amf == nullptr)
    {
        delete msg;
        return;
    }

    auto *pdu = asn::New<ASN_NGAP_NGAP_PDU>();
    auto res = aper_decode(nullptr, &asn_DEF_ASN_NGAP_NGAP_PDU, reinterpret_cast<void **>(&pdu), msg->buffer,
                           msg->length, 0, 0);

    if (res.code != RC_OK)
    {
        logger->err("APER decoding failed for SCTP message");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        sendErrorIndication(msg->clientId, NgapCause::CauseProtocol_transfer_syntax_error);
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

} // namespace nr::gnb