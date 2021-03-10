//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <nas/nas.hpp>
#include <ue/types.hpp>

#pragma once

namespace nr::ue
{

class MobileStorage
{
  private:
    bool m_simIsValid{};

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
    std::optional<nas::IENetworkName> networkFullName{};
    std::optional<nas::IENetworkName> networkShortName{};
    std::optional<nas::IETimeZone> localTimeZone{};
    std::optional<nas::IETimeZoneAndTime> universalTimeAndLocalTimeZone{};
    std::optional<nas::IEDaylightSavingTime> networkDaylightSavingTime{};

    // eCall related
    bool isECallOnly{};

  public:
    void initialize(bool hasSupi, const UeConfig::Initials &initials)
    {
        m_simIsValid = hasSupi;
        m_defConfiguredNssai = initials.defaultConfiguredNssai;
        m_configuredNssai = initials.configuredNssai;
    }

    void invalidateSim()
    {
        m_simIsValid = false;
    }

    [[nodiscard]] bool isSimValid() const
    {
        return m_simIsValid;
    }
};

} // namespace nr::ue