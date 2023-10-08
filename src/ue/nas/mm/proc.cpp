//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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

static void AssignCause(std::optional<EInitialRegCause> &oldCause, EInitialRegCause newCause)
{
    oldCause = newCause;
}

static void AssignCause(std::optional<ERegUpdateCause> &oldCause, ERegUpdateCause newCause)
{
    oldCause = newCause;
}

static void AssignCause(std::optional<EServiceReqCause> &oldCause, EServiceReqCause newCause)
{
    oldCause = newCause;
}

static void AssignCause(std::optional<EDeregCause> &oldCause, EDeregCause newCause)
{
    if ((oldCause == EDeregCause::SWITCH_OFF || oldCause == EDeregCause::USIM_REMOVAL) &&
        (newCause == EDeregCause::SWITCH_OFF || newCause == EDeregCause::USIM_REMOVAL))
        return;
    if (oldCause == EDeregCause::DISABLE_5G && newCause == EDeregCause::ECALL_INACTIVITY)
        return;

    oldCause = newCause;
}

void NasMm::initialRegistrationRequired(EInitialRegCause cause)
{
    if (m_mmState == EMmState::MM_NULL)
        return;

    if (m_procCtl.initialRegistration == cause)
        return;

    m_logger->debug("Initial registration required due to [%s]", ToJson(cause).str().c_str());

    AssignCause(m_procCtl.initialRegistration, cause);
    triggerMmCycle();
}

void NasMm::mobilityUpdatingRequired(ERegUpdateCause cause)
{
    if (m_mmState == EMmState::MM_NULL)
        return;

    if (m_procCtl.mobilityRegistration == cause)
        return;

    m_logger->debug("Mobility registration updating required due to [%s]", ToJson(cause).str().c_str());

    AssignCause(m_procCtl.mobilityRegistration, cause);
    triggerMmCycle();
}

void NasMm::serviceRequestRequired(EServiceReqCause cause)
{
    if (m_mmState == EMmState::MM_NULL)
        return;

    if (m_procCtl.serviceRequest == cause)
        return;

    m_logger->debug("Service request required due to [%s]", ToJson(cause).str().c_str());

    AssignCause(m_procCtl.serviceRequest, cause);
    triggerMmCycle();
}

void NasMm::deregistrationRequired(EDeregCause cause)
{
    if (m_mmState == EMmState::MM_NULL)
        return;

    if (m_procCtl.deregistration == cause)
        return;

    m_logger->debug("De-registration required due to [%s]", ToJson(cause).str().c_str());

    AssignCause(m_procCtl.deregistration, cause);
    triggerMmCycle();
}

void NasMm::serviceRequestRequiredForSignalling()
{
    serviceRequestRequired(EServiceReqCause::IDLE_UPLINK_SIGNAL_PENDING);
}

void NasMm::serviceRequestRequiredForData()
{
    serviceRequestRequired(m_cmState == ECmState::CM_CONNECTED ? EServiceReqCause::CONNECTED_UPLINK_DATA_PENDING
                                                               : EServiceReqCause::IDLE_UPLINK_DATA_PENDING);
}

void NasMm::invokeProcedures()
{
    auto activeCell = m_base->shCtx.currentCell.get();
    bool hasActiveCell = activeCell.hasValue();

    if (hasActiveCell && m_procCtl.deregistration)
    {
        EProcRc rc = sendDeregistration(*m_procCtl.deregistration);
        if (rc == EProcRc::OK)
        {
            m_procCtl.deregistration = std::nullopt;
            m_procCtl.initialRegistration = std::nullopt;
            m_procCtl.mobilityRegistration = std::nullopt;
            m_procCtl.serviceRequest = std::nullopt;
        }
        else if (rc == EProcRc::CANCEL)
        {
            m_procCtl.deregistration = std::nullopt;
        }
        return;
    }

    if (hasActiveCell && m_procCtl.initialRegistration)
    {
        EProcRc rc = sendInitialRegistration(*m_procCtl.initialRegistration);
        if (rc == EProcRc::OK)
        {
            m_procCtl.initialRegistration = std::nullopt;
            m_procCtl.mobilityRegistration = std::nullopt;
            m_procCtl.serviceRequest = std::nullopt;
            return;
        }
        else if (rc == EProcRc::CANCEL)
        {
            m_procCtl.initialRegistration = std::nullopt;
            return;
        }
    }

    if (hasActiveCell && m_procCtl.mobilityRegistration)
    {
        EProcRc rc = sendMobilityRegistration(*m_procCtl.mobilityRegistration);
        if (rc == EProcRc::OK)
        {
            m_procCtl.mobilityRegistration = std::nullopt;
            m_procCtl.serviceRequest = std::nullopt;
            return;
        }
        else if (rc == EProcRc::CANCEL)
        {
            m_procCtl.mobilityRegistration = std::nullopt;
            return;
        }
        return;
    }

    if (hasActiveCell && m_procCtl.serviceRequest)
    {
        EProcRc rc = sendServiceRequest(*m_procCtl.serviceRequest);
        if (rc == EProcRc::OK || rc == EProcRc::CANCEL)
        {
            m_procCtl.serviceRequest = std::nullopt;
            return;
        }
    }

    if (m_mmSubState == EMmSubState::MM_REGISTERED_NORMAL_SERVICE ||
        m_mmSubState == EMmSubState::MM_REGISTERED_LIMITED_SERVICE)
    {
        m_sm->establishRequiredSessions();
    }
}

bool NasMm::hasPendingProcedure()
{
    if (m_procCtl.initialRegistration)
        return true;
    if (m_procCtl.mobilityRegistration)
        return true;
    if (m_procCtl.serviceRequest)
        return true;
    if (m_procCtl.deregistration)
        return true;

    // TODO: Check for SM sublayer, (and other stacks in the future such as SMS) because they are transported over MM.
    // NOTE: Other MM common procedures are ignored. (Except UL/DL NAS Transport)
    return false;
}

} // namespace nr::ue