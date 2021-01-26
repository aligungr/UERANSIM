//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <app_monitor.hpp>
#include <common_types.hpp>
#include <logger.hpp>
#include <nas.hpp>
#include <nas_timer.hpp>
#include <nts.hpp>
#include <octet_string.hpp>

namespace nas
{

} // namespace nas

namespace nr::ue
{

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
    std::optional<SliceSupport> sNssai{};
    std::optional<std::string> apn{};
};

struct UeConfig
{
    bool emulationMode;
    OctetString key;
    OctetString opC;
    OpType opType;
    OctetString amf;
    std::string imei;
    std::optional<Supi> supi;
    Plmn plmn;
    std::vector<SliceSupport> nssais;
    SupportedAlgs supportedAlgs;
    std::vector<std::string> gnbSearchList;
    std::vector<SessionConfig> initSessions;
    bool configureRouting;

    [[nodiscard]] std::string getNodeName() const
    {
        if (supi.has_value())
            return supi->type + "-" + supi->value;
        if (imei.length() > 0)
            return imei;
        return "unknown-ue";
    }
};

struct TaskBase
{
    UeConfig *config;
    LogBase *logBase;
    app::INodeListener *nodeListener;

    NtsTask *appTask;
    NtsTask *mrTask;
    NtsTask *nasTask;
    NtsTask *rrcTask;
};

struct UeTimers
{
    nas::NasTimer t3346; /* MM - ... */
    nas::NasTimer t3396; /* SM - ... */

    nas::NasTimer t3444; /* MM - ... */
    nas::NasTimer t3445; /* MM - ... */

    nas::NasTimer t3502; /* MM - ... */
    nas::NasTimer t3510; /* MM - Registration Request transmission timer */
    nas::NasTimer t3511; /* MM - ... */
    nas::NasTimer t3512; /* MM - Periodic registration update timer */
    nas::NasTimer t3516; /* MM - 5G AKA - RAND and RES* storing timer */
    nas::NasTimer t3517; /* MM - Service Request transmission timer */
    nas::NasTimer t3519; /* MM - Transmission with fresh SUCI timer */
    nas::NasTimer t3520; /* MM - ... */
    nas::NasTimer t3521; /* MM - De-registration transmission timer for not switch off */
    nas::NasTimer t3525; /* MM - ... */
    nas::NasTimer t3540; /* MM - ... */

    nas::NasTimer t3580; /* SM - ... */
    nas::NasTimer t3581; /* SM - ... */
    nas::NasTimer t3582; /* SM - ... */
    nas::NasTimer t3583; /* SM - ... */
    nas::NasTimer t3584; /* SM - ... */
    nas::NasTimer t3585; /* SM - ... */

    UeTimers();
};

enum class ERmState
{
    RM_DEREGISTERED,
    RM_REGISTERED
};

enum class ECmState
{
    CM_IDLE,
    CM_CONNECTED
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
    MM_NULL_NA,

    MM_DEREGISTERED_NA,
    MM_DEREGISTERED_NORMAL_SERVICE,
    MM_DEREGISTERED_LIMITED_SERVICE,
    MM_DEREGISTERED_ATTEMPTING_REGISTRATION,
    MM_DEREGISTERED_PLMN_SEARCH,
    MM_DEREGISTERED_NO_SUPI,
    MM_DEREGISTERED_NO_CELL_AVAILABLE,
    MM_DEREGISTERED_ECALL_INACTIVE,
    MM_DEREGISTERED_INITIAL_REGISTRATION_NEEDED,

    MM_REGISTERED_INITIATED_NA,

    MM_REGISTERED_NA,
    MM_REGISTERED_NORMAL_SERVICE,
    MM_REGISTERED_NON_ALLOWED_SERVICE,
    MM_REGISTERED_ATTEMPTING_REGISTRATION_UPDATE,
    MM_REGISTERED_LIMITED_SERVICE,
    MM_REGISTERED_PLMN_SEARCH,
    MM_REGISTERED_NO_CELL_AVAILABLE,
    MM_REGISTERED_UPDATE_NEEDED,

    MM_DEREGISTERED_INITIATED_NA,

    MM_SERVICE_REQUEST_INITIATED_NA
};

struct MmContext
{
    ERmState rmState;
    ECmState cmState;
    EMmState mmState;
    EMmSubState mmSubState;

    std::unique_ptr<nas::RegistrationRequest> registrationRequest{};

    nas::IE5gsMobileIdentity storedSuci{};
    nas::IE5gsMobileIdentity storedGuti{};

    std::optional<nas::IE5gsTrackingAreaIdentity> lastVisitedRegisteredTai{};
    std::optional<nas::IE5gsTrackingAreaIdentityList> taiList{};

    long lastPlmnSearchTrigger{};

    OctetString sqn{};

    MmContext()
        : rmState(ERmState::RM_DEREGISTERED), cmState(ECmState::CM_IDLE), mmState(EMmState::MM_NULL),
          mmSubState(EMmSubState::MM_NULL_NA)
    {
    }
};

struct PduSession
{
    static constexpr const int MIN_ID = 1;
    static constexpr const int MAX_ID = 15;

    int id;
    bool isEstablished{};

    nas::EPduSessionType sessionType{};
    std::optional<std::string> apn{};
    std::optional<SliceSupport> sNssai{};

    std::optional<nas::IEQoSRules> authorizedQoSRules{};
    std::optional<nas::IESessionAmbr> sessionAmbr{};
    std::optional<nas::IEQoSFlowDescriptions> authorizedQoSFlowDescriptions{};
    std::optional<nas::IEPduAddress> pduAddress{};
};

struct ProcedureTransaction
{
    static constexpr const int MIN_ID = 1;
    static constexpr const int MAX_ID = 254;

    int id;
};

struct SmContext
{
    PduSession pduSessions[16]{};
    ProcedureTransaction procedureTransactions[255]{};
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

    OctetString rand{};
    OctetString res{};
    OctetString resStar{}; // used in 5G-AKA

    OctetString kAusf{};
    OctetString kSeaf{};
    OctetString kAmf{};
    OctetString kNasInt{};
    OctetString kNasEnc{};

    [[nodiscard]] UeKeys deepCopy() const
    {
        UeKeys keys;
        keys.rand = rand.subCopy(0);
        keys.res = res.subCopy(0);
        keys.resStar = resStar.subCopy(0);
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

struct UeStatusInfo
{
    struct UePduSessionInfo
    {
        std::string type;
        std::string address;
    };

    bool isConnected;
    std::string connectedGnb;
    std::string mmState;
    std::string rmState;
    std::optional<UePduSessionInfo> pduSessions[16];
};

const char *RmStateName(ERmState state);
const char *MmStateName(EMmState state);
const char *MmSubStateName(EMmSubState state);

} // namespace nr::ue
