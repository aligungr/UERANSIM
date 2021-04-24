//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <gnb/types.hpp>
#include <lib/asn/ngap.hpp>
#include <lib/asn/utils.hpp>
#include <utils/common.hpp>
#include <utils/common_types.hpp>

#include <asn/ngap/ASN_NGAP_Cause.h>
#include <asn/ngap/ASN_NGAP_GUAMI.h>
#include <asn/ngap/ASN_NGAP_PagingDRX.h>
#include <asn/ngap/ASN_NGAP_ProtocolIE-Field.h>
#include <asn/ngap/ASN_NGAP_SliceSupportItem.h>
#include <asn/ngap/ASN_NGAP_UE-NGAP-ID-pair.h>

namespace nr::gnb::ngap_utils
{

ASN_NGAP_PagingDRX_t PagingDrxToAsn(EPagingDrx pagingDrx);
std::string CauseToString(const ASN_NGAP_Cause_t &cause);
octet3 PlmnToOctet3(const Plmn &plmn);
PduSessionType PduSessionTypeFromAsn(const ASN_NGAP_PDUSessionType_t &source);

void PlmnFromAsn_Ref(const ASN_NGAP_PLMNIdentity_t &source, Plmn &target);
void GuamiFromAsn_Ref(const ASN_NGAP_GUAMI_t &guami, Guami &target);
void ToCauseAsn_Ref(NgapCause source, ASN_NGAP_Cause_t &target);
void ToPlmnAsn_Ref(const Plmn &source, ASN_NGAP_PLMNIdentity_t &target);

SingleSlice SliceSupportFromAsn(ASN_NGAP_SliceSupportItem &supportItem);

NgapIdPair FindNgapIdPairFromAsnNgapIds(const ASN_NGAP_UE_NGAP_IDs &ngapIDs);

template <typename T>
inline NgapIdPair FindNgapIdPair(T *msg)
{
    auto *ieAmfUeNgapId = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_AMF_UE_NGAP_ID);
    auto *ieRanUeNgapId = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_RAN_UE_NGAP_ID);

    std::optional<int64_t> amfUeNgapId{}, ranUeNgapId{};
    if (ieAmfUeNgapId)
        amfUeNgapId = asn::GetSigned64(ieAmfUeNgapId->AMF_UE_NGAP_ID);
    if (ieRanUeNgapId)
        ranUeNgapId = ieRanUeNgapId->RAN_UE_NGAP_ID;

    return NgapIdPair{amfUeNgapId, ranUeNgapId};
}

template <typename T>
inline NgapIdPair FindNgapIdPairFromUeNgapIds(T *msg)
{
    auto *ieUeNgapIds = asn::ngap::GetProtocolIe(msg, ASN_NGAP_ProtocolIE_ID_id_UE_NGAP_IDs);
    if (ieUeNgapIds)
        return FindNgapIdPairFromAsnNgapIds(ieUeNgapIds->UE_NGAP_IDs);
    return NgapIdPair{{}, {}};
}

} // namespace nr::gnb::ngap_utils