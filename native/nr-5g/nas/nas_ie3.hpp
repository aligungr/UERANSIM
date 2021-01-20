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

struct IEIntegrityProtectionMaximumDataRate : InformationElement3<IEIntegrityProtectionMaximumDataRate>
{
    EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink{};
    EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink{};

    IEIntegrityProtectionMaximumDataRate() = default;

    IEIntegrityProtectionMaximumDataRate(
        EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink,
        EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink)
        : maxRateUplink(maxRateUplink), maxRateDownlink(maxRateDownlink)
    {
    }

    IEIntegrityProtectionMaximumDataRate decodeIE3(OctetBuffer &stream) override
    {
        IEIntegrityProtectionMaximumDataRate r;
        r.maxRateUplink = static_cast<EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink>(stream.readI());
        r.maxRateDownlink =
            static_cast<EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink>(stream.readI());
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.appendOctet(static_cast<int>(maxRateUplink));
        stream.appendOctet(static_cast<int>(maxRateDownlink));
    }
};

struct IEMaximumNumberOfSupportedPacketFilters : InformationElement3<IEMaximumNumberOfSupportedPacketFilters>
{
    int value : 11;

    IEMaximumNumberOfSupportedPacketFilters() : value(0)
    {
    }

    explicit IEMaximumNumberOfSupportedPacketFilters(int value) : value(value)
    {
    }

    IEMaximumNumberOfSupportedPacketFilters decodeIE3(OctetBuffer &stream) override
    {
        int v = stream.read2I();
        v >>= 5;

        IEMaximumNumberOfSupportedPacketFilters r;
        r.value = v;
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.appendOctet((value >> 3) & 0xFF);
        stream.appendOctet((value & 0b111) << 5);
    }
};

struct IEN1ModeToS1ModeNasTransparentContainer : InformationElement3<IEN1ModeToS1ModeNasTransparentContainer>
{
    octet sequenceNumber{};

    IEN1ModeToS1ModeNasTransparentContainer() = default;

    explicit IEN1ModeToS1ModeNasTransparentContainer(uint8_t value) : sequenceNumber(value)
    {
    }

    IEN1ModeToS1ModeNasTransparentContainer decodeIE3(OctetBuffer &stream) override
    {
        IEN1ModeToS1ModeNasTransparentContainer r;
        r.sequenceNumber = stream.read();
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.appendOctet(sequenceNumber);
    }
};

struct IENasSecurityAlgorithms : InformationElement3<IENasSecurityAlgorithms>
{
    ETypeOfIntegrityProtectionAlgorithm integrity{};
    ETypeOfCipheringAlgorithm ciphering{};

    IENasSecurityAlgorithms() = default;

    IENasSecurityAlgorithms(ETypeOfIntegrityProtectionAlgorithm integrity, ETypeOfCipheringAlgorithm ciphering)
        : integrity(integrity), ciphering(ciphering)
    {
    }

    IENasSecurityAlgorithms decodeIE3(OctetBuffer &stream) override
    {
        IENasSecurityAlgorithms r;
        r.integrity = static_cast<ETypeOfIntegrityProtectionAlgorithm>(stream.peekI() & 0xF);
        r.ciphering = static_cast<ETypeOfCipheringAlgorithm>((stream.readI() >> 4) & 0xF);
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.appendOctet(static_cast<int>(ciphering), static_cast<int>(integrity));
    }
};

struct IEPduSessionIdentity2 : InformationElement3<IEPduSessionIdentity2>
{
    octet value{};

    IEPduSessionIdentity2() = default;

    explicit IEPduSessionIdentity2(uint8_t value) : value(value)
    {
    }

    IEPduSessionIdentity2 decodeIE3(OctetBuffer &stream) override
    {
        IEPduSessionIdentity2 r;
        r.value = stream.read();
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.appendOctet(value);
    }
};

struct IETimeZone : InformationElement3<IETimeZone>
{
    octet value{};

    IETimeZone() = default;

    explicit IETimeZone(uint8_t value) : value(value)
    {
    }

    IETimeZone decodeIE3(OctetBuffer &stream) override
    {
        IETimeZone r;
        r.value = stream.read();
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        stream.appendOctet(value);
    }
};

struct IETimeZoneAndTime : InformationElement3<IETimeZoneAndTime>
{
    VTime time;
    octet timezone{};

    IETimeZoneAndTime() = default;

    IETimeZoneAndTime(VTime time, octet timezone) : time(time), timezone(timezone)
    {
    }

    IETimeZoneAndTime decodeIE3(OctetBuffer &stream) override
    {
        IETimeZoneAndTime r;
        r.time = VTime::Decode(stream);
        r.timezone = stream.read();
        return r;
    }

    void encodeIE3(OctetString &stream) override
    {
        VTime::Encode(time, stream);
        stream.appendOctet(timezone);
    }
};

} // namespace nas