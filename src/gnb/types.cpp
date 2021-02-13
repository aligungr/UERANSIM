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

std::string nr::gnb::GnbStatusInfo::toString() const
{
    std::stringstream ss{};
    ss << "is-NGAP-up: ";
    ss << std::boolalpha << this->isNgapUp;
    return ss.str();
}

std::string nr::gnb::GnbConfig::toString() const
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
