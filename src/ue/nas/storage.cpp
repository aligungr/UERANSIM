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

static void BackupTaiListInSharedCtx(const std::vector<Tai> &buffer, size_t count, Locked<std::vector<Tai>> &target)
{
    target.mutate([count, &buffer](auto &value) {
        value.clear();
        for (size_t i = 0; i < count; i++)
            value.push_back(buffer[i]);
    });
}

namespace nr::ue
{

MmStorage::MmStorage(TaskBase *base) : m_base{base}
{
    storedSuci = std::make_unique<nas::NasSlot<nas::IE5gsMobileIdentity>>(0, std::nullopt);

    storedGuti = std::make_unique<nas::NasSlot<nas::IE5gsMobileIdentity>>(0, std::nullopt);

    taiList = std::make_unique<nas::NasSlot<nas::IE5gsTrackingAreaIdentityList>>(0, std::nullopt);

    lastVisitedRegisteredTai = std::make_unique<nas::NasSlot<Tai>>(0, std::nullopt);

    forbiddenTaiListRoaming = std::make_unique<nas::NasList<Tai>>(
        FORBIDDEN_TAI_LIST_SIZE, FORBIDDEN_TAI_CLEAR_PERIOD, [this](const std::vector<Tai> &buffer, size_t count) {
            BackupTaiListInSharedCtx(buffer, count, m_base->shCtx.forbiddenTaiRoaming);
        });

    forbiddenTaiListRps = std::make_unique<nas::NasList<Tai>>(
        FORBIDDEN_TAI_LIST_SIZE, FORBIDDEN_TAI_CLEAR_PERIOD, [this](const std::vector<Tai> &buffer, size_t count) {
            BackupTaiListInSharedCtx(buffer, count, m_base->shCtx.forbiddenTaiRps);
        });

    serviceAreaList = std::make_unique<nas::NasSlot<nas::IEServiceAreaList>>(0, std::nullopt);
}

} // namespace nr::ue