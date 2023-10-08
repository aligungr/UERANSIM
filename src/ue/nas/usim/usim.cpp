//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "usim.hpp"

namespace nr::ue
{

void ue::Usim::initialize(bool hasSupi)
{
    m_isValid = hasSupi;

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
