//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "unique_buffer.hpp"

UniqueBuffer UniqueBuffer::FromOctetString(const OctetString &s)
{
    auto buffer = new uint8_t[s.length()];
    std::memcpy(buffer, s.data(), static_cast<size_t>(s.length()));
    return UniqueBuffer{buffer, static_cast<size_t>(s.length())};
}
