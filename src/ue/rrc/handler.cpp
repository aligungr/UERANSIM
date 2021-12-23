//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <lib/asn/utils.hpp>
#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>
#include <ue/nts.hpp>
#include <utils/common.hpp>

#include <asn/rrc/ASN_RRC_DLInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_DLInformationTransfer.h>
#include <asn/rrc/ASN_RRC_Paging.h>
#include <asn/rrc/ASN_RRC_PagingRecord.h>
#include <asn/rrc/ASN_RRC_PagingRecordList.h>
#include <asn/rrc/ASN_RRC_RRCSetup-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetup.h>
#include <asn/rrc/ASN_RRC_RRCSetupComplete-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupComplete.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest.h>
#include <asn/rrc/ASN_RRC_UL-DCCH-Message.h>
#include <asn/rrc/ASN_RRC_UL-DCCH-MessageType.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer.h>

namespace nr::ue
{

void UeRrcTask::receivePaging(const ASN_RRC_Paging &msg)
{
    std::vector<GutiMobileIdentity> tmsiIds{};

    asn::ForeachItem(*msg.pagingRecordList, [&tmsiIds](auto &pagingRecord) {
        if (pagingRecord.ue_Identity.present == ASN_RRC_PagingUE_Identity_PR_ng_5G_S_TMSI)
        {
            auto recordTmsi = asn::GetOctetString(pagingRecord.ue_Identity.choice.ng_5G_S_TMSI);
            auto tmsiOs = BitBuffer{recordTmsi.data()};

            GutiMobileIdentity tmsi{};
            tmsi.amfSetId = tmsiOs.readBits(10);
            tmsi.amfPointer = tmsiOs.readBits(6);
            tmsi.tmsi = octet4{static_cast<uint32_t>(tmsiOs.readBitsLong(32) & 0xFFFFFFFFu)};

            tmsiIds.push_back(tmsi);
        }
    });

    auto w = std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::PAGING);
    w->pagingTmsi = std::move(tmsiIds);
    m_base->nasTask->push(std::move(w));
}

} // namespace nr::ue