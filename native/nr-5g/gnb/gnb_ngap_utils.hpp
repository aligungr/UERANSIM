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

#include "gnb_ngap_types.hpp"

#include <ASN_NGAP_Cause.h>
#include <ASN_NGAP_GUAMI.h>
#include <ASN_NGAP_PagingDRX.h>
#include <ASN_NGAP_SliceSupportItem.h>

namespace nr::gnb::ngap_utils
{

ASN_NGAP_PagingDRX_t PagingDrxToAsn(EPagingDrx pagingDrx);
std::string CauseToString(const ASN_NGAP_Cause_t &cause);
octet3 PlmnToOctet3(const Plmn &plmn);

void PlmnFromAsn_Ref(const ASN_NGAP_PLMNIdentity_t &source, Plmn &target);
void GuamiFromAsn_Ref(const ASN_NGAP_GUAMI_t &guami, Guami &target);

std::unique_ptr<SliceSupport> SliceSupportFromAsn_Unique(ASN_NGAP_SliceSupportItem &supportItem);

} // namespace nr::gnb::ngap_utils