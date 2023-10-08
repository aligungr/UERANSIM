//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <stdexcept>

class LibError : public std::runtime_error
{
  public:
    explicit LibError(const std::string &what);
    LibError(const std::string &what, int err);
};