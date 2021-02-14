//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <app/monitor.hpp>
#include <nas/nas.hpp>
#include <nas/timer.hpp>
#include <utils/common_types.hpp>
#include <utils/json.hpp>
#include <utils/logger.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>

namespace nr::ue
{

class UeAppTask;
class UeMrTask;
class NasTask;
class UeRrcTask;

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
    /* Read from config file */
    std::optional<Supi> supi{};
    Plmn plmn{};
    OctetString key{};
    OctetString opC{};
    OpType opType{};
    OctetString amf{};
    std::optional<std::string> imei{};
    std::optional<std::string> imeiSv{};
    std::vector<SliceSupport> nssais{};
    SupportedAlgs supportedAlgs{};
    std::vector<std::string> gnbSearchList{};
    std::vector<SessionConfig> initSessions{};

    /* Assigned by program */
    bool emulationMode;
    bool configureRouting;
    bool prefixLogger;

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

struct TaskBase
{
    UeConfig *config{};
    LogBase *logBase{};
    app::INodeListener *nodeListener{};

    UeAppTask *appTask{};
    UeMrTask *mrTask{};
    NasTask *nasTask{};
    UeRrcTask *rrcTask{};
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
    CM_IDLE, // Exact same thing with 5GMM-IDLE in 24.501
    CM_CONNECTED
};

enum class E5UState
{
    U1_UPDATED,
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

struct PduSession
{
    static constexpr const int MIN_ID = 1;
    static constexpr const int MAX_ID = 15;

    int id{};
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

    int id{};
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
        std::string type{};
        std::string address{};
    };

    std::optional<UePduSessionInfo> pduSessions[16]{};
};

Json ToJson(const ECmState &state);
Json ToJson(const ERmState &state);
Json ToJson(const EMmState &state);
Json ToJson(const EMmSubState &state);
Json ToJson(const UeConfig &v);
Json ToJson(const UeTimers &v);

} // namespace nr::ue
