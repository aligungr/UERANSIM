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

OctetBuffer::OctetBuffer(OctetString &data) : data(data.data()), index(0), size(data.length())
{
}

OctetBuffer::OctetBuffer(uint8_t *data) : data(data), index(0), size(~0)
{
}

OctetBuffer::OctetBuffer(uint8_t *data, size_t size) : data(data), index(0), size(size)
{
}
