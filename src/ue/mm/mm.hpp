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
#include <ue/nas/storage.hpp>
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
    UeTimers *m_timers;
    std::unique_ptr<Logger> m_logger;
    NasSm *m_sm;
    MobileStorage m_storage{};

    ERmState m_rmState;
    ECmState m_cmState;
    EMmState m_mmState;
    EMmSubState m_mmSubState;

    // Most recent registration request
    std::unique_ptr<nas::RegistrationRequest> m_lastRegistrationRequest{};
    // Most recent de-registration request
    std::unique_ptr<nas::DeRegistrationRequestUeOriginating> m_lastDeregistrationRequest{};
    // Indicates the last de-registration cause
    EDeregCause m_lastDeregCause{};
    // Last time PLMN search is triggered
    long m_lastPlmnSearchTrigger{};
    // Registration attempt counter
    int m_regCounter{};
    // Service request attempt counter
    int m_serCounter{};
    // Indicates registered for emergency services (Only meaningful in RM-REGISTERED state)
    bool m_registeredForEmergency{};
    // Network feature support information
    nas::IE5gsNetworkFeatureSupport m_nwFeatureSupport{};

    friend class UeCmdHandler;

  public:
    NasMm(TaskBase *base, UeTimers *timers);

  public: /* Base */
    void onStart(NasSm *sm);
    void onQuit();
    void triggerMmCycle();
    void performMmCycle();

  private: /* Base */
    void switchMmState(EMmState state, EMmSubState subState);
    void switchRmState(ERmState state);
    void switchCmState(ECmState state);
    void switchUState(E5UState state);
    void onSwitchMmState(EMmState oldState, EMmState newState, EMmSubState oldSubState, EMmSubState newSubSate);
    void onSwitchRmState(ERmState oldState, ERmState newState);
    void onSwitchCmState(ECmState oldState, ECmState newState);
    void onSwitchUState(E5UState oldState, E5UState newState);
    void setN1Capability(bool enabled);

  public: /* Transport */
    void sendNasMessage(const nas::PlainMmMessage &msg);
    void receiveNasMessage(const nas::NasMessage &msg);

  private: /* Transport */
    void sendMmStatus(nas::EMmCause cause);
    void receiveMmMessage(const nas::PlainMmMessage &msg);
    void receiveDlNasTransport(const nas::DlNasTransport &msg);
    void receiveMmStatus(const nas::FiveGMmStatus &msg);
    void receiveMmCause(const nas::IE5gMmCause &msg);

  private: /* Registration */
    void sendInitialRegistration(bool isEmergencyReg, bool dueToDereg);
    void sendMobilityRegistration(ERegUpdateCause updateCause);
    void receiveRegistrationAccept(const nas::RegistrationAccept &msg);
    void receiveInitialRegistrationAccept(const nas::RegistrationAccept &msg);
    void receiveMobilityRegistrationAccept(const nas::RegistrationAccept &msg);
    void receiveRegistrationReject(const nas::RegistrationReject &msg);
    void receiveInitialRegistrationReject(const nas::RegistrationReject &msg);
    void receiveMobilityRegistrationReject(const nas::RegistrationReject &msg);
    void handleAbnormalInitialRegFailure(nas::ERegistrationType regType);
    void handleAbnormalMobilityRegFailure(nas::ERegistrationType regType);

  private: /* Authentication */
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

  private: /* Security */
    void receiveSecurityModeCommand(const nas::SecurityModeCommand &msg);
    nas::IEUeSecurityCapability createSecurityCapabilityIe();

  public: /* De-registration */
    void sendDeregistration(EDeregCause deregCause);

  private: /* De-registration */
    void receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg);
    void receiveDeregistrationRequest(const nas::DeRegistrationRequestUeTerminated &msg);

  private: /* Configuration */
    void receiveConfigurationUpdate(const nas::ConfigurationUpdateCommand &msg);

  private: /* Identity */
    void receiveIdentityRequest(const nas::IdentityRequest &msg);
    nas::IE5gsMobileIdentity getOrGenerateSuci();
    nas::IE5gsMobileIdentity generateSuci();
    nas::IE5gsMobileIdentity getOrGeneratePreferredId();

  private: /* Service */
    void receiveServiceAccept(const nas::ServiceAccept &msg);
    void receiveServiceReject(const nas::ServiceReject &msg);

  private: /* Network Slicing */
    NetworkSlice makeRequestedNssai(bool &isDefaultNssai) const;
    void handleNetworkSlicingSubscriptionChange();

  public: /* Radio */
    void handlePlmnSearchResponse(const std::string &gnbName);
    void handlePlmnSearchFailure();
    void handleRrcConnectionSetup();
    void handleRrcConnectionRelease();
    void handleRadioLinkFailure();

  private: /* Radio */
    void localReleaseConnection();

  private: /* Access Control */
    bool isHighPriority();
    bool hasEmergency();

  public: /* Timer */
    void onTimerExpire(nas::NasTimer &timer);
};

} // namespace nr::ue