//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_rrc_task.hpp"

#include <ASN_RRC_RRCSetupRequest.h>
#include <ASN_RRC_ULInformationTransfer-IEs.h>
#include <ASN_RRC_ULInformationTransfer.h>

namespace nr::gnb
{

void GnbRrcTask::receiveRrcSetupRequest(int ueId, ASN_RRC_RRCSetupRequest *msg)
{
    // TODO
}

void GnbRrcTask::receiveUplinkInformationTransfer(int ueId, ASN_RRC_ULInformationTransfer *msg)
{
    if (msg->criticalExtensions.present == ASN_RRC_ULInformationTransfer__criticalExtensions_PR_ulInformationTransfer)
        deliverUplinkNas(
            ueId, asn::GetOctetString(*msg->criticalExtensions.choice.ulInformationTransfer->dedicatedNAS_Message));
}

} // namespace nr::gnb