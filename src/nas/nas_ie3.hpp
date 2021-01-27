//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "nas_base.hpp"
#include "nas_values.hpp"

namespace nas
{

struct IE5gMmCause : InformationElement3
{
    EMmCause value{};

    IE5gMmCause() = default;
    explicit IE5gMmCause(EMmCause value);

    static IE5gMmCause Decode(const OctetBuffer &stream);
    static void Encode(const IE5gMmCause &ie, OctetString &stream);
};

struct IE5gsTrackingAreaIdentity : InformationElement3
{
    int mcc{};
    int mnc{};
    bool isLongMnc{};
    octet3 trackingAreaCode{};

    IE5gsTrackingAreaIdentity() = default;
    IE5gsTrackingAreaIdentity(int mcc, int mnc, bool isLongMnc, const octet3 &trackingAreaCode);

    static IE5gsTrackingAreaIdentity Decode(const OctetBuffer &stream);
    static void Encode(const IE5gsTrackingAreaIdentity &ie, OctetString &stream);
};

struct IEAuthenticationParameterRand : InformationElement3
{
    OctetString value;

    IEAuthenticationParameterRand() = default;
    explicit IEAuthenticationParameterRand(OctetString &&value);

    static IEAuthenticationParameterRand Decode(const OctetBuffer &stream);
    static void Encode(const IEAuthenticationParameterRand &ie, OctetString &stream);
};

struct IEEpsNasSecurityAlgorithms : InformationElement3
{
    EEpsTypeOfIntegrityProtectionAlgorithm integrity{};
    EEpsTypeOfCipheringAlgorithm ciphering{};

    IEEpsNasSecurityAlgorithms() = default;
    IEEpsNasSecurityAlgorithms(EEpsTypeOfIntegrityProtectionAlgorithm integrity,
                               EEpsTypeOfCipheringAlgorithm ciphering);

    static IEEpsNasSecurityAlgorithms Decode(const OctetBuffer &stream);
    static void Encode(const IEEpsNasSecurityAlgorithms &ie, OctetString &stream);
};

struct IEGprsTimer : InformationElement3
{
    int timerValue : 5;
    EGprsTimerValueUnit timerValueUnit;

    IEGprsTimer();
    IEGprsTimer(int timerValue, EGprsTimerValueUnit timerValueUnit);

    static IEGprsTimer Decode(const OctetBuffer &stream);
    static void Encode(const IEGprsTimer &ie, OctetString &stream);
};

struct IEIntegrityProtectionMaximumDataRate : InformationElement3
{
    EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink{};
    EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink{};

    IEIntegrityProtectionMaximumDataRate() = default;
    IEIntegrityProtectionMaximumDataRate(
        EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink,
        EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink);

    static IEIntegrityProtectionMaximumDataRate Decode(const OctetBuffer &stream);
    static void Encode(const IEIntegrityProtectionMaximumDataRate &ie, OctetString &stream);
};

struct IEMaximumNumberOfSupportedPacketFilters : InformationElement3
{
    int value : 11;

    IEMaximumNumberOfSupportedPacketFilters();
    explicit IEMaximumNumberOfSupportedPacketFilters(int value);

    static IEMaximumNumberOfSupportedPacketFilters Decode(const OctetBuffer &stream);
    static void Encode(const IEMaximumNumberOfSupportedPacketFilters &ie, OctetString &stream);
};

struct IEN1ModeToS1ModeNasTransparentContainer : InformationElement3
{
    octet sequenceNumber{};

    IEN1ModeToS1ModeNasTransparentContainer() = default;
    explicit IEN1ModeToS1ModeNasTransparentContainer(uint8_t value);

    static IEN1ModeToS1ModeNasTransparentContainer Decode(const OctetBuffer &stream);
    static void Encode(const IEN1ModeToS1ModeNasTransparentContainer &ie, OctetString &stream);
};

struct IENasSecurityAlgorithms : InformationElement3
{
    ETypeOfIntegrityProtectionAlgorithm integrity{};
    ETypeOfCipheringAlgorithm ciphering{};

    IENasSecurityAlgorithms() = default;
    IENasSecurityAlgorithms(ETypeOfIntegrityProtectionAlgorithm integrity, ETypeOfCipheringAlgorithm ciphering);

    static IENasSecurityAlgorithms Decode(const OctetBuffer &stream);
    static void Encode(const IENasSecurityAlgorithms &ie, OctetString &stream);
};

struct IEPduSessionIdentity2 : InformationElement3
{
    octet value{};

    IEPduSessionIdentity2() = default;
    explicit IEPduSessionIdentity2(uint8_t value);

    static IEPduSessionIdentity2 Decode(const OctetBuffer &stream);
    static void Encode(const IEPduSessionIdentity2 &ie, OctetString &stream);
};

struct IETimeZone : InformationElement3
{
    octet value{};

    IETimeZone() = default;
    explicit IETimeZone(uint8_t value);

    static IETimeZone Decode(const OctetBuffer &stream);
    static void Encode(const IETimeZone &ie, OctetString &stream);
};

struct IETimeZoneAndTime : InformationElement3
{
    VTime time;
    octet timezone{};

    IETimeZoneAndTime() = default;
    IETimeZoneAndTime(VTime time, octet timezone);

    static IETimeZoneAndTime Decode(const OctetBuffer &stream);
    static void Encode(const IETimeZoneAndTime &ie, OctetString &stream);
};

struct IE5gSmCause : InformationElement3
{
    ESmCause value{};

    IE5gSmCause() = default;
    explicit IE5gSmCause(ESmCause value);

    static IE5gSmCause Decode(const OctetBuffer &stream);
    static void Encode(const IE5gSmCause &ie, OctetString &stream);
};

} // namespace nas