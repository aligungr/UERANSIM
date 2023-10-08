//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "libc_error.hpp"

#include <cstring>
#include <stdexcept>

static std::string PostfixError(const std::string &what, int err)
{
    char *msg = strdup(strerror(err));
    std::string str(msg);
    free(msg);
    return what.length() > 0 ? what + " " + str : str;
}

LibError::LibError(const std::string &what) : runtime_error(what)
{
}

LibError::LibError(const std::string &what, int err) : std::runtime_error(PostfixError(what, err))
{
}
