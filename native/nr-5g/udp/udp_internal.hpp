//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <string>

#include <udp_utils.hpp>

namespace nr::udp
{

int CreateSocket(bool isIpV4);
void BindSocket(int sd, const std::string &address, uint16_t port);
void CloseSocket(int sd);
int TimedReceive(int sd, uint8_t *buffer, size_t bufferSize, int timeoutMs, PeerAddress &outAddress);

} // namespace nr::udp
