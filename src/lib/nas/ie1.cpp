//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ie1.hpp"

namespace nas
{

IE5gsIdentityType::IE5gsIdentityType(EIdentityType value) : value(value)
{
}

IE5gsIdentityType IE5gsIdentityType::Decode(int val)
{
    IE5gsIdentityType res;
    bits::Bmp4Dec13(val, nullptr, &res.value);
    return res;
}

int IE5gsIdentityType::Encode(const IE5gsIdentityType &ie)
{
    return (int)ie.value;
}

IE5gsRegistrationType::IE5gsRegistrationType(EFollowOnRequest followOnRequestPending,
                                             ERegistrationType registrationType)
    : followOnRequestPending(followOnRequestPending), registrationType(registrationType)
{
}

IE5gsRegistrationType IE5gsRegistrationType::Decode(int val)
{
    IE5gsRegistrationType res;
    bits::Bmp4Dec13(val, &res.followOnRequestPending, &res.registrationType);
    return res;
}

int IE5gsRegistrationType::Encode(const IE5gsRegistrationType &ie)
{
    return bits::Bmp4Enc13(ie.followOnRequestPending, ie.registrationType);
}

IEAccessType::IEAccessType(EAccessType value) : value(value)
{
}

IEAccessType IEAccessType::Decode(int val)
{
    IEAccessType res;
    bits::Bmp4Dec22(val, nullptr, &res.value);
    return res;
}

int IEAccessType::Encode(const IEAccessType &ie)
{
    return (int)ie.value;
}

IEAllowedSscMode::IEAllowedSscMode(ESsc1 ssc1, ESsc2 ssc2, ESsc3 ssc3) : ssc1(ssc1), ssc2(ssc2), ssc3(ssc3)
{
}

IEAllowedSscMode IEAllowedSscMode::Decode(int val)
{
    IEAllowedSscMode res;
    bits::Bmp4Dec1111(val, nullptr, &res.ssc3, &res.ssc2, &res.ssc1);
    return res;
}

int IEAllowedSscMode::Encode(const IEAllowedSscMode &ie)
{
    return bits::Bmp4Enc1111(0, ie.ssc3, ie.ssc2, ie.ssc1);
}

IEAlwaysOnPduSessionIndication::IEAlwaysOnPduSessionIndication(EAlwaysOnPduSessionIndication value) : value(value)
{
}

IEAlwaysOnPduSessionIndication IEAlwaysOnPduSessionIndication::Decode(int val)
{
    IEAlwaysOnPduSessionIndication res;
    bits::Bmp4Dec31(val, nullptr, &res.value);
    return res;
}

int IEAlwaysOnPduSessionIndication::Encode(const IEAlwaysOnPduSessionIndication &ie)
{
    return (int)ie.value;
}

IEAlwaysOnPduSessionRequested::IEAlwaysOnPduSessionRequested(EAlwaysOnPduSessionRequested value) : value(value)
{
}

IEAlwaysOnPduSessionRequested IEAlwaysOnPduSessionRequested::Decode(int val)
{
    IEAlwaysOnPduSessionRequested res;
    bits::Bmp4Dec31(val, nullptr, &res.value);
    return res;
}

int IEAlwaysOnPduSessionRequested::Encode(const IEAlwaysOnPduSessionRequested &ie)
{
    return (int)ie.value;
}

IEConfigurationUpdateIndication::IEConfigurationUpdateIndication(EAcknowledgement ack, ERegistrationRequested red)
    : ack(ack), red(red)
{
}

IEConfigurationUpdateIndication IEConfigurationUpdateIndication::Decode(int val)
{
    IEConfigurationUpdateIndication res;
    bits::Bmp4Dec211(val, nullptr, &res.red, &res.ack);
    return res;
}

int IEConfigurationUpdateIndication::Encode(const IEConfigurationUpdateIndication &ie)
{
    return bits::Bmp4Enc211(0, ie.red, ie.ack);
}

IEDeRegistrationType::IEDeRegistrationType(EDeRegistrationAccessType accessType,
                                           EReRegistrationRequired reRegistrationRequired, ESwitchOff switchOff)
    : accessType(accessType), reRegistrationRequired(reRegistrationRequired), switchOff(switchOff)
{
}

IEDeRegistrationType IEDeRegistrationType::Decode(int val)
{
    IEDeRegistrationType res;
    bits::Bmp4Dec112(val, &res.switchOff, &res.reRegistrationRequired, &res.accessType);
    return res;
}

int IEDeRegistrationType::Encode(const IEDeRegistrationType &ie)
{
    return bits::Bmp4Enc112(ie.switchOff, ie.reRegistrationRequired, ie.accessType);
}

IEImeiSvRequest::IEImeiSvRequest(EImeiSvRequest imeiSvRequest) : imeiSvRequest(imeiSvRequest)
{
}

IEImeiSvRequest IEImeiSvRequest::Decode(int val)
{
    IEImeiSvRequest res;
    bits::Bmp4Dec13(val, nullptr, &res.imeiSvRequest);
    return res;
}

int IEImeiSvRequest::Encode(const IEImeiSvRequest &ie)
{
    return bits::Bmp4Enc13(0, ie.imeiSvRequest);
}

IEMicoIndication::IEMicoIndication(ERegistrationAreaAllocationIndication raai) : raai(raai)
{
}

IEMicoIndication IEMicoIndication::Decode(int val)
{
    IEMicoIndication res;
    bits::Bmp4Dec31(val, nullptr, &res.raai);
    return res;
}

int IEMicoIndication::Encode(const IEMicoIndication &ie)
{
    return bits::Bmp4Enc31(0, ie.raai);
}

IENasKeySetIdentifier::IENasKeySetIdentifier(ETypeOfSecurityContext tsc, int ksi) : tsc(tsc), ksi(ksi)
{
}

IENasKeySetIdentifier IENasKeySetIdentifier::Decode(int val)
{
    IENasKeySetIdentifier res;
    bits::Bmp4Dec13(val, &res.tsc, &res.ksi);
    return res;
}

int IENasKeySetIdentifier::Encode(const IENasKeySetIdentifier &ie)
{
    return bits::Bmp4Enc13(ie.tsc, ie.ksi);
}

IENetworkSlicingIndication::IENetworkSlicingIndication(ENetworkSlicingSubscriptionChangeIndication nssci,
                                                       EDefaultConfiguredNssaiIndication dcni)
    : nssci(nssci), dcni(dcni)
{
}

IENetworkSlicingIndication IENetworkSlicingIndication::Decode(int val)
{
    IENetworkSlicingIndication res;
    bits::Bmp4Dec211(val, nullptr, &res.dcni, &res.nssci);
    return res;
}

int IENetworkSlicingIndication::Encode(const IENetworkSlicingIndication &ie)
{
    return bits::Bmp4Enc211(0, ie.dcni, ie.nssci);
}

IENssaiInclusionMode::IENssaiInclusionMode(ENssaiInclusionMode nssaiInclusionMode)
    : nssaiInclusionMode(nssaiInclusionMode)
{
}

IENssaiInclusionMode IENssaiInclusionMode::Decode(int val)
{
    IENssaiInclusionMode res;
    bits::Bmp4Dec22(val, nullptr, &res.nssaiInclusionMode);
    return res;
}

int IENssaiInclusionMode::Encode(const IENssaiInclusionMode &ie)
{
    return bits::Bmp4Enc22(0, ie.nssaiInclusionMode);
}

IEPayloadContainerType::IEPayloadContainerType(EPayloadContainerType payloadContainerType)
    : payloadContainerType(payloadContainerType)
{
}

IEPayloadContainerType IEPayloadContainerType::Decode(int val)
{
    IEPayloadContainerType res;
    bits::Bmp4Dec4(val, &res.payloadContainerType);
    return res;
}

int IEPayloadContainerType::Encode(const IEPayloadContainerType &ie)
{
    return bits::Bmp4Enc4(ie.payloadContainerType);
}

IEPduSessionType::IEPduSessionType(EPduSessionType pduSessionType) : pduSessionType(pduSessionType)
{
}

IEPduSessionType IEPduSessionType::Decode(int val)
{
    IEPduSessionType res;
    bits::Bmp4Dec13(val, nullptr, &res.pduSessionType);
    return res;
}

int IEPduSessionType::Encode(const IEPduSessionType &ie)
{
    return bits::Bmp4Enc13(0, ie.pduSessionType);
}

IERequestType::IERequestType(ERequestType requestType) : requestType(requestType)
{
}

IERequestType IERequestType::Decode(int val)
{
    IERequestType res;
    bits::Bmp4Dec13(val, nullptr, &res.requestType);
    return res;
}

int IERequestType::Encode(const IERequestType &ie)
{
    return bits::Bmp4Enc13(0, ie.requestType);
}

IEServiceType::IEServiceType(EServiceType serviceType) : serviceType(serviceType)
{
}

IEServiceType IEServiceType::Decode(int val)
{
    IEServiceType res;
    bits::Bmp4Dec4(val, &res.serviceType);
    return res;
}

int IEServiceType::Encode(const IEServiceType &ie)
{
    return bits::Bmp4Enc4(ie.serviceType);
}

IESmsIndication::IESmsIndication(ESmsAvailabilityIndication sai) : sai(sai)
{
}

IESmsIndication IESmsIndication::Decode(int val)
{
    IESmsIndication res;
    bits::Bmp4Dec31(val, nullptr, &res.sai);
    return res;
}

int IESmsIndication::Encode(const IESmsIndication &ie)
{
    return bits::Bmp4Enc31(0, ie.sai);
}

IESscMode::IESscMode(ESscMode sscMode) : sscMode(sscMode)
{
}

IESscMode IESscMode::Decode(int val)
{
    IESscMode res;
    bits::Bmp4Dec13(val, nullptr, &res.sscMode);
    return res;
}

int IESscMode::Encode(const IESscMode &ie)
{
    return bits::Bmp4Enc13(0, ie.sscMode);
}

} // namespace nas
