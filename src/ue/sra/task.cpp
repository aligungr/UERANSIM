//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <ue/nts.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>

static const int TIMER_ID_MEASUREMENT = 1;
static const int TIMER_PERIOD_MEASUREMENT = 2000;

namespace nr::ue
{

UeSraTask::UeSraTask(TaskBase *base)
    : m_base{base}, m_udpTask{}, m_cellSearchSpace{}, m_pendingMeasurements{}, m_activeMeasurements{}, m_servingCell{}
{
    m_logger = m_base->logBase->makeUniqueLogger(m_base->config->getLoggerPrefix() + "sra");

    for (auto &addr : m_base->config->gnbSearchList)
        m_cellSearchSpace.emplace_back(addr, cons::PortalPort);

    m_sti = utils::Random64();
}

void UeSraTask::onStart()
{
    m_udpTask = new udp::UdpServerTask(this);

    std::vector<InetAddress> gnbSearchList{};
    for (auto &ip : m_base->config->gnbSearchList)
        gnbSearchList.emplace_back(ip, cons::PortalPort);

    m_udpTask->start();

    setTimer(TIMER_ID_MEASUREMENT, TIMER_PERIOD_MEASUREMENT);
    onMeasurement();
}

void UeSraTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_RRC_TO_SRA: {
        auto *w = dynamic_cast<NwUeRrcToSra *>(msg);
        switch (w->present)
        {
        case NwUeRrcToSra::PLMN_SEARCH_REQUEST:
            plmnSearchRequested();
            break;
        case NwUeRrcToSra::CELL_SELECTION_COMMAND:
            handleCellSelectionCommand(w->cellId, w->isSuitableCell);
            break;
        case NwUeRrcToSra::RRC_PDU_DELIVERY:
            deliverUplinkRrc(w->channel, std::move(w->pdu));
            break;
        }
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
        auto sraMsg = sra::DecodeSraMessage(OctetView{w->packet});
        if (sraMsg == nullptr)
        {
            m_logger->err("Unable to decode SRA message");
            break;
        }
        receiveSraMessage(w->fromAddress, *sraMsg);
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }

    delete msg;
}

void UeSraTask::onQuit()
{
    m_udpTask->quit();
    delete m_udpTask;
}

} // namespace nr::ue
