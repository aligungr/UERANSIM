//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <array>
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
    UeTimers *m_timers;
    std::unique_ptr<Logger> m_logger;
    NasMm *m_mm;

    std::array<PduSession *, 16> m_pduSessions{};
    std::array<ProcedureTransaction, 255> m_procedureTransactions{};

    friend class UeCmdHandler;

  public:
    NasSm(TaskBase *base, UeTimers *timers);

  public:
    /* Base */
    void onStart(NasMm *mm);
    void onQuit();
    void establishInitialSessions();

    /* Transport */
    void receiveSmMessage(const nas::SmMessage &msg);

    /* Resource */
    void localReleaseSession(int psi);
    void localReleaseAllSessions();
    bool anyEmergencySession();

  private:
    /* Transport */
    void sendSmMessage(int psi, const nas::SmMessage &msg);
    void receiveSmStatus(const nas::FiveGSmStatus &msg);
    void receiveSmCause(const nas::IE5gSmCause &msg);
    void sendSmCause(const nas::ESmCause &cause, int psi);

    /* Allocation */
    int allocatePduSessionId(const SessionConfig &config);
    int allocateProcedureTransactionId();
    void freeProcedureTransactionId(int pti);
    void freePduSessionId(int psi);

    /* Session Establishment */
    void sendEstablishmentRequest(const SessionConfig &config);
    void receiveEstablishmentAccept(const nas::PduSessionEstablishmentAccept &msg);
    void receiveEstablishmentReject(const nas::PduSessionEstablishmentReject &msg);
    void abortEstablishmentRequest(int pti);

    /* Timer */
    std::unique_ptr<nas::NasTimer> newTransactionTimer(int code);
    void onTimerExpire(nas::NasTimer &timer);
    void onTransactionTimerExpire(int pti);

    /* Utils */
    bool checkPtiAndPsi(const nas::SmMessage& msg);

  public:
    /* Interface */
    void handleNasEvent(const NwUeNasToNas &msg); // used by NAS
    void onTimerTick();                           // used by NAS
};

} // namespace nr::ue