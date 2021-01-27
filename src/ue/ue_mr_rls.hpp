//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "ue_types.hpp"
#include <nas.hpp>
#include <urs_rls_ue_entity.hpp>

namespace nr::ue
{

class UeRls : public rls::RlsUeEntity
{
  private:
    std::unique_ptr<Logger> logger;
    NtsTask *targetTask;

  public:
    UeRls(const std::string &nodeName, const std::vector<InetAddress> &gnbSearchList, std::unique_ptr<Logger> logger,
          NtsTask *targetTask);

  protected:
    void logWarn(const std::string &msg) override;
    void logError(const std::string &msg) override;
    void startWaitingTimer(int period) override;
    void searchFailure(rls::ECause cause) override;
    void onRelease(rls::ECause cause) override;
    void onConnect(const std::string &gnbName) override;
    void sendRlsPdu(const InetAddress &address, OctetString &&pdu) override;
    void deliverPayload(rls::EPayloadType type, OctetString &&payload) override;
};

} // namespace nr::ue