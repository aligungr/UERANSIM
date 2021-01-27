//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "nas_eap.hpp"

OctetString eap::EapAttributes::getRand() const
{
    return attributes[(int)EAttributeType::AT_RAND].value().subCopy(2);
}

OctetString eap::EapAttributes::getMac() const
{
    return attributes[(int)EAttributeType::AT_MAC].value().subCopy(2);
}

OctetString eap::EapAttributes::getAutn() const
{
    return attributes[(int)EAttributeType::AT_AUTN].value().subCopy(2);
}

int eap::EapAttributes::getClientErrorCode() const
{
    return attributes[(int)EAttributeType::AT_CLIENT_ERROR_CODE].value().get2I(0);
}

int eap::EapAttributes::getKdf() const
{
    return attributes[(int)EAttributeType::AT_KDF].value().get2I(0);
}

const OctetString &eap::EapAttributes::getAuts() const
{
    return attributes[(int)EAttributeType::AT_AUTS].value();
}

void eap::EapAttributes::putRes(const OctetString &value)
{
    attributes[(int)EAttributeType::AT_RES] = OctetString::Concat(OctetString::FromOctet2(value.length()), value);
}

void eap::EapAttributes::putMac(const OctetString &value)
{
    attributes[(int)EAttributeType::AT_MAC] = OctetString::Concat(OctetString::FromOctet2(0), value);
}

void eap::EapAttributes::putAutn(const OctetString &value)
{
    attributes[(int)EAttributeType::AT_AUTN] = OctetString::Concat(OctetString::FromOctet2(0), value);
}

void eap::EapAttributes::putKdf(int value)
{
    attributes[(int)EAttributeType::AT_KDF] = OctetString::FromOctet2(value);
}

void eap::EapAttributes::putClientErrorCode(int code)
{
    attributes[(int)EAttributeType::AT_CLIENT_ERROR_CODE] = OctetString::FromOctet2(code);
}

void eap::EapAttributes::putAuts(OctetString &&auts)
{
    attributes[(int)EAttributeType::AT_AUTS] = std::move(auts);
}

void eap::EapAttributes::forEachEntry(const std::function<void(EAttributeType, const OctetString &)> &fun) const
{
    for (size_t i = 0; i < attributes.size(); i++)
        if (attributes[i].has_value())
            fun(static_cast<EAttributeType>(i), attributes[i].value());
}

void eap::EapAttributes::putRawAttribute(eap::EAttributeType key, OctetString &&value)
{
    attributes[(int)key] = std::move(value);
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

            akaPrime.attributes.forEachEntry([&stream](EAttributeType key, const OctetString &value) {
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

std::unique_ptr<eap::Eap> eap::DecodeEapPdu(const OctetBuffer &stream)
{
    auto code = static_cast<ECode>(stream.readI());
    int id = stream.readI();
    int length = stream.read2I();

    if (length == 4)
        return std::unique_ptr<Eap>(new Eap(code, id, EEapType::NO_TYPE));

    auto type = static_cast<EEapType>(stream.readI());

    int innerLength = length - 1 // code
                      - 1        // id
                      - 2        // length
                      - 1;       // type

    if (type == EEapType::EAP_AKA_PRIME)
    {
        int readBytes = 0;

        // decode subtype
        auto subType = static_cast<ESubType>(stream.readI());
        readBytes += 1;

        auto *akaPrime = new EapAkaPrime(code, id, subType);

        // consume reserved 2 octets
        stream.read2I();
        readBytes += 2;

        while (readBytes < length)
        {
            // decode attribute type
            auto t = static_cast<EAttributeType>(stream.readI());
            readBytes += 1;

            // decode attribute length
            auto attributeLength = stream.readI();
            readBytes += 1;

            // decode attribute value
            auto attributeVal = stream.readOctetString(4 * attributeLength - 2);
            readBytes += 4 * attributeLength - 2;

            akaPrime->attributes.putRawAttribute(t, std::move(attributeVal));
        }

        if (readBytes != length)
        {
            // Error: "readBytes exceeds the element length"
            return nullptr;
        }

        return std::unique_ptr<Eap>(akaPrime);
    }
    else if (type == EEapType::NOTIFICATION)
        return std::unique_ptr<Eap>(new EapNotification(code, id, stream.readOctetString(innerLength)));
    else if (type == EEapType::IDENTITY)
        return std::unique_ptr<Eap>(new EapIdentity(code, id, stream.readOctetString(innerLength)));

    return nullptr;
}
