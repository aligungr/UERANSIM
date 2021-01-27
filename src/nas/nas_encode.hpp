//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "nas_msg.hpp"

namespace nas
{

void EncodeNasMessage(const NasMessage &msg, OctetString &stream);
std::unique_ptr<NasMessage> DecodeNasMessage(const OctetBuffer &stream);

} // namespace nas
