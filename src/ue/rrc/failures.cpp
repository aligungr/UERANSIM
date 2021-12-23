//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>
#include <ue/nts.hpp>

namespace nr::ue
{

void UeRrcTask::declareRadioLinkFailure(rls::ERlfCause cause)
{
    handleRadioLinkFailure(cause);
}

void UeRrcTask::handleRadioLinkFailure(rls::ERlfCause cause)
{
    m_state = ERrcState::RRC_IDLE;
    m_base->nasTask->push(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::RADIO_LINK_FAILURE));
}

} // namespace nr::ue