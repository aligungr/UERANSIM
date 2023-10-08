//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "sm.hpp"
#include <algorithm>
#include <lib/nas/proto_conf.hpp>
#include <lib/nas/utils.hpp>
#include <ue/app/task.hpp>
#include <ue/nas/mm/mm.hpp>

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
    m_logger->debug("Sending PDU Session Establishment Request");

    /* Control the protocol state */
    if (m_mm->m_rmState != ERmState::RM_REGISTERED)
    {
        m_logger->err("PDU session establishment could not be triggered, UE is not registered");
        return;
    }

    if (m_mm->m_mmSubState == EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE && !m_mm->hasEmergency() && !m_mm->isHighPriority())
    {
        m_logger->err("PDU session establishment could not be triggered, non allowed service condition");
        return;
    }

    /* Control the received config */
    if (config.type != nas::EPduSessionType::IPV4)
    {
        m_logger->err("PDU session type [%s] is not supported", nas::utils::EnumToString(config.type));
        return;
    }
    if (m_mm->m_rmState == ERmState::RM_REGISTERED && m_mm->m_registeredForEmergency && !config.isEmergency)
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
    auto &ps = m_pduSessions[psi];
    ps->psState = EPsState::ACTIVE_PENDING;
    ps->sessionType = config.type;
    ps->apn = config.apn;
    ps->sNssai = config.sNssai;
    ps->isEmergency = config.isEmergency;
    ps->authorizedQoSRules = {};
    ps->sessionAmbr = {};
    ps->authorizedQoSFlowDescriptions = {};
    ps->pduAddress = {};
    ps->uplinkPending = false;

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
    auto req = std::make_unique<nas::PduSessionEstablishmentRequest>();
    req->pti = pti;
    req->pduSessionId = psi;
    req->integrityProtectionMaximumDataRate = MakeIntegrityMaxRate(m_base->config->integrityMaxRate);
    req->pduSessionType = nas::IEPduSessionType{};
    req->pduSessionType->pduSessionType = nas::EPduSessionType::IPV4;
    req->sscMode = nas::IESscMode{};
    req->sscMode->sscMode = nas::ESscMode::SSC_MODE_1;
    req->extendedProtocolConfigurationOptions = std::move(iePco);
    req->smCapability = MakeSmCapability();

    /* Set relevant fields of the PT, and start T3580 */
    auto &pt = m_procedureTransactions[pti];
    pt.state = EPtState::PENDING;
    pt.timer = newTransactionTimer(3580);
    pt.message = std::move(req);
    pt.psi = psi;

    /* Send SM message */
    sendSmMessage(psi, *pt.message);
}

void NasSm::receiveEstablishmentAccept(const nas::PduSessionEstablishmentAccept &msg)
{
    m_logger->debug("PDU Session Establishment Accept received");

    if (!checkPtiAndPsi(msg))
        return;

    freeProcedureTransactionId(msg.pti);

    auto &pduSession = m_pduSessions[msg.pduSessionId];
    if (pduSession->psState != EPsState::ACTIVE_PENDING)
    {
        m_logger->err("PS establishment accept received without being requested");
        sendSmCause(nas::ESmCause::MESSAGE_TYPE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE, msg.pti, msg.pduSessionId);
        return;
    }

    if (msg.smCause.has_value())
    {
        m_logger->warn("SM cause received in PduSessionEstablishmentAccept [%s]",
                       nas::utils::EnumToString(msg.smCause->value));
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

    auto statusUpdate = std::make_unique<NmUeStatusUpdate>(NmUeStatusUpdate::SESSION_ESTABLISHMENT);
    statusUpdate->pduSession = pduSession;
    m_base->appTask->push(std::move(statusUpdate));

    m_logger->info("PDU Session establishment is successful PSI[%d]", pduSession->psi);
}

void NasSm::receiveEstablishmentReject(const nas::PduSessionEstablishmentReject &msg)
{
    m_logger->err("PDU Session Establishment Reject received [%s]", nas::utils::EnumToString(msg.smCause.value));

    if (!checkPtiAndPsi(msg))
        return;

    freeProcedureTransactionId(msg.pti);

    auto &pduSession = m_pduSessions[msg.pduSessionId];

    if (pduSession->psState != EPsState::ACTIVE_PENDING)
    {
        m_logger->err("PS establishment reject received without being requested");
        sendSmCause(nas::ESmCause::MESSAGE_TYPE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE, msg.pti, msg.pduSessionId);
        return;
    }

    pduSession->psState = EPsState::INACTIVE;

    if (pduSession->isEmergency)
    {
        // This not much important and no need for now
        // TODO: inform the upper layers of the failure of the procedure
    }
}

} // namespace nr::ue