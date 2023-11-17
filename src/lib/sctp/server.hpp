//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <string>
#include <cstdint>

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