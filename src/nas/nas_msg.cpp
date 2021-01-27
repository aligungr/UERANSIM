//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "nas_msg.hpp"

namespace nas
{

AuthenticationFailure::AuthenticationFailure()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::AUTHENTICATION_FAILURE;
}

void AuthenticationFailure::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&mmCause);
    b.optionalIE(0x30, &authenticationFailureParameter);
}

AuthenticationReject::AuthenticationReject()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::AUTHENTICATION_REJECT;
}

void AuthenticationReject::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x78, &eapMessage);
}

AuthenticationRequest::AuthenticationRequest()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::AUTHENTICATION_REQUEST;
}

void AuthenticationRequest::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&ngKSI);
    b.mandatoryIE(&abba);
    b.optionalIE(0x21, &authParamRAND);
    b.optionalIE(0x20, &authParamAUTN);
    b.optionalIE(0x78, &eapMessage);
}

AuthenticationResponse::AuthenticationResponse()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::AUTHENTICATION_RESPONSE;
}

void AuthenticationResponse::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x2D, &authenticationResponseParameter);
    b.optionalIE(0x78, &eapMessage);
}

AuthenticationResult::AuthenticationResult()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::AUTHENTICATION_RESULT;
}

void AuthenticationResult::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&ngKSI);
    b.mandatoryIE(&eapMessage);
    b.optionalIE(0x38, &abba);
}

ConfigurationUpdateCommand::ConfigurationUpdateCommand()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::CONFIGURATION_UPDATE_COMMAND;
}

void ConfigurationUpdateCommand::onBuild(NasMessageBuilder &b)
{
    b.optionalIE1(0xD, &configurationUpdateIndication);
    b.optionalIE(0x77, &guti);
    b.optionalIE(0x54, &taiList);
    b.optionalIE(0x15, &allowedNssai);
    b.optionalIE(0x27, &serviceAreaList);
    b.optionalIE(0x43, &networkFullName);
    b.optionalIE(0x45, &networkShortName);
    b.optionalIE(0x46, &localTimeZone);
    b.optionalIE(0x47, &universalTimeAndLocalTimeZone);
    b.optionalIE(0x49, &networkDaylightSavingTime);
    b.optionalIE(0x79, &ladnInformation);
    b.optionalIE1(0xB, &micoIndication);
    b.optionalIE1(0x9, &networkSlicingIndication);
    b.optionalIE(0x31, &configuredNssai);
    b.optionalIE(0x11, &rejectedNssai);
    b.optionalIE(0x76, &operatorDefinedAccessCategoryDefinitions);
    b.optionalIE1(0xF, &smsIndication);
}

ConfigurationUpdateComplete::ConfigurationUpdateComplete()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::CONFIGURATION_UPDATE_COMPLETE;
}

void ConfigurationUpdateComplete::onBuild(NasMessageBuilder &b)
{
}

DeRegistrationAcceptUeOriginating::DeRegistrationAcceptUeOriginating()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::DEREGISTRATION_ACCEPT_UE_ORIGINATING;
}

void DeRegistrationAcceptUeOriginating::onBuild(NasMessageBuilder &b)
{
}

DeRegistrationAcceptUeTerminated::DeRegistrationAcceptUeTerminated()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::DEREGISTRATION_ACCEPT_UE_TERMINATED;
}

void DeRegistrationAcceptUeTerminated::onBuild(NasMessageBuilder &b)
{
}

DeRegistrationRequestUeOriginating::DeRegistrationRequestUeOriginating()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::DEREGISTRATION_REQUEST_UE_ORIGINATING;
}

void DeRegistrationRequestUeOriginating::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&ngKSI, &deRegistrationType);
    b.mandatoryIE(&mobileIdentity);
}

DeRegistrationRequestUeTerminated::DeRegistrationRequestUeTerminated()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::DEREGISTRATION_REQUEST_UE_TERMINATED;
}

void DeRegistrationRequestUeTerminated::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&deRegistrationType);
    b.optionalIE(0x58, &mmCause);
    b.optionalIE(0x5F, &t3346Value);
}

DlNasTransport::DlNasTransport()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::DL_NAS_TRANSPORT;
}

void DlNasTransport::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&payloadContainerType);
    b.mandatoryIE(&payloadContainer);
    b.optionalIE(0x12, &pduSessionId);
    b.optionalIE(0x24, &additionalInformation);
    b.optionalIE(0x58, &mmCause);
    b.optionalIE(0x37, &backOffTimerValue);
}

FiveGMmStatus::FiveGMmStatus()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::FIVEG_MM_STATUS;
}

void FiveGMmStatus::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&mmCause);
}

FiveGSmStatus::FiveGSmStatus()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::FIVEG_SM_STATUS;
}

void FiveGSmStatus::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&smCause);
}

IdentityRequest::IdentityRequest()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::IDENTITY_REQUEST;
}

void IdentityRequest::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&identityType);
}

IdentityResponse::IdentityResponse()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::IDENTITY_RESPONSE;
}

void IdentityResponse::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&mobileIdentity);
}

Notification::Notification()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::NOTIFICATION;
}

void Notification::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&accessType);
}

NotificationResponse::NotificationResponse()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::NOTIFICATION_RESPONSE;
}

void NotificationResponse::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x50, &pduSessionStatus);
}

PduSessionAuthenticationCommand::PduSessionAuthenticationCommand()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_AUTHENTICATION_COMMAND;
}

void PduSessionAuthenticationCommand::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&eapMessage);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionAuthenticationComplete::PduSessionAuthenticationComplete()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_AUTHENTICATION_COMPLETE;
}

void PduSessionAuthenticationComplete::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&eapMessage);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionAuthenticationResult::PduSessionAuthenticationResult()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_AUTHENTICATION_RESULT;
}

void PduSessionAuthenticationResult::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x78, &eapMessage);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionEstablishmentAccept::PduSessionEstablishmentAccept()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_ESTABLISHMENT_ACCEPT;
}

void PduSessionEstablishmentAccept::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&selectedSscMode, &selectedPduSessionType);
    b.mandatoryIE(&authorizedQoSRules);
    b.mandatoryIE(&sessionAmbr);
    b.optionalIE(0x59, &smCause);
    b.optionalIE(0x29, &pduAddress);
    b.optionalIE(0x56, &rqTimerValue);
    b.optionalIE(0x22, &sNssai);
    b.optionalIE1(0x8, &alwaysOnPduSessionIndication);
    b.optionalIE(0x7F, &mappedEpsBearerContexts);
    b.optionalIE(0x78, &eapMessage);
    b.optionalIE(0x79, &authorizedQoSFlowDescriptions);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
    b.optionalIE(0x25, &dnn);
}

PduSessionEstablishmentReject::PduSessionEstablishmentReject()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_ESTABLISHMENT_REJECT;
}

void PduSessionEstablishmentReject::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&smCause);
    b.optionalIE(0x37, &backOffTimerValue);
    b.optionalIE1(0xF, &allowedSscMode);
    b.optionalIE(0x78, &eapMessage);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionEstablishmentRequest::PduSessionEstablishmentRequest()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST;
}

void PduSessionEstablishmentRequest::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&integrityProtectionMaximumDataRate);
    b.optionalIE1(0x9, &pduSessionType);
    b.optionalIE1(0xA, &sscMode);
    b.optionalIE(0x28, &smCapability);
    b.optionalIE(0x55, &maximumNumberOfSupportedPacketFilters);
    b.optionalIE1(0xB, &alwaysOnPduSessionRequested);
    b.optionalIE(0x39, &smPduDnRequestContainer);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionModificationCommand::PduSessionModificationCommand()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_MODIFICATION_COMMAND;
}

void PduSessionModificationCommand::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x59, &smCause);
    b.optionalIE(0x2A, &sessionAmbr);
    b.optionalIE(0x56, &rqTimerValue);
    b.optionalIE1(0x8, &alwaysOnPduSessionIndication);
    b.optionalIE(0x7A, &authorizedQoSRules);
    b.optionalIE(0x7F, &mappedEpsBearerContexts);
    b.optionalIE(0x79, &authorizedQoSFlowDescriptions);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionModificationCommandReject::PduSessionModificationCommandReject()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_MODIFICATION_COMMAND_REJECT;
}

void PduSessionModificationCommandReject::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&smCause);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionModificationComplete::PduSessionModificationComplete()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_MODIFICATION_COMPLETE;
}

void PduSessionModificationComplete::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionModificationReject::PduSessionModificationReject()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_MODIFICATION_REJECT;
}

void PduSessionModificationReject::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&smCause);
    b.optionalIE(0x37, &backOffTimerValue);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionModificationRequest::PduSessionModificationRequest()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_MODIFICATION_REQUEST;
}

void PduSessionModificationRequest::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x28, &smCapability);
    b.optionalIE(0x59, &smCause);
    b.optionalIE(0x55, &maximumNumberOfSupportedPacketFilters);
    b.optionalIE1(0xB, &alwaysOnPduSessionRequested);
    b.optionalIE(0x13, &integrityProtectionMaximumDataRate);
    b.optionalIE(0x7A, &requestedQosRules);
    b.optionalIE(0x79, &requestedQosFlowDescriptions);
    b.optionalIE(0x7F, &mappedEpsBearerContexts);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionReleaseCommand::PduSessionReleaseCommand()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_RELEASE_COMMAND;
}

void PduSessionReleaseCommand::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&smCause);
    b.optionalIE(0x37, &backOffTimerValue);
    b.optionalIE(0x78, &eapMessage);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionReleaseComplete::PduSessionReleaseComplete()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_RELEASE_COMPLETE;
}

void PduSessionReleaseComplete::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x59, &smCause);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionReleaseReject::PduSessionReleaseReject()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_RELEASE_REJECT;
}

void PduSessionReleaseReject::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&smCause);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

PduSessionReleaseRequest::PduSessionReleaseRequest()
{
    epd = EExtendedProtocolDiscriminator::SESSION_MANAGEMENT_MESSAGES;
    messageType = EMessageType::PDU_SESSION_RELEASE_REQUEST;
}

void PduSessionReleaseRequest::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x59, &smCause);
    b.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
}

RegistrationAccept::RegistrationAccept()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::REGISTRATION_ACCEPT;
}

void RegistrationAccept::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&registrationResult);
    b.optionalIE1(0x9, &networkSlicingIndication);
    b.optionalIE1(0xA, &nssaiInclusionMode);
    b.optionalIE1(0xB, &micoIndication);
    b.optionalIE(0x77, &mobileIdentity);
    b.optionalIE(0x4A, &equivalentPLMNs);
    b.optionalIE(0x54, &taiList);
    b.optionalIE(0x15, &allowedNSSAI);
    b.optionalIE(0x11, &rejectedNSSAI);
    b.optionalIE(0x31, &configuredNSSAI);
    b.optionalIE(0x21, &networkFeatureSupport);
    b.optionalIE(0x50, &pduSessionStatus);
    b.optionalIE(0x26, &pduSessionReactivationResult);
    b.optionalIE(0x72, &pduSessionReactivationResultErrorCause);
    b.optionalIE(0x79, &ladnInformation);
    b.optionalIE(0x27, &serviceAreaList);
    b.optionalIE(0x5E, &t3512Value);
    b.optionalIE(0x5D, &non3gppDeRegistrationTimerValue);
    b.optionalIE(0x16, &t3502Value);
    b.optionalIE(0x34, &emergencyNumberList);
    b.optionalIE(0x7A, &extendedEmergencyNumberList);
    b.optionalIE(0x73, &sorTransparentContainer);
    b.optionalIE(0x78, &eapMessage);
    b.optionalIE(0x76, &operatorDefinedAccessCategoryDefinitions);
    b.optionalIE(0x51, &negotiatedDrxParameters);
}

RegistrationComplete::RegistrationComplete()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::REGISTRATION_COMPLETE;
}

void RegistrationComplete::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x73, &sorTransparentContainer);
}

RegistrationReject::RegistrationReject()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::REGISTRATION_REJECT;
}

void RegistrationReject::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&mmCause);
    b.optionalIE(0x5F, &t3346value);
    b.optionalIE(0x16, &t3502value);
    b.optionalIE(0x78, &eapMessage);
}

RegistrationRequest::RegistrationRequest()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::REGISTRATION_REQUEST;
}

void RegistrationRequest::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&nasKeySetIdentifier, &registrationType);
    b.mandatoryIE(&mobileIdentity);
    b.optionalIE1(0xC, &nonCurrentNgKsi);
    b.optionalIE1(0xB, &micoIndication);
    b.optionalIE1(0x9, &networkSlicingIndication);
    b.optionalIE(0x10, &mmCapability);
    b.optionalIE(0x2E, &ueSecurityCapability);
    b.optionalIE(0x2F, &requestedNSSAI);
    b.optionalIE(0x52, &lastVisitedRegisteredTai);
    b.optionalIE(0x17, &s1UeNetworkCapability);
    b.optionalIE(0x40, &uplinkDataStatus);
    b.optionalIE(0x50, &pduSessionStatus);
    b.optionalIE(0x2B, &ueStatus);
    b.optionalIE(0x77, &additionalGuti);
    b.optionalIE(0x25, &allowedPduSessionStatus);
    b.optionalIE(0x18, &uesUsageSetting);
    b.optionalIE(0x51, &requestedDrxParameters);
    b.optionalIE(0x70, &epsNasMessageContainer);
    b.optionalIE(0x7E, &ladnIndication);
    b.optionalIE(0x7B, &payloadContainer);
    b.optionalIE(0x53, &updateType);
    b.optionalIE(0x71, &nasMessageContainer);
}

SecurityModeCommand::SecurityModeCommand()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::SECURITY_MODE_COMMAND;
}

void SecurityModeCommand::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&selectedNasSecurityAlgorithms);
    b.mandatoryIE1(&ngKsi);
    b.mandatoryIE(&replayedUeSecurityCapabilities);
    b.optionalIE1(0xE, &imeiSvRequest);
    b.optionalIE(0x57, &epsNasSecurityAlgorithms);
    b.optionalIE(0x36, &additional5gSecurityInformation);
    b.optionalIE(0x78, &eapMessage);
    b.optionalIE(0x38, &abba);
    b.optionalIE(0x19, &replayedS1UeNetworkCapability);
}

SecurityModeComplete::SecurityModeComplete()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::SECURITY_MODE_COMPLETE;
}

void SecurityModeComplete::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x77, &imeiSv);
    b.optionalIE(0x71, &nasMessageContainer);
}

SecurityModeReject::SecurityModeReject()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::SECURITY_MODE_REJECT;
}

void SecurityModeReject::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&mmCause);
}

ServiceAccept::ServiceAccept()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::SERVICE_ACCEPT;
}

void ServiceAccept::onBuild(NasMessageBuilder &b)
{
    b.optionalIE(0x50, &pduSessionStatus);
    b.optionalIE(0x26, &pduSessionReactivationResult);
    b.optionalIE(0x72, &pduSessionReactivationResultErrorCause);
    b.optionalIE(0x78, &eapMessage);
}

ServiceReject::ServiceReject()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::SERVICE_REJECT;
}

void ServiceReject::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE(&mmCause);
    b.optionalIE(0x50, &pduSessionStatus);
    b.optionalIE(0x5f, &t3346Value);
    b.optionalIE(0x78, &eapMessage);
}

ServiceRequest::ServiceRequest()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::SERVICE_REQUEST;
}

void ServiceRequest::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&serviceType, &ngKSI);
    b.mandatoryIE(&tmsi);
    b.optionalIE(0x40, &uplinkDataStatus);
    b.optionalIE(0x50, &pduSessionStatus);
    b.optionalIE(0x25, &allowedPduSessionStatus);
    b.optionalIE(0x71, &nasMessageContainer);
}

UlNasTransport::UlNasTransport()
{
    epd = EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    sht = ESecurityHeaderType::NOT_PROTECTED;
    messageType = EMessageType::UL_NAS_TRANSPORT;
}

void UlNasTransport::onBuild(NasMessageBuilder &b)
{
    b.mandatoryIE1(&payloadContainerType);
    b.mandatoryIE(&payloadContainer);
    b.optionalIE(0x12, &pduSessionId);
    b.optionalIE(0x59, &oldPduSessionId);
    b.optionalIE1(0x8, &requestType);
    b.optionalIE(0x22, &sNssa);
    b.optionalIE(0x25, &dnn);
    b.optionalIE(0x24, &additionalInformation);
}

} // namespace nas
