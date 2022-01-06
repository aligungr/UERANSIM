//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "layer.hpp"
#include "config.hpp"

#include <unistd.h>
#include <ue/nts.hpp>
#include <ue/task.hpp>
#include <utils/libc_error.hpp>

namespace nr::ue
{

TunLayer::TunLayer(UeTask *ue) : m_ue{ue}
{
}

TunLayer::~TunLayer() = default;

std::string TunLayer::allocate(int psi, const std::string &ipAddress, bool configureRouting, std::string &outError)
{
    if (!utils::IsRoot())
    {
        outError = "TUN interface could not be setup. Permission denied. Please run the UE with 'sudo'";
        return {};
    }

    if (psi == 0 || psi > 15)
    {
        outError = "Connection could not setup. Invalid PSI.";
        return {};
    }

    if (m_ue->fdBase->contains(FdBase::PS_START + psi))
    {
        outError = "Connection could not setup. PSI already exists in TUN layer";
        return {};
    }

    std::string error{}, allocatedName{};
    int fd = tun::TunAllocate(cons::TunNamePrefix, allocatedName, error);
    if (fd == 0 || error.length() > 0)
    {
        outError = "TUN allocation failure [" + error + "]";
        return {};
    }

    bool r = tun::TunConfigure(allocatedName, ipAddress, cons::TunMtu, configureRouting, error);
    if (!r || error.length() > 0)
    {
        outError = "TUN configuration failure [" + error + "]";
        return {};
    }

    m_ue->fdBase->allocate(FdBase::PS_START + psi, fd, false);

    return allocatedName;
}

void TunLayer::release(int psi)
{
    m_ue->fdBase->release(FdBase::PS_START + psi);
}

} // namespace nr::ue
