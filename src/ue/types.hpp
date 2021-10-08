//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "timer.hpp"

#include <array>
#include <atomic>
#include <deque>
#include <memory>
#include <queue>
#include <set>
#include <unordered_set>

#include <lib/app/monitor.hpp>
#include <lib/app/ue_ctl.hpp>
#include <lib/nas/nas.hpp>
#include <utils/common_types.hpp>
#include <utils/json.hpp>
#include <utils/locked.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>

namespace nr::ue
{

class UeAppTask;
class NasTask;
class UeRrcTask;
class UeRlsTask;
class UserEquipment;

struct UeCellDesc
{
    int dbm{};

    struct
    {
        bool hasMib = false;
        bool isBarred = true;
        bool isIntraFreqReselectAllowed = true;
    } mib{};

    struct
    {
        bool hasSib1 = false;
        bool isReserved = false;
        int64_t nci = 0;
        int tac = 0;
        Plmn plmn;
        UacAiBarringSet aiBarringSet;
    } sib1{};
};

struct SupportedAlgs
{
    bool nia1 = true;
    bool nia2 = true;
    bool nia3 = true;
    bool nea1 = true;
    bool nea2 = true;
    bool nea3 = true;
};

enum class OpType
{
    OP,
    OPC
};

struct SessionConfig
{
    nas::EPduSessionType type{};
    std::optional<SingleSlice> sNssai{};
    std::optional<std::string> apn{};
    bool isEmergency{};
};

struct IntegrityMaxDataRateConfig
{
    bool uplinkFull{};
    bool downlinkFull{};
};

struct UeConfig
{
    /* Read from config file */
    std::optional<Supi> supi{};
    Plmn hplmn{};
    OctetString key{};
    OctetString opC{};
    OpType opType{};
    OctetString amf{};
    std::optional<std::string> imei{};
    std::optional<std::string> imeiSv{};
    SupportedAlgs supportedAlgs{};
    std::vector<std::string> gnbSearchList{};
    std::vector<SessionConfig> defaultSessions{};
    IntegrityMaxDataRateConfig integrityMaxRate{};
    NetworkSlice defaultConfiguredNssai{};
    NetworkSlice configuredNssai{};

    struct
    {
        bool mps{};
        bool mcs{};
    } uacAic;

    struct
    {
        int normalCls{}; // [0..9]
        bool cls11{};
        bool cls12{};
        bool cls13{};
        bool cls14{};
        bool cls15{};
    } uacAcc;

    /* Assigned by program */
    bool configureRouting{};
    bool prefixLogger{};

    [[nodiscard]] std::string getNodeName() const
    {
        if (supi.has_value())
            return ToJson(supi).str();
        if (imei.has_value())
            return "imei-" + *imei;
        if (imeiSv.has_value())
            return "imeisv-" + *imeiSv;
        return "unknown-ue";
    }

    [[nodiscard]] std::string getLoggerPrefix() const
    {
        if (!prefixLogger)
            return "";
        if (supi.has_value())
            return supi->value + "|";
        if (imei.has_value())
            return *imei + "|";
        if (imeiSv.has_value())
            return *imeiSv + "|";
        return "unknown-ue|";
    }
};

struct CellSelectionReport
{
    int outOfPlmnCells{};
    int siMissingCells{};
    int reservedCells{};
    int barredCells{};
    int forbiddenTaiCells{};
};

struct ActiveCellInfo
{
    int cellId{};
    ECellCategory category{};
    Plmn plmn{};
    int tac{};

    [[nodiscard]] bool hasValue() const;
};

struct UeSharedContext
{
    Locked<std::unordered_set<Plmn>> availablePlmns;
    Locked<Plmn> selectedPlmn;
    Locked<ActiveCellInfo> currentCell;
    Locked<std::vector<Tai>> forbiddenTaiRoaming;
    Locked<std::vector<Tai>> forbiddenTaiRps;
    Locked<std::optional<GutiMobileIdentity>> providedGuti;
    Locked<std::optional<GutiMobileIdentity>> providedTmsi;

    Plmn getCurrentPlmn();
    Tai getCurrentTai();
    bool hasActiveCell();
};

struct RlsSharedContext
{
    std::atomic<uint64_t> sti{};
};

struct TaskBase
{
    UserEquipment *ue{};
    UeConfig *config{};
    LogBase *logBase{};
    app::IUeController *ueController{};
    app::INodeListener *nodeListener{};
    NtsTask *cliCallbackTask{};

    UeSharedContext shCtx{};

    UeAppTask *appTask{};
    NasTask *nasTask{};
    UeRrcTask *rrcTask{};
    UeRlsTask *rlsTask{};
};

struct RrcTimers
{
    UeTimer t300;

    RrcTimers();
};

struct NasTimers
{
    UeTimer t3346; /* MM - ... */
    UeTimer t3396; /* SM - ... */

    UeTimer t3444; /* MM - ... */
    UeTimer t3445; /* MM - ... */

    UeTimer t3502; /* MM - Initiation of the registration procedure, if still required */
    UeTimer t3510; /* MM - Registration Request transmission timer */
    UeTimer t3511; /* MM - Retransmission of the REGISTRATION REQUEST, if still required */
    UeTimer t3512; /* MM - Periodic registration update timer */
    UeTimer t3516; /* MM - 5G AKA - RAND and RES* storing timer */
    UeTimer t3517; /* MM - Service Request transmission timer */
    UeTimer t3519; /* MM - Transmission with fresh SUCI timer */
    UeTimer t3520; /* MM - ... */
    UeTimer t3521; /* MM - De-registration transmission timer for not switch off */
    UeTimer t3525; /* MM - ... */
    UeTimer t3540; /* MM - ... */

    UeTimer t3584; /* SM - ... */
    UeTimer t3585; /* SM - ... */

    NasTimers();
};

enum class ERmState
{
    RM_DEREGISTERED,
    RM_REGISTERED
};

enum class ECmState
{
    CM_IDLE, // Exact same thing with 5GMM-IDLE in 24.501
    CM_CONNECTED
};

enum class E5UState
{
    U1_UPDATED = 0,
    U2_NOT_UPDATED,
    U3_ROAMING_NOT_ALLOWED
};

enum class ERrcState
{
    RRC_IDLE,
    RRC_CONNECTED,
    RRC_INACTIVE,
};

enum class ERrcLastSetupRequest
{
    SETUP_REQUEST,
    REESTABLISHMENT_REQUEST,
    RESUME_REQUEST,
    RESUME_REQUEST1,
};

enum class EMmState
{
    MM_NULL,
    MM_DEREGISTERED,
    MM_REGISTERED_INITIATED,
    MM_REGISTERED,
    MM_DEREGISTERED_INITIATED,
    MM_SERVICE_REQUEST_INITIATED,
};

enum class EMmSubState
{
    MM_NULL_PS,

    MM_DEREGISTERED_PS,
    MM_DEREGISTERED_NORMAL_SERVICE,
    MM_DEREGISTERED_LIMITED_SERVICE,
    MM_DEREGISTERED_ATTEMPTING_REGISTRATION,
    MM_DEREGISTERED_PLMN_SEARCH,
    MM_DEREGISTERED_NO_SUPI,
    MM_DEREGISTERED_NO_CELL_AVAILABLE,
    MM_DEREGISTERED_ECALL_INACTIVE,
    MM_DEREGISTERED_INITIAL_REGISTRATION_NEEDED,

    MM_REGISTERED_INITIATED_PS,

    MM_REGISTERED_PS,
    MM_REGISTERED_NORMAL_SERVICE,
    MM_REGISTERED_NON_ALLOWED_SERVICE,
    MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE,
    MM_REGISTERED_LIMITED_SERVICE,
    MM_REGISTERED_PLMN_SEARCH,
    MM_REGISTERED_NO_CELL_AVAILABLE,
    MM_REGISTERED_UPDATE_NEEDED,

    MM_DEREGISTERED_INITIATED_PS,

    MM_SERVICE_REQUEST_INITIATED_PS
};

enum class EPsState
{
    INACTIVE,
    ACTIVE_PENDING,
    ACTIVE,
    INACTIVE_PENDING,
    MODIFICATION_PENDING
};

enum class EPtState
{
    INACTIVE,
    PENDING,
};

struct PduSession
{
    static constexpr const int MIN_ID = 1;
    static constexpr const int MAX_ID = 15;

    const int psi;

    EPsState psState{};
    bool uplinkPending{};

    nas::EPduSessionType sessionType{};
    std::optional<std::string> apn{};
    std::optional<SingleSlice> sNssai{};
    bool isEmergency{};

    std::optional<nas::IEQoSRules> authorizedQoSRules{};
    std::optional<nas::IESessionAmbr> sessionAmbr{};
    std::optional<nas::IEQoSFlowDescriptions> authorizedQoSFlowDescriptions{};
    std::optional<nas::IEPduAddress> pduAddress{};

    explicit PduSession(int psi) : psi(psi)
    {
    }
};

struct ProcedureTransaction
{
    static constexpr const int MIN_ID = 1;
    static constexpr const int MAX_ID = 254;

    EPtState state{};
    std::unique_ptr<UeTimer> timer{};
    std::unique_ptr<nas::SmMessage> message{};
    int psi{};
};

enum class EConnectionIdentifier
{
    THREE_3GPP_ACCESS = 0x01,
    NON_THREE_3GPP_ACCESS = 0x02,
};

struct NasCount
{
    octet2 overflow{};
    octet sqn{};

    [[nodiscard]] inline octet4 toOctet4() const
    {
        uint32_t value = 0;
        value |= (uint32_t)overflow;
        value <<= 8;
        value |= (uint32_t)sqn;
        return octet4{value};
    }
};

struct UeKeys
{
    OctetString abba{};

    OctetString kAusf{};
    OctetString kSeaf{};
    OctetString kAmf{};
    OctetString kNasInt{};
    OctetString kNasEnc{};

    [[nodiscard]] UeKeys deepCopy() const
    {
        UeKeys keys;
        keys.kAusf = kAusf.subCopy(0);
        keys.kSeaf = kSeaf.subCopy(0);
        keys.kAmf = kAmf.subCopy(0);
        keys.kNasInt = kNasInt.subCopy(0);
        keys.kNasEnc = kNasEnc.subCopy(0);
        return keys;
    }
};

struct NasSecurityContext
{
    nas::ETypeOfSecurityContext tsc{};
    int ngKsi{}; // 3-bit

    NasCount downlinkCount{};
    NasCount uplinkCount{};

    bool is3gppAccess = true;

    UeKeys keys{};
    nas::ETypeOfIntegrityProtectionAlgorithm integrity{};
    nas::ETypeOfCipheringAlgorithm ciphering{};

    std::deque<int> lastNasSequenceNums{};

    void updateDownlinkCount(const NasCount &validatedCount)
    {
        downlinkCount.overflow = validatedCount.overflow;
        downlinkCount.sqn = validatedCount.sqn;
    }

    [[nodiscard]] NasCount estimatedDownlinkCount(octet sequenceNumber) const
    {
        NasCount count;
        count.sqn = downlinkCount.sqn;
        count.overflow = downlinkCount.overflow;

        if (count.sqn > sequenceNumber)
            count.overflow = octet2(((int)count.overflow + 1) & 0xFFFF);
        count.sqn = sequenceNumber;
        return count;
    }

    void countOnEncrypt()
    {
        uplinkCount.sqn = static_cast<uint8_t>((((int)uplinkCount.sqn + 1) & 0xFF));
        if (uplinkCount.sqn == 0)
            uplinkCount.overflow = octet2(((int)uplinkCount.overflow + 1) & 0xFFFF);
    }

    void rollbackCountOnEncrypt()
    {
        if (uplinkCount.sqn == 0)
        {
            uplinkCount.sqn = 0xFF;

            if ((int)uplinkCount.overflow == 0)
                uplinkCount.overflow = octet2{0xFFFF};
            else
                uplinkCount.overflow = octet2{(int)uplinkCount.overflow - 1};
        }
        else
        {
            uplinkCount.sqn = static_cast<uint8_t>(((int)uplinkCount.sqn - 1) & 0xFF);
        }
    }

    [[nodiscard]] NasSecurityContext deepCopy() const
    {
        NasSecurityContext ctx;
        ctx.tsc = tsc;
        ctx.ngKsi = ngKsi;
        ctx.downlinkCount = downlinkCount;
        ctx.uplinkCount = uplinkCount;
        ctx.is3gppAccess = is3gppAccess;
        ctx.keys = keys.deepCopy();
        ctx.integrity = integrity;
        ctx.ciphering = ciphering;
        ctx.lastNasSequenceNums = lastNasSequenceNums;
        return ctx;
    }
};

enum class EAutnValidationRes
{
    OK,
    MAC_FAILURE,
    AMF_SEPARATION_BIT_FAILURE,
    SYNCHRONISATION_FAILURE,
};

enum class ERegUpdateCause
{
    // when the UE detects entering a tracking area that is not in the list of tracking areas that the UE previously
    // registered in the AMF
    ENTER_UNLISTED_TRACKING_AREA,
    // when the periodic registration updating timer T3512 expires
    T3512_EXPIRY,
    // when the UE receives a CONFIGURATION UPDATE COMMAND message indicating "registration requested" in the
    // Configuration update indication IE as specified in subclauses 5.4.4.3;
    CONFIGURATION_UPDATE,
    // when the UE in state 5GMM-REGISTERED.ATTEMPTING-REGISTRATION-UPDATE either receives a paging or the UE receives a
    // NOTIFICATION message with access type indicating 3GPP access over the non-3GPP access for PDU sessions associated
    // with 3GPP access
    PAGING_OR_NOTIFICATION,
    // upon inter-system change from S1 mode to N1 mode
    INTER_SYSTEM_CHANGE_S1_TO_N1,
    // when the UE receives an indication of "RRC Connection failure" from the lower layers and does not have signalling
    // pending (i.e. when the lower layer requests NAS signalling connection recovery) except for the case specified in
    // subclause 5.3.1.4;
    CONNECTION_RECOVERY,
    // when the UE receives a fallback indication from the lower layers and does not have signalling pending (i.e. when
    // the lower layer requests NAS signalling connection recovery, see subclauses 5.3.1.4 and 5.3.1.2);
    FALLBACK_INDICATION,
    // when the UE changes the 5GMM capability or the S1 UE network capability or both
    MM_OR_S1_CAPABILITY_CHANGE,
    // when the UE's usage setting changes
    USAGE_SETTING_CHANGE,
    // when the UE needs to change the slice(s) it is currently registered to
    SLICE_CHANGE,
    // when the UE changes the UE specific DRX parameters
    DRX_CHANGE,
    // when the UE in state 5GMM-REGISTERED.ATTEMPTING-REGISTRATION-UPDATE receives a request from the upper layers to
    // establish an emergency PDU session or perform emergency services fallback
    EMERGENCY_CASE,
    // when the UE needs to register for SMS over NAS, indicate a change in the requirements to use SMS over NAS, or
    // de-register from SMS over NAS;
    SMS_OVER_NAS_CHANGE,
    // when the UE needs to indicate PDU session status to the network after performing a local release of PDU
    // session(s) as specified in subclauses 6.4.1.5 and 6.4.3.5;
    PS_STATUS_INFORM,
    // when the UE in 5GMM-IDLE mode changes the radio capability for NG-RAN
    RADIO_CAP_CHANGE,
    // when the UE needs to request new LADN information
    NEW_LADN_NEEDED,
    // when the UE needs to request the use of MICO mode or needs to stop the use of MICO mode
    MICO_MODE_CHANGE,
    // when the UE in 5GMM-CONNECTED mode with RRC inactive indication enters a cell in the current registration area
    // belonging to an equivalent PLMN of the registered PLMN and not belonging to the registered PLMN;
    ENTER_EQUIVALENT_PLMN_CELL,
    // when the UE receives a SERVICE REJECT message with the 5GMM cause value set to #28 "Restricted service area".
    RESTRICTED_SERVICE_AREA,
    // ------ following are not specified by 24.501 ------
    TAI_CHANGE_IN_ATT_UPD,
    PLMN_CHANGE_IN_ATT_UPD,
    T3346_EXPIRY_IN_ATT_UPD,
    T3502_EXPIRY_IN_ATT_UPD,
    T3511_EXPIRY_IN_ATT_UPD,
};

enum class EServiceReqCause
{
    // a) the UE, in 5GMM-IDLE mode over 3GPP access, receives a paging request from the network
    IDLE_PAGING,
    // b) the UE, in 5GMM-CONNECTED mode over 3GPP access, receives a notification from the network with access type
    // indicating non-3GPP access
    CONNECTED_3GPP_NOTIFICATION_N3GPP,
    // c) the UE, in 5GMM-IDLE mode over 3GPP access, has uplink signalling pending
    IDLE_UPLINK_SIGNAL_PENDING,
    // d) the UE, in 5GMM-IDLE mode over 3GPP access, has uplink user data pending
    IDLE_UPLINK_DATA_PENDING,
    // e) the UE, in 5GMM-CONNECTED mode or in 5GMM-CONNECTED mode with RRC inactive indication, has user data pending
    // due to no user-plane resources established for PDU session(s) used for user data transport
    CONNECTED_UPLINK_DATA_PENDING,
    // f) the UE in 5GMM-IDLE mode over non-3GPP access, receives an indication from the lower layers of non-3GPP
    // access, that the access stratum connection is established between UE and network
    NON_3GPP_AS_ESTABLISHED,
    // g) the UE, in 5GMM-IDLE mode over 3GPP access, receives a notification from the network with access type
    // indicating 3GPP access when the UE is in 5GMM-CONNECTED mode over non-3GPP access
    IDLE_3GPP_NOTIFICATION_N3GPP,
    // h) the UE, in 5GMM-IDLE, 5GMM-CONNECTED mode over 3GPP access, or 5GMM-CONNECTED mode with RRC inactive
    // indication, receives a request for emergency services fallback from the upper layer and performs emergency
    // services fallback as specified in subclause 4.13.4.2 of 3GPP TS 23.502 [9]
    EMERGENCY_FALLBACK,
    // i) the UE, in 5GMM-CONNECTED mode over 3GPP access or in 5GMM-CONNECTED mode with RRC inactive indication,
    // receives a fallback indication from the lower layers (see subclauses 5.3.1.2 and 5.3.1.4) and or the UE has a
    // pending NAS procedure other than a registration, service request, or de-registration procedure
    FALLBACK_INDICATION
};

enum class EProcRc
{
    OK,
    CANCEL,
    STAY,
};

struct ProcControl
{
    std::optional<EInitialRegCause> initialRegistration{};
    std::optional<ERegUpdateCause> mobilityRegistration{};
    std::optional<EServiceReqCause> serviceRequest{};
    std::optional<EDeregCause> deregistration{};
};

struct UacInput
{
    std::bitset<16> identities;
    int category{};
    int establishmentCause{};
};

enum class EUacResult
{
    ALLOWED,
    BARRED,
    BARRING_APPLICABLE_EXCEPT_0_2,
};

struct UacOutput
{
    EUacResult res{};
};

enum class ENasTransportHint
{
    PDU_SESSION_ESTABLISHMENT_REQUEST,
    PDU_SESSION_ESTABLISHMENT_ACCEPT,
    PDU_SESSION_ESTABLISHMENT_REJECT,

    PDU_SESSION_AUTHENTICATION_COMMAND,
    PDU_SESSION_AUTHENTICATION_COMPLETE,
    PDU_SESSION_AUTHENTICATION_RESULT,

    PDU_SESSION_MODIFICATION_REQUEST,
    PDU_SESSION_MODIFICATION_REJECT,
    PDU_SESSION_MODIFICATION_COMMAND,
    PDU_SESSION_MODIFICATION_COMPLETE,
    PDU_SESSION_MODIFICATION_COMMAND_REJECT,

    PDU_SESSION_RELEASE_REQUEST,
    PDU_SESSION_RELEASE_REJECT,
    PDU_SESSION_RELEASE_COMMAND,
    PDU_SESSION_RELEASE_COMPLETE,

    FIVEG_SM_STATUS
};

Json ToJson(const ECmState &state);
Json ToJson(const ERmState &state);
Json ToJson(const EMmState &state);
Json ToJson(const EMmSubState &state);
Json ToJson(const E5UState &state);
Json ToJson(const NasTimers &v);
Json ToJson(const ERegUpdateCause &v);
Json ToJson(const EPsState &v);
Json ToJson(const EServiceReqCause &v);
Json ToJson(const ERrcState &v);

} // namespace nr::ue
