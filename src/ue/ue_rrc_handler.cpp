//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nts.hpp"
#include "ue_rrc_task.hpp"

#include <asn_utils.hpp>
#include <rrc_encode.hpp>

#include <ASN_RRC_DLInformationTransfer-IEs.h>
#include <ASN_RRC_DLInformationTransfer.h>
#include <ASN_RRC_ULInformationTransfer-IEs.h>
#include <ASN_RRC_ULInformationTransfer.h>

namespace nr::ue
{

void UeRrcTask::receiveDownlinkInformationTransfer(ASN_RRC_DLInformationTransfer *msg)
{
    OctetString nasPdu =
        asn::GetOctetString(*msg->criticalExtensions.choice.dlInformationTransfer->dedicatedNAS_Message);
    base->nasTask->push(new NwDownlinkNasDelivery(std::move(nasPdu)));
}

} // namespace nr::ue