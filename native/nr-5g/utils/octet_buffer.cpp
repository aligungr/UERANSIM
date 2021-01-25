//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "octet_buffer.hpp"
#include "octet_string.hpp"

OctetBuffer::OctetBuffer(const OctetString &data) : data(data.data()), index(0), size(data.length())
{
}

OctetBuffer::OctetBuffer(const uint8_t *data, size_t size) : data(data), index(0), size(size)
{
}

OctetString OctetBuffer::readOctetString(int length) const
{
    std::vector<uint8_t> v{data + index, data + index + length};
    index += length;
    return OctetString(std::move(v));
}

OctetString OctetBuffer::readOctetString() const
{
    return readOctetString(static_cast<int>(size - index));
}

std::string OctetBuffer::readUtf8String(int length) const
{
    auto res = std::string(data + index, data + index + length);
    index += length;
    return res;
}
