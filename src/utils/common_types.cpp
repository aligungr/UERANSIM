//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "common_types.hpp"
#include <iomanip>
#include <sstream>

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

Json ToJson(const SliceSupport &v)
{
    return Json::Obj({{"sst", ToJson(v.sst)}, {"sd", ToJson(v.sd)}});
}

Json ToJson(const PlmnSupport &v)
{
    return Json::Obj({{"plmn", ToJson(v.plmn)}, {"nssai", ToJson(v.sliceSupportList)}});
}
