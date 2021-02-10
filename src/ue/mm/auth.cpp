//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "mm.hpp"
#include <ue/nas/keys.hpp>

static const bool IGNORE_CONTROLS_FAILURES = false;
static const bool USE_SQN_HACK = true; // TODO

namespace nr::ue
{

void NasMm::receiveAuthenticationRequest(const nas::AuthenticationRequest &msg)
{
    if (msg.eapMessage.has_value())
        receiveAuthenticationRequestEap(msg);
    else
        receiveAuthenticationRequest5gAka(msg);
}

void NasMm::receiveAuthenticationRequestEap(const nas::AuthenticationRequest &msg)
{
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

    m_logger->debug("received at_rand: %s", receivedRand.toHexString().c_str());
    m_logger->debug("received at_mac: %s", receivedMac.toHexString().c_str());
    m_logger->debug("received at_autn: %s", receivedAutn.toHexString().c_str());

    // Derive keys
    if (USE_SQN_HACK)
    {
        // Log.warning(Tag.CONFIG, "USE_SQN_HACK: %s", USE_SQN_HACK);
    }

    if (IGNORE_CONTROLS_FAILURES)
        m_logger->warn("IGNORE_CONTROLS_FAILURES enabled");

    if (USE_SQN_HACK)
    {
        auto ak = calculateMilenage(OctetString::FromSpare(6), receivedRand).ak;
        m_sqn = OctetString::Xor(receivedAutn.subCopy(0, 6), ak);
    }

    auto milenage = calculateMilenage(m_sqn, receivedRand);
    auto &res = milenage.res;
    auto &ck = milenage.ck;
    auto &ik = milenage.ik;
    auto &milenageAk = milenage.ak;
    auto &milenageMac = milenage.mac_a;

    auto sqnXorAk = OctetString::Xor(m_sqn, milenageAk);
    auto ckPrimeIkPrime =
        keys::CalculateCkPrimeIkPrime(ck, ik, keys::ConstructServingNetworkName(m_base->config->plmn), sqnXorAk);
    auto &ckPrime = ckPrimeIkPrime.first;
    auto &ikPrime = ckPrimeIkPrime.second;

    if (!m_base->config->supi.has_value())
    {
        m_logger->err("UE has no SUPI, ignoring authentication request");
        return;
    }

    auto mk = keys::CalculateMk(ckPrime, ikPrime, m_base->config->supi.value());
    auto kaut = mk.subCopy(16, 32);

    m_logger->debug("ueData.sqn: %s", m_sqn.toHexString().c_str());
    m_logger->debug("ueData.op(C): %s", m_base->config->opC.toHexString().c_str());
    m_logger->debug("ueData.K: %s", m_base->config->key.toHexString().c_str());
    m_logger->debug("calculated res: %s", res.toHexString().c_str());
    m_logger->debug("calculated ck: %s", ck.toHexString().c_str());
    m_logger->debug("calculated ik: %s", ik.toHexString().c_str());
    m_logger->debug("calculated milenageAk: %s", milenageAk.toHexString().c_str());
    m_logger->debug("calculated milenageMac: %s", milenageMac.toHexString().c_str());
    m_logger->debug("calculated ckPrime: %s", ckPrime.toHexString().c_str());
    m_logger->debug("calculated ikPrime: %s", ikPrime.toHexString().c_str());
    m_logger->debug("calculated kaut: %s", kaut.toHexString().c_str());

    // Control received KDF
    if (!IGNORE_CONTROLS_FAILURES && receivedKdf != 1)
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

        if (!IGNORE_CONTROLS_FAILURES && eapResponse != nullptr)
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

        if (!IGNORE_CONTROLS_FAILURES)
        {
            ueRejectionTimers();

            auto eapResponse = std::make_unique<eap::EapAkaPrime>(eap::ECode::RESPONSE, receivedEap.id,
                eap::ESubType::AKA_CLIENT_ERROR);
            eapResponse->attributes.putClientErrorCode(0);

            nas::AuthenticationReject response;
            response.eapMessage = nas::IEEapMessage{};
            response.eapMessage->eap = std::move(eapResponse);

            sendNasMessage(response);
            return;
        }
    }

    // Create new partial native NAS security context and continue key derivation
    auto kAusf = keys::CalculateKAusfForEapAkaPrime(mk);
    m_logger->debug("kAusf: %s", kAusf.toHexString().c_str());

    m_nonCurrentNsCtx = NasSecurityContext{};
    m_nonCurrentNsCtx->tsc = msg.ngKSI.tsc;
    m_nonCurrentNsCtx->ngKsi = msg.ngKSI.ksi;
    m_nonCurrentNsCtx->keys.rand = std::move(receivedRand);
    m_nonCurrentNsCtx->keys.res = std::move(res);
    m_nonCurrentNsCtx->keys.resStar = {};
    m_nonCurrentNsCtx->keys.kAusf = std::move(kAusf);
    m_nonCurrentNsCtx->keys.abba = msg.abba.rawData.copy();

    keys::DeriveKeysSeafAmf(*m_base->config, *m_nonCurrentNsCtx);

    m_logger->debug("kSeaf: %s", m_nonCurrentNsCtx->keys.kSeaf.toHexString().c_str());
    m_logger->debug("kAmf: %s", m_nonCurrentNsCtx->keys.kAmf.toHexString().c_str());

    // Send Response
    {
        auto *akaPrimeResponse =
            new eap::EapAkaPrime(eap::ECode::RESPONSE, receivedEap.id, eap::ESubType::AKA_CHALLENGE);
        akaPrimeResponse->attributes.putRes(m_nonCurrentNsCtx->keys.res);
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
    auto sendFailure = [this](nas::EMmCause cause) {
      nas::AuthenticationFailure resp;
      resp.mmCause.value = cause;
      sendNasMessage(resp);
    };

    if (USE_SQN_HACK)
    {
        // Log.warning(Tag.CONFIG, "USE_SQN_HACK: %s", USE_SQN_HACK);
    }

    if (IGNORE_CONTROLS_FAILURES)
        m_logger->warn("IGNORE_CONTROLS_FAILURES enabled");

    if (!msg.authParamRAND.has_value() || !msg.authParamAUTN.has_value())
    {
        sendFailure(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
        return;
    }

    auto &rand = msg.authParamRAND->value;
    auto &autn = msg.authParamAUTN->value;

    m_logger->debug("Received rand[%s] autn[%s]", rand.toHexString().c_str(), autn.toHexString().c_str());

    if (USE_SQN_HACK)
    {
        auto ak = calculateMilenage(OctetString::FromSpare(6), rand).ak;
        m_sqn = OctetString::Xor(autn.subCopy(0, 6), ak);
    }

    auto milenage = calculateMilenage(m_sqn, rand);
    auto &res = milenage.res;
    auto &ck = milenage.ck;
    auto &ik = milenage.ik;
    auto ckIk = OctetString::Concat(ck, ik);
    auto &milenageAk = milenage.ak;
    auto &milenageMac = milenage.mac_a;
    auto sqnXorAk = OctetString::Xor(m_sqn, milenageAk);
    auto snn = keys::ConstructServingNetworkName(m_base->config->plmn);

    m_logger->debug("Calculated res[%s] ck[%s] ik[%s] ak[%s] mac_a[%s]", res.toHexString().c_str(),
        ck.toHexString().c_str(), ik.toHexString().c_str(), milenageAk.toHexString().c_str(),
        milenageMac.toHexString().c_str());
    m_logger->debug("Used snn[%s] sqn[%s]", snn.c_str(), m_sqn.toHexString().c_str());

    auto autnCheck = validateAutn(milenageAk, milenageMac, autn);

    if (IGNORE_CONTROLS_FAILURES || autnCheck == EAutnValidationRes::OK)
    {
        // Create new partial native NAS security context and continue with key derivation
        m_nonCurrentNsCtx = NasSecurityContext{};
        m_nonCurrentNsCtx->tsc = msg.ngKSI.tsc;
        m_nonCurrentNsCtx->ngKsi = msg.ngKSI.ksi;
        m_nonCurrentNsCtx->keys.rand = rand.copy();
        m_nonCurrentNsCtx->keys.resStar = keys::CalculateResStar(ckIk, snn, rand, res);
        m_nonCurrentNsCtx->keys.res = std::move(res);
        m_nonCurrentNsCtx->keys.kAusf = keys::CalculateKAusfFor5gAka(ck, ik, snn, sqnXorAk);
        m_nonCurrentNsCtx->keys.abba = msg.abba.rawData.copy();

        keys::DeriveKeysSeafAmf(*m_base->config, *m_nonCurrentNsCtx);

        m_logger->debug("Derived kSeaf[%s] kAusf[%s] kAmf[%s]", m_nonCurrentNsCtx->keys.kSeaf.toHexString().c_str(),
            m_nonCurrentNsCtx->keys.kAusf.toHexString().c_str(),
            m_nonCurrentNsCtx->keys.kAmf.toHexString().c_str());

        // Send response
        nas::AuthenticationResponse resp;
        resp.authenticationResponseParameter = nas::IEAuthenticationResponseParameter{};
        resp.authenticationResponseParameter->rawData = m_nonCurrentNsCtx->keys.resStar.copy();
        sendNasMessage(resp);
    }
    else if (autnCheck == EAutnValidationRes::MAC_FAILURE)
    {
        sendFailure(nas::EMmCause::MAC_FAILURE);
    }
    else if (autnCheck == EAutnValidationRes::SYNCHRONISATION_FAILURE)
    {
        // TODO
        m_logger->err("SYNCHRONISATION_FAILURE case not implemented yet in AUTN validation");
        sendFailure(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
    }
    else
    {
        sendFailure(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
    }
}

void NasMm::receiveAuthenticationResult(const nas::AuthenticationResult &msg)
{
    if (msg.abba.has_value())
        m_nonCurrentNsCtx->keys.abba = msg.abba->rawData.copy();

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

    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
        {
            m_storedGuti = {};
            m_taiList = {};
            m_lastVisitedRegisteredTai = {};
            m_currentNsCtx = {};
            m_nonCurrentNsCtx = {};

            receiveEapFailureMessage(*msg.eapMessage->eap);
        }
        else
        {
            m_logger->warn("Network sent EAP with inconvenient type in AuthenticationReject, ignoring EAP IE.");
        }
    }
}

void NasMm::receiveEapSuccessMessage(const eap::Eap &eap)
{
    // do nothing
}

void NasMm::receiveEapFailureMessage(const eap::Eap &eap)
{
    m_logger->err("EAP failure received. Deleting non-current NAS security context");
    m_nonCurrentNsCtx = {};
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

    // Check MAC
    if (receivedMAC != mac)
    {
        m_logger->err("AUTN validation MAC mismatch. expected: %s received: %s", mac.toHexString().c_str(),
            receivedMAC.toHexString().c_str());
        return EAutnValidationRes::MAC_FAILURE;
    }

    // TS 33.501: An ME accessing 5G shall check during authentication that the "separation bit" in the AMF field
    // of AUTN is set to 1. The "separation bit" is bit 0 of the AMF field of AUTN.
    if (receivedAMF.get(0).bit(7) != 1)
    {
        m_logger->err("AUTN validation SEP-BIT failure. expected: 1, received: 0");
        return EAutnValidationRes::AMF_SEPARATION_BIT_FAILURE;
    }

    // Verify that the received sequence number SQN is in the correct range
    if (!checkSqn(receivedSQN))
    {
        m_logger->err("AUTN validation SQN not acceptable");
        return EAutnValidationRes::SYNCHRONISATION_FAILURE;
    }

    return EAutnValidationRes::OK;
}

bool NasMm::checkSqn(const OctetString &)
{
    // TODO:
    //  Verify the freshness of sequence numbers to determine whether the specified sequence number is
    //  in the correct range and acceptable by the USIM. See 3GPP TS 33.102, Annex C.2.
    return true;
}

crypto::milenage::Milenage NasMm::calculateMilenage(const OctetString &sqn, const OctetString &rand)
{
    if (m_base->config->opType == OpType::OPC)
        return crypto::milenage::Calculate(m_base->config->opC, m_base->config->key, rand, sqn, m_base->config->amf);
    OctetString opc = crypto::milenage::CalculateOpC(m_base->config->opC, m_base->config->key);
    return crypto::milenage::Calculate(opc, m_base->config->key, rand, sqn, m_base->config->amf);
}


}