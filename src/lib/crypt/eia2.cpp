//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "eia2.hpp"
#include "mac.hpp"

#include <utils/bits.hpp>

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
