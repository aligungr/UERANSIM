//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "ie6.hpp"

// fuzzing
#include "nas_mutator.hpp"
#include <ue/app/state_learner.hpp>

namespace nas
{

IEQoSFlowDescriptions::IEQoSFlowDescriptions(std::vector<VQoSFlowDescription> &&list) : list(std::move(list))
{
}

IEQoSFlowDescriptions IEQoSFlowDescriptions::Decode(const OctetView &stream, int length)
{
    IEQoSFlowDescriptions r;
    DecodeListVal(stream, length, r.list);
    return r;
}

void IEQoSFlowDescriptions::Encode(const IEQoSFlowDescriptions &ie, OctetString &stream)
{
    for (auto &x : ie.list)
        VQoSFlowDescription::Encode(x, stream);
}

void IEQoSFlowDescriptions::Mutate(IEQoSFlowDescriptions &ie)
{
    for (auto &x : ie.list)
        VQoSFlowDescription::Mutate(x);
}

IEPayloadContainer::IEPayloadContainer(OctetString &&data) : data(std::move(data))
{
}

IEPayloadContainer IEPayloadContainer::Decode(const OctetView &stream, int length)
{
    return IEPayloadContainer{stream.readOctetString(length)};
}

void IEPayloadContainer::Encode(const IEPayloadContainer &ie, OctetString &stream)
{
    stream.append(ie.data);
}

void IEPayloadContainer::Mutate(IEPayloadContainer &ie)
{
    mutate_octet_string(ie.data);
}

IEExtendedEmergencyNumberList::IEExtendedEmergencyNumberList(OctetString &&data) : data(std::move(data))
{
}

IEExtendedEmergencyNumberList IEExtendedEmergencyNumberList::Decode(const OctetView &stream, int length)
{
    return IEExtendedEmergencyNumberList{stream.readOctetString(length)};
}

void IEExtendedEmergencyNumberList::Encode(const IEExtendedEmergencyNumberList &ie, OctetString &stream)
{
    stream.append(ie.data);
}

void IEExtendedEmergencyNumberList::Mutate(IEExtendedEmergencyNumberList &ie)
{
    mutate_octet_string(ie.data);
}

IEEpsNasMessageContainer::IEEpsNasMessageContainer(OctetString &&data) : data(std::move(data))
{
}

IEEpsNasMessageContainer IEEpsNasMessageContainer::Decode(const OctetView &stream, int length)
{
    return IEEpsNasMessageContainer{stream.readOctetString(length)};
}

void IEEpsNasMessageContainer::Encode(const IEEpsNasMessageContainer &ie, OctetString &stream)
{
    stream.append(ie.data);
}

void IEEpsNasMessageContainer::Mutate(IEEpsNasMessageContainer &ie)
{
    mutate_octet_string(ie.data);
}

IENasMessageContainer::IENasMessageContainer(OctetString &&data) : data(std::move(data))
{
}

IENasMessageContainer IENasMessageContainer::Decode(const OctetView &stream, int length)
{
    return IENasMessageContainer{stream.readOctetString(length)};
}

void IENasMessageContainer::Encode(const IENasMessageContainer &ie, OctetString &stream)
{
    stream.append(ie.data);
}

void IENasMessageContainer::Mutate(IENasMessageContainer &ie)
{
    mutate_octet_string(ie.data);
}

IEExtendedProtocolConfigurationOptions::IEExtendedProtocolConfigurationOptions(
    EConfigurationProtocol configurationProtocol, bool extension, OctetString &&options)
    : configurationProtocol(configurationProtocol), extension(extension), options(std::move(options))
{
}

IEExtendedProtocolConfigurationOptions IEExtendedProtocolConfigurationOptions::Decode(const OctetView &stream,
                                                                                      int length)
{
    auto octet = stream.read();

    IEExtendedProtocolConfigurationOptions r;
    r.configurationProtocol = static_cast<EConfigurationProtocol>(bits::BitRange8<0, 2>(octet));
    r.extension = octet.bit(7);
    r.options = stream.readOctetString(length - 1);
    return r;
}

void IEExtendedProtocolConfigurationOptions::Encode(const IEExtendedProtocolConfigurationOptions &ie,
                                                    OctetString &stream)
{
    stream.appendOctet(static_cast<int>(ie.configurationProtocol) | ((ie.extension ? 1 : 0) << 7));
    stream.append(ie.options);
}

void IEExtendedProtocolConfigurationOptions::Mutate(IEExtendedProtocolConfigurationOptions &ie)
{
    ie.configurationProtocol = static_cast<EConfigurationProtocol>(generate_bit(3));
    ie.extension = generate_bit(1);
    mutate_octet_string(ie.options);
}

IEPduSessionReactivationResultErrorCause::IEPduSessionReactivationResultErrorCause(
    std::vector<VPduSessionReactivationResultErrorCause> &&values)
    : values(std::move(values))
{
}

IEPduSessionReactivationResultErrorCause IEPduSessionReactivationResultErrorCause::Decode(const OctetView &stream,
                                                                                          int length)
{
    IEPduSessionReactivationResultErrorCause r;
    DecodeListVal(stream, length, r.values);
    return r;
}

void IEPduSessionReactivationResultErrorCause::Encode(const IEPduSessionReactivationResultErrorCause &ie,
                                                      OctetString &stream)
{
    for (auto &x : ie.values)
        VPduSessionReactivationResultErrorCause::Encode(x, stream);
}

void IEPduSessionReactivationResultErrorCause::Mutate(IEPduSessionReactivationResultErrorCause &ie)
{
    for (auto &x : ie.values)
        VPduSessionReactivationResultErrorCause::Mutate(x);
}

IELadnIndication::IELadnIndication(std::vector<IEDnn> &&values) : values(std::move(values))
{
}

IELadnIndication IELadnIndication::Decode(const OctetView &stream, int length)
{
    IELadnIndication r;
    DecodeListIe(stream, length, r.values);
    return r;
}

void IELadnIndication::Encode(const IELadnIndication &ie, OctetString &stream)
{
    for (auto &x : ie.values)
        Encode2346(x, stream);
}

void IELadnIndication::Mutate(IELadnIndication &ie)
{
    for (auto &x : ie.values)
        Mutate2346(x);
}

VLadn::VLadn(IEDnn &&dnn, IE5gsTrackingAreaIdentityList &&trackingAreaIdentityList)
    : dnn(std::move(dnn)), trackingAreaIdentityList(std::move(trackingAreaIdentityList))
{
}

void VLadn::Encode(const VLadn &value, OctetString &stream)
{
    Encode2346(value.dnn, stream);
    Encode2346(value.trackingAreaIdentityList, stream);
}

VLadn VLadn::Decode(const OctetView &stream)
{
    return VLadn{DecodeIe2346<IEDnn>(stream), DecodeIe2346<IE5gsTrackingAreaIdentityList>(stream)};
}

void VLadn::Mutate(VLadn &value)
{
    Mutate2346(value.dnn);
    Mutate2346(value.trackingAreaIdentityList);
}

IELadnInformation::IELadnInformation(std::vector<VLadn> &&ladnList) : ladnList(std::move(ladnList))
{
}

IELadnInformation IELadnInformation::Decode(const OctetView &stream, int length)
{
    IELadnInformation r;
    DecodeListVal(stream, length, r.ladnList);
    return r;
}

void IELadnInformation::Encode(const IELadnInformation &ie, OctetString &stream)
{
    for (auto &x : ie.ladnList)
        VLadn::Encode(x, stream);
}

void IELadnInformation::Mutate(IELadnInformation &ie)
{
    for (auto &x : ie.ladnList)
        VLadn::Mutate(x);
}

IEOperatorDefinedAccessCategoryDefinitions::IEOperatorDefinedAccessCategoryDefinitions(
    std::vector<VOperatorDefinedAccessCategoryDefinition> &&values)
    : values(std::move(values))
{
}

IEOperatorDefinedAccessCategoryDefinitions IEOperatorDefinedAccessCategoryDefinitions::Decode(const OctetView &stream,
                                                                                              int length)
{
    IEOperatorDefinedAccessCategoryDefinitions r;
    DecodeListVal(stream, length, r.values);
    return r;
}

void IEOperatorDefinedAccessCategoryDefinitions::Encode(const IEOperatorDefinedAccessCategoryDefinitions &ie,
                                                        OctetString &stream)
{
    for (auto &x : ie.values)
        VOperatorDefinedAccessCategoryDefinition::Encode(x, stream);
}

void IEOperatorDefinedAccessCategoryDefinitions::Mutate(IEOperatorDefinedAccessCategoryDefinitions &ie)
{
    for (auto &x : ie.values)
        VOperatorDefinedAccessCategoryDefinition::Mutate(x);
}

IEMappedEpsBearerContexts::IEMappedEpsBearerContexts(OctetString &&data) : data(std::move(data))
{
}

IEMappedEpsBearerContexts IEMappedEpsBearerContexts::Decode(const OctetView &stream, int length)
{
    return IEMappedEpsBearerContexts{stream.readOctetString(length)};
}

void IEMappedEpsBearerContexts::Encode(const IEMappedEpsBearerContexts &ie, OctetString &stream)
{
    stream.append(ie.data);
}

void IEMappedEpsBearerContexts::Mutate(IEMappedEpsBearerContexts &ie)
{
    mutate_octet_string(ie.data);
}

IEQoSRules::IEQoSRules(OctetString &&data) : data(std::move(data))
{
}

IEQoSRules IEQoSRules::Decode(const OctetView &stream, int length)
{
    return IEQoSRules{stream.readOctetString(length)};
}

void IEQoSRules::Encode(const IEQoSRules &ie, OctetString &stream)
{
    stream.append(ie.data);
}

void IEQoSRules::Mutate(IEQoSRules &ie)
{
    mutate_octet_string(ie.data);
}

IESorTransparentContainer IESorTransparentContainer::Decode(const OctetView &stream, int length)
{
    IESorTransparentContainer res;

    octet header = stream.read();
    res.sorDataType = static_cast<ESorDataType>(header.bit(0));

    if (res.sorDataType == ESorDataType::STEERING_OF_ROAMING_INFORMATION)
    {
        res.listIndication = static_cast<ESorListIndication>(header.bit(1));
        res.listType = static_cast<ESorListType>(header.bit(2));
        res.ack = static_cast<EAcknowledgement>(header.bit(3));
        res.sorMacIAusf = stream.readOctetString(16);
        res.counterSor = stream.read2();

        if (res.listType == ESorListType::SECURED_PACKET)
            res.securedPacket = stream.readOctetString(length - 19);
        else
            DecodeListVal(stream, length - 19, res.piat);
    }
    else
    {
        res.sorMacIUe = stream.readOctetString(16);
    }

    return res;
}

void IESorTransparentContainer::Encode(const IESorTransparentContainer &ie, OctetString &stream)
{
    if (ie.sorDataType == ESorDataType::ACKNOWLEDGEMENT)
    {
        stream.appendOctet(0b00000001);
        stream.append(ie.sorMacIUe);
        return;
    }

    uint8_t flags = bits::Ranged8({{4, 0},
                                   {1, static_cast<int>(ie.ack)},
                                   {1, static_cast<int>(ie.listType)},
                                   {1, static_cast<int>(ie.listIndication)},
                                   {1, static_cast<int>(ie.sorDataType)}});
    stream.appendOctet(flags);
    stream.append(ie.sorMacIAusf);
    stream.appendOctet2(ie.counterSor);

    if (ie.listIndication != ESorListIndication::NOT_PROVIDED)
    {
        if (ie.listType == ESorListType::SECURED_PACKET)
            stream.append(ie.securedPacket);
        else
        {
            for (auto &x : ie.piat)
                VPlmnIdAccessTech::Encode(x, stream);
        }
    }
}

void IESorTransparentContainer::Mutate(IESorTransparentContainer &ie)
{
    ie.sorDataType = static_cast<ESorDataType>(generate_bit(1));

    if (ie.sorDataType == ESorDataType::STEERING_OF_ROAMING_INFORMATION)
    {
        ie.listIndication = static_cast<ESorListIndication>(generate_bit(1));
        ie.listType = static_cast<ESorListType>(generate_bit(1));
        ie.ack = static_cast<EAcknowledgement>(generate_bit(1));
        mutate_octet_string(ie.sorMacIAusf);
        ie.counterSor = octet2(generate_bit(16));

        if (ie.listType == ESorListType::SECURED_PACKET)
            mutate_octet_string(ie.securedPacket);
        else
        {
            for (auto &x : ie.piat)
                VPlmnIdAccessTech::Mutate(x);
        }
    }
    else
    {
        mutate_octet_string(ie.sorMacIUe);
    }
}

void IE5gsMobileIdentity::Encode(const IE5gsMobileIdentity &ie, OctetString &stream)
{
    switch (ie.type)
    {
    case EIdentityType::SUCI: {
        if (ie.supiFormat == ESupiFormat::NETWORK_SPECIFIC_IDENTIFIER)
        {
            stream.appendOctet(0b00010001);
            for (char c : ie.value)
                stream.appendOctet(c);
        }
        else
        {
            stream.appendOctet(0x01);

            VPlmn::Encode(VPlmn{ie.imsi.plmn.mcc, ie.imsi.plmn.mnc, ie.imsi.plmn.isLongMnc}, stream);

            EncodeRoutingIndicator(stream, ie.imsi.routingIndicator);
            stream.appendOctet(ie.imsi.protectionSchemaId);
            stream.appendOctet(ie.imsi.homeNetworkPublicKeyIdentifier);

            if (ie.imsi.protectionSchemaId == 0)
                EncodeBcdString(stream, ie.imsi.schemeOutput, ~0, false, 0);
            else
                stream.append(OctetString::FromHex(ie.imsi.schemeOutput));
        }
        break;
    }
    case EIdentityType::GUTI: {
        stream.appendOctet(0xf2);

        VPlmn::Encode(VPlmn{ie.gutiOrTmsi.plmn.mcc, ie.gutiOrTmsi.plmn.mnc, ie.gutiOrTmsi.plmn.isLongMnc}, stream);
        stream.appendOctet(ie.gutiOrTmsi.amfRegionId);

        stream.appendOctet(ie.gutiOrTmsi.amfSetId >> 2);
        stream.appendOctet(((ie.gutiOrTmsi.amfSetId & 0b11) << 6) | ie.gutiOrTmsi.amfPointer);
        stream.appendOctet4(ie.gutiOrTmsi.tmsi);
        break;
    }
    case EIdentityType::IMEI: {
        int imeiFlag = 0b0011;
        if (ie.value.length() % 2 != 0)
            imeiFlag |= 0b1000; // odd flag set if imei has odd number of digits.

        EncodeBcdString(stream, ie.value, ~0, true, imeiFlag);
        break;
    }
    case EIdentityType::TMSI: {
        stream.appendOctet(0xf4);

        stream.appendOctet(ie.gutiOrTmsi.amfSetId >> 2);
        stream.appendOctet(((ie.gutiOrTmsi.amfSetId & 0b11) << 6) | ie.gutiOrTmsi.amfPointer);
        stream.appendOctet4(ie.gutiOrTmsi.tmsi);
        break;
    }
    case EIdentityType::IMEISV: {
        int imeiSvFlag = 0b0101;
        if (ie.value.length() % 2 != 0)
            imeiSvFlag |= 0b1000; // odd flag set if imei has odd number of digits.

        EncodeBcdString(stream, ie.value, ~0, true, imeiSvFlag);
        break;
    }
    case EIdentityType::NO_IDENTITY:
        stream.appendOctet(0x00);
        break;
    }
}

IE5gsMobileIdentity IE5gsMobileIdentity::Decode(const OctetView &stream, int length)
{
    IE5gsMobileIdentity result;

    int flags = stream.peekI();

    result.type = static_cast<EIdentityType>(flags & 0b111);
    // bool isEven = ((flags >> 3) & 0b1) == 0;

    if (result.type == EIdentityType::SUCI)
    {
        stream.readI();

        result.supiFormat = static_cast<ESupiFormat>((flags >> 4) & 0b111);

        if (result.supiFormat == ESupiFormat::IMSI)
        {
            length--;

            VPlmn plmn = VPlmn::Decode(stream);
            result.imsi.plmn.mcc = plmn.mcc;
            result.imsi.plmn.mnc = plmn.mnc;
            result.imsi.plmn.isLongMnc = plmn.isLongMnc;

            int riLen = stream.peek(1) == 0xFF ? 1 : 2;
            result.imsi.routingIndicator = DecodeBcdString(stream, riLen, false);
            if (riLen == 1)
                stream.read();

            result.imsi.protectionSchemaId = stream.readI() & 0b1111;

            result.imsi.homeNetworkPublicKeyIdentifier = stream.read();

            if (result.imsi.protectionSchemaId == 0)
                result.imsi.schemeOutput = DecodeBcdString(stream, length - 7, false);
            else
            {
                auto range = stream.readOctetString(length - 7);
                result.imsi.schemeOutput = range.toHexString();
            }
        }
        else
        {
            result.value = stream.readUtf8String(length - 1);
        }
    }
    else if (result.type == EIdentityType::GUTI)
    {
        stream.read();

        VPlmn plmn = VPlmn::Decode(stream);
        result.gutiOrTmsi.plmn.mcc = plmn.mcc;
        result.gutiOrTmsi.plmn.mnc = plmn.mnc;
        result.gutiOrTmsi.plmn.isLongMnc = plmn.isLongMnc;
        result.gutiOrTmsi.amfRegionId = stream.read();
        result.gutiOrTmsi.amfSetId = VAmfSetId::Decode(stream).value;
        result.gutiOrTmsi.amfPointer = stream.readI() & 0b111111;
        result.gutiOrTmsi.tmsi = stream.read4();
    }
    else if (result.type == EIdentityType::TMSI)
    {
        stream.read();

        result.gutiOrTmsi.amfSetId = VAmfSetId::Decode(stream).value;
        result.gutiOrTmsi.amfPointer = stream.readI() & 0b111111;
        result.gutiOrTmsi.tmsi = stream.read4();
    }
    else if (result.type == EIdentityType::IMEI || result.type == EIdentityType::IMEISV)
    {
        result.value = DecodeBcdString(stream, length, true);
    }

    return result;
}

void IE5gsMobileIdentity::Mutate(IE5gsMobileIdentity &ie)
{
    // fuzzing: mutate type and further mutate based on type
    int if_mutate = generate_bit(1);
    ie.type = static_cast<EIdentityType>(generate_bit(3));
    switch (ie.type)
    {
    case EIdentityType::SUCI: {
        ie.supiFormat = static_cast<ESupiFormat>(generate_bit(3));
        if (ie.supiFormat == ESupiFormat::NETWORK_SPECIFIC_IDENTIFIER)
        {
            mutate_string(ie.value);
        }
        else
        {
            if (if_mutate)
            {
                ie.imsi.plmn.mcc = generate_int(1000);
                ie.imsi.plmn.mnc = generate_int(1000);
                ie.imsi.plmn.isLongMnc = generate_bit(1);

                mutate_string(ie.imsi.routingIndicator);
                ie.imsi.protectionSchemaId = generate_bit(4);
                ie.imsi.homeNetworkPublicKeyIdentifier = generate_bit(8);
                mutate_string(ie.imsi.schemeOutput);
            }
            else
            {
                // get SUCI
                ie = nr::ue::state_learner->getOrGenerateId(EIdentityType::SUCI);
            }
        }
        break;
    }
    case EIdentityType::GUTI: {
        if (if_mutate)
        {
            ie.gutiOrTmsi.plmn.mcc = generate_int(1000);
            ie.gutiOrTmsi.plmn.mnc = generate_int(1000);
            ie.gutiOrTmsi.plmn.isLongMnc = generate_bit(1);
            ie.gutiOrTmsi.amfRegionId = generate_bit(8);
            ie.gutiOrTmsi.amfSetId = generate_bit(10);
            ie.gutiOrTmsi.amfPointer = generate_bit(6);
            ie.gutiOrTmsi.tmsi = octet4(generate_bit(32));
        }
        else
        {
            // get GUTI
            ie = nr::ue::state_learner->getOrGenerateId(EIdentityType::GUTI);
        }
        break;
    }
    case EIdentityType::IMEI: {
        if (if_mutate)
        {
            mutate_string(ie.value);
        }
        else
        {
            // get IMEI
            ie = nr::ue::state_learner->getOrGenerateId(EIdentityType::IMEI);
        }
        break;
    }
    case EIdentityType::TMSI: {
        if (if_mutate)
        {
            ie.gutiOrTmsi.amfSetId = generate_bit(10);
            ie.gutiOrTmsi.amfPointer = generate_bit(6);
            ie.gutiOrTmsi.tmsi = octet4(generate_bit(32));
        }
        else
        {
            // get TMSI
            ie = nr::ue::state_learner->getOrGenerateId(EIdentityType::TMSI);
        }
        break;
    }
    case EIdentityType::IMEISV: {
        if (if_mutate)
        {
            mutate_string(ie.value);
        }
        else
        {
            // get IMEISV
            ie = nr::ue::state_learner->getOrGenerateId(EIdentityType::IMEISV);
        }
        break;
    }
    case EIdentityType::NO_IDENTITY:
        break;
    default:
        break;
    }
}

IEEapMessage IEEapMessage::Decode(const OctetView &stream, int length)
{
    IEEapMessage r;
    r.eap = eap::DecodeEapPdu(stream);
    return r;
}

void IEEapMessage::Encode(const IEEapMessage &ie, OctetString &stream)
{
    eap::EncodeEapPdu(stream, *ie.eap);
}

void IEEapMessage::Mutate(IEEapMessage &ie)
{
    // fuzzing: Not mutate EAP message for now
}

Json ToJson(const IE5gsMobileIdentity &v)
{
    switch (v.type)
    {
    case EIdentityType::NO_IDENTITY:
        return "no-identity";
    case EIdentityType::SUCI: {
        switch (v.supiFormat)
        {
        case ESupiFormat::IMSI:
            return Json::Obj({
                {"plmn", ToJson(v.imsi.plmn)},
                {"routing-indicator", v.imsi.routingIndicator},
                {"protection-schema", v.imsi.protectionSchemaId},
                {"hnp-key-id", ToJson(v.imsi.homeNetworkPublicKeyIdentifier)},
                {"scheme-output", v.imsi.schemeOutput},
            });
        case ESupiFormat::NETWORK_SPECIFIC_IDENTIFIER:
            return "nai-" + v.value;
        default:
            return "?";
        }
    }
    case EIdentityType::GUTI:
        return Json::Obj({
            {"plmn", ToJson(v.gutiOrTmsi.plmn)},
            {"amf-region-id", ToJson(v.gutiOrTmsi.amfRegionId)},
            {"amf-set-id", v.gutiOrTmsi.amfSetId},
            {"amf-pointer", v.gutiOrTmsi.amfPointer},
            {"tmsi", ToJson(v.gutiOrTmsi.tmsi)},
        });
    case EIdentityType::IMEI:
        return "imei-" + v.value;
    case EIdentityType::TMSI:
        return Json::Obj({
            {"amf-set-id", v.gutiOrTmsi.amfSetId},
            {"amf-pointer", v.gutiOrTmsi.amfPointer},
            {"tmsi", ToJson(v.gutiOrTmsi.tmsi)},
        });
    case EIdentityType::IMEISV:
        return "imeisv-" + v.value;
    default:
        return "?";
    }
}

} // namespace nas