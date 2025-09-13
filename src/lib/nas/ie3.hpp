//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "base.hpp"
#include "values.hpp"

namespace nas
{

struct IE5gMmCause : InformationElement3
{
    EMmCause value{};

    IE5gMmCause() = default;
    explicit IE5gMmCause(EMmCause value);

    static IE5gMmCause Decode(const OctetView &stream);
    static void Encode(const IE5gMmCause &ie, OctetString &stream);
    static void Mutate(IE5gMmCause &ie);
};

struct IE5gsTrackingAreaIdentity : InformationElement3
{
    int mcc{};
    int mnc{};
    bool isLongMnc{};
    octet3 trackingAreaCode{};

    IE5gsTrackingAreaIdentity() = default;
    IE5gsTrackingAreaIdentity(int mcc, int mnc, bool isLongMnc, const octet3 &trackingAreaCode);
    explicit IE5gsTrackingAreaIdentity(const Tai &tai);

    static IE5gsTrackingAreaIdentity Decode(const OctetView &stream);
    static void Encode(const IE5gsTrackingAreaIdentity &ie, OctetString &stream);
    static void Mutate(IE5gsTrackingAreaIdentity &ie);
};

struct IEAuthenticationParameterRand : InformationElement3
{
    OctetString value;

    IEAuthenticationParameterRand() = default;
    explicit IEAuthenticationParameterRand(OctetString &&value);

    static IEAuthenticationParameterRand Decode(const OctetView &stream);
    static void Encode(const IEAuthenticationParameterRand &ie, OctetString &stream);
    static void Mutate(IEAuthenticationParameterRand &ie);
};

struct IEEpsNasSecurityAlgorithms : InformationElement3
{
    EEpsTypeOfIntegrityProtectionAlgorithm integrity{};
    EEpsTypeOfCipheringAlgorithm ciphering{};

    IEEpsNasSecurityAlgorithms() = default;
    IEEpsNasSecurityAlgorithms(EEpsTypeOfIntegrityProtectionAlgorithm integrity,
                               EEpsTypeOfCipheringAlgorithm ciphering);

    static IEEpsNasSecurityAlgorithms Decode(const OctetView &stream);
    static void Encode(const IEEpsNasSecurityAlgorithms &ie, OctetString &stream);
    static void Mutate(IEEpsNasSecurityAlgorithms &ie);
};

struct IEGprsTimer : InformationElement3
{
    int timerValue; // 5-bit
    EGprsTimerValueUnit timerValueUnit;

    IEGprsTimer();
    IEGprsTimer(int timerValue, EGprsTimerValueUnit timerValueUnit);

    static IEGprsTimer Decode(const OctetView &stream);
    static void Encode(const IEGprsTimer &ie, OctetString &stream);
    static void Mutate(IEGprsTimer &ie);
};

struct IEIntegrityProtectionMaximumDataRate : InformationElement3
{
    EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink{};
    EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink{};

    IEIntegrityProtectionMaximumDataRate() = default;
    IEIntegrityProtectionMaximumDataRate(
        EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink,
        EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink);

    static IEIntegrityProtectionMaximumDataRate Decode(const OctetView &stream);
    static void Encode(const IEIntegrityProtectionMaximumDataRate &ie, OctetString &stream);
    static void Mutate(IEIntegrityProtectionMaximumDataRate &ie);
};

struct IEMaximumNumberOfSupportedPacketFilters : InformationElement3
{
    int value; // 11-bit

    IEMaximumNumberOfSupportedPacketFilters();
    explicit IEMaximumNumberOfSupportedPacketFilters(int value);

    static IEMaximumNumberOfSupportedPacketFilters Decode(const OctetView &stream);
    static void Encode(const IEMaximumNumberOfSupportedPacketFilters &ie, OctetString &stream);
    static void Mutate(IEMaximumNumberOfSupportedPacketFilters &ie);
};

struct IEN1ModeToS1ModeNasTransparentContainer : InformationElement3
{
    octet sequenceNumber{};

    IEN1ModeToS1ModeNasTransparentContainer() = default;
    explicit IEN1ModeToS1ModeNasTransparentContainer(uint8_t value);

    static IEN1ModeToS1ModeNasTransparentContainer Decode(const OctetView &stream);
    static void Encode(const IEN1ModeToS1ModeNasTransparentContainer &ie, OctetString &stream);
    static void Mutate(IEN1ModeToS1ModeNasTransparentContainer &ie);
};

struct IENasSecurityAlgorithms : InformationElement3
{
    ETypeOfIntegrityProtectionAlgorithm integrity{};
    ETypeOfCipheringAlgorithm ciphering{};

    IENasSecurityAlgorithms() = default;
    IENasSecurityAlgorithms(ETypeOfIntegrityProtectionAlgorithm integrity, ETypeOfCipheringAlgorithm ciphering);

    static IENasSecurityAlgorithms Decode(const OctetView &stream);
    static void Encode(const IENasSecurityAlgorithms &ie, OctetString &stream);
    static void Mutate(IENasSecurityAlgorithms &ie);
};

struct IEPduSessionIdentity2 : InformationElement3
{
    octet value{};

    IEPduSessionIdentity2() = default;
    explicit IEPduSessionIdentity2(uint8_t value);

    static IEPduSessionIdentity2 Decode(const OctetView &stream);
    static void Encode(const IEPduSessionIdentity2 &ie, OctetString &stream);
    static void Mutate(IEPduSessionIdentity2 &ie);
};

struct IETimeZone : InformationElement3
{
    octet value{};

    IETimeZone() = default;
    explicit IETimeZone(uint8_t value);

    static IETimeZone Decode(const OctetView &stream);
    static void Encode(const IETimeZone &ie, OctetString &stream);
    static void Mutate(IETimeZone &ie);
};

struct IETimeZoneAndTime : InformationElement3
{
    VTime time;
    octet timezone{};

    IETimeZoneAndTime() = default;
    IETimeZoneAndTime(VTime time, octet timezone);

    static IETimeZoneAndTime Decode(const OctetView &stream);
    static void Encode(const IETimeZoneAndTime &ie, OctetString &stream);
    static void Mutate(IETimeZoneAndTime &ie);
};

struct IE5gSmCause : InformationElement3
{
    ESmCause value{};

    IE5gSmCause() = default;
    explicit IE5gSmCause(ESmCause value);

    static IE5gSmCause Decode(const OctetView &stream);
    static void Encode(const IE5gSmCause &ie, OctetString &stream);
    static void Mutate(IE5gSmCause &ie);
};

} // namespace nas