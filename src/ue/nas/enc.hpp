//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <lib/nas/nas.hpp>
#include <ue/types.hpp>

namespace nr::ue::nas_enc
{

std::unique_ptr<nas::SecuredMmMessage> Encrypt(NasSecurityContext &ctx, const nas::PlainMmMessage &msg,
                                               bool bypassCiphering, bool noCipheredHeader);
std::unique_ptr<nas::NasMessage> Decrypt(NasSecurityContext &ctx, const nas::SecuredMmMessage &msg);

uint32_t ComputeMac(nas::ETypeOfIntegrityProtectionAlgorithm alg, NasCount count, bool is3gppAccess, bool isUplink,
                    const OctetString &key, const OctetString &plainMessage);

} // namespace nr::ue::nas_enc
