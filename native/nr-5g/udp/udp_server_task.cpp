//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "udp_server_task.hpp"

#include <cstring>

#define BUFFER_SIZE 65536
#define TIMEOUT_MS 500

nr::udp::UdpServerTask::UdpServerTask(const std::string &address, uint16_t port, NtsTask *targetTask)
    : server{}, targetTask(targetTask)
{
    server = new UdpServer(address, port);
}

nr::udp::UdpServerTask::~UdpServerTask() = default;

void nr::udp::UdpServerTask::onStart()
{
}

void nr::udp::UdpServerTask::onLoop()
{
    uint8_t buffer[BUFFER_SIZE];

    PeerAddress peerAddress{};

    int size = server->receive(buffer, BUFFER_SIZE, TIMEOUT_MS, peerAddress);
    if (size > 0)
    {
        std::vector<uint8_t> v;
        v.reserve(size);
        std::memcpy(v.data(), buffer, size);

        targetTask->push(new NwUdpServerReceive(OctetString{std::move(v)}, peerAddress));
    }
}

void nr::udp::UdpServerTask::onQuit()
{
    delete server;
}
