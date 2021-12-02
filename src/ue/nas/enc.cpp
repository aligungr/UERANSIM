//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "enc.hpp"

#include <lib/crypt/crypt.hpp>
#include <stdexcept>

namespace nr::ue::nas_enc
{

static nas::ESecurityHeaderType MakeSecurityHeaderType(const NasSecurityContext &ctx, nas::EMessageType msgType,
                                                       bool noCipheredHeader)
{
    if (msgType == nas::EMessageType::SECURITY_MODE_COMPLETE)
        return nas::ESecurityHeaderType::INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT;

    if (msgType == nas::EMessageType::SECURITY_MODE_COMMAND)
        return nas::ESecurityHeaderType::INTEGRITY_PROTECTED_WITH_NEW_SECURITY_CONTEXT;

    auto &intKey = ctx.keys.kNasInt;
    auto &encKey = ctx.keys.kNasEnc;

    bool integrityProtected = intKey.length() > 0;
    bool ciphered = encKey.length() > 0;

    if (!integrityProtected)
        return nas::ESecurityHeaderType::NOT_PROTECTED;
    if (!ciphered || noCipheredHeader)
        return nas::ESecurityHeaderType::INTEGRITY_PROTECTED;
    return nas::ESecurityHeaderType::INTEGRITY_PROTECTED_AND_CIPHERED;
}

static OctetString EncryptData(nas::ETypeOfCipheringAlgorithm alg, const NasCount &count, bool is3gppAccess,
                               const OctetString &data, const OctetString &key)
{
    int bearer = is3gppAccess ? 1 : 2;
    int direction = 0;

    OctetString msg = data.copy();

    switch (alg)
    {
    case nas::ETypeOfCipheringAlgorithm::EA0:
        break;
    case nas::ETypeOfCipheringAlgorithm::EA1_128:
        crypto::EncryptEea1((uint32_t)count.toOctet4(), bearer, direction, msg, key);
        break;
    case nas::ETypeOfCipheringAlgorithm::EA2_128:
        crypto::EncryptEea2((uint32_t)count.toOctet4(), bearer, direction, msg, key);
        break;
    case nas::ETypeOfCipheringAlgorithm::EA3_128:
        crypto::EncryptEea3((uint32_t)count.toOctet4(), bearer, direction, msg, key);
        break;
    default:
        throw std::runtime_error("Bad ciphering algorithm");
    }

    return msg;
}

static std::unique_ptr<nas::SecuredMmMessage> Encrypt(NasSecurityContext &ctx, OctetString &&plainNasMessage,
                                                      nas::EMessageType msgType, bool bypassCiphering,
                                                      bool noCipheredHeader)
{
    auto count = ctx.uplinkCount;
    auto is3gppAccess = ctx.is3gppAccess;
    auto &intKey = ctx.keys.kNasInt;
    auto &encKey = ctx.keys.kNasEnc;
    auto intAlg = ctx.integrity;
    auto encAlg = ctx.ciphering;

    auto encryptedData =
        bypassCiphering ? plainNasMessage.copy() : EncryptData(encAlg, count, is3gppAccess, plainNasMessage, encKey);
    auto mac = ComputeMac(intAlg, count, is3gppAccess, true, intKey, encryptedData);

    auto secured = std::make_unique<nas::SecuredMmMessage>();
    secured->epd = nas::EExtendedProtocolDiscriminator::MOBILITY_MANAGEMENT_MESSAGES;
    secured->sht = MakeSecurityHeaderType(ctx, msgType, noCipheredHeader);
    secured->messageAuthenticationCode = octet4{mac};
    secured->sequenceNumber = count.sqn;
    secured->plainNasMessage = std::move(encryptedData);

    ctx.countOnEncrypt();

    return secured;
}

static OctetString DecryptData(nas::ETypeOfCipheringAlgorithm alg, const NasCount &count, bool is3gppAccess,
                               const OctetString &key, nas::ESecurityHeaderType sht, const OctetString &data)
{
    OctetString msg = data.copy();

    if (sht != nas::ESecurityHeaderType::INTEGRITY_PROTECTED_AND_CIPHERED &&
        sht != nas::ESecurityHeaderType::INTEGRITY_PROTECTED_AND_CIPHERED_WITH_NEW_SECURITY_CONTEXT)
        return msg;

    int bearer = is3gppAccess ? 1 : 2;
    int direction = 1;

    switch (alg)
    {
    case nas::ETypeOfCipheringAlgorithm::EA0:
        break;
    case nas::ETypeOfCipheringAlgorithm::EA1_128:
        crypto::DecryptEea1((uint32_t)count.toOctet4(), bearer, direction, msg, key);
        break;
    case nas::ETypeOfCipheringAlgorithm::EA2_128:
        crypto::DecryptEea2((uint32_t)count.toOctet4(), bearer, direction, msg, key);
        break;
    case nas::ETypeOfCipheringAlgorithm::EA3_128:
        crypto::DecryptEea3((uint32_t)count.toOctet4(), bearer, direction, msg, key);
        break;
    default:
        throw std::runtime_error("Bad ciphering algorithm");
    }

    return msg;
}

std::unique_ptr<nas::SecuredMmMessage> Encrypt(NasSecurityContext &ctx, const nas::PlainMmMessage &msg,
                                               bool bypassCiphering, bool noCipheredHeader)
{
    nas::EMessageType msgType = msg.messageType;

    OctetString stream;
    nas::EncodeNasMessage(msg, stream);

    return Encrypt(ctx, std::move(stream), msgType, bypassCiphering, noCipheredHeader);
}

std::unique_ptr<nas::NasMessage> Decrypt(NasSecurityContext &ctx, const nas::SecuredMmMessage &msg)
{
    auto estimatedCount = ctx.estimatedDownlinkCount(msg.sequenceNumber);

    auto is3gppAccess = ctx.is3gppAccess;
    auto &intKey = ctx.keys.kNasInt;
    auto &encKey = ctx.keys.kNasEnc;
    auto intAlg = ctx.integrity;
    auto encAlg = ctx.ciphering;

    auto mac = ComputeMac(intAlg, estimatedCount, is3gppAccess, false, intKey, msg.plainNasMessage);

    if (mac != (uint32_t)msg.messageAuthenticationCode)
    {
        // MAC mismatch
        return nullptr;
    }

    ctx.updateDownlinkCount(estimatedCount);
    OctetString decryptedData = DecryptData(encAlg, estimatedCount, is3gppAccess, encKey, msg.sht, msg.plainNasMessage);
    OctetView buff{decryptedData};
    return nas::DecodeNasMessage(buff);
}

uint32_t ComputeMac(nas::ETypeOfIntegrityProtectionAlgorithm alg, NasCount count, bool is3gppAccess, bool isUplink,
                    const OctetString &key, const OctetString &plainMessage)
{
    if (alg == nas::ETypeOfIntegrityProtectionAlgorithm::IA0)
        return 0;

    auto data = OctetString::Concat(OctetString::FromOctet(count.sqn), plainMessage);

    int bearer = is3gppAccess ? 1 : 2;
    int direction = isUplink ? 0 : 1;

    switch (alg)
    {
    case nas::ETypeOfIntegrityProtectionAlgorithm::IA1_128:
        return crypto::ComputeMacEia1((int)count.toOctet4(), bearer, direction, data, key);
    case nas::ETypeOfIntegrityProtectionAlgorithm::IA2_128:
        return crypto::ComputeMacEia2((int)count.toOctet4(), bearer, direction, data, key);
    case nas::ETypeOfIntegrityProtectionAlgorithm::IA3_128:
        return crypto::ComputeMacEia3((int)count.toOctet4(), bearer, direction, data, key);
    default:
        throw std::runtime_error("Bad integrity algorithm");
    }
}

} // namespace nr::ue::nas_enc
