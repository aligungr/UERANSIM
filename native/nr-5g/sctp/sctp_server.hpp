//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <string>

namespace sctp
{

class SctpServer
{
  private:
    int sd;

  public:
    SctpServer(const std::string &address, uint16_t port);
    ~SctpServer();

    void start();

    // TODO: Other functionalities
};

} // namespace sctp