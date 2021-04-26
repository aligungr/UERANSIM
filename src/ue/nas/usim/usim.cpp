//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "usim.hpp"

namespace nr::ue
{

void ue::Usim::initialize(bool hasSupi, const UeConfig::Initials &initials)
{
    m_isValid = hasSupi;

    m_uState = E5UState::U1_UPDATED;

    m_defConfiguredNssai = initials.defaultConfiguredNssai;
    m_configuredNssai = initials.configuredNssai;

    m_sqnMng = std::make_unique<SqnManager>(5ull, 1ull << 28ull);
}

bool Usim::isValid()
{
    return m_isValid;
}

void Usim::invalidate()
{
    m_isValid = false;
}

} // namespace nr::ue
