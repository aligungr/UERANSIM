//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <logger.hpp>
#include <nts.hpp>
#include <urs_rls_gnb_entity.hpp>

namespace nr::gnb
{

class GnbRls : public rls::RlsGnbEntity
{
  private:
    std::unique_ptr<Logger> logger;
    NtsTask *targetTask;
    bool isN1Ready;

  public:
    explicit GnbRls(std::string nodeName, std::unique_ptr<Logger> logger, NtsTask *targetTask);

  protected:
    void logWarn(const std::string &msg) override;
    void logError(const std::string &msg) override;
    bool isInReadyState() override;
    void onUeConnected(int ue, std::string name) override;
    void onUeReleased(int ue, rls::ECause cause) override;
    void sendRlsPdu(const InetAddress &address, OctetString &&pdu) override;
    void deliverUplinkPayload(int ue, rls::EPayloadType type, OctetString &&payload) override;

  public:
    void setN1IsReady(bool isReady);
};

} // namespace nr::gnb
