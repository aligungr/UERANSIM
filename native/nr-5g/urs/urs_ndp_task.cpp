//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "urs_ndp_task.hpp"

#include <constants.hpp>
#include <udp_server.hpp>

#define READ_TIMEOUT 500

static Socket CreateSocket(const InetAddress &address)
{
    Socket s(address.getSockAddr()->sa_family, SOCK_DGRAM, IPPROTO_UDP);
    s.setReuseAddress();
    s.bind(address);
    return s;
}

namespace urs
{

class NdpEntityImpl : public NdpEntity
{
  private:
    std::unique_ptr<Logger> &logger;
    Socket &clientSocket;

  public:
    NdpEntityImpl(uint32_t appVersion, uint8_t nodeType, const std::string &nodeName, uint16_t portalPort,
                  uint16_t mrPort, const std::string &portalIp, std::unique_ptr<Logger> &logger, Socket &clientSocket)
        : NdpEntity(appVersion, nodeType, nodeName, portalPort, mrPort, portalIp), logger(logger),
          clientSocket(clientSocket)
    {
    }

  protected:
    void onErrorReceived(uint8_t type, const std::string &message) override
    {
        // TODO:
    }

    void onSendMessage(const InetAddress &address, uint8_t *buffer, size_t size) override
    {
        clientSocket.send(address, buffer, size);
    }

    void onNodeDiscover(uint8_t nodeType, const std::string &nodeName, uint16_t portalPort, uint16_t mrPort,
                        const std::string &portalIp) override
    {
        // TODO:
    }
};

NdpTask::NdpTask(std::unique_ptr<Logger> &logger, uint8_t nodeType, std::string nodeName, uint16_t portalPort,
                 uint16_t mrPort, std::string portalAddress)
    : logger(logger), nodeType(nodeType), nodeName(std::move(nodeName)), portalPort(portalPort), mrPort(mrPort),
      portalAddress(std::move(portalAddress)), entity{}, localSocket{}, externalSocket{}, clientSocket{}
{
}

void NdpTask::onStart()
{
    localSocket = CreateSocket({cons::NdpLoopback, cons::NdpPort});
    externalSocket = CreateSocket({portalAddress, cons::NdpPort});
    clientSocket = Socket::CreateUdp4();

    entity = new NdpEntityImpl(cons::AppVersion, nodeType, nodeName, portalPort, mrPort, portalAddress, logger,
                               clientSocket);
}

void NdpTask::onLoop()
{
    Socket s = Socket::Select({localSocket, externalSocket}, {}, READ_TIMEOUT);
    if (!s.hasFd())
        return;

    uint8_t buffer[Ndp::PACKET_MAX] = {0};
    InetAddress address;

    size_t n = s.receive(buffer, Ndp::PACKET_MAX, READ_TIMEOUT, address);

    entity->handleMessage(address, buffer, n);
}

void NdpTask::onQuit()
{
    delete entity;

    localSocket.close();
    externalSocket.close();
    clientSocket.close();
}

} // namespace urs
