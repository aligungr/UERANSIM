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

std::string GnbStatusInfo::toString() const
{
    Printer printer{};
    printer.appendKeyValue({{"is-ngap-up", this->isNgapUp ? "true" : "false"}});
    printer.trim();
    return printer.makeString();
}

std::string GnbConfig::toString() const
{
    Printer printer{};
    printer.appendKeyValue({
        {"nci", std::to_string(this->nci)},
        {"gnb-id", std::to_string(this->getGnbId())},
        {"cell-id", std::to_string(this->getCellId())},
        {"mcc", std::to_string(this->plmn.mcc)},
        {"mnc", std::to_string(this->plmn.mnc)},
        {"tac", std::to_string(this->tac)},
        {"name", this->name},
    });
    printer.trim();
    return printer.makeString();
}

const char *EnumToString(EAmfState v)
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
        return "<?>";
    }
}

} // namespace nr::gnb
