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
#include <urs/rls/rls.hpp>
#include <utility>
#include <utils/network.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>
#include <utils/unique_buffer.hpp>

#include "types.hpp"

namespace nr::gnb
{

struct NwGnbMrToRrc : NtsMessage
{
    enum PR
    {
        RRC_PDU_DELIVERY
    } present;

    // RRC_PDU_DELIVERY
    int ueId{};
    rrc::RrcChannel channel{};
    OctetString pdu{};

    explicit NwGnbMrToRrc(PR present) : NtsMessage(NtsMessageType::GNB_MR_TO_RRC), present(present)
    {
    }
};

struct NwGnbRrcToMr : NtsMessage
{
    enum PR
    {
        NGAP_LAYER_INITIALIZED,
        RRC_PDU_DELIVERY
    } present;

    // RRC_PDU_DELIVERY
    int ueId{};
    rrc::RrcChannel channel{};
    OctetString pdu{};

    explicit NwGnbRrcToMr(PR present) : NtsMessage(NtsMessageType::GNB_RRC_TO_MR), present(present)
    {
    }
};

struct NwGnbNgapToRrc : NtsMessage
{
    enum PR
    {
        NGAP_LAYER_INITIALIZED,
        NAS_DELIVERY,
    } present;

    // NAS_DELIVERY
    int ueId{};
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
        UPLINK_NAS_DELIVERY
    } present;

    // INITIAL_NAS_DELIVERY
    // UPLINK_NAS_DELIVERY
    int ueId{};
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
        SESSION_CREATE
    } present;

    // UE_CONTEXT_UPDATE
    std::unique_ptr<GtpUeContextUpdate> update{};

    // SESSION_CREATE
    PduSessionResource *resource{};

    explicit NwGnbNgapToGtp(PR present) : NtsMessage(NtsMessageType::GNB_NGAP_TO_GTP), present(present)
    {
    }
};

struct NwGnbMrToGtp : NtsMessage
{
    enum PR
    {
        UPLINK_DELIVERY,
    } present;

    // UPLINK_DELIVERY
    int ueId{};
    int pduSessionId{};
    OctetString data{};

    explicit NwGnbMrToGtp(PR present) : NtsMessage(NtsMessageType::GNB_MR_TO_GTP), present(present)
    {
    }
};

struct NwGnbGtpToMr : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY,
    } present;

    // DATA_PDU_DELIVERY
    int ueId{};
    int pduSessionId{};
    OctetString data{};

    explicit NwGnbGtpToMr(PR present) : NtsMessage(NtsMessageType::GNB_GTP_TO_MR), present(present)
    {
    }
};

struct NwGnbMrToMr : NtsMessage
{
    enum PR
    {
        UE_CONNECTED,
        UE_RELEASED,
        SEND_OVER_UDP,
        RECEIVE_OVER_UDP,
    } present;

    // UE_CONNECTED
    // UE_RELEASED
    // RECEIVE_OVER_UDP
    int ue{};

    // UE_CONNECTED
    std::string name{};

    // UE_RELEASED
    rls::ECause cause{};

    // SEND_OVER_RLS
    InetAddress address{};
    OctetString pdu{};

    // RECEIVE_OVER_UDP
    rls::EPayloadType type{};

    explicit NwGnbMrToMr(PR present) : NtsMessage(NtsMessageType::GNB_MR_TO_MR), present(present)
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
    NtsTask *callbackTask;

    NwGnbCliCommand(std::unique_ptr<app::GnbCliCommand> cmd, InetAddress address, NtsTask *callbackTask)
        : NtsMessage(NtsMessageType::GNB_CLI_COMMAND), cmd(std::move(cmd)), address(address), callbackTask(callbackTask)
    {
    }

    void sendResult(const std::string &output) const
    {
        callbackTask->push(new app::NwCliSendResponse(address, output, false));
    }

    void sendError(const std::string &output) const
    {
        callbackTask->push(new app::NwCliSendResponse(address, output, true));
    }
};

} // namespace nr::gnb
