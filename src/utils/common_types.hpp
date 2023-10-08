//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "json.hpp"
#include "octet.hpp"

#include <memory>
#include <optional>
#include <stdexcept>
#include <utility>
#include <vector>

enum class EPagingDrx
{
    V32 = 32,
    V64 = 64,
    V128 = 128,
    V256 = 256
};

struct Plmn
{
    int mcc{};
    int mnc{};
    bool isLongMnc{};

    [[nodiscard]] bool hasValue() const;
};

struct Tai
{
    Plmn plmn;
    int tac;

    Tai();
    Tai(const Plmn &plmn, int tac);
    Tai(int mcc, int mnc, bool longMnc, int tac);

    [[nodiscard]] bool hasValue() const;
};

struct SingleSlice
{
    octet sst{};
    std::optional<octet3> sd{};
};

struct NetworkSlice
{
    std::vector<SingleSlice> slices{};

    void addIfNotExists(const SingleSlice &slice);
};

enum class PduSessionType
{
    IPv4,
    IPv6,
    IPv4v6,
    ETHERNET,
    UNSTRUCTURED
};

struct PlmnSupport
{
    Plmn plmn{};
    NetworkSlice sliceSupportList{};
};

struct GutiMobileIdentity
{
    Plmn plmn;         // Not used in TMSI
    octet amfRegionId; // Not used in TMSI
    int amfSetId;      // 10-bit
    int amfPointer;    // 6-bit
    octet4 tmsi;

    GutiMobileIdentity() : plmn{}, amfRegionId{}, amfSetId{}, amfPointer{}, tmsi{}
    {
    }

    GutiMobileIdentity(const Plmn &plmn, const octet &amfRegionId, int amfSetId, int amfPointer, const octet4 &tmsi)
        : plmn(plmn), amfRegionId(amfRegionId), amfSetId(amfSetId), amfPointer(amfPointer), tmsi(tmsi)
    {
    }

    [[nodiscard]] int64_t toTmsiValue() const;

    static GutiMobileIdentity FromSTmsi(int64_t sTmsi);
};

struct ImsiMobileIdentity
{
    Plmn plmn;
    std::string routingIndicator;
    int protectionSchemaId; // 4-bit
    octet homeNetworkPublicKeyIdentifier;
    std::string schemeOutput;

    ImsiMobileIdentity()
        : plmn{}, routingIndicator{}, protectionSchemaId{}, homeNetworkPublicKeyIdentifier{}, schemeOutput{}
    {
    }

    ImsiMobileIdentity(const Plmn &plmn, std::string routingIndicator, int protectionSchemaId,
                       const octet &homeNetworkPublicKeyIdentifier, std::string schemeOutput)
        : plmn(plmn), routingIndicator(std::move(routingIndicator)), protectionSchemaId(protectionSchemaId),
          homeNetworkPublicKeyIdentifier(homeNetworkPublicKeyIdentifier), schemeOutput(std::move(schemeOutput))
    {
    }
};

struct Supi
{
    std::string type;
    std::string value;

    Supi(std::string type, std::string value) : type(std::move(type)), value(std::move(value))
    {
    }

    static Supi Parse(const std::string &supi);
};

enum class EDeregCause
{
    NORMAL,
    SWITCH_OFF,
    USIM_REMOVAL,
    DISABLE_5G,
    ECALL_INACTIVITY,
};

enum class EInitialRegCause
{
    EMERGENCY_SERVICES,
    MM_DEREG_NORMAL_SERVICE,
    T3346_EXPIRY,
    DUE_TO_DEREGISTRATION,
    DUE_TO_SERVICE_REJECT,
    TAI_CHANGE_IN_ATT_REG,
    PLMN_CHANGE_IN_ATT_REG,
    T3346_EXPIRY_IN_ATT_REG,
    T3502_EXPIRY_IN_ATT_REG,
    T3511_EXPIRY_IN_ATT_REG,
};

struct GlobalNci
{
    Plmn plmn{};
    int64_t nci{};

    GlobalNci() = default;

    GlobalNci(const Plmn &plmn, int64_t nci) : plmn(plmn), nci(nci)
    {
    }
};

enum class ECellCategory
{
    BARRED_CELL,
    RESERVED_CELL,
    ACCEPTABLE_CELL,
    SUITABLE_CELL,
};

struct Vector3
{
    int x{};
    int y{};
    int z{};

    Vector3() = default;

    Vector3(int x, int y, int z) : x(x), y(y), z(z)
    {
    }
};

struct UacAiBarringSet
{
    bool ai1 = false;
    bool ai2 = false;
    bool ai11 = false;
    bool ai12 = false;
    bool ai13 = false;
    bool ai14 = false;
    bool ai15 = false;
};

bool operator==(const Plmn &lhs, const Plmn &rhs);
bool operator!=(const Plmn &lhs, const Plmn &rhs);

bool operator==(const Tai &lhs, const Tai &rhs);
bool operator!=(const Tai &lhs, const Tai &rhs);

bool operator==(const SingleSlice &lhs, const SingleSlice &rhs);
bool operator!=(const SingleSlice &lhs, const SingleSlice &rhs);

bool operator==(const GlobalNci &lhs, const GlobalNci &rhs);
bool operator!=(const GlobalNci &lhs, const GlobalNci &rhs);

Json ToJson(const Supi &v);
Json ToJson(const Plmn &v);
Json ToJson(const Tai &v);
Json ToJson(const SingleSlice &v);
Json ToJson(const NetworkSlice &v);
Json ToJson(const PlmnSupport &v);
Json ToJson(const EDeregCause &v);
Json ToJson(const ECellCategory &v);
Json ToJson(const EInitialRegCause &v);

namespace std
{

template <>
struct hash<Plmn>
{
    std::size_t operator()(const Plmn &v) const noexcept;
};

template <>
struct hash<Tai>
{
    std::size_t operator()(const Tai &v) const noexcept;
};

template <>
struct hash<GlobalNci>
{
    std::size_t operator()(const GlobalNci &v) const noexcept;
};

} // namespace std