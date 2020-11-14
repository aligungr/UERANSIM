// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#include "eea3_128.h"
#include "zuc.h"

static void ZUC(uint8_t *pKey, uint8_t *pIv, uint32_t *pKeyStream, uint32_t nKeyStream)
{
    Zuc::Initialize(pKey, pIv);
    Zuc::GenerateKeyStream(pKeyStream, nKeyStream);
}

void EEA3_128::EEA3(uint8_t *pKey, uint32_t count, uint32_t bearer, uint32_t direction, uint32_t length, uint32_t *pData)
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
        pData[i] ^= z[i];
    delete[] z;
}