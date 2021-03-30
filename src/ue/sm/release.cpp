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

void NasSm::sendReleaseRequest(int psi)
{
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

} // namespace nr::ue