//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <array>
#include <bitset>
#include <lib/nas/nas.hpp>
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
    NasTimers *m_timers;
    std::unique_ptr<Logger> m_logger;
    NasMm *m_mm;

    std::array<PduSession *, 16> m_pduSessions{};
    std::array<ProcedureTransaction, 255> m_procedureTransactions{};

    friend class UeCmdHandler;
    friend class NasMm;
    friend class NasTask;

  public:
    NasSm(TaskBase *base, NasTimers *timers);

  public: /* Base */
    void onStart(NasMm *mm);
    void onQuit();

  private: /* Resource */
    void localReleaseSession(int psi);
    void localReleaseAllSessions();
    bool anyEmergencySession();
    void handleUplinkStatusChange(int psi, bool isPending);
    bool anyUplinkDataPending();
    bool anyEmergencyUplinkDataPending();
    std::bitset<16> getUplinkDataStatus();
    std::bitset<16> getPduSessionStatus();
    void establishRequiredSessions();
    bool anySessionMatches(const SessionConfig &config);

  private: /* Transport */
    void receiveSmMessage(const nas::SmMessage &msg);
    void sendSmMessage(int psi, const nas::SmMessage &msg);
    void receiveSmStatus(const nas::FiveGSmStatus &msg);
    void sendSmCause(const nas::ESmCause &cause, int pti, int psi);
    void receiveForwardingFailure(const nas::SmMessage &msg, nas::EMmCause cause,
                                  const std::optional<nas::IEGprsTimer3> &backoffTimer);

  private: /* Allocation */
    int allocatePduSessionId(const SessionConfig &config);
    int allocateProcedureTransactionId();
    void freeProcedureTransactionId(int pti);
    void freePduSessionId(int psi);

  private: /* Session Establishment */
    void sendEstablishmentRequest(const SessionConfig &config);
    void receiveEstablishmentAccept(const nas::PduSessionEstablishmentAccept &msg);
    void receiveEstablishmentReject(const nas::PduSessionEstablishmentReject &msg);

  private: /* Session Release */
    void sendReleaseRequest(int psi);
    void sendReleaseRequestForAll();
    void receiveReleaseReject(const nas::PduSessionReleaseReject &msg);
    void receiveReleaseCommand(const nas::PduSessionReleaseCommand &msg);

  private: /* Timer */
    std::unique_ptr<UeTimer> newTransactionTimer(int code);
    void onTimerExpire(UeTimer &timer);
    void onTransactionTimerExpire(int pti);

  private: /* Procedure */
    bool checkPtiAndPsi(const nas::SmMessage &msg);
    void abortProcedureByPti(int pti);
    void abortProcedureByPtiOrPsi(int pti, int psi);

  private: /* Service Access Point */
    void handleNasEvent(const NmUeNasToNas &msg);
    void onTimerTick();
    void handleUplinkDataRequest(int psi, OctetString &&data);
    void handleDownlinkDataRequest(int psi, OctetString &&data);
};

} // namespace nr::ue