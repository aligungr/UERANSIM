//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <gnb/ngap/task.hpp>
#include <lib/rrc/encode.hpp>

namespace nr::gnb
{

void GnbRrcTask::handleRlsSapMessage(NwGnbRlsToRrc &msg)
{
    switch (msg.present)
    {
    case NwGnbRlsToRrc::SIGNAL_DETECTED: {
        m_logger->info("UE[%d] new signal detected", msg.ueId);
        triggerSysInfoBroadcast();
        break;
    }
    }
}

} // namespace nr::gnb
