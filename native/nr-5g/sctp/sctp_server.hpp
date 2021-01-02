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