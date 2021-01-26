//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "nas.hpp"

namespace nas::utils
{

IESNssai SNssaiFrom(const SliceSupport &v);
IENssai NssaiFrom(const std::vector<SliceSupport> &v);
IEDnn DnnFromApn(const std::string& apn);

bool HasValue(const IEGprsTimer3 &v);
bool HasValue(const IEGprsTimer2 &v);

const char *EnumToString(ERegistrationType v);
const char *EnumToString(EMmCause v);
const char *EnumToString(ESmCause v);
const char *EnumToString(eap::ECode v);
const char *EnumToString(EPduSessionType v);

template <typename T>
inline bool DeepEqualsIe(const T &a, const T &b)
{
    OctetString s1{}, s2{};
    if constexpr (std::is_base_of<InformationElement1, T>::value)
    {
        EncodeIe1(0, a, s1);
        EncodeIe1(0, a, s2);
    }
    else
    {
        Encode2346(a, s1);
        Encode2346(b, s2);
    }
    return s1 == s2;
}

template <typename T>
inline T DeepCopyIe(const T &a)
{
    OctetString s;

    if constexpr (std::is_base_of<InformationElement1, T>::value)
    {
        EncodeIe1(0, a, s);
        OctetBuffer buf{s};
        return DecodeIe1<T>(buf);
    }
    else
    {
        Encode2346(a, s);
        OctetBuffer buf{s};
        return DecodeIe2346<T>(buf);
    }
}

} // namespace nas::utils
