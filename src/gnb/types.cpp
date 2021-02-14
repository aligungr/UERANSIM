//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <gnb/types.hpp>
#include <iomanip>
#include <sstream>
#include <utils/printer.hpp>

namespace nr::gnb
{

Json ToJson(const GnbStatusInfo &v)
{
    return Json::Obj({{"is-ngap-up", v.isNgapUp}});
}

Json ToJson(const GnbConfig &v)
{
    return Json::Obj({
        {"nci", v.nci},
        {"plmn", ToJson(v.plmn)},
        {"tac", v.tac},
        {"name", v.name},
    });
}

Json ToJson(const NgapAmfContext &v)
{
    return Json::Obj({
        {"name", v.amfName},
        {"capacity", v.relativeCapacity},
        {"state", ToJson(v.state).str()},
        {"address", v.address + ":" + std::to_string(v.port)},
    });
}

Json ToJson(const EAmfState &v)
{
    switch (v)
    {
    case EAmfState::NOT_CONNECTED:
        return "NOT_CONNECTED";
    case EAmfState::WAITING_NG_SETUP:
        return "WAITING_NG_SETUP";
    case EAmfState::CONNECTED:
        return "CONNECTED";
    default:
        return "?";
    }
}

} // namespace nr::gnb
