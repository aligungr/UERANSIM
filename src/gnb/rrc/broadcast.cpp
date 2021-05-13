//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <gnb/ngap/task.hpp>
#include <lib/asn/utils.hpp>
#include <lib/rrc/encode.hpp>

#include <asn/rrc/ASN_RRC_MIB.h>

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

void GnbRrcTask::onBroadcastTimerExpired()
{
    triggerSysInfoBroadcast();
}

void GnbRrcTask::triggerSysInfoBroadcast()
{
    ASN_RRC_BCCH_BCH_Message *msg = ConstructMibMessage(m_isBarred, m_intraFreqReselectAllowed);

    sendRrcMessage(msg);

    asn::Free(asn_DEF_ASN_RRC_BCCH_BCH_Message, msg);
}

} // namespace nr::gnb