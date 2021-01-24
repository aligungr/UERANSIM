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
        // TODO: Bu kısım çalışmıyor olabilir.
        long current = utils::CurrentTimeMillis();
        if (mmCtx.lastPlmnSearchTrigger == 0)
        {
            mmCtx.lastPlmnSearchTrigger = current;
            return;
        }
        long elapsedMs = current - mmCtx.lastPlmnSearchTrigger;
        if (elapsedMs > 50)
        {
            // TODO rrcTask.push(new IwPlmnSearchRequest());
            mmCtx.lastPlmnSearchTrigger = current;
        }
        return;
    }

    if (mmCtx.mmSubState == EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE)
    {
        if (emulationMode && !timers.t3346.running())
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
    mmCtx.mmState = state;
    mmCtx.mmSubState = subState;

    // TODO: trigger on switch
    // TODO: status update

    logger->info("UE switches to state: %s/%s", MmStateName(state), MmSubStateName(subState));

    triggerMmCycle();
}

void NasTask::switchRmState(ERmState state)
{
    mmCtx.rmState = state;

    // TODO: trigger on switch
    // TODO: status update

    logger->info("UE switches to state: %s", RmStateName(state));

    triggerMmCycle();
}

void NasTask::receivePlmnSearchResponse(const NwPlmnSearchResponse &msg)
{
    // TODO trigger on connected

    logger->info("UE connected to gNB");
    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NORMAL_SERVICE);
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
            if (!timers.t3519.running())
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

    sendMmMessage(*registrationRequest);
    mmCtx.registrationRequest = std::move(registrationRequest);
}

void NasTask::receiveAuthenticationRequest(const nas::AuthenticationRequest &message)
{
    if (message.eapMessage.has_value())
        receiveAuthenticationRequestEap(message);
    else
        receiveAuthenticationRequest5gAka(message);
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

nas::IE5gsMobileIdentity NasTask::getOrGenerateSuci()
{
    if (timers.t3519.running())
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

    if (supi->value != "imsi")
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

void NasTask::receiveAuthenticationRequestEap(const nas::AuthenticationRequest &message)
{
    // TODO
}

void NasTask::receiveAuthenticationResponse(const nas::AuthenticationResponse &message)
{
    // TODO
}

void NasTask::receiveAuthenticationRequest5gAka(const nas::AuthenticationRequest &message)
{
    // TODO
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

    sendMmMessage(resp);
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
                sendMmMessage(resp);
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

    sendMmMessage(request);

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

    sendMmMessage(nas::DeRegistrationAcceptUeTerminated{});
    switchMmState(EMmState::MM_DEREGISTERED, EMmSubState::MM_DEREGISTERED_NA);
    switchRmState(ERmState::RM_DEREGISTERED);
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
        timers.t3512.start(msg.t3512Value.value());

    if (msg.mobileIdentity.has_value() && msg.mobileIdentity->type == nas::EIdentityType::GUTI)
    {
        mmCtx.storedGuti = msg.mobileIdentity.value();
        timers.t3519.stop();

        sendCompleteMes = true;
    }

    if (sendCompleteMes)
        sendMmMessage(nas::RegistrationComplete{});

    switchMmState(EMmState::MM_REGISTERED, EMmSubState::MM_REGISTERED_NORMAL_SERVICE);
    switchRmState(ERmState::RM_REGISTERED);

    auto regType = mmCtx.registrationRequest->registrationType.registrationType;
    logger->info("Registration is successful (%s)", nas::utils::EnumToString(regType));
}

void NasTask::receiveRegistrationReject(const nas::RegistrationReject &msg)
{
    auto cause = msg.mmCause.value;
    auto regType = mmCtx.registrationRequest->registrationType.registrationType;

    logger->err("Registration failed (%s) (%s)", nas::utils::EnumToString(regType), nas::utils::EnumToString(cause));

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

void NasTask::receiveSecurityModeCommand(const nas::SecurityModeCommand &msg)
{
    auto reject = [this](nas::EMmCause cause) {
        nas::SecurityModeReject resp;
        resp.mmCause.value = cause;
        sendMmMessage(resp);
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

        if (!nas::utils::DeepEqualsIe2346(replayed, real))
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

    // Assign selected algorithms to security context, and derive NAS keys
    nonCurrentNsCtx->integrity = msg.selectedNasSecurityAlgorithms.integrity;
    nonCurrentNsCtx->ciphering = msg.selectedNasSecurityAlgorithms.ciphering;
    keys::DeriveNasKeys(*nonCurrentNsCtx);

    logger->debug("kNasEnc: %s", nonCurrentNsCtx->keys.kNasEnc.toHexString().c_str());
    logger->debug("kNasInt: %s", nonCurrentNsCtx->keys.kNasInt.toHexString().c_str());
    logger->debug("selectedIntAlg: %s", nonCurrentNsCtx->integrity);
    logger->debug("selectedEncAlg: %s", nonCurrentNsCtx->ciphering);

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
    sendMmMessage(resp);
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

void NasTask::receiveMmCause(const nas::IE5gMmCause &msg)
{
    logger->err("MM cause received: %s", nas::utils::EnumToString(msg.value));
}

} // namespace nr::ue