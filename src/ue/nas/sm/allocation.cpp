//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "sm.hpp"
#include <lib/nas/utils.hpp>

namespace nr::ue
{

int NasSm::allocatePduSessionId(const SessionConfig &config)
{
    if (config.type != nas::EPduSessionType::IPV4)
    {
        m_logger->debug("PDU session type [%s] is not supported", nas::utils::EnumToString(config.type));
        return 0;
    }

    auto &arr = m_pduSessions;

    int id = -1;
    for (int i = PduSession::MIN_ID; i <= PduSession::MAX_ID; i++)
    {
        if (arr[i]->psState == EPsState::INACTIVE)
        {
            id = i;
            break;
        }
    }

    if (id == -1)
    {
        m_logger->err("PDU session allocation failed");
        return 0;
    }

    return id;
}

int NasSm::allocateProcedureTransactionId()
{
    auto &arr = m_procedureTransactions;

    int id = -1;
    for (int i = ProcedureTransaction::MIN_ID; i <= ProcedureTransaction::MAX_ID; i++)
    {
        if (arr[i].state == EPtState::INACTIVE)
        {
            id = i;
            break;
        }
    }

    if (id == -1)
    {
        m_logger->err("PTI allocation failed");
        return 0;
    }

    arr[id] = {};

    return id;
}

void NasSm::freeProcedureTransactionId(int pti)
{
    m_procedureTransactions[pti] = {};
}

void NasSm::freePduSessionId(int psi)
{
    m_pduSessions[psi]->psState = EPsState::INACTIVE;
}

} // namespace nr::ue