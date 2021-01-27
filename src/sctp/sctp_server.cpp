//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sctp_server.hpp"
#include "sctp_error.hpp"
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
    catch (const SctpError &e)
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
