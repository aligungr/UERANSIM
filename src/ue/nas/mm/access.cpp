//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <lib/nas/utils.hpp>
#include <ue/nas/sm/sm.hpp>

namespace nr::ue
{

bool NasMm::hasEmergency()
{
    // Indicates emergency services are required (even if registered for normal initial registration)
    // This happens if it 'has' or 'need' some emergency PDU Session, as well.
    if (m_rmState == ERmState::RM_REGISTERED && m_registeredForEmergency)
        return true;

    if (m_mmState == EMmState::MM_REGISTERED_INITIATED && m_lastRegistrationRequest &&
        m_lastRegistrationRequest->registrationType.registrationType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
        return true;

    if (m_procCtl.initialRegistration && m_procCtl.initialRegistration == EInitialRegCause::EMERGENCY_SERVICES)
        return true;

    if (m_procCtl.mobilityRegistration && m_procCtl.mobilityRegistration == ERegUpdateCause::EMERGENCY_CASE)
        return true;

    if (m_procCtl.serviceRequest && m_procCtl.serviceRequest == EServiceReqCause::EMERGENCY_FALLBACK)
        return true;

    if (m_sm->anyEmergencySession())
        return true;

    if (m_usim->m_emgIndication)
        return true;

    return false;
}

bool NasMm::isHighPriority()
{
    auto &acc = m_base->config->uacAcc;
    return acc.cls11 || acc.cls12 || acc.cls13 || acc.cls14 || acc.cls15;
}

void NasMm::setN1Capability(bool enabled)
{
    // TODO
}

bool NasMm::isInNonAllowedArea()
{
    auto currentCell = m_base->shCtx.currentCell.get();
    if (!currentCell.hasValue())
        return false;

    auto plmn = currentCell.plmn;
    if (nas::utils::ServiceAreaListForbidsPlmn(m_storage->serviceAreaList->get(), nas::utils::PlmnFrom(plmn)))
        return true;

    int tac = currentCell.tac;
    if (nas::utils::ServiceAreaListForbidsTai(m_storage->serviceAreaList->get(),
                                              nas::VTrackingAreaIdentity{nas::utils::PlmnFrom(plmn), octet3{tac}}))
    {
        return true;
    }
    return false;
}

} // namespace nr::ue
