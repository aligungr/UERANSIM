//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "encode.hpp"
#include "task.hpp"
#include "utils.hpp"

#include <gnb/gtp/task.hpp>
#include <gnb/rrc/task.hpp>

namespace nr::gnb
{

void NgapTask::handleRadioLinkFailure(int ueId)
{
    // Notify GTP task
    auto *w2 = new NwGnbNgapToGtp(NwGnbNgapToGtp::UE_CONTEXT_RELEASE);
    w2->ueId = ueId;
    m_base->gtpTask->push(w2);

    // Notify AMF
    sendContextRelease(ueId, NgapCause::RadioNetwork_radio_connection_with_ue_lost);
}

} // namespace nr::gnb
