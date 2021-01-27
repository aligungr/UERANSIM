//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <exception>
#include <stdexcept>

namespace udp
{

class UdpError : public std::runtime_error
{
  public:
    explicit UdpError(const std::string &what) : std::runtime_error(what)
    {
    }
    explicit UdpError(const char *what) : std::runtime_error(what)
    {
    }
};

} // namespace nr::udp
