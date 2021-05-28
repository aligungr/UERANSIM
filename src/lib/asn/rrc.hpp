//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
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
