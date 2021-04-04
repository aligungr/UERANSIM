//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <app/cli_base.hpp>
#include <app/cli_cmd.hpp>
#include <rrc/rrc.hpp>
#include <sctp/sctp.hpp>
#include <utility>
#include <utils/network.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>
#include <utils/unique_buffer.hpp>

#include "types.hpp"

namespace nr::gnb
{

struct NwGnbSraToRrc : NtsMessage
{
    enum PR
    {
        RRC_PDU_DELIVERY,
        SIGNAL_LOST
    } present;

    // RRC_PDU_DELIVERY
    // SIGNAL_LOST
    int ueId{};

    // RRC_PDU_DELIVERY
    rrc::RrcChannel channel{};
    OctetString pdu{};

    explicit NwGnbSraToRrc(PR present) : NtsMessage(NtsMessageType::GNB_SRA_TO_RRC), present(present)
    {
    }
};

struct NwGnbRrcToSra : NtsMessage
{
    enum PR
    {
        RRC_PDU_DELIVERY,
    } present;

    // RRC_PDU_DELIVERY
    int ueId{};
    rrc::RrcChannel channel{};
    OctetString pdu{};

    explicit NwGnbRrcToSra(PR present) : NtsMessage(NtsMessageType::GNB_RRC_TO_SRA), present(present)
    {
    }
};

struct NwGnbNgapToRrc : NtsMessage
{
    enum PR
    {
        NGAP_LAYER_INITIALIZED,
        NAS_DELIVERY,
        AN_RELEASE,
    } present;

    // NAS_DELIVERY
    // AN_RELEASE
    int ueId{};

    // NAS_DELIVERY
    OctetString pdu{};

    explicit NwGnbNgapToRrc(PR present) : NtsMessage(NtsMessageType::GNB_NGAP_TO_RRC), present(present)
    {
    }
};

struct NwGnbRrcToNgap : NtsMessage
{
    enum PR
    {
        INITIAL_NAS_DELIVERY,
        UPLINK_NAS_DELIVERY,
        RADIO_LINK_FAILURE
    } present;

    // INITIAL_NAS_DELIVERY
    // UPLINK_NAS_DELIVERY
    // RADIO_LINK_FAILURE
    int ueId{};

    // INITIAL_NAS_DELIVERY
    // UPLINK_NAS_DELIVERY
    OctetString pdu{};

    // INITIAL_NAS_DELIVERY
    long rrcEstablishmentCause{};

    explicit NwGnbRrcToNgap(PR present) : NtsMessage(NtsMessageType::GNB_RRC_TO_NGAP), present(present)
    {
    }
};

struct NwGnbNgapToGtp : NtsMessage
{
    enum PR
    {
        UE_CONTEXT_UPDATE,
        UE_CONTEXT_RELEASE,
        SESSION_CREATE,
        SESSION_RELEASE,
    } present;

    // UE_CONTEXT_UPDATE
    std::unique_ptr<GtpUeContextUpdate> update{};

    // SESSION_CREATE
    PduSessionResource *resource{};

    // UE_CONTEXT_RELEASE
    // SESSION_RELEASE
    int ueId{};

    // SESSION_RELEASE
    int psi{};

    explicit NwGnbNgapToGtp(PR present) : NtsMessage(NtsMessageType::GNB_NGAP_TO_GTP), present(present)
    {
    }
};

struct NwGnbSctp : NtsMessage
{
    enum PR
    {
        CONNECTION_REQUEST,
        CONNECTION_CLOSE,
        ASSOCIATION_SETUP,
        ASSOCIATION_SHUTDOWN,
        RECEIVE_MESSAGE,
        SEND_MESSAGE,
        UNHANDLED_NOTIFICATION,
    } present;

    // CONNECTION_REQUEST
    // CONNECTION_CLOSE
    // ASSOCIATION_SETUP
    // ASSOCIATION_SHUTDOWN
    // RECEIVE_MESSAGE
    // SEND_MESSAGE
    // UNHANDLED_NOTIFICATION
    int clientId{};

    // CONNECTION_REQUEST
    std::string localAddress{};
    uint16_t localPort{};
    std::string remoteAddress{};
    uint16_t remotePort{};
    sctp::PayloadProtocolId ppid{};
    NtsTask *associatedTask{};

    // ASSOCIATION_SETUP
    int associationId{};
    int inStreams{};
    int outStreams{};

    // RECEIVE_MESSAGE
    // SEND_MESSAGE
    UniqueBuffer buffer{};
    uint16_t stream{};

    explicit NwGnbSctp(PR present) : NtsMessage(NtsMessageType::GNB_SCTP), present(present)
    {
    }
};

struct NwGnbStatusUpdate : NtsMessage
{
    static constexpr const int NGAP_IS_UP = 1;

    const int what;

    // NGAP_IS_UP
    bool isNgapUp{};

    explicit NwGnbStatusUpdate(const int what) : NtsMessage(NtsMessageType::GNB_STATUS_UPDATE), what(what)
    {
    }
};

struct NwGnbCliCommand : NtsMessage
{
    std::unique_ptr<app::GnbCliCommand> cmd;
    InetAddress address;

    NwGnbCliCommand(std::unique_ptr<app::GnbCliCommand> cmd, InetAddress address)
        : NtsMessage(NtsMessageType::GNB_CLI_COMMAND), cmd(std::move(cmd)), address(address)
    {
    }
};

} // namespace nr::gnb
