//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"
#include <algorithm>
#include <nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/rrc/task.hpp>
#include <ue/sm/sm.hpp>

namespace nr::ue
{

void NasMm::handlePlmnSearchResponse(const std::vector<UeCellMeasurement> &measures)
{
    // TODO
    // if (m_base->nodeListener)
    //    m_base->nodeListener->onConnected(app::NodeType::UE, m_base->config->getNodeName(), app::NodeType::GNB,
    //                                      gnbName);

    if (m_mmSubState != EMmSubState::MM_REGISTERED_PLMN_SEARCH &&
        m_mmSubState != EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE &&
        m_mmSubState != EMmSubState::MM_DEREGISTERED_PLMN_SEARCH &&
        m_mmSubState != EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE)
    {
        m_logger->warn("PLMN search response received without being requested");
        return;
    }

    if (measures.empty())
    {
        if (m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH)
        {
            m_logger->err("PLMN selection failure, no cell available");
            switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE);
            return;
        }
        else if (m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH)
        {
            m_logger->err("PLMN selection failure, no cell available");
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE);
            return;
        }
        return; // otherwise it's one of the no-cell-available states, no need to print an error
    }

    int listedAsForbiddenPlmn = 0;
    int listedAsForbiddenTai = 0;
    int listedAsForbiddenServiceAreaPlmn = 0;
    int listedAsForbiddenServiceAreaTai = 0;
    int unlistedPlmn = 0;

    std::vector<UeCellMeasurement> suitable{};
    std::vector<UeCellMeasurement> acceptable{};

    for (auto &item : measures)
    {
        acceptable.push_back(item);

        if (nas::utils::PlmnListContains(m_usim->m_forbiddenPlmnList, item.cellId.plmn))
        {
            listedAsForbiddenPlmn++;
            continue;
        }
        if (nas::utils::TaiListContains(
                m_usim->m_forbiddenTaiList,
                nas::VTrackingAreaIdentity{nas::utils::PlmnFrom(item.cellId.plmn), octet3{item.tac}}))
        {
            listedAsForbiddenTai++;
            continue;
        }
        if (nas::utils::ServiceAreaListForbidsPlmn(m_usim->m_serviceAreaList, nas::utils::PlmnFrom(item.cellId.plmn)))
        {
            listedAsForbiddenServiceAreaPlmn++;
            continue;
        }
        if (nas::utils::ServiceAreaListForbidsTai(
                m_usim->m_serviceAreaList,
                nas::VTrackingAreaIdentity{nas::utils::PlmnFrom(item.cellId.plmn), octet3{item.tac}}))
        {
            listedAsForbiddenServiceAreaTai++;
            continue;
        }

        if (item.cellId.plmn == m_base->config->hplmn || item.cellId.plmn == m_usim->m_currentPlmn ||
            nas::utils::PlmnListContains(m_usim->m_equivalentPlmnList, item.cellId.plmn))
        {
            suitable.push_back(item);
        }
        else
        {
            unlistedPlmn++;
            continue;
        }
    }

    int totalForbidden = listedAsForbiddenPlmn + listedAsForbiddenTai + listedAsForbiddenServiceAreaPlmn +
                         listedAsForbiddenServiceAreaTai;

    auto logErrorSuitableAcceptable = [this, totalForbidden, unlistedPlmn]() {
        m_logger->err("PLMN selection failure, no suitable or acceptable cell can be found");
        if (totalForbidden > 0 || unlistedPlmn > 0)
            m_logger->err("[%d] cell was in forbidden list, [%d] was in unknown PLMN", totalForbidden, unlistedPlmn);
    };

    auto logWarningAcceptable = [this, totalForbidden, unlistedPlmn]() {
        m_logger->warn("PLMN selection failure, no suitable cell can be found, an acceptable cell is selected instead");
        if (totalForbidden > 0 || unlistedPlmn > 0)
            m_logger->warn("[%d] cell was in forbidden list, [%d] was in unknown PLMN", totalForbidden, unlistedPlmn);
    };

    if (!suitable.empty())
    {
        std::stable_sort(suitable.begin(), suitable.end(), [](auto &x, auto &y) { return x.dbm >= y.dbm; });

        auto *w = new NwUeNasToRrc(NwUeNasToRrc::CELL_SELECTION_COMMAND);
        w->cellId = suitable[0].cellId;
        w->isSuitableCell = true;
        m_base->rrcTask->push(w);
    }
    else if (!acceptable.empty())
    {
        std::stable_sort(acceptable.begin(), acceptable.end(), [](auto &x, auto &y) { return x.dbm >= y.dbm; });

        logWarningAcceptable();

        auto *w = new NwUeNasToRrc(NwUeNasToRrc::CELL_SELECTION_COMMAND);
        w->cellId = acceptable[0].cellId;
        w->isSuitableCell = false;
        m_base->rrcTask->push(w);
    }
    else
    {
        if (m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH)
        {
            logErrorSuitableAcceptable();
            switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE);
            return;
        }
        else if (m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH)
        {
            logErrorSuitableAcceptable();
            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NO_CELL_AVAILABLE);
            return;
        }
    }
}

void NasMm::handleRrcConnectionSetup()
{
    switchCmState(ECmState::CM_CONNECTED);
}

void NasMm::handleRrcConnectionRelease()
{
    switchCmState(ECmState::CM_IDLE);
}

void NasMm::handleRadioLinkFailure()
{
    m_logger->debug("Radio link failure detected");
    handleRrcConnectionRelease();
}

void NasMm::localReleaseConnection()
{
    m_logger->info("Performing local release of NAS connection");

    m_base->rrcTask->push(new NwUeNasToRrc(NwUeNasToRrc::LOCAL_RELEASE_CONNECTION));
}

} // namespace nr::ue