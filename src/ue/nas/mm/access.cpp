//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <nas/utils.hpp>
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

    // TODO: Other case which is an emergency PDU session is established, or need to be established (and wanted to be
    //  established soon)
    if (m_sm->anyEmergencySession())
        return true;
    return false;
}

bool NasMm::isHighPriority()
{
    // TODO
    return false;
}

void NasMm::setN1Capability(bool enabled)
{
    // TODO
}

} // namespace nr::ue
