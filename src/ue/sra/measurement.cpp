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

void UeSraTask::onMeasurement()
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
        sra::SraCellInfoRequest req{};
        sendSraMessage(ip, req);
    }
}

void UeSraTask::receiveCellInfoResponse(const sra::SraCellInfoResponse &msg)
{
    UeCellMeasurement meas{};
    meas.cellId = msg.cellId;
    meas.tac = msg.tac;
    meas.dbm = msg.dbm;
    meas.gnbName = msg.gnbName;
    meas.linkIp = msg.linkIp;

    m_pendingMeasurements[meas.cellId] = meas;
}

void UeSraTask::onCoverageChange(const std::vector<GlobalNci> &entered, const std::vector<GlobalNci> &exited)
{
    m_logger->debug("Coverage change detected. [%d] cell entered, [%d] cell exited", static_cast<int>(entered.size()),
                    static_cast<int>(exited.size()));
}

void UeSraTask::plmnSearchRequested()
{
    std::vector<UeCellMeasurement> measurements{};
    for (auto &m : m_activeMeasurements)
        measurements.push_back(m.second);

    auto *w = new NwUeSraToRrc(NwUeSraToRrc::PLMN_SEARCH_RESPONSE);
    w->measurements = std::move(measurements);
    m_base->rrcTask->push(w);
}

} // namespace nr::ue
