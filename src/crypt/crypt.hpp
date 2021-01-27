//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <octet_string.hpp>

namespace crypto
{

/* KDF and MAC etc. */
OctetString CalculatePrfPrime(const OctetString &key, const OctetString &input, int outputLength);
OctetString HmacSha256(const OctetString &key, const OctetString &input);
OctetString CalculateKdfKey(const OctetString &key, int fc, OctetString *parameters, int numberOfParameter);
OctetString CalculateKdfKey(const OctetString &key, int fc1, int fc2, OctetString *parameters, int numberOfParameter);
OctetString EncodeKdfString(const std::string &string);

/* Snow3G etc. */
std::vector<uint32_t> Snow3g(const OctetString &key, const OctetString &iv, int length);
std::vector<uint32_t> Zuc(const OctetString &key, const OctetString &iv, int length);

/* UIA2 and UEA2 */
uint32_t ComputeMacUia2(const uint8_t *pKey, uint32_t count, uint32_t fresh, uint32_t dir, const uint8_t *pData,
                        uint64_t length);
void EncryptUea2(const uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t dir, uint8_t *pData, uint32_t length);

/* EEA1 and EIA1 */
void EncryptEea1(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key);
void DecryptEea1(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key);
uint32_t ComputeMacEia1(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key);

/* EEA2 and EIA2 */
void EncryptEea2(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key);
void DecryptEea2(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key);
uint32_t ComputeMacEia2(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key);

/* EEA3 and EIA3 */
void EncryptEea3(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key);
void DecryptEea3(uint32_t count, int bearer, int direction, OctetString &message, const OctetString &key);
uint32_t ComputeMacEia3(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key);

} // namespace crypt