//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "base.hpp"
#include "enums.hpp"

#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

namespace nas
{

struct IE5gsIdentityType : InformationElement1
{
    EIdentityType value{};

    IE5gsIdentityType() = default;
    explicit IE5gsIdentityType(EIdentityType value);

    static IE5gsIdentityType Decode(int val);
    static int Encode(const IE5gsIdentityType &ie);
};

struct IE5gsRegistrationType : InformationElement1
{
    EFollowOnRequest followOnRequestPending{};
    ERegistrationType registrationType{};

    IE5gsRegistrationType() = default;
    IE5gsRegistrationType(EFollowOnRequest followOnRequestPending, ERegistrationType registrationType);

    static IE5gsRegistrationType Decode(int val);
    static int Encode(const IE5gsRegistrationType &ie);
};

struct IEAccessType : InformationElement1
{
    EAccessType value{};

    IEAccessType() = default;
    explicit IEAccessType(EAccessType value);

    static IEAccessType Decode(int val);
    static int Encode(const IEAccessType &ie);
};

struct IEAllowedSscMode : InformationElement1
{
    ESsc1 ssc1{};
    ESsc2 ssc2{};
    ESsc3 ssc3{};

    IEAllowedSscMode() = default;
    IEAllowedSscMode(ESsc1 ssc1, ESsc2 ssc2, ESsc3 ssc3);

    static IEAllowedSscMode Decode(int val);
    static int Encode(const IEAllowedSscMode &ie);
};

struct IEAlwaysOnPduSessionIndication : InformationElement1
{
    EAlwaysOnPduSessionIndication value{};

    IEAlwaysOnPduSessionIndication() = default;
    explicit IEAlwaysOnPduSessionIndication(EAlwaysOnPduSessionIndication value);

    static IEAlwaysOnPduSessionIndication Decode(int val);
    static int Encode(const IEAlwaysOnPduSessionIndication &ie);
};

struct IEAlwaysOnPduSessionRequested : InformationElement1
{
    EAlwaysOnPduSessionRequested value{};

    IEAlwaysOnPduSessionRequested() = default;
    explicit IEAlwaysOnPduSessionRequested(EAlwaysOnPduSessionRequested value);

    static IEAlwaysOnPduSessionRequested Decode(int val);
    static int Encode(const IEAlwaysOnPduSessionRequested &ie);
};

struct IEConfigurationUpdateIndication : InformationElement1
{
    EAcknowledgement ack{};
    ERegistrationRequested red{};

    IEConfigurationUpdateIndication() = default;
    IEConfigurationUpdateIndication(EAcknowledgement ack, ERegistrationRequested red);

    static IEConfigurationUpdateIndication Decode(int val);
    static int Encode(const IEConfigurationUpdateIndication &ie);
};

struct IEDeRegistrationType : InformationElement1
{
    EDeRegistrationAccessType accessType{};
    EReRegistrationRequired reRegistrationRequired{}; // This bit is spare in UE to Network direction
    ESwitchOff switchOff{};

    IEDeRegistrationType() = default;
    IEDeRegistrationType(EDeRegistrationAccessType accessType, EReRegistrationRequired reRegistrationRequired,
                         ESwitchOff switchOff);

    static IEDeRegistrationType Decode(int val);
    static int Encode(const IEDeRegistrationType &ie);
};

struct IEImeiSvRequest : InformationElement1
{
    EImeiSvRequest imeiSvRequest{};

    IEImeiSvRequest() = default;
    explicit IEImeiSvRequest(EImeiSvRequest imeiSvRequest);

    static IEImeiSvRequest Decode(int val);
    static int Encode(const IEImeiSvRequest &ie);
};

struct IEMicoIndication : InformationElement1
{
    ERegistrationAreaAllocationIndication raai{};

    IEMicoIndication() = default;
    explicit IEMicoIndication(ERegistrationAreaAllocationIndication raai);

    static IEMicoIndication Decode(int val);
    static int Encode(const IEMicoIndication &ie);
};

struct IENasKeySetIdentifier : InformationElement1
{
    static constexpr const int NOT_AVAILABLE_OR_RESERVED = 0b111;

    ETypeOfSecurityContext tsc{};
    int ksi = NOT_AVAILABLE_OR_RESERVED;

    IENasKeySetIdentifier() = default;
    IENasKeySetIdentifier(ETypeOfSecurityContext tsc, int ksi);

    static IENasKeySetIdentifier Decode(int val);
    static int Encode(const IENasKeySetIdentifier &ie);
};

struct IENetworkSlicingIndication : InformationElement1
{
    ENetworkSlicingSubscriptionChangeIndication nssci{}; // This is spare if dir is UE->NW
    EDefaultConfiguredNssaiIndication dcni{};            // This is spare if dir is NW->UE

    IENetworkSlicingIndication() = default;
    IENetworkSlicingIndication(ENetworkSlicingSubscriptionChangeIndication nssci,
                               EDefaultConfiguredNssaiIndication dcni);

    static IENetworkSlicingIndication Decode(int val);
    static int Encode(const IENetworkSlicingIndication &ie);
};

struct IENssaiInclusionMode : InformationElement1
{
    ENssaiInclusionMode nssaiInclusionMode{};

    IENssaiInclusionMode() = default;
    explicit IENssaiInclusionMode(ENssaiInclusionMode nssaiInclusionMode);

    static IENssaiInclusionMode Decode(int val);
    static int Encode(const IENssaiInclusionMode &ie);
};

struct IEPayloadContainerType : InformationElement1
{
    EPayloadContainerType payloadContainerType{};

    IEPayloadContainerType() = default;
    explicit IEPayloadContainerType(EPayloadContainerType payloadContainerType);

    static IEPayloadContainerType Decode(int val);
    static int Encode(const IEPayloadContainerType &ie);
};

struct IEPduSessionType : InformationElement1
{
    EPduSessionType pduSessionType{};

    IEPduSessionType() = default;
    explicit IEPduSessionType(EPduSessionType pduSessionType);

    static IEPduSessionType Decode(int val);
    static int Encode(const IEPduSessionType &ie);
};

struct IERequestType : InformationElement1
{
    ERequestType requestType{};

    IERequestType() = default;
    explicit IERequestType(ERequestType requestType);

    static IERequestType Decode(int val);
    static int Encode(const IERequestType &ie);
};

struct IEServiceType : InformationElement1
{
    EServiceType serviceType{};

    IEServiceType() = default;
    explicit IEServiceType(EServiceType serviceType);

    static IEServiceType Decode(int val);
    static int Encode(const IEServiceType &ie);
};

struct IESmsIndication : InformationElement1
{
    ESmsAvailabilityIndication sai{};

    IESmsIndication() = default;
    explicit IESmsIndication(ESmsAvailabilityIndication sai);

    static IESmsIndication Decode(int val);
    static int Encode(const IESmsIndication &ie);
};

struct IESscMode : InformationElement1
{
    ESscMode sscMode{};

    IESscMode() = default;
    explicit IESscMode(ESscMode sscMode);

    static IESscMode Decode(int val);
    static int Encode(const IESscMode &ie);
};

} // namespace nas