//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstdint>
#include <memory>
#include <optional>

#include <octet_buffer.hpp>
#include <octet_string.hpp>

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

    static std::unique_ptr<PduSessionInformation> Decode(OctetBuffer &stream);
    static bool Encode(const PduSessionInformation &pdu, OctetString &stream);
};

struct DlPduSessionInformation : PduSessionInformation
{
    bool qmp{};                        // (Mandatory) QoS Monitoring Packet, See 5.5.3.8
    int qfi{};                         // (Mandatory) 6-bit, QOS Flow Identifier, See 5.5.3.3
    bool rqi{};                        // (Mandatory) Reflective QOS Indicator, See 5.5.3.4
    std::optional<int> ppi{};          // (Optional, may be null) Paging Policy Indicator, See 5.5.3.7
    std::optional<long> dlSendingTs{}; // (Optional, may be null) DL Sending Time Stamp, See 5.5.3.9
    std::optional<int> dlQfiSeq{};     // (Optional, may be null) 3-octet, DL QFI Sequence Number, See 5.5.3.18

    DlPduSessionInformation() : PduSessionInformation(PDU_TYPE_DL)
    {
    }

    ~DlPduSessionInformation() override = default;
};

struct UlPduSessionInformation : PduSessionInformation
{
    bool qmp{};                                // (Mandatory) QoS Monitoring Packet, See 5.5.3.8
    int qfi{};                                 // (Mandatory) 6-bit, QOS Flow Identifier, See 5.5.3.3
    std::optional<long> dlSendingTsRepeated{}; // (Optional, may be null) DL Sending Time Stamp Repeated, See 5.5.3.10
    std::optional<long> dlReceivedTs{};        // (Optional, may be null) DL Received Time Stamp, See 5.5.3.11
    std::optional<long> ulSendingTs{};         // (Optional, may be null) UL Sending Time Stamp, See 5.5.3.12
    std::optional<uint32_t> dlDelayResult{};   // (Optional, may be null) DL Delay Result, See 5.5.3.14
    std::optional<uint32_t> ulDelayResult{};   // (Optional, may be null) UL Delay Result, See 5.5.3.16
    std::optional<int> ulQfiSeq{};             // (Optional, may be null) 3-octet, UL QFI Sequence Number, See 5.5.3.19

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

} // namespace gtp
