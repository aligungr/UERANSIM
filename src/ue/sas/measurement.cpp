//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <utils/common.hpp>

static int DBM_STRONG_STRENGTH_THRESHOLD = -70;

namespace nr::ue
{

void UeSasTask::onMeasurement()
{
    std::vector<GlobalNci> entered{};
    std::vector<GlobalNci> exited{};

    // compare active and pending measurements
    for (auto &m : m_activeMeasurements)
    {
        bool newStrong = m.second.dbm >= DBM_STRONG_STRENGTH_THRESHOLD;
        if (m_pendingMeasurements.count(m.first))
        {
            bool oldStrong = m_pendingMeasurements[m.first].dbm >= DBM_STRONG_STRENGTH_THRESHOLD;
            if (newStrong ^ oldStrong)
                (newStrong ? entered : exited).push_back(m.first);
        }
        else if (newStrong)
            entered.push_back(m.first);
    }
    for (auto &m : m_pendingMeasurements)
    {
        bool oldStrong = m_pendingMeasurements[m.first].dbm >= DBM_STRONG_STRENGTH_THRESHOLD;
        if (!m_activeMeasurements.count(m.first) && oldStrong)
            exited.push_back(m.first);
    }
    if (!entered.empty() && !exited.empty())
        onCoverageChange(entered, exited);

    // copy from pending to active measurements
    m_activeMeasurements = m_pendingMeasurements;
    // clear pending measurements
    m_pendingMeasurements = {};

    // Issue another cell info request for search space
    for (auto &ip : m_cellSearchSpace)
    {
        sas::SasCellInfoRequest req{};
        sendSasMessage(ip, req);
    }
}

void UeSasTask::receiveCellInfoResponse(const sas::SasCellInfoResponse &msg)
{
    UeCellMeasurement meas{};
    meas.cellId = msg.cellId;
    meas.tac = msg.tac;
    meas.dbm = msg.dbm;
    meas.time = utils::CurrentTimeMillis();

    m_pendingMeasurements[meas.cellId] = meas;
}

void UeSasTask::onCoverageChange(const std::vector<GlobalNci> &entered, const std::vector<GlobalNci> &exited)
{
    m_logger->debug("Coverage change detected. [%d] cell entered, [%d] exited to/from coverage",
                    static_cast<int>(entered.size()), static_cast<int>(exited.size()));

    // TODO
}

} // namespace nr::ue
