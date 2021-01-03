//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "sctp_handler.hpp"
#include <string>

namespace sctp
{

int CreateSocket();
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