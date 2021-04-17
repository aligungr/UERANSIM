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

void UeRlsTask::onMeasurement()
{
    std::vector<GlobalNci> entered{};
    std::vector<GlobalNci> exited{};

    // compare active and pending measurements
    for (auto &m : m_activeMeasurements)
    {
        if (!m_pendingMeasurements.count(m.first))
            exited.push_back(m.first);
    }
    for (auto &m : m_pendingMeasurements)
    {
        if (!m_activeMeasurements.count(m.first))
            entered.push_back(m.first);
    }
    if (!entered.empty() || !exited.empty())
        onCoverageChange(entered, exited);

    // copy from pending to active measurements
    m_activeMeasurements = m_pendingMeasurements;
    // clear pending measurements
    m_pendingMeasurements = {};

    // Issue another cell info request for each address in the search space
    for (auto &ip : m_cellSearchSpace)
    {
        rls::RlsCellInfoRequest req{m_sti};
        sendRlsMessage(ip, req);
    }

    // Send PLMN search response to the RRC if it is requested
    if (m_pendingPlmnResponse)
    {
        m_pendingPlmnResponse = false;

        std::vector<UeCellMeasurement> measurements{};
        for (auto &m : m_activeMeasurements)
            measurements.push_back(m.second);

        auto *w = new NwUeRlsToRrc(NwUeRlsToRrc::PLMN_SEARCH_RESPONSE);
        w->measurements = std::move(measurements);
        m_base->rrcTask->push(w);
    }
}

void UeRlsTask::receiveCellInfoResponse(const rls::RlsCellInfoResponse &msg)
{
    UeCellMeasurement meas{};
    meas.sti = msg.sti;
    meas.cellId = msg.cellId;
    meas.tac = msg.tac;
    meas.dbm = msg.dbm;
    meas.gnbName = msg.gnbName;
    meas.linkIp = msg.linkIp;

    m_pendingMeasurements[meas.cellId] = meas;
}

void UeRlsTask::onCoverageChange(const std::vector<GlobalNci> &entered, const std::vector<GlobalNci> &exited)
{
    m_logger->debug("Coverage change detected. [%d] cell entered, [%d] cell exited", static_cast<int>(entered.size()),
                    static_cast<int>(exited.size()));

    bool campedCellLost = m_servingCell.has_value() && std::any_of(exited.begin(), exited.end(), [this](auto &i) {
                              return i == m_servingCell->cellId;
                          });
    if (campedCellLost)
    {
        m_logger->warn("Signal lost from camped cell");
        m_servingCell = std::nullopt;
        m_base->rrcTask->push(new NwUeRlsToRrc(NwUeRlsToRrc::RADIO_LINK_FAILURE));
    }
}

void UeRlsTask::plmnSearchRequested()
{
    m_pendingPlmnResponse = true;
}

void UeRlsTask::handleCellSelectionCommand(const GlobalNci &cellId, bool isSuitable)
{
    slowDownMeasurements();

    if (!m_activeMeasurements.count(cellId))
    {
        m_logger->err("Selected cell is no longer available for camping");
        return;
    }

    auto &measurement = m_activeMeasurements[cellId];

    m_servingCell = UeCellInfo{};
    m_servingCell->sti = measurement.sti;
    m_servingCell->cellId = measurement.cellId;
    m_servingCell->tac = measurement.tac;
    m_servingCell->gnbName = measurement.gnbName;
    m_servingCell->linkIp = measurement.linkIp;
    m_servingCell->cellCategory = isSuitable ? ECellCategory::SUITABLE_CELL : ECellCategory::ACCEPTABLE_CELL;

    auto *w = new NwUeRlsToRrc(NwUeRlsToRrc::SERVING_CELL_CHANGE);
    w->servingCell = *m_servingCell;
    m_base->rrcTask->push(w);
}

} // namespace nr::ue
