//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include "cmd_handler.hpp"
#include <nas/utils.hpp>
#include <ue/mr/task.hpp>
#include <ue/tun/tun.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>

namespace nr::ue
{

UeAppTask::UeAppTask(TaskBase *base) : m_base{base}, m_statusInfo{}, m_tunTasks{}
{
    m_logger = m_base->logBase->makeUniqueLogger(m_base->config->getLoggerPrefix() + "app");
}

void UeAppTask::onStart()
{
}

void UeAppTask::onQuit()
{
    for (auto &tunTask : m_tunTasks)
    {
        if (tunTask != nullptr)
        {
            tunTask->quit();
            delete tunTask;
            tunTask = nullptr;
        }
    }
}

void UeAppTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_MR_TO_APP: {
        auto *w = dynamic_cast<NwUeMrToApp *>(msg);
        switch (w->present)
        {
        case NwUeMrToApp::DATA_PDU_DELIVERY: {
            auto *tunTask = m_tunTasks[w->psi];
            if (tunTask)
            {
                auto *nw = new NwAppToTun(NwAppToTun::DATA_PDU_DELIVERY);
                nw->psi = w->psi;
                nw->data = std::move(w->data);
                tunTask->push(nw);
            }
            break;
        }
        }
        break;
    }
    case NtsMessageType::UE_TUN_TO_APP: {
        auto *w = dynamic_cast<NwUeTunToApp *>(msg);
        switch (w->present)
        {
        case NwUeTunToApp::DATA_PDU_DELIVERY: {
            auto *nw = new NwAppToMr(NwAppToMr::DATA_PDU_DELIVERY);
            nw->psi = w->psi;
            nw->data = std::move(w->data);
            m_base->mrTask->push(nw);
            break;
        }
        case NwUeTunToApp::TUN_ERROR: {
            m_logger->err("TUN failure [%s]", w->error.c_str());
            break;
        }
        }
        break;
    }
    case NtsMessageType::UE_STATUS_UPDATE: {
        receiveStatusUpdate(*dynamic_cast<NwUeStatusUpdate *>(msg));
        break;
    }
    case NtsMessageType::UE_CLI_COMMAND: {
        auto *w = dynamic_cast<NwUeCliCommand *>(msg);
        UeCmdHandler::HandleCmd(*m_base, *w);
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }
    delete msg;
}

void UeAppTask::receiveStatusUpdate(NwUeStatusUpdate &msg)
{
    if (msg.what == NwUeStatusUpdate::SESSION_ESTABLISHMENT)
    {
        auto *session = msg.pduSession;

        UeStatusInfo::UePduSessionInfo sessionInfo{};
        sessionInfo.type = nas::utils::EnumToString(session->sessionType);
        if (session->pduAddress.has_value())
            sessionInfo.address = utils::OctetStringToIp(session->pduAddress->pduAddressInformation);

        m_statusInfo.pduSessions[session->id] = std::move(sessionInfo);

        setupTunInterface(session);
    }
}

void UeAppTask::setupTunInterface(const PduSession *pduSession)
{
    if (!utils::IsRoot())
    {
        m_logger->err("TUN interface could not be setup. Permission denied. Please run the UE with 'sudo'");
        return;
    }

    if (!pduSession->pduAddress.has_value())
    {
        m_logger->err("Connection could not setup. PDU address is missing.");
        return;
    }

    if (pduSession->pduAddress->sessionType != nas::EPduSessionType::IPV4 ||
        pduSession->sessionType != nas::EPduSessionType::IPV4)
    {
        m_logger->err("Connection could not setup. PDU session type is not supported.");
        return;
    }

    int psi = pduSession->id;
    if (psi == 0 || psi > 15)
    {
        m_logger->err("Connection could not setup. Invalid PSI.");
        return;
    }

    if (m_tunTasks[psi] != nullptr)
    {
        m_logger->err("Connection could not setup. TUN task for specified PSI is non-null.");
        return;
    }

    std::string error{}, allocatedName{};
    int fd = tun::TunAllocate(cons::TunNamePrefix, allocatedName, error);
    if (fd == 0 || error.length() > 0)
    {
        m_logger->err("TUN allocation failure [%s]", error.c_str());
        return;
    }

    std::string ipAddress = utils::OctetStringToIp(pduSession->pduAddress->pduAddressInformation);

    bool r = tun::TunConfigure(allocatedName, ipAddress, m_base->config->configureRouting, error);
    if (!r || error.length() > 0)
    {
        m_logger->err("TUN configuration failure [%s]", error.c_str());
        return;
    }

    auto *task = new TunTask(m_base, psi, fd);
    m_tunTasks[psi] = task;
    task->start();

    m_logger->info("Connection setup for PDU session[%d] is successful, TUN interface[%s, %s] is up.", pduSession->id,
                   allocatedName.c_str(), ipAddress.c_str());
}

} // namespace nr::ue
