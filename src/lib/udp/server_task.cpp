//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "server_task.hpp"

#include <cstring>

#define BUFFER_SIZE 65536
#define TIMEOUT_MS 500

udp::UdpServerTask::UdpServerTask(NtsTask *targetTask) : server{}, targetTask(targetTask)
{
    server = new UdpServer();
}

udp::UdpServerTask::UdpServerTask(const std::string &address, uint16_t port, NtsTask *targetTask)
    : server{}, targetTask(targetTask)
{
    server = new UdpServer(address, port);
}

udp::UdpServerTask::~UdpServerTask() = default;

void udp::UdpServerTask::onStart()
{
}

void udp::UdpServerTask::onLoop()
{
    uint8_t buffer[BUFFER_SIZE];

    InetAddress peerAddress{};

    int size = server->Receive(buffer, BUFFER_SIZE, TIMEOUT_MS, peerAddress);
    if (size > 0)
    {
        std::vector<uint8_t> v(size);
        std::memcpy(v.data(), buffer, size);
        targetTask->push(std::make_unique<NwUdpServerReceive>(OctetString{std::move(v)}, peerAddress));
    }
}

void udp::UdpServerTask::onQuit()
{
    delete server;
}

void udp::UdpServerTask::send(const InetAddress &to, const OctetString &packet)
{
    server->Send(to, packet.data(), static_cast<size_t>(packet.length()));
}
