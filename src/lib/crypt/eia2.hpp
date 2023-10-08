//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <utils/octet_string.hpp>

namespace crypto::eia2
{

uint32_t Compute(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key);

} // namespace crypt::eia2