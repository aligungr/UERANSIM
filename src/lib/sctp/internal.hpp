//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "types.hpp"

#include <string>

namespace sctp
{

int CreateSocket(const std::string &address);
void BindSocket(int sd, const std::string &address, uint16_t port);
void SetInitOptions(int sd, int maxRxStreams, int maxTxStreams, int maxAttempts, int initTimeoutMs);
void SetEventOptions(int sd);
void StartListening(int sd);
void CloseSocket(int sd);
void Accept(int sd);
void Connect(int sd, const std::string &address, uint16_t port);
void SendMessage(int sd, const uint8_t *buffer, size_t length, int ppid, uint16_t stream);
void ReceiveMessage(int sd, uint32_t ppid, ISctpHandler *handler);

} // namespace sctp