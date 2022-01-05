//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "layer.hpp"

#include <algorithm>
#include <lib/rrc/encode.hpp>
#include <ue/task.hpp>

namespace nr::ue
{

EUacResult UeRrcLayer::performUac(std::bitset<16> identities, int category, int establishmentCause)
{
    m_establishmentCause = establishmentCause;

    int cellId = m_ue->shCtx.currentCell.cellId;
    if (m_cellDesc.count(cellId))
    {
        auto &desc = m_cellDesc[cellId];
        if (!desc.mib.hasMib || !desc.sib1.hasSib1 || desc.mib.isBarred || desc.sib1.isReserved)
            return EUacResult::BARRED;
        else
        {
            size_t barredCount = 0;

            if (desc.sib1.aiBarringSet.ai1 && identities[1])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai2 && identities[2])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai11 && identities[11])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai12 && identities[12])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai13 && identities[13])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai14 && identities[14])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai15 && identities[15])
                barredCount++;

            if (identities.count() == barredCount)
                return EUacResult::BARRED;
            else
                return EUacResult::ALLOWED;
        }
    }

    return EUacResult::BARRED;
}

} // namespace nr::ue
