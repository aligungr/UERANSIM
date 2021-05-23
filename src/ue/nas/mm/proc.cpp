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

} // namespace nr::ue