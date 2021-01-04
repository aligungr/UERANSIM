//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <nts.hpp>
#include <sctp.hpp>
#include <utility>

namespace nr::gnb
{

struct NwSctpConnectionRequest : NtsMessage
{
    int clientId;

    std::string localAddress;
    uint16_t localPort;

    std::string remoteAddress;
    uint16_t remotePort;

    sctp::PayloadProtocolId ppid;

    NtsTask *associatedTask;

    NwSctpConnectionRequest(int clientId, std::string localAddress, uint16_t localPort, std::string remoteAddress,
                            uint16_t remotePort, sctp::PayloadProtocolId ppid, NtsTask *associatedTask)
        : NtsMessage(NtsMessageType::SCTP_CONNECTION_REQUEST), clientId(clientId),
          localAddress(std::move(localAddress)), localPort(localPort), remoteAddress(std::move(remoteAddress)),
          remotePort(remotePort), ppid(ppid), associatedTask(associatedTask)
    {
    }
};

struct NwSctpAssociationSetup : NtsMessage
{
    int clientId;
    int associationId;
    int inStreams;
    int outStreams;

    NwSctpAssociationSetup(int clientId, int associationId, int inStreams, int outStreams)
        : NtsMessage(NtsMessageType::SCTP_ASSOCIATION_SETUP), clientId(clientId), associationId(associationId),
          inStreams(inStreams), outStreams(outStreams)
    {
    }
};

struct NwSctpAssociationShutdown : NtsMessage
{
    int clientId;

    explicit NwSctpAssociationShutdown(int clientId)
        : NtsMessage(NtsMessageType::SCTP_ASSOCIATION_SHUTDOWN), clientId(clientId)
    {
    }
};

struct NwSctpClientReceive : NtsMessage
{
    int clientId;
    uint8_t *buffer;
    size_t length;
    uint16_t stream;

    NwSctpClientReceive(int clientId, uint8_t *buffer, size_t length, uint16_t stream)
        : NtsMessage(NtsMessageType::SCTP_CLIENT_RECEIVE), clientId(clientId), buffer(buffer), length(length),
          stream(stream)
    {
    }
};

struct NwSctpUnhandledNotificationReceive : NtsMessage
{
    int clientId;

    explicit NwSctpUnhandledNotificationReceive(int clientId)
        : NtsMessage(NtsMessageType::SCTP_UNHANDLED_NOTIFICATION_RECEIVE), clientId(clientId)
    {
    }
};

struct NwSctpConnectionClose : NtsMessage
{
    int clientId;

    explicit NwSctpConnectionClose(int clientId) : NtsMessage(NtsMessageType::SCTP_CONNECTION_CLOSE), clientId(clientId)
    {
    }
};

struct NwSctpSendMessage : NtsMessage
{
    const int clientId;
    const uint16_t stream;
    uint8_t *const buffer;
    const size_t offset;
    const size_t length;

    NwSctpSendMessage(int clientId, uint16_t stream, uint8_t *buffer, size_t offset, size_t length)
        : NtsMessage(NtsMessageType::SCTP_SEND_MESSAGE), clientId(clientId), stream(stream), buffer(buffer),
          offset(offset), length(length)
    {
    }

    ~NwSctpSendMessage() override
    {
        // This buffer was allocated by asn1c library using malloc/calloc
        free(buffer);
    }
};

} // namespace nr::gnb
