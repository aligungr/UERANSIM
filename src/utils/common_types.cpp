//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "common_types.hpp"
#include "common.hpp"

#include <algorithm>
#include <iomanip>
#include <sstream>
#include <stdexcept>

Supi Supi::Parse(const std::string &supi)
{
    if (supi[0] == 'i' && supi[1] == 'm' && supi[2] == 's' && supi[3] == 'i' && supi[4] == '-')
    {
        std::string val = supi.substr(5);
        if (val.size() != 15 && val.size() != 16)
            throw std::runtime_error("invalid IMSI value");
        for (char c : val)
            if (c < '0' || c > '9')
                throw std::runtime_error("invalid IMSI value");
        return Supi{"imsi", val};
    }
    throw std::runtime_error("invalid SUPI value");
}

Json ToJson(const Supi &v)
{
    return v.type + "-" + v.value;
}

Json ToJson(const Plmn &v)
{
    std::stringstream ss{};
    ss << std::setfill('0') << std::setw(3) << v.mcc << "/";
    ss << std::setfill('0') << std::setw(v.isLongMnc ? 3 : 2) << v.mnc;
    return ss.str();
}

Json ToJson(const SingleSlice &v)
{
    return Json::Obj({{"sst", ToJson(v.sst)}, {"sd", ToJson(v.sd)}});
}

Json ToJson(const NetworkSlice &v)
{
    return ToJson(v.slices);
}

Json ToJson(const PlmnSupport &v)
{
    return Json::Obj({{"plmn", ToJson(v.plmn)}, {"nssai", ToJson(v.sliceSupportList)}});
}

Json ToJson(const EDeregCause &v)
{
    switch (v)
    {
    case EDeregCause::UNSPECIFIED:
        return "NORMAL";
    case EDeregCause::SWITCH_OFF:
        return "SWITCH-OFF";
    case EDeregCause::USIM_REMOVAL:
        return "USIM-REMOVAL";
    case EDeregCause::DISABLE_5G:
        return "DISABLE-5G";
    case EDeregCause::ECALL_INACTIVITY:
        return "ECALL-INACTIVITY";
    default:
        return "?";
    }
}

bool operator==(const SingleSlice &lhs, const SingleSlice &rhs)
{
    if ((int)lhs.sst != (int)rhs.sst)
        return false;
    if (lhs.sd.has_value() != rhs.sd.has_value())
        return false;
    if (!lhs.sd.has_value())
        return true;
    return ((int)*lhs.sd) == ((int)*rhs.sd);
}

bool operator==(const Plmn &lhs, const Plmn &rhs)
{
    if (lhs.mcc != rhs.mcc)
        return false;
    if (lhs.mnc != rhs.mnc)
        return false;
    return lhs.isLongMnc == rhs.isLongMnc;
}

bool operator==(const GlobalNci &lhs, const GlobalNci &rhs)
{
    return lhs.plmn == rhs.plmn && lhs.nci == rhs.nci;
}

void NetworkSlice::addIfNotExists(const SingleSlice &slice)
{
    if (!std::any_of(slices.begin(), slices.end(), [&slice](auto &s) { return s == slice; }))
        slices.push_back(slice);
}

std::size_t std::hash<Plmn>::operator()(const Plmn &v) const noexcept
{
    std::size_t h = 0;
    utils::HashCombine(h, v.mcc);
    utils::HashCombine(h, v.mnc);
    utils::HashCombine(h, v.isLongMnc);
    return h;
}

std::size_t std::hash<GlobalNci>::operator()(const GlobalNci &v) const noexcept
{
    std::size_t h = 0;
    utils::HashCombine(h, v.plmn);
    utils::HashCombine(h, v.nci);
    return h;
}
