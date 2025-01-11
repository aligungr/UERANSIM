//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"

#include <lib/asn/rrc.hpp>
#include <lib/asn/utils.hpp>
#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>

#include <asn/rrc/ASN_RRC_MIB.h>
#include <asn/rrc/ASN_RRC_PLMN-IdentityInfo.h>
#include <asn/rrc/ASN_RRC_PLMN-IdentityInfoList.h>
#include <asn/rrc/ASN_RRC_SIB1.h>
#include <asn/rrc/ASN_RRC_UAC-BarringInfoSet.h>

namespace nr::ue
{

void UeRrcTask::receiveMib(int cellId, const ASN_RRC_MIB &msg)
{
    auto &desc = m_cellDesc[cellId];

    desc.mib.isBarred = msg.cellBarred == ASN_RRC_MIB__cellBarred_barred;
    desc.mib.isIntraFreqReselectAllowed = msg.intraFreqReselection == ASN_RRC_MIB__intraFreqReselection_allowed;

    desc.mib.hasMib = true;

    updateAvailablePlmns();
}

void UeRrcTask::receiveSib1(int cellId, const ASN_RRC_SIB1 &msg)
{
    auto &desc = m_cellDesc[cellId];

    desc.sib1.isReserved = msg.cellAccessRelatedInfo.cellReservedForOtherUse != nullptr;

    auto *plmnIdentityInfo = msg.cellAccessRelatedInfo.plmn_IdentityList.list.array[0];
    desc.sib1.nci = asn::GetBitStringLong<36>(plmnIdentityInfo->cellIdentity);

    desc.sib1.isReserved &=
        plmnIdentityInfo->cellReservedForOperatorUse == ASN_RRC_PLMN_IdentityInfo__cellReservedForOperatorUse_reserved;

    desc.sib1.tac = asn::GetBitStringInt<24>(*plmnIdentityInfo->trackingAreaCode);

    auto plmnIdentity = plmnIdentityInfo->plmn_IdentityList.list.array[0];
    desc.sib1.plmn = asn::rrc::GetPlmnId(*plmnIdentity);


    // The UAC_BarringInfoSetList is optional
    // C.f.: https://www.nrexplained.com/rrc/ge0#SIB1
    if(msg.uac_BarringInfo){
        auto *barringInfo = msg.uac_BarringInfo->uac_BarringInfoSetList.list.array[0];

        int barringBits = asn::GetBitStringInt<7>(barringInfo->uac_BarringForAccessIdentity);
        desc.sib1.aiBarringSet.ai15 = bits::BitAt<0>(barringBits);
        desc.sib1.aiBarringSet.ai14 = bits::BitAt<1>(barringBits);
        desc.sib1.aiBarringSet.ai13 = bits::BitAt<2>(barringBits);
        desc.sib1.aiBarringSet.ai12 = bits::BitAt<3>(barringBits);
        desc.sib1.aiBarringSet.ai11 = bits::BitAt<4>(barringBits);
        desc.sib1.aiBarringSet.ai2 = bits::BitAt<5>(barringBits);
        desc.sib1.aiBarringSet.ai1 = bits::BitAt<6>(barringBits);
    }

    desc.sib1.hasSib1 = true;

    updateAvailablePlmns();
}

} // namespace nr::ue