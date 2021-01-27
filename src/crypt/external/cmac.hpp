#pragma once

#include <cstdint>

extern "C"
{
    void external_aes_cmac(uint8_t *cmac, const uint8_t *key, const uint8_t *msg, uint32_t len);
}
