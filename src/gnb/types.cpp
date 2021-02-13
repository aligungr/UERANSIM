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

namespace nr::gnb
{

std::string GnbStatusInfo::toString() const
{
    std::stringstream ss{};
    ss << "is-NGAP-up: ";
    ss << std::boolalpha << this->isNgapUp;
    return ss.str();
}

std::string GnbConfig::toString() const
{
    std::stringstream ss{};
    ss << "nci: " << this->nci << "\n";
    ss << "gnb-id: " << this->getGnbId() << "\n";
    ss << "cell-id: " << this->getGnbId() << "\n";
    ss << "mcc: " << this->plmn.mcc << "\n";
    ss << "mnc: " << this->plmn.mnc << "\n";
    ss << "tac: " << this->tac << "\n";
    ss << "name: " << this->name;
    return ss.str();
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
