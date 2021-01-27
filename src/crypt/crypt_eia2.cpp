//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "crypt_eia2.hpp"
#include "crypt_mac.hpp"

#include <bits.hpp>

static OctetString GenerateMacInput(uint32_t count, int bearer, int direction, const OctetString &message)
{
    OctetString m{};
    m.appendOctet4(count);
    m.appendOctet(bits::Ranged8({{5, bearer}, {1, direction}, {2, 0}}));
    m.appendOctet3(0);
    m.append(message);
    return m;
}

namespace crypto::eia2
{

uint32_t Compute(uint32_t count, int bearer, int direction, const OctetString &message, const OctetString &key)
{
    assert(key.length() == 16);

    auto macInput = GenerateMacInput(count, bearer, direction, message);

    uint8_t buf[16] = {0};
    AesCmac(buf, key.data(), macInput.data(), macInput.length());

    return (uint32_t)octet4{buf[0], buf[1], buf[2], buf[3]};
}

} // namespace crypto::eia2
