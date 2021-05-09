//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <stdexcept>

#include <lib/nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/task.hpp>
#include <ue/rrc/task.hpp>

namespace nr::ue
{

bool NasMm::startECallInactivityIfNeeded()
{
    // 5.5.3 eCall inactivity procedure

    // "The eCall inactivity procedure is performed only in 3GPP access and applicable only to a UE configured for eCall
    // only mode as specified in 3GPP TS 31.102 [22].
    if (!m_usim->m_isECallOnly)
        return false;

    // The procedure shall be started when
    // a) the UE is in any 5GMM-REGISTERED substate except substates 5GMM-REGISTERED.PLMN-SEARCH or
    // 5GMM-REGISTERED.NO-CELL-AVAILABLE;"
    if (m_mmState != EMmState::MM_REGISTERED)
        return false;
    if (m_mmSubState == EMmSubState::MM_REGISTERED_PLMN_SEARCH ||
        m_mmSubState == EMmSubState::MM_REGISTERED_NO_CELL_AVAILABLE)
        return false;
    // "b) the UE is in 5GMM-IDLE mode; and"
    if (m_cmState != ECmState::CM_IDLE)
        return false;
    // c) one of the following conditions applies:
    // 1) timer T3444 expires or is found to have already expired and timer T3445 is not running;
    // 2) timer T3445 expires or is found to have already expired and timer T3444 is not running; or
    // 3) timers T3444 and T3445 expire or are found to have already expired.
    if (!(!m_timers->t3444.isRunning() && m_timers->t3445.isRunning()) ||
        (!m_timers->t3445.isRunning() && m_timers->t3444.isRunning()) ||
        (!m_timers->t3444.isRunning() && !m_timers->t3445.isRunning()))
        return false;

    // "The UE shall then perform the following actions:
    // a) stop other running timers (e.g. T3511, T3512);
    // b) if the UE is currently registered to the network for 5GS services, perform a de-registration procedure;
    // c) delete any 5G-GUTI, TAI list, last visited registered TAI, list of equivalent PLMNs, and ngKSI; and
    // d) enter 5GMM-DEREGISTERED.eCALL-INACTIVE state."

    // TODO: Spec says 'other running timers' in item a), what are those timers other than 3511 and 3512?
    m_timers->t3511.stop();
    m_timers->t3512.stop();

    // Spec says if the UE is currently registered to the network for 5GS services, but it also says procedure shall
    // be started if ... and 5GMM-REGISTERED ....
    if (m_rmState != ERmState::RM_REGISTERED)
    {
        // Therefore just assert that the UE is registered
        throw std::runtime_error("UE MM eCall - MM and RM state inconsistent");
    }
    // And perform de-registration.
    // NOTE: The items c) and d) is performed after de-registration by the other function, therefore we are just
    // performing de-registration for now.
    sendDeregistration(EDeregCause::ECALL_INACTIVITY);
    return true;
}

bool NasMm::switchToECallInactivityIfNeeded()
{
    if (!m_usim->m_isECallOnly)
        return false;

    if (m_mmState != EMmState::MM_DEREGISTERED)
        return false;

    if (m_cmState != ECmState::CM_IDLE)
        return false;
    if (!((!m_timers->t3444.isRunning() && m_timers->t3445.isRunning()) ||
          (!m_timers->t3445.isRunning() && m_timers->t3444.isRunning()) ||
          (!m_timers->t3444.isRunning() && !m_timers->t3445.isRunning())))
        return false;

    // Perform item c) in 5.5.3
    m_usim->m_storedGuti = {};
    m_usim->m_taiList = {};
    m_usim->m_lastVisitedRegisteredTai = {};
    m_usim->m_equivalentPlmnList = {};
    m_usim->m_currentNsCtx = {};
    m_usim->m_nonCurrentNsCtx = {};

    // Perform item d) in 5.5.3
    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_ECALL_INACTIVE);
    return true;
}

} // namespace nr::ue
