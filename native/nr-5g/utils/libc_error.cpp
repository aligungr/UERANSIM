//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
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

LibError::LibError(const std::string &what, int err) : std::runtime_error(PostfixError(what, err))
{
}
