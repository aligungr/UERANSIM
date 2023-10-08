//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "sm.hpp"

namespace nr::ue
{

NasSm::NasSm(TaskBase *base, NasTimers *timers) : m_base(base), m_timers(timers), m_mm(nullptr)
{
    m_logger = base->logBase->makeUniqueLogger(base->config->getLoggerPrefix() + "nas");

    for (int i = 0; i < 16; i++)
        m_pduSessions[i] = new PduSession(i);
}

void NasSm::onStart(NasMm *mm)
{
    m_mm = mm;
}

void NasSm::onQuit()
{
    // TODO
}

} // namespace nr::ue