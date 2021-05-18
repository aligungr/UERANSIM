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

void UeRrcTask::deliverUplinkNas(uint32_t pduId, OctetString &&nasPdu)
{
    if (!m_base->shCtx.currentCell.get<bool>([](auto &value) { return value.hasValue(); }))
    {
        m_logger->err("Uplink NAS delivery failed. No cell is active");
        return;
    }

    if (m_state == ERrcState::RRC_CONNECTED)
    {
        // TODO
    }
    else if (m_state == ERrcState::RRC_INACTIVE)
    {
        // TODO
    }
    else if (m_state == ERrcState::RRC_IDLE)
    {
        // TODO
    }
}

} // namespace nr::ue