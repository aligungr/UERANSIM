//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "eap.hpp"

#include <memory>
#include <stdexcept>

OctetString eap::EapAttributes::getRand() const
{
    auto &val = attributes[(int)EAttributeType::AT_RAND];
    if (!val.has_value() || val->length() < 2)
        return {};

    return val->subCopy(2);
}

OctetString eap::EapAttributes::getMac() const
{
    auto &val = attributes[(int)EAttributeType::AT_MAC];
    if (!val.has_value() || val->length() < 2)
        return {};

    return val->subCopy(2);
}

OctetString eap::EapAttributes::getAutn() const
{
    auto &val = attributes[(int)EAttributeType::AT_AUTN];
    if (!val.has_value() || val->length() < 2)
        return {};

    return val->subCopy(2);
}

int eap::EapAttributes::getClientErrorCode() const
{
    auto &val = attributes[(int)EAttributeType::AT_CLIENT_ERROR_CODE];
    if (!val.has_value() || val->length() != 2)
        return 0;
    return val->get2I(0);
}

int eap::EapAttributes::getKdf() const
{
    auto &val = attributes[(int)EAttributeType::AT_KDF];

    if (!val.has_value())
        return 0;
    if (val->length() != 2)
        return 0;
    return val->get2I(0);
}

OctetString eap::EapAttributes::getKdfInput() const
{
    auto &val = attributes[(int)EAttributeType::AT_KDF_INPUT];
    if (!val.has_value() || val->length() < 2)
        return {};

    int len = val->get2I(0);
    if (len + 2 > val->length())
        return {};

    return val->subCopy(2, len);
}

void eap::EapAttributes::putRes(const OctetString &value)
{
    putRawAttribute(EAttributeType::AT_RES, OctetString::Concat(OctetString::FromOctet2(value.length() * 8), value));
}

void eap::EapAttributes::putMac(const OctetString &value)
{
    putRawAttribute(EAttributeType::AT_MAC, OctetString::Concat(OctetString::FromOctet2(0), value));
}

void eap::EapAttributes::replaceMac(const OctetString &value)
{
    attributes[(int)EAttributeType::AT_MAC] = OctetString::Concat(OctetString::FromOctet2(0), value);
}

void eap::EapAttributes::putKdf(int value)
{
    putRawAttribute(EAttributeType::AT_KDF, OctetString::FromOctet2(value));
}

void eap::EapAttributes::putClientErrorCode(int code)
{
    putRawAttribute(EAttributeType::AT_CLIENT_ERROR_CODE, OctetString::FromOctet2(code));
}

void eap::EapAttributes::putAuts(OctetString &&auts)
{
    putRawAttribute(EAttributeType::AT_AUTS, std::move(auts));
}

void eap::EapAttributes::forEachEntry(const std::function<void(EAttributeType, const OctetString &)> &fun) const
{
    for (size_t i = 0; i < attributes.size(); i++)
        if (attributes[i].has_value())
            fun(static_cast<EAttributeType>(i), attributes[i].value());
}

void eap::EapAttributes::forEachEntryOrdered(const std::function<void(EAttributeType, const OctetString &)> &fun) const
{
    for (auto it = order_received.begin(); it != order_received.end(); it++)
        if (attributes[*it].has_value())
            fun(static_cast<EAttributeType>(*it), attributes[*it].value());
}

void eap::EapAttributes::putRawAttribute(eap::EAttributeType key, OctetString &&value)
{
    attributes[(int)key] = std::move(value);
    order_received.push_back((int)key);
}

eap::Eap::Eap(eap::ECode code, octet id, eap::EEapType eapType) : code(code), id(id), eapType(eapType)
{
}

eap::EapAkaPrime::EapAkaPrime(eap::ECode code, octet id, eap::ESubType subType)
    : Eap(code, id, EEapType::EAP_AKA_PRIME), subType(subType)
{
}

eap::EapIdentity::EapIdentity(eap::ECode code, octet id) : Eap(code, id, EEapType::IDENTITY), rawData{}
{
}

eap::EapIdentity::EapIdentity(eap::ECode code, octet id, OctetString &&rawData)
    : Eap(code, id, EEapType::IDENTITY), rawData(std::move(rawData))
{
}

eap::EapNotification::EapNotification(eap::ECode code, octet id) : Eap(code, id, EEapType::NOTIFICATION), rawData{}
{
}

eap::EapNotification::EapNotification(eap::ECode code, octet id, OctetString &&rawData)
    : Eap(code, id, EEapType::NOTIFICATION), rawData(std::move(rawData))
{
}

void eap::EncodeEapPdu(OctetString &stream, const eap::Eap &pdu)
{
    int initialLength = stream.length();

    stream.appendOctet((int)pdu.code);
    stream.appendOctet(pdu.id);

    if (pdu.eapType == EEapType::NO_TYPE)
    {
        stream.appendOctet2(4);
    }
    else
    {
        stream.appendOctet2(0);
        stream.appendOctet((int)pdu.eapType);

        if (pdu.eapType == EEapType::EAP_AKA_PRIME)
        {
            auto &akaPrime = (const EapAkaPrime &)pdu;
            stream.appendOctet(static_cast<int>(akaPrime.subType));
            stream.appendOctet2(0);

            akaPrime.attributes.forEachEntryOrdered([&stream](EAttributeType key, const OctetString &value) {
                stream.appendOctet((int)key);
                stream.appendOctet((value.length() + 2) / 4);
                stream.append(value);
            });
        }
        else if (pdu.eapType == EEapType::NOTIFICATION)
            stream.append(((const EapNotification &)pdu).rawData);
        else if (pdu.eapType == EEapType::IDENTITY)
            stream.append(((const EapIdentity &)pdu).rawData);

        octet2 realLength = octet2{stream.length() - initialLength};
        stream.data()[initialLength + 2] = realLength[0];
        stream.data()[initialLength + 3] = realLength[1];
    }
}

std::unique_ptr<eap::Eap> eap::DecodeEapPdu(const OctetView &stream)
{
    auto code = static_cast<ECode>(stream.readI());

    if (code < ECode::REQUEST || code > ECode::FINISH)
        return nullptr;

    int id = stream.readI();
    int length = stream.read2I();

    if (length == 4)
        return std::make_unique<Eap>(code, id, EEapType::NO_TYPE);

    auto type = static_cast<EEapType>(stream.readI());

    if (type < EEapType::NO_TYPE || type > EEapType::EXPERIMENTAL)
        return nullptr;

    int innerLength = length - 1 // code
                      - 1        // id
                      - 2        // length
                      - 1;       // type

    if (type == EEapType::EAP_AKA_PRIME)
    {
        int readBytes = 0;

        // decode subtype
        auto subType = static_cast<ESubType>(stream.readI());

        if (subType < ESubType::AKA_CHALLENGE || subType > ESubType::AKA_CLIENT_ERROR)
            return nullptr;

        readBytes += 1;

        auto *akaPrime = new EapAkaPrime(code, id, subType);

        // consume reserved 2 octets
        stream.read2I();
        readBytes += 2;

        while (readBytes < innerLength)
        {
            // decode attribute type
            auto t = static_cast<EAttributeType>(stream.readI());
            readBytes += 1;

            if (t < EAttributeType::AT_RAND || t > EAttributeType::AT_TRUST_IND)
                return nullptr;

            // decode attribute length
            auto attributeLength = stream.readI();
            readBytes += 1;

            // Valid length must be at least 2 octets
            if (attributeLength < 2)
                return nullptr;

            // decode attribute value
            auto attributeVal = stream.readOctetString(4 * attributeLength - 2);
            readBytes += 4 * attributeLength - 2;

            akaPrime->attributes.putRawAttribute(t, std::move(attributeVal));
        }

        if (readBytes != innerLength)
        {
            // Error: "readBytes exceeds the element length"
            throw std::runtime_error("EAP decoding failure: readBytes exceeds the element length");
        }

        return std::unique_ptr<Eap>(akaPrime);
    }
    else if (type == EEapType::NOTIFICATION)
        return std::unique_ptr<Eap>(new EapNotification(code, id, stream.readOctetString(innerLength)));
    else if (type == EEapType::IDENTITY)
        return std::unique_ptr<Eap>(new EapIdentity(code, id, stream.readOctetString(innerLength)));

    return nullptr;
}
