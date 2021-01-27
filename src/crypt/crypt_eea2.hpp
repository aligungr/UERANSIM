//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <octet_string.hpp>

namespace crypto::eea2
{

void Encrypt(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key);
void Decrypt(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key);

} // namespace crypt::eea2