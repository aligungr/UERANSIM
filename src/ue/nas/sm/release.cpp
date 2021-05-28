//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <lib/nas/proto_conf.hpp>
#include <lib/nas/utils.hpp>
#include <optional>
#include <ue/app/task.hpp>
#include <ue/nas/mm/mm.hpp>

namespace nr::ue
{

void NasSm::sendReleaseRequest(int psi)
{
    /* Control the protocol state */
    if (m_mm->m_mmSubState == EMmSubState::MM_REGISTERED_NON_ALLOWED_SERVICE && !m_mm->hasEmergency() && !m_mm->isHighPriority())
    {
        m_logger->err("PDU session release could not start, non allowed service condition");
        return;
    }

    /* Control the PDU session state */
    auto &ps = m_pduSessions[psi];
    if (ps->psState != EPsState::ACTIVE)
    {
        m_logger->warn("PDU session release procedure could not start: PS[%d] is not active already", psi);
        return;
    }

    m_logger->debug("Sending PDU Session Release Request for PSI[%d]", psi);

    /* Allocate PTI */
    int pti = allocateProcedureTransactionId();
    if (pti == 0)
        return;

    /* Construct the message */
    auto req = std::make_unique<nas::PduSessionReleaseRequest>();
    req->pti = pti;
    req->pduSessionId = psi;
    req->smCause = nas::IE5gSmCause{};
    req->smCause->value = nas::ESmCause::REGULAR_DEACTIVATION;

    /* Set relevant fields of the PT, and start T3582 */
    auto &pt = m_procedureTransactions[pti];
    pt.state = EPtState::PENDING;
    pt.timer = newTransactionTimer(3582);
    pt.message = std::move(req);
    pt.psi = psi;

    /* Send SM message */
    sendSmMessage(psi, *pt.message);
}

void NasSm::sendReleaseRequestForAll()
{
    for (auto &ps : m_pduSessions)
        if (ps->psState == EPsState::ACTIVE)
            sendReleaseRequest(ps->psi);
}

void NasSm::receiveReleaseReject(const nas::PduSessionReleaseReject &msg)
{
    auto cause = msg.smCause.value;

    m_logger->err("PDU Session Release Reject received [%s]", nas::utils::EnumToString(cause));

    if (!checkPtiAndPsi(msg))
        return;

    freeProcedureTransactionId(msg.pti);

    if (cause != nas::ESmCause::PTI_ALREADY_IN_USE && cause != nas::ESmCause::INVALID_PDU_SESSION_IDENTITY)
    {
        auto &pduSession = m_pduSessions[msg.pduSessionId];

        if (pduSession->psState != EPsState::INACTIVE_PENDING)
        {
            m_logger->err("PS release reject received without being requested");
            sendSmCause(nas::ESmCause::MESSAGE_TYPE_NOT_COMPATIBLE_WITH_THE_PROTOCOL_STATE, msg.pti, msg.pduSessionId);
            return;
        }

        pduSession->psState = EPsState::ACTIVE;
    }
    else
    {
        localReleaseSession(msg.pduSessionId);
    }
}

void NasSm::receiveReleaseCommand(const nas::PduSessionReleaseCommand &msg)
{
    m_logger->debug("PDU Session Release Command received");

    int psi = msg.pduSessionId;
    int pti = msg.pti;

    /* Abnormal case handling 6.3.3.6/a */
    if (m_pduSessions[msg.pduSessionId]->psState == EPsState::INACTIVE)
    {
        m_logger->err("PS[%d] is already in inactive state, ignoring release command", msg.pduSessionId);
        sendSmCause(nas::ESmCause::INVALID_PDU_SESSION_IDENTITY, msg.pti, msg.pduSessionId);
        return;
    }

    /* Abnormal case handling 6.4.1.6/c */
    if (m_pduSessions[psi]->psState == EPsState::ACTIVE_PENDING)
    {
        m_logger->warn("PDU Session Release Command ignored for PSI[%d] due to collision with establishment procedure.",
                       psi);
        return;
    }

    /* Handle reactivation case (take the fields before releasing the PS) */
    std::optional<SessionConfig> reactivation{};
    if (msg.smCause.value == nas::ESmCause::REACTIVATION_REQUESTED)
    {
        reactivation = SessionConfig{};
        reactivation->type = m_pduSessions[psi]->sessionType;
        reactivation->apn = m_pduSessions[psi]->apn;
        reactivation->sNssai = m_pduSessions[psi]->sNssai;
    }

    /* Release PS and handle PT */
    localReleaseSession(psi);
    if (pti != 0)
    {
        auto &pt = m_procedureTransactions[pti];

        if (pt.message == nullptr || pt.message->messageType != nas::EMessageType::PDU_SESSION_RELEASE_REQUEST)
        {
            m_logger->err("PTI mismatch occurred, received PTI[%d] has no PDU session release request", pti);
            sendSmCause(nas::ESmCause::PTI_MISMATCH, msg.pti, msg.pduSessionId);
            return;
        }

        // Warning: PS has been released, and the spec says that (6.3.3.3) this PTI should not be released immediately
        // Therefore the PT does not hold a valid PSI after this point
        pt.psi = 0;

        // TODO: Let's immediately release the PT for now. See 6.3.3.3
        freeProcedureTransactionId(pti);
    }

    /* Construct Release Complete message */
    nas::PduSessionReleaseComplete resp;
    resp.pduSessionId = psi;
    resp.pti = pti;

    /* Send SM message */
    sendSmMessage(psi, resp);

    /* Handle reactivation */
    if (reactivation.has_value())
    {
        m_logger->debug("Re-initiating a PDU session establishment procedure due to reactivation request");
        sendEstablishmentRequest(*reactivation);
    }
}

} // namespace nr::ue
