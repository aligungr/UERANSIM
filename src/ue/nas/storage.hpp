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
    nas::IE5gsTrackingAreaIdentityList m_taiList{};
    nas::IE5gsTrackingAreaIdentityList m_forbiddenTaiList{};
    nas::IEPlmnList m_equivalentPlmnList{};
    nas::IEPlmnList m_forbiddenPlmnList{};

    // Security related
    std::unique_ptr<NasSecurityContext> m_currentNsCtx{};
    std::unique_ptr<NasSecurityContext> m_nonCurrentNsCtx{};
    OctetString m_sqn{};

  public:
    void initialize(bool hasSupi)
    {
        m_simIsValid = hasSupi;
    }

    void discardLocation()
    {
        m_storedGuti = {};
        m_lastVisitedRegisteredTai = {};
    }

    void discardPlmn()
    {
        m_taiList = {};
    }

    void discardSecurity()
    {
        m_currentNsCtx = {};
        m_nonCurrentNsCtx = {};
    }

    void discardCurrentSecurity()
    {
        m_currentNsCtx = {};
        // normally NON-current nsCtx is not stored in USIM
    }

    void discardUsim()
    {
        discardLocation();
        discardPlmn();
        discardSecurity();
    }

    // todo metodları kaldır geri
    void invalidateSim__()
    {
        // TODO: log
        m_simIsValid = false;
    }

    [[nodiscard]] bool isSimValid() const
    {
        return m_simIsValid;
    }
};

} // namespace nr::ue