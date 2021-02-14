//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "types.hpp"
#include "ue.hpp"
#include <app/cli_base.hpp>
#include <nas/timer.hpp>
#include <rrc/rrc.hpp>
#include <urs/rls/rls.hpp>
#include <utility>
#include <utils/network.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>

namespace nr::ue
{

struct NwUeMrToMr : NtsMessage
{
    enum PR
    {
        RLS_CONNECTED,
        RLS_RELEASED,
        RLS_SEARCH_FAILURE,
        RLS_START_WAITING_TIMER,
        RLS_SEND_OVER_UDP,
        RLS_RECEIVE_OVER_UDP
    } present;

    // RLS_CONNECTED
    std::string gnbName{};

    // RLS_RELEASED
    // RLS_SEARCH_FAILURE
    rls::ECause cause{};

    // RLS_START_WAITING_TIMER
    int period{};

    // RLS_SEND_OVER_UDP
    InetAddress address{};

    // RLS_RECEIVE_OVER_UDP
    rls::EPayloadType type{};

    // RLS_SEND_OVER_UDP
    // RLS_RECEIVE_OVER_UDP
    OctetString pdu{};

    explicit NwUeMrToMr(PR present) : NtsMessage(NtsMessageType::UE_MR_TO_MR), present(present)
    {
    }
};

struct NwUeMrToRrc : NtsMessage
{
    enum PR
    {
        PLMN_SEARCH_RESPONSE,
        PLMN_SEARCH_FAILURE,
        RRC_PDU_DELIVERY,
    } present;

    // PLMN_SEARCH_RESPONSE
    std::string gnbName{};

    // RRC_PDU_DELIVERY
    rrc::RrcChannel channel{};
    OctetString pdu{};

    explicit NwUeMrToRrc(PR present) : NtsMessage(NtsMessageType::UE_MR_TO_RRC), present(present)
    {
    }
};

struct NwUeMrToApp : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString data{};

    explicit NwUeMrToApp(PR present) : NtsMessage(NtsMessageType::UE_MR_TO_APP), present(present)
    {
    }
};

struct NwAppToMr : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString data{};

    explicit NwAppToMr(PR present) : NtsMessage(NtsMessageType::UE_APP_TO_MR), present(present)
    {
    }
};

struct NwAppToTun : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString data{};

    explicit NwAppToTun(PR present) : NtsMessage(NtsMessageType::UE_APP_TO_TUN), present(present)
    {
    }
};

struct NwUeTunToApp : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY,
        TUN_ERROR
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString data{};

    // TUN_ERROR
    std::string error{};

    explicit NwUeTunToApp(PR present) : NtsMessage(NtsMessageType::UE_TUN_TO_APP), present(present)
    {
    }
};

struct NwUeRrcToNas : NtsMessage
{
    enum PR
    {
        NAS_DELIVERY,
        PLMN_SEARCH_RESPONSE,
        PLMN_SEARCH_FAILURE,
        RRC_CONNECTION_SETUP,
    } present;

    // NAS_DELIVERY
    OctetString nasPdu{};

    // PLMN_SEARCH_RESPONSE
    std::string gnbName{};

    explicit NwUeRrcToNas(PR present) : NtsMessage(NtsMessageType::UE_RRC_TO_NAS), present(present)
    {
    }
};

struct NwUeNasToRrc : NtsMessage
{
    enum PR
    {
        PLMN_SEARCH_REQUEST,
        INITIAL_NAS_DELIVERY,
        UPLINK_NAS_DELIVERY
    } present;

    // INITIAL_NAS_DELIVERY
    // UPLINK_NAS_DELIVERY
    OctetString nasPdu{};

    // INITIAL_NAS_DELIVERY
    long rrcEstablishmentCause{};

    explicit NwUeNasToRrc(PR present) : NtsMessage(NtsMessageType::UE_NAS_TO_RRC), present(present)
    {
    }
};

struct NwUeRrcToMr : NtsMessage
{
    enum PR
    {
        PLMN_SEARCH_REQUEST,
        RRC_PDU_DELIVERY
    } present;

    // RRC_PDU_DELIVERY
    rrc::RrcChannel channel{};
    OctetString pdu{};

    explicit NwUeRrcToMr(PR present) : NtsMessage(NtsMessageType::UE_RRC_TO_MR), present(present)
    {
    }
};

struct NwUeNasToNas : NtsMessage
{
    enum PR
    {
        PERFORM_MM_CYCLE,
        NAS_TIMER_EXPIRE,
        ESTABLISH_INITIAL_SESSIONS
    } present;

    // NAS_TIMER_EXPIRE
    nas::NasTimer *timer{};

    explicit NwUeNasToNas(PR present) : NtsMessage(NtsMessageType::UE_NAS_TO_NAS), present(present)
    {
    }
};

struct NwUeStatusUpdate : NtsMessage
{
    static constexpr const int SESSION_ESTABLISHMENT = 5;

    const int what{};

    // SESSION_ESTABLISHMENT
    PduSession *pduSession{};

    explicit NwUeStatusUpdate(const int what) : NtsMessage(NtsMessageType::UE_STATUS_UPDATE), what(what)
    {
    }
};

struct NwUeCliCommand : NtsMessage
{
    std::unique_ptr<app::UeCliCommand> cmd;
    InetAddress address;
    NtsTask *callbackTask;

    NwUeCliCommand(std::unique_ptr<app::UeCliCommand> cmd, InetAddress address, NtsTask *callbackTask)
        : NtsMessage(NtsMessageType::UE_CLI_COMMAND), cmd(std::move(cmd)), address(address), callbackTask(callbackTask)
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

} // namespace nr::ue