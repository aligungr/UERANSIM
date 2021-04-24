//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <lib/nas/utils.hpp>
#include <set>
#include <ue/app/task.hpp>
#include <ue/nas/mm/mm.hpp>

namespace nr::ue
{

bool NasSm::checkPtiAndPsi(const nas::SmMessage &msg)
{
    if (msg.pti < ProcedureTransaction::MIN_ID || msg.pti > ProcedureTransaction::MAX_ID)
    {
        m_logger->err("Received PTI [%d] value is invalid", msg.pti);
        sendSmCause(nas::ESmCause::INVALID_PTI_VALUE, msg.pti, msg.pduSessionId);
        return false;
    }

    if (m_procedureTransactions[msg.pti].psi != msg.pduSessionId)
    {
        m_logger->err("Received PSI value [%d] is invalid, expected was [%d]", msg.pduSessionId,
                      m_procedureTransactions[msg.pti].psi);
        sendSmCause(nas::ESmCause::INVALID_PTI_VALUE, msg.pti, msg.pduSessionId);
        return false;
    }

    return true;
}

void NasSm::abortProcedureByPti(int pti)
{
    auto &pt = m_procedureTransactions[pti];
    if (pt.state != EPtState::PENDING)
        return;

    if (pt.message == nullptr)
    {
        m_logger->err("Procedure abortion failure, stored SM message is null");
        return;
    }

    auto msgType = pt.message->messageType;

    int psi = m_procedureTransactions[pti].psi;

    m_logger->debug("Aborting SM procedure for PTI[%d], PSI[%d]", pti, psi);

    if (msgType == nas::EMessageType::PDU_SESSION_ESTABLISHMENT_REQUEST)
    {
        freeProcedureTransactionId(pti);
        freePduSessionId(psi);
    }
    else if (msgType == nas::EMessageType::PDU_SESSION_RELEASE_REQUEST)
    {
        freeProcedureTransactionId(pti);
        localReleaseSession(psi);
    }

    // todo: others
}

void NasSm::abortProcedureByPtiOrPsi(int pti, int psi)
{
    std::set<int> ptiToAbort{};
    int i = 0;
    for (auto &pt : m_procedureTransactions)
    {
        if (pt.state == EPtState::PENDING && pt.psi == psi)
            ptiToAbort.insert(i);
        i++;
    }

    ptiToAbort.insert(pti);

    for (int id : ptiToAbort)
        abortProcedureByPti(id);
}

} // namespace nr::ue