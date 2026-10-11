//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "octet_view.hpp"
#include "octet_string.hpp"

#include <stdexcept>

OctetView::OctetView(const OctetString &data) : data(data.data()), index(0), size(data.length())
{
}

OctetView::OctetView(const uint8_t *data, size_t size) : data(data), index(0), size(size)
{
}

OctetString OctetView::readOctetString(int length) const
{
    if (length < 0)
        throw std::out_of_range("readOctetString: negative length");
    if (length == 0)
        return {};
    if (index + static_cast<size_t>(length) > size)
        throw std::out_of_range("readOctetString: out of bounds");

    std::vector<uint8_t> v{data + index, data + index + length};
    index += length;
    return OctetString(std::move(v));
}

OctetString OctetView::readOctetString(size_t length) const
{
    if (length > static_cast<size_t>(INT32_MAX))
        throw std::out_of_range("readOctetString: length exceeds INT32_MAX");
    return readOctetString(static_cast<int>(length));
}

OctetString OctetView::readOctetString() const
{
    return readOctetString(static_cast<int>(size - index));
}

std::string OctetView::readUtf8String(int length) const
{
    if (length < 0 || index + static_cast<size_t>(length) > size)
        throw std::out_of_range("readUtf8String: out of bounds");
    auto res = std::string(data + index, data + index + length);
    index += length;
    return res;
}

std::string OctetView::readUtf8String(size_t length) const
{
    if (length > static_cast<size_t>(INT32_MAX))
        throw std::out_of_range("readUtf8String: length exceeds INT32_MAX");
    return readUtf8String(static_cast<int>(length));
}
