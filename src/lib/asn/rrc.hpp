//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include <utils/common.hpp>

#include <asn/rrc/ASN_RRC_PLMN-Identity.h>

namespace asn::rrc
{

void SetPlmnId(const Plmn &source, ASN_RRC_PLMN_Identity &target);

ASN_RRC_PLMN_Identity *NewPlmnId(const Plmn &plmn);

ASN_RRC_MCC_MNC_Digit_t *NewMccMncDigit(int digit);

Plmn GetPlmnId(const ASN_RRC_PLMN_Identity& value);

} // namespace asn::rrc
