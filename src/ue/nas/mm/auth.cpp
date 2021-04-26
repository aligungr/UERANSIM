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

static const bool USE_SQN_HACK = true; // TODO

namespace nr::ue
{

void NasMm::receiveAuthenticationRequest(const nas::AuthenticationRequest &msg)
{
    if (!m_usim->isValid())
    {
        m_logger->warn("Authentication request is ignored. USIM is invalid");
        return;
    }

    if (msg.eapMessage.has_value())
        receiveAuthenticationRequestEap(msg);
    else
        receiveAuthenticationRequest5gAka(msg);
}

void NasMm::receiveAuthenticationRequestEap(const nas::AuthenticationRequest &msg)
{
    // TODO
    m_logger->err("EAP AKA' not implemented yet. Use 5G AKA instead");
    return;

    auto ueRejectionTimers = [this]() {
        m_timers->t3520.start();

        m_timers->t3510.stop();
        m_timers->t3517.stop();
        m_timers->t3521.stop();
    };

    m_timers->t3520.stop();

    // Read EAP-AKA' request
    auto &receivedEap = (const eap::EapAkaPrime &)*msg.eapMessage->eap;
    auto receivedRand = receivedEap.attributes.getRand();
    auto receivedMac = receivedEap.attributes.getMac();
    auto receivedAutn = receivedEap.attributes.getAutn();
    auto receivedKdf = receivedEap.attributes.getKdf();

    // m_logger->debug("received at_rand: %s", receivedRand.toHexString().c_str());
    // m_logger->debug("received at_mac: %s", receivedMac.toHexString().c_str());
    // m_logger->debug("received at_autn: %s", receivedAutn.toHexString().c_str());

    // Derive keys
    if (USE_SQN_HACK)
    {
        // Log.warning(Tag.CONFIG, "USE_SQN_HACK: %s", USE_SQN_HACK);
    }

    if (USE_SQN_HACK)
    {
        auto ak = calculateMilenage(OctetString::FromSpare(6), receivedRand, false).ak;
        m_usim->m_sqn = OctetString::Xor(receivedAutn.subCopy(0, 6), ak);
    }

    auto milenage = calculateMilenage(m_usim->m_sqn, receivedRand, false);
    auto &res = milenage.res;
    auto &ck = milenage.ck;
    auto &ik = milenage.ik;
    auto &milenageAk = milenage.ak;
    auto &milenageMac = milenage.mac_a;

    auto sqnXorAk = OctetString::Xor(m_usim->m_sqn, milenageAk);
    auto ckPrimeIkPrime =
        keys::CalculateCkPrimeIkPrime(ck, ik, keys::ConstructServingNetworkName(*m_usim->m_currentPlmn), sqnXorAk);
    auto &ckPrime = ckPrimeIkPrime.first;
    auto &ikPrime = ckPrimeIkPrime.second;

    if (!m_base->config->supi.has_value())
    {
        m_logger->err("UE has no SUPI, ignoring authentication request");
        return;
    }

    auto mk = keys::CalculateMk(ckPrime, ikPrime, m_base->config->supi.value());
    auto kaut = mk.subCopy(16, 32);

    // m_logger->debug("ueData.sqn: %s", m_usim->m_sqn.toHexString().c_str());
    // m_logger->debug("ueData.op(C): %s", m_base->config->opC.toHexString().c_str());
    // m_logger->debug("ueData.K: %s", m_base->config->key.toHexString().c_str());
    // m_logger->debug("calculated res: %s", res.toHexString().c_str());
    // m_logger->debug("calculated ck: %s", ck.toHexString().c_str());
    // m_logger->debug("calculated ik: %s", ik.toHexString().c_str());
    // m_logger->debug("calculated milenageAk: %s", milenageAk.toHexString().c_str());
    // m_logger->debug("calculated milenageMac: %s", milenageMac.toHexString().c_str());
    // m_logger->debug("calculated ckPrime: %s", ckPrime.toHexString().c_str());
    // m_logger->debug("calculated ikPrime: %s", ikPrime.toHexString().c_str());
    // m_logger->debug("calculated kaut: %s", kaut.toHexString().c_str());

    // Control received KDF
    if (receivedKdf != 1)
    {
        ueRejectionTimers();

        nas::AuthenticationReject resp;
        resp.eapMessage = nas::IEEapMessage{};
        resp.eapMessage->eap = std::make_unique<eap::EapAkaPrime>(eap::ECode::RESPONSE, receivedEap.id,
                                                                  eap::ESubType::AKA_AUTHENTICATION_REJECT);

        sendNasMessage(resp);
        return;
    }

    // Control received SSN
    {
        // todo
    }

    // Control received AUTN
    auto autnCheck = validateAutn(milenageAk, milenageMac, receivedAutn);
    if (autnCheck != EAutnValidationRes::OK)
    {
        eap::EapAkaPrime *eapResponse = nullptr;

        if (autnCheck == EAutnValidationRes::MAC_FAILURE)
        {
            eapResponse =
                new eap::EapAkaPrime(eap::ECode::RESPONSE, receivedEap.id, eap::ESubType::AKA_AUTHENTICATION_REJECT);
        }
        else if (autnCheck == EAutnValidationRes::SYNCHRONISATION_FAILURE)
        {
            // TODO
            // .... eapResponse = new EapAkaPrime(Eap.ECode.RESPONSE, receivedEap.id,
            // ESubType.AKA_SYNCHRONIZATION_FAILURE); eapResponse.attributes.putAuts(...);
            m_logger->err("Feature not implemented yet: SYNCHRONISATION_FAILURE in AUTN validation for EAP AKA'");
        }
        else
        {
            eapResponse = new eap::EapAkaPrime(eap::ECode::RESPONSE, receivedEap.id, eap::ESubType::AKA_CLIENT_ERROR);
            eapResponse->attributes.putClientErrorCode(0);
        }

        if (eapResponse != nullptr)
        {
            ueRejectionTimers();

            nas::AuthenticationReject resp;
            resp.eapMessage = nas::IEEapMessage{};
            resp.eapMessage->eap = std::unique_ptr<eap::Eap>(eapResponse);

            sendNasMessage(resp);
        }
        return;
    }

    // Control received AT_MAC
    auto expectedMac = keys::CalculateMacForEapAkaPrime(kaut, receivedEap);
    if (expectedMac != receivedMac)
    {
        m_logger->err("AT_MAC failure in EAP AKA'. expected: %s received: %s", expectedMac.toHexString().c_str(),
                      receivedMac.toHexString().c_str());

        ueRejectionTimers();

        auto eapResponse =
            std::make_unique<eap::EapAkaPrime>(eap::ECode::RESPONSE, receivedEap.id, eap::ESubType::AKA_CLIENT_ERROR);
        eapResponse->attributes.putClientErrorCode(0);

        nas::AuthenticationReject response;
        response.eapMessage = nas::IEEapMessage{};
        response.eapMessage->eap = std::move(eapResponse);

        sendNasMessage(response);
        return;
    }

    // Create new partial native NAS security context and continue key derivation
    auto kAusf = keys::CalculateKAusfForEapAkaPrime(mk);
    m_logger->debug("kAusf: %s", kAusf.toHexString().c_str());

    m_usim->m_nonCurrentNsCtx = std::make_unique<NasSecurityContext>();
    m_usim->m_nonCurrentNsCtx->tsc = msg.ngKSI.tsc;
    m_usim->m_nonCurrentNsCtx->ngKsi = msg.ngKSI.ksi;
    m_usim->m_rand = std::move(receivedRand);
    m_usim->m_res = std::move(res);
    m_usim->m_resStar = {};
    m_usim->m_nonCurrentNsCtx->keys.kAusf = std::move(kAusf);
    m_usim->m_nonCurrentNsCtx->keys.abba = msg.abba.rawData.copy();

    keys::DeriveKeysSeafAmf(*m_base->config, *m_usim->m_currentPlmn, *m_usim->m_nonCurrentNsCtx);

    // m_logger->debug("kSeaf: %s", m_usim->m_nonCurrentNsCtx->keys.kSeaf.toHexString().c_str());
    // m_logger->debug("kAmf: %s", m_usim->m_nonCurrentNsCtx->keys.kAmf.toHexString().c_str());

    // Send Response
    {
        auto *akaPrimeResponse =
            new eap::EapAkaPrime(eap::ECode::RESPONSE, receivedEap.id, eap::ESubType::AKA_CHALLENGE);
        akaPrimeResponse->attributes.putRes(m_usim->m_res);
        akaPrimeResponse->attributes.putMac(OctetString::FromSpare(16)); // Dummy mac for now
        akaPrimeResponse->attributes.putKdf(1);

        // Calculate and put mac value
        auto sendingMac = keys::CalculateMacForEapAkaPrime(kaut, *akaPrimeResponse);
        akaPrimeResponse->attributes.putMac(sendingMac);

        m_logger->debug("sending eap at_mac: %s", sendingMac.toHexString().c_str());

        nas::AuthenticationResponse response;
        response.eapMessage = nas::IEEapMessage{};
        response.eapMessage->eap = std::unique_ptr<eap::EapAkaPrime>(akaPrimeResponse);

        sendNasMessage(response);
    }
}

void NasMm::receiveAuthenticationRequest5gAka(const nas::AuthenticationRequest &msg)
{
    auto sendFailure = [this](nas::EMmCause cause, std::optional<OctetString> &&auts = std::nullopt) {
        if (cause != nas::EMmCause::SYNCH_FAILURE)
            m_logger->err("Sending Authentication Failure with cause [%s]", nas::utils::EnumToString(cause));

        // Clear parameters stored in volatile memory of ME
        m_usim->m_rand = {};
        m_usim->m_res = {};
        m_usim->m_resStar = {};

        // Stop T3516 if running
        m_timers->t3516.stop();

        // Send Authentication Failure
        nas::AuthenticationFailure resp{};
        resp.mmCause.value = cause;

        if (auts.has_value())
        {
            resp.authenticationFailureParameter = nas::IEAuthenticationFailureParameter{};
            resp.authenticationFailureParameter->rawData = std::move(*auts);
        }

        sendNasMessage(resp);
    };

    // ========================== Check the received parameters syntactically ==========================

    if (!msg.authParamRAND.has_value() || !msg.authParamAUTN.has_value())
    {
        sendFailure(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
        return;
    }

    if (msg.authParamRAND->value.length() != 16 || msg.authParamAUTN->value.length() != 16)
    {
        sendFailure(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
        return;
    }

    // =================================== Check the received ngKSI ===================================

    if (msg.ngKSI.tsc == nas::ETypeOfSecurityContext::MAPPED_SECURITY_CONTEXT)
    {
        m_logger->err("Mapped security context not supported");
        sendFailure(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
        return;
    }

    if (msg.ngKSI.ksi == nas::IENasKeySetIdentifier::NOT_AVAILABLE_OR_RESERVED)
    {
        m_logger->err("Invalid ngKSI value received");
        sendFailure(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
        return;
    }

    if ((m_usim->m_currentNsCtx && m_usim->m_currentNsCtx->ngKsi == msg.ngKSI.ksi) ||
        (m_usim->m_nonCurrentNsCtx && m_usim->m_nonCurrentNsCtx->ngKsi == msg.ngKSI.ksi))
    {
        sendFailure(nas::EMmCause::NGKSI_ALREADY_IN_USE);
        return;
    }

    // ============================================ Others ============================================

    auto &rand = msg.authParamRAND->value;
    auto &autn = msg.authParamAUTN->value;

    auto milenage = calculateMilenage(m_usim->m_sqn, rand, false);
    auto &res = milenage.res;
    auto &ck = milenage.ck;
    auto &ik = milenage.ik;
    auto ckIk = OctetString::Concat(ck, ik);
    auto &milenageAk = milenage.ak;
    auto &milenageMac = milenage.mac_a;
    auto sqnXorAk = OctetString::Xor(m_usim->m_sqn, milenageAk);
    auto snn = keys::ConstructServingNetworkName(*m_usim->m_currentPlmn);

    auto autnCheck = validateAutn(milenageAk, milenageMac, autn);

    if (autnCheck == EAutnValidationRes::OK)
    {
        // Store the relevant parameters
        m_usim->m_rand = rand.copy();
        m_usim->m_resStar = keys::CalculateResStar(ckIk, snn, rand, res);
        m_usim->m_res = std::move(res);

        // Create new partial native NAS security context and continue with key derivation
        m_usim->m_nonCurrentNsCtx = std::make_unique<NasSecurityContext>();
        m_usim->m_nonCurrentNsCtx->tsc = msg.ngKSI.tsc;
        m_usim->m_nonCurrentNsCtx->ngKsi = msg.ngKSI.ksi;
        m_usim->m_nonCurrentNsCtx->keys.kAusf = keys::CalculateKAusfFor5gAka(ck, ik, snn, sqnXorAk);
        m_usim->m_nonCurrentNsCtx->keys.abba = msg.abba.rawData.copy();

        keys::DeriveKeysSeafAmf(*m_base->config, *m_usim->m_currentPlmn, *m_usim->m_nonCurrentNsCtx);

        // Send response
        nas::AuthenticationResponse resp;
        resp.authenticationResponseParameter = nas::IEAuthenticationResponseParameter{};
        resp.authenticationResponseParameter->rawData = m_usim->m_resStar.copy();
        sendNasMessage(resp);
    }
    else if (autnCheck == EAutnValidationRes::MAC_FAILURE)
    {
        sendFailure(nas::EMmCause::MAC_FAILURE);
    }
    else if (autnCheck == EAutnValidationRes::SYNCHRONISATION_FAILURE)
    {
        auto milenageForSync = calculateMilenage(m_usim->m_sqn, rand, true);
        auto auts = keys::CalculateAuts(m_usim->m_sqn, milenageForSync.ak_r, milenageForSync.mac_s);
        sendFailure(nas::EMmCause::SYNCH_FAILURE, std::move(auts));
    }
    else
    {
        // the other case, separation bit mismatched
        sendFailure(nas::EMmCause::NON_5G_AUTHENTICATION_UNACCEPTABLE);
    }
}

void NasMm::receiveAuthenticationResult(const nas::AuthenticationResult &msg)
{
    if (msg.abba.has_value())
        m_usim->m_nonCurrentNsCtx->keys.abba = msg.abba->rawData.copy();

    if (msg.eapMessage.eap->code == eap::ECode::SUCCESS)
        receiveEapSuccessMessage(*msg.eapMessage.eap);
    else if (msg.eapMessage.eap->code == eap::ECode::FAILURE)
        receiveEapFailureMessage(*msg.eapMessage.eap);
    else
        m_logger->warn("Network sent EAP with an inconvenient type in Authentication Result, ignoring EAP IE.");
}

void NasMm::receiveAuthenticationResponse(const nas::AuthenticationResponse &msg)
{
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::RESPONSE)
            receiveEapResponseMessage(*msg.eapMessage->eap);
        else
            m_logger->warn("Network sent EAP with an inconvenient type in Authentication Response, ignoring EAP IE.");
    }
}

void NasMm::receiveAuthenticationReject(const nas::AuthenticationReject &msg)
{
    m_logger->err("Authentication Reject received.");

    if (msg.eapMessage.has_value() && msg.eapMessage->eap->code != eap::ECode::FAILURE)
    {
        m_logger->warn("Network sent EAP with inconvenient type in AuthenticationReject, ignoring EAP IE.");
        return;
    }

    // The UE shall set the update status to 5U3 ROAMING NOT ALLOWED,
    switchUState(E5UState::U3_ROAMING_NOT_ALLOWED);
    // Delete the stored 5G-GUTI, TAI list, last visited registered TAI and ngKSI. The USIM shall be considered invalid
    // until switching off the UE or the UICC containing the USIM is removed
    m_usim->m_storedGuti = {};
    m_usim->m_lastVisitedRegisteredTai = {};
    m_usim->m_taiList = {};
    m_usim->m_currentNsCtx = {};
    m_usim->m_nonCurrentNsCtx = {};
    m_usim->invalidate();
    // The UE shall abort any 5GMM signalling procedure, stop any of the timers T3510, T3516, T3517, T3519 or T3521 (if
    // they were running) ..
    m_timers->t3510.stop();
    m_timers->t3516.stop();
    m_timers->t3517.stop();
    m_timers->t3519.stop();
    m_timers->t3521.stop();
    // .. and enter state 5GMM-DEREGISTERED.
    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
}

void NasMm::receiveEapSuccessMessage(const eap::Eap &eap)
{
    // do nothing
}

void NasMm::receiveEapFailureMessage(const eap::Eap &eap)
{
    m_logger->err("EAP failure received. Deleting non-current NAS security context");
    m_usim->m_nonCurrentNsCtx = {};
}

void NasMm::receiveEapResponseMessage(const eap::Eap &eap)
{
    if (eap.eapType == eap::EEapType::EAP_AKA_PRIME)
    {
        // TODO
    }
    else
    {
        m_logger->err("Unhandled EAP Response message type");
    }
}

EAutnValidationRes NasMm::validateAutn(const OctetString &ak, const OctetString &mac, const OctetString &autn)
{
    // Decode AUTN
    OctetString receivedSQNxorAK = autn.subCopy(0, 6);
    OctetString receivedSQN = OctetString::Xor(receivedSQNxorAK, ak);
    OctetString receivedAMF = autn.subCopy(6, 2);
    OctetString receivedMAC = autn.subCopy(8, 8);

    // TS 33.501: An ME accessing 5G shall check during authentication that the "separation bit" in the AMF field
    // of AUTN is set to 1. The "separation bit" is bit 0 of the AMF field of AUTN.
    if (receivedAMF.get(0).bit(7) != 1)
    {
        m_logger->err("AUTN validation SEP-BIT failure. expected: 1, received: 0");
        return EAutnValidationRes::AMF_SEPARATION_BIT_FAILURE;
    }

    // Verify that the received sequence number SQN is in the correct range
    if (!m_usim->checkSqn(receivedSQN))
        return EAutnValidationRes::SYNCHRONISATION_FAILURE;

    // Check MAC
    if (receivedMAC != mac)
    {
        m_logger->err("AUTN validation MAC mismatch. expected [%s] received [%s]", mac.toHexString().c_str(),
                      receivedMAC.toHexString().c_str());
        return EAutnValidationRes::MAC_FAILURE;
    }

    return EAutnValidationRes::OK;
}

crypto::milenage::Milenage NasMm::calculateMilenage(const OctetString &sqn, const OctetString &rand, bool dummyAmf)
{
    OctetString amf = dummyAmf ? OctetString::FromSpare(2) : m_base->config->amf.copy();

    if (m_base->config->opType == OpType::OPC)
        return crypto::milenage::Calculate(m_base->config->opC, m_base->config->key, rand, sqn, amf);

    OctetString opc = crypto::milenage::CalculateOpC(m_base->config->opC, m_base->config->key);
    return crypto::milenage::Calculate(opc, m_base->config->key, rand, sqn, amf);
}

} // namespace nr::ue