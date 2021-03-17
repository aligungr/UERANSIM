//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <algorithm>
#include <nas/proto_conf.hpp>
#include <nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/mm/mm.hpp>

namespace nr::ue
{

static nas::IE5gSmCapability MakeSmCapability()
{
    nas::IE5gSmCapability cap{};
    cap.rqos = nas::EReflectiveQoS::NOT_SUPPORTED;
    cap.mh6pdu = nas::EMultiHomedIPv6PduSession::NOT_SUPPORTED;
    return cap;
}

static nas::IEIntegrityProtectionMaximumDataRate MakeIntegrityMaxRate(const IntegrityMaxDataRateConfig &config)
{
    nas::IEIntegrityProtectionMaximumDataRate res{};
    res.maxRateDownlink = nas::EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink::SIXTY_FOUR_KBPS;
    res.maxRateUplink = nas::EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink::SIXTY_FOUR_KBPS;
    if (config.downlinkFull)
        res.maxRateDownlink = nas::EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForDownlink::FULL_DATA_RATE;
    if (config.uplinkFull)
        res.maxRateUplink = nas::EMaximumDataRatePerUeForUserPlaneIntegrityProtectionForUplink::FULL_DATA_RATE;
    return res;
}

void NasSm::sendEstablishmentRequest(const SessionConfig &config)
{
    m_logger->debug("Sending PDU session establishment request");

    /* Control the protocol state */
    if (!m_mm->isRegistered())
    {
        m_logger->err("UE is not registered");
        return;
    }

    /* Control the received config */
    if (config.type != nas::EPduSessionType::IPV4)
    {
        m_logger->err("PDU session type [%s] is not supported", nas::utils::EnumToString(config.type));
        return;
    }
    if (m_mm->isRegisteredForEmergency() && !config.isEmergency)
    {
        m_logger->err("Non-emergency PDU session cannot be requested, UE is registered for emergency only");
        return;
    }
    if (config.isEmergency && anyEmergencySession())
    {
        m_logger->err(
            "Emergency PDU session cannot be requested, another emergency session already established or establishing");
        return;
    }

    /* Allocate PSI */
    int psi = allocatePduSessionId(config);
    if (psi == 0)
        return;

    /* Allocate PTI */
    int pti = allocateProcedureTransactionId();
    if (pti == 0)
    {
        freePduSessionId(psi);
        return;
    }

    /* Set relevant fields of the PS description */
    auto ps = m_pduSessions[psi];
    ps->psState = EPsState::ACTIVE_PENDING;
    ps->sessionType = config.type;
    ps->apn = config.apn;
    ps->sNssai = config.sNssai;
    ps->isEmergency = config.isEmergency;
    ps->authorizedQoSRules = {};
    ps->sessionAmbr = {};
    ps->authorizedQoSFlowDescriptions = {};
    ps->pduAddress = {};

    /* Make PCO */
    nas::ProtocolConfigurationOptions opt{};
    opt.additionalParams.push_back(std::make_unique<nas::ProtocolConfigurationItem>(
        nas::EProtocolConfigId::CONT_ID_UP_IP_ADDRESS_ALLOCATION_VIA_NAS_SIGNALLING, true, OctetString::Empty()));
    opt.additionalParams.push_back(std::make_unique<nas::ProtocolConfigurationItem>(
        nas::EProtocolConfigId::CONT_ID_DOWN_DNS_SERVER_IPV4_ADDRESS, true, OctetString::Empty()));

    nas::IEExtendedProtocolConfigurationOptions iePco{};
    iePco.configurationProtocol = nas::EConfigurationProtocol::PPP;
    iePco.extension = true;
    iePco.options = opt.encode();

    /* Prepare the establishment request message */
    nas::PduSessionEstablishmentRequest req{};
    req.pti = pti;
    req.pduSessionId = static_cast<nas::EPduSessionIdentity>(psi);
    req.integrityProtectionMaximumDataRate = MakeIntegrityMaxRate(m_base->config->integrityMaxRate);
    req.pduSessionType = nas::IEPduSessionType{};
    req.pduSessionType->pduSessionType = nas::EPduSessionType::IPV4;
    req.sscMode = nas::IESscMode{};
    req.sscMode->sscMode = nas::ESscMode::SSC_MODE_1;
    req.extendedProtocolConfigurationOptions = std::move(iePco);
    req.smCapability = MakeSmCapability();

    /* Start T3580 */
    m_timers->t3580.start();

    /* Send SM message */
    sendSmMessage(psi, req);
}

void NasSm::receivePduSessionEstablishmentAccept(const nas::PduSessionEstablishmentAccept &msg)
{
    if (msg.smCause.has_value())
    {
        m_logger->warn("SM cause received in PduSessionEstablishmentAccept [%s]",
                       nas::utils::EnumToString(msg.smCause->value));
    }

    m_timers->t3580.stop();

    freeProcedureTransactionId(msg.pti);

    auto pduSession = m_pduSessions[static_cast<int>(msg.pduSessionId)];
    if (pduSession->psState != EPsState::ACTIVE_PENDING)
    {
        m_logger->err("PS establishment accept received without requested");
        sendSmCause(nas::ESmCause::MESSAGE_TYPE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE, pduSession->psi);
        return;
    }

    pduSession->psState = EPsState::ACTIVE;
    pduSession->authorizedQoSRules = nas::utils::DeepCopyIe(msg.authorizedQoSRules);
    pduSession->sessionAmbr = nas::utils::DeepCopyIe(msg.sessionAmbr);
    pduSession->sessionType = msg.selectedPduSessionType.pduSessionType;

    if (msg.authorizedQoSFlowDescriptions.has_value())
        pduSession->authorizedQoSFlowDescriptions = nas::utils::DeepCopyIe(*msg.authorizedQoSFlowDescriptions);
    else
        pduSession->authorizedQoSFlowDescriptions = {};

    if (msg.pduAddress.has_value())
        pduSession->pduAddress = nas::utils::DeepCopyIe(*msg.pduAddress);
    else
        pduSession->pduAddress = {};

    auto *statusUpdate = new NwUeStatusUpdate(NwUeStatusUpdate::SESSION_ESTABLISHMENT);
    statusUpdate->pduSession = pduSession;
    m_base->appTask->push(statusUpdate);

    m_logger->info("PDU Session establishment is successful PSI[%d]", pduSession->psi);
}

void NasSm::receivePduSessionEstablishmentReject(const nas::PduSessionEstablishmentReject &msg)
{
    m_logger->err("PDU Session Establishment Reject received [%s]", nas::utils::EnumToString(msg.smCause.value));
    // TODO
}

} // namespace nr::ue