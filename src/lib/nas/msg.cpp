//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "msg.hpp"

// fuzzing
#include "nas_mutator.hpp"

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

// fuzzing
void AuthenticationFailure::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&mmCause);
        break;
    case 1:
        m.optionalIE(0x30, &authenticationFailureParameter);
        break;
    default:
        break;
    }
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

void AuthenticationReject::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.optionalIE(0x78, &eapMessage);
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

void AuthenticationRequest::onMutate(NasMessageMutator &m)
{
    int i = generate_int(6);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&ngKSI);
        break;
    case 1:
        m.mandatoryIE(&abba);
        break;
    case 2:
        m.optionalIE(0x21, &authParamRAND);
        break;
    case 3:
        m.optionalIE(0x20, &authParamAUTN);
        break;
    case 4:
        m.optionalIE(0x78, &eapMessage);
        break;
    default:
        break;
    }
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

void AuthenticationResponse::onMutate(NasMessageMutator &m)
{
    // TODO: not mutate

    // int i = generate_int(3);
    // switch (i)
    // {
    // case 0:
    //     m.optionalIE(0x2D, &authenticationResponseParameter);
    //     break;
    // case 1:
    //     m.optionalIE(0x78, &eapMessage);
    //     break;
    // default:
    //     break;
    // }
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

void AuthenticationResult::onMutate(NasMessageMutator &m)
{
    int i = generate_int(4);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&ngKSI);
        break;
    case 1:
        m.mandatoryIE(&eapMessage);
        break;
    case 2:
        m.optionalIE(0x38, &abba);
        break;
    default:
        break;
    }
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

void ConfigurationUpdateCommand::onMutate(NasMessageMutator &m)
{
    int i = generate_int(18);
    switch (i)
    {
    case 0:
        m.optionalIE1(0xD, &configurationUpdateIndication);
        break;
    case 1:
        m.optionalIE(0x77, &guti);
        break;
    case 2:
        m.optionalIE(0x54, &taiList);
        break;
    case 3:
        m.optionalIE(0x15, &allowedNssai);
        break;
    case 4:
        m.optionalIE(0x27, &serviceAreaList);
        break;
    case 5:
        m.optionalIE(0x43, &networkFullName);
        break;
    case 6:
        m.optionalIE(0x45, &networkShortName);
        break;
    case 7:
        m.optionalIE(0x46, &localTimeZone);
        break;
    case 8:
        m.optionalIE(0x47, &universalTimeAndLocalTimeZone);
        break;
    case 9:
        m.optionalIE(0x49, &networkDaylightSavingTime);
        break;
    case 10:
        m.optionalIE(0x79, &ladnInformation);
        break;
    case 11:
        m.optionalIE1(0xB, &micoIndication);
        break;
    case 12:
        m.optionalIE1(0x9, &networkSlicingIndication);
        break;
    case 13:
        m.optionalIE(0x31, &configuredNssai);
        break;
    case 14:
        m.optionalIE(0x11, &rejectedNssai);
        break;
    case 15:
        m.optionalIE(0x76, &operatorDefinedAccessCategoryDefinitions);
        break;
    case 16:
        m.optionalIE1(0xF, &smsIndication);
        break;
    default:
        break;
    }  
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

void ConfigurationUpdateComplete::onMutate(NasMessageMutator &m)
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

void DeRegistrationAcceptUeOriginating::onMutate(NasMessageMutator &m)
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

void DeRegistrationAcceptUeTerminated::onMutate(NasMessageMutator &m)
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

void DeRegistrationRequestUeOriginating::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    printf("Mutate DeRegistrationRequestUeOriginating, i = %d\n", i);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&ngKSI, &deRegistrationType);
        break;
    case 1:
        m.mandatoryIE(&mobileIdentity);
        break;
    default:
        break;
    }
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

void DeRegistrationRequestUeTerminated::onMutate(NasMessageMutator &m)
{
    int i = generate_int(4);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&deRegistrationType);
        break;
    case 1:
        m.optionalIE(0x58, &mmCause);
        break;
    case 2:
        m.optionalIE(0x5F, &t3346Value);
        break;
    default:
        break;
    }
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

void DlNasTransport::onMutate(NasMessageMutator &m)
{
    int i = generate_int(7);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&payloadContainerType);
        break;
    case 1:
        m.mandatoryIE(&payloadContainer);
        break;
    case 2:
        m.optionalIE(0x12, &pduSessionId);
        break;
    case 3:
        m.optionalIE(0x24, &additionalInformation);
        break;
    case 4:
        m.optionalIE(0x58, &mmCause);
        break;
    case 5:
        m.optionalIE(0x37, &backOffTimerValue);
        break;
    default:
        break;
    }
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

void FiveGMmStatus::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.mandatoryIE(&mmCause);
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

void FiveGSmStatus::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.mandatoryIE(&smCause);
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

void IdentityRequest::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.mandatoryIE1(&identityType);
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

void IdentityResponse::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.mandatoryIE(&mobileIdentity);
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

void Notification::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.mandatoryIE1(&accessType);
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

void NotificationResponse::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.optionalIE(0x50, &pduSessionStatus);
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

void PduSessionAuthenticationCommand::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&eapMessage);
        break;
    case 1:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionAuthenticationComplete::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&eapMessage);
        break;
    case 1:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionAuthenticationResult::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.optionalIE(0x78, &eapMessage);
        break;
    case 1:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionEstablishmentAccept::onMutate(NasMessageMutator &m)
{
    int i = generate_int(14);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&selectedSscMode, &selectedPduSessionType);
        break;
    case 1:
        m.mandatoryIE(&authorizedQoSRules);
        break;
    case 2:
        m.mandatoryIE(&sessionAmbr);
        break;
    case 3:
        m.optionalIE(0x59, &smCause);
        break;
    case 4:
        m.optionalIE(0x29, &pduAddress);
        break;
    case 5:
        m.optionalIE(0x56, &rqTimerValue);
        break;
    case 6:
        m.optionalIE(0x22, &sNssai);
        break;
    case 7:
        m.optionalIE1(0x8, &alwaysOnPduSessionIndication);
        break;
    case 8:
        m.optionalIE(0x7F, &mappedEpsBearerContexts);
        break;
    case 9:
        m.optionalIE(0x78, &eapMessage);
        break;
    case 10:
        m.optionalIE(0x79, &authorizedQoSFlowDescriptions);
        break;
    case 11:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    case 12:
        m.optionalIE(0x25, &dnn);
        break;
    default:
        break;
    }
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

void PduSessionEstablishmentReject::onMutate(NasMessageMutator &m)
{
    int i = generate_int(6);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&smCause);
        break;
    case 1:
        m.optionalIE(0x37, &backOffTimerValue);
        break;
    case 2:
        m.optionalIE1(0xF, &allowedSscMode);
        break;
    case 3:
        m.optionalIE(0x78, &eapMessage);
        break;
    case 4:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionEstablishmentRequest::onMutate(NasMessageMutator &m)
{
    int i = generate_int(9);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&integrityProtectionMaximumDataRate);
        break;
    case 1:
        m.optionalIE1(0x9, &pduSessionType);
        break;
    case 2:
        m.optionalIE1(0xA, &sscMode);
        break;
    case 3:
        m.optionalIE(0x28, &smCapability);
        break;
    case 4:
        m.optionalIE(0x55, &maximumNumberOfSupportedPacketFilters);
        break;
    case 5:
        m.optionalIE1(0xB, &alwaysOnPduSessionRequested);
        break;
    case 6:
        m.optionalIE(0x39, &smPduDnRequestContainer);
        break;
    case 7: 
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionModificationCommand::onMutate(NasMessageMutator &m)
{
    int i = generate_int(9);
    switch (i)
    {
    case 0:
        m.optionalIE(0x59, &smCause);
        break;
    case 1:
        m.optionalIE(0x2A, &sessionAmbr);
        break;
    case 2:
        m.optionalIE(0x56, &rqTimerValue);
        break;
    case 3:
        m.optionalIE1(0x8, &alwaysOnPduSessionIndication);
        break;
    case 4:
        m.optionalIE(0x7A, &authorizedQoSRules);
        break;
    case 5:
        m.optionalIE(0x7F, &mappedEpsBearerContexts);
        break;
    case 6:
        m.optionalIE(0x79, &authorizedQoSFlowDescriptions);
        break;
    case 7:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionModificationCommandReject::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&smCause);
        break;
    case 1:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionModificationComplete::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
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

void PduSessionModificationReject::onMutate(NasMessageMutator &m)
{
    int i = generate_int(4);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&smCause);
        break;
    case 1:
        m.optionalIE(0x37, &backOffTimerValue);
        break;
    case 2:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionModificationRequest::onMutate(NasMessageMutator &m)
{
    int i = generate_int(10);
    switch (i)
    {
    case 0:
        m.optionalIE(0x28, &smCapability);
        break;
    case 1:
        m.optionalIE(0x59, &smCause);
        break;
    case 2:
        m.optionalIE(0x55, &maximumNumberOfSupportedPacketFilters);
        break;
    case 3:
        m.optionalIE1(0xB, &alwaysOnPduSessionRequested);
        break;
    case 4:
        m.optionalIE(0x13, &integrityProtectionMaximumDataRate);
        break;
    case 5:
        m.optionalIE(0x7A, &requestedQosRules);
        break;
    case 6:
        m.optionalIE(0x79, &requestedQosFlowDescriptions);
        break;
    case 7:
        m.optionalIE(0x7F, &mappedEpsBearerContexts);
        break;
    case 8:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionReleaseCommand::onMutate(NasMessageMutator &m)
{
    int i = generate_int(5);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&smCause);
        break;
    case 1:
        m.optionalIE(0x37, &backOffTimerValue);
        break;
    case 2:
        m.optionalIE(0x78, &eapMessage);
        break;
    case 3:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionReleaseComplete::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.optionalIE(0x59, &smCause);
        break;
    case 1:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionReleaseReject::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&smCause);
        break;
    case 1:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void PduSessionReleaseRequest::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.optionalIE(0x59, &smCause);
        break;
    case 1:
        m.optionalIE(0x7B, &extendedProtocolConfigurationOptions);
        break;
    default:
        break;
    }
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

void RegistrationAccept::onMutate(NasMessageMutator &m)
{
    int i = generate_int(26);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&registrationResult);
        break;
    case 1:
        m.optionalIE1(0x9, &networkSlicingIndication);
        break;
    case 2:
        m.optionalIE1(0xA, &nssaiInclusionMode);
        break;
    case 3:
        m.optionalIE1(0xB, &micoIndication);
        break;
    case 4:
        m.optionalIE(0x77, &mobileIdentity);
        break;
    case 5:
        m.optionalIE(0x4A, &equivalentPLMNs);
        break;
    case 6:
        m.optionalIE(0x54, &taiList);
        break;
    case 7:
        m.optionalIE(0x15, &allowedNSSAI);
        break;
    case 8:
        m.optionalIE(0x11, &rejectedNSSAI);
        break;
    case 9:
        m.optionalIE(0x31, &configuredNSSAI);
        break;
    case 10:
        m.optionalIE(0x21, &networkFeatureSupport);
        break;
    case 11:
        m.optionalIE(0x50, &pduSessionStatus);
        break;
    case 12:
        m.optionalIE(0x26, &pduSessionReactivationResult);
        break;
    case 13:
        m.optionalIE(0x72, &pduSessionReactivationResultErrorCause);
        break;
    case 14:
        m.optionalIE(0x79, &ladnInformation);
        break;
    case 15:
        m.optionalIE(0x27, &serviceAreaList);
        break;
    case 16:
        m.optionalIE(0x5E, &t3512Value);
        break;
    case 17:
        m.optionalIE(0x5D, &non3gppDeRegistrationTimerValue);
        break;
    case 18:
        m.optionalIE(0x16, &t3502Value);
        break;
    case 19:
        m.optionalIE(0x34, &emergencyNumberList);
        break;
    case 20:
        m.optionalIE(0x7A, &extendedEmergencyNumberList);
        break;
    case 21:
        m.optionalIE(0x73, &sorTransparentContainer);
        break;
    case 22:
        m.optionalIE(0x78, &eapMessage);
        break;
    case 23:
        m.optionalIE(0x76, &operatorDefinedAccessCategoryDefinitions);
        break;
    case 24:
        m.optionalIE(0x51, &negotiatedDrxParameters);
        break;
    default:
        break;
    }
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

void RegistrationComplete::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.optionalIE(0x73, &sorTransparentContainer);
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

void RegistrationReject::onMutate(NasMessageMutator &m)
{
    int i = generate_int(5);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&mmCause);
        break;
    case 1:
        m.optionalIE(0x5F, &t3346value);
        break;
    case 2:
        m.optionalIE(0x16, &t3502value);
        break;
    case 3:
        m.optionalIE(0x78, &eapMessage);
        break;
    default:
        break;
    }
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

void RegistrationRequest::onMutate(NasMessageMutator &m)
{
    int i = generate_int(23);
    printf("mutate RegistrationRequest, i = %d\n", i);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&nasKeySetIdentifier, &registrationType);
        break;
    case 1:
        m.mandatoryIE(&mobileIdentity);
        break;
    case 2:
        m.optionalIE1(0xC, &nonCurrentNgKsi);
        break;
    case 3:
        m.optionalIE1(0xB, &micoIndication);
        break;
    case 4:
        m.optionalIE1(0x9, &networkSlicingIndication);
        break;
    case 5:
        m.optionalIE(0x10, &mmCapability);
        break;
    case 6:
        m.optionalIE(0x2E, &ueSecurityCapability);
        break;
    case 7:
        m.optionalIE(0x2F, &requestedNSSAI);
        break;
    case 8:
        m.optionalIE(0x52, &lastVisitedRegisteredTai);
        break;
    case 9:
        m.optionalIE(0x17, &s1UeNetworkCapability);
        break;
    case 10:
        m.optionalIE(0x40, &uplinkDataStatus);
        break;
    case 11:
        m.optionalIE(0x50, &pduSessionStatus);
        break;
    case 12:
        m.optionalIE(0x2B, &ueStatus);
        break;
    case 13:
        m.optionalIE(0x77, &additionalGuti);
        break;
    case 14:
        m.optionalIE(0x25, &allowedPduSessionStatus);
        break;
    case 15:
        m.optionalIE(0x18, &uesUsageSetting);
        break;
    case 16:
        m.optionalIE(0x51, &requestedDrxParameters);
        break;
    case 17:
        m.optionalIE(0x70, &epsNasMessageContainer);
        break;
    case 18:
        m.optionalIE(0x7E, &ladnIndication);
        break;
    case 19:
        m.optionalIE(0x7B, &payloadContainer);
        break;
    case 20:
        m.optionalIE(0x53, &updateType);
        break;
    case 21:
        m.optionalIE(0x71, &nasMessageContainer);
        break;
    default:
        break;
    }
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

void SecurityModeCommand::onMutate(NasMessageMutator &m)
{
    int i = generate_int(10);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&selectedNasSecurityAlgorithms);
        break;
    case 1:
        m.mandatoryIE1(&ngKsi);
        break;
    case 2:
        m.mandatoryIE(&replayedUeSecurityCapabilities);
        break;
    case 3:
        m.optionalIE1(0xE, &imeiSvRequest);
        break;
    case 4:
        m.optionalIE(0x57, &epsNasSecurityAlgorithms);
        break;
    case 5:
        m.optionalIE(0x36, &additional5gSecurityInformation);
        break;
    case 6:
        m.optionalIE(0x78, &eapMessage);
        break;
    case 7:
        m.optionalIE(0x38, &abba);
        break;
    case 8:
        m.optionalIE(0x19, &replayedS1UeNetworkCapability);
        break;
    default:
        break;
    }
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

void SecurityModeComplete::onMutate(NasMessageMutator &m)
{
    int i = generate_int(3);
    switch (i)
    {
    case 0:
        m.optionalIE(0x77, &imeiSv);
        break;
    case 1:
        m.optionalIE(0x71, &nasMessageContainer);
        break;
    default:
        break;
    }
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

void SecurityModeReject::onMutate(NasMessageMutator &m)
{
    if (generate_bit(1))
        m.mandatoryIE(&mmCause);
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

void ServiceAccept::onMutate(NasMessageMutator &m)
{  
    int i = generate_int(5);
    switch (i)
    {
    case 0:
        m.optionalIE(0x50, &pduSessionStatus);
        break;
    case 1:
        m.optionalIE(0x26, &pduSessionReactivationResult);
        break;
    case 2:
        m.optionalIE(0x72, &pduSessionReactivationResultErrorCause);
        break;
    case 3:
        m.optionalIE(0x78, &eapMessage);
        break;
    default:
        break;
    }
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

void ServiceReject::onMutate(NasMessageMutator &m)
{
    int i = generate_int(5);
    switch (i)
    {
    case 0:
        m.mandatoryIE(&mmCause);
        break;
    case 1:
        m.optionalIE(0x50, &pduSessionStatus);
        break;
    case 2:
        m.optionalIE(0x5f, &t3346Value);
        break;
    case 3:
        m.optionalIE(0x78, &eapMessage);
        break;
    default:
        break;
    }
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

void ServiceRequest::onMutate(NasMessageMutator &m)
{
    int i = generate_int(7);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&serviceType, &ngKSI);
        break;
    case 1:
        m.mandatoryIE(&tmsi);
        break;
    case 2:
        m.optionalIE(0x40, &uplinkDataStatus);
        break;
    case 3:
        m.optionalIE(0x50, &pduSessionStatus);
        break;
    case 4:
        m.optionalIE(0x25, &allowedPduSessionStatus);
        break;
    case 5:
        m.optionalIE(0x71, &nasMessageContainer);
        break;
    default:
        break;
    }
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
    b.optionalIE(0x22, &sNssai);
    b.optionalIE(0x25, &dnn);
    b.optionalIE(0x24, &additionalInformation);
}

void UlNasTransport::onMutate(NasMessageMutator &m)
{
    int i = generate_int(9);
    switch (i)
    {
    case 0:
        m.mandatoryIE1(&payloadContainerType);
        break;
    case 1:
        m.mandatoryIE(&payloadContainer);
        break;
    case 2:
        m.optionalIE(0x12, &pduSessionId);
        break;
    case 3:
        m.optionalIE(0x59, &oldPduSessionId);
        break;
    case 4:
        m.optionalIE1(0x8, &requestType);
        break;
    case 5:
        m.optionalIE(0x22, &sNssai);
        break;
    case 6:
        m.optionalIE(0x25, &dnn);
        break;
    case 7:
        m.optionalIE(0x24, &additionalInformation);
        break;
    default:
        break;
    }
}

} // namespace nas
