//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <gnb/ngap/task.hpp>
#include <lib/asn/rrc.hpp>
#include <lib/asn/utils.hpp>
#include <lib/rrc/encode.hpp>
#include <utils/common.hpp>

#include <asn/rrc/ASN_RRC_MIB.h>
#include <asn/rrc/ASN_RRC_PLMN-IdentityInfo.h>
#include <asn/rrc/ASN_RRC_PLMN-IdentityInfoList.h>
#include <asn/rrc/ASN_RRC_SIB1.h>
#include <asn/rrc/ASN_RRC_UAC-BarringInfoSet.h>
#include <asn/rrc/ASN_RRC_UAC-BarringInfoSetIndex.h>
#include <asn/rrc/ASN_RRC_UAC-BarringPerCat.h>
#include <asn/rrc/ASN_RRC_UAC-BarringPerCatList.h>

namespace nr::gnb
{

static ASN_RRC_BCCH_BCH_Message *ConstructMibMessage(bool barred, bool intraFreqReselectAllowed)
{
    auto *pdu = asn::New<ASN_RRC_BCCH_BCH_Message>();
    pdu->message.present = ASN_RRC_BCCH_BCH_MessageType_PR_mib;
    pdu->message.choice.mib = asn::New<ASN_RRC_MIB>();

    auto &mib = *pdu->message.choice.mib;

    asn::SetBitStringInt<6>(0, mib.systemFrameNumber);
    mib.subCarrierSpacingCommon = ASN_RRC_MIB__subCarrierSpacingCommon_scs15or60;
    mib.ssb_SubcarrierOffset = 0;
    mib.dmrs_TypeA_Position = ASN_RRC_MIB__dmrs_TypeA_Position_pos2;
    mib.cellBarred = barred ? ASN_RRC_MIB__cellBarred_barred : ASN_RRC_MIB__cellBarred_notBarred;
    mib.intraFreqReselection = intraFreqReselectAllowed ? ASN_RRC_MIB__intraFreqReselection_allowed
                                                        : ASN_RRC_MIB__intraFreqReselection_notAllowed;
    asn::SetBitStringInt<1>(0, mib.spare);
    mib.pdcch_ConfigSIB1.controlResourceSetZero = 0;
    mib.pdcch_ConfigSIB1.searchSpaceZero = 0;
    return pdu;
}

static ASN_RRC_BCCH_DL_SCH_Message *ConstructSib1Message(bool cellReserved, int tac, int64_t nci, const Plmn &plmn,
                                                         const UacAiBarringSet &aiBarringSet)
{
    auto *pdu = asn::New<ASN_RRC_BCCH_DL_SCH_Message>();
    pdu->message.present = ASN_RRC_BCCH_DL_SCH_MessageType_PR_c1;
    pdu->message.choice.c1 = asn::NewFor(pdu->message.choice.c1);
    pdu->message.choice.c1->present = ASN_RRC_BCCH_DL_SCH_MessageType__c1_PR_systemInformationBlockType1;
    pdu->message.choice.c1->choice.systemInformationBlockType1 = asn::New<ASN_RRC_SIB1>();

    auto &sib1 = *pdu->message.choice.c1->choice.systemInformationBlockType1;

    if (cellReserved)
    {
        asn::MakeNew(sib1.cellAccessRelatedInfo.cellReservedForOtherUse);
        *sib1.cellAccessRelatedInfo.cellReservedForOtherUse =
            ASN_RRC_CellAccessRelatedInfo__cellReservedForOtherUse_true;
    }

    auto *plmnInfo = asn::New<ASN_RRC_PLMN_IdentityInfo>();
    plmnInfo->cellReservedForOperatorUse = cellReserved
                                               ? ASN_RRC_PLMN_IdentityInfo__cellReservedForOperatorUse_reserved
                                               : ASN_RRC_PLMN_IdentityInfo__cellReservedForOperatorUse_notReserved;
    asn::MakeNew(plmnInfo->trackingAreaCode);
    asn::SetBitStringInt<24>(tac, *plmnInfo->trackingAreaCode);
    asn::SetBitStringLong<36>(nci, plmnInfo->cellIdentity);
    asn::SequenceAdd(plmnInfo->plmn_IdentityList, asn::rrc::NewPlmnId(plmn));
    asn::SequenceAdd(sib1.cellAccessRelatedInfo.plmn_IdentityList, plmnInfo);

    asn::MakeNew(sib1.uac_BarringInfo);

    auto *info = asn::New<ASN_RRC_UAC_BarringInfoSet>();
    info->uac_BarringFactor = ASN_RRC_UAC_BarringInfoSet__uac_BarringFactor_p50;
    info->uac_BarringTime = ASN_RRC_UAC_BarringInfoSet__uac_BarringTime_s4;

    asn::SetBitStringInt<7>(bits::Consequential8(false, aiBarringSet.ai1, aiBarringSet.ai2, aiBarringSet.ai11,
                                                 aiBarringSet.ai12, aiBarringSet.ai13, aiBarringSet.ai14,
                                                 aiBarringSet.ai15),
                            info->uac_BarringForAccessIdentity);

    asn::SequenceAdd(sib1.uac_BarringInfo->uac_BarringInfoSetList, info);

    asn::MakeNew(sib1.uac_BarringInfo->uac_BarringForCommon);

    for (size_t i = 0; i < 63; i++)
    {
        auto *item = asn::New<ASN_RRC_UAC_BarringPerCat>();
        item->accessCategory = static_cast<decltype(item->accessCategory)>(i + 1);
        item->uac_barringInfoSetIndex = 1;

        asn::SequenceAdd(*sib1.uac_BarringInfo->uac_BarringForCommon, item);
    }

    return pdu;
}

void GnbRrcTask::onBroadcastTimerExpired()
{
    triggerSysInfoBroadcast();
}

void GnbRrcTask::triggerSysInfoBroadcast()
{
    auto *mib = ConstructMibMessage(m_isBarred, m_intraFreqReselectAllowed);
    auto *sib1 = ConstructSib1Message(m_cellReserved, m_config->tac, m_config->nci, m_config->plmn, m_aiBarringSet);

    sendRrcMessage(mib);
    sendRrcMessage(sib1);

    asn::Free(asn_DEF_ASN_RRC_BCCH_BCH_Message, mib);
    asn::Free(asn_DEF_ASN_RRC_BCCH_DL_SCH_Message, sib1);
}

} // namespace nr::gnb