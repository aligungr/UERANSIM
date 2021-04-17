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

static constexpr const int TIMER_ID_MEASUREMENT = 1;
static constexpr const int TIMER_PERIOD_MEASUREMENT_MIN = 1;
static constexpr const int TIMER_PERIOD_MEASUREMENT_MAX = 2000;

static constexpr const int TIMER_ID_RAPID_LAUNCH = 2;
static constexpr const int TIMER_PERIOD_RAPID_LAUNCH = 750;

namespace nr::ue
{

UeRlsTask::UeRlsTask(TaskBase *base)
    : m_base{base}, m_udpTask{}, m_cellSearchSpace{}, m_pendingMeasurements{}, m_activeMeasurements{},
      m_pendingPlmnResponse{}, m_measurementPeriod{TIMER_PERIOD_MEASUREMENT_MIN}, m_servingCell{}
{
    m_logger = m_base->logBase->makeUniqueLogger(m_base->config->getLoggerPrefix() + "rls");

    for (auto &addr : m_base->config->gnbSearchList)
        m_cellSearchSpace.emplace_back(addr, cons::PortalPort);

    m_sti = utils::Random64();
}

void UeRlsTask::onStart()
{
    m_udpTask = new udp::UdpServerTask(this);

    std::vector<InetAddress> gnbSearchList{};
    for (auto &ip : m_base->config->gnbSearchList)
        gnbSearchList.emplace_back(ip, cons::PortalPort);

    m_udpTask->start();

    setTimer(TIMER_ID_MEASUREMENT, m_measurementPeriod);
    setTimer(TIMER_ID_RAPID_LAUNCH, TIMER_PERIOD_RAPID_LAUNCH);
    onMeasurement();
}

void UeRlsTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_RRC_TO_RLS: {
        auto *w = dynamic_cast<NwUeRrcToRls *>(msg);
        switch (w->present)
        {
        case NwUeRrcToRls::PLMN_SEARCH_REQUEST:
            plmnSearchRequested();
            break;
        case NwUeRrcToRls::CELL_SELECTION_COMMAND:
            handleCellSelectionCommand(w->cellId, w->isSuitableCell);
            break;
        case NwUeRrcToRls::RRC_PDU_DELIVERY:
            deliverUplinkPdu(rls::EPduType::RRC, std::move(w->pdu),
                             OctetString::FromOctet4(static_cast<int>(w->channel)));
            break;
        case NwUeRrcToRls::RESET_STI:
            m_sti = utils::Random64();
            break;
        }
        break;
    }
    case NtsMessageType::UE_APP_TO_RLS: {
        auto *w = dynamic_cast<NwUeAppToRls *>(msg);
        switch (w->present)
        {
        case NwUeAppToRls::DATA_PDU_DELIVERY: {
            deliverUplinkPdu(rls::EPduType::DATA, std::move(w->pdu), OctetString::FromOctet4(static_cast<int>(w->psi)));
            break;
        }
        }
        break;
    }
    case NtsMessageType::TIMER_EXPIRED: {
        auto *w = dynamic_cast<NwTimerExpired *>(msg);
        if (w->timerId == TIMER_ID_MEASUREMENT)
        {
            setTimer(TIMER_ID_MEASUREMENT, m_measurementPeriod);
            onMeasurement();
        }
        else if (w->timerId == TIMER_ID_RAPID_LAUNCH)
        {
            slowDownMeasurements();
        }
        break;
    }
    case NtsMessageType::UDP_SERVER_RECEIVE: {
        auto *w = dynamic_cast<udp::NwUdpServerReceive *>(msg);
        auto rlsMsg = rls::DecodeRlsMessage(OctetView{w->packet});
        if (rlsMsg == nullptr)
        {
            m_logger->err("Unable to decode RLS message");
            break;
        }
        receiveRlsMessage(w->fromAddress, *rlsMsg);
        break;
    }
    default:
        m_logger->unhandledNts(msg);
        break;
    }

    delete msg;
}

void UeRlsTask::onQuit()
{
    m_udpTask->quit();
    delete m_udpTask;
}

void UeRlsTask::slowDownMeasurements()
{
    m_measurementPeriod = TIMER_PERIOD_MEASUREMENT_MAX;
}

} // namespace nr::ue
