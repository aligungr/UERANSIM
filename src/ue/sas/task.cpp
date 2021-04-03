//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <ue/nts.hpp>
#include <utils/constants.hpp>

static const int TIMER_ID_MEASUREMENT = 1;
static const int TIMER_PERIOD_MEASUREMENT = 2000;

namespace nr::ue
{

UeSasTask::UeSasTask(TaskBase *base)
    : m_base{base}, m_udpTask{}, m_cellSearchSpace{}, m_pendingMeasurements{}, m_activeMeasurements{}
{
    m_logger = m_base->logBase->makeUniqueLogger(m_base->config->getLoggerPrefix() + "sas");

    for (auto &addr : m_base->config->gnbSearchList)
        m_cellSearchSpace.emplace_back(addr, cons::PortalPort);
}

void UeSasTask::onStart()
{
    m_udpTask = new udp::UdpServerTask(this);

    std::vector<InetAddress> gnbSearchList{};
    for (auto &ip : m_base->config->gnbSearchList)
        gnbSearchList.emplace_back(ip, cons::PortalPort);

    m_udpTask->start();

    setTimer(TIMER_ID_MEASUREMENT, TIMER_PERIOD_MEASUREMENT);
    onMeasurement();
}

void UeSasTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_RRC_TO_SAS: {
        auto *w = dynamic_cast<NwUeRrcToSas *>(msg);
        if (w->present == NwUeRrcToSas::PLMN_SEARCH_REQUEST)
            plmnSearchRequested();
        else if (w->present == NwUeRrcToSas::CELL_SELECTION_COMMAND)
            receiveCellSelectionCommand(w->cellId, w->isSuitableCell);
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        if (w->timerId == TIMER_ID_MEASUREMENT)
        {
            setTimer(TIMER_ID_MEASUREMENT, TIMER_PERIOD_MEASUREMENT);
            onMeasurement();
        }
        break;
    }
    case NtsMessageType::UDP_SERVER_RECEIVE: {
        auto *w = dynamic_cast<udp::NwUdpServerReceive *>(msg);
        auto sasMsg = sas::DecodeSasMessage(OctetView{w->packet});
        if (sasMsg == nullptr)
        {
            m_logger->err("Unable to decode SAS message");
            break;
        }
        receiveSasMessage(w->fromAddress, *sasMsg);
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }

    delete msg;
}

void UeSasTask::onQuit()
{
    m_udpTask->quit();
    delete m_udpTask;
}

} // namespace nr::ue
