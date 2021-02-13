//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <crypt/milenage.hpp>
#include <nas/nas.hpp>
#include <nas/timer.hpp>
#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>

namespace nr::ue
{

class NasSm;

class NasMm
{
  private:
    TaskBase *m_base;
    NtsTask *m_nas;
    UeTimers *m_timers;
    std::unique_ptr<Logger> m_logger;
    NasSm *m_sm;

    ERmState m_rmState;
    ECmState m_cmState;
    EMmState m_mmState;
    EMmSubState m_mmSubState;

    std::unique_ptr<nas::RegistrationRequest> m_lastRegistrationRequest{};
    nas::IE5gsMobileIdentity m_storedSuci{};
    nas::IE5gsMobileIdentity m_storedGuti{};
    std::optional<nas::IE5gsTrackingAreaIdentity> m_lastVisitedRegisteredTai{};
    std::optional<nas::IE5gsTrackingAreaIdentityList> m_taiList{};

    std::optional<NasSecurityContext> m_currentNsCtx;
    std::optional<NasSecurityContext> m_nonCurrentNsCtx;

    bool m_emulationMode;
    bool m_validSim;
    long m_lastPlmnSearchTrigger{};
    OctetString m_sqn{};

    friend class UeCmdHandler;

  public:
    NasMm(TaskBase *base, NtsTask *nas, UeTimers *timers);

  public:
    /* Base */
    void onStart(NasSm *sm);
    void onQuit();
    void triggerMmCycle();
    void performMmCycle();
    void onTimerExpire(nas::NasTimer &timer);
    void receivePlmnSearchResponse(const std::string &gnbName);
    void receivePlmnSearchFailure();
    void receiveRrcConnectionSetup();

    /* Transport */
    void sendNasMessage(const nas::PlainMmMessage &msg);
    void receiveNasMessage(const nas::NasMessage &msg);

  private:
    /* Base */
    void switchMmState(EMmState state, EMmSubState subState);
    void switchRmState(ERmState state);
    void switchCmState(ECmState state);
    void onSwitchMmState(EMmState oldState, EMmState newState, EMmSubState oldSubState, EMmSubState newSubSate);
    void onSwitchRmState(ERmState oldState, ERmState newState);
    void onSwitchCmState(ECmState oldState, ECmState newState);

    /* Transport */
    void sendMmStatus(nas::EMmCause cause);
    void receiveMmMessage(const nas::PlainMmMessage &msg);
    void receiveDlNasTransport(const nas::DlNasTransport &msg);
    void receiveMmStatus(const nas::FiveGMmStatus &msg);
    void receiveMmCause(const nas::IE5gMmCause &msg);

    /* Registration */
    void sendRegistration(nas::ERegistrationType registrationType, nas::EFollowOnRequest followOn);
    void receiveRegistrationAccept(const nas::RegistrationAccept &msg);
    void receiveRegistrationReject(const nas::RegistrationReject &msg);

    /* Authentication */
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

    /* Security */
    void receiveSecurityModeCommand(const nas::SecurityModeCommand &msg);
    nas::IEUeSecurityCapability createSecurityCapabilityIe();

    /* Configuration */
    void receiveConfigurationUpdate(const nas::ConfigurationUpdateCommand &msg);

    /* De-registration */
    void sendDeregistration(nas::ESwitchOff switchOff);
    void receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg);
    void receiveDeregistrationRequest(const nas::DeRegistrationRequestUeTerminated &msg);

    /* Identity */
    void receiveIdentityRequest(const nas::IdentityRequest &msg);
    nas::IE5gsMobileIdentity getOrGenerateSuci();
    nas::IE5gsMobileIdentity generateSuci();

    /* Service */
    void receiveServiceAccept(const nas::ServiceAccept &msg);
    void receiveServiceReject(const nas::ServiceReject &msg);
};

} // namespace nr::ue