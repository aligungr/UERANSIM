//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "msg.hpp"

namespace nas
{

void EncodeNasMessage(const NasMessage &msg, OctetString &stream);
std::unique_ptr<NasMessage> DecodeNasMessage(const OctetView &stream);

} // namespace nas
