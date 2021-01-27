//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "ue_types.hpp"
#include <nas.hpp>

namespace nr::ue::nas_enc
{

std::unique_ptr<nas::SecuredMmMessage> Encrypt(NasSecurityContext &ctx, const nas::PlainMmMessage &msg);
std::unique_ptr<nas::NasMessage> Decrypt(NasSecurityContext &ctx, const nas::SecuredMmMessage &msg);

uint32_t ComputeMac(nas::ETypeOfIntegrityProtectionAlgorithm alg, NasCount count, bool is3gppAccess,
                    bool isUplink, const OctetString &key, const OctetString &plainMessage);

} // namespace nr::ue::nas_enc
