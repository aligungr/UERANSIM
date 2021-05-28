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

RrcUeContext *GnbRrcTask::createUe(int id)
{
    auto *ctx = new RrcUeContext(id);
    m_ueCtx[id] = ctx;
    return ctx;
}

RrcUeContext *GnbRrcTask::tryFindUe(int id)
{
    if (m_ueCtx.count(id))
        return m_ueCtx[id];
    return nullptr;
}

RrcUeContext *GnbRrcTask::findUe(int id)
{
    auto *ue = tryFindUe(id);
    if (ue == nullptr)
    {
        m_logger->err("UE context with ID[%d] not found", id);
        return ue;
    }
    return ue;
}

} // namespace nr::gnb
