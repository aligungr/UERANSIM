//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "octet_buffer.hpp"
#include "octet_string.hpp"

OctetString OctetBuffer::readOctetString(int length)
{
    std::vector<uint8_t> v{data + index, data + index + length};
    index += length;
    return OctetString(std::move(v));
}
