//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "crypt_uea2.hpp"
#include "crypt_snow3g.hpp"

using u8 = uint8_t;
using u32 = uint32_t;
using u64 = uint64_t;

void crypto::uea2::F8(const u8 *pKey, u32 count, u32 bearer, u32 dir, u8 *pData, u32 length)
{
    u32 K[4], IV[4];
    int n = static_cast<int>((length + 31) / 32);
    u32 *KS;
    for (int i = 0; i < 4; i++)
        K[3 - i] = (pKey[4 * i] << 24) ^ (pKey[4 * i + 1] << 16) ^ (pKey[4 * i + 2] << 8) ^ (pKey[4 * i + 3]);
    IV[3] = count;
    IV[2] = (bearer << 27) | ((dir & 0x1) << 26);
    IV[1] = IV[3];
    IV[0] = IV[2];
    crypto::snow3g::Initialize(K, IV);
    KS = new u32[n];
    crypto::snow3g::GenerateKeyStream((u32 *)KS, n);
    for (int i = 0; i < n; i++)
    {
        pData[4 * i + 0] ^= (u8)(KS[i] >> 24) & 0xff;
        pData[4 * i + 1] ^= (u8)(KS[i] >> 16) & 0xff;
        pData[4 * i + 2] ^= (u8)(KS[i] >> 8) & 0xff;
        pData[4 * i + 3] ^= (u8)(KS[i]) & 0xff;
    }
    delete[] KS;
}

static u64 MUL64x(u64 V, u64 c)
{
    if (V & 0x8000000000000000)
        return (V << 1) ^ c;
    else
        return V << 1;
}

static u64 MUL64xPOW(u64 V, u8 i, u64 c)
{
    if (i == 0)
        return V;
    else
        return MUL64x(MUL64xPOW(V, i - 1, c), c);
}

static u64 MUL64(u64 V, u64 P, u64 c)
{
    u64 result = 0;
    for (int i = 0; i < 64; i++)
        if ((P >> i) & 0x1)
            result ^= MUL64xPOW(V, i, c);
    return result;
}

static u8 mask8bit(int n)
{
    return 0xFF ^ ((1 << (8 - n)) - 1);
}

u32 crypto::uea2::F9(const u8 *pKey, u32 count, u32 fresh, u32 dir, const u8 *pData, u64 length)
{
    u32 K[4], IV[4], z[5];
    u32 i = 0, D;
    u8 MAC_I[4] = {0, 0, 0, 0};
    u64 EVAL, V, P, Q, c, M_D_2;
    int rem_bits;
    for (i = 0; i < 4; i++)
        K[3 - i] = (pKey[4 * i] << 24) ^ (pKey[4 * i + 1] << 16) ^ (pKey[4 * i + 2] << 8) ^ (pKey[4 * i + 3]);
    IV[3] = count;
    IV[2] = fresh;
    IV[1] = count ^ (dir << 31);
    IV[0] = fresh ^ (dir << 15);
    z[0] = z[1] = z[2] = z[3] = z[4] = 0;
    crypto::snow3g::Initialize(K, IV);
    crypto::snow3g::GenerateKeyStream(z, 5);
    P = (u64)z[0] << 32 | (u64)z[1];
    Q = (u64)z[2] << 32 | (u64)z[3];
    if ((length % 64) == 0)
        D = (length >> 6) + 1;
    else
        D = (length >> 6) + 2;
    EVAL = 0;
    c = 0x1b;
    for (i = 0; i < D - 2; i++)
    {
        V = EVAL ^ ((u64)pData[8 * i] << 56 | (u64)pData[8 * i + 1] << 48 | (u64)pData[8 * i + 2] << 40 |
                    (u64)pData[8 * i + 3] << 32 | (u64)pData[8 * i + 4] << 24 | (u64)pData[8 * i + 5] << 16 |
                    (u64)pData[8 * i + 6] << 8 | (u64)pData[8 * i + 7]);
        EVAL = MUL64(V, P, c);
    }
    rem_bits = static_cast<int>(length % 64);
    if (rem_bits == 0)
        rem_bits = 64;
    M_D_2 = 0;
    i = 0;
    while (rem_bits > 7)
    {
        M_D_2 |= (u64)pData[8 * (D - 2) + i] << (8 * (7 - i));
        rem_bits -= 8;
        i++;
    }
    if (rem_bits > 0)
        M_D_2 |= (u64)(pData[8 * (D - 2) + i] & mask8bit(rem_bits)) << (8 * (7 - i));
    V = EVAL ^ M_D_2;
    EVAL = MUL64(V, P, c);
    EVAL ^= length;
    EVAL = MUL64(EVAL, Q, c);
    for (i = 0; i < 4; i++)
        MAC_I[i] = ((EVAL >> (56 - (i * 8))) ^ (z[4] >> (24 - (i * 8)))) & 0xff;

    u32 mac32 = 0;
    mac32 |= (MAC_I[0] << 24);
    mac32 |= (MAC_I[1] << 16);
    mac32 |= (MAC_I[2] << 8);
    mac32 |= MAC_I[3];
    return mac32;
}