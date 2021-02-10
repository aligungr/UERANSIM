//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "rls.hpp"
#include <ue/nts.hpp>
#include <urs/rls/ue_entity.hpp>

namespace nr::ue
{

UeRls::UeRls(const std::string &nodeName, const std::vector<InetAddress> &gnbSearchList, std::unique_ptr<Logger> logger,
             NtsTask *targetTask)
    : RlsUeEntity(nodeName, gnbSearchList), m_logger(std::move(logger)), m_targetTask(targetTask)
{
}

void UeRls::logWarn(const std::string &msg)
{
    m_logger->warn(msg);
}

void UeRls::logError(const std::string &msg)
{
    m_logger->err(msg);
}

void UeRls::startWaitingTimer(int period)
{
    auto *w = new NwUeMrToMr(NwUeMrToMr::RLS_START_WAITING_TIMER);
    w->period = period;
    m_targetTask->push(w);
}

void UeRls::searchFailure(rls::ECause cause)
{
    auto *w = new NwUeMrToMr(NwUeMrToMr::RLS_SEARCH_FAILURE);
    w->cause = cause;
    m_targetTask->push(w);
}

void UeRls::onRelease(rls::ECause cause)
{
    auto *w = new NwUeMrToMr(NwUeMrToMr::RLS_RELEASED);
    w->cause = cause;
    m_targetTask->push(w);
}

void UeRls::onConnect(const std::string &gnbName)
{
    auto *w = new NwUeMrToMr(NwUeMrToMr::RLS_CONNECTED);
    w->gnbName = gnbName;
    m_targetTask->push(w);
}

void UeRls::sendRlsPdu(const InetAddress &address, OctetString &&pdu)
{
    auto *w = new NwUeMrToMr(NwUeMrToMr::RLS_SEND_OVER_UDP);
    w->address = address;
    w->pdu = std::move(pdu);
    m_targetTask->push(w);
}

void UeRls::deliverPayload(rls::EPayloadType type, OctetString &&payload)
{
    auto *w = new NwUeMrToMr(NwUeMrToMr::RLS_RECEIVE_OVER_UDP);
    w->type = type;
    w->pdu = std::move(payload);
    m_targetTask->push(w);
}

} // namespace nr::ue
