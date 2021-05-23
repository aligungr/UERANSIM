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
    // TODO

    triggerMmCycle();
}

void NasMm::mobilityUpdatingRequired(ERegUpdateCause cause)
{
    // TODO

    // TODO: "the periodic registration update procedure is delayed until the UE returns to
    //  5GMM-REGISTERED.NORMAL-SERVICE over 3GPP access." See 5.3.7

    triggerMmCycle();
}

void NasMm::serviceRequestRequiredForSignalling()
{
    // TODO
}

void NasMm::serviceRequestRequiredForData()
{
    static constexpr const int64_t SERVICE_REQUEST_NEEDED_FOR_DATA_THRESHOLD = 1000;

    auto currentTime = utils::CurrentTimeMillis();
    if (currentTime - m_lastTimeServiceReqNeededIndForData > SERVICE_REQUEST_NEEDED_FOR_DATA_THRESHOLD)
    {
        serviceRequestRequired(m_cmState == ECmState::CM_CONNECTED ? EServiceReqCause::CONNECTED_UPLINK_DATA_PENDING
                                                                   : EServiceReqCause::IDLE_UPLINK_DATA_PENDING);

        m_lastTimeServiceReqNeededIndForData = currentTime;

        triggerMmCycle();
    }
}

void NasMm::serviceRequestRequired(EServiceReqCause cause)
{
    // TODO

    triggerMmCycle();
}

void NasMm::deregistrationRequired(EDeregCause cause)
{
    // TODO

    triggerMmCycle();
}

} // namespace nr::ue