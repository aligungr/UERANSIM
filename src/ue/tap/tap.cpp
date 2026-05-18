//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "tap.hpp"
#include "config.hpp"
#include <utils/libc_error.hpp>


namespace nr::ue::tap
{

int TapAllocate(const char *namePrefix, std::string &allocatedName, std::string &error)
{
    int fd;
    char *name = nullptr;
    try
    {
        fd = tap::AllocateTap(namePrefix, &name);
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

bool TapConfigure(const std::string &tapName, int mtu, std::string &error)
{
    try
    {
        tap::ConfigureTap(tapName.c_str(), mtu);
    }
    catch (const LibError &e)
    {
        error = e.what();
        return false;
    }

    return true;
}

}