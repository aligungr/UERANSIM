//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_utils.hpp"

namespace nr::gnb::ngap_utils
{

ASN_NGAP_PagingDRX_t PagingDrxToAsn(EPagingDrx pagingDrx)
{
    return (((int)pagingDrx) / 32) - 1;
}

void SetPlmnFromAsn(const ASN_NGAP_PLMNIdentity_t &source, Plmn &target)
{
    int i;
    assert(source.size == 3);
    i = ((source.buf[1] & 0xf0) >> 4);
    if (i == 0xf)
    {
        i = 0;
        target.isLong = false;
    }
    else
        target.isLong = true;
    target.mcc = (((source.buf[0] & 0xf0) >> 4) * 10) + ((source.buf[0] & 0x0f) * 100) + (source.buf[1] & 0x0f);
    target.mnc = (i * 100) + ((source.buf[2] & 0xf0) >> 4) + ((source.buf[2] & 0x0f) * 10);
}

void SetGuamiFromAsn(const ASN_NGAP_GUAMI_t &guami, Guami &target)
{
    target.amfRegionId = asn::GetBitStringInt<8>(guami.aMFRegionID);
    target.amfSetId = asn::GetBitStringInt<10>(guami.aMFSetID);
    target.amfPointer = asn::GetBitStringInt<6>(guami.aMFPointer);
    target.plmn = {};
    SetPlmnFromAsn(guami.pLMNIdentity, target.plmn);
}

} // namespace nr::gnb::ngap_utils