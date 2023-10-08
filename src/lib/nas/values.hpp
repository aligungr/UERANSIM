//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "enums.hpp"

#include <optional>
#include <utility>

#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>
#include <utils/common_types.hpp>

namespace nas
{

struct VAmfSetId
{
    int value; // 10-bit

    explicit VAmfSetId(int value);

    static void Encode(const VAmfSetId &value, OctetString &stream);
    static VAmfSetId Decode(const OctetView &stream);
};

struct VPlmn
{
    int mcc;
    int mnc;
    bool isLongMnc;

    VPlmn(int mcc, int mnc, bool isLongMnc);

    static void Encode(const VPlmn &value, OctetString &stream);
    static VPlmn Decode(const OctetView &stream);
};

struct VQoSFlowParameter
{
    uint8_t identifier;
    OctetString content;

    VQoSFlowParameter(uint8_t identifier, OctetString content);

    static void Encode(const VQoSFlowParameter &value, OctetString &stream);
    static VQoSFlowParameter Decode(const OctetView &stream);
};

struct VQoSFlowDescription
{
    int qfi; // 6-bit
    EQoSOperationCode opCode;
    int numOfParameters; // 6-bit
    bool eBit;
    std::vector<std::unique_ptr<VQoSFlowParameter>> parameterList;

    VQoSFlowDescription(int qfi, EQoSOperationCode opCode, int numOfParameters, bool eBit,
                        std::vector<std::unique_ptr<VQoSFlowParameter>> parameterList);

    static void Encode(const VQoSFlowDescription &value, OctetString &stream);
    static VQoSFlowDescription Decode(const OctetView &stream);
};

struct VTrackingAreaIdentity
{
    VPlmn plmn;
    octet3 tac;

    VTrackingAreaIdentity(const VPlmn &plmn, const octet3 &tac);
    explicit VTrackingAreaIdentity(const Tai &tai);

    static void Encode(const VTrackingAreaIdentity &value, OctetString &stream);
    static VTrackingAreaIdentity Decode(const OctetView &stream);
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
          const octet &second);

    static void Encode(const VTime &value, OctetString &stream);
    static VTime Decode(const OctetView &stream);
};

struct VRejectedSNssai
{
    ERejectedSNssaiCause cause{};
    octet sst{};
    std::optional<octet3> sd{};

    VRejectedSNssai() = default;

    VRejectedSNssai(ERejectedSNssaiCause cause, octet sst, const std::optional<octet3> &sd);

    static void Encode(const VRejectedSNssai &value, OctetString &stream);
    static VRejectedSNssai Decode(const OctetView &stream);
};

struct VPartialServiceAreaList00
{
    EAllowedType allowedType;
    VPlmn plmn;
    std::vector<octet3> tacs;

    VPartialServiceAreaList00(EAllowedType allowedType, const VPlmn &plmn, std::vector<octet3> &&tacs);

    static void Encode(const VPartialServiceAreaList00 &value, OctetString &stream);
    static VPartialServiceAreaList00 Decode(const OctetView &stream);
};

struct VPartialServiceAreaList01
{
    EAllowedType allowedType;
    VPlmn plmn;
    octet3 tac;

    VPartialServiceAreaList01(EAllowedType allowedType, const VPlmn &plmn, const octet3 &tac);

    static void Encode(const VPartialServiceAreaList01 &value, OctetString &stream);
    static VPartialServiceAreaList01 Decode(const OctetView &stream);
};

struct VPartialServiceAreaList10
{
    EAllowedType allowedType;
    std::vector<VTrackingAreaIdentity> tais;

    VPartialServiceAreaList10(EAllowedType allowedType, std::vector<VTrackingAreaIdentity> &&tais);

    static void Encode(const VPartialServiceAreaList10 &value, OctetString &stream);
    static VPartialServiceAreaList10 Decode(const OctetView &stream);
};

struct VPartialServiceAreaList11
{
    EAllowedType allowedType;
    VPlmn plmn;

    VPartialServiceAreaList11(EAllowedType allowedType, const VPlmn &plmn);

    static void Encode(const VPartialServiceAreaList11 &value, OctetString &stream);
    static VPartialServiceAreaList11 Decode(const OctetView &stream);
};

struct VPartialServiceAreaList
{
    uint8_t present : 2;
    std::optional<VPartialServiceAreaList00> list00{};
    std::optional<VPartialServiceAreaList01> list01{};
    std::optional<VPartialServiceAreaList10> list10{};
    std::optional<VPartialServiceAreaList11> list11{};

    VPartialServiceAreaList();

    static void Encode(const VPartialServiceAreaList &value, OctetString &stream);
    static VPartialServiceAreaList Decode(const OctetView &stream);
};

struct VPartialTrackingAreaIdentityList00
{
    VPlmn plmn;
    std::vector<octet3> tacs;

    VPartialTrackingAreaIdentityList00(const VPlmn &plmn, std::vector<octet3> &&tacs);

    static void Encode(const VPartialTrackingAreaIdentityList00 &value, OctetString &stream);
    static VPartialTrackingAreaIdentityList00 Decode(const OctetView &stream);
};

struct VPartialTrackingAreaIdentityList01
{
    VPlmn plmn;
    octet3 tac;

    VPartialTrackingAreaIdentityList01(const VPlmn &plmn, octet3 tac);

    static void Encode(const VPartialTrackingAreaIdentityList01 &value, OctetString &stream);
    static VPartialTrackingAreaIdentityList01 Decode(const OctetView &stream);
};

struct VPartialTrackingAreaIdentityList10
{
    std::vector<VTrackingAreaIdentity> tais;

    explicit VPartialTrackingAreaIdentityList10(std::vector<VTrackingAreaIdentity> &&tais);

    static void Encode(const VPartialTrackingAreaIdentityList10 &value, OctetString &stream);
    static VPartialTrackingAreaIdentityList10 Decode(const OctetView &stream);
};

struct VPartialTrackingAreaIdentityList
{
    uint8_t present : 2;
    std::optional<VPartialTrackingAreaIdentityList00> list00{};
    std::optional<VPartialTrackingAreaIdentityList01> list01{};
    std::optional<VPartialTrackingAreaIdentityList10> list10{};

    VPartialTrackingAreaIdentityList();

    static void Encode(const VPartialTrackingAreaIdentityList &value, OctetString &stream);
    static VPartialTrackingAreaIdentityList Decode(const OctetView &stream);
};

struct VPduSessionReactivationResultErrorCause
{
    int pduSessionId;
    EMmCause causeValue;

    VPduSessionReactivationResultErrorCause(int pduSessionId, EMmCause causeValue);

    static void Encode(const VPduSessionReactivationResultErrorCause &value, OctetString &stream);
    static VPduSessionReactivationResultErrorCause Decode(const OctetView &stream);
};

struct VOperatorDefinedAccessCategoryDefinition
{
    octet precedence{};
    uint8_t operatorDefinedAccessCategoryNumber{}; // 5-bit
    EPresenceOfStandardizedAccessCategory psac{};
    OctetString criteria{};
    uint8_t standardizedAccessCategory{}; // 5-bit

    VOperatorDefinedAccessCategoryDefinition(const octet &precedence, uint8_t operatorDefinedAccessCategoryNumber,
                                             EPresenceOfStandardizedAccessCategory psac, OctetString &&criteria,
                                             uint8_t standardizedAccessCategory);

    static void Encode(const VOperatorDefinedAccessCategoryDefinition &value, OctetString &stream);
    static VOperatorDefinedAccessCategoryDefinition Decode(const OctetView &stream);
};

struct VPlmnIdAccessTech
{
    VPlmn plmnId;
    octet2 accessTechnologyIdentifier;

    VPlmnIdAccessTech(const VPlmn &plmnId, const octet2 &accessTechnologyIdentifier);

    static void Encode(const VPlmnIdAccessTech &value, OctetString &stream);
    static VPlmnIdAccessTech Decode(const OctetView &stream);
};

} // namespace nas
