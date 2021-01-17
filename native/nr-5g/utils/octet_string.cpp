//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "octet_string.hpp"

void OctetString::append(const OctetString &v)
{
    m_data.insert(v.m_data.end(), v.m_data.begin(), v.m_data.end());
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
