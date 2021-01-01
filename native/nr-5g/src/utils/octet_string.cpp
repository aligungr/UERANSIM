#include "octet_string.hpp"
#include <cstring>

int OctetString::size() const
{
    return length;
}

uint8_t &OctetString::at(size_t i)
{
    return data[i + offset];
}

uint8_t &OctetString::at(size_t i) const
{
    return data[i + offset];
}

OctetString *OctetString::wrapBuffer(std::shared_ptr<uint8_t[]> &data, size_t offset, size_t length)
{
    return new OctetString(offset, length, data);
}

OctetString *OctetString::wrapBuffer(std::shared_ptr<uint8_t[]> &data, size_t length)
{
    return wrapBuffer(data, 0, length);
}

OctetString *OctetString::sub(size_t index)
{
    return sub(index, size() - index);
}

OctetString *OctetString::sub(size_t index, size_t len)
{
    return wrapBuffer(data, offset + index, len);
}

OctetString *OctetString::copyBuffer(uint8_t *data, size_t offset, size_t length)
{
    auto *copy = new uint8_t[length];
    std::memcpy(copy, data + offset, length);
    return wrapBuffer(std::shared_ptr<uint8_t[]>(copy), length);
}

OctetString *OctetString::copyBuffer(uint8_t *data, size_t length)
{
    return copyBuffer(data, 0, length);
}

OctetString *OctetString::wrapBuffer(std::shared_ptr<uint8_t[]> &&data, size_t length)
{
    return new OctetString(0, length, data);
}
