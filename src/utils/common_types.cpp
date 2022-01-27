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
        if (val.size() < 4u || val.size() > 15u)
            throw std::runtime_error("invalid IMSI value");
        for (char c : val)
            if (c < '0' || c > '9')
                throw std::runtime_error("invalid IMSI value");
        return Supi{"imsi", val};
    }
    throw std::runtime_error("invalid SUPI value");
}

int64_t GutiMobileIdentity::toTmsiValue() const
{
    return (static_cast<int64_t>(this->tmsi)) | (static_cast<int64_t>(this->amfPointer) << 32LL) |
           (static_cast<int64_t>(this->amfSetId) << 38LL);
}

GutiMobileIdentity GutiMobileIdentity::FromSTmsi(int64_t sTmsi)
{
    GutiMobileIdentity res;
    res.tmsi = octet4{static_cast<uint32_t>(sTmsi & 0xFFFFFFFFLL)};
    res.amfPointer = static_cast<int>(((sTmsi >> 32LL) & 0b111111LL));
    res.amfSetId = static_cast<int>(((sTmsi >> 38LL) & 0b1111111111LL));
    return res;
}

Json ToJson(const Supi &v)
{
    return v.type + "-" + v.value;
}

Json ToJson(const Plmn &v)
{
    if (!v.hasValue())
        return nullptr;

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

Json ToJson(const ECellCategory &v)
{
    switch (v)
    {
    case ECellCategory::BARRED_CELL:
        return "BARRED";
    case ECellCategory::RESERVED_CELL:
        return "RESERVED";
    case ECellCategory::ACCEPTABLE_CELL:
        return "ACCEPTABLE";
    case ECellCategory::SUITABLE_CELL:
        return "SUITABLE";
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

bool operator!=(const SingleSlice &lhs, const SingleSlice &rhs)
{
    return !(lhs == rhs);
}

bool operator==(const Plmn &lhs, const Plmn &rhs)
{
    if (lhs.mcc != rhs.mcc)
        return false;
    if (lhs.mnc != rhs.mnc)
        return false;
    return lhs.isLongMnc == rhs.isLongMnc;
}

bool operator!=(const Plmn &lhs, const Plmn &rhs)
{
    return !(lhs == rhs);
}

bool operator==(const GlobalNci &lhs, const GlobalNci &rhs)
{
    return lhs.plmn == rhs.plmn && lhs.nci == rhs.nci;
}

bool operator!=(const GlobalNci &lhs, const GlobalNci &rhs)
{
    return !(lhs == rhs);
}

bool operator==(const Tai &lhs, const Tai &rhs)
{
    return lhs.plmn == rhs.plmn && lhs.tac == rhs.tac;
}

bool operator!=(const Tai &lhs, const Tai &rhs)
{
    return !(lhs == rhs);
}

Json ToJson(const EDeregCause &v)
{
    switch (v)
    {
    case EDeregCause::NORMAL:
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

Json ToJson(const EInitialRegCause &v)
{
    switch (v)
    {
    case EInitialRegCause::EMERGENCY_SERVICES:
        return "EMERGENCY-SERVICES";
    case EInitialRegCause::MM_DEREG_NORMAL_SERVICE:
        return "MM-DEREG-NORMAL-SERVICE";
    case EInitialRegCause::T3346_EXPIRY:
        return "T3346-EXPIRY";
    case EInitialRegCause::DUE_TO_DEREGISTRATION:
        return "DUE-TO-DEREGISTRATION";
    case EInitialRegCause::DUE_TO_SERVICE_REJECT:
        return "DUE-TO-SERVICE_REJECT";
    case EInitialRegCause::TAI_CHANGE_IN_ATT_REG:
        return "TAI-CHANGE-IN-ATT-REG";
    case EInitialRegCause::PLMN_CHANGE_IN_ATT_REG:
        return "PLMN-CHANGE-IN-ATT-REG";
    case EInitialRegCause::T3346_EXPIRY_IN_ATT_REG:
        return "T3346-EXPIRY-IN-ATT-REG";
    case EInitialRegCause::T3502_EXPIRY_IN_ATT_REG:
        return "T3502-EXPIRY-IN-ATT-REG";
    case EInitialRegCause::T3511_EXPIRY_IN_ATT_REG:
        return "T3511-EXPIRY-IN-ATT-REG";
    default:
        return "?";
    }
}

Json ToJson(const Tai &v)
{
    if (!v.hasValue())
        return nullptr;
    return "PLMN[" + ToJson(v.plmn).str() + "] TAC[" + std::to_string(v.tac) + "]";
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

std::size_t std::hash<Tai>::operator()(const Tai &v) const noexcept
{
    std::size_t h = 0;
    utils::HashCombine(h, v.plmn);
    utils::HashCombine(h, v.tac);
    return h;
}

bool Plmn::hasValue() const
{
    return this->mcc != 0;
}

Tai::Tai() : plmn{}, tac{}
{
}

Tai::Tai(const Plmn &plmn, int tac) : plmn{plmn}, tac{tac}
{
}

Tai::Tai(int mcc, int mnc, bool longMnc, int tac) : plmn{mcc, mnc, longMnc}, tac{tac}
{
}

bool Tai::hasValue() const
{
    return plmn.hasValue();
}
