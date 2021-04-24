//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "types.hpp"

#include <string>
#include <vector>

namespace sctp
{

class SctpClient
{
  private:
    const int sd;
    const PayloadProtocolId ppid;

  public:
    explicit SctpClient(PayloadProtocolId ppid);
    ~SctpClient();

    void bind(const std::string &address, uint16_t port);
    void connect(const std::string &address, uint16_t port) const;

    void send(uint16_t stream, const uint8_t *buffer, size_t offset, size_t length);
    void send(uint16_t stream, const uint8_t *buffer, size_t length);
    void send(uint16_t stream, const std::vector<uint8_t> &data);

    void receive(ISctpHandler *handler);
};

} // namespace sctp