//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "mm.hpp"
#include <lib/nas/base.hpp>
#include <utils/common.hpp>
// STEPHANE
#include <iostream>

namespace nr::ue
{

void NasMm::receiveIdentityRequest(const nas::IdentityRequest &msg)
{
    nas::IdentityResponse resp;

    if (msg.identityType.value == nas::EIdentityType::SUCI)
    {
        resp.mobileIdentity = getOrGenerateSuci();
    }
    else if (msg.identityType.value == nas::EIdentityType::IMEI)
    {
        resp.mobileIdentity.type = nas::EIdentityType::IMEI;
        resp.mobileIdentity.value = *m_base->config->imei;
    }
    else if (msg.identityType.value == nas::EIdentityType::IMEISV)
    {
        resp.mobileIdentity.type = nas::EIdentityType::IMEISV;
        resp.mobileIdentity.value = *m_base->config->imeiSv;
    }
    else if (msg.identityType.value == nas::EIdentityType::GUTI)
    {
        resp.mobileIdentity = m_storage->storedGuti->get();
    }
    else if (msg.identityType.value == nas::EIdentityType::TMSI)
    {
        // TMSI is already a part of GUTI
        resp.mobileIdentity = m_storage->storedGuti->get();
        if (resp.mobileIdentity.type != nas::EIdentityType::NO_IDENTITY)
        {
            resp.mobileIdentity.type = nas::EIdentityType::TMSI;
            resp.mobileIdentity.gutiOrTmsi.plmn = {};
            resp.mobileIdentity.gutiOrTmsi.amfRegionId = {};
        }
    }
    else
    {
        resp.mobileIdentity.type = nas::EIdentityType::NO_IDENTITY;
        m_logger->err("Requested identity is not available: %d", (int)msg.identityType.value);
    }

    sendNasMessage(resp);
}

nas::IE5gsMobileIdentity NasMm::getOrGenerateSuci()
{
    if (m_timers->t3519.isRunning() && m_storage->storedSuci->get().type != nas::EIdentityType::NO_IDENTITY)
        return m_storage->storedSuci->get();

    m_storage->storedSuci->set(generateSuci());

    m_timers->t3519.start();

    if (m_storage->storedSuci->get().type == nas::EIdentityType::NO_IDENTITY)
        return {};
    return m_storage->storedSuci->get();
}

nas::IE5gsMobileIdentity NasMm::generateSuci()
{
    auto &supi = m_base->config->supi;
    auto &plmn = m_base->config->hplmn;
    auto &protectionScheme = m_base->config->protectionScheme;
    auto &homeNetworkPublicKeyId = m_base->config->homeNetworkPublicKeyId;
    auto &homeNetworkPublicKey = m_base->config->homeNetworkPublicKey;

    if (!supi.has_value())
        return {};

    if (supi->type != "imsi")
    {
        m_logger->err("SUCI generating failed, invalid SUPI type: %s", supi->value.c_str());
        return {};
    }

    const std::string &imsi = supi->value;

    nas::IE5gsMobileIdentity ret;
    ret.type = nas::EIdentityType::SUCI;
    ret.supiFormat = nas::ESupiFormat::IMSI;
    ret.imsi.plmn.isLongMnc = plmn.isLongMnc;
    ret.imsi.plmn.mcc = plmn.mcc;
    ret.imsi.plmn.mnc = plmn.mnc;
    if (m_base->config->routingIndicator.has_value())
    {
        ret.imsi.routingIndicator = *m_base->config->routingIndicator;
    }
    else
    {
        ret.imsi.routingIndicator = "0000";
    }
    if (protectionScheme == 0) {
        ret.imsi.protectionSchemaId = 0;
        ret.imsi.homeNetworkPublicKeyIdentifier = 0;
        ret.imsi.schemeOutput = imsi.substr(plmn.isLongMnc ? 6 : 5);
        return ret;
    }
    else if (protectionScheme == 1)
    {
        ret.imsi.protectionSchemaId = 1;
        ret.imsi.homeNetworkPublicKeyIdentifier = homeNetworkPublicKeyId;
        ret.imsi.schemeOutput = generateSUCIProfileA(imsi.substr(plmn.isLongMnc ? 6 : 5), homeNetworkPublicKey);
        return ret;
    }
    else
    {
        m_logger->err("Protection Scheme %d not implemented", protectionScheme);
        return {};
    }
}

std::string NasMm::generateSUCIProfileA(const std::string &imsi, const OctetString &hnPublicKey)
{
    std::string name("Seed for x25519 generation");
    std::string seed;
    Random rnd = Random::Mixed(name);
    int intLength = sizeof(int32_t);

    for (int i=0; i < (X25519_KEY_SIZE/intLength); i++)
    {
        seed = seed + utils::IntToHex(rnd.nextI());
    }

    OctetString randomSeed = OctetString::FromHex(seed);
    uint8_t privateKey[X25519_KEY_SIZE];
    uint8_t publicKey[X25519_KEY_SIZE];    
    compact_x25519_keygen(privateKey,publicKey, randomSeed.data());

    OctetString uePrivateKey = OctetString::FromArray(privateKey, X25519_KEY_SIZE);
    OctetString uePublicKey = OctetString::FromArray(publicKey, X25519_KEY_SIZE);
    OctetString shared;
    shared.appendPadding(32);
    compact_x25519_shared(shared.data(), uePrivateKey.data(), hnPublicKey.data());

    uint8_t derivatedKey[64];
    x963kdf(derivatedKey, shared.data(), uePublicKey.data(), 64);
    OctetString buf = OctetString::FromArray(derivatedKey, 64);
    OctetString encryptionKey = buf.subCopy(0, 16);
    OctetString initializationVector = buf.subCopy(16, 16);
    OctetString macKey = buf.subCopy(32, 32);

    OctetString msin;
    nas::EncodeBcdString(msin, imsi, ~0, false, 0);
    struct AES_ctx ctx;
    AES_init_ctx(&ctx, encryptionKey.data());
    AES_ctx_set_iv(&ctx, initializationVector.data());
    AES_CTR_xcrypt_buffer(&ctx, msin.data(), msin.length());

    uint8_t suciHMAC[HMAC_SHA256_DIGEST_SIZE];
    hmac_sha256(suciHMAC, msin.data(), msin.length(), macKey.data(), HMAC_SHA256_DIGEST_SIZE);
    
    OctetString macTag = OctetString::FromArray(suciHMAC, 8);
    OctetString schemeOutput;
    schemeOutput.append(uePublicKey);
    schemeOutput.append(msin);
    schemeOutput.append(macTag);
    return schemeOutput.toHexString();
}

nas::IE5gsMobileIdentity NasMm::getOrGeneratePreferredId()
{
    if (m_storage->storedGuti->get().type != nas::EIdentityType::NO_IDENTITY)
        return m_storage->storedGuti->get();

    auto suci = getOrGenerateSuci();
    if (suci.type != nas::EIdentityType::NO_IDENTITY)
    {
        return suci;
    }
    else if (m_base->config->imei.has_value())
    {
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::IMEI;
        res.value = *m_base->config->imei;
        return res;
    }
    else if (m_base->config->imeiSv.has_value())
    {
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::IMEISV;
        res.value = *m_base->config->imeiSv;
        return res;
    }
    else
    {
        nas::IE5gsMobileIdentity res{};
        res.type = nas::EIdentityType::NO_IDENTITY;
        return res;
    }
}

} // namespace nr::ue
