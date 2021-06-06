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
#include <lib/rls/rls_base.hpp>
#include <lib/rrc/rrc.hpp>
#include <utils/light_sync.hpp>
#include <utils/network.hpp>
#include <utils/nts.hpp>
#include <utils/octet_string.hpp>

namespace nr::ue
{

struct NmAppToTun : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString data{};

    explicit NmAppToTun(PR present) : NtsMessage(NtsMessageType::UE_APP_TO_TUN), present(present)
    {
    }
};

struct NmUeTunToApp : NtsMessage
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

    explicit NmUeTunToApp(PR present) : NtsMessage(NtsMessageType::UE_TUN_TO_APP), present(present)
    {
    }
};

struct NmUeRrcToNas : NtsMessage
{
    enum PR
    {
        NAS_NOTIFY,
        NAS_DELIVERY,
        RRC_CONNECTION_SETUP,
        RRC_CONNECTION_RELEASE,
        RRC_ESTABLISHMENT_FAILURE,
        RADIO_LINK_FAILURE,
        PAGING,
        ACTIVE_CELL_CHANGED,
        RRC_FALLBACK_INDICATION,
    } present;

    // NAS_DELIVERY
    OctetString nasPdu;

    // PAGING
    std::vector<GutiMobileIdentity> pagingTmsi;

    // ACTIVE_CELL_CHANGED
    Tai previousTai;

    explicit NmUeRrcToNas(PR present) : NtsMessage(NtsMessageType::UE_RRC_TO_NAS), present(present)
    {
    }
};

struct NmUeNasToRrc : NtsMessage
{
    enum PR
    {
        LOCAL_RELEASE_CONNECTION,
        UPLINK_NAS_DELIVERY,
        RRC_NOTIFY,
        PERFORM_UAC,
    } present;

    // UPLINK_NAS_DELIVERY
    uint32_t pduId{};
    OctetString nasPdu;

    // LOCAL_RELEASE_CONNECTION
    bool treatBarred{};

    // PERFORM_UAC
    std::shared_ptr<LightSync<UacInput, UacOutput>> uacCtl{};

    explicit NmUeNasToRrc(PR present) : NtsMessage(NtsMessageType::UE_NAS_TO_RRC), present(present)
    {
    }
};

struct NmUeRrcToRls : NtsMessage
{
    enum PR
    {
        ASSIGN_CURRENT_CELL,
        RRC_PDU_DELIVERY,
        RESET_STI,
    } present;

    // ASSIGN_CURRENT_CELL
    int cellId{};

    // RRC_PDU_DELIVERY
    rrc::RrcChannel channel{};
    uint32_t pduId{};
    OctetString pdu{};

    explicit NmUeRrcToRls(PR present) : NtsMessage(NtsMessageType::UE_RRC_TO_RLS), present(present)
    {
    }
};

struct NmUeRrcToRrc : NtsMessage
{
    enum PR
    {
        TRIGGER_CYCLE,
    } present;

    explicit NmUeRrcToRrc(PR present) : NtsMessage(NtsMessageType::UE_RRC_TO_RRC), present(present)
    {
    }
};

struct NmUeRlsToRrc : NtsMessage
{
    enum PR
    {
        DOWNLINK_RRC_DELIVERY,
        SIGNAL_CHANGED,
        RADIO_LINK_FAILURE
    } present;

    // DOWNLINK_RRC_DELIVERY
    // SIGNAL_CHANGED
    int cellId{};

    // DOWNLINK_RRC_DELIVERY
    rrc::RrcChannel channel{};
    OctetString pdu;

    // SIGNAL_CHANGED
    int dbm{};

    // RADIO_LINK_FAILURE
    rls::ERlfCause rlfCause{};

    explicit NmUeRlsToRrc(PR present) : NtsMessage(NtsMessageType::UE_RLS_TO_RRC), present(present)
    {
    }
};

struct NmUeNasToNas : NtsMessage
{
    enum PR
    {
        PERFORM_MM_CYCLE,
        NAS_TIMER_EXPIRE,
    } present;

    // NAS_TIMER_EXPIRE
    UeTimer *timer{};

    explicit NmUeNasToNas(PR present) : NtsMessage(NtsMessageType::UE_NAS_TO_NAS), present(present)
    {
    }
};

struct NmUeNasToApp : NtsMessage
{
    enum PR
    {
        PERFORM_SWITCH_OFF,
        DOWNLINK_DATA_DELIVERY
    } present;

    // DOWNLINK_DATA_DELIVERY
    int psi{};
    OctetString data;

    explicit NmUeNasToApp(PR present) : NtsMessage(NtsMessageType::UE_NAS_TO_APP), present(present)
    {
    }
};

struct NmUeAppToNas : NtsMessage
{
    enum PR
    {
        UPLINK_DATA_DELIVERY,
    } present;

    // UPLINK_DATA_DELIVERY
    int psi{};
    OctetString data;

    explicit NmUeAppToNas(PR present) : NtsMessage(NtsMessageType::UE_APP_TO_NAS), present(present)
    {
    }
};

struct NmUeNasToRls : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString pdu;

    explicit NmUeNasToRls(PR present) : NtsMessage(NtsMessageType::UE_NAS_TO_RLS), present(present)
    {
    }
};

struct NmUeRlsToNas : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString pdu{};

    explicit NmUeRlsToNas(PR present) : NtsMessage(NtsMessageType::UE_RLS_TO_NAS), present(present)
    {
    }
};

struct NmUeRlsToRls : NtsMessage
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

    explicit NmUeRlsToRls(PR present) : NtsMessage(NtsMessageType::UE_RLS_TO_RLS), present(present)
    {
    }
};

struct NmUeStatusUpdate : NtsMessage
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

    explicit NmUeStatusUpdate(const int what) : NtsMessage(NtsMessageType::UE_STATUS_UPDATE), what(what)
    {
    }
};

struct NmUeCliCommand : NtsMessage
{
    std::unique_ptr<app::UeCliCommand> cmd;
    InetAddress address;

    NmUeCliCommand(std::unique_ptr<app::UeCliCommand> cmd, InetAddress address)
        : NtsMessage(NtsMessageType::UE_CLI_COMMAND), cmd(std::move(cmd)), address(address)
    {
    }
};

} // namespace nr::ue