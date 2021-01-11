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

} // namespace nas
