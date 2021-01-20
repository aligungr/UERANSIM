//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "urs_ndp.hpp"
#include <logger.hpp>
#include <nts.hpp>

namespace urs
{

class NdpTask : public NtsTask
{
  private:
    std::unique_ptr<Logger> &logger;
    uint8_t nodeType;
    std::string nodeName;
    uint16_t portalPort;
    uint16_t mrPort;
    std::string portalAddress;

    NdpEntity *entity;
    Socket localSocket;    // server on 127.0.0.1
    Socket externalSocket; // server on portal IP address
    Socket clientSocket;   // client

  public:
    NdpTask(std::unique_ptr<Logger> &logger, uint8_t nodeType, std::string nodeName, uint16_t portalPort,
            uint16_t mrPort, std::string portalAddress);
    ~NdpTask() override = default;

  protected:
    void onStart() override;
    void onLoop() override;
    void onQuit() override;
};

} // namespace urs