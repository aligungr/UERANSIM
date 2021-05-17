//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <algorithm>

#include <lib/nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/sm/sm.hpp>
#include <ue/rrc/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

void NasMm::performPlmnSelection()
{
    int64_t currentTime = utils::CurrentTimeMillis();

    // If the state is PLMN_SEARCH instead of NO_CELL_AVAILABLE, then we log the errors more intensely.
    int64_t loggingThreshold = m_mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH ||
                                       m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH
                                   ? 1'000LL
                                   : 10'000LL;

    bool logFailures = currentTime - m_lastTimePlmnSearchFailureLogged >= loggingThreshold;

    // If the device just switched on, then no error logging for PLMN selection failures
    if (m_lastTimePlmnSearchFailureLogged == 0)
    {
        m_lastTimePlmnSearchFailureLogged = currentTime;
        logFailures = false;
    }

    Plmn lastSelectedPlmn = m_base->shCtx.selectedPlmn.get();

    std::unordered_set<Plmn> plmns = m_base->shCtx.availablePlmns.get();

    if (!m_usim->isValid() || plmns.empty())
    {
        if (logFailures)
        {
            if (!m_usim->isValid())
                m_logger->warn("No PLMN can be selected, USIM is invalid");
            else
                m_logger->err("PLMN selection failure, no cells in coverage");
            m_lastTimePlmnSearchFailureLogged = currentTime;
        }

        m_base->shCtx.selectedPlmn.set({});
        return;
    }

    // Determine candidate PLMNs from the list. Candidates are in priority order
    std::vector<Plmn> candidates;

    // Highest priority is for HPLMN, so just look for HPLMN first.
    for (auto &plmn : plmns)
        if (plmn == m_base->config->hplmn)
            candidates.push_back(plmn);

    // Then again look for the all PLMNS
    for (auto &plmn : plmns)
    {
        if (plmn == m_base->config->hplmn)
            continue; // If it's the HPLMN, it's already added above
        if (nas::utils::PlmnListContains(m_usim->m_forbiddenPlmnList, plmn))
            continue;
        if (nas::utils::ServiceAreaListForbidsPlmn(m_storage->m_serviceAreaList->get(), nas::utils::PlmnFrom(plmn)))
            continue;
        if (nas::utils::PlmnListContains(m_usim->m_equivalentPlmnList, plmn))
            candidates.push_back(plmn);
    }

    Plmn selected = candidates.empty() ? Plmn{} : candidates[0];

    if (!selected.hasValue())
    {
        if (logFailures)
        {
            m_logger->err("No PLMN could be selected among [%d] PLMNs", static_cast<int>(plmns.size()));
            m_lastTimePlmnSearchFailureLogged = currentTime;
        }
    }
    else if (lastSelectedPlmn != selected)
    {
        m_logger->info("Selected PLMN[%s]", ToJson(selected).str().c_str());
        m_base->rrcTask->push(new NwUeNasToRrc(NwUeNasToRrc::RRC_NOTIFY));
    }

    m_base->shCtx.selectedPlmn.set(selected);

    // Check if the RRC selected some cell
    bool cellSelected{};
    ECellCategory cellCategory{};

    m_base->shCtx.currentCell.access([&cellSelected, &cellCategory](auto &value) {
        if (value.cellId != 0)
        {
            cellSelected = true;
            cellCategory = value.category;
        }
    });

    if (cellSelected)
    {
        if (cellCategory == ECellCategory::SUITABLE_CELL)
        {
            if (m_mmState == EMmState::MM_REGISTERED)
                switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
            else
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE);
        }
        else
        {
            if (m_mmState == EMmState::MM_REGISTERED)
                switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_LIMITED_SERVICE);
            else
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
        }
    }
}

void NasMm::handleServingCellChange(const UeCellInfo &servingCell)
{
    if (m_cmState == ECmState::CM_CONNECTED)
    {
        m_logger->err("Serving cell change in CM-CONNECTED");
        return;
    }

    if (servingCell.cellCategory != ECellCategory::ACCEPTABLE_CELL &&
        servingCell.cellCategory != ECellCategory::SUITABLE_CELL)
    {
        m_logger->err("Serving cell change with unhandled cell category");
        return;
    }

    m_logger->info("Serving cell determined [%s]", servingCell.gnbName.c_str());

    if (m_mmState == EMmState::MM_REGISTERED || m_mmState == EMmState::MM_DEREGISTERED)
    {
        bool isSuitable = servingCell.cellCategory == ECellCategory::SUITABLE_CELL;

        if (m_mmState == EMmState::MM_REGISTERED)
            switchMmState(EMmState::MM_REGISTERED, isSuitable ? EMmSubState::MM_REGISTERED_NORMAL_SERVICE
                                                              : EMmSubState::MM_REGISTERED_LIMITED_SERVICE);
        else
            switchMmState(EMmState::MM_DEREGISTERED, isSuitable ? EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE
                                                                : EMmSubState::MM_DEREGISTERED_LIMITED_SERVICE);
    }
    // todo: else, other states abnormal case

    resetRegAttemptCounter();

    m_usim->m_servingCell = servingCell;
    m_usim->m_currentPlmn = servingCell.cellId.plmn;
    m_usim->m_currentTai =
        nas::VTrackingAreaIdentity{nas::utils::PlmnFrom(servingCell.cellId.plmn), octet3{servingCell.tac}};
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
    if (m_cmState == ECmState::CM_CONNECTED)
    {
        m_logger->err("Radio link failure detected");
    }

    m_usim->m_servingCell = std::nullopt;
    m_usim->m_currentPlmn = std::nullopt;
    m_usim->m_currentTai = std::nullopt;

    handleRrcConnectionRelease();

    if (m_mmState == EMmState::MM_REGISTERED)
        switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NA);
    else if (m_mmState == EMmState::MM_DEREGISTERED)
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
}

void NasMm::localReleaseConnection()
{
    m_logger->info("Performing local release of NAS connection");

    m_base->rrcTask->push(new NwUeNasToRrc(NwUeNasToRrc::LOCAL_RELEASE_CONNECTION));
}

void NasMm::handlePaging(const std::vector<GutiMobileIdentity> &tmsiIds)
{
    if (m_usim->m_storedGuti.type == nas::EIdentityType::NO_IDENTITY)
        return;
    bool tmsiMatches = false;
    for (auto &tmsi : tmsiIds)
    {
        if (tmsi.amfSetId == m_usim->m_storedGuti.gutiOrTmsi.amfSetId &&
            tmsi.amfPointer == m_usim->m_storedGuti.gutiOrTmsi.amfPointer &&
            tmsi.tmsi == m_usim->m_storedGuti.gutiOrTmsi.tmsi)
        {
            tmsiMatches = true;
            break;
        }
    }

    if (!tmsiMatches)
        return;

    m_timers->t3346.stop();

    if (m_mmState == EMmState::MM_REGISTERED_INITIATED || m_mmState == EMmState::MM_DEREGISTERED_INITIATED ||
        m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED)
    {
        m_logger->debug("Ignoring received Paging, another procedure already initiated");
        return;
    }

    m_logger->debug("Responding to received Paging");

    if (m_cmState == ECmState::CM_CONNECTED)
        sendMobilityRegistration(ERegUpdateCause::PAGING_OR_NOTIFICATION);
    else
        sendServiceRequest(EServiceReqCause::IDLE_PAGING);
}

} // namespace nr::ue