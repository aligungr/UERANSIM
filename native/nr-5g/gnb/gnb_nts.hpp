//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <network.hpp>
#include <nts.hpp>
#include <octet_string.hpp>
#include <rrc.hpp>
#include <sctp.hpp>
#include <urs_rls.hpp>
#include <utility>

#include "gnb_types.hpp"

namespace nr::gnb
{

// TODO: remove unused ones

struct NwSctpConnectionRequest : NtsMessage
{
    int clientId;

    std::string localAddress;
    uint16_t localPort;

    std::string remoteAddress;
    uint16_t remotePort;

    sctp::PayloadProtocolId ppid;

    NtsTask *associatedTask;

    NwSctpConnectionRequest(int clientId, std::string localAddress, uint16_t localPort, std::string remoteAddress,
                            uint16_t remotePort, sctp::PayloadProtocolId ppid, NtsTask *associatedTask)
        : NtsMessage(NtsMessageType::SCTP_CONNECTION_REQUEST), clientId(clientId),
          localAddress(std::move(localAddress)), localPort(localPort), remoteAddress(std::move(remoteAddress)),
          remotePort(remotePort), ppid(ppid), associatedTask(associatedTask)
    {
    }
};

struct NwSctpAssociationSetup : NtsMessage
{
    int clientId;
    int associationId;
    int inStreams;
    int outStreams;

    NwSctpAssociationSetup(int clientId, int associationId, int inStreams, int outStreams)
        : NtsMessage(NtsMessageType::SCTP_ASSOCIATION_SETUP), clientId(clientId), associationId(associationId),
          inStreams(inStreams), outStreams(outStreams)
    {
    }
};

struct NwSctpAssociationShutdown : NtsMessage
{
    int clientId;

    explicit NwSctpAssociationShutdown(int clientId)
        : NtsMessage(NtsMessageType::SCTP_ASSOCIATION_SHUTDOWN), clientId(clientId)
    {
    }
};

struct NwSctpClientReceive : NtsMessage
{
    int clientId;
    uint8_t *buffer;
    size_t length;
    uint16_t stream;

    NwSctpClientReceive(int clientId, uint8_t *buffer, size_t length, uint16_t stream)
        : NtsMessage(NtsMessageType::SCTP_CLIENT_RECEIVE), clientId(clientId), buffer(buffer), length(length),
          stream(stream)
    {
    }

    ~NwSctpClientReceive() override
    {
        delete[] buffer;
    }
};

struct NwSctpUnhandledNotificationReceive : NtsMessage
{
    int clientId;

    explicit NwSctpUnhandledNotificationReceive(int clientId)
        : NtsMessage(NtsMessageType::SCTP_UNHANDLED_NOTIFICATION_RECEIVE), clientId(clientId)
    {
    }
};

struct NwSctpConnectionClose : NtsMessage
{
    int clientId;

    explicit NwSctpConnectionClose(int clientId) : NtsMessage(NtsMessageType::SCTP_CONNECTION_CLOSE), clientId(clientId)
    {
    }
};

struct NwSctpSendMessage : NtsMessage
{
    const int clientId;
    const uint16_t stream;
    uint8_t *const buffer;
    const size_t offset;
    const size_t length;

    NwSctpSendMessage(int clientId, uint16_t stream, uint8_t *buffer, size_t offset, size_t length)
        : NtsMessage(NtsMessageType::SCTP_SEND_MESSAGE), clientId(clientId), stream(stream), buffer(buffer),
          offset(offset), length(length)
    {
    }

    ~NwSctpSendMessage() override
    {
        // This buffer was allocated by asn1c library using malloc/calloc
        free(buffer);
    }
};

struct NwDownlinkNasDelivery : NtsMessage
{
    int ueId;
    OctetString nasPdu;

    NwDownlinkNasDelivery(int ueId, OctetString &&nasPdu)
        : NtsMessage(NtsMessageType::NGAP_DOWNLINK_NAS_DELIVERY), ueId(ueId), nasPdu(std::move(nasPdu))
    {
    }
};

struct NwUplinkNasDelivery : NtsMessage
{
    int ueId;
    OctetString nasPdu;

    NwUplinkNasDelivery(int ueId, OctetString &&nasPdu)
        : NtsMessage(NtsMessageType::NGAP_UPLINK_NAS_DELIVERY), ueId(ueId), nasPdu(std::move(nasPdu))
    {
    }
};

struct NwPduSessionResourceCreate : NtsMessage
{
    PduSessionResource *resource;

    explicit NwPduSessionResourceCreate(PduSessionResource *resource)
        : NtsMessage(NtsMessageType::NGAP_PDU_SESSION_RESOURCE_CREATE), resource(resource)
    {
    }
};

struct NwGnbStatusUpdate : NtsMessage
{
    static constexpr const int INITIAL_SCTP_ESTABLISHED = 1;

    const int what;

    // INITIAL_SCTP_ESTABLISHED
    bool isInitialSctpEstablished{};

    explicit NwGnbStatusUpdate(const int what) : NtsMessage(NtsMessageType::GNB_STATUS_UPDATE), what(what)
    {
    }
};

struct NwGnbN1Ready : NtsMessage
{
    NwGnbN1Ready() : NtsMessage(NtsMessageType::GNB_MR_N1_IS_READY)
    {
    }
};

struct NwUeContextUpdate : NtsMessage
{
    int ueId;
    bool isCreate;
    AggregateMaximumBitRate ueAmbr;

    NwUeContextUpdate(int ueId, bool isCreate, const AggregateMaximumBitRate &ueAmbr)
        : NtsMessage(NtsMessageType::GTP_UE_CONTEXT_UPDATE), ueId(ueId), isCreate(isCreate), ueAmbr(ueAmbr)
    {
    }
};

struct NwGnbDownlinkRrc : NtsMessage
{
    int ueId;
    rrc::RrcChannel channel;
    OctetString rrcPdu;

    NwGnbDownlinkRrc(int ueId, rrc::RrcChannel channel, OctetString &&rrcPdu)
        : NtsMessage(NtsMessageType::GNB_MR_DOWNLINK_RRC), ueId(ueId), channel(channel), rrcPdu(std::move(rrcPdu))
    {
    }
};

struct NwGnbUplinkRrc : NtsMessage
{
    int ueId;
    rrc::RrcChannel channel;
    OctetString rrcPdu;

    NwGnbUplinkRrc(int ueId, rrc::RrcChannel channel, OctetString &&rrcPdu)
        : NtsMessage(NtsMessageType::GNB_MR_UPLINK_RRC), ueId(ueId), channel(channel), rrcPdu(std::move(rrcPdu))
    {
    }
};

struct NwUplinkData : NtsMessage
{
    int ueId;
    int pduSessionId;
    OctetString data;

    NwUplinkData(int ueId, int pduSessionId, OctetString &&data)
        : NtsMessage(NtsMessageType::GNB_MR_UPLINK_DATA), ueId(ueId), pduSessionId(pduSessionId), data(std::move(data))
    {
    }
};

struct NwDownlinkData : NtsMessage
{
    int ueId;
    int pduSessionId;
    OctetString data;

    NwDownlinkData(int ueId, int pduSessionId, OctetString &&data)
        : NtsMessage(NtsMessageType::GNB_MR_DOWNLINK_DATA), ueId(ueId), pduSessionId(pduSessionId),
          data(std::move(data))
    {
    }
};

struct NwUeConnected : NtsMessage
{
    int ue;
    std::string name;

    NwUeConnected(int ue, std::string name)
        : NtsMessage(NtsMessageType::GNB_RLS_UE_CONNECTED), ue(ue), name(std::move(name))
    {
    }
};

struct NwUeReleased : NtsMessage
{
    int ue;
    rls::ECause cause;

    NwUeReleased(int ue, rls::ECause cause) : NtsMessage(NtsMessageType::GNB_RLS_UE_RELEASED), ue(ue), cause(cause)
    {
    }
};

struct NwUplinkPayload : NtsMessage
{
    int ue;
    rls::EPayloadType type;
    OctetString payload;

    NwUplinkPayload(int ue, rls::EPayloadType type, OctetString &&payload)
        : NtsMessage(NtsMessageType::GNB_RLS_UPLINK_PAYLOAD), ue(ue), type(type), payload(std::move(payload))
    {
    }
};

struct NwRlsSendPdu : NtsMessage
{
    InetAddress address;
    OctetString pdu;

    NwRlsSendPdu(const InetAddress &address, OctetString &&pdu)
        : NtsMessage(NtsMessageType::GNB_RLS_SEND_PDU), address(address), pdu(std::move(pdu))
    {
    }
};

} // namespace nr::gnb
