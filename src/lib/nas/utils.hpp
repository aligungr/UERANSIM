//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "nas.hpp"

namespace nas::utils
{

IESNssai SNssaiFrom(const SingleSlice &v);
IENssai NssaiFrom(const NetworkSlice &v);
IEDnn DnnFromApn(const std::string &apn);
VPlmn PlmnFrom(const Plmn &plmn);
Plmn PlmnFrom(const VPlmn &plmn);

NetworkSlice NssaiTo(const IENssai &v);
SingleSlice SNssaiTo(const IESNssai &v);

bool HasValue(const IEGprsTimer3 &v);
bool HasValue(const IEGprsTimer2 &v);

void AddToPlmnList(IEPlmnList &list, const VPlmn &item);
void AddToTaiList(nas::IE5gsTrackingAreaIdentityList &list, const VTrackingAreaIdentity &tai);
void RemoveFromTaiList(nas::IE5gsTrackingAreaIdentityList &list, const VTrackingAreaIdentity &tai);
bool PlmnListContains(const IEPlmnList &list, VPlmn item);
bool PlmnListContains(const IEPlmnList &list, Plmn item);
bool TaiListContains(const nas::IE5gsTrackingAreaIdentityList &list, const VTrackingAreaIdentity &tai);
bool TaiListContains(const nas::VPartialTrackingAreaIdentityList &list, const VTrackingAreaIdentity &tai);
int TaiListSize(const nas::VPartialTrackingAreaIdentityList &list);
int TaiListSize(const nas::IE5gsTrackingAreaIdentityList &list);
bool ServiceAreaListForbidsPlmn(const nas::IEServiceAreaList &list, const VPlmn &plmn);
bool ServiceAreaListForbidsTai(const nas::IEServiceAreaList &list, const VTrackingAreaIdentity &tai);
bool ServiceAreaListForbidsPlmn(const nas::VPartialServiceAreaList &list, const VPlmn &plmn);
bool ServiceAreaListForbidsTai(const nas::VPartialServiceAreaList &list, const VTrackingAreaIdentity &tai);
bool ServiceAreaListAllowsPlmn(const nas::IEServiceAreaList &list, const VPlmn &plmn);
bool ServiceAreaListAllowsTai(const nas::IEServiceAreaList &list, const VTrackingAreaIdentity &tai);
bool ServiceAreaListAllowsPlmn(const nas::VPartialServiceAreaList &list, const VPlmn &plmn);
bool ServiceAreaListAllowsTai(const nas::VPartialServiceAreaList &list, const VTrackingAreaIdentity &tai);
void RemoveFromServiceAreaList(nas::IEServiceAreaList &list, const VTrackingAreaIdentity &tai);

const char *EnumToString(ERegistrationType v);
const char *EnumToString(EMmCause v);
const char *EnumToString(ESmCause v);
const char *EnumToString(eap::ECode v);
const char *EnumToString(EPduSessionType v);

template <typename T>
inline bool DeepEqualsIe(const T &a, const T &b)
{
    OctetString s1, s2;
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
inline bool DeepEqualsV(const T &a, const T &b)
{
    OctetString s1, s2;
    T::Encode(a, s1);
    T::Encode(b, s2);
    return s1 == s2;
}

template <typename T>
inline T DeepCopyIe(const T &a)
{
    OctetString s;

    if constexpr (std::is_base_of<InformationElement1, T>::value)
    {
        EncodeIe1(0, a, s);
        OctetView buf{s};
        return DecodeIe1<T>(buf);
    }
    else
    {
        Encode2346(a, s);
        OctetView buf{s};
        return DecodeIe2346<T>(buf);
    }
}

inline std::unique_ptr<NasMessage> DeepCopyMsg(const NasMessage &msg)
{
    OctetString stream;

    EncodeNasMessage(msg, stream);
    return DecodeNasMessage(OctetView{stream});
}

} // namespace nas::utils
