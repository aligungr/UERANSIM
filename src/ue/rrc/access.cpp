//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"

#include <algorithm>

#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>

namespace nr::ue
{

void UeRrcTask::performUac(std::shared_ptr<LightSync<UacInput, UacOutput>> &uacCtl)
{
    auto &input = uacCtl->input();

    m_establishmentCause = input.establishmentCause;

    auto output = std::make_unique<UacOutput>();

    int cellId = m_base->shCtx.currentCell.get().cellId;
    if (m_cellDesc.count(cellId))
    {
        auto &desc = m_cellDesc[cellId];
        if (!desc.mib.hasMib || !desc.sib1.hasSib1 || desc.mib.isBarred || desc.sib1.isReserved)
            output->res = EUacResult::BARRED;
        else
        {

            size_t barredCount = 0;

            if (desc.sib1.aiBarringSet.ai1 && input.identities[1])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai2 && input.identities[2])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai11 && input.identities[11])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai12 && input.identities[12])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai13 && input.identities[13])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai14 && input.identities[14])
                barredCount++;
            if (desc.sib1.aiBarringSet.ai15 && input.identities[15])
                barredCount++;

            if (input.identities.count() == barredCount)
                output->res = EUacResult::BARRED;
            else
                output->res = EUacResult::ALLOWED;
        }
    }
    else
    {
        output->res = EUacResult::BARRED;
    }

    uacCtl->notifyProcessed(std::move(output));
}

} // namespace nr::ue
