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
    std::string result;

    long enumValue;
    asn_TYPE_descriptor_t *typeDescriptor;

    switch (cause.present)
    {
    case ASN_NGAP_Cause_PR_radioNetwork:
        result = "radio-network";
        enumValue = cause.choice.radioNetwork;
        typeDescriptor = &asn_DEF_ASN_NGAP_CauseRadioNetwork;
        break;
    case ASN_NGAP_Cause_PR_transport:
        result = "transport";
        enumValue = cause.choice.transport;
        typeDescriptor = &asn_DEF_ASN_NGAP_CauseTransport;
        break;
    case ASN_NGAP_Cause_PR_nas:
        result = "nas";
        enumValue = cause.choice.nas;
        typeDescriptor = &asn_DEF_ASN_NGAP_CauseNas;
        break;
    case ASN_NGAP_Cause_PR_protocol:
        result = "protocol";
        enumValue = cause.choice.protocol;
        typeDescriptor = &asn_DEF_ASN_NGAP_CauseProtocol;
        break;
    case ASN_NGAP_Cause_PR_misc:
        result = "misc";
        enumValue = cause.choice.misc;
        typeDescriptor = &asn_DEF_ASN_NGAP_CauseMisc;
        break;
    default:
        return "<?>";
    }

    auto *specs = reinterpret_cast<const asn_INTEGER_specifics_t *>(typeDescriptor->specifics);
    if (specs)
    {
        if (specs->value2enum)
        {
            for (int i = 0; i < specs->map_count; i++)
            {
                if (specs->value2enum[i].nat_value == enumValue)
                {
                    result += "/" + std::string(specs->value2enum[i].enum_name);
                    break;
                }
            }
        }
    }

    return result;
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

PduSessionType PduSessionTypeFromAsn(const ASN_NGAP_PDUSessionType_t &source)
{
    switch (source)
    {
    case ASN_NGAP_PDUSessionType_ipv4:
        return PduSessionType::IPv4;
    case ASN_NGAP_PDUSessionType_ipv6:
        return PduSessionType::IPv6;
    case ASN_NGAP_PDUSessionType_ipv4v6:
        return PduSessionType::IPv4v6;
    case ASN_NGAP_PDUSessionType_ethernet:
        return PduSessionType::ETHERNET;
    default:
        return PduSessionType::UNSTRUCTURED;
    }
}

void ToPlmnAsn_Ref(const Plmn &source, ASN_NGAP_PLMNIdentity_t &target)
{
    octet3 val = PlmnToOctet3(source);
    asn::SetOctetString(target, val);
}

NgapIdPair FindNgapIdPairFromAsnNgapIds(const ASN_NGAP_UE_NGAP_IDs &ngapIDs)
{
    std::optional<int64_t> amfUeNgapId{}, ranUeNgapId{};

    if (ngapIDs.present == ASN_NGAP_UE_NGAP_IDs_PR_uE_NGAP_ID_pair)
    {
        amfUeNgapId = asn::GetSigned64(ngapIDs.choice.uE_NGAP_ID_pair->aMF_UE_NGAP_ID);
        ranUeNgapId = ngapIDs.choice.uE_NGAP_ID_pair->rAN_UE_NGAP_ID;
    }
    else if (ngapIDs.present == ASN_NGAP_UE_NGAP_IDs_PR_aMF_UE_NGAP_ID)
    {
        amfUeNgapId = asn::GetSigned64(ngapIDs.choice.aMF_UE_NGAP_ID);
    }

    return NgapIdPair{amfUeNgapId, ranUeNgapId};
}

} // namespace nr::gnb::ngap_utils