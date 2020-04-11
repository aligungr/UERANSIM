#include "eia3_128.h"
#include "zuc.h"
#include <cstdlib>

static void ZUC(uint8_t *k, uint8_t *iv, uint32_t *ks, int len)
{
    Zuc::Initialize(k, iv);
    Zuc::GenerateKeyStream(ks, len);
}

static uint32_t GET_WORD(uint32_t *DATA, uint32_t i)
{
    uint32_t WORD, ti;

    ti = i % 32;
    if (ti == 0)
        WORD = DATA[i / 32];
    else
        WORD = (DATA[i / 32] << ti) | (DATA[i / 32 + 1] >> (32 - ti));

    return WORD;
}

static uint8_t GET_BIT(uint32_t *DATA, uint32_t i)
{
    return (DATA[i / 32] & (1 << (31 - (i % 32)))) ? 1 : 0;
}

uint32_t EIA3_128::EIA3(uint8_t *IK, uint32_t COUNT, uint32_t DIRECTION, uint32_t BEARER, uint32_t LENGTH, uint32_t *M)
{
    uint32_t *z, N, L, T, i;
    uint8_t IV[16];

    IV[0] = (COUNT >> 24) & 0xFF;
    IV[1] = (COUNT >> 16) & 0xFF;
    IV[2] = (COUNT >> 8) & 0xFF;
    IV[3] = COUNT & 0xFF;

    IV[4] = (BEARER << 3) & 0xF8;
    IV[5] = IV[6] = IV[7] = 0;

    IV[8] = ((COUNT >> 24) & 0xFF) ^ ((DIRECTION & 1) << 7);
    IV[9] = (COUNT >> 16) & 0xFF;
    IV[10] = (COUNT >> 8) & 0xFF;
    IV[11] = COUNT & 0xFF;

    IV[12] = IV[4];
    IV[13] = IV[5];
    IV[14] = IV[6] ^ ((DIRECTION & 1) << 7);
    IV[15] = IV[7];

    N = LENGTH + 64;
    L = (N + 31) / 32;
    z = new uint32_t[L];
    ZUC(IK, IV, z, L);

    T = 0;
    for (i = 0; i < LENGTH; i++)
    {
        if (GET_BIT(M, i))
            T ^= GET_WORD(z, i);
    }

    T ^= GET_WORD(z, LENGTH);
    uint32_t MAC = T ^ z[L - 1];
    delete[] z;

    return MAC;
}