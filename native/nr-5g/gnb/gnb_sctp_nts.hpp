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

    NwSctpConnectionRequest(int clientId, std::string localAddress, uint16_t localPort, std::string remoteAddress,
                            uint16_t remotePort, sctp::PayloadProtocolId ppid)
        : NtsMessage(NtsMessageType::SCTP_CONNECTION_REQUEST), clientId(clientId),
          localAddress(std::move(localAddress)), localPort(localPort), remoteAddress(std::move(remoteAddress)),
          remotePort(remotePort), ppid(ppid)
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

} // namespace nr::gnb
