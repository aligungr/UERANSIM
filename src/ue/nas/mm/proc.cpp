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
    if (m_procCtl.initialRegistration == cause)
        return;

    m_logger->debug("Initial registration required due to [%s]", ToJson(cause).str().c_str());

    AssignCause(m_procCtl.initialRegistration, cause);
    triggerMmCycle();
}

void NasMm::mobilityUpdatingRequired(ERegUpdateCause cause)
{
    if (m_procCtl.mobilityRegistration == cause)
        return;

    m_logger->debug("Mobility registration updating required due to [%s]", ToJson(cause).str().c_str());

    AssignCause(m_procCtl.mobilityRegistration, cause);
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

void NasMm::serviceRequestRequired(EServiceReqCause cause)
{
    if (m_procCtl.serviceRequest == cause)
        return;

    m_logger->debug("Service request required due to [%s]", ToJson(cause).str().c_str());

    AssignCause(m_procCtl.serviceRequest, cause);
    triggerMmCycle();
}

void NasMm::deregistrationRequired(EDeregCause cause)
{
    if (m_procCtl.deregistration == cause)
        return;

    m_logger->debug("De-registration required due to [%s]", ToJson(cause).str().c_str());

    AssignCause(m_procCtl.deregistration, cause);
    triggerMmCycle();
}

void NasMm::invokeProcedures()
{
    if (m_procCtl.deregistration)
    {
        EProcRc rc = sendDeregistration(*m_procCtl.deregistration);
        if (rc != EProcRc::STAY)
            m_procCtl.deregistration = std::nullopt;

        m_procCtl.initialRegistration = std::nullopt;
        m_procCtl.mobilityRegistration = std::nullopt;
        m_procCtl.serviceRequest = std::nullopt;
        return;
    }

    // note1:
    // TODO: "the periodic registration update procedure is delayed until the UE returns to
    //  5GMM-REGISTERED.NORMAL-SERVICE over 3GPP access." See 5.3.7
}

} // namespace nr::ue