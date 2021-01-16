//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <asn_ngap.hpp>
#include <asn_utils.hpp>
#include <common_types.hpp>
#include <convert.hpp>

#include "gnb_types.hpp"

#include <ASN_NGAP_Cause.h>
#include <ASN_NGAP_GUAMI.h>
#include <ASN_NGAP_PagingDRX.h>
#include <ASN_NGAP_ProtocolIE-Field.h>
#include <ASN_NGAP_SliceSupportItem.h>

namespace nr::gnb::ngap_utils
{

ASN_NGAP_PagingDRX_t PagingDrxToAsn(EPagingDrx pagingDrx);
std::string CauseToString(const ASN_NGAP_Cause_t &cause);
octet3 PlmnToOctet3(const Plmn &plmn);
PduSessionType PduSessionTypeFromAsn(const ASN_NGAP_PDUSessionType_t &source);

void PlmnFromAsn_Ref(const ASN_NGAP_PLMNIdentity_t &source, Plmn &target);
void GuamiFromAsn_Ref(const ASN_NGAP_GUAMI_t &guami, Guami &target);
void ToCauseAsn_Ref(NgapCause source, ASN_NGAP_Cause_t &target);

std::unique_ptr<SliceSupport> SliceSupportFromAsn_Unique(ASN_NGAP_SliceSupportItem &supportItem);

template <typename T>
inline NgapIdPair FindNgapIdPair(T *msg)
{
    auto *ieAmfUeNgapId = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMF_UE_NGAP_ID);
    auto *ieRanUeNgapId = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_RAN_UE_NGAP_ID);

    std::optional<int64_t> amfUeNgapId{}, ranUeNgapId{};
    if (ieAmfUeNgapId)
    {
        uint64_t v;
        if (asn_INTEGER2ulong(&ieAmfUeNgapId->AMF_UE_NGAP_ID, &v) == 0)
            amfUeNgapId = static_cast<int64_t>(v);
    }
    if (ieRanUeNgapId)
        ranUeNgapId = ieRanUeNgapId->RAN_UE_NGAP_ID;

    return NgapIdPair{amfUeNgapId, ranUeNgapId};
}

} // namespace nr::gnb::ngap_utils