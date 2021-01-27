//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "crypt.hpp"
#include "crypt_eea2.hpp"
#include "crypt_eea3.hpp"
#include "crypt_eia2.hpp"
#include "crypt_mac.hpp"
#include "crypt_snow3g.hpp"
#include "crypt_uea2.hpp"
#include "crypt_zuc.hpp"

namespace crypto
{

OctetString CalculatePrfPrime(const OctetString &key, const OctetString &input, int outputLength)
{
    if (key.length() != 32)
        throw std::runtime_error("CalculatePrfPrime, 256-bit key expected");

    int round = outputLength / 32 + 1;
    if (round <= 0 || round > 254)
        throw std::runtime_error("CalculatePrfPrime, invalid outputLength value");

    std::vector<OctetString> T(round);

    for (int i = 0; i < round; i++)
    {
        OctetString s{};

        if (i == 0)
        {
            s.append(input);
            s.appendOctet(i + 1);
        }
        else
        {
            s.append(T[i - 1]);
            s.append(input);
            s.appendOctet(i + 1);
        }

        T[i] = HmacSha256(key, s);
    }

    OctetString res;
    for (auto &s : T)
        res.append(s);
    return res;
}

OctetString HmacSha256(const OctetString &key, const OctetString &input)
{
    std::vector<uint8_t> out(32);
    HmacSha256(out.data(), input.data(), input.length(), key.data(), key.length());
    return OctetString{std::move(out)};
}

OctetString CalculateKdfKey(const OctetString &key, int fc, OctetString *parameters, int numberOfParameter)
{
    OctetString inp;
    inp.appendOctet(fc);
    for (int i = 0; i < numberOfParameter; i++)
    {
        inp.append(parameters[i]);
        inp.appendOctet2(parameters[i].length());
    }
    return HmacSha256(key, inp);
}

OctetString CalculateKdfKey(const OctetString &key, int fc1, int fc2, OctetString *parameters, int numberOfParameter)
{
    OctetString inp;
    inp.appendOctet(fc1);
    inp.appendOctet(fc2);
    for (int i = 0; i < numberOfParameter; i++)
    {
        inp.append(parameters[i]);
        inp.appendOctet2(parameters[i].length());
    }
    return HmacSha256(key, inp);
}

OctetString EncodeKdfString(const std::string &string)
{
    // Todo normalize the string
    // V16.0.0 - B.2.1.2 Character string encoding
    // A character string shall be encoded to an octet string according to UTF-8 encoding rules as specified in
    // IETF RFC 3629 [24] and apply Normalization Form KC (NFKC) as specified in [37].
    return OctetString{std::vector<uint8_t>{string.c_str(), string.c_str() + string.length()}};
}

std::vector<uint32_t> Snow3g(const OctetString &key, const OctetString &iv, int length)
{
    std::vector<uint32_t> res(length);
    snow3g::Initialize(reinterpret_cast<const uint32_t *>(key.data()), reinterpret_cast<const uint32_t *>(iv.data()));
    snow3g::GenerateKeyStream(res.data(), length);
    return res;
}

std::vector<uint32_t> Zuc(const OctetString &key, const OctetString &iv, int length)
{
    std::vector<uint32_t> res(length);
    zuc::Initialize(key.data(), iv.data());
    zuc::GenerateKeyStream(res.data(), length);
    return res;
}

uint32_t ComputeMacUia2(const uint8_t *pKey, uint32_t count, uint32_t fresh, uint32_t dir, const uint8_t *pData,
                        uint64_t length)
{
    return crypto::uea2::F9(pKey, count, fresh, dir, pData, length * 8);
}

void EncryptUea2(const uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t dir, uint8_t *pData, uint32_t length)
{
    crypto::uea2::F8(pKey, count, bearer, dir, pData, length * 8);
}

void EncryptEea1(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key)
{
    EncryptUea2(key.data(), count, bearer, direction, message.data(), message.length());
}

void DecryptEea1(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key)
{
    EncryptEea1(count, bearer, direction, message, key);
}

uint32_t ComputeMacEia1(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key)
{
    uint32_t fresh = bearer << 27;
    return ComputeMacUia2(key.data(), count, fresh, direction, message.data(), message.length());
}

void EncryptEea2(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key)
{
    eea2::Encrypt(count, bearer, direction, message, key);
}

void DecryptEea2(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key)
{
    eea2::Decrypt(count, bearer, direction, message, key);
}

uint32_t ComputeMacEia2(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key)
{
    return eia2::Compute(count, bearer, direction, message, key);
}

void EncryptEea3(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key)
{
    eea3::EEA3(key.data(), count, bearer, direction, message.length() * 8,
               reinterpret_cast<uint32_t *>(message.data()));
}

void DecryptEea3(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key)
{
    eea3::EEA3(key.data(), count, bearer, direction, message.length() * 8,
               reinterpret_cast<uint32_t *>(message.data()));
}

uint32_t ComputeMacEia3(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key)
{
    return eea3::EIA3(key.data(), count, direction, bearer, message.length() * 8,
                      reinterpret_cast<const uint32_t *>(message.data()));
}

} // namespace crypto
