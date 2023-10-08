//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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

    bool isActiveCell = false;
    ActiveCellInfo lastActiveCell;
    m_base->shCtx.currentCell.mutate([&isActiveCell, &lastActiveCell, cellId](auto &value) {
        if (value.cellId == cellId)
        {
            lastActiveCell = value;
            value = {};
            isActiveCell = true;
        }
    });

    m_cellDesc.erase(cellId);

    m_logger->debug("Signal lost for cell[%d], total [%d] cells in coverage", cellId,
                    static_cast<int>(m_cellDesc.size()));

    if (isActiveCell)
    {
        if (m_state != ERrcState::RRC_IDLE)
            declareRadioLinkFailure(rls::ERlfCause::SIGNAL_LOST_TO_CONNECTED_CELL);
        else
        {
            auto w = std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::ACTIVE_CELL_CHANGED);
            w->previousTai = Tai{lastActiveCell.plmn, lastActiveCell.tac};
            m_base->nasTask->push(std::move(w));
        }
    }

    updateAvailablePlmns();
}

bool UeRrcTask::hasSignalToCell(int cellId)
{
    return m_cellDesc.count(cellId);
}

bool UeRrcTask::isActiveCell(int cellId)
{
    return m_base->shCtx.currentCell.get<int>([](auto &value) { return value.cellId; }) == cellId;
}

void UeRrcTask::updateAvailablePlmns()
{
    m_base->shCtx.availablePlmns.mutate([this](std::unordered_set<Plmn> &value) {
        value.clear();
        for (auto &cellDesc : m_cellDesc)
            if (cellDesc.second.sib1.hasSib1)
                value.insert(cellDesc.second.sib1.plmn);
    });

    m_base->nasTask->push(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::NAS_NOTIFY));
}

} // namespace nr::ue
