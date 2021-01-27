//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "crypt_milenage.hpp"
#include "external/milenage.hpp"

namespace crypto::milenage
{

Milenage Calculate(const OctetString &opc, const OctetString &key, const OctetString &rand, const OctetString &sqn,
                   const OctetString &amf)
{
    Milenage r;
    r.mac_a = OctetString::FromSpare(8);
    r.mac_s = OctetString::FromSpare(8);
    r.res = OctetString::FromSpare(8);
    r.ck = OctetString::FromSpare(16);
    r.ik = OctetString::FromSpare(16);
    r.ak = OctetString::FromSpare(6);
    r.ak_r = OctetString::FromSpare(6);

    if (milenage_f1(opc.data(), key.data(), rand.data(), sqn.data(), amf.data(), r.mac_a.data(), r.mac_s.data()))
        throw std::runtime_error("OPC calculation failed");
    if (milenage_f2345(opc.data(), key.data(), rand.data(), r.res.data(), r.ck.data(), r.ik.data(), r.ak.data(),
                       r.ak_r.data()))
        throw std::runtime_error("OPC calculation failed");

    return r;
}

OctetString CalculateOpC(const OctetString &op, const OctetString &key)
{
    OctetString opc = OctetString::FromSpare(op.length());
    if (milenage_opc_gen(opc.data(), key.data(), op.data()))
        throw std::runtime_error("OPC calculation failed");
    return opc;
}

} // namespace crypt::milenage