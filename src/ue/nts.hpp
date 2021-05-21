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

#include <utility>

#include <lib/app/cli_base.hpp>
#include <lib/nas/timer.hpp>
#include <lib/rls/rls_base.hpp>
#include <lib/rrc/rrc.hpp>
#include <utils/network.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>

namespace nr::ue
{

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
        RRC_CONNECTION_SETUP,
        RRC_CONNECTION_RELEASE,
        RADIO_LINK_FAILURE,
        PAGING,
        NAS_NOTIFY,
    } present;

    // NAS_DELIVERY
    OctetString nasPdu{};

    // PAGING
    std::vector<GutiMobileIdentity> pagingTmsi{};

    explicit NwUeRrcToNas(PR present) : NtsMessage(NtsMessageType::UE_RRC_TO_NAS), present(present)
    {
    }
};

struct NwUeNasToRrc : NtsMessage
{
    enum PR
    {
        LOCAL_RELEASE_CONNECTION,
        UPLINK_NAS_DELIVERY,
        RRC_NOTIFY,
    } present;

    // UPLINK_NAS_DELIVERY
    uint32_t pduId{};
    OctetString nasPdu;

    explicit NwUeNasToRrc(PR present) : NtsMessage(NtsMessageType::UE_NAS_TO_RRC), present(present)
    {
    }
};

struct NwUeRrcToRls : NtsMessage
{
    enum PR
    {
        ASSIGN_CURRENT_CELL,
        RRC_PDU_DELIVERY,
    } present;

    // ASSIGN_CURRENT_CELL
    int cellId{};

    // RRC_PDU_DELIVERY
    rrc::RrcChannel channel{};
    uint32_t pduId{};
    OctetString pdu{};

    explicit NwUeRrcToRls(PR present) : NtsMessage(NtsMessageType::UE_RRC_TO_RLS), present(present)
    {
    }
};

struct NwUeRrcToRrc : NtsMessage
{
    enum PR
    {
        TRIGGER_CYCLE,
    } present;

    explicit NwUeRrcToRrc(PR present) : NtsMessage(NtsMessageType::UE_RRC_TO_RRC), present(present)
    {
    }
};

struct NwUeRlsToRrc : NtsMessage
{
    enum PR
    {
        DOWNLINK_RRC_DELIVERY,
        SIGNAL_CHANGED
    } present;

    // DOWNLINK_RRC_DELIVERY
    // SIGNAL_CHANGED
    int cellId{};

    // DOWNLINK_RRC_DELIVERY
    rrc::RrcChannel channel{};
    OctetString pdu;

    // SIGNAL_CHANGED
    int dbm{};

    explicit NwUeRlsToRrc(PR present) : NtsMessage(NtsMessageType::UE_RLS_TO_RRC), present(present)
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

struct NwUeNasToApp : NtsMessage
{
    enum PR
    {
        PERFORM_SWITCH_OFF,
    } present;

    explicit NwUeNasToApp(PR present) : NtsMessage(NtsMessageType::UE_NAS_TO_APP), present(present)
    {
    }
};

struct NwUeAppToNas : NtsMessage
{
    enum PR
    {
        UPLINK_STATUS_CHANGE,
    } present;

    // UPLINK_STATUS_CHANGE
    int psi{};
    bool isPending{};

    explicit NwUeAppToNas(PR present) : NtsMessage(NtsMessageType::UE_APP_TO_NAS), present(present)
    {
    }
};

struct NwUeAppToRls : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString pdu;

    explicit NwUeAppToRls(PR present) : NtsMessage(NtsMessageType::UE_APP_TO_RLS), present(present)
    {
    }
};

struct NwUeRlsToApp : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString pdu{};

    explicit NwUeRlsToApp(PR present) : NtsMessage(NtsMessageType::UE_RLS_TO_APP), present(present)
    {
    }
};

struct NwUeRlsToRls : NtsMessage
{
    enum PR
    {
        RECEIVE_RLS_MESSAGE,
        SIGNAL_CHANGED,
        UPLINK_DATA,
        UPLINK_RRC,
        DOWNLINK_DATA,
        DOWNLINK_RRC,
        RADIO_LINK_FAILURE,
        TRANSMISSION_FAILURE,
        ASSIGN_CURRENT_CELL,
    } present;

    // RECEIVE_RLS_MESSAGE
    // UPLINK_RRC
    // DOWNLINK_RRC
    // SIGNAL_CHANGED
    // ASSIGN_CURRENT_CELL
    int cellId{};

    // RECEIVE_RLS_MESSAGE
    std::unique_ptr<rls::RlsMessage> msg{};

    // SIGNAL_CHANGED
    int dbm{};

    // UPLINK_DATA
    // DOWNLINK_DATA
    int psi{};

    // UPLINK_DATA
    // DOWNLINK_DATA
    // UPLINK_RRC
    // DOWNLINK_RRC
    OctetString data;

    // UPLINK_RRC
    // DOWNLINK_RRC
    rrc::RrcChannel rrcChannel{};

    // UPLINK_RRC
    uint32_t pduId{};

    // RADIO_LINK_FAILURE
    rls::ERlfCause rlfCause{};

    // TRANSMISSION_FAILURE
    std::vector<rls::PduInfo> pduList;

    explicit NwUeRlsToRls(PR present) : NtsMessage(NtsMessageType::UE_RLS_TO_RLS), present(present)
    {
    }
};

struct NwUeStatusUpdate : NtsMessage
{
    static constexpr const int SESSION_ESTABLISHMENT = 1;
    static constexpr const int SESSION_RELEASE = 2;
    static constexpr const int CM_STATE = 3;

    const int what{};

    // SESSION_ESTABLISHMENT
    PduSession *pduSession{};

    // SESSION_RELEASE
    int psi{};

    // CM_STATE
    ECmState cmState{};

    explicit NwUeStatusUpdate(const int what) : NtsMessage(NtsMessageType::UE_STATUS_UPDATE), what(what)
    {
    }
};

struct NwUeCliCommand : NtsMessage
{
    std::unique_ptr<app::UeCliCommand> cmd;
    InetAddress address;

    NwUeCliCommand(std::unique_ptr<app::UeCliCommand> cmd, InetAddress address)
        : NtsMessage(NtsMessageType::UE_CLI_COMMAND), cmd(std::move(cmd)), address(address)
    {
    }
};

} // namespace nr::ue