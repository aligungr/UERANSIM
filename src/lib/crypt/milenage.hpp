//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <utils/octet_string.hpp>

namespace crypto::milenage
{

struct Milenage
{
    OctetString res;
    OctetString ck;
    OctetString ik;
    OctetString ak;
    OctetString ak_r;
    OctetString mac_a;
    OctetString mac_s;
};

Milenage Calculate(const OctetString &opc, const OctetString &key, const OctetString &rand, const OctetString &sqn,
                   const OctetString &amf);

OctetString CalculateOpC(const OctetString &op, const OctetString &key);

} // namespace crypt::milenage