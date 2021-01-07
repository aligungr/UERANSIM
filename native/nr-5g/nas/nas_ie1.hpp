//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <octet_buffer.hpp>
#include <octet_string.hpp>

#include "nas_enums.hpp"

namespace nas
{

template <typename T>
struct InformationElement
{
    virtual T decodeIE(OctetBuffer &stream) = 0;
};

template <typename T>
struct InformationElement1 : InformationElement<T>
{
    virtual T decodeIE1(int value) = 0;
    virtual int encodeIE1() = 0;

    T decodeIE(OctetBuffer &stream) final
    {
        int octet = stream.readI();
        int iei = octet >> 4 & 0xF;
        int value = octet & 0xF;
        return decodeIE1(value);
    }
};

struct IE5gsIdentityType : InformationElement1<IE5gsIdentityType>
{
    EIdentityType value;

    explicit IE5gsIdentityType(EIdentityType value) : value(value)
    {
    }

    IE5gsIdentityType decodeIE1(int val) override
    {
        return IE5gsIdentityType{(EIdentityType)(val & 0b111)};
    }

    int encodeIE1() override
    {
        return (int)value;
    }
};

// TODO: Others.. IE1

} // namespace nas