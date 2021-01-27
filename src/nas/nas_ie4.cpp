//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "nas_ie4.hpp"

namespace nas
{

IEDaylightSavingTime::IEDaylightSavingTime(EDaylightSavingTime value) : value(value)
{
}

IEDaylightSavingTime IEDaylightSavingTime::Decode(const OctetBuffer &stream, int length)
{
    IEDaylightSavingTime r;
    r.value = static_cast<EDaylightSavingTime>(stream.readI() & 0b11);
    return r;
}

void IEDaylightSavingTime::Encode(const IEDaylightSavingTime &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.value));
}

IEPduSessionReactivationResult::IEPduSessionReactivationResult(std::bitset<16> psi) : psi(psi)
{
}

IEPduSessionReactivationResult IEPduSessionReactivationResult::Decode(const OctetBuffer &stream, int length)
{
    octet octet1 = stream.read();
    octet octet2 = stream.read();

    IEPduSessionReactivationResult r;
    for (int i = 0; i < 8; i++)
        r.psi[i] = octet1.bit(i);
    for (int i = 0; i < 8; i++)
        r.psi[8 + i] = octet2.bit(i);
    r.psi[0] = false; // psi[0] is reserved
    return r;
}

void IEPduSessionReactivationResult::Encode(const IEPduSessionReactivationResult &ie, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, 0},
                                      {1, ie.psi[1]},
                                      {1, ie.psi[2]},
                                      {1, ie.psi[3]},
                                      {1, ie.psi[4]},
                                      {1, ie.psi[5]},
                                      {1, ie.psi[6]},
                                      {1, ie.psi[7]}}));
    stream.appendOctet(bits::Ranged8({{1, ie.psi[8]},
                                      {1, ie.psi[9]},
                                      {1, ie.psi[10]},
                                      {1, ie.psi[11]},
                                      {1, ie.psi[12]},
                                      {1, ie.psi[13]},
                                      {1, ie.psi[14]},
                                      {1, ie.psi[15]}}));
}

IEPduAddress::IEPduAddress(EPduSessionType sessionType, OctetString &&pduAddressInformation)
    : sessionType(sessionType), pduAddressInformation(std::move(pduAddressInformation))
{
}

IEPduAddress IEPduAddress::Decode(const OctetBuffer &stream, int length)
{
    IEPduAddress r;
    r.sessionType = static_cast<EPduSessionType>(stream.readI() & 0b111);
    r.pduAddressInformation = stream.readOctetString(length - 1);
    return r;
}

void IEPduAddress::Encode(const IEPduAddress &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.sessionType));
    stream.append(ie.pduAddressInformation);
}

IESNssai::IESNssai(const octet &sst, const std::optional<octet3> &sd, const std::optional<octet> &mappedHplmnSst,
                   const std::optional<octet3> &mappedHplmnSd)
    : sst(sst), sd(sd), mappedHplmnSst(mappedHplmnSst), mappedHplmnSd(mappedHplmnSd)
{
}

IESNssai IESNssai::Decode(const OctetBuffer &stream, int length)
{
    IESNssai res;

    switch (length)
    {
    case 0b00000001: // SST
        res.sst = stream.read();
        break;
    case 0b00000010: // SST and mapped HPLMN SST
        res.sst = stream.read();
        res.mappedHplmnSst = stream.read();
        break;
    case 0b00000100: // SST and SD
        res.sst = stream.read();
        res.sd = stream.read3();
        break;
    case 0b00000101: // SST, SD and mapped HPLMN SST
        res.sst = stream.read();
        res.sd = stream.read3();
        res.mappedHplmnSst = stream.read();
        break;
    case 0b00001000: // SST, SD, mapped HPLMN SST and mapped HPLMN SD
        res.sst = stream.read();
        res.sd = stream.read3();
        res.mappedHplmnSst = stream.read();
        res.mappedHplmnSd = stream.read3();
        break;
    default: // All other values are reserved
        break;
    }

    return res;
}

void IESNssai::Encode(const IESNssai &ie, OctetString &stream)
{
    stream.appendOctet(ie.sst);
    if (ie.sd.has_value())
        stream.appendOctet3(ie.sd.value());
    if (ie.mappedHplmnSst.has_value())
        stream.appendOctet(ie.mappedHplmnSst.value());
    if (ie.mappedHplmnSd.has_value())
        stream.appendOctet3(ie.mappedHplmnSd.value());
}

IEAdditional5gSecurityInformation::IEAdditional5gSecurityInformation(EHorizontalDerivationParameter hdp,
                                                                     ERetransmissionOfInitialNasMessageRequest rinmr)
    : hdp(hdp), rinmr(rinmr)
{
}

IEAdditional5gSecurityInformation IEAdditional5gSecurityInformation::Decode(const OctetBuffer &stream, int length)
{
    IEAdditional5gSecurityInformation r;
    r.hdp = static_cast<EHorizontalDerivationParameter>(stream.peekI() & 0b1);
    r.rinmr = static_cast<ERetransmissionOfInitialNasMessageRequest>((stream.readI() >> 1) & 0b1);
    return r;
}

void IEAdditional5gSecurityInformation::Encode(const IEAdditional5gSecurityInformation &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.rinmr) << 1 | static_cast<int>(ie.hdp));
}

IES1UeNetworkCapability IES1UeNetworkCapability::Decode(const OctetBuffer &stream, int length)
{
    IES1UeNetworkCapability cap;

    if (length >= 1)
    {
        auto bits = stream.read();
        cap.b_EEA7 = bits.bit(0);
        cap.b_EEA6 = bits.bit(1);
        cap.b_EEA5 = bits.bit(2);
        cap.b_EEA4 = bits.bit(3);
        cap.b_128_EEA3 = bits.bit(4);
        cap.b_128_EEA2 = bits.bit(5);
        cap.b_128_EEA1 = bits.bit(6);
        cap.b_EEA0 = bits.bit(7);
    }

    if (length >= 2)
    {
        auto bits = stream.read();
        cap.b_EIA7 = bits.bit(0);
        cap.b_EIA6 = bits.bit(1);
        cap.b_EIA5 = bits.bit(2);
        cap.b_EIA4 = bits.bit(3);
        cap.b_128_EIA3 = bits.bit(4);
        cap.b_128_EIA2 = bits.bit(5);
        cap.b_128_EIA1 = bits.bit(6);
        cap.b_EIA0 = bits.bit(7);
    }

    if (length >= 3)
    {
        auto bits = stream.read();
        cap.b_UEA7 = bits.bit(0);
        cap.b_UEA6 = bits.bit(1);
        cap.b_UEA5 = bits.bit(2);
        cap.b_UEA4 = bits.bit(3);
        cap.b_UEA3 = bits.bit(4);
        cap.b_UEA2 = bits.bit(5);
        cap.b_UEA1 = bits.bit(6);
        cap.b_UEA0 = bits.bit(7);
    }

    if (length >= 4)
    {
        auto bits = stream.read();
        cap.b_UIA7 = bits.bit(0);
        cap.b_UIA6 = bits.bit(1);
        cap.b_UIA5 = bits.bit(2);
        cap.b_UIA4 = bits.bit(3);
        cap.b_UIA3 = bits.bit(4);
        cap.b_UIA2 = bits.bit(5);
        cap.b_UIA1 = bits.bit(6);
        cap.b_UCS2 = bits.bit(7);
    }

    if (length >= 5)
    {
        auto bits = stream.read();
        cap.b_NF = bits.bit(0);
        cap.b_1xSR_VCC = bits.bit(1);
        cap.b_LCS = bits.bit(2);
        cap.b_LPP = bits.bit(3);
        cap.b_ACC_CSFB = bits.bit(4);
        cap.b_H_245_ASH = bits.bit(5);
        cap.b_ProSe = bits.bit(6);
        cap.b_ProSe_dd = bits.bit(7);
    }

    if (length >= 6)
    {
        auto bits = stream.read();
        cap.b_ProSe_dc = bits.bit(0);
        cap.b_Prose_relay = bits.bit(1);
        cap.b_CP_CIoT = bits.bit(2);
        cap.b_UP_CIoT = bits.bit(3);
        cap.b_S1_U_data = bits.bit(4);
        cap.b_ERw_oPDN = bits.bit(5);
        cap.b_HC_CP_CIoT = bits.bit(6);
        cap.b_ePCO = bits.bit(7);
    }

    if (length >= 7)
    {
        auto bits = stream.read();
        cap.b_multipleDRB = bits.bit(0);
        cap.b_V2X_PC5 = bits.bit(1);
        cap.b_RestrictEC = bits.bit(2);
        cap.b_CP_backoff = bits.bit(3);
        cap.b_DCNR = bits.bit(4);
        cap.b_N1mode = bits.bit(5);
        cap.b_SGC = bits.bit(6);
        cap.b_15_bearers = bits.bit(7);
    }

    // Other octets are spare, if any
    if (length >= 8)
        stream.readOctetString(length - 7);

    return cap;
}

void IES1UeNetworkCapability::Encode(const IES1UeNetworkCapability &ie, OctetString &stream)
{
    stream.appendOctet(bits::Consequential8(ie.b_EEA0, ie.b_128_EEA1, ie.b_128_EEA2, ie.b_128_EEA3, ie.b_EEA4,
                                            ie.b_EEA5, ie.b_EEA6, ie.b_EEA7));
    stream.appendOctet(bits::Consequential8(ie.b_EIA0, ie.b_128_EIA1, ie.b_128_EIA2, ie.b_128_EIA3, ie.b_EIA4,
                                            ie.b_EIA5, ie.b_EIA6, ie.b_EIA7));
    stream.appendOctet(
        bits::Consequential8(ie.b_UEA0, ie.b_UEA1, ie.b_UEA2, ie.b_UEA3, ie.b_UEA4, ie.b_UEA5, ie.b_UEA6, ie.b_UEA7));
    stream.appendOctet(
        bits::Consequential8(ie.b_UCS2, ie.b_UIA1, ie.b_UIA2, ie.b_UIA3, ie.b_UIA4, ie.b_UIA5, ie.b_UIA6, ie.b_UIA7));
    stream.appendOctet(bits::Consequential8(ie.b_ProSe_dd, ie.b_ProSe, ie.b_H_245_ASH, ie.b_ACC_CSFB, ie.b_LPP,
                                            ie.b_LCS, ie.b_1xSR_VCC, ie.b_NF));
    stream.appendOctet(bits::Consequential8(ie.b_ePCO, ie.b_HC_CP_CIoT, ie.b_ERw_oPDN, ie.b_S1_U_data, ie.b_UP_CIoT,
                                            ie.b_CP_CIoT, ie.b_Prose_relay, ie.b_ProSe_dc));
    stream.appendOctet(bits::Consequential8(ie.b_15_bearers, ie.b_SGC, ie.b_N1mode, ie.b_DCNR, ie.b_CP_backoff,
                                            ie.b_RestrictEC, ie.b_V2X_PC5, ie.b_multipleDRB));
}

IES1UeNetworkCapability::IES1UeNetworkCapability()
    : b_EEA0(0), b_128_EEA1(0), b_128_EEA2(0), b_128_EEA3(0), b_EEA4(0), b_EEA5(0), b_EEA6(0), b_EEA7(0), b_EIA0(0),
      b_128_EIA1(0), b_128_EIA2(0), b_128_EIA3(0), b_EIA4(0), b_EIA5(0), b_EIA6(0), b_EIA7(0), b_UEA0(0), b_UEA1(0),
      b_UEA2(0), b_UEA3(0), b_UEA4(0), b_UEA5(0), b_UEA6(0), b_UEA7(0), b_UCS2(0), b_UIA1(0), b_UIA2(0), b_UIA3(0),
      b_UIA4(0), b_UIA5(0), b_UIA6(0), b_UIA7(0), b_ProSe_dd(0), b_ProSe(0), b_H_245_ASH(0), b_ACC_CSFB(0), b_LPP(0),
      b_LCS(0), b_1xSR_VCC(0), b_NF(0), b_ePCO(0), b_HC_CP_CIoT(0), b_ERw_oPDN(0), b_S1_U_data(0), b_UP_CIoT(0),
      b_CP_CIoT(0), b_Prose_relay(0), b_ProSe_dc(0), b_15_bearers(0), b_SGC(0), b_N1mode(0), b_DCNR(0), b_CP_backoff(0),
      b_RestrictEC(0), b_V2X_PC5(0), b_multipleDRB(0)
{
}

IEGprsTimer3::IEGprsTimer3() : timerValue(0), unit(static_cast<EGprsTimerValueUnit3>(0))
{
}

IEGprsTimer3::IEGprsTimer3(int timerValue, EGprsTimerValueUnit3 unit) : timerValue(timerValue), unit(unit)
{
}

IEGprsTimer3 IEGprsTimer3::Decode(const OctetBuffer &stream, int length)
{
    auto oct = stream.read();

    IEGprsTimer3 r;
    r.timerValue = bits::BitRange8<0, 4>(oct);
    r.unit = static_cast<EGprsTimerValueUnit3>(bits::BitRange8<5, 7>(oct));
    return r;
}

void IEGprsTimer3::Encode(const IEGprsTimer3 &ie, OctetString &stream)
{
    int octet = static_cast<int>(ie.unit) & 0b111;
    octet <<= 5;
    octet |= static_cast<int>(ie.timerValue) & 0b11111;
    stream.appendOctet(octet);
}

IEAuthenticationFailureParameter::IEAuthenticationFailureParameter(OctetString &&rawData) : rawData(std::move(rawData))
{
}

IEAuthenticationFailureParameter IEAuthenticationFailureParameter::Decode(const OctetBuffer &stream, int length)
{
    IEAuthenticationFailureParameter r;
    r.rawData = stream.readOctetString(length);
    return r;
}

void IEAuthenticationFailureParameter::Encode(const IEAuthenticationFailureParameter &ie, OctetString &stream)
{
    stream.append(ie.rawData);
}

IEAbba::IEAbba(OctetString &&rawData) : rawData(std::move(rawData))
{
}

IEAbba IEAbba::Decode(const OctetBuffer &stream, int length)
{
    IEAbba r;
    r.rawData = stream.readOctetString(length);
    return r;
}

void IEAbba::Encode(const IEAbba &ie, OctetString &stream)
{
    stream.append(ie.rawData);
}

IES1ModeToN1ModeNasTransparentContainer::IES1ModeToN1ModeNasTransparentContainer(
    const octet4 &mac, ETypeOfCipheringAlgorithm cipheringAlg,
    ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg, int keySetIdentifierIn5G, ETypeOfSecurityContext tsc,
    int ncc, const octet2 &ueSecurityCapability5G, const octet2 &ueSecurityCapabilityEps)
    : mac(mac), cipheringAlg(cipheringAlg), integrityProtectionAlg(integrityProtectionAlg),
      keySetIdentifierIn5g(keySetIdentifierIn5G), tsc(tsc), ncc(ncc), ueSecurityCapability5g(ueSecurityCapability5G),
      ueSecurityCapabilityEps(ueSecurityCapabilityEps)
{
}

IES1ModeToN1ModeNasTransparentContainer IES1ModeToN1ModeNasTransparentContainer::Decode(const OctetBuffer &stream, int length)
{
    IES1ModeToN1ModeNasTransparentContainer res;
    res.mac = stream.read4();

    auto octet = stream.read();
    res.integrityProtectionAlg = static_cast<ETypeOfIntegrityProtectionAlgorithm>(bits::BitRange8<0, 3>(octet));
    res.cipheringAlg = static_cast<ETypeOfCipheringAlgorithm>(bits::BitRange8<4, 7>(octet));

    octet = stream.read();
    res.keySetIdentifierIn5g = bits::BitRange8<0, 2>(octet);
    res.tsc = static_cast<ETypeOfSecurityContext>(octet.bit(3));
    res.ncc = bits::BitRange8<4, 6>(octet);

    res.ueSecurityCapability5g = stream.read2();

    if (length >= 9)
        res.ueSecurityCapabilityEps = stream.read2();

    return res;
}

void IES1ModeToN1ModeNasTransparentContainer::Encode(const IES1ModeToN1ModeNasTransparentContainer &ie,
                                                     OctetString &stream)
{
    stream.appendOctet4(ie.mac);
    stream.appendOctet((static_cast<int>(ie.cipheringAlg) << 4) | static_cast<int>(ie.integrityProtectionAlg));
    stream.appendOctet(
        bits::Ranged8({{1, 0}, {3, ie.ncc}, {1, static_cast<int>(ie.tsc)}, {3, ie.keySetIdentifierIn5g}}));
    stream.appendOctet2(ie.ueSecurityCapability5g);
    if (ie.ueSecurityCapabilityEps.has_value())
        stream.appendOctet2(ie.ueSecurityCapabilityEps.value());
}

IEGprsTimer2 IEGprsTimer2::Decode(const OctetBuffer &stream, int length)
{
    IEGprsTimer2 r;
    r.value = stream.read();
    return r;
}

IEGprsTimer2::IEGprsTimer2(octet value) : value(value)
{
}

void IEGprsTimer2::Encode(const IEGprsTimer2 &ie, OctetString &stream)
{
    stream.appendOctet(ie.value);
}

IE5gSmCapability::IE5gSmCapability(EReflectiveQoS rqos, EMultiHomedIPv6PduSession mh6Pdu) : rqos(rqos), mh6pdu(mh6Pdu)
{
}

IE5gSmCapability IE5gSmCapability::Decode(const OctetBuffer &stream, int length)
{
    IE5gSmCapability r;
    r.rqos = static_cast<EReflectiveQoS>(stream.peekI() & 0b1);
    r.mh6pdu = static_cast<EMultiHomedIPv6PduSession>((stream.readI() >> 1) & 0b1);

    // other octets are spare, if any
    stream.readOctetString(length - 1);

    return r;
}

void IE5gSmCapability::Encode(const IE5gSmCapability &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.mh6pdu) << 1 | static_cast<int>(ie.rqos));
}

IEUeSecurityCapability::IEUeSecurityCapability()
    : b_5G_EA0(0), b_128_5G_EA1(0), b_128_5G_EA2(0), b_128_5G_EA3(0), b_5G_EA4(0), b_5G_EA5(0), b_5G_EA6(0),
      b_5G_EA7(0), b_5G_IA0(0), b_128_5G_IA1(0), b_128_5G_IA2(0), b_128_5G_IA3(0), b_5G_IA4(0), b_5G_IA5(0),
      b_5G_IA6(0), b_5G_IA7(0), b_EEA0(0), b_128_EEA1(0), b_128_EEA2(0), b_128_EEA3(0), b_EEA4(0), b_EEA5(0), b_EEA6(0),
      b_EEA7(0), b_EIA0(0), b_128_EIA1(0), b_128_EIA2(0), b_128_EIA3(0), b_EIA4(0), b_EIA5(0), b_EIA6(0), b_EIA7(0)
{
}

IEUeSecurityCapability IEUeSecurityCapability::Decode(const OctetBuffer &stream, int length)
{
    IEUeSecurityCapability cap;

    if (length >= 1)
    {
        auto bits = stream.read();
        cap.b_5G_EA0 = bits.bit(7);
        cap.b_128_5G_EA1 = bits.bit(6);
        cap.b_128_5G_EA2 = bits.bit(5);
        cap.b_128_5G_EA3 = bits.bit(4);
        cap.b_5G_EA4 = bits.bit(3);
        cap.b_5G_EA5 = bits.bit(2);
        cap.b_5G_EA6 = bits.bit(1);
        cap.b_5G_EA7 = bits.bit(0);
    }

    if (length >= 2)
    {
        auto bits = stream.read();
        cap.b_5G_IA0 = bits.bit(7);
        cap.b_128_5G_IA1 = bits.bit(6);
        cap.b_128_5G_IA2 = bits.bit(5);
        cap.b_128_5G_IA3 = bits.bit(4);
        cap.b_5G_IA4 = bits.bit(3);
        cap.b_5G_IA5 = bits.bit(2);
        cap.b_5G_IA6 = bits.bit(1);
        cap.b_5G_IA7 = bits.bit(0);
    }

    if (length >= 3)
    {
        auto bits = stream.read();
        cap.b_EEA0 = bits.bit(7);
        cap.b_128_EEA1 = bits.bit(6);
        cap.b_128_EEA2 = bits.bit(5);
        cap.b_128_EEA3 = bits.bit(4);
        cap.b_EEA4 = bits.bit(3);
        cap.b_EEA5 = bits.bit(2);
        cap.b_EEA6 = bits.bit(1);
        cap.b_EEA7 = bits.bit(0);
    }

    if (length >= 4)
    {
        auto bits = stream.read();
        cap.b_EIA0 = bits.bit(7);
        cap.b_128_EIA1 = bits.bit(6);
        cap.b_128_EIA2 = bits.bit(5);
        cap.b_128_EIA3 = bits.bit(4);
        cap.b_EIA4 = bits.bit(3);
        cap.b_EIA5 = bits.bit(2);
        cap.b_EIA6 = bits.bit(1);
        cap.b_EIA7 = bits.bit(0);
    }

    if (length >= 5)
    {
        // Other octets are spare, if any
        stream.readOctetString(length - 4);
    }

    return cap;
}

void IEUeSecurityCapability::Encode(const IEUeSecurityCapability &ie, OctetString &stream)
{
    stream.appendOctet(bits::Consequential8(ie.b_5G_EA0, ie.b_128_5G_EA1, ie.b_128_5G_EA2, ie.b_128_5G_EA3, ie.b_5G_EA4,
                                            ie.b_5G_EA5, ie.b_5G_EA6, ie.b_5G_EA7));
    stream.appendOctet(bits::Consequential8(ie.b_5G_IA0, ie.b_128_5G_IA1, ie.b_128_5G_IA2, ie.b_128_5G_IA3, ie.b_5G_IA4,
                                            ie.b_5G_IA5, ie.b_5G_IA6, ie.b_5G_IA7));
    stream.appendOctet(bits::Consequential8(ie.b_EEA0, ie.b_128_EEA1, ie.b_128_EEA2, ie.b_128_EEA3, ie.b_EEA4,
                                            ie.b_EEA5, ie.b_EEA6, ie.b_EEA7));
    stream.appendOctet(bits::Consequential8(ie.b_EIA0, ie.b_128_EIA1, ie.b_128_EIA2, ie.b_128_EIA3, ie.b_EIA4,
                                            ie.b_EIA5, ie.b_EIA6, ie.b_EIA7));
}

IESessionAmbr::IESessionAmbr(EUnitForSessionAmbr unitForSessionAmbrForDownlink, const octet2 &sessionAmbrForDownlink,
                             EUnitForSessionAmbr unitForSessionAmbrForUplink, const octet2 &sessionAmbrForUplink)
    : unitForSessionAmbrForDownlink(unitForSessionAmbrForDownlink), sessionAmbrForDownlink(sessionAmbrForDownlink),
      unitForSessionAmbrForUplink(unitForSessionAmbrForUplink), sessionAmbrForUplink(sessionAmbrForUplink)
{
}

IESessionAmbr IESessionAmbr::Decode(const OctetBuffer &stream, int length)
{
    IESessionAmbr r;
    r.unitForSessionAmbrForDownlink = static_cast<EUnitForSessionAmbr>(stream.readI());
    r.sessionAmbrForDownlink = stream.read2();
    r.unitForSessionAmbrForUplink = static_cast<EUnitForSessionAmbr>(stream.readI());
    r.sessionAmbrForUplink = stream.read2();
    return r;
}

void IESessionAmbr::Encode(const IESessionAmbr &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.unitForSessionAmbrForDownlink));
    stream.appendOctet2(ie.sessionAmbrForDownlink);
    stream.appendOctet(static_cast<int>(ie.unitForSessionAmbrForUplink));
    stream.appendOctet2(ie.sessionAmbrForUplink);
}

IEAuthenticationParameterAutn::IEAuthenticationParameterAutn(OctetString &&value) : value(std::move(value))
{
}

IEAuthenticationParameterAutn IEAuthenticationParameterAutn::Decode(const OctetBuffer &stream, int length)
{
    IEAuthenticationParameterAutn r;
    r.value = stream.readOctetString(length);
    return r;
}

void IEAuthenticationParameterAutn::Encode(const IEAuthenticationParameterAutn &ie, OctetString &stream)
{
    stream.append(ie.value);
}

IE5gsUpdateType::IE5gsUpdateType(ESmsRequested smsRequested, ENgRanRadioCapabilityUpdate ngRanRcu)
    : smsRequested(smsRequested), ngRanRcu(ngRanRcu)
{
}

IE5gsUpdateType IE5gsUpdateType::Decode(const OctetBuffer &stream, int length)
{
    IE5gsUpdateType r;
    r.smsRequested = static_cast<ESmsRequested>(stream.peekI() & 0b1);
    r.ngRanRcu = static_cast<ENgRanRadioCapabilityUpdate>((stream.readI() >> 1) & 0b1);
    return r;
}

void IE5gsUpdateType::Encode(const IE5gsUpdateType &ie, OctetString &stream)
{
    stream.appendOctet(
        bits::Ranged8({{6, 0}, {1, static_cast<int>(ie.ngRanRcu)}, {1, static_cast<int>(ie.smsRequested)}}));
}

IEUplinkDataStatus::IEUplinkDataStatus(std::bitset<16> psi) : psi(psi)
{
}

IEUplinkDataStatus IEUplinkDataStatus::Decode(const OctetBuffer &stream, int length)
{
    octet octet1 = stream.read();
    octet octet2 = stream.read();

    IEUplinkDataStatus r;
    for (int i = 0; i < 8; i++)
        r.psi[i] = octet1.bit(i);
    for (int i = 0; i < 8; i++)
        r.psi[8 + i] = octet2.bit(i);
    r.psi[0] = false; // psi[0] is reserved
    return r;
}

void IEUplinkDataStatus::Encode(const IEUplinkDataStatus &ie, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, 0},
                                      {1, ie.psi[1]},
                                      {1, ie.psi[2]},
                                      {1, ie.psi[3]},
                                      {1, ie.psi[4]},
                                      {1, ie.psi[5]},
                                      {1, ie.psi[6]},
                                      {1, ie.psi[7]}}));
    stream.appendOctet(bits::Ranged8({{1, ie.psi[8]},
                                      {1, ie.psi[9]},
                                      {1, ie.psi[10]},
                                      {1, ie.psi[11]},
                                      {1, ie.psi[12]},
                                      {1, ie.psi[13]},
                                      {1, ie.psi[14]},
                                      {1, ie.psi[15]}}));
}

IEAdditionalInformation::IEAdditionalInformation(OctetString &&rawData) : rawData(std::move(rawData))
{
}

IEAdditionalInformation IEAdditionalInformation::Decode(const OctetBuffer &stream, int length)
{
    IEAdditionalInformation r;
    r.rawData = stream.readOctetString(length);
    return r;
}

void IEAdditionalInformation::Encode(const IEAdditionalInformation &ie, OctetString &stream)
{
    stream.append(ie.rawData);
}

IEAuthenticationResponseParameter::IEAuthenticationResponseParameter(OctetString &&rawData)
    : rawData(std::move(rawData))
{
}

IEAuthenticationResponseParameter IEAuthenticationResponseParameter::Decode(const OctetBuffer &stream, int length)
{
    IEAuthenticationResponseParameter r;
    r.rawData = stream.readOctetString(length);
    return r;
}

void IEAuthenticationResponseParameter::Encode(const IEAuthenticationResponseParameter &ie, OctetString &stream)
{
    stream.append(ie.rawData);
}

IEUeStatus::IEUeStatus(EEmmRegistrationStatus s1ModeReg, E5gMmRegistrationStatus n1ModeReg)
    : s1ModeReg(s1ModeReg), n1ModeReg(n1ModeReg)
{
}

IEUeStatus IEUeStatus::Decode(const OctetBuffer &stream, int length)
{
    auto octet = stream.readI();

    IEUeStatus r;
    r.s1ModeReg = static_cast<EEmmRegistrationStatus>(octet & 0b1);
    r.n1ModeReg = static_cast<E5gMmRegistrationStatus>((octet >> 1) & 0b1);
    return r;
}

void IEUeStatus::Encode(const IEUeStatus &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.s1ModeReg) | (static_cast<int>(ie.n1ModeReg) << 1));
}

IE5gsRegistrationResult::IE5gsRegistrationResult(ESmsOverNasTransportAllowed smsOverNasAllowed,
                                                 E5gsRegistrationResult registrationResult)
    : registrationResult(registrationResult), smsOverNasAllowed(smsOverNasAllowed)
{
}

IE5gsRegistrationResult IE5gsRegistrationResult::Decode(const OctetBuffer &stream, int length)
{
    auto octet = stream.readI();

    IE5gsRegistrationResult r;
    r.registrationResult = static_cast<E5gsRegistrationResult>(octet & 0b111);
    r.smsOverNasAllowed = static_cast<ESmsOverNasTransportAllowed>((octet >> 3) & 0b1);
    return r;
}

void IE5gsRegistrationResult::Encode(const IE5gsRegistrationResult &ie, OctetString &stream)
{
    stream.appendOctet((static_cast<int>(ie.smsOverNasAllowed) << 3) | static_cast<int>(ie.registrationResult));
}

IENetworkName::IENetworkName(uint8_t numOfSpareBits, EAddCountryInitials addCi, ECodingScheme codingScheme,
                             OctetString &&textString)
    : numOfSpareBits(numOfSpareBits), addCi(addCi), codingScheme(codingScheme), textString(std::move(textString))
{
}

IENetworkName IENetworkName::Decode(const OctetBuffer &stream, int length)
{
    int flags = stream.readI();

    IENetworkName r;
    r.numOfSpareBits = flags & 0b111;
    r.addCi = static_cast<EAddCountryInitials>(flags >> 3 & 0b1);
    r.codingScheme = static_cast<ECodingScheme>(flags >> 4 & 0b111);
    r.textString = stream.readOctetString(length - 1);
    return r;
}

void IENetworkName::Encode(const IENetworkName &ie, OctetString &stream)
{
    bits::Ranged8(
        {{1, 0}, {3, static_cast<int>(ie.codingScheme)}, {1, static_cast<int>(ie.addCi)}, {3, ie.numOfSpareBits}});
    stream.append(ie.textString);
}

IEUesUsageSetting::IEUesUsageSetting(EUesUsageSetting value) : value(value)
{
}

IEUesUsageSetting IEUesUsageSetting::Decode(const OctetBuffer &stream, int length)
{
    IEUesUsageSetting r;
    r.value = static_cast<EUesUsageSetting>(stream.readI() & 0b1);
    return r;
}

void IEUesUsageSetting::Encode(const IEUesUsageSetting &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.value));
}

IEIntraN1ModeNasTransparentContainer::IEIntraN1ModeNasTransparentContainer(
    const octet4 &mac, ETypeOfCipheringAlgorithm cipheringAlg,
    ETypeOfIntegrityProtectionAlgorithm integrityProtectionAlg, uint8_t keySetIdentifierIn5G,
    ETypeOfSecurityContext tsc, EKeyAmfChangeFlag kacf, const octet &sequenceNumber)
    : mac(mac), cipheringAlg(cipheringAlg), integrityProtectionAlg(integrityProtectionAlg),
      keySetIdentifierIn5g(keySetIdentifierIn5G), tsc(tsc), kacf(kacf), sequenceNumber(sequenceNumber)
{
}

IEIntraN1ModeNasTransparentContainer IEIntraN1ModeNasTransparentContainer::Decode(const OctetBuffer &stream, int length)
{
    IEIntraN1ModeNasTransparentContainer res;
    res.mac = stream.read4();

    auto octet = stream.read();

    res.integrityProtectionAlg = static_cast<ETypeOfIntegrityProtectionAlgorithm>(bits::BitRange8<0, 3>(octet));
    res.cipheringAlg = static_cast<ETypeOfCipheringAlgorithm>(bits::BitRange8<4, 7>(octet));

    octet = stream.read();

    res.keySetIdentifierIn5g = bits::BitRange8<0, 2>(octet);
    res.tsc = static_cast<ETypeOfSecurityContext>(octet.bit(3));
    res.kacf = static_cast<EKeyAmfChangeFlag>(octet.bit(4));

    res.sequenceNumber = stream.read();
    return res;
}

void IEIntraN1ModeNasTransparentContainer::Encode(const IEIntraN1ModeNasTransparentContainer &ie, OctetString &stream)
{
    stream.appendOctet4(ie.mac);
    stream.appendOctet((static_cast<int>(ie.cipheringAlg) << 4) | static_cast<int>(ie.integrityProtectionAlg));
    stream.appendOctet(bits::Ranged8(
        {{3, 0}, {1, static_cast<int>(ie.kacf)}, {1, static_cast<int>(ie.tsc)}, {3, ie.keySetIdentifierIn5g}}));
    stream.appendOctet(ie.sequenceNumber);
}

IEAllowedPduSessionStatus::IEAllowedPduSessionStatus(std::bitset<16> psi) : psi(psi)
{
}

IEAllowedPduSessionStatus IEAllowedPduSessionStatus::Decode(const OctetBuffer &stream, int length)
{
    octet octet1 = stream.read();
    octet octet2 = stream.read();

    IEAllowedPduSessionStatus r;
    for (int i = 0; i < 8; i++)
        r.psi[i] = octet1.bit(i);
    for (int i = 0; i < 8; i++)
        r.psi[8 + i] = octet2.bit(i);
    r.psi[0] = false; // psi[0] is reserved
    return r;
}

void IEAllowedPduSessionStatus::Encode(const IEAllowedPduSessionStatus &ie, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, 0},
                                      {1, ie.psi[1]},
                                      {1, ie.psi[2]},
                                      {1, ie.psi[3]},
                                      {1, ie.psi[4]},
                                      {1, ie.psi[5]},
                                      {1, ie.psi[6]},
                                      {1, ie.psi[7]}}));
    stream.appendOctet(bits::Ranged8({{1, ie.psi[8]},
                                      {1, ie.psi[9]},
                                      {1, ie.psi[10]},
                                      {1, ie.psi[11]},
                                      {1, ie.psi[12]},
                                      {1, ie.psi[13]},
                                      {1, ie.psi[14]},
                                      {1, ie.psi[15]}}));
}

IEPduSessionStatus::IEPduSessionStatus(std::bitset<16> psi) : psi(psi)
{
}

IEPduSessionStatus IEPduSessionStatus::Decode(const OctetBuffer &stream, int length)
{
    octet octet1 = stream.read();
    octet octet2 = stream.read();

    IEPduSessionStatus r;
    for (int i = 0; i < 8; i++)
        r.psi[i] = octet1.bit(i);
    for (int i = 0; i < 8; i++)
        r.psi[8 + i] = octet2.bit(i);
    r.psi[0] = false; // psi[0] is reserved
    return r;
}

void IEPduSessionStatus::Encode(const IEPduSessionStatus &ie, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, 0},
                                      {1, ie.psi[1]},
                                      {1, ie.psi[2]},
                                      {1, ie.psi[3]},
                                      {1, ie.psi[4]},
                                      {1, ie.psi[5]},
                                      {1, ie.psi[6]},
                                      {1, ie.psi[7]}}));
    stream.appendOctet(bits::Ranged8({{1, ie.psi[8]},
                                      {1, ie.psi[9]},
                                      {1, ie.psi[10]},
                                      {1, ie.psi[11]},
                                      {1, ie.psi[12]},
                                      {1, ie.psi[13]},
                                      {1, ie.psi[14]},
                                      {1, ie.psi[15]}}));
}

IE5gsDrxParameters::IE5gsDrxParameters(EDrxValue value) : value(value)
{
}

IE5gsDrxParameters IE5gsDrxParameters::Decode(const OctetBuffer &stream, int length)
{
    IE5gsDrxParameters r;
    r.value = static_cast<EDrxValue>(stream.readI() & 0xF);
    return r;
}

void IE5gsDrxParameters::Encode(const IE5gsDrxParameters &ie, OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.value));
}

IE5gMmCapability::IE5gMmCapability(EEpcNasSupported s1Mode, EHandoverAttachSupported hoAttach,
                                   ELtePositioningProtocolCapability lpp)
    : s1Mode(s1Mode), hoAttach(hoAttach), lpp(lpp)
{
}

IE5gMmCapability IE5gMmCapability::Decode(const OctetBuffer &stream, int length)
{
    auto octet = stream.read();

    IE5gMmCapability r;
    r.s1Mode = static_cast<EEpcNasSupported>(octet.bit(0));
    r.hoAttach = static_cast<EHandoverAttachSupported>(octet.bit(1));
    r.lpp = static_cast<ELtePositioningProtocolCapability>(octet.bit(2));
    return r;
}

void IE5gMmCapability::Encode(const IE5gMmCapability &ie, OctetString &stream)
{
    int octet = 0;
    octet |= static_cast<int>(ie.lpp);
    octet <<= 1;
    octet |= static_cast<int>(ie.hoAttach);
    octet <<= 1;
    octet |= static_cast<int>(ie.s1Mode);
    stream.appendOctet(octet);
}

IESmPduDnRequestContainer::IESmPduDnRequestContainer(OctetString &&dnSpecificIdentity)
    : dnSpecificIdentity(std::move(dnSpecificIdentity))
{
}

IESmPduDnRequestContainer IESmPduDnRequestContainer::Decode(const OctetBuffer &stream, int length)
{
    IESmPduDnRequestContainer r;
    r.dnSpecificIdentity = stream.readOctetString(length);
    return r;
}

void IESmPduDnRequestContainer::Encode(const IESmPduDnRequestContainer &ie, OctetString &stream)
{
    stream.append(ie.dnSpecificIdentity);
}

IE5gsNetworkFeatureSupport::IE5gsNetworkFeatureSupport(
    EImsVoPs3gpp imsVoPs3Gpp, EImsVoPsN3gpp imsVoPsN3Gpp, EEmergencyServiceSupport3gppIndicator emc,
    EEmergencyServiceFallback3gppIndicator emf, EInterworkingWithoutN26InterfaceIndicator iwkN26, EMpsIndicator mpsi,
    const std::optional<EEmergencyServiceSupportNon3gppIndicator> &emcn3, const std::optional<EMcsIndicator> &mcsi)
    : imsVoPs3gpp(imsVoPs3Gpp), imsVoPsN3gpp(imsVoPsN3Gpp), emc(emc), emf(emf), iwkN26(iwkN26), mpsi(mpsi),
      emcn3(emcn3), mcsi(mcsi)
{
}

IE5gsNetworkFeatureSupport IE5gsNetworkFeatureSupport::Decode(const OctetBuffer &stream, int length)
{
    IE5gsNetworkFeatureSupport res;

    auto octet = stream.read();
    res.imsVoPs3gpp = static_cast<EImsVoPs3gpp>(octet.bit(0));
    res.imsVoPsN3gpp = static_cast<EImsVoPsN3gpp>(octet.bit(1));
    res.emc = static_cast<EEmergencyServiceSupport3gppIndicator>(bits::BitRange8<2, 3>(octet));
    res.emf = static_cast<EEmergencyServiceFallback3gppIndicator>(bits::BitRange8<4, 5>(octet));
    res.iwkN26 = static_cast<EInterworkingWithoutN26InterfaceIndicator>(octet.bit(6));
    res.mpsi = static_cast<EMpsIndicator>(octet.bit(7));

    if (length > 1)
    {
        octet = stream.read();
        res.emcn3 = static_cast<EEmergencyServiceSupportNon3gppIndicator>(octet.bit(0));
        res.mcsi = static_cast<EMcsIndicator>(octet.bit(1));
    }

    if (length > 2)
    {
        // The other octet is spare, if any.
        stream.read();
    }

    return res;
}

void IE5gsNetworkFeatureSupport::Encode(const IE5gsNetworkFeatureSupport &ie, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, static_cast<int>(ie.mpsi)},
                                      {1, static_cast<int>(ie.iwkN26)},
                                      {2, static_cast<int>(ie.emf)},
                                      {2, static_cast<int>(ie.emc)},
                                      {1, static_cast<int>(ie.imsVoPsN3gpp)},
                                      {1, static_cast<int>(ie.imsVoPs3gpp)}}));
    if (ie.emcn3.has_value() && ie.mcsi.has_value())
        stream.appendOctet(
            bits::Ranged8({{6, 0}, {1, static_cast<int>(ie.mcsi.value())}, {1, static_cast<int>(ie.emcn3.value())}}));
}

IEDnn::IEDnn(OctetString &&apn) : apn(std::move(apn))
{
}

IEDnn::IEDnn(const std::string &&apn) : apn{}
{
    // NOTE: apn must be ASCII and length < 256
    this->apn.appendOctet(static_cast<int>(apn.length()));
    for (char c : apn)
        this->apn.appendOctet(c);
}

IEDnn IEDnn::Decode(const OctetBuffer &stream, int length)
{
    IEDnn res;
    res.apn = stream.readOctetString(length);
    return res;
}

void IEDnn::Encode(const IEDnn &ie, OctetString &stream)
{
    stream.append(ie.apn);
}

IENssai::IENssai(std::vector<IESNssai> &&sNssais) : sNssais(std::move(sNssais))
{
}

IENssai IENssai::Decode(const OctetBuffer &stream, int length)
{
    IENssai r;
    DecodeListIe(stream, length, r.sNssais);
    return r;
}

void IENssai::Encode(const IENssai &ie, OctetString &stream)
{
    for (auto &x : ie.sNssais)
        EncodeIe4(x, stream);
}

IEPlmnList::IEPlmnList(std::vector<VPlmn> &&plmns) : plmns(std::move(plmns))
{
}

IEPlmnList IEPlmnList::Decode(const OctetBuffer &stream, int length)
{
    IEPlmnList r;
    DecodeListVal(stream, length, r.plmns);
    return r;
}

void IEPlmnList::Encode(const IEPlmnList &ie, OctetString &stream)
{
    for (auto &x : ie.plmns)
        VPlmn::Encode(x, stream);
}

IEEmergencyNumberList::IEEmergencyNumberList(OctetString &&rawData) : rawData(std::move(rawData))
{
}

IEEmergencyNumberList IEEmergencyNumberList::Decode(const OctetBuffer &stream, int length)
{
    IEEmergencyNumberList r;
    r.rawData = stream.readOctetString(length);
    return r;
}

void IEEmergencyNumberList::Encode(const IEEmergencyNumberList &ie, OctetString &stream)
{
    stream.append(ie.rawData);
}

IERejectedNssai::IERejectedNssai(std::vector<VRejectedSNssai> &&list) : list(std::move(list))
{
}

IERejectedNssai IERejectedNssai::Decode(const OctetBuffer &stream, int length)
{
    IERejectedNssai r;
    DecodeListVal(stream, length, r.list);
    return r;
}

void IERejectedNssai::Encode(const IERejectedNssai &ie, OctetString &stream)
{
    for (auto &x : ie.list)
        VRejectedSNssai::Encode(x, stream);
}

IEServiceAreaList::IEServiceAreaList(std::vector<VPartialServiceAreaList> &&list) : list(std::move(list))
{
}

IEServiceAreaList IEServiceAreaList::Decode(const OctetBuffer &stream, int length)
{
    IEServiceAreaList r;
    DecodeListVal(stream, length, r.list);
    return r;
}

void IEServiceAreaList::Encode(const IEServiceAreaList &ie, OctetString &stream)
{
    for (auto &x : ie.list)
        VPartialServiceAreaList::Encode(x, stream);
}

IE5gsTrackingAreaIdentityList::IE5gsTrackingAreaIdentityList(std::vector<VPartialTrackingAreaIdentityList> &&list)
    : list(std::move(list))
{
}

IE5gsTrackingAreaIdentityList IE5gsTrackingAreaIdentityList::Decode(const OctetBuffer &stream, int length)
{
    IE5gsTrackingAreaIdentityList r;
    DecodeListVal(stream, length, r.list);
    return r;
}

void IE5gsTrackingAreaIdentityList::Encode(const IE5gsTrackingAreaIdentityList &ie, OctetString &stream)
{
    for (auto &x : ie.list)
        VPartialTrackingAreaIdentityList::Encode(x, stream);
}

} // namespace nas