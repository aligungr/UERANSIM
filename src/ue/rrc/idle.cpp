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
#include <ue/rls/task.hpp>

namespace nr::ue
{

void UeRrcTask::performCellSelection()
{
    if (m_state == ERrcState::RRC_CONNECTED)
        return;

    int64_t currentTime = utils::CurrentTimeMillis();

    if (currentTime - m_startedTime <= 1000LL && m_cellDesc.empty())
        return;
    if (currentTime - m_startedTime <= 4000LL && !m_base->shCtx.selectedPlmn.get().hasValue())
        return;

    auto lastCell = m_base->shCtx.currentCell.get();

    bool shouldLogErrors = lastCell.cellId != 0 || (currentTime - m_lastTimePlmnSearchFailureLogged >= 30'000LL);

    ActiveCellInfo cellInfo;
    CellSelectionReport report;

    bool cellFound = false;
    if (m_base->shCtx.selectedPlmn.get().hasValue())
    {
        cellFound = lookForSuitableCell(cellInfo, report);
        if (!cellFound)
        {
            if (shouldLogErrors)
            {
                if (!m_cellDesc.empty())
                {
                    m_logger->warn(
                        "Suitable cell selection failed in [%d] cells. [%d] out of PLMN, [%d] no SI, [%d] reserved, "
                        "[%d] barred, ftai [%d]",
                        static_cast<int>(m_cellDesc.size()), report.outOfPlmnCells, report.siMissingCells,
                        report.reservedCells, report.barredCells, report.forbiddenTaiCells);
                }
                else
                {
                    m_logger->warn("Suitable cell selection failed, no cell is in coverage");
                }

                m_lastTimePlmnSearchFailureLogged = currentTime;
            }
        }
    }

    if (!cellFound)
    {
        report = {};

        cellFound = lookForAcceptableCell(cellInfo, report);

        if (!cellFound)
        {
            if (shouldLogErrors)
            {
                if (!m_cellDesc.empty())
                {
                    m_logger->warn("Acceptable cell selection failed in [%d] cells. [%d] no SI, [%d] reserved, [%d] "
                                   "barred, ftai [%d]",
                                   static_cast<int>(m_cellDesc.size()), report.siMissingCells, report.reservedCells,
                                   report.barredCells, report.forbiddenTaiCells);
                }
                else
                {
                    m_logger->warn("Acceptable cell selection failed, no cell is in coverage");
                }

                m_logger->err("Cell selection failure, no suitable or acceptable cell found");

                m_lastTimePlmnSearchFailureLogged = currentTime;
            }
        }
    }

    int selectedCell = cellInfo.cellId;
    m_base->shCtx.currentCell.set(cellInfo);

    if (selectedCell != 0 && selectedCell != lastCell.cellId)
        m_logger->info("Selected cell plmn[%s] tac[%d] category[%s]", ToJson(cellInfo.plmn).str().c_str(), cellInfo.tac,
                       ToJson(cellInfo.category).str().c_str());

    if (selectedCell != lastCell.cellId)
    {
        auto w1 = std::make_unique<NmUeRrcToRls>(NmUeRrcToRls::ASSIGN_CURRENT_CELL);
        w1->cellId = selectedCell;
        m_base->rlsTask->push(std::move(w1));

        auto w2 = std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::ACTIVE_CELL_CHANGED);
        w2->previousTai = Tai{lastCell.plmn, lastCell.tac};
        m_base->nasTask->push(std::move(w2));
    }
}

void UeRrcTask::performCellChange(int newCellId)
{

    m_logger->debug("Checking existence of cellId = %d", newCellId);
    auto lastCell = m_base->shCtx.currentCell.get();
    if (newCellId == 0)
    {
        m_logger->err("Could not change cell: new cell id is 0");
        return;
    }
    if (newCellId == lastCell.cellId)
    {
        m_logger->err("Could not change cell: new cell has same id than current cell (cell[%d])", lastCell.cellId);
        return;
    }
    if (m_cellDesc.count(newCellId) == 0)
    {
        m_logger->err("Could not change cell: new cell has no description");
        return;
    }
    m_logger->debug("Performing cell change from cell[%d] to cell[%d]", lastCell.cellId, newCellId);

    ActiveCellInfo cellInfo = {};
    auto handoverCell = m_cellDesc[newCellId];
    cellInfo.cellId = newCellId;
    cellInfo.plmn = handoverCell.sib1.plmn;
    cellInfo.tac = handoverCell.sib1.tac;
    if (isSuitable(handoverCell))
        cellInfo.category = ECellCategory::SUITABLE_CELL;
    else if (isAcceptable(handoverCell))
        cellInfo.category = ECellCategory::ACCEPTABLE_CELL;
    else
    {
        m_logger->err("Could not change cell: new cell is not suitable and not acceptable");
        return;
    }

    m_base->shCtx.currentCell.set(cellInfo);
    m_logger->info("Selected new cell plmn[%s] tac[%d] category[%s]", ToJson(cellInfo.plmn).str().c_str(), cellInfo.tac,
                   ToJson(cellInfo.category).str().c_str());
    m_logger->debug("Cell[%d] found", m_base->shCtx.currentCell.get<int>([](auto &item) { return item.cellId; }));

    // notify other tasks
    m_state = ERrcState::RRC_CONNECTED;
    auto w1 = std::make_unique<NmUeRrcToRls>(NmUeRrcToRls::ASSIGN_CURRENT_CELL);
    w1->cellId = newCellId;
    m_base->rlsTask->push(std::move(w1));
    m_base->nasTask->push(std::make_unique<NmUeRrcToNas>(NmUeRrcToNas::RRC_HANDOVER_COMMAND));

    // Sending handover Confirm Message
    m_base->rrcTask->push(std::make_unique<NmUeRrcToRrc>(NmUeRrcToRrc::HANDOVER_CONFIRM));
    m_logger->debug("Cell change from cell[%d] to cell[%d]: completed", lastCell.cellId, newCellId);
}

bool UeRrcTask::isSuitable(UeCellDesc &cell)
{
    Plmn selectedPlmn = m_base->shCtx.selectedPlmn.get();
    Tai tai{cell.sib1.plmn, cell.sib1.tac};
    if ((!cell.sib1.hasSib1 || !cell.mib.hasMib || cell.sib1.plmn != selectedPlmn || cell.mib.isBarred ||
         cell.sib1.isReserved) ||
        (m_base->shCtx.forbiddenTaiRoaming.get<bool>([&tai](auto &item) {
            return std::any_of(item.begin(), item.end(), [&tai](auto &element) { return element == tai; });
        })) ||

        (m_base->shCtx.forbiddenTaiRps.get<bool>([&tai](auto &item) {
            return std::any_of(item.begin(), item.end(), [&tai](auto &element) { return element == tai; });
        })))
        return false;
    return true;
}

bool UeRrcTask::isAcceptable(UeCellDesc &cell)
{
    Tai tai{cell.sib1.plmn, cell.sib1.tac};
    if ((!cell.sib1.hasSib1 || !cell.mib.hasMib || cell.mib.isBarred || cell.sib1.isReserved) ||

        (m_base->shCtx.forbiddenTaiRoaming.get<bool>([&tai](auto &item) {
            return std::any_of(item.begin(), item.end(), [&tai](auto &element) { return element == tai; });
        })) ||

        (m_base->shCtx.forbiddenTaiRps.get<bool>([&tai](auto &item) {
            return std::any_of(item.begin(), item.end(), [&tai](auto &element) { return element == tai; });
        })))
        return false;
    return true;
}

bool UeRrcTask::lookForSuitableCell(ActiveCellInfo &cellInfo, CellSelectionReport &report)
{
    Plmn selectedPlmn = m_base->shCtx.selectedPlmn.get();
    if (!selectedPlmn.hasValue())
        return false;

    std::vector<int> candidates;

    for (auto &item : m_cellDesc)
    {
        auto &cell = item.second;

        if (!cell.sib1.hasSib1)
        {
            report.siMissingCells++;
            continue;
        }

        if (!cell.mib.hasMib)
        {
            report.siMissingCells++;
            continue;
        }

        if (cell.sib1.plmn != selectedPlmn)
        {
            report.outOfPlmnCells++;
            continue;
        }

        if (cell.mib.isBarred)
        {
            report.barredCells++;
            continue;
        }

        if (cell.sib1.isReserved)
        {
            report.reservedCells++;
            continue;
        }

        Tai tai{cell.sib1.plmn, cell.sib1.tac};

        if (m_base->shCtx.forbiddenTaiRoaming.get<bool>([&tai](auto &item) {
                return std::any_of(item.begin(), item.end(), [&tai](auto &element) { return element == tai; });
            }))
        {
            report.forbiddenTaiCells++;
            continue;
        }

        if (m_base->shCtx.forbiddenTaiRps.get<bool>([&tai](auto &item) {
                return std::any_of(item.begin(), item.end(), [&tai](auto &element) { return element == tai; });
            }))
        {
            report.forbiddenTaiCells++;
            continue;
        }

        // It seems suitable
        candidates.push_back(item.first);
    }

    if (candidates.empty())
        return false;

    // Order candidates by signal strength
    auto selectedId = *std::max_element(candidates.begin(), candidates.end(), [this](int a, int b) {
        auto &cellA = m_cellDesc[a];
        auto &cellB = m_cellDesc[b];
        if (cellA.dbm == cellB.dbm)
        {
            // same strength: the smallest identifier has priority (to have a stable sort)
            return a < b;
        }
        return cellA.dbm < cellB.dbm;
    });

    auto &selectedCell = m_cellDesc[selectedId];

    cellInfo = {};
    cellInfo.cellId = selectedId;
    cellInfo.plmn = selectedCell.sib1.plmn;
    cellInfo.tac = selectedCell.sib1.tac;
    cellInfo.category = ECellCategory::SUITABLE_CELL;

    return true;
}

bool UeRrcTask::lookForAcceptableCell(ActiveCellInfo &cellInfo, CellSelectionReport &report)
{
    std::vector<int> candidates;

    for (auto &item : m_cellDesc)
    {
        auto &cell = item.second;

        if (!cell.sib1.hasSib1)
        {
            report.siMissingCells++;
            continue;
        }

        if (!cell.mib.hasMib)
        {
            report.siMissingCells++;
            continue;
        }

        if (cell.mib.isBarred)
        {
            report.barredCells++;
            continue;
        }

        if (cell.sib1.isReserved)
        {
            report.reservedCells++;
            continue;
        }

        Tai tai{cell.sib1.plmn, cell.sib1.tac};

        if (m_base->shCtx.forbiddenTaiRoaming.get<bool>([&tai](auto &item) {
                return std::any_of(item.begin(), item.end(), [&tai](auto &element) { return element == tai; });
            }))
        {
            report.forbiddenTaiCells++;
            continue;
        }

        if (m_base->shCtx.forbiddenTaiRps.get<bool>([&tai](auto &item) {
                return std::any_of(item.begin(), item.end(), [&tai](auto &element) { return element == tai; });
            }))
        {
            report.forbiddenTaiCells++;
            continue;
        }

        // It seems acceptable
        candidates.push_back(item.first);
    }

    if (candidates.empty())
        return false;

    // Order candidates by signal strength
    std::sort(candidates.begin(), candidates.end(), [this](int a, int b) {
        auto &cellA = m_cellDesc[a];
        auto &cellB = m_cellDesc[b];
        if (cellA.dbm != cellB.dbm)
            return cellA.dbm < cellB.dbm;
        return cellA.sib1.nci > cellB.sib1.nci;
    });

    // Then order candidates by PLMN priority if we have a selected PLMN
    Plmn selectedPlmn = m_base->shCtx.selectedPlmn.get();
    if (selectedPlmn.hasValue())
    {
        // Using stable-sort here
        std::stable_sort(candidates.begin(), candidates.end(), [this, &selectedPlmn](int a, int b) {
            auto &cellA = m_cellDesc[a];
            auto &cellB = m_cellDesc[b];

            bool matchesA = cellA.sib1.hasSib1 && cellA.sib1.plmn == selectedPlmn;
            bool matchesB = cellB.sib1.hasSib1 && cellB.sib1.plmn == selectedPlmn;

            return matchesB < matchesA;
        });
    }

    auto &selectedId = candidates[0];
    auto &selectedCell = m_cellDesc[selectedId];

    cellInfo = {};
    cellInfo.cellId = selectedId;
    cellInfo.plmn = selectedCell.sib1.plmn;
    cellInfo.tac = selectedCell.sib1.tac;
    cellInfo.category = ECellCategory::ACCEPTABLE_CELL;

    return true;
}

} // namespace nr::ue