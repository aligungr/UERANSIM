//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"

namespace nr::gnb
{

int GnbRrcTask::getNextTid()
{
    m_tidCounter++;
    m_tidCounter %= 4;
    return m_tidCounter;
}

} // namespace nr::gnb
