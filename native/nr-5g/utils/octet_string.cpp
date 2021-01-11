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
    data.insert(v.data.end(), v.data.begin(), v.data.end());
}

void OctetString::appendOctet(uint8_t v)
{
    data.push_back(v);
}

void OctetString::appendOctet(int v)
{
    data.push_back(v & 0xFF);
}

void OctetString::appendOctet2(octet2 v)
{
    data.push_back(v[0]);
    data.push_back(v[1]);
}

void OctetString::appendOctet2(int v)
{
    appendOctet2(octet2{v});
}

void OctetString::appendOctet3(octet3 v)
{
    data.push_back(v[0]);
    data.push_back(v[1]);
    data.push_back(v[2]);
}

void OctetString::appendOctet3(int v)
{
    appendOctet3(octet3{v});
}

void OctetString::appendOctet4(octet4 v)
{
    data.push_back(v[0]);
    data.push_back(v[1]);
    data.push_back(v[2]);
    data.push_back(v[3]);
}

void OctetString::appendOctet4(int v)
{
    appendOctet4(octet4{v});
}

int OctetString::length() const
{
    return static_cast<int>(data.size());
}

void OctetString::appendOctet(int bigHalf, int littleHalf)
{
    bigHalf &= 0xF;
    littleHalf &= 0xF;
    appendOctet(bigHalf << 4 | littleHalf);
}
