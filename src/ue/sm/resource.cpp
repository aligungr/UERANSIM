//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "sm.hpp"
#include <nas/utils.hpp>

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
        if (arr[i].id == 0)
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

    arr[id] = {};
    arr[id].id = id;
    arr[id].isEstablished = false;
    arr[id].apn = config.apn;
    arr[id].sessionType = config.type;
    arr[id].sNssai = config.sNssai;

    return id;
}

int NasSm::allocateProcedureTransactionId()
{
    auto &arr = m_procedureTransactions;

    int id = -1;
    for (int i = ProcedureTransaction::MIN_ID; i <= ProcedureTransaction::MAX_ID; i++)
    {
        if (arr[i].id == 0)
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
    arr[id].id = id;

    return id;
}

void NasSm::releaseProcedureTransactionId(int pti)
{
    m_procedureTransactions[pti].id = 0;
}

void NasSm::releasePduSession(int psi)
{
    m_pduSessions[psi].id = 0;
    m_logger->info("PDU session[%d] released", psi);
}

} // namespace nr::ue