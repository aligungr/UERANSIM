//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"
#include <lib/nas/utils.hpp>
#include <ue/nas/keys.hpp>

namespace nr::ue
{

static bool IsValidKsi(const nas::IENasKeySetIdentifier &ngKsi)
{
    return ngKsi.tsc == nas::ETypeOfSecurityContext::NATIVE_SECURITY_CONTEXT &&
           ngKsi.ksi != nas::IENasKeySetIdentifier::NOT_AVAILABLE_OR_RESERVED;
}

static int FindSecurityContext(int ksi, const std::unique_ptr<NasSecurityContext> &current,
                               const std::unique_ptr<NasSecurityContext> &nonCurrent)
{
    if (current != nullptr && current->ngKsi == ksi)
        return 0;
    if (nonCurrent != nullptr && nonCurrent->ngKsi == ksi)
        return 1;
    return -1;
}

void NasMm::receiveSecurityModeCommand(const nas::SecurityModeCommand &msg)
{
    m_logger->debug("Security Mode Command received");

    auto reject = [this](nas::EMmCause cause) {
        nas::SecurityModeReject resp;
        resp.mmCause.value = cause;
        sendNasMessage(resp);
        m_logger->err("Rejecting Security Mode Command with cause: %s", nas::utils::EnumToString(cause));
    };

    // ============================== Check the received ngKSI ==============================

    if (!IsValidKsi(msg.ngKsi))
    {
        m_logger->err("Invalid ngKSI received, tsc[%d], ksi[%d]", (int)msg.ngKsi.tsc, msg.ngKsi.ksi);
        reject(nas::EMmCause::SEC_MODE_REJECTED_UNSPECIFIED);
        return;
    }

    if (msg.ngKsi.ksi == 0 &&
        msg.selectedNasSecurityAlgorithms.integrity == nas::ETypeOfIntegrityProtectionAlgorithm::IA0 &&
        msg.selectedNasSecurityAlgorithms.ciphering == nas::ETypeOfCipheringAlgorithm::EA0)
    {
        // TODO
    }

    int whichCtx = FindSecurityContext(msg.ngKsi.ksi, m_usim->m_currentNsCtx, m_usim->m_nonCurrentNsCtx);
    if (whichCtx == -1)
    {
        m_logger->err("Security context with ngKSI[%d] not found", msg.ngKsi.ksi);
        reject(nas::EMmCause::SEC_MODE_REJECTED_UNSPECIFIED);
        return;
    }

    auto &nsCtx = whichCtx == 0 ? m_usim->m_currentNsCtx : m_usim->m_nonCurrentNsCtx;

    // ======================== Check the integrity with new security context ========================

    {
        // TODO:
        octet4 mac = msg._macForNewSC;
        (void)mac;
    }

    // ======================== Check replayed UE security capabilities ========================

    if (!nas::utils::DeepEqualsIe(msg.replayedUeSecurityCapabilities, createSecurityCapabilityIe()))
    {
        m_logger->err("Replayed UE security capability mismatch");
        reject(nas::EMmCause::UE_SECURITY_CAP_MISMATCH);
        return;
    }

    // ======================== Check selected NAS security algorithms ========================

    {
        auto integrity = msg.selectedNasSecurityAlgorithms.integrity;
        auto ciphering = msg.selectedNasSecurityAlgorithms.ciphering;

        if (integrity > nas::ETypeOfIntegrityProtectionAlgorithm::IA3_128 ||
            ciphering > nas::ETypeOfCipheringAlgorithm::EA3_128)
        {
            m_logger->err("Selected NAS security algorithms are invalid");
            reject(nas::EMmCause::UE_SECURITY_CAP_MISMATCH);
            return;
        }
    }

    // ============================ Process the security context. ============================

    // Assign ABBA (if any)
    if (msg.abba.has_value())
        nsCtx->keys.abba = msg.abba->rawData.copy();

    // Assign selected algorithms to security context, and derive NAS keys
    nsCtx->integrity = msg.selectedNasSecurityAlgorithms.integrity;
    nsCtx->ciphering = msg.selectedNasSecurityAlgorithms.ciphering;
    keys::DeriveNasKeys(*nsCtx);

    // m_logger->debug("Derived NAS keys integrity[%s] ciphering[%s]", nsCtx->keys.kNasInt.toHexString().c_str(),
    //                nsCtx->keys.kNasEnc.toHexString().c_str());
    m_logger->debug("Selected integrity[%d] ciphering[%d]", (int)nsCtx->integrity, (int)nsCtx->ciphering);

    // The UE shall in addition reset the uplink NAS COUNT counter if a) the SECURITY MODE COMMAND message is received
    // in order to take a 5G NAS security context into use created after a successful execution of the 5G AKA based
    // primary authentication and key agreement procedure or the EAP based ...
    if (whichCtx == 1) // It is unclear how we can detect this, but checking if it is 'non-current' one.
    {
        nsCtx->uplinkCount.sqn = 0;
        nsCtx->uplinkCount.overflow = octet2{0};
    }

    if (msg.selectedNasSecurityAlgorithms.integrity != nas::ETypeOfIntegrityProtectionAlgorithm::IA0)
    {
        // TODO
    }

    // Set the new NAS Security Context as current one. (If it is not already the current one)
    if (whichCtx == 1)
        m_usim->m_currentNsCtx = std::make_unique<NasSecurityContext>(nsCtx->deepCopy());

    // ============================ Handle EAP-Success message if any. ============================

    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::SUCCESS)
            receiveEapSuccessMessage(*msg.eapMessage->eap);
        else
            m_logger->warn(
                "EAP message with inconvenient code received in Security Mode Command. Ignoring EAP message.");
    }

    // ============================ Send the Security Mode Complete. ============================

    nas::SecurityModeComplete resp{};

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

    // TODO: Bu service request de olabilir en son hangisiyse, ayrıca son mesaj yerine son unciphered mesaj da olabilir
    //  See 4.4.6
    resp.nasMessageContainer = nas::IENasMessageContainer{};
    nas::EncodeNasMessage(*m_lastRegistrationRequest, resp.nasMessageContainer->data);

    // Send response
    sendNasMessage(resp);
}

nas::IEUeSecurityCapability NasMm::createSecurityCapabilityIe()
{
    auto &algs = m_base->config->supportedAlgs;
    auto supported = ~0;

    nas::IEUeSecurityCapability res{};
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