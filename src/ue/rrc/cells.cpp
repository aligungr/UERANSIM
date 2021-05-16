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

namespace nr::ue
{

void UeRrcTask::handleCellSignalChange(int cellId, int dbm)
{
    bool considerLost = dbm < -120;

    if (!m_cellDesc.count(cellId))
    {
        if (!considerLost)
            notifyCellDetected(cellId, dbm);
    }
    else
    {
        if (considerLost)
            notifyCellLost(cellId);
        else
            m_cellDesc[cellId].dbm = dbm;
    }
}

void UeRrcTask::notifyCellDetected(int cellId, int dbm)
{
    m_cellDesc[cellId] = {};
    m_cellDesc[cellId].dbm = dbm;

    m_logger->debug("New signal detected for cell[%d], total [%d] cells in coverage", cellId,
                    static_cast<int>(m_cellDesc.size()));

    updateAvailablePlmns();
}

void UeRrcTask::notifyCellLost(int cellId)
{
    if (!m_cellDesc.count(cellId))
        return;

    m_cellDesc.erase(cellId);

    m_logger->debug("Signal lost for cell[%d], total [%d] cells in coverage", cellId,
                    static_cast<int>(m_cellDesc.size()));

    updateAvailablePlmns();

    // TODO: handle other operations
}

bool UeRrcTask::hasSignalToCell(int cellId)
{
    return m_cellDesc.count(cellId);
}

void UeRrcTask::updateAvailablePlmns()
{
    m_base->shCtx.availablePlmns.mutate([this](std::unordered_set<Plmn> &value) {
        value.clear();
        for (auto &cellDesc : m_cellDesc)
            if (cellDesc.second.sib1.hasSib1)
                value.insert(cellDesc.second.sib1.plmn);
    });

    m_base->nasTask->push(new NwUeRrcToNas(NwUeRrcToNas::NAS_NOTIFY));
}

} // namespace nr::ue
