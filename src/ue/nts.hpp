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

struct NmUeTunToApp : NtsMessage
{
    enum PR
    {
        DATA_PDU_DELIVERY,
    } present;

    // DATA_PDU_DELIVERY
    int psi{};
    OctetString data{};

    explicit NmUeTunToApp(PR present) : NtsMessage(NtsMessageType::UE_TUN_TO_APP), present(present)
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

struct NmUeCliCommand : NtsMessage
{
    std::unique_ptr<app::UeCliCommand> cmd;
    InetAddress address;

    NmUeCliCommand(std::unique_ptr<app::UeCliCommand> cmd, InetAddress address)
        : NtsMessage(NtsMessageType::UE_CLI_COMMAND), cmd(std::move(cmd)), address(address)
    {
    }
};

struct NmCycleRequired : NtsMessage
{
    NmCycleRequired() : NtsMessage(NtsMessageType::UE_CYCLE_REQUIRED)
    {
    }
};


struct NmSwitchOff : NtsMessage
{
    NmSwitchOff() : NtsMessage(NtsMessageType::UE_SWITCH_OFF)
    {
    }
};

} // namespace nr::ue