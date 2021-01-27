//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_mr_rls.hpp"
#include "ue_nts.hpp"

#include <urs_rls_ue_entity.hpp>

namespace nr::ue
{

UeRls::UeRls(const std::string &nodeName, const std::vector<InetAddress> &gnbSearchList,
    std::unique_ptr<Logger> logger, NtsTask *targetTask)
    : RlsUeEntity(nodeName, gnbSearchList), logger(std::move(logger)), targetTask(targetTask)
{
}

void UeRls::logWarn(const std::string &msg)
{
    logger->warn(msg);
}

void UeRls::logError(const std::string &msg)
{
    logger->err(msg);
}

void UeRls::startWaitingTimer(int period)
{
    targetTask->push(new NwRlsStartWaitingTimer(period));
}

void UeRls::searchFailure(rls::ECause cause)
{
    targetTask->push(new NwRlsSearchFailure(cause));
}

void UeRls::onRelease(rls::ECause cause)
{
    targetTask->push(new NwRlsReleased(cause));
}

void UeRls::onConnect(const std::string &gnbName)
{
    targetTask->push(new NwRlsConnected(gnbName));
}

void UeRls::sendRlsPdu(const InetAddress &address, OctetString &&pdu)
{
    targetTask->push(new NwRlsSendPdu(address, std::move(pdu)));
}

void UeRls::deliverPayload(rls::EPayloadType type, OctetString &&payload)
{
    targetTask->push(new NwDownlinkPayload(type, std::move(payload)));
}

} // namespace nr::ue
