//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "rls.hpp"
#include <gnb/nts.hpp>

namespace nr::gnb
{

GnbRls::GnbRls(std::string nodeName, std::unique_ptr<Logger> logger, NtsTask *targetTask)
    : RlsGnbEntity(std::move(nodeName)), m_logger(std::move(logger)), m_targetTask(targetTask)
{
}

void GnbRls::logWarn(const std::string &msg)
{
    m_logger->warn(msg);
}

void GnbRls::logError(const std::string &msg)
{
    m_logger->err(msg);
}

void GnbRls::onUeConnected(int ue, std::string name)
{
    auto *w = new NwGnbMrToMr(NwGnbMrToMr::UE_CONNECTED);
    w->ue = ue;
    w->name = std::move(name);
    m_targetTask->push(w);
}

void GnbRls::onUeReleased(int ue, rls::ECause cause)
{
    auto *w = new NwGnbMrToMr(NwGnbMrToMr::UE_RELEASED);
    w->ue = ue;
    w->cause = cause;
    m_targetTask->push(w);
}

void GnbRls::deliverUplinkPayload(int ue, rls::EPayloadType type, OctetString &&payload)
{
    auto *w = new NwGnbMrToMr(NwGnbMrToMr::RECEIVE_OVER_UDP);
    w->ue = ue;
    w->type = type;
    w->pdu = std::move(payload);
    m_targetTask->push(w);
}

void GnbRls::sendRlsPdu(const InetAddress &address, OctetString &&pdu)
{
    auto *w = new NwGnbMrToMr(NwGnbMrToMr::SEND_OVER_UDP);
    w->address = address;
    w->pdu = std::move(pdu);
    m_targetTask->push(w);
}

} // namespace nr::gnb
