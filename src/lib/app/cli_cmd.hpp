//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <array>
#include <memory>
#include <string>
#include <vector>

#include <utils/common_types.hpp>

namespace app
{

struct GnbCliCommand
{
    enum PR
    {
        STATUS,
        INFO,
        AMF_LIST,
        AMF_INFO,
        UE_LIST,
        UE_COUNT,
        UE_RELEASE_REQ,
    } present;

    // AMF_INFO
    int amfId{};

    // UE_RELEASE_REQ
    int ueId{};

    explicit GnbCliCommand(PR present) : present(present)
    {
    }
};

struct UeCliCommand
{
    enum PR
    {
        INFO,
        STATUS,
        TIMERS,
        PS_ESTABLISH,
        PS_RELEASE,
        PS_RELEASE_ALL,
        PS_LIST,
        DE_REGISTER,
        RLS_STATE,
        COVERAGE,
    } present;

    // DE_REGISTER
    EDeregCause deregCause{};

    // PS_RELEASE
    std::array<int8_t, 16> psIds{};
    int psCount{};

    // PS_ESTABLISH
    std::optional<SingleSlice> sNssai{};
    std::optional<std::string> apn{};
    bool isEmergency{};

    explicit UeCliCommand(PR present) : present(present)
    {
    }
};

std::unique_ptr<GnbCliCommand> ParseGnbCliCommand(std::vector<std::string> &&tokens, std::string &error,
                                                  std::string &output);

std::unique_ptr<UeCliCommand> ParseUeCliCommand(std::vector<std::string> &&tokens, std::string &error,
                                                std::string &output);

} // namespace app