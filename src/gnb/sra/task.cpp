//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <gnb/gtp/task.hpp>
#include <gnb/nts.hpp>
#include <gnb/rrc/task.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/libc_error.hpp>

namespace nr::gnb
{

GnbSraTask::GnbSraTask(TaskBase *base) : m_base{base}, m_udpTask{}, m_ueCtx{}, m_stiToUeId{}
{
    m_logger = m_base->logBase->makeUniqueLogger("sra");
    m_sti = utils::Random64();
}

void GnbSraTask::onStart()
{
    try
    {
        m_udpTask = new udp::UdpServerTask(m_base->config->portalIp, cons::PortalPort, this);
        m_udpTask->start();
    }
    catch (const LibError &e)
    {
        m_logger->err("SRA failure [%s]", e.what());
        quit();
        return;
    }
}

void GnbSraTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
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

void GnbSraTask::onQuit()
{
    if (m_udpTask != nullptr)
        m_udpTask->quit();
    delete m_udpTask;
}

} // namespace nr::gnb
