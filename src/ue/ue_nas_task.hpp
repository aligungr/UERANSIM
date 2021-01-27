//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "ue_nts.hpp"
#include "ue_types.hpp"

#include <crypt_milenage.hpp>
#include <nas.hpp>
#include <nas_timer.hpp>
#include <nts.hpp>

namespace nr::ue
{

class NasTask : public NtsTask
{
  private:
    TaskBase *base;
    std::unique_ptr<Logger> logger;

    UeTimers timers;
    MmContext mmCtx;
    SmContext smCtx;
    std::optional<NasSecurityContext> currentNsCtx;
    std::optional<NasSecurityContext> nonCurrentNsCtx;
    bool emulationMode;

  public:
    explicit NasTask(TaskBase *base);
    ~NasTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;

  private:
    /* NAS transport related */
    void receiveNasMessage(const nas::NasMessage &msg);
    void receiveMmMessage(const nas::PlainMmMessage &msg);
    void receiveDlNasTransport(const nas::DlNasTransport &msg);
    void receiveSmMessage(const nas::SmMessage &msg);
    void sendNasMessage(const nas::PlainMmMessage &msg);
    void sendSmMessage(int psi, const nas::SmMessage &msg);
    void sendMmStatus(nas::EMmCause cause);

    /* Timer related */
    void onTimerExpire(nas::NasTimer &timer);
    void performTick();

    /* MM base functions */
    void triggerMmCycle();
    void performMmCycle();
    void switchMmState(EMmState state, EMmSubState subState);
    void switchRmState(ERmState state);
    void receivePlmnSearchResponse(const NwPlmnSearchResponse &msg);
    void receiveMmStatus(const nas::FiveGMmStatus &msg);
    void receiveMmCause(const nas::IE5gMmCause &msg);

    /* MM registration */
    void sendRegistration(nas::ERegistrationType registrationType, nas::EFollowOnRequest followOn);
    void receiveRegistrationAccept(const nas::RegistrationAccept &msg);
    void receiveRegistrationReject(const nas::RegistrationReject &msg);

    /* MM authentication */
    void receiveAuthenticationRequest(const nas::AuthenticationRequest &msg);
    void receiveAuthenticationRequestEap(const nas::AuthenticationRequest &msg);
    void receiveAuthenticationRequest5gAka(const nas::AuthenticationRequest &msg);
    void receiveAuthenticationResult(const nas::AuthenticationResult &msg);
    void receiveAuthenticationResponse(const nas::AuthenticationResponse &msg);
    void receiveAuthenticationReject(const nas::AuthenticationReject &msg);
    void receiveEapSuccessMessage(const eap::Eap &eap);
    void receiveEapFailureMessage(const eap::Eap &eap);
    void receiveEapResponseMessage(const eap::Eap &eap);
    EAutnValidationRes validateAutn(const OctetString &ak, const OctetString &mac, const OctetString &autn);
    bool checkSqn(const OctetString &sqn);
    crypto::milenage::Milenage calculateMilenage(const OctetString &sqn, const OctetString &rand);

    /* MM security */
    void receiveSecurityModeCommand(const nas::SecurityModeCommand &msg);
    nas::IEUeSecurityCapability createSecurityCapabilityIe();

    /* MM configuration */
    void receiveConfigurationUpdate(const nas::ConfigurationUpdateCommand &msg);

    /* MM de-registration */
    void sendDeregistration(nas::ESwitchOff switchOff);
    void receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg);
    void receiveDeregistrationRequest(const nas::DeRegistrationRequestUeTerminated &msg);

    /* MM identity */
    void receiveIdentityRequest(const nas::IdentityRequest &msg);
    nas::IE5gsMobileIdentity getOrGenerateSuci();
    nas::IE5gsMobileIdentity generateSuci();

    /* MM service */
    void receiveServiceAccept(const nas::ServiceAccept &msg);
    void receiveServiceReject(const nas::ServiceReject &msg);

    /* SM base functions */
    int allocatePduSessionId(const SessionConfig &config);
    int allocateProcedureTransactionId();
    void releaseProcedureTransactionId(int pti);
    void releasePduSession(int psi);

    /* SM resource allocation */
    void receiveSmStatus(const nas::FiveGSmStatus &msg);
    void receiveSmCause(const nas::IE5gSmCause &msg);

    /* SM session establishment */
    void establishInitialSessions();
    void sendEstablishmentRequest(const SessionConfig &config);
    void receivePduSessionEstablishmentAccept(const nas::PduSessionEstablishmentAccept &msg);
    void receivePduSessionEstablishmentReject(const nas::PduSessionEstablishmentReject &msg);
};

} // namespace nr::ue