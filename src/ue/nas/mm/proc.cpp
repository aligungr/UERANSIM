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

void NasMm::initialRegistrationRequired(EInitialRegCause cause)
{
    m_procCtl.initialRegistration = cause;
    triggerMmCycle();
}

void NasMm::mobilityUpdatingRequired(ERegUpdateCause cause)
{
    m_procCtl.mobilityRegistration = cause;
    triggerMmCycle();

    // TODO: "the periodic registration update procedure is delayed until the UE returns to
    //  5GMM-REGISTERED.NORMAL-SERVICE over 3GPP access." See 5.3.7
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
    m_procCtl.serviceRequest = cause;
    triggerMmCycle();
}

void NasMm::deregistrationRequired(EDeregCause cause)
{
    m_procCtl.deregistration = cause;
    triggerMmCycle();
}

void NasMm::invokeProcedures()
{
    // TODO
}

} // namespace nr::ue