//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_tun.hpp"
#include "ue_tun_config.hpp"
#include <libc_error.hpp>

namespace nr::ue::tun
{

int TunAllocate(const char *namePrefix, std::string &allocatedName, std::string &error)
{
    int fd;
    char *name = nullptr;
    try
    {
        fd = tun::AllocateTun(namePrefix, &name);
        allocatedName = std::string{name};
    }
    catch (const LibError &e)
    {
        error = e.what();
        allocatedName = "";
        return 0;
    }

    return fd;
}

bool TunConfigure(const std::string &tunName, const std::string &ipAddress, bool configureRouting, std::string &error)
{
    try
    {
        tun::ConfigureTun(tunName.c_str(), ipAddress.c_str(), configureRouting);
    }
    catch (const LibError &e)
    {
        error = e.what();
        return false;
    }

    return true;
}

} // namespace nr::ue::tun