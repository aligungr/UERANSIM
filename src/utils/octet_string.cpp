//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "octet_string.hpp"
#include "common.hpp"
#include <cstring>

void OctetString::append(const OctetString &v)
{
    m_data.insert(m_data.end(), v.m_data.begin(), v.m_data.end());
}

void OctetString::appendOctet(uint8_t v)
{
    m_data.push_back(v);
}

void OctetString::appendOctet(int v)
{
    m_data.push_back(v & 0xFF);
}

void OctetString::appendOctet2(octet2 v)
{
    m_data.push_back(v[0]);
    m_data.push_back(v[1]);
}

void OctetString::appendOctet2(int v)
{
    appendOctet2(octet2{v});
}

void OctetString::appendOctet3(octet3 v)
{
    m_data.push_back(v[0]);
    m_data.push_back(v[1]);
    m_data.push_back(v[2]);
}

void OctetString::appendOctet3(int v)
{
    appendOctet3(octet3{v});
}

void OctetString::appendOctet4(octet4 v)
{
    m_data.push_back(v[0]);
    m_data.push_back(v[1]);
    m_data.push_back(v[2]);
    m_data.push_back(v[3]);
}

void OctetString::appendOctet8(octet8 v)
{
    m_data.push_back(v[0]);
    m_data.push_back(v[1]);
    m_data.push_back(v[2]);
    m_data.push_back(v[3]);
    m_data.push_back(v[4]);
    m_data.push_back(v[5]);
    m_data.push_back(v[6]);
    m_data.push_back(v[7]);
}

void OctetString::appendOctet8(int64_t v)
{
    appendOctet8(octet8{v});
}

void OctetString::appendOctet8(uint64_t v)
{
    appendOctet8(octet8{v});
}

void OctetString::appendOctet4(int v)
{
    appendOctet4(octet4{v});
}

int OctetString::length() const
{
    return static_cast<int>(m_data.size());
}

void OctetString::appendOctet(int bigHalf, int littleHalf)
{
    bigHalf &= 0xF;
    littleHalf &= 0xF;
    appendOctet(bigHalf << 4 | littleHalf);
}

const uint8_t *OctetString::data() const
{
    return m_data.data();
}

uint8_t *OctetString::data()
{
    return m_data.data();
}

void OctetString::appendPadding(int length)
{
    for (int i = 0; i < length; i++)
        appendOctet(0);
}

OctetString OctetString::FromHex(const std::string &hex)
{
    return OctetString{utils::HexStringToVector(hex)};
}

std::string OctetString::toHexString() const
{
    return utils::VectorToHexString(m_data);
}

OctetString OctetString::subCopy(int index) const
{
    return subCopy(index, length() - index);
}

OctetString OctetString::subCopy(int index, int length) const
{
    return OctetString{std::vector<uint8_t>{m_data.data() + index, m_data.data() + index + length}};
}

octet OctetString::get(int index) const
{
    return m_data[index];
}

octet2 OctetString::get2(int index) const
{
    return octet2{get(index), get(index + 1)};
}

octet3 OctetString::get3(int index) const
{
    return octet3{get(index), get(index + 1), get(index + 2)};
}

octet4 OctetString::get4(int index) const
{
    return octet4{get(index), get(index + 1), get(index + 2), get(index + 3)};
}

octet8 OctetString::get8(int index) const
{
    return octet8{get(index),     get(index + 1), get(index + 2), get(index + 3),
                  get(index + 4), get(index + 5), get(index + 6), get(index + 7)};
}

int OctetString::getI(int index) const
{
    return get(index);
}

int OctetString::get2I(int index) const
{
    return static_cast<int>(get2(index));
}

int OctetString::get3I(int index) const
{
    return static_cast<int>(get3(index));
}

int OctetString::get4I(int index) const
{
    return static_cast<int>(get4(index));
}

uint32_t OctetString::get4UI(int index) const
{
    return static_cast<uint32_t>(get4(index));
}

long OctetString::get8L(int index) const
{
    return static_cast<long>(get8(index));
}

uint64_t OctetString::get8UL(int index) const
{
    return static_cast<uint64_t>(get8(index));
}

OctetString OctetString::Concat(const OctetString &a, const OctetString &b)
{
    std::vector<uint8_t> v1 = a.m_data;
    std::vector<uint8_t> v2 = b.m_data;
    std::vector<uint8_t> v3;

    v3.reserve(v1.size() + v2.size());
    v3.insert(v3.end(), v1.begin(), v1.end());
    v3.insert(v3.end(), v2.begin(), v2.end());

    return OctetString{std::move(v3)};
}

OctetString OctetString::FromOctet(uint8_t value)
{
    std::vector<uint8_t> v(1);
    v[0] = value;
    return OctetString{std::move(v)};
}

OctetString OctetString::FromOctet(int value)
{
    return FromOctet(static_cast<uint8_t>(value & 0xFF));
}

OctetString OctetString::FromOctet2(octet2 value)
{
    std::vector<uint8_t> v(2);
    v[0] = value[0];
    v[1] = value[1];
    return OctetString{std::move(v)};
}

OctetString OctetString::FromOctet2(int value)
{
    return FromOctet2(octet2{value});
}

OctetString OctetString::FromOctet4(octet4 value)
{
    std::vector<uint8_t> v(4);
    v[0] = value[0];
    v[1] = value[1];
    v[2] = value[2];
    v[3] = value[3];
    return OctetString{std::move(v)};
}

OctetString OctetString::FromOctet4(int value)
{
    return FromOctet4(octet4{value});
}

OctetString OctetString::FromOctet4(uint32_t value)
{
    return FromOctet4(octet4{value});
}

OctetString OctetString::FromOctet8(octet8 value)
{
    std::vector<uint8_t> v(8);
    v[0] = value[0];
    v[1] = value[1];
    v[2] = value[2];
    v[3] = value[3];
    v[4] = value[4];
    v[5] = value[5];
    v[6] = value[6];
    v[7] = value[7];
    return OctetString{std::move(v)};
}

OctetString OctetString::FromOctet8(long value)
{
    return FromOctet8(octet8{value});
}

OctetString OctetString::FromOctet8(uint64_t value)
{
    return FromOctet8(octet8{value});
}

OctetString OctetString::copy() const
{
    return subCopy(0);
}

OctetString OctetString::FromAscii(const std::string &ascii)
{
    return OctetString{std::vector<uint8_t>{ascii.c_str(), ascii.c_str() + ascii.length()}};
}

OctetString OctetString::FromSpare(int length)
{
    return OctetString{std::vector<uint8_t>(length)};
}

OctetString OctetString::Xor(const OctetString &a, const OctetString &b)
{
    const OctetString &min = a.length() < b.length() ? a : b;
    const OctetString &other = a.length() < b.length() ? b : a;

    OctetString res = min.copy();
    for (int i = 0; i < res.length(); i++)
        res.data()[i] ^= other.data()[i];
    return res;
}

OctetString OctetString::Empty()
{
    return FromSpare(0);
}

OctetString OctetString::FromArray(const uint8_t *arr, size_t len)
{
    std::vector<uint8_t> v(len);
    std::memcpy(v.data(), arr, len);
    return OctetString(std::move(v));
}
