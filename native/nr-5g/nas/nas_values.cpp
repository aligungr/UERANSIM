//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "nas_values.hpp"
#include <octet_string.hpp>

namespace nas
{

void VAmfSetId::Encode(const VAmfSetId &value, OctetString &stream)
{
    stream.appendOctet(value.value >> 2);
    stream.appendOctet((value.value & 0b11) << 6);
}

VAmfSetId VAmfSetId::Decode(OctetBuffer &stream)
{
    int octet0 = stream.readI();
    int octet1 = stream.peekI();
    return VAmfSetId{(octet0 << 2) | (octet1 >> 6 & 0b11)};
}

void VPlmn::Encode(const VPlmn &value, OctetString &stream)
{
    int mcc = value.mcc;
    int mcc3 = mcc % 10;
    int mcc2 = (mcc % 100) / 10;
    int mcc1 = (mcc % 1000) / 100;

    int mnc = value.mnc;
    bool longMnc = value.isLongMnc;

    int mnc1 = longMnc ? (mnc % 1000) / 100 : (mnc % 100) / 10;
    int mnc2 = longMnc ? (mnc % 100) / 10 : (mnc % 10);
    int mnc3 = longMnc ? (mnc % 10) : 0xF;

    int octet1 = mcc2 << 4 | mcc1;
    int octet2 = mnc3 << 4 | mcc3;
    int octet3 = mnc2 << 4 | mnc1;

    stream.appendOctet(octet1);
    stream.appendOctet(octet2);
    stream.appendOctet(octet3);
}

VPlmn VPlmn::Decode(OctetBuffer &stream)
{
    bool longMnc = false;

    /* Decode MCC */
    int octet1 = stream.readI();
    int mcc1 = octet1 & 0b1111;
    int mcc2 = (octet1 >> 4) & 0b1111;
    int octet2 = stream.readI();
    int mcc3 = octet2 & 0b1111;
    int mcc = 100 * mcc1 + 10 * mcc2 + mcc3;

    /* Decode MNC */
    int mnc3 = (octet2 >> 4) & 0b1111;
    int octet3 = stream.readI();
    int mnc1 = octet3 & 0b1111;
    int mnc2 = (octet3 >> 4) & 0b1111;
    int mnc = 10 * mnc1 + mnc2;
    if ((mnc3 != 0xf) || (octet1 == 0xff && octet2 == 0xff && octet3 == 0xff))
    {
        mnc = 10 * mnc + mnc3;
        longMnc = true;
    }

    return VPlmn{mcc, mnc, longMnc};
}

void VQoSFlowParameter::Encode(const VQoSFlowParameter &value, OctetString &stream)
{
    stream.appendOctet(value.identifier);
    stream.appendOctet(value.content.length());
    stream.append(value.content);
}

VQoSFlowParameter VQoSFlowParameter::Decode(OctetBuffer &stream)
{
    uint8_t identifier = stream.read();
    uint8_t contentLength = stream.read();
    OctetString content = stream.readOctetString(contentLength);
    return VQoSFlowParameter{identifier, std::move(content)};
}

void VQoSFlowDescription::Encode(const VQoSFlowDescription &value, OctetString &stream)
{
    stream.appendOctet(value.qfi & 0b111111);
    stream.appendOctet(((int)value.opCode & 0b111) << 5);
    stream.appendOctet((value.eBit << 6) | (value.numOfParameters & 0b111111));
    for (auto &item : value.parameterList)
        VQoSFlowParameter::Encode(*item, stream);
}

VQoSFlowDescription VQoSFlowDescription::Decode(OctetBuffer &stream)
{
    int qfi = stream.readI() & 0b111111;
    int opCode = (stream.readI() >> 5) & 0b111;
    int numOfParameters = stream.peekI() & 0b111111;
    bool eBit = stream.read().bit(6);
    std::vector<std::unique_ptr<VQoSFlowParameter>> parametersList;
    parametersList.reserve(numOfParameters);
    for (int i = 0; i < numOfParameters; i++)
        parametersList.push_back(std::make_unique<VQoSFlowParameter>(std::move(VQoSFlowParameter::Decode(stream))));

    return VQoSFlowDescription{qfi, (EQoSOperationCode)opCode, numOfParameters, eBit, std::move(parametersList)};
}

void VTrackingAreaIdentity::Encode(const VTrackingAreaIdentity &value, OctetString &stream)
{
    VPlmn::Encode(value.plmn, stream);
    stream.appendOctet3(value.tac);
}

VTrackingAreaIdentity VTrackingAreaIdentity::Decode(OctetBuffer &stream)
{
    VPlmn plmn = VPlmn::Decode(stream);
    octet3 tac = stream.read3();
    return VTrackingAreaIdentity{plmn, tac};
}

void VTime::Encode(const VTime &value, OctetString &stream)
{
    stream.appendOctet(value.year);
    stream.appendOctet(value.month);
    stream.appendOctet(value.day);
    stream.appendOctet(value.hour);
    stream.appendOctet(value.minute);
    stream.appendOctet(value.second);
}

VTime VTime::Decode(OctetBuffer &stream)
{
    VTime time;
    time.year = stream.read();
    time.month = stream.read();
    time.day = stream.read();
    time.hour = stream.read();
    time.minute = stream.read();
    time.second = stream.read();
    return time;
}

void VRejectedSNssai::Encode(const VRejectedSNssai &value, OctetString &stream)
{
    int totalLength = 0;
    if (value.sd.has_value() && !value.sst.has_value())
    {
        // error: "sst must not be null if sd is not null" (currently ignoring)
    }
    if (value.sst.has_value())
        totalLength++;
    if (value.sd.has_value())
        totalLength += 3;

    int octet = totalLength << 4 | static_cast<int>(value.cause);
    stream.appendOctet(octet);

    if (value.sst.has_value())
        stream.appendOctet(value.sst.value());
    if (value.sd.has_value())
        stream.appendOctet3(value.sd.value());
}

VRejectedSNssai VRejectedSNssai::Decode(OctetBuffer &stream)
{
    VRejectedSNssai res;

    int octet = stream.readI();
    res.cause = static_cast<ERejectedSNssaiCause>(octet & 0xF);

    int length = octet >> 4 & 0xF;
    if (length >= 1)
        res.sst = stream.read();
    if (length >= 2)
        res.sd = stream.read3();
    return res;
}

void VPartialServiceAreaList::Encode(const VPartialServiceAreaList &value, OctetString &stream)
{
    switch (value.present)
    {
    case 0b00:
        VPartialServiceAreaList00::Encode(value.list00.value(), stream);
        break;
    case 0b01:
        VPartialServiceAreaList01::Encode(value.list01.value(), stream);
        break;
    case 0b10:
        VPartialServiceAreaList10::Encode(value.list10.value(), stream);
        break;
    case 0b11:
        VPartialServiceAreaList11::Encode(value.list11.value(), stream);
        break;
    default:
        break;
    }
}

VPartialServiceAreaList VPartialServiceAreaList::Decode(OctetBuffer &stream)
{
    auto octet = stream.peek();

    VPartialServiceAreaList res;
    res.present = bits::BitRange8<5, 6>(octet);
    switch (res.present)
    {
    case 0b00:
        res.list00 = VPartialServiceAreaList00::Decode(stream);
        break;
    case 0b01:
        res.list01 = VPartialServiceAreaList01::Decode(stream);
        break;
    case 0b10:
        res.list10 = VPartialServiceAreaList10::Decode(stream);
        break;
    case 0b11:
        res.list11 = VPartialServiceAreaList11::Decode(stream);
        break;
    default:
        break;
    }
    return res;
}

void VPartialServiceAreaList00::Encode(const VPartialServiceAreaList00 &value, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8(
        {{1, static_cast<int>(value.allowedType)}, {2, 0b00}, {5, static_cast<int>(value.tacs.size()) - 1}}));

    VPlmn::Encode(value.plmn, stream);
    for (auto &tac : value.tacs)
        stream.appendOctet3(tac);
}

VPartialServiceAreaList00 VPartialServiceAreaList00::Decode(OctetBuffer &stream)
{
    auto octet = stream.read();
    auto allowed = static_cast<EAllowedType>(octet.bit(7));
    int count = bits::BitRange8<0, 4>(octet) + 1;

    auto plmn = VPlmn::Decode(stream);
    std::vector<octet3> tacs;
    for (int i = 0; i < count; i++)
        tacs.push_back(stream.read3());
    return VPartialServiceAreaList00{allowed, plmn, std::move(tacs)};
}

void VPartialServiceAreaList01::Encode(const VPartialServiceAreaList01 &value, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, static_cast<int>(value.allowedType)}, {2, 0b01}, {5, 0}}));
    VPlmn::Encode(value.plmn, stream);
    stream.appendOctet3(value.tac);
}

VPartialServiceAreaList01 VPartialServiceAreaList01::Decode(OctetBuffer &stream)
{
    auto octet = stream.read();
    auto allowed = static_cast<EAllowedType>(octet.bit(7));
    return VPartialServiceAreaList01{allowed, VPlmn::Decode(stream), stream.read3()};
}

void VPartialServiceAreaList10::Encode(const VPartialServiceAreaList10 &value, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8(
        {{1, static_cast<int>(value.allowedType)}, {2, 0b10}, {5, static_cast<int>(value.tais.size()) - 1}}));
    for (auto &tai : value.tais)
        VTrackingAreaIdentity::Encode(tai, stream);
}

VPartialServiceAreaList10 VPartialServiceAreaList10::Decode(OctetBuffer &stream)
{
    auto octet = stream.read();
    auto allowed = static_cast<EAllowedType>(octet.bit(7));
    int count = bits::BitRange8<0, 4>(octet) + 1;

    std::vector<VTrackingAreaIdentity> list;
    for (int i = 0; i < count; i++)
        list.push_back(VTrackingAreaIdentity::Decode(stream));
    return VPartialServiceAreaList10{allowed, std::move(list)};
}

void VPartialServiceAreaList11::Encode(const VPartialServiceAreaList11 &value, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, static_cast<int>(value.allowedType)}, {2, 0b11}, {5, 0}}));
    VPlmn::Encode(value.plmn, stream);
}

VPartialServiceAreaList11 VPartialServiceAreaList11::Decode(OctetBuffer &stream)
{
    auto octet = stream.read();
    auto allowed = static_cast<EAllowedType>(octet.bit(7));
    return VPartialServiceAreaList11{allowed, VPlmn::Decode(stream)};
}

void VPartialTrackingAreaIdentityList00::Encode(const VPartialTrackingAreaIdentityList00 &value, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, 0}, {2, 0b00}, {5, static_cast<int>(value.tacs.size()) - 1}}));
    VPlmn::Encode(value.plmn, stream);
    for (auto &x : value.tacs)
        stream.appendOctet3(x);
}

VPartialTrackingAreaIdentityList00 VPartialTrackingAreaIdentityList00::Decode(OctetBuffer &stream)
{
    auto octet = stream.read();
    int count = bits::BitRange8<0, 4>(octet) + 1;

    auto plmn = VPlmn::Decode(stream);
    std::vector<octet3> tacs;
    for (int i = 0; i < count; i++)
        tacs.push_back(stream.read3());
    return VPartialTrackingAreaIdentityList00{plmn, std::move(tacs)};
}

void VPartialTrackingAreaIdentityList01::Encode(const VPartialTrackingAreaIdentityList01 &value, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, 0}, {2, 0b01}, {5, 0}}));
    VPlmn::Encode(value.plmn, stream);
    stream.appendOctet3(value.tac);
}

VPartialTrackingAreaIdentityList01 VPartialTrackingAreaIdentityList01::Decode(OctetBuffer &stream)
{
    stream.read();
    return VPartialTrackingAreaIdentityList01{VPlmn::Decode(stream), stream.read3()};
}

void VPartialTrackingAreaIdentityList10::Encode(const VPartialTrackingAreaIdentityList10 &value, OctetString &stream)
{
    stream.appendOctet(bits::Ranged8({{1, 0}, {2, 0b10}, {5, static_cast<int>(value.tais.size()) - 1}}));
    for (auto &x : value.tais)
        VTrackingAreaIdentity::Encode(x, stream);
}

VPartialTrackingAreaIdentityList10 VPartialTrackingAreaIdentityList10::Decode(OctetBuffer &stream)
{
    auto octet = stream.read();
    int count = bits::BitRange8<0, 4>(octet) + 1;

    std::vector<VTrackingAreaIdentity> tacs;
    for (int i = 0; i < count; i++)
        tacs.push_back(VTrackingAreaIdentity::Decode(stream));
    return VPartialTrackingAreaIdentityList10{std::move(tacs)};
}

void VPartialTrackingAreaIdentityList::Encode(const VPartialTrackingAreaIdentityList &value, OctetString &stream)
{
    switch (value.present)
    {
    case 0b00:
        VPartialTrackingAreaIdentityList00::Encode(value.list00.value(), stream);
        break;
    case 0b01:
        VPartialTrackingAreaIdentityList01::Encode(value.list01.value(), stream);
        break;
    case 0b10:
        VPartialTrackingAreaIdentityList10::Encode(value.list10.value(), stream);
        break;
    default:
        break;
    }
}

VPartialTrackingAreaIdentityList VPartialTrackingAreaIdentityList::Decode(OctetBuffer &stream)
{
    auto octet = stream.peek();

    VPartialTrackingAreaIdentityList res;
    res.present = bits::BitRange8<5, 6>(octet);
    switch (res.present)
    {
    case 0b00:
        res.list00 = VPartialTrackingAreaIdentityList00::Decode(stream);
        break;
    case 0b01:
        res.list01 = VPartialTrackingAreaIdentityList01::Decode(stream);
        break;
    case 0b10:
        res.list10 = VPartialTrackingAreaIdentityList10::Decode(stream);
        break;
    default:
        break;
    }
    return res;
}

} // namespace nas
