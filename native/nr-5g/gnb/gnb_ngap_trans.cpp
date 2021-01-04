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
        return;

    auto res = asn_encode_to_new_buffer(nullptr, ATS_ALIGNED_CANONICAL_PER, &asn_DEF_ASN_NGAP_NGAP_PDU, pdu);

    if (res.buffer == nullptr)
        logger->err("NGAP APER encoding failed.");
    else
    {
        auto *msg = new NwSctpSendMessage(amf->sctpClientId, 0, reinterpret_cast<uint8_t *>(res.buffer), 0,
                                          static_cast<size_t>(res.result.encoded));
        sctpTask->push(msg);

        // todo: trigger OnSend
    }

    asn::Free(asn_DEF_ASN_NGAP_NGAP_PDU, pdu);
}

} // namespace nr::gnb