//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "client.hpp"
#include "internal.hpp"

sctp::SctpClient::SctpClient(PayloadProtocolId ppid, const std::string &address) : sd(0), ppid(ppid)
{
    try
    {
        sd = CreateSocket(address);
        SetInitOptions(sd, 10, 10, 10, 10 * 1000);
        SetEventOptions(sd);
    }
    catch (const std::exception &e)
    {
        CloseSocket(sd);
        throw;
    }
}

sctp::SctpClient::~SctpClient()
{
    CloseSocket(sd);
}

void sctp::SctpClient::connect(const std::string &address, uint16_t port) const
{
    Connect(sd, address, port);
}

void sctp::SctpClient::send(uint16_t stream, const uint8_t *buffer, size_t offset, size_t length)
{
    SendMessage(sd, buffer + offset, length, (int)ppid, stream);
}

void sctp::SctpClient::send(uint16_t stream, const uint8_t *buffer, size_t length)
{
    send(stream, buffer, 0, length);
}

void sctp::SctpClient::send(uint16_t stream, const std::vector<uint8_t> &data)
{
    send(stream, data.data(), data.size());
}

void sctp::SctpClient::receive(ISctpHandler *handler)
{
    ReceiveMessage(sd, static_cast<uint32_t>(ppid), handler);
}

void sctp::SctpClient::bind(const std::string &address, uint16_t port)
{
    BindSocket(sd, address, port);
}
