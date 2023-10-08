//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "eea3.hpp"
#include "zuc.hpp"

#include <endian.h>
#include <vector>

namespace crypto::eea3
{

static void ZUC(const uint8_t *pKey, uint8_t *pIv, uint32_t *pKeyStream, uint32_t nKeyStream)
{
    zuc::Initialize(pKey, pIv);
    zuc::GenerateKeyStream(pKeyStream, nKeyStream);
}

static uint32_t GetWord(uint32_t *pData, uint32_t index)
{
    uint32_t ti = index % 32;
    if (ti == 0)
        return pData[index / 32];
    else
        return (pData[index / 32] << ti) | (pData[index / 32 + 1] >> (32 - ti));
}

static uint8_t GetBit(const uint32_t *pData, uint32_t index)
{
    return (pData[index / 32] & (1 << (31 - (index % 32)))) ? 1 : 0;
}

uint32_t EIA3(const uint8_t *pKey, uint32_t count, uint32_t direction, uint32_t bearer, uint32_t length,
              const uint32_t *pData)
{
    uint32_t *z, N, L, T, i;
    uint8_t IV[16];

    IV[0] = (count >> 24) & 0xFF;
    IV[1] = (count >> 16) & 0xFF;
    IV[2] = (count >> 8) & 0xFF;
    IV[3] = count & 0xFF;

    IV[4] = (bearer << 3) & 0xF8;
    IV[5] = IV[6] = IV[7] = 0;

    IV[8] = ((count >> 24) & 0xFF) ^ ((direction & 1) << 7);
    IV[9] = (count >> 16) & 0xFF;
    IV[10] = (count >> 8) & 0xFF;
    IV[11] = count & 0xFF;

    IV[12] = IV[4];
    IV[13] = IV[5];
    IV[14] = IV[6] ^ ((direction & 1) << 7);
    IV[15] = IV[7];

    N = length + 64;
    L = (N + 31) / 32;
    z = new uint32_t[L];
    ZUC(pKey, IV, z, L);

    std::vector<uint32_t> data_htobe32(length / 32 + 1);
    for (uint32_t j = 0; j < (length + 31) / 32; j++)
        data_htobe32[j] = htobe32(pData[j]);

    T = 0;
    for (i = 0; i < length; i++)
    {
        if (GetBit(data_htobe32.data(), i))
            T ^= GetWord(z, i);
    }

    T ^= GetWord(z, length);
    uint32_t MAC = T ^ z[L - 1];
    delete[] z;

    return MAC;
}

void EEA3(const uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t direction, uint32_t length, uint32_t *pData)
{
    uint32_t *z, L, i;
    uint8_t iv[16];

    L = (length + 31) / 32;
    z = new uint32_t[L];

    iv[0] = (count >> 24) & 0xFF;
    iv[1] = (count >> 16) & 0xFF;
    iv[2] = (count >> 8) & 0xFF;
    iv[3] = count & 0xFF;

    iv[4] = ((bearer << 3) | ((direction & 1) << 2)) & 0xFC;
    iv[5] = 0;
    iv[6] = 0;
    iv[7] = 0;

    iv[8] = iv[0];
    iv[9] = iv[1];
    iv[10] = iv[2];
    iv[11] = iv[3];

    iv[12] = iv[4];
    iv[13] = iv[5];
    iv[14] = iv[6];
    iv[15] = iv[7];

    ZUC(pKey, iv, z, L);
    for (i = 0; i < L; i++)
        pData[i] = be32toh(htobe32(pData[i]) ^ z[i]);
    delete[] z;
}

} // namespace crypto::eea3
