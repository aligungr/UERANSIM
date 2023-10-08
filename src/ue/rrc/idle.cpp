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
    std::sort(candidates.begin(), candidates.end(), [this](int a, int b) {
        auto &cellA = m_cellDesc[a];
        auto &cellB = m_cellDesc[b];
        return cellB.dbm < cellA.dbm;
    });

    auto &selectedId = candidates[0];
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

    // Order candidates by signal strength first
    std::sort(candidates.begin(), candidates.end(), [this](int a, int b) {
        auto &cellA = m_cellDesc[a];
        auto &cellB = m_cellDesc[b];
        return cellB.dbm < cellA.dbm;
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