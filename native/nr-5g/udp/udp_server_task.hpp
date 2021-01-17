//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <nts.hpp>
#include <octet_string.hpp>
#include <udp_server.hpp>

namespace nr::udp
{

struct NwUdpServerReceive : NtsMessage
{
    OctetString packet;
    PeerAddress peerAddress;

    explicit NwUdpServerReceive(OctetString &&packet, const PeerAddress &peerAddress)
        : NtsMessage(NtsMessageType::UDP_SERVER_RECEIVE), packet(std::move(packet)), peerAddress(peerAddress)
    {
    }
};

class UdpServerTask : public NtsTask
{
  private:
    UdpServer *server;
    NtsTask *targetTask;

  public:
    UdpServerTask(const std::string &address, uint16_t port, NtsTask *targetTask);
    ~UdpServerTask() override;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace nr::udp
