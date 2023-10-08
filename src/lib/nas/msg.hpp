//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "ie1.hpp"
#include "ie2.hpp"
#include "ie3.hpp"
#include "ie4.hpp"
#include "ie6.hpp"

namespace nas
{

struct NasMessageBuilder
{
    std::vector<std::function<void(OctetString &)>> mandatoryEncoders{};
    std::vector<std::function<void(OctetString &)>> optionalEncoders{};

    std::vector<std::function<void(const OctetView &)>> mandatoryDecoders{};
    std::unordered_map<int, std::function<void(const OctetView &)>> optionalDecoders{};

    template <typename T>
    inline void mandatoryIE(T *ptr)
    {
        mandatoryEncoders.push_back([ptr](OctetString &stream) { Encode2346(*ptr, stream); });
        mandatoryDecoders.push_back([ptr](const OctetView &stream) { *ptr = DecodeIe2346<T>(stream); });
    }

    template <typename T>
    inline void mandatoryIE1(T *ptr)
    {
        mandatoryEncoders.push_back([ptr](OctetString &stream) { EncodeIe1(0, *ptr, stream); });
        mandatoryDecoders.push_back([ptr](const OctetView &stream) { *ptr = DecodeIe1<T>(stream); });
    }

    template <typename T, typename U>
    inline void mandatoryIE1(T *ptr1, U *ptr2)
    {
        mandatoryEncoders.push_back([ptr1, ptr2](OctetString &stream) { EncodeIe1(*ptr1, *ptr2, stream); });
        mandatoryDecoders.push_back([ptr1, ptr2](const OctetView &stream) {
            int octet = stream.readI();
            *ptr1 = T::Decode((octet >> 4) & 0xF);
            *ptr2 = U::Decode(octet & 0xF);
        });
    }

    template <typename T>
    inline void optionalIE(int iei, std::optional<T> *ptr)
    {
        optionalEncoders.push_back([ptr, iei](OctetString &stream) {
            if (ptr->has_value())
            {
                stream.appendOctet(iei);
                Encode2346(ptr->value(), stream);
            }
        });
        optionalDecoders[iei] = [ptr](const OctetView &stream) {
            stream.readI();
            *ptr = DecodeIe2346<T>(stream);
        };
    }

    template <typename T>
    inline void optionalIE1(int iei, std::optional<T> *ptr)
    {
        optionalEncoders.push_back([ptr, iei](OctetString &stream) {
            if (ptr->has_value())
                EncodeIe1(iei, ptr->value(), stream);
        });
        optionalDecoders[iei] = [ptr](const OctetView &stream) { *ptr = DecodeIe1<T>(stream); };
    }
};

struct NasMessage
{
    EExtendedProtocolDiscriminator epd{};

  protected:
    NasMessage() = default;

  public:
    virtual ~NasMessage() = default;
};

struct SmMessage : NasMessage
{
    int pduSessionId{};
    int pti{};
    EMessageType messageType{};

  protected:
    SmMessage() = default;
};

struct MmMessage : NasMessage
{
    ESecurityHeaderType sht{};

  protected:
    MmMessage() = default;
};

struct PlainMmMessage : MmMessage
{
    EMessageType messageType{};

  protected:
    PlainMmMessage() = default;
};

struct SecuredMmMessage : MmMessage
{
    octet4 messageAuthenticationCode{};
    octet sequenceNumber{};
    OctetString plainNasMessage{};
};

///////////////////////////////////////////////////////////////////////////////////////

struct AuthenticationFailure : PlainMmMessage
{
    IE5gMmCause mmCause{};
    std::optional<IEAuthenticationFailureParameter> authenticationFailureParameter{};

    AuthenticationFailure();
    void onBuild(NasMessageBuilder &b);
};

struct AuthenticationReject : PlainMmMessage
{
    std::optional<IEEapMessage> eapMessage{};

    AuthenticationReject();
    void onBuild(NasMessageBuilder &b);
};

struct AuthenticationRequest : PlainMmMessage
{
    IENasKeySetIdentifier ngKSI{};
    IEAbba abba{};
    std::optional<IEAuthenticationParameterRand> authParamRAND{};
    std::optional<IEAuthenticationParameterAutn> authParamAUTN{};
    std::optional<IEEapMessage> eapMessage{};

    AuthenticationRequest();
    void onBuild(NasMessageBuilder &b);
};

struct AuthenticationResponse : PlainMmMessage
{
    std::optional<IEAuthenticationResponseParameter> authenticationResponseParameter{};
    std::optional<IEEapMessage> eapMessage{};

    AuthenticationResponse();
    void onBuild(NasMessageBuilder &b);
};

struct AuthenticationResult : PlainMmMessage
{
    IENasKeySetIdentifier ngKSI{};
    IEEapMessage eapMessage{};
    std::optional<IEAbba> abba{};

    AuthenticationResult();
    void onBuild(NasMessageBuilder &b);
};

struct ConfigurationUpdateCommand : PlainMmMessage
{
    std::optional<IEConfigurationUpdateIndication> configurationUpdateIndication{};
    std::optional<IE5gsMobileIdentity> guti{};
    std::optional<IE5gsTrackingAreaIdentityList> taiList{};
    std::optional<IENssai> allowedNssai{};
    std::optional<IEServiceAreaList> serviceAreaList{};
    std::optional<IENetworkName> networkFullName{};
    std::optional<IENetworkName> networkShortName{};
    std::optional<IETimeZone> localTimeZone{};
    std::optional<IETimeZoneAndTime> universalTimeAndLocalTimeZone{};
    std::optional<IEDaylightSavingTime> networkDaylightSavingTime{};
    std::optional<IELadnInformation> ladnInformation{};
    std::optional<IEMicoIndication> micoIndication{};
    std::optional<IENetworkSlicingIndication> networkSlicingIndication{};
    std::optional<IENssai> configuredNssai{};
    std::optional<IERejectedNssai> rejectedNssai{};
    std::optional<IEOperatorDefinedAccessCategoryDefinitions> operatorDefinedAccessCategoryDefinitions{};
    std::optional<IESmsIndication> smsIndication{};

    ConfigurationUpdateCommand();
    void onBuild(NasMessageBuilder &b);
};

struct ConfigurationUpdateComplete : PlainMmMessage
{
    ConfigurationUpdateComplete();
    void onBuild(NasMessageBuilder &b);
};

struct DeRegistrationAcceptUeOriginating : PlainMmMessage
{
    DeRegistrationAcceptUeOriginating();
    void onBuild(NasMessageBuilder &b);
};

struct DeRegistrationAcceptUeTerminated : PlainMmMessage
{
    DeRegistrationAcceptUeTerminated();
    void onBuild(NasMessageBuilder &b);
};

struct DeRegistrationRequestUeOriginating : PlainMmMessage
{
    IEDeRegistrationType deRegistrationType{};
    IENasKeySetIdentifier ngKSI{};
    IE5gsMobileIdentity mobileIdentity{};

    DeRegistrationRequestUeOriginating();
    void onBuild(NasMessageBuilder &b);
};

struct DeRegistrationRequestUeTerminated : PlainMmMessage
{
    IEDeRegistrationType deRegistrationType{};
    std::optional<IE5gMmCause> mmCause{};
    std::optional<IEGprsTimer2> t3346Value{};

    DeRegistrationRequestUeTerminated();
    void onBuild(NasMessageBuilder &b);
};

struct DlNasTransport : PlainMmMessage
{
    IEPayloadContainerType payloadContainerType{};
    IEPayloadContainer payloadContainer{};
    std::optional<IEPduSessionIdentity2> pduSessionId{};
    std::optional<IEAdditionalInformation> additionalInformation{};
    std::optional<IE5gMmCause> mmCause{};
    std::optional<IEGprsTimer3> backOffTimerValue{};

    DlNasTransport();
    void onBuild(NasMessageBuilder &b);
};

struct FiveGMmStatus : PlainMmMessage
{
    IE5gMmCause mmCause{};

    FiveGMmStatus();
    void onBuild(NasMessageBuilder &b);
};

struct FiveGSmStatus : SmMessage
{
    IE5gSmCause smCause{};

    FiveGSmStatus();
    void onBuild(NasMessageBuilder &b);
};

struct IdentityRequest : PlainMmMessage
{
    IE5gsIdentityType identityType{};

    IdentityRequest();
    void onBuild(NasMessageBuilder &b);
};

struct IdentityResponse : PlainMmMessage
{
    IE5gsMobileIdentity mobileIdentity{};

    IdentityResponse();
    void onBuild(NasMessageBuilder &b);
};

struct Notification : PlainMmMessage
{
    IEAccessType accessType{};

    Notification();
    void onBuild(NasMessageBuilder &b);
};

struct NotificationResponse : PlainMmMessage
{
    std::optional<IEPduSessionStatus> pduSessionStatus{};

    NotificationResponse();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionAuthenticationCommand : SmMessage
{
    IEEapMessage eapMessage{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionAuthenticationCommand();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionAuthenticationComplete : SmMessage
{
    IEEapMessage eapMessage{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionAuthenticationComplete();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionAuthenticationResult : SmMessage
{
    std::optional<IEEapMessage> eapMessage{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionAuthenticationResult();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionEstablishmentAccept : SmMessage
{
    IEPduSessionType selectedPduSessionType{};
    IESscMode selectedSscMode{};
    IEQoSRules authorizedQoSRules{};
    IESessionAmbr sessionAmbr{};
    std::optional<IE5gSmCause> smCause{};
    std::optional<IEPduAddress> pduAddress{};
    std::optional<IEGprsTimer> rqTimerValue{};
    std::optional<IESNssai> sNssai{};
    std::optional<IEAlwaysOnPduSessionIndication> alwaysOnPduSessionIndication{};
    std::optional<IEMappedEpsBearerContexts> mappedEpsBearerContexts{};
    std::optional<IEEapMessage> eapMessage{};
    std::optional<IEQoSFlowDescriptions> authorizedQoSFlowDescriptions{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};
    std::optional<IEDnn> dnn{};

    PduSessionEstablishmentAccept();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionEstablishmentReject : SmMessage
{
    IE5gSmCause smCause{};
    std::optional<IEGprsTimer3> backOffTimerValue{};
    std::optional<IEAllowedSscMode> allowedSscMode{};
    std::optional<IEEapMessage> eapMessage{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionEstablishmentReject();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionEstablishmentRequest : SmMessage
{
    IEIntegrityProtectionMaximumDataRate integrityProtectionMaximumDataRate{};
    std::optional<IEPduSessionType> pduSessionType{};
    std::optional<IESscMode> sscMode{};
    std::optional<IE5gSmCapability> smCapability{};
    std::optional<IEMaximumNumberOfSupportedPacketFilters> maximumNumberOfSupportedPacketFilters{};
    std::optional<IEAlwaysOnPduSessionRequested> alwaysOnPduSessionRequested{};
    std::optional<IESmPduDnRequestContainer> smPduDnRequestContainer{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionEstablishmentRequest();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionModificationCommand : SmMessage
{
    std::optional<IE5gSmCause> smCause{};
    std::optional<IESessionAmbr> sessionAmbr{};
    std::optional<IEGprsTimer> rqTimerValue{};
    std::optional<IEAlwaysOnPduSessionIndication> alwaysOnPduSessionIndication{};
    std::optional<IEQoSRules> authorizedQoSRules{};
    std::optional<IEMappedEpsBearerContexts> mappedEpsBearerContexts{};
    std::optional<IEQoSFlowDescriptions> authorizedQoSFlowDescriptions{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionModificationCommand();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionModificationCommandReject : SmMessage
{
    IE5gSmCause smCause{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionModificationCommandReject();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionModificationComplete : SmMessage
{
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionModificationComplete();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionModificationReject : SmMessage
{
    IE5gSmCause smCause{};
    std::optional<IEGprsTimer3> backOffTimerValue{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionModificationReject();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionModificationRequest : SmMessage
{
    std::optional<IE5gSmCapability> smCapability{};
    std::optional<IE5gSmCause> smCause{};
    std::optional<IEMaximumNumberOfSupportedPacketFilters> maximumNumberOfSupportedPacketFilters{};
    std::optional<IEAlwaysOnPduSessionRequested> alwaysOnPduSessionRequested{};
    std::optional<IEIntegrityProtectionMaximumDataRate> integrityProtectionMaximumDataRate{};
    std::optional<IEQoSRules> requestedQosRules{};
    std::optional<IEQoSFlowDescriptions> requestedQosFlowDescriptions{};
    std::optional<IEMappedEpsBearerContexts> mappedEpsBearerContexts{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionModificationRequest();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionReleaseCommand : SmMessage
{
    IE5gSmCause smCause{};
    std::optional<IEGprsTimer3> backOffTimerValue{};
    std::optional<IEEapMessage> eapMessage{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionReleaseCommand();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionReleaseComplete : SmMessage
{
    std::optional<IE5gSmCause> smCause{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionReleaseComplete();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionReleaseReject : SmMessage
{
    IE5gSmCause smCause{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionReleaseReject();
    void onBuild(NasMessageBuilder &b);
};

struct PduSessionReleaseRequest : SmMessage
{
    std::optional<IE5gSmCause> smCause{};
    std::optional<IEExtendedProtocolConfigurationOptions> extendedProtocolConfigurationOptions{};

    PduSessionReleaseRequest();
    void onBuild(NasMessageBuilder &b);
};

struct RegistrationAccept : PlainMmMessage
{
    IE5gsRegistrationResult registrationResult{};
    std::optional<IENetworkSlicingIndication> networkSlicingIndication{};
    std::optional<IENssaiInclusionMode> nssaiInclusionMode{};
    std::optional<IEMicoIndication> micoIndication{};
    std::optional<IE5gsMobileIdentity> mobileIdentity{};
    std::optional<IENssai> allowedNSSAI{};
    std::optional<IEPduSessionStatus> pduSessionStatus{};
    std::optional<IEEapMessage> eapMessage{};
    std::optional<IEPlmnList> equivalentPLMNs{};
    std::optional<IERejectedNssai> rejectedNSSAI{};
    std::optional<IENssai> configuredNSSAI{};
    std::optional<IE5gsNetworkFeatureSupport> networkFeatureSupport{};
    std::optional<IEPduSessionReactivationResult> pduSessionReactivationResult{};
    std::optional<IEPduSessionReactivationResultErrorCause> pduSessionReactivationResultErrorCause{};
    std::optional<IEGprsTimer3> t3512Value{};
    std::optional<IEGprsTimer2> t3502Value{};
    std::optional<IEGprsTimer2> non3gppDeRegistrationTimerValue{};
    std::optional<IE5gsDrxParameters> negotiatedDrxParameters{};
    std::optional<IE5gsTrackingAreaIdentityList> taiList{};
    std::optional<IEServiceAreaList> serviceAreaList{};
    std::optional<IEEmergencyNumberList> emergencyNumberList{};
    std::optional<IEOperatorDefinedAccessCategoryDefinitions> operatorDefinedAccessCategoryDefinitions{};
    std::optional<IELadnInformation> ladnInformation{};
    std::optional<IESorTransparentContainer> sorTransparentContainer{};
    std::optional<IEExtendedEmergencyNumberList> extendedEmergencyNumberList{};

    RegistrationAccept();
    void onBuild(NasMessageBuilder &b);
};

struct RegistrationComplete : PlainMmMessage
{
    std::optional<IESorTransparentContainer> sorTransparentContainer{};

    RegistrationComplete();
    void onBuild(NasMessageBuilder &b);
};

struct RegistrationReject : PlainMmMessage
{
    IE5gMmCause mmCause{};
    std::optional<IEGprsTimer2> t3346value{};
    std::optional<IEGprsTimer2> t3502value{};
    std::optional<IEEapMessage> eapMessage{};

    RegistrationReject();
    void onBuild(NasMessageBuilder &b);
};

struct RegistrationRequest : PlainMmMessage
{
    IE5gsRegistrationType registrationType{};
    IENasKeySetIdentifier nasKeySetIdentifier{};
    IE5gsMobileIdentity mobileIdentity{};
    std::optional<IENasKeySetIdentifier> nonCurrentNgKsi{};
    std::optional<IEMicoIndication> micoIndication{};
    std::optional<IENetworkSlicingIndication> networkSlicingIndication{};
    std::optional<IEUeSecurityCapability> ueSecurityCapability{};
    std::optional<IE5gMmCapability> mmCapability{};
    std::optional<IENssai> requestedNSSAI{};
    std::optional<IE5gsMobileIdentity> additionalGuti{};
    std::optional<IE5gsDrxParameters> requestedDrxParameters{};
    std::optional<IEUesUsageSetting> uesUsageSetting{};
    std::optional<IE5gsUpdateType> updateType{};
    std::optional<IEUeStatus> ueStatus{};
    std::optional<IEUplinkDataStatus> uplinkDataStatus{};
    std::optional<IEEpsNasMessageContainer> epsNasMessageContainer{};
    std::optional<IENasMessageContainer> nasMessageContainer{};
    std::optional<IEAllowedPduSessionStatus> allowedPduSessionStatus{};
    std::optional<IE5gsTrackingAreaIdentity> lastVisitedRegisteredTai{};
    std::optional<IES1UeNetworkCapability> s1UeNetworkCapability{};
    std::optional<IEPduSessionStatus> pduSessionStatus{};
    std::optional<IEPayloadContainer> payloadContainer{};
    std::optional<IELadnIndication> ladnIndication{};

    RegistrationRequest();
    void onBuild(NasMessageBuilder &b);
};

struct SecurityModeCommand : PlainMmMessage
{
    IENasSecurityAlgorithms selectedNasSecurityAlgorithms{};
    IENasKeySetIdentifier ngKsi{};
    IEUeSecurityCapability replayedUeSecurityCapabilities{};
    std::optional<IEImeiSvRequest> imeiSvRequest{};
    std::optional<IEEpsNasSecurityAlgorithms> epsNasSecurityAlgorithms{};
    std::optional<IEAdditional5gSecurityInformation> additional5gSecurityInformation{};
    std::optional<IEEapMessage> eapMessage{};
    std::optional<IEAbba> abba{};
    std::optional<IES1UeNetworkCapability> replayedS1UeNetworkCapability{};

    octet4 _macForNewSC{};
    OctetString _originalPlainNasPdu{};

    SecurityModeCommand();
    void onBuild(NasMessageBuilder &b);
};

struct SecurityModeComplete : PlainMmMessage
{
    std::optional<IE5gsMobileIdentity> imeiSv{};
    std::optional<IENasMessageContainer> nasMessageContainer{};

    SecurityModeComplete();
    void onBuild(NasMessageBuilder &b);
};

struct SecurityModeReject : PlainMmMessage
{
    IE5gMmCause mmCause{};

    SecurityModeReject();
    void onBuild(NasMessageBuilder &b);
};

struct ServiceAccept : PlainMmMessage
{
    std::optional<IEPduSessionStatus> pduSessionStatus{};
    std::optional<IEPduSessionReactivationResult> pduSessionReactivationResult{};
    std::optional<IEPduSessionReactivationResultErrorCause> pduSessionReactivationResultErrorCause{};
    std::optional<IEEapMessage> eapMessage{};

    ServiceAccept();
    void onBuild(NasMessageBuilder &b);
};

struct ServiceReject : PlainMmMessage
{
    IE5gMmCause mmCause{};
    std::optional<IEPduSessionStatus> pduSessionStatus{};
    std::optional<IEGprsTimer2> t3346Value{};
    std::optional<IEEapMessage> eapMessage{};

    ServiceReject();
    void onBuild(NasMessageBuilder &b);
};

struct ServiceRequest : PlainMmMessage
{
    IENasKeySetIdentifier ngKSI{};
    IEServiceType serviceType{};
    IE5gsMobileIdentity tmsi{};
    std::optional<IEUplinkDataStatus> uplinkDataStatus{};
    std::optional<IEPduSessionStatus> pduSessionStatus{};
    std::optional<IEAllowedPduSessionStatus> allowedPduSessionStatus{};
    std::optional<IENasMessageContainer> nasMessageContainer{};

    ServiceRequest();
    void onBuild(NasMessageBuilder &b);
};

struct UlNasTransport : PlainMmMessage
{
    IEPayloadContainerType payloadContainerType{};
    IEPayloadContainer payloadContainer{};
    std::optional<IEPduSessionIdentity2> pduSessionId{};
    std::optional<IEPduSessionIdentity2> oldPduSessionId{};
    std::optional<IERequestType> requestType{};
    std::optional<IESNssai> sNssai{};
    std::optional<IEDnn> dnn{};
    std::optional<IEAdditionalInformation> additionalInformation{};

    UlNasTransport();
    void onBuild(NasMessageBuilder &b);
};

} // namespace nas
