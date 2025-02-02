//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "mm.hpp"
#include <lib/nas/utils.hpp>
#include <ue/nas/enc.hpp>
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

static std::unique_ptr<NasSecurityContext> LocallyDeriveNsc()
{
    auto nsc = std::make_unique<NasSecurityContext>();
    nsc->tsc = nas::ETypeOfSecurityContext::NATIVE_SECURITY_CONTEXT;
    nsc->ngKsi = 0;
    nsc->downlinkCount = {};
    nsc->uplinkCount = {};
    nsc->integrity = nas::ETypeOfIntegrityProtectionAlgorithm::IA0;
    nsc->ciphering = nas::ETypeOfCipheringAlgorithm::EA0;
    nsc->keys.abba = OctetString::FromSpare(2);
    nsc->keys.kAusf = OctetString::FromSpare(32);
    nsc->keys.kSeaf = OctetString::FromSpare(32);
    nsc->keys.kAmf = OctetString::FromSpare(32);
    nsc->keys.kNasInt = OctetString::FromSpare(16);
    nsc->keys.kNasEnc = OctetString::FromSpare(16);
    return nsc;
}

void NasMm::receiveSecurityModeCommand(const nas::SecurityModeCommand &msg)
{
    m_logger->debug("Security Mode Command received");

    auto reject = [this](nas::EMmCause cause) {
        nas::SecurityModeReject resp;
        resp.mmCause.value = cause;
        sendNasMessage(resp);
        m_logger->err("Rejecting Security Mode Command with cause [%s]", nas::utils::EnumToString(cause));
    };

    // The RAND and RES* values stored in the ME shall be deleted and timer T3516, if running, shall be stopped
    m_usim->m_rand = {};
    m_usim->m_resStar = {};
    m_timers->t3516.stop();

    // ============================== Check the received ngKSI ==============================

    bool locallyDerived = false;

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
        if (hasEmergency())
        {
            m_logger->debug("Locally deriving a current NAS security context");
            m_usim->m_currentNsCtx = LocallyDeriveNsc();
            locallyDerived = true;
        }
        else
        {
            m_logger->err("[IA0, EA0] cannot be accepted as the UE does not have an emergency");
            reject(nas::EMmCause::SEC_MODE_REJECTED_UNSPECIFIED);
            return;
        }
    }

    int whichCtx = FindSecurityContext(msg.ngKsi.ksi, m_usim->m_currentNsCtx, m_usim->m_nonCurrentNsCtx);
    if (whichCtx == -1)
    {
        m_logger->err("Security context with ngKSI[%d] not found", msg.ngKsi.ksi);
        reject(nas::EMmCause::SEC_MODE_REJECTED_UNSPECIFIED);
        return;
    }

    auto &nsCtx = whichCtx == 0 ? m_usim->m_currentNsCtx : m_usim->m_nonCurrentNsCtx;

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

        if (integrity == nas::ETypeOfIntegrityProtectionAlgorithm::IA0 && !(hasEmergency() || locallyDerived))
        {
            m_logger->err("[IA0] cannot be accepted as the UE does not have an emergency");
            reject(nas::EMmCause::SEC_MODE_REJECTED_UNSPECIFIED);
            return;
        }
    }

    // ======================== Check the integrity with new security context ========================

    bool clearNasCount = false;
    bool horizontalDeriveNeeded =
        msg.additional5gSecurityInformation.has_value() &&
        msg.additional5gSecurityInformation->hdp == nas::EHorizontalDerivationParameter::REQUIRED;

    if (msg.selectedNasSecurityAlgorithms.integrity != nas::ETypeOfIntegrityProtectionAlgorithm::IA0)
    {
        NasSecurityContext tmpCtx = nsCtx->deepCopy();

        tmpCtx.integrity = msg.selectedNasSecurityAlgorithms.integrity;
        tmpCtx.ciphering = msg.selectedNasSecurityAlgorithms.ciphering;

        // Before deriving the keys for temporary NAS security context, concern the horizontal derivation case
        //  Because 33.501/6.9.3 says integrity check should be performed with the new key
        if (horizontalDeriveNeeded)
            tmpCtx.keys.kAmf = keys::DeriveAmfPrimeInMobility(true, tmpCtx.uplinkCount, tmpCtx.keys.kAmf);

        keys::DeriveNasKeys(tmpCtx);

        uint32_t calculatedMac = nas_enc::ComputeMac(tmpCtx.integrity, tmpCtx.downlinkCount, tmpCtx.is3gppAccess, false,
                                                     tmpCtx.keys.kNasInt, msg._originalPlainNasPdu);

        // First check with the last estimated NAS COUNT
        if (calculatedMac != static_cast<uint32_t>(msg._macForNewSC))
        {
            // Integrity check failed with the last NAS COUNT
            // Now check with the NAS COUNT=0

            tmpCtx.downlinkCount = {}; // assign NAS COUNT=0

            calculatedMac = nas_enc::ComputeMac(tmpCtx.integrity, tmpCtx.downlinkCount, tmpCtx.is3gppAccess, false,
                                                tmpCtx.keys.kNasInt, msg._originalPlainNasPdu);

            if (calculatedMac != static_cast<uint32_t>(msg._macForNewSC))
            {
                // If it still mismatched, reject the security mode command
                m_logger->err("Security Mode Command integrity check failed");
                reject(nas::EMmCause::SEC_MODE_REJECTED_UNSPECIFIED);
                return;
            }
            else
            {
                // Otherwise since the integrity passed with NAS COUNT=0, we should clear the NAS COUNT
                // as specified in 5.4.2.3
                m_logger->debug("Setting downlink NAS COUNT to 0");
                clearNasCount = true;
            }
        }
    }

    // ============================ Process the security context ============================

    // Clear the NAS count if necessary
    if (clearNasCount)
        nsCtx->downlinkCount = {};

    // Assign ABBA (if any)
    if (msg.abba.has_value())
        nsCtx->keys.abba = msg.abba->rawData.copy();

    // Handle horizontal derivation
    if (horizontalDeriveNeeded)
    {
        m_logger->debug("Performing kAMF' derivation from kAMF in mobility");
        nsCtx->keys.kAmf = keys::DeriveAmfPrimeInMobility(true, nsCtx->uplinkCount, nsCtx->keys.kAmf);
        nsCtx->uplinkCount = {};
        nsCtx->downlinkCount = {};
    }

    // Assign selected algorithms to security context, and derive NAS keys
    nsCtx->integrity = msg.selectedNasSecurityAlgorithms.integrity;
    nsCtx->ciphering = msg.selectedNasSecurityAlgorithms.ciphering;
    keys::DeriveNasKeys(*nsCtx);

    m_logger->debug("Selected integrity[%d] ciphering[%d]", (int)nsCtx->integrity, (int)nsCtx->ciphering);

    // The UE shall in addition reset the uplink NAS COUNT counter if a) the SECURITY MODE COMMAND message is received
    // in order to take a 5G NAS security context into use created after a successful execution of the 5G AKA based
    // primary authentication and key agreement procedure or the EAP based ...
    if (whichCtx == 1) // NOTE: It is unclear how we can detect this, but checking if it is 'non-current' one.
    {
        nsCtx->uplinkCount.sqn = 0;
        nsCtx->uplinkCount.overflow = octet2{0};
    }

    // Set the new NAS Security Context as current one. (If it is not already the current one)
    if (whichCtx == 1)
        m_usim->m_currentNsCtx = std::make_unique<NasSecurityContext>(nsCtx->deepCopy());

    // ============================ Handle EAP-Success message if any. ============================

    if (msg.eapMessage.has_value() && msg.eapMessage->eap)
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

    // Handle NAS message container
    if (msg.additional5gSecurityInformation.has_value() &&
        msg.additional5gSecurityInformation->rinmr == nas::ERetransmissionOfInitialNasMessageRequest::REQUESTED)
    {
        resp.nasMessageContainer = nas::IENasMessageContainer{};
        if (m_mmState == EMmState::MM_REGISTERED_INITIATED && m_lastRegistrationRequest)
            nas::EncodeNasMessage(*m_lastRegistrationRequest, resp.nasMessageContainer->data);
        else if (m_mmState == EMmState::MM_SERVICE_REQUEST_INITIATED && m_lastServiceRequest)
            nas::EncodeNasMessage(*m_lastServiceRequest, resp.nasMessageContainer->data);
    }
    if (m_lastRegWithoutNsc && m_lastRegistrationRequest)
    {
        resp.nasMessageContainer = nas::IENasMessageContainer{};
        nas::EncodeNasMessage(*m_lastRegistrationRequest, resp.nasMessageContainer->data);
    }

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