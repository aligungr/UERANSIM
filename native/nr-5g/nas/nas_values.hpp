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
#include <utility>

#include "nas_enums.hpp"

namespace nas
{

struct VAmfSetId
{
    int value : 10;

    explicit VAmfSetId(int value) : value(value)
    {
    }

    static void Encode(const VAmfSetId &value, OctetString &stream);
    static VAmfSetId Decode(OctetBuffer &stream);
};

struct VPlmn
{
    int mcc;
    int mnc;
    bool isLongMnc;

    VPlmn(int mcc, int mnc, bool isLongMnc) : mcc(mcc), mnc(mnc), isLongMnc(isLongMnc)
    {
    }

    static void Encode(const VPlmn &value, OctetString &stream);
    static VPlmn Decode(OctetBuffer &stream);
};

struct VQoSFlowParameter
{
    uint8_t identifier;
    OctetString content;

    VQoSFlowParameter(uint8_t identifier, OctetString content) : identifier(identifier), content(std::move(content))
    {
    }

    static void Encode(const VQoSFlowParameter &value, OctetString &stream);
    static VQoSFlowParameter Decode(OctetBuffer &stream);
};

struct VQoSFlowDescription
{
    int qfi : 6;
    EQoSOperationCode opCode;
    int numOfParameters : 6;
    bool eBit;
    std::vector<std::unique_ptr<VQoSFlowParameter>> parameterList;

    VQoSFlowDescription(int qfi, EQoSOperationCode opCode, int numOfParameters, bool eBit,
                        std::vector<std::unique_ptr<VQoSFlowParameter>> parameterList)
        : qfi(qfi), opCode(opCode), numOfParameters(numOfParameters), eBit(eBit),
          parameterList(std::move(parameterList))
    {
    }

    static void Encode(const VQoSFlowDescription &value, OctetString &stream);
    static VQoSFlowDescription Decode(OctetBuffer &stream);
};

struct VTrackingAreaIdentity
{
    VPlmn plmn;
    octet3 tac;

    VTrackingAreaIdentity(const VPlmn &plmn, const octet3 &tac) : plmn(plmn), tac(tac)
    {
    }

    static void Encode(const VTrackingAreaIdentity &value, OctetString &stream);
    static VTrackingAreaIdentity Decode(OctetBuffer &stream);
};

struct VTime
{
    octet year;
    octet month;
    octet day;
    octet hour;
    octet minute;
    octet second;

    VTime() = default;

    VTime(const octet &year, const octet &month, const octet &day, const octet &hour, const octet &minute,
          const octet &second)
        : year(year), month(month), day(day), hour(hour), minute(minute), second(second)
    {
    }

    static void Encode(const VTime &value, OctetString &stream);
    static VTime Decode(OctetBuffer &stream);
};

} // namespace nas
