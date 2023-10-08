//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "ie3.hpp"

namespace nas
{

IE5gMmCause::IE5gMmCause(EMmCause value) : value(value)
{
}

IE5gMmCause IE5gMmCause::Decode(const OctetView &stream)
{
    IE5gMmCause r;
    r.value = static_cast<EMmCause>(stream.readI());
    return r;
}

void IE5gMmCause::Encode(const IE5gMmCause &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.value));
}

IE5gsTrackingAreaIdentity::IE5gsTrackingAreaIdentity(int mcc, int mnc, bool isLongMnc, const octet3 &trackingAreaCode)
    : mcc(mcc), mnc(mnc), isLongMnc(isLongMnc), trackingAreaCode(trackingAreaCode)
{
}

IE5gsTrackingAreaIdentity::IE5gsTrackingAreaIdentity(const Tai &tai)
    : mcc(tai.plmn.mcc), mnc(tai.plmn.mnc), isLongMnc(tai.plmn.isLongMnc), trackingAreaCode(tai.tac)
{
}

IE5gsTrackingAreaIdentity IE5gsTrackingAreaIdentity::Decode(const OctetView &stream)
{
    auto plmn = VPlmn::Decode(stream);

    IE5gsTrackingAreaIdentity r;
    r.mcc = plmn.mcc;
    r.mnc = plmn.mnc;
    r.isLongMnc = plmn.isLongMnc;
    r.trackingAreaCode = stream.read3();
    return r;
}

void IE5gsTrackingAreaIdentity::Encode(const IE5gsTrackingAreaIdentity &ie, OctetString &stream)
{
    VPlmn plmn{ie.mcc, ie.mnc, ie.isLongMnc};
    VPlmn::Encode(plmn, stream);
    stream.appendOctet3(ie.trackingAreaCode);
}

IEAuthenticationParameterRand::IEAuthenticationParameterRand(OctetString &&value) : value(std::move(value))
{
}

IEAuthenticationParameterRand IEAuthenticationParameterRand::Decode(const OctetView &stream)
{
    return IEAuthenticationParameterRand{stream.readOctetString(16)};
}

void IEAuthenticationParameterRand::Encode(const IEAuthenticationParameterRand &ie, OctetString &stream)
{
    stream.append(ie.value);
}

IEEpsNasSecurityAlgorithms::IEEpsNasSecurityAlgorithms(EEpsTypeOfIntegrityProtectionAlgorithm integrity,
                                                       EEpsTypeOfCipheringAlgorithm ciphering)
    : integrity(integrity), ciphering(ciphering)
{
}

IEEpsNasSecurityAlgorithms IEEpsNasSecurityAlgorithms::Decode(const OctetView &stream)
{
    IEEpsNasSecurityAlgorithms r;
    r.integrity = static_cast<EEpsTypeOfIntegrityProtectionAlgorithm>(stream.peekI() & 0b111);
    r.ciphering = static_cast<EEpsTypeOfCipheringAlgorithm>((stream.readI() >> 4) & 0b111);
    return r;
}

void IEEpsNasSecurityAlgorithms::Encode(const IEEpsNasSecurityAlgorithms &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.ciphering), static_cast<int>(ie.integrity));
}

IEGprsTimer::IEGprsTimer(int timerValue, EGprsTimerValueUnit timerValueUnit)
    : timerValue(timerValue), timerValueUnit(timerValueUnit)
{
}

IEGprsTimer IEGprsTimer::Decode(const OctetView &stream)
{
    int octet = stream.readI();
    IEGprsTimer r;
    r.timerValue = bits::BitRange8<0, 4>(octet);
    r.timerValueUnit = static_cast<EGprsTimerValueUnit>(bits::BitAt<5>(octet));
    return r;
}

void IEGprsTimer::Encode(const IEGprsTimer &ie, OctetString &stream)
{
    uint8_t octet = 0;
    bits::SetBitRange8<0, 4>(octet, ie.timerValue);
    bits::SetBitAt<5>(octet, static_cast<int>(ie.timerValueUnit));
    stream.appendOctet(octet);
}

IEGprsTimer::IEGprsTimer() : timerValue(0), timerValueUnit()
{
}

IEIntegrityProtectionMaximumDataRate::IEIntegrityProtectionMaximumDataRate(
    EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink maxRateUplink,
    EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink maxRateDownlink)
    : maxRateUplink(maxRateUplink), maxRateDownlink(maxRateDownlink)
{
}

IEIntegrityProtectionMaximumDataRate IEIntegrityProtectionMaximumDataRate::Decode(const OctetView &stream)
{
    IEIntegrityProtectionMaximumDataRate r;
    r.maxRateUplink = static_cast<EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink>(stream.readI());
    r.maxRateDownlink = static_cast<EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink>(stream.readI());
    return r;
}

void IEIntegrityProtectionMaximumDataRate::Encode(const IEIntegrityProtectionMaximumDataRate &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.maxRateUplink));
    stream.appendOctet(static_cast<int>(ie.maxRateDownlink));
}

IEMaximumNumberOfSupportedPacketFilters::IEMaximumNumberOfSupportedPacketFilters() : value(0)
{
}

IEMaximumNumberOfSupportedPacketFilters::IEMaximumNumberOfSupportedPacketFilters(int value) : value(value)
{
}

IEMaximumNumberOfSupportedPacketFilters IEMaximumNumberOfSupportedPacketFilters::Decode(const OctetView &stream)
{
    int v = stream.read2I();
    v >>= 5;

    IEMaximumNumberOfSupportedPacketFilters r;
    r.value = v;
    return r;
}

void IEMaximumNumberOfSupportedPacketFilters::Encode(const IEMaximumNumberOfSupportedPacketFilters &ie,
                                                     OctetString &stream)
{
    stream.appendOctet((ie.value >> 3) & 0xFF);
    stream.appendOctet((ie.value & 0b111) << 5);
}

IEN1ModeToS1ModeNasTransparentContainer::IEN1ModeToS1ModeNasTransparentContainer(uint8_t value) : sequenceNumber(value)
{
}

IEN1ModeToS1ModeNasTransparentContainer IEN1ModeToS1ModeNasTransparentContainer::Decode(const OctetView &stream)
{
    IEN1ModeToS1ModeNasTransparentContainer r;
    r.sequenceNumber = stream.read();
    return r;
}

void IEN1ModeToS1ModeNasTransparentContainer::Encode(const IEN1ModeToS1ModeNasTransparentContainer &ie,
                                                     OctetString &stream)
{
    stream.appendOctet(ie.sequenceNumber);
}

IENasSecurityAlgorithms::IENasSecurityAlgorithms(ETypeOfIntegrityProtectionAlgorithm integrity,
                                                 ETypeOfCipheringAlgorithm ciphering)
    : integrity(integrity), ciphering(ciphering)
{
}

IENasSecurityAlgorithms IENasSecurityAlgorithms::Decode(const OctetView &stream)
{
    IENasSecurityAlgorithms r;
    r.integrity = static_cast<ETypeOfIntegrityProtectionAlgorithm>(stream.peekI() & 0xF);
    r.ciphering = static_cast<ETypeOfCipheringAlgorithm>((stream.readI() >> 4) & 0xF);
    return r;
}

void IENasSecurityAlgorithms::Encode(const IENasSecurityAlgorithms &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.ciphering), static_cast<int>(ie.integrity));
}

IEPduSessionIdentity2::IEPduSessionIdentity2(uint8_t value) : value(value)
{
}

IEPduSessionIdentity2 IEPduSessionIdentity2::Decode(const OctetView &stream)
{
    IEPduSessionIdentity2 r;
    r.value = stream.read();
    return r;
}

void IEPduSessionIdentity2::Encode(const IEPduSessionIdentity2 &ie, OctetString &stream)
{
    stream.appendOctet(ie.value);
}

IETimeZone::IETimeZone(uint8_t value) : value(value)
{
}

IETimeZone IETimeZone::Decode(const OctetView &stream)
{
    IETimeZone r;
    r.value = stream.read();
    return r;
}

void IETimeZone::Encode(const IETimeZone &ie, OctetString &stream)
{
    stream.appendOctet(ie.value);
}

IETimeZoneAndTime::IETimeZoneAndTime(VTime time, octet timezone) : time(time), timezone(timezone)
{
}

IETimeZoneAndTime IETimeZoneAndTime::Decode(const OctetView &stream)
{
    IETimeZoneAndTime r;
    r.time = VTime::Decode(stream);
    r.timezone = stream.read();
    return r;
}

void IETimeZoneAndTime::Encode(const IETimeZoneAndTime &ie, OctetString &stream)
{
    VTime::Encode(ie.time, stream);
    stream.appendOctet(ie.timezone);
}

IE5gSmCause::IE5gSmCause(ESmCause value) : value(value)
{
}

IE5gSmCause IE5gSmCause::Decode(const OctetView &stream)
{
    return IE5gSmCause{static_cast<ESmCause>(stream.readI())};
}

void IE5gSmCause::Encode(const IE5gSmCause &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.value));
}

} // namespace nas
