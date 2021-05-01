//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <functional>
#include <map>

#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

namespace eap
{

enum class ESubType
{
    AKA_CHALLENGE = 1,
    AKA_AUTHENTICATION_REJECT = 2,
    AKA_SYNCHRONIZATION_FAILURE = 4,
    AKA_IDENTITY = 5,
    AKA_NOTIFICATION = 12,
    AKA_REAUTHENTICATION = 13,
    AKA_CLIENT_ERROR = 14,
};

enum class EAttributeType : uint8_t
{
    AT_RAND = 1,
    AT_AUTN = 2,
    AT_RES = 3,
    AT_AUTS = 4,
    AT_PADDING = 6,
    AT_NONCE_MT = 7,
    AT_PERMANENT_ID_REQ = 10,
    AT_MAC = 11,
    AT_NOTIFICATION = 12,
    AT_ANY_ID_REQ = 13,
    AT_IDENTITY = 14,
    AT_VERSION_LIST = 15,
    AT_SELECTED_VERSION = 16,
    AT_FULLAUTH_ID_REQ = 17,
    AT_COUNTER = 19,
    AT_COUNTER_TOO_SMALL = 20,
    AT_NONCE_S = 21,
    AT_CLIENT_ERROR_CODE = 22,
    AT_KDF_INPUT = 23,
    AT_KDF = 24,
    AT_IV = 129,
    AT_ENCR_DATA = 130,
    AT_NEXT_PSEUDONYM = 132,
    AT_NEXT_REAUTH_ID = 133,
    AT_CHECKCODE = 134,
    AT_RESULT_IND = 135,
    AT_BIDDING = 136,
    AT_IPMS_IND = 137,
    AT_IPMS_RES = 138,
    AT_TRUST_IND = 139,
};

enum class ECode
{
    REQUEST = 1,
    RESPONSE = 2,
    SUCCESS = 3,
    FAILURE = 4,
    INITIATE = 5,
    FINISH = 6,
};

enum class EEapType
{
    NO_TYPE = 0,
    IDENTITY = 1,
    NOTIFICATION = 2,
    LEGACY_NAK_RESPONSE_ONLY = 3,
    EAP_MD5_CHALLANGE = 4,
    EAP_OTP = 5,
    EAP_GTC = 6,
    ALLOCATED1 = 7,
    ALLOCATED2 = 8,
    EAP_RSA_PKA = 9,
    EAP_DSS = 10,
    EAP_KEA = 11,
    EAP_KEA_VALIDATE = 12,
    EAP_TLS = 13,
    EAP_AXENT = 14,
    EAP_RSA_SECURID = 15,
    EAP_ARCOT_SYSTEMS = 16,
    EAP_LEAP = 17,
    EAP_SIM = 18,
    EAP_SRP_SHA1_PART1 = 19,
    EAP_SRP_SHA1_PART2 = 20,
    EAP_TTLS = 21,
    EAP_RAS = 22,
    EAP_AKA = 23,
    EAP_3COM_WIRELESS = 24,
    EAP_PEAP = 25,
    EAP_MS_AUTH = 26,
    EAP_MAKE = 27,
    EAP_CRYPTOCARD = 28,
    EAP_MS_CHAP_V2 = 29,
    EAP_DYNAMID = 30,
    EAP_ROB = 31,
    EAP_POTP = 32,
    EAP_MS_AUTH_TLV = 33,
    EAP_SENTRINET = 34,
    EAP_ACTIONTEC_WIRELESS = 35,
    EAP_COGENT_BIOMETRIC = 36,
    EAP_AIRFORTRESS = 37,
    EAP_HTTP_DIGEST = 38,
    EAP_SECURESUITE = 39,
    EAP_DEVICECONNECT = 40,
    EAP_SPEKE = 41,
    EAP_MOBAC = 42,
    EAP_FAST = 43,
    EAP_ZLXEAP = 44,
    EAP_LINK = 45,
    EAP_PAX = 46,
    EAP_PSK = 47,
    EAP_SAKE = 48,
    EAP_IKEV2 = 49,
    EAP_AKA_PRIME = 50,
    EAP_GPSK = 51,
    EAP_PWD = 52,
    EAP_EKEV1 = 53,
    EXPANDED_EAP_TYPE = 254,
    EXPERIMENTAL = 255,
};

class EapAttributes
{
    std::array<std::optional<OctetString>, 256> attributes{};

  public:
    [[nodiscard]] OctetString getRand() const;
    [[nodiscard]] OctetString getMac() const;
    [[nodiscard]] OctetString getAutn() const;
    [[nodiscard]] int getClientErrorCode() const;
    [[nodiscard]] int getKdf() const;
    [[nodiscard]] OctetString getKdfInput() const;

  public:
    void putRes(const OctetString &value);
    void putMac(const OctetString &value);
    void putKdf(int value);
    void putClientErrorCode(int code);
    void putAuts(OctetString &&auts);

  public:
    void forEachEntry(const std::function<void(EAttributeType, const OctetString &)> &fun) const;
    void putRawAttribute(EAttributeType key, OctetString &&value);
};

class Eap
{
  public:
    const ECode code;
    const octet id;
    const EEapType eapType;

    Eap(ECode code, octet id, EEapType eapType);
};

class EapAkaPrime : public Eap
{
  public:
    ESubType subType;
    EapAttributes attributes{};

    EapAkaPrime(ECode code, octet id, ESubType subType);
};

class EapIdentity : public Eap
{
  public:
    OctetString rawData;

    EapIdentity(ECode code, octet id);
    EapIdentity(ECode code, octet id, OctetString &&rawData);
};

class EapNotification : public Eap
{
  public:
    OctetString rawData;

    EapNotification(ECode code, octet id);
    EapNotification(ECode code, octet id, OctetString &&rawData);
};

void EncodeEapPdu(OctetString &stream, const Eap &eap);
std::unique_ptr<Eap> DecodeEapPdu(const OctetView &stream);

} // namespace eap
