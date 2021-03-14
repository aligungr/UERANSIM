//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <memory>
#include <nas/msg.hpp>
#include <optional>
#include <ue/types.hpp>
#include <utils/common_types.hpp>
#include <utils/octet_string.hpp>

namespace nr::ue
{

class Usim
{
  private:
    bool m_isValid{};

  public:
    // Location related
    nas::IE5gsMobileIdentity m_storedGuti{};
    std::optional<nas::IE5gsTrackingAreaIdentity> m_lastVisitedRegisteredTai{};
    E5UState m_uState{};

    // Identity related
    nas::IE5gsMobileIdentity m_storedSuci{};

    // Plmn related
    Plmn m_currentPlmn{};
    nas::IE5gsTrackingAreaIdentityList m_taiList{};
    nas::IE5gsTrackingAreaIdentityList m_forbiddenTaiList{};
    nas::IEPlmnList m_equivalentPlmnList{};
    nas::IEPlmnList m_forbiddenPlmnList{};
    nas::IEServiceAreaList m_serviceAreaList{};

    // Security related
    std::unique_ptr<NasSecurityContext> m_currentNsCtx{};
    std::unique_ptr<NasSecurityContext> m_nonCurrentNsCtx{};
    OctetString m_sqn{};

    // NSSAI related
    NetworkSlice m_defConfiguredNssai{};
    NetworkSlice m_configuredNssai{};
    NetworkSlice m_allowedNssai{};
    NetworkSlice m_rejectedNssaiInPlmn{};
    NetworkSlice m_rejectedNssaiInTa{};

    // NITZ related
    std::optional<nas::IENetworkName> m_networkFullName{};
    std::optional<nas::IENetworkName> m_networkShortName{};
    std::optional<nas::IETimeZone> m_localTimeZone{};
    std::optional<nas::IETimeZoneAndTime> m_universalTimeAndLocalTimeZone{};
    std::optional<nas::IEDaylightSavingTime> m_networkDaylightSavingTime{};

    // eCall related
    bool m_isECallOnly{};

  public:
    void initialize(bool hasSupi, const UeConfig::Initials &initials);
    bool isValid();
    void invalidate();
};

} // namespace nr::ue