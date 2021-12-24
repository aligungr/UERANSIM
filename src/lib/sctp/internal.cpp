//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "internal.hpp"
#include "types.hpp"

#include <cstring>

#include <arpa/inet.h>
#include <netdb.h>
#include <netinet/in.h>
#include <netinet/sctp.h>
#include <sys/socket.h>
#include <unistd.h>

#include <utils/common.hpp>

#define RECEIVE_BUFFER_SIZE 8192u

namespace sctp
{

[[noreturn]] static void ThrowError(const std::string &prefix)
{
    throw SctpError(prefix);
}

[[noreturn]] static void ThrowError(const std::string &prefix, int err)
{
    char *msg = strdup(strerror(err));
    std::string str(msg);
    free(msg);
    throw SctpError(prefix + str);
}

int CreateSocket()
{
    int sd = socket(AF_INET6, SOCK_STREAM, IPPROTO_SCTP);
    if (sd < 0)
        ThrowError("SCTP socket could not be created");
    return sd;
}

void BindSocket(int sd, const std::string &address, uint16_t port)
{
    sockaddr addr = {};
    std::memset(&addr, 0, sizeof(sockaddr_in));

    int ipVersion = utils::GetIpVersion(address);

    if (ipVersion == 4)
    {
        auto addr4 = (sockaddr_in *)&addr;
        addr4->sin_family = AF_INET;
        addr4->sin_port = htons(port);

        if (inet_pton(AF_INET, address.c_str(), &(addr4->sin_addr)) != 1)
            ThrowError("Bad IPv4 address.");
    }
    else if (ipVersion == 6)
    {
        auto addr6 = (sockaddr_in6 *)&addr;
        addr6->sin6_family = AF_INET6;
        addr6->sin6_port = htons(port);
        if (inet_pton(AF_INET6, address.c_str(), &(addr6->sin6_addr)) != 1)
            ThrowError("Bad IPv6 address.");
    }
    else
        ThrowError("Bad IPv4 or IPv6 address.");

    if (sctp_bindx(sd, &addr, 1, SCTP_BINDX_ADD_ADDR))
        ThrowError("SCTP bind failed: ", errno);
}

void SetInitOptions(int sd, int maxRxStreams, int maxTxStreams, int maxAttempts, int initTimeoutMs)
{
    sctp_initmsg init{};
    init.sinit_max_instreams = maxRxStreams;
    init.sinit_num_ostreams = maxTxStreams;
    init.sinit_max_attempts = maxAttempts;
    init.sinit_max_init_timeo = initTimeoutMs;

    if (setsockopt(sd, IPPROTO_SCTP, SCTP_INITMSG, &init, (socklen_t)sizeof(struct sctp_initmsg)) < 0)
        ThrowError("SCTP SCTP_INITMSG option failed");
}

void SetEventOptions(int sd)
{
    sctp_event_subscribe events{};
    events.sctp_data_io_event = 1;
    events.sctp_association_event = 1;
    events.sctp_address_event = 1;
    events.sctp_send_failure_event = 1;
    events.sctp_peer_error_event = 1;
    events.sctp_shutdown_event = 1;
    events.sctp_partial_delivery_event = 1;

    if (setsockopt(sd, IPPROTO_SCTP, SCTP_EVENTS, &events, 8) < 0)
        ThrowError("SCTP SCTP_EVENTS option failed");
}

void StartListening(int sd)
{
    if (listen(sd, 10))
        ThrowError("SCTP socket could not listen: ", errno);
}

void CloseSocket(int sd)
{
    close(sd);
}

void Accept(int sd)
{
    sockaddr saddr{};
    socklen_t saddr_size{};

    int clientSd = accept(sd, &saddr, &saddr_size);
    if (clientSd < 0)
        ThrowError("SCTP accept failure: ", errno);
}

void Connect(int sd, const std::string &address, uint16_t port)
{
    addrinfo hints{};
    std::memset(&hints, 0, sizeof(hints));
    hints.ai_family = AF_UNSPEC;
    hints.ai_socktype = 0;
    hints.ai_flags = 0;
    hints.ai_protocol = 0;

    addrinfo *result, *rp;

    int s = getaddrinfo(address.c_str(), std::to_string(port).c_str(), &hints, &result);
    if (s != 0)
        ThrowError("SCTP getaddrinfo failure: ", errno);

    bool connected = false;

    for (rp = result; rp != nullptr; rp = rp->ai_next)
    {
        // TODO: make non blocking and set timeout (O_NONBLOCK etc)
        if (connect(sd, rp->ai_addr, rp->ai_addrlen) != -1)
        {
            connected = true;
            break;
        }
    }

    freeaddrinfo(result);

    if (!connected)
        ThrowError("SCTP could not connect: ", errno);
}

void SendMessage(int sd, const uint8_t *buffer, size_t length, int ppid, uint16_t stream)
{
    if (sctp_sendmsg(sd, buffer, length, nullptr, 0, htonl(ppid), 0, stream, 0, 0) < 0)
        ThrowError("SCTP send message failure: ", errno);
}

void ReceiveMessage(int sd, uint32_t ppid, ISctpHandler *handler)
{
    uint8_t buffer[RECEIVE_BUFFER_SIZE];
    sockaddr_in addr{};
    sctp_sndrcvinfo info{};
    int flags = 0;

    auto fromLen = (socklen_t)sizeof(sockaddr_in);

    std::memset((void *)&addr, 0, sizeof(sockaddr_in));
    std::memset((void *)&info, 0, sizeof(sctp_sndrcvinfo));

    int r = sctp_recvmsg(sd, (void *)buffer, RECEIVE_BUFFER_SIZE, (sockaddr *)&addr, &fromLen, &info, &flags);

    if (r < 0)
    {
        if (errno == ECONNRESET)
        {
            if (handler)
                handler->onConnectionReset();
            return;
        }
        ThrowError("SCTP receive message failure: ", errno);
    }

    if (r == 0)
        return; // no data

    if (!(flags & MSG_EOR))
        ThrowError("SCTP partial message received, which is not handled");

    if (flags & MSG_NOTIFICATION)
    {
        auto *notification = (union sctp_notification *)buffer;

        if (notification->sn_header.sn_type == sctp_sn_type::SCTP_SHUTDOWN_EVENT)
        {
            if (handler)
                handler->onAssociationShutdown();
        }
        else if (notification->sn_header.sn_type == sctp_sn_type::SCTP_ASSOC_CHANGE)
        {
            auto *sac = &notification->sn_assoc_change;
            if (sac)
            {
                if (sac->sac_state == sctp_sac_state::SCTP_COMM_UP)
                {
                    sctp_status status{};
                    std::memset(&status, 0, sizeof(sctp_status));
                    auto socklen = (socklen_t)sizeof(sctp_status);

                    if (getsockopt(sd, IPPROTO_SCTP, SCTP_STATUS, &status, &socklen) < 0)
                        ThrowError("SCTP_STATUS obtaining failed: ", errno);

                    if (handler)
                        handler->onAssociationSetup(status.sstat_assoc_id, status.sstat_instrms, status.sstat_outstrms);
                }
                else if (sac->sac_state == sctp_sac_state::SCTP_COMM_LOST)
                {
                    if (handler)
                        handler->onAssociationShutdown();
                }
                else
                {
                    if (handler)
                        handler->onUnhandledNotification();
                }
            }
        }
        else
        {
            if (handler)
                handler->onUnhandledNotification();
        }
    }
    else
    {
        if (info.sinfo_ppid != ppid)
        {
            // Perhaps we should ignore the message. But some core networks does not set this correctly. Therefore
            // do nothing for now.
        }

        handler->onMessage(buffer, r, info.sinfo_stream);
    }
}

} // namespace sctp
