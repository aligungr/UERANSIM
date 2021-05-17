//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "storage.hpp"

static constexpr const size_t FORBIDDEN_TAI_LIST_SIZE = 40;
static constexpr const int64_t FORBIDDEN_TAI_CLEAR_PERIOD = 1000ll * 60ll * 60ll * 12ll;

namespace nr::ue
{

MmStorage::MmStorage()
{
    m_forbiddenTaiListRoaming =
        std::make_unique<nas::NasListT1<Tai>>(FORBIDDEN_TAI_LIST_SIZE, FORBIDDEN_TAI_CLEAR_PERIOD, std::nullopt);
    m_forbiddenTaiListRps =
        std::make_unique<nas::NasListT1<Tai>>(FORBIDDEN_TAI_LIST_SIZE, FORBIDDEN_TAI_CLEAR_PERIOD, std::nullopt);
}

} // namespace nr::ue