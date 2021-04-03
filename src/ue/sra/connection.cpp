//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <ue/nts.hpp>
#include <ue/rrc/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

void UeSraTask::handleCellSelectionCommand(const GlobalNci &cellId, bool isSuitable)
{
    if (!m_activeMeasurements.count(cellId))
    {
        m_logger->err("Selected cell is no longer available for camping");
        return;
    }

    auto &measurement = m_activeMeasurements[cellId];

    m_servingCell = UeCellInfo{};
    m_servingCell->cellId = measurement.cellId;
    m_servingCell->tac = measurement.tac;
    m_servingCell->gnbName = measurement.gnbName;
    m_servingCell->linkIp = measurement.linkIp;
    m_servingCell->cellCategory = isSuitable ? ECellCategory::SUITABLE_CELL : ECellCategory::ACCEPTABLE_CELL;

    auto *w = new NwUeSraToRrc(NwUeSraToRrc::SERVING_CELL_CHANGE);
    w->servingCell = *m_servingCell;
    m_base->rrcTask->push(w);
}

} // namespace nr::ue
