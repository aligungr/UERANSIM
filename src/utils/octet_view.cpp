//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "octet_view.hpp"
#include "octet_string.hpp"

OctetView::OctetView(const OctetString &data) : data(data.data()), index(0), size(data.length())
{
}

OctetView::OctetView(const uint8_t *data, size_t size) : data(data), index(0), size(size)
{
}

OctetString OctetView::readOctetString(int length) const
{
    if (length == 0)
        return {};

    std::vector<uint8_t> v{data + index, data + index + length};
    index += length;
    return OctetString(std::move(v));
}

OctetString OctetView::readOctetString(size_t length) const
{
    return readOctetString(static_cast<int>(length));
}

OctetString OctetView::readOctetString() const
{
    return readOctetString(static_cast<int>(size - index));
}

std::string OctetView::readUtf8String(int length) const
{
    auto res = std::string(data + index, data + index + length);
    index += length;
    return res;
}

std::string OctetView::readUtf8String(size_t length) const
{
    return readUtf8String(static_cast<int>(length));
}
