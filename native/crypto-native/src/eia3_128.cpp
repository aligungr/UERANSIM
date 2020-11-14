// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "eia3_128.h"
#include "zuc.h"
#include <cstdlib>

static void ZUC(uint8_t *pKey, uint8_t *pIv, uint32_t *pKeyStream, uint32_t nKeyStream)
{
    Zuc::Initialize(pKey, pIv);
    Zuc::GenerateKeyStream(pKeyStream, nKeyStream);
}

static uint32_t GetWord(uint32_t *pData, uint32_t index)
{
    uint32_t ti = index % 32;
    if (ti == 0)
        return pData[index / 32];
    else
        return (pData[index / 32] << ti) | (pData[index / 32 + 1] >> (32 - ti));
}

static uint8_t GetBit(uint32_t *pData, uint32_t index)
{
    return (pData[index / 32] & (1 << (31 - (index % 32)))) ? 1 : 0;
}

uint32_t EIA3_128::EIA3(uint8_t *pKey, uint32_t count, uint32_t direction, uint32_t bearer, uint32_t length, uint32_t *pData)
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

    T = 0;
    for (i = 0; i < length; i++)
    {
        if (GetBit(pData, i))
            T ^= GetWord(z, i);
    }

    T ^= GetWord(z, length);
    uint32_t MAC = T ^ z[L - 1];
    delete[] z;

    return MAC;
}