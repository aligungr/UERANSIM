//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <asn_application.h>
#include <lib/asn/utils.hpp>
#include <utils/octet_string.hpp>

namespace nr::gnb::ngap_encode
{

template <typename T>
inline std::string EncodeXer(const asn_TYPE_descriptor_t &desc, T *pdu)
{
    auto res = asn_encode_to_new_buffer(nullptr, ATS_CANONICAL_XER, &desc, pdu);

    if (res.buffer == nullptr || res.result.encoded < 0)
        return {};

    std::string s{reinterpret_cast<char *>(res.buffer), reinterpret_cast<char *>(res.buffer) + res.result.encoded};
    free(res.buffer);
    return s;
}

template <typename T>
inline bool Encode(const asn_TYPE_descriptor_t &desc, T *pdu, ssize_t &encoded, uint8_t *&buffer)
{
    auto res = asn_encode_to_new_buffer(nullptr, ATS_ALIGNED_CANONICAL_PER, &desc, pdu);

    if (res.buffer == nullptr || res.result.encoded < 0)
        return false;

    encoded = res.result.encoded;
    buffer = new uint8_t[encoded];
    std::memcpy(buffer, res.buffer, encoded);
    free(res.buffer);

    return true;
}

template <typename T>
inline OctetString EncodeS(const asn_TYPE_descriptor_t &desc, T *pdu)
{
    uint8_t *buffer;
    ssize_t encoded;
    if (Encode(desc, pdu, encoded, buffer))
    {
        std::vector<uint8_t> v(encoded);
        memcpy(v.data(), buffer, encoded);
        delete[] buffer;
        return OctetString{std::move(v)};
    }
    return OctetString{};
}

template <typename T>
inline T *Decode(asn_TYPE_descriptor_t &desc, const uint8_t *buffer, size_t size)
{
    auto *pdu = asn::New<T>();
    auto res = aper_decode(nullptr, &desc, reinterpret_cast<void **>(&pdu), buffer, size, 0, 0);

    if (res.code != RC_OK)
    {
        asn::Free(desc, pdu);
        return nullptr;
    }

    return pdu;
}

template <typename T>
inline bool DecodeInPlace(asn_TYPE_descriptor_t &desc, uint8_t *buffer, size_t size, T **outPdu)
{
    auto res = aper_decode(nullptr, &desc, reinterpret_cast<void **>(outPdu), buffer, size, 0, 0);
    return res.code == RC_OK;
}

template <typename T>
inline bool DecodeInPlace(asn_TYPE_descriptor_t &desc, const OCTET_STRING_t &octetString, T **outPdu)
{
    return DecodeInPlace(desc, octetString.buf, octetString.size, outPdu);
}

template <typename T>
inline T *Decode(asn_TYPE_descriptor_t &desc, const OCTET_STRING_t &octetString)
{
    return Decode<T>(desc, octetString.buf, octetString.size);
}

} // namespace nr::gnb::ngap_encode