//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <string>
#include <unordered_map>
#include <vector>

namespace app
{

void Initialize();

void RunAtExit(void (*fun)());

void DeleteAtExit(const std::string &file);

} // namespace app
