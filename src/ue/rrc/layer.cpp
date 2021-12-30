//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "layer.hpp"

#include <asn/rrc/ASN_RRC_RRCSetupRequest-IEs.h>
#include <asn/rrc/ASN_RRC_RRCSetupRequest.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer-IEs.h>
#include <asn/rrc/ASN_RRC_ULInformationTransfer.h>

#include <lib/rrc/encode.hpp>
#include <ue/app/task.hpp>
#include <ue/rls/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

UeRrcLayer::UeRrcLayer(TaskBase *base) : m_base{base}, m_timers{}
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "rrc");

    m_startedTime = utils::CurrentTimeMillis();
    m_state = ERrcState::RRC_IDLE;
    m_establishmentCause = ASN_RRC_EstablishmentCause_mt_Access;
}

void UeRrcLayer::onStart()
{
    triggerCycle();
}

void UeRrcLayer::onQuit()
{
    // TODO
}

} // namespace nr::ue