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
#include <common.hpp>
#include <convert.hpp>

#include "gnb_ngap_types.hpp"
#include <ASN_NGAP_GUAMI.h>
#include <ASN_NGAP_PagingDRX.h>

namespace nr::gnb::ngap_utils
{

ASN_NGAP_PagingDRX_t PagingDrxToAsn(EPagingDrx pagingDrx);
void SetPlmnFromAsn(const ASN_NGAP_PLMNIdentity_t &source, Plmn &target);
void SetGuamiFromAsn(const ASN_NGAP_GUAMI_t &guami, Guami &target);

} // namespace nr::gnb::ngap_utils