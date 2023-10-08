//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <lib/crypt/milenage.hpp>
#include <lib/nas/nas.hpp>
#include <ue/nas/storage.hpp>
#include <ue/nas/usim/usim.hpp>
#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>
#include <utils/random.hpp>
#include "ext/compact25519/compact_x25519.hpp"
#include "ext/crypt-ext/x963kdf.h"
#include "ext/crypt-ext/aes.hpp"
#include "ext/crypt-ext/hmac-sha256.h"


namespace nr::ue
{

class NasSm;

class NasMm
{
  private:
    TaskBase *m_base;
    NasTimers *m_timers;
    std::unique_ptr<Logger> m_logger;
    NasSm *m_sm;
    Usim *m_usim;
    MmStorage *m_storage;

    ERmState m_rmState;
    ECmState m_cmState;
    EMmState m_mmState;
    EMmSubState m_mmSubState;

    // Procedure management
    ProcControl m_procCtl;
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
    // Registration attempt counter
    int m_regCounter{};
    // Service request attempt counter
    int m_serCounter{};
    // Indicates registered for emergency services (Only meaningful in RM-REGISTERED state, or implies the last one)
    bool m_registeredForEmergency{};
    // Network feature support information
    std::optional<nas::IE5gsNetworkFeatureSupport> m_nwFeatureSupport{};
    // Number of times the network failing the authentication check
    int m_nwConsecutiveAuthFailure{};
    // Last time PLMN search failure logged
    int64_t m_lastTimePlmnSearchFailureLogged{};
    // Last time MM state changed
    int64_t m_lastTimeMmStateChange{};

    friend class UeCmdHandler;
    friend class NasSm;
    friend class NasTask;

  public:
    NasMm(TaskBase *base, NasTimers *timers);

  public: /* Base */
    void onStart(NasSm *sm, Usim *usim);
    void onQuit();

  private: /* Base */
    void triggerMmCycle();
    void performMmCycle();
    void switchMmState(EMmSubState subState);
    void switchCmState(ECmState state);
    void switchUState(E5UState state);
    void onSwitchMmState(EMmState oldState, EMmState newState, EMmSubState oldSubState, EMmSubState newSubSate);
    void onSwitchRmState(ERmState oldState, ERmState newState);
    void onSwitchCmState(ECmState oldState, ECmState newState);
    void onSwitchUState(E5UState oldState, E5UState newState);
    void onSwitchOn();
    void onSimInsertion();
    void onSwitchOff();
    void onSimRemoval();

  private: /* Messaging */
    EProcRc sendNasMessage(const nas::PlainMmMessage &msg);
    void receiveNasMessage(const nas::NasMessage &msg);
    void receiveMmMessage(const nas::PlainMmMessage &msg);
    void receiveMmStatus(const nas::FiveGMmStatus &msg);
    void sendMmStatus(nas::EMmCause cause);
    bool checkForReplay(const nas::SecuredMmMessage &msg);

  private: /* Transport */
    void receiveDlNasTransport(const nas::DlNasTransport &msg);
    EProcRc deliverUlTransport(const nas::UlNasTransport &msg, ENasTransportHint hint);

  private: /* Registration */
    EProcRc sendInitialRegistration(EInitialRegCause regCause);
    EProcRc sendMobilityRegistration(ERegUpdateCause updateCause);
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

  private: /* De-registration */
    EProcRc sendDeregistration(EDeregCause deregCause);
    void receiveDeregistrationAccept(const nas::DeRegistrationAcceptUeOriginating &msg);
    void receiveDeregistrationRequest(const nas::DeRegistrationRequestUeTerminated &msg);
    void performLocalDeregistration();

  private: /* Configuration */
    void receiveConfigurationUpdate(const nas::ConfigurationUpdateCommand &msg);
    void updateForbiddenTaiListsForAllowedIndications();

  private: /* Identity */
    void receiveIdentityRequest(const nas::IdentityRequest &msg);
    nas::IE5gsMobileIdentity getOrGenerateSuci();
    nas::IE5gsMobileIdentity generateSuci();
    std::string generateSUCIProfileA(const std::string &imsi, const OctetString &hnPublicKey);
    nas::IE5gsMobileIdentity getOrGeneratePreferredId();

  private: /* Service */
    EProcRc sendServiceRequest(EServiceReqCause reqCause);
    void receiveServiceAccept(const nas::ServiceAccept &msg);
    void receiveServiceReject(const nas::ServiceReject &msg);

  private: /* Network Slicing */
    NetworkSlice makeRequestedNssai(bool &isDefaultNssai) const;
    void handleNetworkSlicingSubscriptionChange();

  private: /* Radio */
    void performPlmnSelection();
    void localReleaseConnection(bool treatBarred);
    void handleActiveCellChange(const Tai &prevTai);
    void handleRrcConnectionSetup();
    void handleRrcConnectionRelease();
    void handleRrcEstablishmentFailure();
    void handleRadioLinkFailure();
    void handleRrcFallbackIndication();
    void handlePaging(const std::vector<GutiMobileIdentity> &tmsiIds);
    void updateProvidedGuti(bool provide = true);

  private: /* Access Control */
    bool isHighPriority();
    bool hasEmergency();
    void setN1Capability(bool enabled);
    bool isInNonAllowedArea();
    EUacResult performUac();

  private: /* eCall */
    bool startECallInactivityIfNeeded();
    bool switchToECallInactivityIfNeeded();

  private: /* Timer */
    void onTimerExpire(UeTimer &timer);

  private: /* Procedure Control */
    void initialRegistrationRequired(EInitialRegCause cause);
    void mobilityUpdatingRequired(ERegUpdateCause cause);
    void serviceRequestRequiredForData();
    void serviceRequestRequiredForSignalling();
    void serviceRequestRequired(EServiceReqCause cause);
    void deregistrationRequired(EDeregCause cause);
    void invokeProcedures();
    bool hasPendingProcedure();

  private: /* Service Access Point */
    void handleRrcEvent(const NmUeRrcToNas &msg);
    void handleNasEvent(const NmUeNasToNas &msg);
};

} // namespace nr::ue