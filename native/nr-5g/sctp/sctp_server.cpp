#include "sctp_server.hpp"
#include "sctp_internal.hpp"

sctp::SctpServer::SctpServer(const std::string &address, uint16_t port) : sd(0)
{
    try
    {
        sd = CreateSocket();
        BindSocket(sd, address, port);
        SetInitOptions(sd, 10, 10, 10, 10 * 1000);
        SetEventOptions(sd);
        StartListening(sd);
    }
    catch (const std::exception &e)
    {
        CloseSocket(sd);
        throw;
    }
}

sctp::SctpServer::~SctpServer()
{
    CloseSocket(sd);
}

void sctp::SctpServer::start()
{
    Accept(sd);
}
