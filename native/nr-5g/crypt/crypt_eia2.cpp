//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "crypt_eia2.hpp"
#include "external/aes_engine.hpp"

#include <bits.hpp>
#include <cstring>

static const int BLOCK_SIZE = 16;
static const int MAC_SIZE = 4;
static const int POLY = 0x87;

static OctetString GenerateMacInput(uint32_t count, int bearer, int direction, const OctetString &message)
{
    OctetString m{};
    m.appendOctet4(count);
    m.appendOctet(bits::Ranged8({{5, bearer}, {1, direction}, {2, 0}}));
    m.appendOctet3(0);
    m.append(message);
    return m;
}

static int ShiftLeft(const uint8_t *block, int blockLen, uint8_t *output)
{
    int i = blockLen;
    int bit = 0;
    while (--i >= 0)
    {
        int b = block[i] & 0xff;
        output[i] = (uint8_t)((b << 1) | bit);
        bit = (b >> 7) & 1;
    }
    return bit;
}

static void DoubleLu(const uint8_t *in, uint8_t *out, int blockLen)
{
    int carry = ShiftLeft(in, blockLen, out);

    int mask = (-carry) & 0xff;
    out[blockLen - 3] ^= ((POLY >> 16) & 0xFF) & mask;
    out[blockLen - 2] ^= ((POLY >> 8) & 0xFF) & mask;
    out[blockLen - 1] ^= (POLY & 0xFF) & mask;
}

static uint32_t Cmac(const OctetString &message, const OctetString &key)
{
    auto copy = message.subCopy(0);

    /////////// Initialize cipher ///////////
    AESEngine cipher;
    {
        cipher.reset();
        cipher.init(true, key.data());
    }

    ///////////// Generate sub keys ///////////
    uint8_t subKey1[BLOCK_SIZE] = {0};
    uint8_t subKey2[BLOCK_SIZE] = {0};
    {
        uint8_t zeros[BLOCK_SIZE] = {0};
        uint8_t L[BLOCK_SIZE] = {0};

        cipher.processBlock(zeros, 0, BLOCK_SIZE, L, 0, BLOCK_SIZE);

        DoubleLu(L, subKey1, BLOCK_SIZE);
        DoubleLu(subKey1, subKey2, BLOCK_SIZE);
    }

    ///////////// Process last block ///////////
    {
        // Add padding
        if (copy.length() % BLOCK_SIZE != 0)
        {
            int padding = BLOCK_SIZE - (copy.length() % BLOCK_SIZE);
            copy.appendOctet(0b10000000);
            if (padding > 1)
                copy.appendPadding(padding - 1);

            // Xor with subKey2
            int offset = copy.length() - BLOCK_SIZE;
            for (int i = 0; i < BLOCK_SIZE; i++)
                copy.data()[offset + i] ^= subKey2[i];
        }
        else
        {
            // Xor with subKey1
            int offset = copy.length() - BLOCK_SIZE;
            for (int i = 0; i < BLOCK_SIZE; i++)
                copy.data()[offset + i] ^= subKey1[i];
        }
    }

    ///////////// Perform tag calculation ///////////
    uint32_t tag;
    {
        int currentOffset = 0;
        uint8_t lastCode[BLOCK_SIZE] = {0};

        while (currentOffset < copy.length())
        {
            for (int i = 0; i < BLOCK_SIZE; i++)
                copy.data()[currentOffset + i] ^= lastCode[i];

            uint8_t code[BLOCK_SIZE] = {0};
            cipher.processBlock(copy.data(), currentOffset, copy.length(), code, 0, BLOCK_SIZE);

            std::memcpy(lastCode, code, BLOCK_SIZE);

            currentOffset += BLOCK_SIZE;
        }

        uint8_t result[MAC_SIZE] = {0};
        std::memcpy(result, lastCode, 4);

        tag = (uint32_t)octet4{result[0], result[1], result[2], result[3]};
    }

    return tag;
}

namespace crypto::eia2
{

uint32_t Compute(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key)
{
    if (key.length() != BLOCK_SIZE)
        throw std::runtime_error("crypt_eia2 failure");

    auto macInput = GenerateMacInput(count, bearer, direction, message);
    return Cmac(macInput, key);
}

} // namespace crypto::eia2
