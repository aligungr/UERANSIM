//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include "cmd_handler.hpp"
#include <lib/nas/utils.hpp>
#include <ue/nas/task.hpp>
#include <ue/rls/task.hpp>
#include <ue/tun/tun.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>

static constexpr const int SWITCH_OFF_TIMER_ID = 1;
static constexpr const int SWITCH_OFF_DELAY = 500;

namespace nr::ue
{

UeAppTask::UeAppTask(TaskBase *base) : m_base{base}
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
    case NtsMessageType::UE_RLS_TO_APP: {
        auto *w = dynamic_cast<NwUeRlsToApp *>(msg);
        switch (w->present)
        {
        case NwUeRlsToApp::DATA_PDU_DELIVERY: {
            auto *tunTask = m_tunTasks[w->psi];
            if (tunTask)
            {
                auto *nw = new NwAppToTun(NwAppToTun::DATA_PDU_DELIVERY);
                nw->psi = w->psi;
                nw->data = std::move(w->pdu);
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
            handleUplinkDataRequest(w->psi, std::move(w->data));
            break;
        }
        case NwUeTunToApp::TUN_ERROR: {
            m_logger->err("TUN failure [%s]", w->error.c_str());
            break;
        }
        }
        break;
    }
    case NtsMessageType::UE_NAS_TO_APP: {
        auto *w = dynamic_cast<NwUeNasToApp *>(msg);
        switch (w->present)
        {
        case NwUeNasToApp::PERFORM_SWITCH_OFF: {
            setTimer(SWITCH_OFF_TIMER_ID, SWITCH_OFF_DELAY);
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
        UeCmdHandler handler{m_base};
        handler.handleCmd(*w);
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        if (w->timerId == SWITCH_OFF_TIMER_ID)
        {
            m_logger->info("UE device is switching off");
            m_base->ueController->performSwitchOff(m_base->ue);
        }
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

        UePduSessionInfo sessionInfo{};
        sessionInfo.psi = session->psi;
        sessionInfo.type = nas::utils::EnumToString(session->sessionType);
        if (session->pduAddress.has_value())
            sessionInfo.address = utils::OctetStringToIp(session->pduAddress->pduAddressInformation);
        sessionInfo.isEmergency = session->isEmergency;
        sessionInfo.uplinkPending = false;

        m_pduSessions[session->psi] = std::move(sessionInfo);

        setupTunInterface(session);
        return;
    }

    if (msg.what == NwUeStatusUpdate::SESSION_RELEASE)
    {
        if (m_tunTasks[msg.psi] != nullptr)
        {
            m_tunTasks[msg.psi]->quit();
            delete m_tunTasks[msg.psi];
            m_tunTasks[msg.psi] = nullptr;
        }

        if (m_pduSessions[msg.psi].has_value())
        {
            m_logger->info("PDU session[%d] released", msg.psi);
            m_pduSessions[msg.psi] = {};
        }
        return;
    }

    if (msg.what == NwUeStatusUpdate::CM_STATE)
    {
        m_cmState = msg.cmState;
        return;
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

    int psi = pduSession->psi;
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

    bool r = tun::TunConfigure(allocatedName, ipAddress, cons::TunMtu, m_base->config->configureRouting, error);
    if (!r || error.length() > 0)
    {
        m_logger->err("TUN configuration failure [%s]", error.c_str());
        return;
    }

    auto *task = new TunTask(m_base, psi, fd);
    m_tunTasks[psi] = task;
    task->start();

    m_logger->info("Connection setup for PDU session[%d] is successful, TUN interface[%s, %s] is up.", pduSession->psi,
                   allocatedName.c_str(), ipAddress.c_str());
}

void UeAppTask::handleUplinkDataRequest(int psi, OctetString &&data)
{
    if (!m_pduSessions[psi].has_value())
        return;

    if (m_cmState == ECmState::CM_CONNECTED)
    {
        if (m_pduSessions[psi]->uplinkPending)
        {
            m_pduSessions[psi]->uplinkPending = false;

            auto *w = new NwUeAppToNas(NwUeAppToNas::UPLINK_STATUS_CHANGE);
            w->psi = psi;
            w->isPending = false;
            m_base->nasTask->push(w);
        }

        auto *nw = new NwUeAppToRls(NwUeAppToRls::DATA_PDU_DELIVERY);
        nw->psi = psi;
        nw->pdu = std::move(data);
        m_base->rlsTask->push(nw);
    }
    else
    {
        if (!m_pduSessions[psi]->uplinkPending)
        {
            m_pduSessions[psi]->uplinkPending = true;

            auto *w = new NwUeAppToNas(NwUeAppToNas::UPLINK_STATUS_CHANGE);
            w->psi = psi;
            w->isPending = true;
            m_base->nasTask->push(w);
        }
    }
}

} // namespace nr::ue
