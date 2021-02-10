//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "message.hpp"

namespace gtp
{

bool EncodeGtpMessage(const GtpMessage &msg, OctetString &stream);
GtpMessage *DecodeGtpMessage(const OctetView &stream);

} // namespace gtp
