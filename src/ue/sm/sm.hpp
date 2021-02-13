//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <nas/nas.hpp>
#include <nas/timer.hpp>
#include <ue/nts.hpp>
#include <ue/types.hpp>
#include <utils/nts.hpp>

namespace nr::ue
{

class NasMm;

class NasSm
{
  private:
    TaskBase *m_base;
    NtsTask *m_nas;
    UeTimers *m_timers;
    std::unique_ptr<Logger> m_logger;
    NasMm *m_mm;

    PduSession m_pduSessions[16]{};
    ProcedureTransaction m_procedureTransactions[255]{};

    friend class UeCmdHandler;

  public:
    NasSm(TaskBase *base, NtsTask *nas, UeTimers *timers);

  public:
    /* Base */
    void onStart(NasMm *mm);
    void onQuit();
    void establishInitialSessions();

    /* Transport */
    void receiveSmMessage(const nas::SmMessage &msg);

  private:
    /* Transport */
    void sendSmMessage(int psi, const nas::SmMessage &msg);
    void receiveSmStatus(const nas::FiveGSmStatus &msg);
    void receiveSmCause(const nas::IE5gSmCause &msg);

    /* Resource */
    int allocatePduSessionId(const SessionConfig &config);
    int allocateProcedureTransactionId();
    void releaseProcedureTransactionId(int pti);
    void releasePduSession(int psi);

    /* Session */
    void sendEstablishmentRequest(const SessionConfig &config);
    void receivePduSessionEstablishmentAccept(const nas::PduSessionEstablishmentAccept &msg);
    void receivePduSessionEstablishmentReject(const nas::PduSessionEstablishmentReject &msg);
};

} // namespace nr::ue