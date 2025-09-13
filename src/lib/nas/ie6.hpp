//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "base.hpp"
#include "eap.hpp"
#include "ie4.hpp"
#include "values.hpp"

#include <utils/common_types.hpp>
#include <utils/json.hpp>

namespace nas
{

struct IEQoSFlowDescriptions : InformationElement6
{
    std::vector<VQoSFlowDescription> list;

    IEQoSFlowDescriptions() = default;
    explicit IEQoSFlowDescriptions(std::vector<VQoSFlowDescription> &&list);

    static IEQoSFlowDescriptions Decode(const OctetView &stream, int length);
    static void Encode(const IEQoSFlowDescriptions &ie, OctetString &stream);
    static void Mutate(IEQoSFlowDescriptions &ie);
};

struct IEPayloadContainer : InformationElement6
{
    OctetString data;

    IEPayloadContainer() = default;
    explicit IEPayloadContainer(OctetString &&data);

    static IEPayloadContainer Decode(const OctetView &stream, int length);
    static void Encode(const IEPayloadContainer &ie, OctetString &stream);
    static void Mutate(IEPayloadContainer &ie);
};

struct IEExtendedEmergencyNumberList : InformationElement6
{
    OctetString data;

    IEExtendedEmergencyNumberList() = default;
    explicit IEExtendedEmergencyNumberList(OctetString &&data);

    static IEExtendedEmergencyNumberList Decode(const OctetView &stream, int length);
    static void Encode(const IEExtendedEmergencyNumberList &ie, OctetString &stream);
    static void Mutate(IEExtendedEmergencyNumberList &ie);
};

struct IEEpsNasMessageContainer : InformationElement6
{
    OctetString data;

    IEEpsNasMessageContainer() = default;
    explicit IEEpsNasMessageContainer(OctetString &&data);

    static IEEpsNasMessageContainer Decode(const OctetView &stream, int length);
    static void Encode(const IEEpsNasMessageContainer &ie, OctetString &stream);
    static void Mutate(IEEpsNasMessageContainer &ie);
};

struct IENasMessageContainer : InformationElement6
{
    OctetString data;

    IENasMessageContainer() = default;
    explicit IENasMessageContainer(OctetString &&data);

    static IENasMessageContainer Decode(const OctetView &stream, int length);
    static void Encode(const IENasMessageContainer &ie, OctetString &stream);
    static void Mutate(IENasMessageContainer &ie);
};

struct IEExtendedProtocolConfigurationOptions : InformationElement6
{
    EConfigurationProtocol configurationProtocol{};
    bool extension{};
    OctetString options{};

    IEExtendedProtocolConfigurationOptions() = default;
    IEExtendedProtocolConfigurationOptions(EConfigurationProtocol configurationProtocol, bool extension,
                                           OctetString &&options);

    static IEExtendedProtocolConfigurationOptions Decode(const OctetView &stream, int length);
    static void Encode(const IEExtendedProtocolConfigurationOptions &ie, OctetString &stream);
    static void Mutate(IEExtendedProtocolConfigurationOptions &ie);
};

struct IEPduSessionReactivationResultErrorCause : InformationElement6
{
    std::vector<VPduSessionReactivationResultErrorCause> values;

    IEPduSessionReactivationResultErrorCause() = default;
    explicit IEPduSessionReactivationResultErrorCause(std::vector<VPduSessionReactivationResultErrorCause> &&values);

    static IEPduSessionReactivationResultErrorCause Decode(const OctetView &stream, int length);
    static void Encode(const IEPduSessionReactivationResultErrorCause &ie, OctetString &stream);
    static void Mutate(IEPduSessionReactivationResultErrorCause &ie);
};

struct IELadnIndication : InformationElement6
{
    std::vector<IEDnn> values;

    IELadnIndication() = default;
    explicit IELadnIndication(std::vector<IEDnn> &&values);

    static IELadnIndication Decode(const OctetView &stream, int length);
    static void Encode(const IELadnIndication &ie, OctetString &stream);
    static void Mutate(IELadnIndication &ie);
};

// Placed here because of the fwd decl issues.
struct VLadn
{
    IEDnn dnn;
    IE5gsTrackingAreaIdentityList trackingAreaIdentityList;

    VLadn(IEDnn &&dnn, IE5gsTrackingAreaIdentityList &&trackingAreaIdentityList);

    static void Encode(const VLadn &value, OctetString &stream);
    static VLadn Decode(const OctetView &stream);
    static void Mutate(VLadn &value);
};  

struct IELadnInformation : InformationElement6
{
    std::vector<VLadn> ladnList;

    IELadnInformation() = default;
    explicit IELadnInformation(std::vector<VLadn> &&values);

    static IELadnInformation Decode(const OctetView &stream, int length);
    static void Encode(const IELadnInformation &ie, OctetString &stream);
    static void Mutate(IELadnInformation &ie);
};

struct IEOperatorDefinedAccessCategoryDefinitions : InformationElement6
{
    std::vector<VOperatorDefinedAccessCategoryDefinition> values;

    IEOperatorDefinedAccessCategoryDefinitions() = default;
    explicit IEOperatorDefinedAccessCategoryDefinitions(std::vector<VOperatorDefinedAccessCategoryDefinition> &&values);

    static IEOperatorDefinedAccessCategoryDefinitions Decode(const OctetView &stream, int length);
    static void Encode(const IEOperatorDefinedAccessCategoryDefinitions &ie, OctetString &stream);
    static void Mutate(IEOperatorDefinedAccessCategoryDefinitions &ie);
};

struct IEMappedEpsBearerContexts : InformationElement6
{
    OctetString data;

    IEMappedEpsBearerContexts() = default;
    explicit IEMappedEpsBearerContexts(OctetString &&data);

    static IEMappedEpsBearerContexts Decode(const OctetView &stream, int length);
    static void Encode(const IEMappedEpsBearerContexts &ie, OctetString &stream);
    static void Mutate(IEMappedEpsBearerContexts &ie);
};

struct IEQoSRules : InformationElement6
{
    OctetString data;

    IEQoSRules() = default;
    explicit IEQoSRules(OctetString &&data);

    static IEQoSRules Decode(const OctetView &stream, int length);
    static void Encode(const IEQoSRules &ie, OctetString &stream);
    static void Mutate(IEQoSRules &ie);
};

struct IESorTransparentContainer : InformationElement6
{
    ESorDataType sorDataType{};
    ESorListIndication listIndication{};   // meaningful if sorDataType is STEERING_OF_ROAMING_INFORMATION
    ESorListType listType{};               // meaningful if listIndication is PROVIDED
    EAcknowledgement ack{};                // meaningful if sorDataType is STEERING_OF_ROAMING_INFORMATION
    OctetString sorMacIAusf{};             // meaningful if sorDataType is STEERING_OF_ROAMING_INFORMATION
    octet2 counterSor{};                   // meaningful if sorDataType is STEERING_OF_ROAMING_INFORMATION
    OctetString sorMacIUe{};               // meaningful if sorDataType is ACKNOWLEDGEMENT
    OctetString securedPacket{};           // meaningful if listType is SECURED_PACKET
    std::vector<VPlmnIdAccessTech> piat{}; // meaningful if listType is PLMN_ID_AND_ACCESS_TECH

    static IESorTransparentContainer Decode(const OctetView &stream, int length);
    static void Encode(const IESorTransparentContainer &ie, OctetString &stream);
    static void Mutate(IESorTransparentContainer &ie);
};

struct IE5gsMobileIdentity : InformationElement6
{
    EIdentityType type{};
    ESupiFormat supiFormat; // meaningful if identityType is SUCI

    // using at most one of the following 3 members:
    GutiMobileIdentity gutiOrTmsi{}; // used for TMSI and GUTI (some members not exist in TMSI)
    std::string value{};             // used for IMEI, IMEI-SV, NSI,
    ImsiMobileIdentity imsi{};       // used for IMSI

    static IE5gsMobileIdentity Decode(const OctetView &stream, int length);
    static void Encode(const IE5gsMobileIdentity &ie, OctetString &stream);
    static void Mutate(IE5gsMobileIdentity &ie);
};

struct IEEapMessage : InformationElement6
{
    std::unique_ptr<eap::Eap> eap{};

    static IEEapMessage Decode(const OctetView &stream, int length);
    static void Encode(const IEEapMessage &ie, OctetString &stream);
    static void Mutate(IEEapMessage &ie);
};

Json ToJson(const IE5gsMobileIdentity &v);

} // namespace nas
