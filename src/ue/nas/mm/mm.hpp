//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <lib/crypt/milenage.hpp>
#include <lib/nas/nas.hpp>
#include <lib/nas/timer.hpp>
#include <ue/nas/usim/usim.hpp>
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
    Usim *m_usim{};

    ERmState m_rmState;
    ECmState m_cmState;
    EMmState m_mmState;
    EMmSubState m_mmSubState;

    // Most recent registration request
    std::unique_ptr<nas::RegistrationRequest> m_lastRegistrationRequest{};
    // Most recent service request
    std::unique_ptr<nas::ServiceRequest> m_lastServiceRequest{};
    // Most recent de-registration request
    std::unique_ptr<nas::DeRegistrationRequestUeOriginating> m_lastDeregistrationRequest{};
    // Indicates that last registration request was sent without a NAS security context
    bool m_lastRegWithoutNsc{};
    // Indicates the last de-registration cause
    EDeregCause m_lastDeregCause{};
    // Indicates the last service request cause
    EServiceReqCause m_lastServiceReqCause{};
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
    // Last time Service Request needed indication for Data
    long m_lastTimeServiceReqNeededIndForData{};
    // Number of times the network failing the authentication check
    int m_nwConsecutiveAuthFailure{};

    friend class UeCmdHandler;

  public:
    NasMm(TaskBase *base, UeTimers *timers);

  public: /* Base */
    void onStart(NasSm *sm, Usim *usim);
    void onQuit();

  private: /* Base */
    void triggerMmCycle();
    void performMmCycle();
    void switchMmState(EMmState state, EMmSubState subState);
    void switchCmState(ECmState state);
    void switchUState(E5UState state);
    void onSwitchMmState(EMmState oldState, EMmState newState, EMmSubState oldSubState, EMmSubState newSubSate);
    void onSwitchRmState(ERmState oldState, ERmState newState);
    void onSwitchCmState(ECmState oldState, ECmState newState);
    void onSwitchUState(E5UState oldState, E5UState newState);

  private: /* Messaging */
    void sendNasMessage(const nas::PlainMmMessage &msg);
    void receiveNasMessage(const nas::NasMessage &msg);
    void receiveMmMessage(const nas::PlainMmMessage &msg);
    void receiveMmStatus(const nas::FiveGMmStatus &msg);
    void sendMmStatus(nas::EMmCause cause);

  private: /* Transport */
    void receiveDlNasTransport(const nas::DlNasTransport &msg);

  public: /* Transport */
    void deliverUlTransport(const nas::UlNasTransport &msg);

  public: /* Registration */
    void sendMobilityRegistration(ERegUpdateCause updateCause);

  private: /* Registration */
    void sendInitialRegistration(EInitialRegCause regCause);
    void receiveRegistrationAccept(const nas::RegistrationAccept &msg);
    void receiveInitialRegistrationAccept(const nas::RegistrationAccept &msg);
    void receiveMobilityRegistrationAccept(const nas::RegistrationAccept &msg);
    void receiveRegistrationReject(const nas::RegistrationReject &msg);
    void receiveInitialRegistrationReject(const nas::RegistrationReject &msg);
    void receiveMobilityRegistrationReject(const nas::RegistrationReject &msg);
    void handleAbnormalInitialRegFailure(nas::ERegistrationType regType);
    void handleAbnormalMobilityRegFailure(nas::ERegistrationType regType);
    void resetRegAttemptCounter();

  private: /* Authentication */
    void receiveAuthenticationRequest(const nas::AuthenticationRequest &msg);
    void receiveAuthenticationRequestEap(const nas::AuthenticationRequest &msg);
    void receiveAuthenticationRequest5gAka(const nas::AuthenticationRequest &msg);
    void receiveAuthenticationResult(const nas::AuthenticationResult &msg);
    void receiveAuthenticationReject(const nas::AuthenticationReject &msg);
    void receiveEapSuccessMessage(const eap::Eap &eap);
    void receiveEapFailureMessage(const eap::Eap &eap);
    EAutnValidationRes validateAutn(const OctetString &rand, const OctetString &autn);
    crypto::milenage::Milenage calculateMilenage(const OctetString &sqn, const OctetString &rand, bool dummyAmf);
    bool networkFailingTheAuthCheck(bool hasChance);

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
    void sendServiceRequest(EServiceReqCause reqCause);
    void receiveServiceAccept(const nas::ServiceAccept &msg);
    void receiveServiceReject(const nas::ServiceReject &msg);

  private: /* Network Slicing */
    NetworkSlice makeRequestedNssai(bool &isDefaultNssai) const;
    void handleNetworkSlicingSubscriptionChange();

  private: /* Radio */
    void localReleaseConnection();
    void handlePlmnSearchResponse(const std::vector<UeCellMeasurement> &measures);
    void handleRrcConnectionSetup();
    void handleRrcConnectionRelease();
    void handleServingCellChange(const UeCellInfo &servingCell);
    void handleRadioLinkFailure();
    void handlePaging(const std::vector<GutiMobileIdentity> &tmsiIds);

  private: /* Access Control */
    bool isHighPriority();
    bool hasEmergency();
    void setN1Capability(bool enabled);
    bool isInNonAllowedArea();

  private: /* eCall */
    bool startECallInactivityIfNeeded();
    bool switchToECallInactivityIfNeeded();

  private: /* Timer */
    void onTimerExpire(nas::NasTimer &timer);

  public:
    /* Interface */
    void handleRrcEvent(const NwUeRrcToNas &msg); // used by RRC
    void handleNasEvent(const NwUeNasToNas &msg); // used by NAS
    bool isRegistered();                          // used by SM
    bool isRegisteredForEmergency();              // used by SM
    void serviceNeededForUplinkData();            // used by SM
};

} // namespace nr::ue