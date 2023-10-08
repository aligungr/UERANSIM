//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstdint>
#include <memory>
#include <optional>
#include <vector>

#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

namespace gtp
{

// See 3GPP 38.415
struct PduSessionInformation
{
    static constexpr const int PDU_TYPE_DL = 0;
    static constexpr const int PDU_TYPE_UL = 1;

    const int pduType;

    explicit PduSessionInformation(int pduType) : pduType(pduType)
    {
    }

    virtual ~PduSessionInformation() = default;

    static std::unique_ptr<PduSessionInformation> Decode(const OctetView &stream);
    static bool Encode(const PduSessionInformation &pdu, OctetString &stream);
};

struct DlPduSessionInformation : PduSessionInformation
{
    bool qmp{};                           // (Mandatory) QoS Monitoring Packet, See 5.5.3.8
    int qfi{};                            // (Mandatory) 6-bit, QOS Flow Identifier, See 5.5.3.3
    bool rqi{};                           // (Mandatory) Reflective QOS Indicator, See 5.5.3.4
    std::optional<int> ppi{};             // (Optional, may be null) Paging Policy Indicator, See 5.5.3.7
    std::optional<int64_t> dlSendingTs{}; // (Optional, may be null) DL Sending Time Stamp, See 5.5.3.9
    std::optional<int> dlQfiSeq{};        // (Optional, may be null) 3-octet, DL QFI Sequence Number, See 5.5.3.18

    DlPduSessionInformation() : PduSessionInformation(PDU_TYPE_DL)
    {
    }

    ~DlPduSessionInformation() override = default;
};

struct UlPduSessionInformation : PduSessionInformation
{
    bool qmp{}; // (Mandatory) QoS Monitoring Packet, See 5.5.3.8
    int qfi{};  // (Mandatory) 6-bit, QOS Flow Identifier, See 5.5.3.3
    std::optional<int64_t>
        dlSendingTsRepeated{};               // (Optional, may be null) DL Sending Time Stamp Repeated, See 5.5.3.10
    std::optional<int64_t> dlReceivedTs{};   // (Optional, may be null) DL Received Time Stamp, See 5.5.3.11
    std::optional<int64_t> ulSendingTs{};    // (Optional, may be null) UL Sending Time Stamp, See 5.5.3.12
    std::optional<uint32_t> dlDelayResult{}; // (Optional, may be null) DL Delay Result, See 5.5.3.14
    std::optional<uint32_t> ulDelayResult{}; // (Optional, may be null) UL Delay Result, See 5.5.3.16
    std::optional<int> ulQfiSeq{};           // (Optional, may be null) 3-octet, UL QFI Sequence Number, See 5.5.3.19

    UlPduSessionInformation() : PduSessionInformation(PDU_TYPE_UL)
    {
    }

    ~UlPduSessionInformation() override = default;
};

enum class ExtHeaderType
{
    LongPdcpPduNumberExtHeader,
    NrRanContainerExtHeader,
    PdcpPduNumberExtHeader,
    PduSessionContainerExtHeader,
    UdpPortExtHeader,
};

struct GtpExtHeader
{
    const ExtHeaderType type;

    explicit GtpExtHeader(ExtHeaderType type) : type(type)
    {
    }

    virtual ~GtpExtHeader() = default;
};

struct LongPdcpPduNumberExtHeader : GtpExtHeader
{
    int pdcpPduNumber{}; // 18-bit

    LongPdcpPduNumberExtHeader() : GtpExtHeader(ExtHeaderType::LongPdcpPduNumberExtHeader)
    {
    }
};

struct NrRanContainerExtHeader : GtpExtHeader
{
    NrRanContainerExtHeader() : GtpExtHeader(ExtHeaderType::NrRanContainerExtHeader)
    {
    }
};

struct PdcpPduNumberExtHeader : GtpExtHeader
{
    uint16_t pdcpPduNumber{};

    PdcpPduNumberExtHeader() : GtpExtHeader(ExtHeaderType::PdcpPduNumberExtHeader)
    {
    }
};

struct UdpPortExtHeader : GtpExtHeader
{
    uint16_t port{};

    UdpPortExtHeader() : GtpExtHeader(ExtHeaderType::UdpPortExtHeader)
    {
    }
};

struct PduSessionContainerExtHeader : GtpExtHeader
{
    std::unique_ptr<PduSessionInformation> pduSessionInformation{};

    PduSessionContainerExtHeader() : GtpExtHeader(ExtHeaderType::PduSessionContainerExtHeader)
    {
    }
};

struct GtpMessage
{
    // GTP Message Types. (Only GTP-U included)
    static constexpr const uint8_t MT_ECHO_REQUEST = 1;
    static constexpr const uint8_t MT_ECHO_RESPONSE = 2;
    static constexpr const uint8_t MT_ERROR_INDICATION = 26;
    static constexpr const uint8_t MT_SUPPORTED_EXT_HEADERS_NOTIFICATION = 31;
    static constexpr const uint8_t MT_END_MARKER = 254;
    static constexpr const uint8_t MT_G_PDU = 255;

    uint8_t msgType;
    uint32_t teid;
    std::optional<uint16_t> seq;
    std::optional<uint8_t> nPduNum;
    std::vector<std::unique_ptr<GtpExtHeader>> extHeaders;
    OctetString payload;
};

bool EncodeGtpMessage(const GtpMessage &msg, OctetString &stream);
std::unique_ptr<GtpMessage> DecodeGtpMessage(const OctetView &stream);

} // namespace gtp
