//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "nas_ie1.hpp"
#include "nas_values.hpp"

namespace nas
{

template <typename T>
struct InformationElement3 : InformationElement<T>
{
    virtual T decodeIE3(OctetBuffer &stream) = 0;
    virtual void encodeIE3(OctetString &stream) = 0;

    T decodeIE(OctetBuffer &stream) final
    {
        return decodeIE3(stream);
    }
};

struct IE5gMmCause : InformationElement3<IE5gMmCause>
{
    EMmCause value{};

    IE5gMmCause() = default;

    explicit IE5gMmCause(EMmCause value) : value(value)
    {
    }

    IE5gMmCause decodeIE3(OctetBuffer &stream) override
    {
        IE5gMmCause r;
        r.value = static_cast<EMmCause>(stream.readI());
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.appendOctet(static_cast<int>(value));
    }
};

struct IE5gsTrackingAreaIdentity : InformationElement3<IE5gsTrackingAreaIdentity>
{
    int mcc{};
    int mnc{};
    bool isLongMnc{};
    octet3 trackingAreaCode{};

    IE5gsTrackingAreaIdentity() = default;

    IE5gsTrackingAreaIdentity(int mcc, int mnc, bool isLongMnc, const octet3 &trackingAreaCode)
        : mcc(mcc), mnc(mnc), isLongMnc(isLongMnc), trackingAreaCode(trackingAreaCode)
    {
    }

    IE5gsTrackingAreaIdentity decodeIE3(OctetBuffer &stream) override
    {
        auto plmn = VPlmn::Decode(stream);

        IE5gsTrackingAreaIdentity r;
        r.mcc = plmn.mcc;
        r.mnc = plmn.mnc;
        r.isLongMnc = plmn.isLongMnc;
        r.trackingAreaCode = stream.read3();
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        VPlmn plmn{mcc, mnc, isLongMnc};
        VPlmn::Encode(plmn, stream);
        stream.appendOctet3(trackingAreaCode);
    }
};

struct IEAuthenticationParameterRand : InformationElement3<IEAuthenticationParameterRand>
{
    OctetString value;

    IEAuthenticationParameterRand() = default;

    explicit IEAuthenticationParameterRand(OctetString &&value) : value(std::move(value))
    {
    }

    IEAuthenticationParameterRand decodeIE3(OctetBuffer &stream) override
    {
        return IEAuthenticationParameterRand{stream.readOctetString(16)};
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.append(value);
    }
};

struct IEEpsNasSecurityAlgorithms : InformationElement3<IEEpsNasSecurityAlgorithms>
{
    EEpsTypeOfIntegrityProtectionAlgorithm integrity{};
    EEpsTypeOfCipheringAlgorithm ciphering{};

    IEEpsNasSecurityAlgorithms() = default;

    IEEpsNasSecurityAlgorithms(EEpsTypeOfIntegrityProtectionAlgorithm integrity, EEpsTypeOfCipheringAlgorithm ciphering)
        : integrity(integrity), ciphering(ciphering)
    {
    }

    IEEpsNasSecurityAlgorithms decodeIE3(OctetBuffer &stream) override
    {
        IEEpsNasSecurityAlgorithms r;
        r.integrity = static_cast<EEpsTypeOfIntegrityProtectionAlgorithm>(stream.peekI() & 0b111);
        r.ciphering = static_cast<EEpsTypeOfCipheringAlgorithm>((stream.readI() >> 4) & 0b111);
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.appendOctet(static_cast<int>(ciphering), static_cast<int>(integrity));
    }
};

struct IEGprsTimer : InformationElement3<IEGprsTimer>
{
    int timerValue : 5;
    EGprsTimerValueUnit timerValueUnit;

    IEGprsTimer() : timerValue(0), timerValueUnit()
    {
    }

    IEGprsTimer(int timerValue, EGprsTimerValueUnit timerValueUnit)
        : timerValue(timerValue), timerValueUnit(timerValueUnit)
    {
    }

    IEGprsTimer decodeIE3(OctetBuffer &stream) override
    {
        int octet = stream.readI();
        IEGprsTimer r;
        r.timerValue = bits::BitRange8<0, 4>(octet);
        r.timerValueUnit = static_cast<EGprsTimerValueUnit>(bits::BitAt<5>(octet));
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        uint8_t octet = 0;
        bits::SetBitRange8<0, 4>(octet, timerValue);
        bits::SetBitAt<5>(octet, static_cast<int>(timerValueUnit));
        stream.appendOctet(octet);
    }
};

// todo: kalanlar
//  IEIntegrityProtectionMaximumDataRate
//  IEMaximumNumberOfSupportedPacketFilters
//  IEN1ModeToS1ModeNasTransparentContainer
//  IENasSecurityAlgorithms
//  IEPduSessionIdentity2
//  IETimeZone
//  IETimeZoneAndTime

} // namespace nas