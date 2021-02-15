//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <nas/utils.hpp>
#include <nas/proto_conf.hpp>
#include <ue/app/task.hpp>

namespace nr::ue
{

void NasSm::sendEstablishmentRequest(const SessionConfig &config)
{
    m_logger->debug("Sending PDU session establishment request");

    int psi = allocatePduSessionId(config);
    if (psi == 0)
    {
        m_logger->err("PDU Session Establishment Request could not send");
        return;
    }

    int pti = allocateProcedureTransactionId();
    if (pti == 0)
    {
        m_logger->err("PDU Session Establishment Request could not send");
        releasePduSession(psi);
        return;
    }

    // TODO
    nas::ProtocolConfigurationOptions opt;
    opt.additionalParams.push_back(std::make_unique<nas::ProtocolConfigurationItem>(
        nas::EProtocolConfigId::CONT_ID_UP_IP_ADDRESS_ALLOCATION_VIA_NAS_SIGNALLING, true, OctetString::Empty()));
    opt.additionalParams.push_back(std::make_unique<nas::ProtocolConfigurationItem>(
        nas::EProtocolConfigId::CONT_ID_DOWN_DNS_SERVER_IPV4_ADDRESS, true, OctetString::Empty()));

    // TODO
    nas::PduSessionEstablishmentRequest req;
    req.pti = pti;
    req.pduSessionId = static_cast<nas::EPduSessionIdentity>(psi);
    req.integrityProtectionMaximumDataRate.maxRateUplink =
        nas::EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink::FULL_DATA_RATE;
    req.integrityProtectionMaximumDataRate.maxRateDownlink =
        nas::EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink::FULL_DATA_RATE;
    req.pduSessionType = nas::IEPduSessionType{};
    req.pduSessionType->pduSessionType = nas::EPduSessionType::IPV4;
    req.sscMode = nas::IESscMode{};
    req.sscMode->sscMode = nas::ESscMode::SSC_MODE_1;
    req.extendedProtocolConfigurationOptions = nas::IEExtendedProtocolConfigurationOptions{};
    req.extendedProtocolConfigurationOptions->configurationProtocol = nas::EConfigurationProtocol::PPP;
    req.extendedProtocolConfigurationOptions->extension = true;
    req.extendedProtocolConfigurationOptions->options = opt.encode();

    m_timers->t3580.start();
    sendSmMessage(pti, req);
}

void NasSm::receivePduSessionEstablishmentAccept(const nas::PduSessionEstablishmentAccept &msg)
{
    if (msg.smCause.has_value())
    {
        m_logger->warn("SM cause received in PduSessionEstablishmentAccept: %s",
                       nas::utils::EnumToString(msg.smCause->value));
    }

    m_timers->t3580.stop();

    releaseProcedureTransactionId(msg.pti);

    auto &pduSession = m_pduSessions[static_cast<int>(msg.pduSessionId)];
    if (pduSession.id == 0)
        m_logger->err("PDU session not found: %d", msg.pduSessionId);

    pduSession.isEstablished = true;
    pduSession.authorizedQoSRules = nas::utils::DeepCopyIe(msg.authorizedQoSRules);
    pduSession.sessionAmbr = nas::utils::DeepCopyIe(msg.sessionAmbr);
    pduSession.sessionType = msg.selectedPduSessionType.pduSessionType;

    if (msg.authorizedQoSFlowDescriptions.has_value())
        pduSession.authorizedQoSFlowDescriptions = nas::utils::DeepCopyIe(*msg.authorizedQoSFlowDescriptions);
    else
        pduSession.authorizedQoSFlowDescriptions = {};

    if (msg.pduAddress.has_value())
        pduSession.pduAddress = nas::utils::DeepCopyIe(*msg.pduAddress);
    else
        pduSession.pduAddress = {};

    auto *statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::SESSION_ESTABLISHMENT);
    statusUpdate->pduSession = &pduSession;
    m_base->appTask->push(statusUpdate);

    m_logger->info("PDU Session establishment is successful PSI[%d]", pduSession.id);
}

void NasSm::receivePduSessionEstablishmentReject(const nas::PduSessionEstablishmentReject &msg)
{
    m_logger->err("PDU Session Establishment Reject received [%s]", nas::utils::EnumToString(msg.smCause.value));
    // TODO
}

} // namespace nr::ue