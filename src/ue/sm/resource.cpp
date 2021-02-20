//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <nas/proto_conf.hpp>
#include <nas/utils.hpp>
#include <ue/app/task.hpp>

namespace nr::ue
{

void NasSm::localReleaseSession(int psi)
{
    m_logger->debug("Performing local release of PDU session[%d]", psi);

    freePduSessionId(psi);

    auto *statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::SESSION_RELEASE);
    statusUpdate->psi = psi;
    m_base->appTask->push(statusUpdate);
}

} // namespace nr::ue