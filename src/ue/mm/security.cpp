//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"
#include <nas/utils.hpp>
#include <ue/nas/keys.hpp>

namespace nr::ue
{

void NasMm::receiveSecurityModeCommand(const nas::SecurityModeCommand &msg)
{
    auto reject = [this](nas::EMmCause cause) {
        nas::SecurityModeReject resp;
        resp.mmCause.value = cause;
        sendNasMessage(resp);
        m_logger->err("Rejecting Security Mode Command with cause: %s", nas::utils::EnumToString(cause));
    };

    if (!m_nonCurrentNsCtx.has_value())
    {
        reject(nas::EMmCause::MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE);
        return;
    }

    // TODO: check the integrity with new security context
    {
        octet4 mac = msg._macForNewSC;
        (void)mac;
    }

    // Check replayed UE security capabilities
    {
        auto &replayed = msg.replayedUeSecurityCapabilities;
        auto real = createSecurityCapabilityIe();

        if (!nas::utils::DeepEqualsIe(replayed, real))
        {
            reject(nas::EMmCause::UE_SECURITY_CAP_MISMATCH);
            return;
        }
    }

    // Handle EAP-Success message if any.
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::SUCCESS)
            receiveEapSuccessMessage(*msg.eapMessage->eap);
        else
            m_logger->warn(
                "EAP message with inconvenient code received in Security Mode Command. Ignoring EAP message.");
    }

    // Assign ABBA (if any)
    if (msg.abba.has_value())
        m_nonCurrentNsCtx->keys.abba = msg.abba->rawData.copy();

    // Check selected algorithms
    {
        // TODO
        // if (msg.selectedNasSecurityAlgorithms.integrity is supported according to config file)
        // if (msg.selectedNasSecurityAlgorithms.ciphering is supported according to config file)
    }

    // Assign selected algorithms to security context, and derive NAS keys
    m_nonCurrentNsCtx->integrity = msg.selectedNasSecurityAlgorithms.integrity;
    m_nonCurrentNsCtx->ciphering = msg.selectedNasSecurityAlgorithms.ciphering;
    keys::DeriveNasKeys(*m_nonCurrentNsCtx);

    m_logger->debug("Derived kNasEnc[%s] kNasInt[%s]", m_nonCurrentNsCtx->keys.kNasEnc.toHexString().c_str(),
                    m_nonCurrentNsCtx->keys.kNasInt.toHexString().c_str());
    m_logger->debug("Selected integrity[%d] ciphering[%d]", (int)m_nonCurrentNsCtx->integrity,
                    (int)m_nonCurrentNsCtx->ciphering);

    // Set non-current NAS Security Context as current one.
    m_currentNsCtx = m_nonCurrentNsCtx->deepCopy();

    // Prepare response
    nas::SecurityModeComplete resp;

    // Append IMEISV if requested
    if (msg.imeiSvRequest.has_value() && msg.imeiSvRequest->imeiSvRequest == nas::EImeiSvRequest::REQUESTED)
    {
        if (m_base->config->imeiSv.has_value())
        {
            resp.imeiSv = nas::IE5gsMobileIdentity{};
            resp.imeiSv->type = nas::EIdentityType::IMEISV;
            resp.imeiSv->value = *m_base->config->imeiSv;
        }
    }

    resp.nasMessageContainer = nas::IENasMessageContainer{};
    nas::EncodeNasMessage(*m_lastRegistrationRequest, resp.nasMessageContainer->data);

    // Send response
    sendNasMessage(resp);
}

nas::IEUeSecurityCapability NasMm::createSecurityCapabilityIe()
{
    auto &algs = m_base->config->supportedAlgs;
    auto supported = ~0;

    nas::IEUeSecurityCapability res;
    res.b_5G_EA0 = supported;
    res.b_5G_IA0 = supported;
    res.b_EEA0 = supported;
    res.b_EIA0 = supported;
    res.b_128_5G_IA1 = algs.nia1;
    res.b_128_EIA1 = algs.nia1;
    res.b_128_5G_EA1 = algs.nea1;
    res.b_128_EEA1 = algs.nea1;
    res.b_128_EIA2 = algs.nia2;
    res.b_128_5G_IA2 = algs.nia2;
    res.b_128_5G_EA2 = algs.nea2;
    res.b_128_EEA2 = algs.nea2;
    res.b_128_5G_IA3 = algs.nia3;
    res.b_128_EIA3 = algs.nia3;
    res.b_128_5G_EA3 = algs.nea3;
    res.b_128_EEA3 = algs.nea3;
    return res;
}

} // namespace nr::ue