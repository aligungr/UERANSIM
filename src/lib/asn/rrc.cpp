//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "rrc.hpp"

#include <lib/asn/utils.hpp>

#include <asn/rrc/ASN_RRC_MCC.h>

namespace asn::rrc
{

static int NthDigit(int number, int n)
{
    for (int i = 0; i < n; i++)
        number /= 10;
    return number % 10;
}

void SetPlmnId(const Plmn &source, ASN_RRC_PLMN_Identity &target)
{
    int mncDigits = source.isLongMnc ? 3 : 2;
    for (int i = 0; i < mncDigits; i++)
        asn::SequenceAdd(target.mnc, NewMccMncDigit(NthDigit(source.mnc, mncDigits - i - 1)));

    target.mcc = asn::NewFor(target.mcc);

    for (int i = 0; i < 3; i++)
        asn::SequenceAdd(*target.mcc, NewMccMncDigit(NthDigit(source.mcc, 2 - i)));
}

ASN_RRC_PLMN_Identity *NewPlmnId(const Plmn &plmn)
{
    auto *value = asn::New<ASN_RRC_PLMN_Identity>();
    SetPlmnId(plmn, *value);
    return value;
}

ASN_RRC_MCC_MNC_Digit_t *NewMccMncDigit(int digit)
{
    auto *value = asn::New<ASN_RRC_MCC_MNC_Digit_t>();
    *value = digit;
    return value;
}

} // namespace asn::rrc
