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

#include <ASN_NGAP_NGAP-PDU.h>

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
        logger->err("NGAP PDU ASN constraint validation failed.");
        asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
        return;
    }

    auto res = asn_encode_to_new_buffer(nullptr, ATS_ALIGNED_CANONICAL_PER, &asn_DEF_ASN_NGAP_NGAP_PDU, pdu);

    if (res.buffer == nullptr || res.result.encoded < 0)
        logger->err("NGAP APER encoding failed.");
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

} // namespace nr::gnb