//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <network.hpp>
#include <octet_buffer.hpp>
#include <string>
#include <udp_server.hpp>
#include <utility>

namespace urs
{

// UERANSIM Node Discovery Protocol
struct Ndp
{
    static const constexpr uint8_t MAGIC_NUMBER[16] = {'U', 'E', 'R', 'A', 'N', 'S', 'I', 'M',
                                                       '-', 'N', 'D', 'P', '_', 'A', 'G', '!'};

    static const constexpr size_t PACKET_MAX = 2500;

    enum NodeType
    {
        NODE_UNSPECIFIED = 0,
        NODE_UE = 1,
        NODE_GNB = 2
    };

    enum MessageType
    {
        MSG_DISCOVERY_REQUEST = 0,
        MSG_DISCOVERY_RESPONSE = 1,
    };

    enum ErrorType
    {
        ERR_UNSPECIFIED = 0,
        ERR_VERSION_MISMATCH = 1,
    };
};

class NdpEntity
{
  private:
    uint32_t appVersion;
    uint8_t nodeType;
    std::string nodeName;
    uint16_t portalPort;
    uint16_t mrPort;
    std::string portalIp;

  public:
    NdpEntity(uint32_t appVersion, uint8_t nodeType, std::string nodeName, uint16_t portalPort, uint16_t mrPort,
              std::string portalIp)
        : appVersion(appVersion), nodeType(nodeType), nodeName(std::move(nodeName)), portalPort(portalPort),
          mrPort(mrPort), portalIp(std::move(portalIp))
    {
    }

    virtual ~NdpEntity() = default;

  protected:
    virtual void onErrorReceived(uint8_t type, const std::string &message) = 0;
    virtual void onSendMessage(const InetAddress &address, uint8_t *buffer, size_t size) = 0;
    virtual void onNodeDiscover(uint8_t nodeType, const std::string &nodeName, uint16_t portalPort, uint16_t mrPort,
                                const std::string &portalIp) = 0;

  public:
    void handleMessage(const InetAddress &address, uint8_t *data, size_t size);
    void sendDiscoveryRequest(const InetAddress &address, uint8_t nodeType, const std::string &nodeName);

  private:
    void onNodeMatches(const InetAddress &address);
    void onBroadcastVersionMismatch(const InetAddress &address);
    void sendErrorIndication(const InetAddress &address, uint8_t errorType, const std::string &message);
    void sendMessage(const InetAddress &address, uint8_t *buffer, size_t size);
};

} // namespace urs