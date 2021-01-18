//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_gtp_task.hpp"
#include "gnb_mr_task.hpp"

#include <gtp_encode.hpp>
#include <gtp_message.hpp>

#include <ASN_NGAP_QosFlowSetupRequestItem.h>
#include <ASN_NGAP_QosFlowSetupRequestList.h>

namespace nr::gnb
{

GtpTask::GtpTask(TaskBase *base)
    : base{base}, udpServer{}, ueContexts{}, rateLimiter(std::make_unique<RateLimiter>()), pduSessions{}, sessionTree{}
{
    logger = base->logBase->makeUniqueLogger("gtp");
}

void GtpTask::onStart()
{
    logger->debug("GTP layer has been started");

    udpServer = new udp::UdpServerTask(base->config->gtpIp, 2152, this);
    udpServer->start();
}

void GtpTask::onQuit()
{
    udpServer->quit();
    delete udpServer;

    ueContexts.clear();
}

void GtpTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UDP_SERVER_RECEIVE:
        handleUdpReceive(dynamic_cast<udp::NwUdpServerReceive *>(msg));
        break;
    case NtsMessageType::GTP_UE_CONTEXT_UPDATE:
        handleUeContextUpdate(dynamic_cast<NwUeContextUpdate *>(msg));
        break;
    case NtsMessageType::NGAP_PDU_SESSION_RESOURCE_CREATE:
        handleSessionCreate(dynamic_cast<NwPduSessionResourceCreate *>(msg));
        break;
    case NtsMessageType::GNB_MR_UPLINK_DATA:
        handleUplinkData(dynamic_cast<NwUplinkData *>(msg));
        break;
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

void GtpTask::handleUeContextUpdate(NwUeContextUpdate *msg)
{
    if (!ueContexts.count(msg->ueId))
        ueContexts[msg->ueId] = std::make_unique<GtpUeContext>(msg->ueId);

    auto &ue = ueContexts[msg->ueId];
    ue->ueAmbr = msg->ueAmbr;

    updateAmbrForUe(ue->ueId);

    delete msg;
}

void GtpTask::handleSessionCreate(NwPduSessionResourceCreate *msg)
{
    PduSessionResource *session = msg->resource;
    if (ueContexts.count(session->ueId))
    {
        logger->err("PDU session resource could not be created, UE context with ID %d not found", session->ueId);
        delete session;
        return;
    }

    uint64_t sessionInd = MakeSessionResInd(session->ueId, session->psi);
    pduSessions[sessionInd] = std::unique_ptr<PduSessionResource>(session);

    sessionTree.insert(sessionInd, session->downTunnel.teid);

    updateAmbrForUe(session->ueId);
    updateAmbrForSession(sessionInd);

    delete msg;
}

void GtpTask::handleUplinkData(NwUplinkData *msg)
{
    const uint8_t *data = msg->data.data();

    // ignore non IPv4 packets
    if ((data[0] >> 4 & 0xF) != 4)
    {
        delete msg;
        return;
    }

    uint64_t sessionInd = MakeSessionResInd(msg->ueId, msg->pduSessionId);

    if (!pduSessions.count(sessionInd))
    {
        logger->err("Uplink data failure, PDU session not found. UE: %d PSI: %d", msg->ueId, msg->pduSessionId);
        delete msg;
        return;
    }

    auto &pduSession = pduSessions[sessionInd];

    if (rateLimiter->allowUplinkPacket(sessionInd, static_cast<int64_t>(msg->data.length())))
    {
        gtp::GtpMessage gtp{};
        gtp.payload = std::move(msg->data);
        gtp.msgType = gtp::GtpMessage::MT_G_PDU;
        gtp.teid = pduSession->upTunnel.teid;

        auto ul = std::make_unique<gtp::UlPduSessionInformation>();
        // TODO: currently using first QSI
        ul->qfi = static_cast<int>(pduSession->qosFlows->list.array[0]->qosFlowIdentifier);

        auto cont = new gtp::PduSessionContainerExtHeader();
        cont->pduSessionInformation = std::move(ul);
        gtp.extHeaders.push_back(std::unique_ptr<gtp::GtpExtHeader>(cont));

        OctetString gtpPdu;
        if (!gtp::EncodeGtpMessage(gtp, gtpPdu))
            logger->err("Uplink data failure, GTP encoding failed");
        else
        {
            udpServer->send(InetAddress(pduSession->upTunnel.address, 2152), gtpPdu);
        }
    }

    delete msg;
}

void GtpTask::handleUdpReceive(udp::NwUdpServerReceive *msg)
{
    OctetBuffer buffer{msg->packet};
    auto *gtp = gtp::DecodeGtpMessage(buffer);

    auto sessionInd = sessionTree.findByDownTeid(gtp->teid);
    if (sessionInd == 0)
    {
        logger->err("TEID %d not found on GTP-U Downlink", gtp->teid);
        delete gtp;
        delete msg;
        return;
    }

    if (gtp->msgType != gtp::GtpMessage::MT_G_PDU)
    {
        logger->err("Unhandled GTP-U message type: %d", gtp->msgType);
        delete gtp;
        delete msg;
        return;
    }

    if (rateLimiter->allowDownlinkPacket(sessionInd, gtp->payload.length()))
        base->mrTask->push(new NwDownlinkData(GetUeId(sessionInd), GetPsi(sessionInd), std::move(gtp->payload)));

    delete gtp;
    delete msg;
}

void GtpTask::updateAmbrForUe(int ueId)
{
    if (!ueContexts.count(ueId))
        return;

    auto &ue = ueContexts[ueId];
    rateLimiter->updateUeUplinkLimit(ueId, ue->ueAmbr.ulAmbr);
    rateLimiter->updateUeDownlinkLimit(ueId, ue->ueAmbr.dlAmbr);
}

void GtpTask::updateAmbrForSession(uint64_t pduSession)
{
    if (!pduSessions.count(pduSession))
        return;

    auto &sess = pduSessions[pduSession];
    rateLimiter->updateSessionUplinkLimit(pduSession, sess->sessionAmbr.ulAmbr);
    rateLimiter->updateSessionDownlinkLimit(pduSession, sess->sessionAmbr.dlAmbr);
}

} // namespace nr::gnb
