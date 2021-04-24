//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <utils/octet_string.hpp>

namespace crypto::eia2
{

uint32_t Compute(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key);

} // namespace crypt::eia2