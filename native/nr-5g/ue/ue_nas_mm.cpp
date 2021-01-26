//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_nas_keys.hpp"
#include "ue_nas_task.hpp"
#include <common.hpp>
#include <crypt_milenage.hpp>
#include <nas_utils.hpp>

static const bool IGNORE_CONTROLS_FAILURES = false;
static const bool USE_SQN_HACK = true; // TODO

namespace nr::ue
{

void NasTask::triggerMmCycle()
{
    push(new NwPerformMmCycle());
}

void NasTask::performMmCycle()
{
    if (mmCtx.mmState == EMmState::MM_NULL)
    {
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
        return;
    }

    if (mmCtx.mmSubState == EMmSubState::MM_DEREGISTERED_PLMN_SEARCH)
    {
        long current = utils::CurrentTimeMillis();
        long elapsedMs = current - mmCtx.lastPlmnSearchTrigger;
        if (elapsedMs > 50)
        {
            base->rrcTask->push(new NwPlmnSearchRequest());
            mmCtx.lastPlmnSearchTrigger = current;
        }
        return;
    }

    if (mmCtx.mmSubState == EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE)
    {
        if (emulationMode && !timers.t3346.isRunning())
            sendRegistration(nas::ERegistrationType::INITIAL_REGISTRATION, nas::EFollowOnRequest::FOR_PENDING);
        return;
    }

    if (mmCtx.mmState == EMmState::MM_REGISTERED_INITIATED)
        return;
    if (mmCtx.mmSubState == EMmSubState::MM_REGISTERED_NORMAL_SERVICE)
        return;
    if (mmCtx.mmState == EMmState::MM_DEREGISTERED_INITIATED)
        return;
    if (mmCtx.mmSubState == EMmSubState::MM_DEREGISTERED_NA)
        return;

    if (emulationMode)
    {
        logger->err("unhandled UE MM state");
        return;
    }
}

void NasTask::switchMmState(EMmState state, EMmSubState subState)
{
    EMmState oldState = mmCtx.mmState;
    EMmSubState oldSubState = mmCtx.mmSubState;

    mmCtx.mmState = state;
    mmCtx.mmSubState = subState;

    if (base->nodeListener)
    {
        base->nodeListener->onSwitch(app::NodeType::UE, base->config->getNodeName(), app::StateType::MM,
                                     MmSubStateName(oldSubState), MmSubStateName(subState));
        base->nodeListener->onSwitch(app::NodeType::UE, base->config->getNodeName(), app::StateType::MM_SUB,
                                     MmStateName(oldState), MmStateName(state));
    }

    auto *statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::MM_STATE);
    statusUpdate->mmState = MmStateName(state);
    statusUpdate->mmSubState = MmSubStateName(subState);
    base->appTask->push(statusUpdate);

    logger->info("UE switches to state: %s", MmSubStateName(subState));

    triggerMmCycle();
}

void NasTask::switchRmState(ERmState state)
{
    ERmState oldState = mmCtx.rmState;

    mmCtx.rmState = state;

    if (base->nodeListener)
    {
        base->nodeListener->onSwitch(app::NodeType::UE, base->config->getNodeName(), app::StateType::RM,
                                     RmStateName(oldState), RmStateName(mmCtx.rmState));
    }

    auto *statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::RM_STATE);
    statusUpdate->rmState = RmStateName(state);
    base->appTask->push(statusUpdate);

    logger->info("UE switches to state: %s", RmStateName(state));

    triggerMmCycle();
}

void NasTask::receivePlmnSearchResponse(const NwPlmnSearchResponse &msg)
{
    if (base->nodeListener)
        base->nodeListener->onConnected(app::NodeType::UE, base->config->getNodeName(), app::NodeType::GNB,
                                        msg.gnbName);

    logger->info("UE connected to gNB");
    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE);
}

void NasTask::receiveMmStatus(const nas::FiveGMmStatus &msg)
{
    receiveMmCause(msg.mmCause);
}

void NasTask::receiveMmCause(const nas::IE5gMmCause &msg)
{
    logger->err("MM cause received: %s", nas::utils::EnumToString(msg.value));
}

void NasTask::sendRegistration(nas::ERegistrationType registrationType, nas::EFollowOnRequest followOn)
{
    switchMmState(EMmState::MM_REGISTERED_INITIATED, EMmSubState::MM_REGISTERED_INITIATED_NA);

    nas::IENasKeySetIdentifier ngKsi;

    if (currentNsCtx.has_value())
    {
        ngKsi.tsc = currentNsCtx->tsc;
        ngKsi.ksi = currentNsCtx->ngKsi;
    }

    auto registrationRequest = std::make_unique<nas::RegistrationRequest>();
    registrationRequest->registrationType = nas::IE5gsRegistrationType{followOn, registrationType};
    registrationRequest->nasKeySetIdentifier = ngKsi;
    registrationRequest->requestedNSSAI = nas::utils::NssaiFrom(base->config->nssais);
    registrationRequest->ueSecurityCapability = createSecurityCapabilityIe();
    registrationRequest->updateType =
        nas::IE5gsUpdateType(nas::ESmsRequested::NOT_SUPPORTED, nas::ENgRanRadioCapabilityUpdate::NOT_NEEDED);

    if (registrationType != nas::ERegistrationType::PERIODIC_REGISTRATION_UPDATING)
    {
        registrationRequest->mmCapability = nas::IE5gMmCapability{};
        registrationRequest->mmCapability->s1Mode = nas::EEpcNasSupported::NOT_SUPPORTED;
        registrationRequest->mmCapability->hoAttach = nas::EHandoverAttachSupported::NOT_SUPPORTED;
        registrationRequest->mmCapability->lpp = nas::ELtePositioningProtocolCapability::NOT_SUPPORTED;
    }

    if (mmCtx.storedGuti.type != nas::EIdentityType::NO_IDENTITY)
    {
        registrationRequest->mobileIdentity = mmCtx.storedGuti;
    }
    else
    {
        auto suci = getOrGenerateSuci();
        if (suci.type != nas::EIdentityType::NO_IDENTITY)
        {
            registrationRequest->mobileIdentity = suci;
            if (!timers.t3519.isRunning())
                timers.t3519.start();
        }
        else if (base->config->imei.length() > 0)
        {
            registrationRequest->mobileIdentity.type = nas::EIdentityType::IMEI;
            registrationRequest->mobileIdentity.value = base->config->imei;
        }
        else
        {
            registrationRequest->mobileIdentity.type = nas::EIdentityType::NO_IDENTITY;
        }
    }

    if (mmCtx.lastVisitedRegisteredTai.has_value())
        registrationRequest->lastVisitedRegisteredTai = mmCtx.lastVisitedRegisteredTai.value();

    timers.t3510.start();
    timers.t3502.stop();
    timers.t3511.stop();

    sendNasMessage(*registrationRequest);
    mmCtx.registrationRequest = std::move(registrationRequest);
}

void NasTask::receiveRegistrationAccept(const nas::RegistrationAccept &msg)
{
    if (mmCtx.mmState != EMmState::MM_REGISTERED_INITIATED)
    {
        logger->warn("Registration Accept ignored since the MM state is MM_REGISTERED_INITIATED");
        return;
    }

    bool sendCompleteMes = false;

    mmCtx.taiList = msg.taiList;

    if (msg.t3512Value.has_value() && nas::utils::HasValue(msg.t3512Value.value()))
    {
        timers.t3512.start(*msg.t3512Value);
        logger->debug("T3512 started with int[%d]", timers.t3512.getInterval());
    }

    if (msg.mobileIdentity.has_value() && msg.mobileIdentity->type == nas::EIdentityType::GUTI)
    {
        mmCtx.storedGuti = msg.mobileIdentity.value();
        timers.t3519.stop();

        sendCompleteMes = true;
    }

    if (sendCompleteMes)
        sendNasMessage(nas::RegistrationComplete{});

    switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
    switchRmState(ERmState::RM_REGISTERED);

    auto regType = mmCtx.registrationRequest->registrationType.registrationType;
    logger->info("%s is successful", nas::utils::EnumToString(regType));

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION ||
        regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
    {
        push(new NwTriggerInitialSessionCreate());
    }
}

void NasTask::receiveRegistrationReject(const nas::RegistrationReject &msg)
{
    auto cause = msg.mmCause.value;
    auto regType = mmCtx.registrationRequest->registrationType.registrationType;

    logger->err("%s failed [%s]", nas::utils::EnumToString(regType), nas::utils::EnumToString(cause));

    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            logger->warn("Network sent EAP with type of %s in RegistrationReject, ignoring EAP IE.",
                         nas::utils::EnumToString(msg.eapMessage->eap->code));
    }

    auto unhandledRejectCase = [cause, this]() {
        logger->err("Registration rejected with unhandled MMCause: %s", nas::utils::EnumToString(cause));
        switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
        switchRmState(ERmState::RM_DEREGISTERED);
    };

    if (regType == nas::ERegistrationType::INITIAL_REGISTRATION)
    {
        if (cause == nas::EMmCause::ILLEGAL_UE || cause == nas::EMmCause::ILLEGAL_ME ||
            cause == nas::EMmCause::FIVEG_SERVICES_NOT_ALLOWED || cause == nas::EMmCause::PLMN_NOT_ALLOWED ||
            cause == nas::EMmCause::TA_NOT_ALLOWED || cause == nas::EMmCause::ROAMING_NOT_ALLOWED_IN_TA ||
            cause == nas::EMmCause::NO_SUITIBLE_CELLS_IN_TA)
        {
            mmCtx.storedGuti = {};
            mmCtx.lastVisitedRegisteredTai = {};
            mmCtx.taiList = {};
            currentNsCtx = {};
            nonCurrentNsCtx = {};

            switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_PLMN_SEARCH);
            switchRmState(ERmState::RM_DEREGISTERED);
        }
        else if (cause == nas::EMmCause::CONGESTION)
        {
            if (msg.t3346value.has_value() && nas::utils::HasValue(*msg.t3346value))
            {
                switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_ATTEMPTING_REGISTRATION);
                switchRmState(ERmState::RM_DEREGISTERED);

                timers.t3346.stop();
                if (msg.sht != nas::ESecurityHeaderType::NOT_PROTECTED)
                    timers.t3346.start(*msg.t3346value);
                else
                    timers.t3346.start(nas::IEGprsTimer2{5});
            }
            else
            {
                // TODO abnormal case see 5.5.1.2.7.
                unhandledRejectCase();
            }
        }
        else if (cause == nas::EMmCause::N1_MODE_NOT_ALLOWED)
        {
            mmCtx.storedGuti = {};
            mmCtx.lastVisitedRegisteredTai = {};
            mmCtx.taiList = {};
            currentNsCtx = {};
            nonCurrentNsCtx = {};

            switchMmState(EMmState::MM_NULL, EMmSubState::MM_NULL_NA);
            switchRmState(ERmState::RM_DEREGISTERED);
        }
        else
        {
            // TODO
            unhandledRejectCase();
        }
    }
    else if (regType == nas::ERegistrationType::EMERGENCY_REGISTRATION)
    {
        if (cause == nas::EMmCause::PEI_NOT_ACCEPTED)
        {
            // TODO
            unhandledRejectCase();
        }
        else
        {
            // TODO: abnormal case
            unhandledRejectCase();
        }
    }
    else
    {
        // TODO
        unhandledRejectCase();
    }
}

void NasTask::receiveAuthenticationRequest(const nas::AuthenticationRequest &msg)
{
    if (msg.eapMessage.has_value())
        receiveAuthenticationRequestEap(msg);
    else
        receiveAuthenticationRequest5gAka(msg);
}

void NasTask::receiveAuthenticationRequestEap(const nas::AuthenticationRequest &msg)
{
    auto ueRejectionTimers = [this]() {
        timers.t3520.start();

        timers.t3510.stop();
        timers.t3517.stop();
        timers.t3521.stop();
    };

    timers.t3520.stop();

    // Read EAP-AKA' request
    auto &receivedEap = (const eap::EapAkaPrime &)*msg.eapMessage->eap;
    auto receivedRand = receivedEap.attributes.getRand();
    auto receivedMac = receivedEap.attributes.getMac();
    auto receivedAutn = receivedEap.attributes.getAutn();
    auto receivedKdf = receivedEap.attributes.getKdf();

    logger->debug("received at_rand: %s", receivedRand.toHexString().c_str());
    logger->debug("received at_mac: %s", receivedMac.toHexString().c_str());
    logger->debug("received at_autn: %s", receivedAutn.toHexString().c_str());

    // Derive keys
    if (USE_SQN_HACK)
    {
        // Log.warning(Tag.CONFIG, "USE_SQN_HACK: %s", USE_SQN_HACK);
    }

    if (IGNORE_CONTROLS_FAILURES)
        logger->warn("IGNORE_CONTROLS_FAILURES enabled");

    if (USE_SQN_HACK)
    {
        auto ak = calculateMilenage(OctetString::FromSpare(6), receivedRand).ak;
        mmCtx.sqn = OctetString::Xor(receivedAutn.subCopy(0, 6), ak);
    }

    auto milenage = calculateMilenage(mmCtx.sqn, receivedRand);
    auto &res = milenage.res;
    auto &ck = milenage.ck;
    auto &ik = milenage.ik;
    auto &milenageAk = milenage.ak;
    auto &milenageMac = milenage.mac_a;

    auto sqnXorAk = OctetString::Xor(mmCtx.sqn, milenageAk);
    auto ckPrimeIkPrime =
        keys::CalculateCkPrimeIkPrime(ck, ik, keys::ConstructServingNetworkName(base->config->plmn), sqnXorAk);
    auto &ckPrime = ckPrimeIkPrime.first;
    auto &ikPrime = ckPrimeIkPrime.second;

    if (!base->config->supi.has_value())
    {
        logger->err("UE has no SUPI, ignoring authentication request");
        return;
    }

    auto mk = keys::CalculateMk(ckPrime, ikPrime, base->config->supi.value());
    auto kaut = mk.subCopy(16, 32);

    logger->debug("ueData.sqn: %s", mmCtx.sqn.toHexString().c_str());
    logger->debug("ueData.op(C): %s", base->config->opC.toHexString().c_str());
    logger->debug("ueData.K: %s", base->config->key.toHexString().c_str());
    logger->debug("calculated res: %s", res.toHexString().c_str());
    logger->debug("calculated ck: %s", ck.toHexString().c_str());
    logger->debug("calculated ik: %s", ik.toHexString().c_str());
    logger->debug("calculated milenageAk: %s", milenageAk.toHexString().c_str());
    logger->debug("calculated milenageMac: %s", milenageMac.toHexString().c_str());
    logger->debug("calculated ckPrime: %s", ckPrime.toHexString().c_str());
    logger->debug("calculated ikPrime: %s", ikPrime.toHexString().c_str());
    logger->debug("calculated kaut: %s", kaut.toHexString().c_str());

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
            logger->err("Feature not implemented yet: SYNCHRONISATION_FAILURE in AUTN validation for EAP AKA'");
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
        logger->err("AT_MAC failure in EAP AKA'. expected: %s received: %s", expectedMac.toHexString().c_str(),
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
    logger->debug("kAusf: %s", kAusf.toHexString().c_str());

    nonCurrentNsCtx = NasSecurityContext{};
    nonCurrentNsCtx->tsc = msg.ngKSI.tsc;
    nonCurrentNsCtx->ngKsi = msg.ngKSI.ksi;
    nonCurrentNsCtx->keys.rand = std::move(receivedRand);
    nonCurrentNsCtx->keys.res = std::move(res);
    nonCurrentNsCtx->keys.resStar = {};
    nonCurrentNsCtx->keys.kAusf = std::move(kAusf);
    nonCurrentNsCtx->keys.abba = msg.abba.rawData.copy();

    keys::DeriveKeysSeafAmf(*base->config, *nonCurrentNsCtx);

    logger->debug("kSeaf: %s", nonCurrentNsCtx->keys.kSeaf.toHexString().c_str());
    logger->debug("kAmf: %s", nonCurrentNsCtx->keys.kAmf.toHexString().c_str());

    // Send Response
    {
        auto *akaPrimeResponse =
            new eap::EapAkaPrime(eap::ECode::RESPONSE, receivedEap.id, eap::ESubType::AKA_CHALLENGE);
        akaPrimeResponse->attributes.putRes(nonCurrentNsCtx->keys.res);
        akaPrimeResponse->attributes.putMac(OctetString::FromSpare(16)); // Dummy mac for now
        akaPrimeResponse->attributes.putKdf(1);

        // Calculate and put mac value
        auto sendingMac = keys::CalculateMacForEapAkaPrime(kaut, *akaPrimeResponse);
        akaPrimeResponse->attributes.putMac(sendingMac);

        logger->debug("sending eap at_mac: %s", sendingMac.toHexString().c_str());

        nas::AuthenticationResponse response;
        response.eapMessage = nas::IEEapMessage{};
        response.eapMessage->eap = std::unique_ptr<eap::EapAkaPrime>(akaPrimeResponse);

        sendNasMessage(response);
    }
}

void NasTask::receiveAuthenticationRequest5gAka(const nas::AuthenticationRequest &msg)
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
        logger->warn("IGNORE_CONTROLS_FAILURES enabled");

    if (!msg.authParamRAND.has_value() || !msg.authParamAUTN.has_value())
    {
        sendFailure(nas::EMmCause::SEMANTICALLY_INCORRECT_MESSAGE);
        return;
    }

    auto &rand = msg.authParamRAND->value;
    auto &autn = msg.authParamAUTN->value;

    logger->debug("Received rand[%s] autn[%s]", rand.toHexString().c_str(), autn.toHexString().c_str());

    if (USE_SQN_HACK)
    {
        auto ak = calculateMilenage(OctetString::FromSpare(6), rand).ak;
        mmCtx.sqn = OctetString::Xor(autn.subCopy(0, 6), ak);
    }

    auto milenage = calculateMilenage(mmCtx.sqn, rand);
    auto &res = milenage.res;
    auto &ck = milenage.ck;
    auto &ik = milenage.ik;
    auto ckIk = OctetString::Concat(ck, ik);
    auto &milenageAk = milenage.ak;
    auto &milenageMac = milenage.mac_a;
    auto sqnXorAk = OctetString::Xor(mmCtx.sqn, milenageAk);
    auto snn = keys::ConstructServingNetworkName(base->config->plmn);

    logger->debug("Calculated res[%s] ck[%s] ik[%s] ak[%s] mac_a[%s]", res.toHexString().c_str(),
                  ck.toHexString().c_str(), ik.toHexString().c_str(), milenageAk.toHexString().c_str(),
                  milenageMac.toHexString().c_str());
    logger->debug("Used snn[%s] sqn[%s]", snn.c_str(), mmCtx.sqn.toHexString().c_str());

    auto autnCheck = validateAutn(milenageAk, milenageMac, autn);

    if (IGNORE_CONTROLS_FAILURES || autnCheck == EAutnValidationRes::OK)
    {
        // Create new partial native NAS security context and continue with key derivation
        nonCurrentNsCtx = NasSecurityContext{};
        nonCurrentNsCtx->tsc = msg.ngKSI.tsc;
        nonCurrentNsCtx->ngKsi = msg.ngKSI.ksi;
        nonCurrentNsCtx->keys.rand = rand.copy();
        nonCurrentNsCtx->keys.resStar = keys::CalculateResStar(ckIk, snn, rand, res);
        nonCurrentNsCtx->keys.res = std::move(res);
        nonCurrentNsCtx->keys.kAusf = keys::CalculateKAusfFor5gAka(ck, ik, snn, sqnXorAk);
        nonCurrentNsCtx->keys.abba = msg.abba.rawData.copy();

        keys::DeriveKeysSeafAmf(*base->config, *nonCurrentNsCtx);

        logger->debug("Derived kSeaf[%s] kAusf[%s] kAmf[%s]", nonCurrentNsCtx->keys.kSeaf.toHexString().c_str(),
                      nonCurrentNsCtx->keys.kAusf.toHexString().c_str(),
                      nonCurrentNsCtx->keys.kAmf.toHexString().c_str());

        // Send response
        nas::AuthenticationResponse resp;
        resp.authenticationResponseParameter = nas::IEAuthenticationResponseParameter{};
        resp.authenticationResponseParameter->rawData = nonCurrentNsCtx->keys.resStar.copy();
        sendNasMessage(resp);
    }
    else if (autnCheck == EAutnValidationRes::MAC_FAILURE)
    {
        sendFailure(nas::EMmCause::MAC_FAILURE);
    }
    else if (autnCheck == EAutnValidationRes::SYNCHRONISATION_FAILURE)
    {
        // TODO
        logger->err("SYNCHRONISATION_FAILURE case not implemented yet in AUTN validation");
        sendFailure(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
    }
    else
    {
        sendFailure(nas::EMmCause::UNSPECIFIED_PROTOCOL_ERROR);
    }
}

void NasTask::receiveAuthenticationResult(const nas::AuthenticationResult &msg)
{
    if (msg.abba.has_value())
        nonCurrentNsCtx->keys.abba = msg.abba->rawData.copy();

    if (msg.eapMessage.eap->code == eap::ECode::SUCCESS)
        receiveEapSuccessMessage(*msg.eapMessage.eap);
    else if (msg.eapMessage.eap->code == eap::ECode::FAILURE)
        receiveEapFailureMessage(*msg.eapMessage.eap);
    else
        logger->warn("Network sent EAP with an inconvenient type in Authentication Result, ignoring EAP IE.");
}

void NasTask::receiveAuthenticationResponse(const nas::AuthenticationResponse &msg)
{
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::RESPONSE)
            receiveEapResponseMessage(*msg.eapMessage->eap);
        else
            logger->warn("Network sent EAP with an inconvenient type in Authentication Response, ignoring EAP IE.");
    }
}

void NasTask::receiveAuthenticationReject(const nas::AuthenticationReject &msg)
{
    logger->err("Authentication Reject received.");

    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
        {
            mmCtx.storedGuti = {};
            mmCtx.taiList = {};
            mmCtx.lastVisitedRegisteredTai = {};
            currentNsCtx = {};
            nonCurrentNsCtx = {};

            receiveEapFailureMessage(*msg.eapMessage->eap);
        }
        else
        {
            logger->warn("Network sent EAP with inconvenient type in AuthenticationReject, ignoring EAP IE.");
        }
    }
}

void NasTask::receiveEapSuccessMessage(const eap::Eap &eap)
{
    // do nothing
}

void NasTask::receiveEapFailureMessage(const eap::Eap &eap)
{
    logger->err("EAP failure received. Deleting non-current NAS security context");
    nonCurrentNsCtx = {};
}

void NasTask::receiveEapResponseMessage(const eap::Eap &eap)
{
    if (eap.eapType == eap::EEapType::EAP_AKA_PRIME)
    {
        // TODO
    }
    else
    {
        logger->err("Unhandled EAP Response message type");
    }
}

EAutnValidationRes NasTask::validateAutn(const OctetString &ak, const OctetString &mac, const OctetString &autn)
{
    // Decode AUTN
    OctetString receivedSQNxorAK = autn.subCopy(0, 6);
    OctetString receivedSQN = OctetString::Xor(receivedSQNxorAK, ak);
    OctetString receivedAMF = autn.subCopy(6, 2);
    OctetString receivedMAC = autn.subCopy(8, 8);

    // Check MAC
    if (receivedMAC != mac)
    {
        logger->err("AUTN validation MAC mismatch. expected: %s received: %s", mac.toHexString().c_str(),
                    receivedMAC.toHexString().c_str());
        return EAutnValidationRes::MAC_FAILURE;
    }

    // TS 33.501: An ME accessing 5G shall check during authentication that the "separation bit" in the AMF field
    // of AUTN is set to 1. The "separation bit" is bit 0 of the AMF field of AUTN.
    if (receivedAMF.get(0).bit(7) != 1)
    {
        logger->err("AUTN validation SEP-BIT failure. expected: 1, received: 0");
        return EAutnValidationRes::AMF_SEPARATION_BIT_FAILURE;
    }

    // Verify that the received sequence number SQN is in the correct range
    if (!checkSqn(receivedSQN))
    {
        logger->err("AUTN validation SQN not acceptable");
        return EAutnValidationRes::SYNCHRONISATION_FAILURE;
    }

    return EAutnValidationRes::OK;
}

bool NasTask::checkSqn(const OctetString &)
{
    // TODO:
    //  Verify the freshness of sequence numbers to determine whether the specified sequence number is
    //  in the correct range and acceptable by the USIM. See 3GPP TS 33.102, Annex C.2.
    return true;
}

crypto::milenage::Milenage NasTask::calculateMilenage(const OctetString &sqn, const OctetString &rand)
{
    if (base->config->opType == OpType::OPC)
        return crypto::milenage::Calculate(base->config->opC, base->config->key, rand, sqn, base->config->amf);
    OctetString opc = crypto::milenage::CalculateOpC(base->config->opC, base->config->key);
    return crypto::milenage::Calculate(opc, base->config->key, rand, sqn, base->config->amf);
}

void NasTask::receiveSecurityModeCommand(const nas::SecurityModeCommand &msg)
{
    auto reject = [this](nas::EMmCause cause) {
        nas::SecurityModeReject resp;
        resp.mmCause.value = cause;
        sendNasMessage(resp);
        logger->err("Rejecting Security Mode Command with cause: %s", nas::utils::EnumToString(cause));
    };

    if (!nonCurrentNsCtx.has_value())
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
            logger->warn("EAP message with inconvenient code received in Security Mode Command. Ignoring EAP message.");
    }

    // Assign ABBA (if any)
    if (msg.abba.has_value())
        nonCurrentNsCtx->keys.abba = msg.abba->rawData.copy();

    // Check selected algorithms
    {
        // TODO
        // if (msg.selectedNasSecurityAlgorithms.integrity is supported according to config file)
        // if (msg.selectedNasSecurityAlgorithms.ciphering is supported according to config file)
    }

    // Assign selected algorithms to security context, and derive NAS keys
    nonCurrentNsCtx->integrity = msg.selectedNasSecurityAlgorithms.integrity;
    nonCurrentNsCtx->ciphering = msg.selectedNasSecurityAlgorithms.ciphering;
    keys::DeriveNasKeys(*nonCurrentNsCtx);

    logger->debug("Derived kNasEnc[%s] kNasInt[%s]", nonCurrentNsCtx->keys.kNasEnc.toHexString().c_str(),
                  nonCurrentNsCtx->keys.kNasInt.toHexString().c_str());
    logger->debug("Selected integrity[%d] ciphering[%d]", (int)nonCurrentNsCtx->integrity,
                  (int)nonCurrentNsCtx->ciphering);

    // Set non-current NAS Security Context as current one.
    currentNsCtx = nonCurrentNsCtx->deepCopy();

    // Prepare response
    nas::SecurityModeComplete resp;

    // Append IMEISV if requested
    if (msg.imeiSvRequest.has_value() && msg.imeiSvRequest->imeiSvRequest == nas::EImeiSvRequest::REQUESTED)
    {
        // TODO: imei vs imeiSv may be separated.
        resp.imeiSv = nas::IE5gsMobileIdentity{};
        resp.imeiSv->type = nas::EIdentityType::IMEISV;
        resp.imeiSv->value = base->config->imei;
    }

    resp.nasMessageContainer = nas::IENasMessageContainer{};
    nas::EncodeNasMessage(*mmCtx.registrationRequest, resp.nasMessageContainer->data);

    // Send response
    sendNasMessage(resp);
}

nas::IEUeSecurityCapability NasTask::createSecurityCapabilityIe()
{
    auto &algs = base->config->supportedAlgs;
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

void NasTask::receiveConfigurationUpdate(const nas::ConfigurationUpdateCommand &msg)
{
    timers.t3346.stop();

    if (msg.guti.has_value() && msg.guti->type == nas::EIdentityType::GUTI)
    {
        mmCtx.storedGuti = msg.guti.value();
        mmCtx.storedSuci = {};
        timers.t3519.stop();
    }

    if (msg.taiList.has_value())
        mmCtx.taiList = msg.taiList.value();

    if (msg.configurationUpdateIndication.has_value())
    {
        if (msg.configurationUpdateIndication.has_value())
        {
            if (msg.configurationUpdateIndication->red == nas::ERegistrationRequested::REQUESTED)
            {
                // TODO
            }
            if (msg.configurationUpdateIndication->ack == nas::EAcknowledgement::REQUESTED)
            {
                nas::ConfigurationUpdateComplete resp;
                sendNasMessage(resp);
            }
        }
    }
}

void NasTask::sendDeregistration(nas::ESwitchOff switchOff)
{
    switchMmState(EMmState::MM_DEREGISTERED_INITIATED, EMmSubState::MM_DEREGISTERED_INITIATED_NA);

    nas::DeRegistrationRequestUeOriginating request;
    request.deRegistrationType.accessType = nas::EDeRegistrationAccessType::THREEGPP_ACCESS;
    request.deRegistrationType.reRegistrationRequired = nas::EReRegistrationRequired::NOT_REQUIRED;
    request.deRegistrationType.switchOff = switchOff;

    if (currentNsCtx.has_value())
    {
        request.ngKSI.tsc = currentNsCtx->tsc;
        request.ngKSI.ksi = currentNsCtx->ngKsi;
    }

    if (mmCtx.storedGuti.type != nas::EIdentityType::NO_IDENTITY)
        request.mobileIdentity = mmCtx.storedGuti;
    else
        request.mobileIdentity = getOrGenerateSuci();

    sendNasMessage(request);

    if (switchOff != nas::ESwitchOff::SWITCH_OFF)
    {
        if (mmCtx.mmState == EMmState::MM_REGISTERED || mmCtx.mmState == EMmState::MM_DEREGISTERED_INITIATED)
            timers.t3521.start();
    }
}

void NasTask::receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg)
{
    timers.t3521.stop();
    timers.t3519.stop();

    mmCtx.storedSuci = {};

    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
    switchRmState(ERmState::RM_DEREGISTERED);

    logger->info("De-registration is successful");
}

void NasTask::receiveDeregistrationRequest(const nas::DeRegistrationRequestUeTerminated &msg)
{
    // TODO: this function is not complete

    if (msg.deRegistrationType.reRegistrationRequired == nas::EReRegistrationRequired::REQUIRED)
    {
        timers.t3346.stop();
        timers.t3396.stop();
        timers.t3584.stop();
        timers.t3585.stop();
    }

    sendNasMessage(nas::DeRegistrationAcceptUeTerminated{});
    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
    switchRmState(ERmState::RM_DEREGISTERED);
}

void NasTask::receiveIdentityRequest(const nas::IdentityRequest &msg)
{
    nas::IdentityResponse resp;

    if (msg.identityType.value == nas::EIdentityType::SUCI)
    {
        resp.mobileIdentity = getOrGenerateSuci();
    }
    else if (msg.identityType.value == nas::EIdentityType::IMEI)
    {
        resp.mobileIdentity.type = nas::EIdentityType::IMEI;
        resp.mobileIdentity.value = base->config->imei;
    }
    else
    {
        resp.mobileIdentity.type = nas::EIdentityType::NO_IDENTITY;
        logger->err("Requested identity is not available: %d", (int)msg.identityType.value);
    }

    sendNasMessage(resp);
}

nas::IE5gsMobileIdentity NasTask::getOrGenerateSuci()
{
    if (timers.t3519.isRunning())
    {
        logger->debug("T3519 is running, returning stored SUCI.");
        return mmCtx.storedSuci;
    }

    mmCtx.storedSuci = generateSuci();

    logger->debug("T3519 is not running, new SUCI generated.");
    timers.t3519.start();

    if (mmCtx.storedSuci.type == nas::EIdentityType::NO_IDENTITY)
        return {};
    return mmCtx.storedSuci;
}

nas::IE5gsMobileIdentity NasTask::generateSuci()
{
    auto &supi = base->config->supi;
    auto &plmn = base->config->plmn;

    if (!supi.has_value())
        return {};

    if (supi->type != "imsi")
    {
        logger->err("SUCI generating failed, invalid SUPI type: %s", supi->value.c_str());
        return {};
    }

    const std::string &imsi = supi->value;
    int mccInImsi = utils::ParseInt(imsi.substr(0, 3));
    int mncInImsi = utils::ParseInt(imsi.substr(3, plmn.isLongMnc ? 3 : 2));

    if (mccInImsi != plmn.mcc || mncInImsi != plmn.mnc)
    {
        logger->err("MCC/MNC mismatch in SUCI generation.");
        return {};
    }

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

void NasTask::receiveServiceAccept(const nas::ServiceAccept &msg)
{
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            logger->warn("Network sent EAP with inconvenient type in ServiceAccept, ignoring EAP IE.");
    }

    // TODO
}

void NasTask::receiveServiceReject(const nas::ServiceReject &msg)
{
    if (msg.eapMessage.has_value())
    {
        if (msg.eapMessage->eap->code == eap::ECode::FAILURE)
            receiveEapFailureMessage(*msg.eapMessage->eap);
        else
            logger->warn("Network sent EAP with inconvenient type in ServiceAccept, ignoring EAP IE.");
    }

    // TODO
}

} // namespace nr::ue