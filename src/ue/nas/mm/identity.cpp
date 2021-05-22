//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"

#include <utils/common.hpp>

namespace nr::ue
{

void NasMm::receiveIdentityRequest(const nas::IdentityRequest &msg)
{
    nas::IdentityResponse resp;

    if (msg.identityType.value == nas::EIdentityType::SUCI)
    {
        resp.mobileIdentity = getOrGenerateSuci();
    }
    else if (msg.identityType.value == nas::EIdentityType::IMEI)
    {
        resp.mobileIdentity.type = nas::EIdentityType::IMEI;
        resp.mobileIdentity.value = *m_base->config->imei;
    }
    else if (msg.identityType.value == nas::EIdentityType::IMEISV)
    {
        resp.mobileIdentity.type = nas::EIdentityType::IMEISV;
        resp.mobileIdentity.value = *m_base->config->imeiSv;
    }
    else if (msg.identityType.value == nas::EIdentityType::GUTI)
    {
        resp.mobileIdentity = m_storage->storedGuti->get();
    }
    else if (msg.identityType.value == nas::EIdentityType::TMSI)
    {
        // TMSI is already a part of GUTI
        resp.mobileIdentity = m_storage->storedGuti->get();
        if (resp.mobileIdentity.type != nas::EIdentityType::NO_IDENTITY)
        {
            resp.mobileIdentity.type = nas::EIdentityType::TMSI;
            resp.mobileIdentity.gutiOrTmsi.plmn = {};
            resp.mobileIdentity.gutiOrTmsi.amfRegionId = {};
        }
    }
    else
    {
        resp.mobileIdentity.type = nas::EIdentityType::NO_IDENTITY;
        m_logger->err("Requested identity is not available: %d", (int)msg.identityType.value);
    }

    sendNasMessage(resp);
}

nas::IE5gsMobileIdentity NasMm::getOrGenerateSuci()
{
    if (m_timers->t3519.isRunning() && m_storage->storedSuci->get().type != nas::EIdentityType::NO_IDENTITY)
        return m_storage->storedSuci->get();

    m_storage->storedSuci->set(generateSuci());

    m_timers->t3519.start();

    if (m_storage->storedSuci->get().type == nas::EIdentityType::NO_IDENTITY)
        return {};
    return m_storage->storedSuci->get();
}

nas::IE5gsMobileIdentity NasMm::generateSuci()
{
    auto &supi = m_base->config->supi;
    auto &plmn = m_base->config->hplmn;

    if (!supi.has_value())
        return {};

    if (supi->type != "imsi")
    {
        m_logger->err("SUCI generating failed, invalid SUPI type: %s", supi->value.c_str());
        return {};
    }

    const std::string &imsi = supi->value;

    nas::IE5gsMobileIdentity ret;
    ret.type = nas::EIdentityType::SUCI;
    ret.supiFormat = nas::ESupiFormat::IMSI;
    ret.imsi.plmn.isLongMnc = plmn.isLongMnc;
    ret.imsi.plmn.mcc = plmn.mcc;
    ret.imsi.plmn.mnc = plmn.mnc;
    ret.imsi.routingIndicator = "0000";
    ret.imsi.protectionSchemaId = 0;
    ret.imsi.homeNetworkPublicKeyIdentifier = 0;
    ret.imsi.schemeOutput = imsi.substr(plmn.isLongMnc ? 6 : 5);
    return ret;
}

nas::IE5gsMobileIdentity NasMm::getOrGeneratePreferredId()
{
    if (m_storage->storedGuti->get().type != nas::EIdentityType::NO_IDENTITY)
        return m_storage->storedGuti->get();

    auto suci = getOrGenerateSuci();
    if (suci.type != nas::EIdentityType::NO_IDENTITY)
    {
        return suci;
    }
    else if (m_base->config->imei.has_value())
    {
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::IMEI;
        res.value = *m_base->config->imei;
        return res;
    }
    else if (m_base->config->imeiSv.has_value())
    {
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::IMEISV;
        res.value = *m_base->config->imeiSv;
        return res;
    }
    else
    {
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::NO_IDENTITY;
        return res;
    }
}

} // namespace nr::ue
