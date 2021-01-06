//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_utils.hpp"

#include <cstring>

namespace nr::gnb::ngap_utils
{

ASN_NGAP_PagingDRX_t PagingDrxToAsn(EPagingDrx pagingDrx)
{
    switch (pagingDrx)
    {
    case EPagingDrx::V32:
        return ASN_NGAP_PagingDRX_v32;
    case EPagingDrx::V64:
        return ASN_NGAP_PagingDRX_v64;
    case EPagingDrx::V128:
        return ASN_NGAP_PagingDRX_v128;
    case EPagingDrx::V256:
        return ASN_NGAP_PagingDRX_v256;
    }
    return ~0;
}

octet3 PlmnToOctet3(const Plmn &plmn)
{
    int mcc = plmn.mcc;
    int mcc3 = mcc % 10;
    int mcc2 = (mcc % 100) / 10;
    int mcc1 = (mcc % 1000) / 100;

    int mnc = plmn.mnc;

    if (plmn.isLongMnc)
    {
        int mnc1 = mnc % 1000 / 100;
        int mnc2 = mnc % 100 / 10;
        int mnc3 = mnc % 10;

        int octet1 = mcc2 << 4 | mcc1;
        int octet2 = mnc1 << 4 | mcc3;
        int octet3 = mnc3 << 4 | mnc2;

        return {(uint8_t)octet1, (uint8_t)octet2, (uint8_t)octet3};
    }
    else
    {
        int mnc1 = mnc % 100 / 10;
        int mnc2 = mnc % 10;
        int mnc3 = 0xF;

        int octet1 = mcc2 << 4 | mcc1;
        int octet2 = mnc3 << 4 | mcc3;
        int octet3 = mnc2 << 4 | mnc1;

        return {(uint8_t)octet1, (uint8_t)octet2, (uint8_t)octet3};
    }
}

void PlmnFromAsn_Ref(const ASN_NGAP_PLMNIdentity_t &source, Plmn &target)
{
    assert(source.size == 3);
    int i = ((source.buf[1] & 0xf0) >> 4);
    if (i == 0xf)
    {
        i = 0;
        target.isLongMnc = false;
    }
    else
        target.isLongMnc = true;
    target.mcc = (((source.buf[0] & 0xf0) >> 4) * 10) + ((source.buf[0] & 0x0f) * 100) + (source.buf[1] & 0x0f);
    target.mnc = (i * 100) + ((source.buf[2] & 0xf0) >> 4) + ((source.buf[2] & 0x0f) * 10);
}

void GuamiFromAsn_Ref(const ASN_NGAP_GUAMI_t &guami, Guami &target)
{
    target.amfRegionId = asn::GetBitStringInt<8>(guami.aMFRegionID);
    target.amfSetId = asn::GetBitStringInt<10>(guami.aMFSetID);
    target.amfPointer = asn::GetBitStringInt<6>(guami.aMFPointer);
    target.plmn = {};
    PlmnFromAsn_Ref(guami.pLMNIdentity, target.plmn);
}

std::unique_ptr<SliceSupport> SliceSupportFromAsn_Unique(ASN_NGAP_SliceSupportItem &supportItem)
{
    auto s = std::make_unique<SliceSupport>();
    s->sst = asn::GetOctet1(supportItem.s_NSSAI.sST);
    s->sd = std::nullopt;
    if (supportItem.s_NSSAI.sD)
        s->sd = asn::GetOctet3(*supportItem.s_NSSAI.sD);
    return s;
}

std::string CauseToString(const ASN_NGAP_Cause_t &cause)
{
    auto res = asn_encode_to_new_buffer(nullptr, ATS_BASIC_XER, &asn_DEF_ASN_NGAP_Cause, &cause);
    if (res.buffer == nullptr || res.result.encoded < 0)
        return "<?>";

    char *str = (char *)malloc(res.result.encoded + 1);
    std::memcpy(str, res.buffer, res.result.encoded);
    str[res.result.encoded] = '\0';

    std::string s = str;
    free(str);
    free(res.buffer);
    return s;
}

void ToCauseAsn_Ref(NgapCause source, ASN_NGAP_Cause_t &target)
{
    int val = (int)source;
    if (val >= 400)
    {
        val -= 400;
        target.present = ASN_NGAP_Cause_PR_misc;
        target.choice.misc = static_cast<ASN_NGAP_CauseMisc_t>(val);
    }
    else if (val >= 300)
    {
        val -= 300;
        target.present = ASN_NGAP_Cause_PR_protocol;
        target.choice.protocol = static_cast<ASN_NGAP_CauseProtocol_t>(val);
    }
    else if (val >= 200)
    {
        val -= 200;
        target.present = ASN_NGAP_Cause_PR_nas;
        target.choice.nas = static_cast<ASN_NGAP_CauseNas_t>(val);
    }
    else if (val >= 100)
    {
        val -= 100;
        target.present = ASN_NGAP_Cause_PR_transport;
        target.choice.transport = static_cast<ASN_NGAP_CauseTransport_t>(val);
    }
    else
    {
        target.present = ASN_NGAP_Cause_PR_radioNetwork;
        target.choice.radioNetwork = static_cast<ASN_NGAP_CauseRadioNetwork_t>(val);
    }
}

} // namespace nr::gnb::ngap_utils