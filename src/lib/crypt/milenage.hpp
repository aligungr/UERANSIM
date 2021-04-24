//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
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