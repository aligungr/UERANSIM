//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <octet_buffer.hpp>
#include <octet_string.hpp>

#include "nas_enums.hpp"

namespace nas
{

template <typename T>
struct InformationElement
{
    virtual T decodeIE(OctetBuffer &stream) = 0;
};

template <typename T>
struct InformationElement1 : InformationElement<T>
{
    virtual T decodeIE1(int value) = 0;
    virtual int encodeIE1() = 0;

    T decodeIE(OctetBuffer &stream) final
    {
        int octet = stream.readI();
        int iei = octet >> 4 & 0xF;
        int value = octet & 0xF;
        return decodeIE1(value);
    }
};

struct IE5gsIdentityType : InformationElement1<IE5gsIdentityType>
{
    EIdentityType value{};

    IE5gsIdentityType() = default;

    explicit IE5gsIdentityType(EIdentityType value) : value(value)
    {
    }

    IE5gsIdentityType decodeIE1(int val) override
    {
        IE5gsIdentityType res;
        bits::Bmp4Dec13(val, nullptr, &res.value);
        return res;
    }

    int encodeIE1() override
    {
        return (int)value;
    }
};

struct IE5gsRegistrationType : InformationElement1<IE5gsRegistrationType>
{
    EFollowOnRequest followOnRequestPending{};
    ERegistrationType registrationType{};

    IE5gsRegistrationType() = default;

    IE5gsRegistrationType(EFollowOnRequest followOnRequestPending, ERegistrationType registrationType)
        : followOnRequestPending(followOnRequestPending), registrationType(registrationType)
    {
    }

    IE5gsRegistrationType decodeIE1(int val) override
    {
        IE5gsRegistrationType res;
        bits::Bmp4Dec13(val, &res.followOnRequestPending, &res.registrationType);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc13(followOnRequestPending, registrationType);
    }
};

struct IEAccessType : InformationElement1<IEAccessType>
{
    EAccessType value{};

    IEAccessType() = default;

    explicit IEAccessType(EAccessType value) : value(value)
    {
    }

    IEAccessType decodeIE1(int val) override
    {
        IEAccessType res;
        bits::Bmp4Dec22(val, nullptr, &res.value);
        return res;
    }

    int encodeIE1() override
    {
        return (int)value;
    }
};

struct IEAllowedSscMode : InformationElement1<IEAllowedSscMode>
{
    ESsc1 ssc1{};
    ESsc2 ssc2{};
    ESsc3 ssc3{};

    IEAllowedSscMode() = default;

    IEAllowedSscMode(ESsc1 ssc1, ESsc2 ssc2, ESsc3 ssc3) : ssc1(ssc1), ssc2(ssc2), ssc3(ssc3)
    {
    }

    IEAllowedSscMode decodeIE1(int val) override
    {
        IEAllowedSscMode res;
        bits::Bmp4Dec1111(val, nullptr, &res.ssc3, &res.ssc2, &res.ssc1);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc1111(0, ssc3, ssc2, ssc1);
    }
};

struct IEAlwaysOnPduSessionIndication : InformationElement1<IEAlwaysOnPduSessionIndication>
{
    EAlwaysOnPduSessionIndication value{};

    IEAlwaysOnPduSessionIndication() = default;

    explicit IEAlwaysOnPduSessionIndication(EAlwaysOnPduSessionIndication value) : value(value)
    {
    }

    IEAlwaysOnPduSessionIndication decodeIE1(int val) override
    {
        IEAlwaysOnPduSessionIndication res;
        bits::Bmp4Dec31(val, nullptr, &res.value);
        return res;
    }

    int encodeIE1() override
    {
        return (int)value;
    }
};

struct IEAlwaysOnPduSessionRequested : InformationElement1<IEAlwaysOnPduSessionRequested>
{
    EAlwaysOnPduSessionRequested value{};

    IEAlwaysOnPduSessionRequested() = default;

    explicit IEAlwaysOnPduSessionRequested(EAlwaysOnPduSessionRequested value) : value(value)
    {
    }

    IEAlwaysOnPduSessionRequested decodeIE1(int val) override
    {
        IEAlwaysOnPduSessionRequested res;
        bits::Bmp4Dec31(val, nullptr, &res.value);
        return res;
    }

    int encodeIE1() override
    {
        return (int)value;
    }
};

struct IEConfigurationUpdateIndication : InformationElement1<IEConfigurationUpdateIndication>
{
    EAcknowledgement ack{};
    ERegistrationRequested red{};

    IEConfigurationUpdateIndication() = default;

    IEConfigurationUpdateIndication(EAcknowledgement ack, ERegistrationRequested red) : ack(ack), red(red)
    {
    }

    IEConfigurationUpdateIndication decodeIE1(int val) override
    {
        IEConfigurationUpdateIndication res;
        bits::Bmp4Dec211(val, nullptr, &res.red, &res.ack);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc211(0, red, ack);
    }
};

struct IEDeRegistrationType : InformationElement1<IEDeRegistrationType>
{
    EDeRegistrationAccessType accessType{};
    EReRegistrationRequired reRegistrationRequired{};
    ESwitchOff switchOff{};

    IEDeRegistrationType() = default;

    IEDeRegistrationType(EDeRegistrationAccessType accessType, EReRegistrationRequired reRegistrationRequired,
                         ESwitchOff switchOff)
        : accessType(accessType), reRegistrationRequired(reRegistrationRequired), switchOff(switchOff)
    {
    }

    IEDeRegistrationType decodeIE1(int val) override
    {
        IEDeRegistrationType res;
        bits::Bmp4Dec112(val, &res.switchOff, &res.reRegistrationRequired, &res.accessType);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc112(switchOff, reRegistrationRequired, accessType);
    }
};

struct IEImeiSvRequest : InformationElement1<IEImeiSvRequest>
{
    EImeiSvRequest imeiSvRequest{};

    IEImeiSvRequest() = default;

    explicit IEImeiSvRequest(EImeiSvRequest imeiSvRequest) : imeiSvRequest(imeiSvRequest)
    {
    }

    IEImeiSvRequest decodeIE1(int val) override
    {
        IEImeiSvRequest res;
        bits::Bmp4Dec13(val, nullptr, &res.imeiSvRequest);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc13(0, imeiSvRequest);
    }
};

struct IEMicoIndication : InformationElement1<IEMicoIndication>
{
    ERegistrationAreaAllocationIndication raai{};

    IEMicoIndication() = default;

    explicit IEMicoIndication(ERegistrationAreaAllocationIndication raai) : raai(raai)
    {
    }

    IEMicoIndication decodeIE1(int val) override
    {
        IEMicoIndication res;
        bits::Bmp4Dec31(val, nullptr, &res.raai);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc31(0, raai);
    }
};

struct IENasKeySetIdentifier : InformationElement1<IENasKeySetIdentifier>
{
    ETypeOfSecurityContext tsc{};
    int ksi{};

    IENasKeySetIdentifier() = default;

    IENasKeySetIdentifier(ETypeOfSecurityContext tsc, int ksi) : tsc(tsc), ksi(ksi)
    {
    }

    IENasKeySetIdentifier decodeIE1(int val) override
    {
        IENasKeySetIdentifier res;
        bits::Bmp4Dec13(val, &res.tsc, &res.ksi);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc13(tsc, ksi);
    }
};

struct IENetworkSlicingIndication : InformationElement1<IENetworkSlicingIndication>
{
    ENetworkSlicingSubscriptionChangeIndication nssci{};
    EDefaultConfiguredNssaiIndication dcni{};

    IENetworkSlicingIndication() = default;

    IENetworkSlicingIndication(ENetworkSlicingSubscriptionChangeIndication nssci,
                               EDefaultConfiguredNssaiIndication dcni)
        : nssci(nssci), dcni(dcni)
    {
    }

    IENetworkSlicingIndication decodeIE1(int val) override
    {
        IENetworkSlicingIndication res;
        bits::Bmp4Dec211(val, nullptr, &res.dcni, &res.nssci);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc211(0, dcni, nssci);
    }
};

struct IENssaiInclusionMode : InformationElement1<IENssaiInclusionMode>
{
    ENssaiInclusionMode nssaiInclusionMode{};

    IENssaiInclusionMode() = default;

    explicit IENssaiInclusionMode(ENssaiInclusionMode nssaiInclusionMode) : nssaiInclusionMode(nssaiInclusionMode)
    {
    }

    IENssaiInclusionMode decodeIE1(int val) override
    {
        IENssaiInclusionMode res;
        bits::Bmp4Dec22(val, nullptr, &res.nssaiInclusionMode);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc22(0, nssaiInclusionMode);
    }
};

struct IEPayloadContainerType : InformationElement1<IEPayloadContainerType>
{
    EPayloadContainerType payloadContainerType{};

    IEPayloadContainerType() = default;

    explicit IEPayloadContainerType(EPayloadContainerType payloadContainerType)
        : payloadContainerType(payloadContainerType)
    {
    }

    IEPayloadContainerType decodeIE1(int val) override
    {
        IEPayloadContainerType res;
        bits::Bmp4Dec4(val, &res.payloadContainerType);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc4(payloadContainerType);
    }
};

struct IEPduSessionType : InformationElement1<IEPduSessionType>
{
    EPduSessionType pduSessionType{};

    IEPduSessionType() = default;

    explicit IEPduSessionType(EPduSessionType pduSessionType) : pduSessionType(pduSessionType)
    {
    }

    IEPduSessionType decodeIE1(int val) override
    {
        IEPduSessionType res;
        bits::Bmp4Dec13(val, nullptr, &res.pduSessionType);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc13(0, pduSessionType);
    }
};

struct IERequestType : InformationElement1<IERequestType>
{
    ERequestType requestType{};

    IERequestType() = default;

    explicit IERequestType(ERequestType requestType) : requestType(requestType)
    {
    }

    IERequestType decodeIE1(int val) override
    {
        IERequestType res;
        bits::Bmp4Dec13(val, nullptr, &res.requestType);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc13(0, requestType);
    }
};

struct IEServiceType : InformationElement1<IEServiceType>
{
    EServiceType serviceType{};

    IEServiceType() = default;

    explicit IEServiceType(EServiceType serviceType) : serviceType(serviceType)
    {
    }

    IEServiceType decodeIE1(int val) override
    {
        IEServiceType res;
        bits::Bmp4Dec4(val, &res.serviceType);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc4(serviceType);
    }
};

struct IESmsIndication : InformationElement1<IESmsIndication>
{
    ESmsAvailabilityIndication sai{};

    IESmsIndication() = default;

    explicit IESmsIndication(ESmsAvailabilityIndication sai) : sai(sai)
    {
    }

    IESmsIndication decodeIE1(int val) override
    {
        IESmsIndication res;
        bits::Bmp4Dec31(val, nullptr, &res.sai);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc31(0, sai);
    }
};

struct IESscMode : InformationElement1<IESscMode>
{
    ESscMode sscMode{};

    IESscMode() = default;

    explicit IESscMode(ESscMode sscMode) : sscMode(sscMode)
    {
    }

    IESscMode decodeIE1(int val) override
    {
        IESscMode res;
        bits::Bmp4Dec13(val, nullptr, &res.sscMode);
        return res;
    }

    int encodeIE1() override
    {
        return bits::Bmp4Enc13(0, sscMode);
    }
};

} // namespace nas