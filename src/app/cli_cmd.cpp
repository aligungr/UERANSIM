//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "cli_cmd.hpp"
#include <map>
#include <optional>
#include <sstream>
#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/options.hpp>
#include <utils/ordered_map.hpp>

#define CMD_ERR(x)                                                                                                     \
    {                                                                                                                  \
        error = x;                                                                                                     \
        return nullptr;                                                                                                \
    }

static opt::OptionsDescription DefaultDesc(const std::string &subCommand, const std::string &desc,
                                           const std::string &usage, bool helpIfEmpty)
{
    return {{}, {}, desc, {}, subCommand, {usage}, helpIfEmpty, true};
}

using DescType = decltype(&DefaultDesc);

class OptionsHandler : public opt::IOptionsHandler
{
  public:
    std::stringstream m_output{};
    std::stringstream m_err{};

  public:
    std::ostream &ostream(bool isError) override
    {
        return isError ? m_err : m_output;
    }

    void status(int code) override
    {
        // nothing to do
    }
};

static std::string DumpCommands(const OrderedMap<std::string, std::string> &descTable)
{
    size_t maxLength = 0;
    for (auto &item : descTable)
        maxLength = std::max(maxLength, item.size());

    std::stringstream ss{};
    for (auto &item : descTable)
        ss << item << std::string(maxLength - item.size(), ' ') << " | " << descTable[item] << "\n";
    std::string output = ss.str();

    utils::Trim(output);
    return output;
}

namespace app
{

static OrderedMap<std::string, DescType> g_gnbCmdToDescFunc = {
    {"status", DefaultDesc},   {"info", DefaultDesc},    {"amf-list", DefaultDesc},
    {"amf-info", DefaultDesc}, {"ue-list", DefaultDesc}, {"ue-count", DefaultDesc},
};

static OrderedMap<std::string, std::string> g_gnbCmdToDescription = {
    {"status", "Show some status information about the gNB"},
    {"info", "Show some information about the gNB"},
    {"amf-list", "List all AMFs associated with the gNB"},
    {"amf-info", "Show some status information about the given AMF"},
    {"ue-list", "List all UEs associated with the gNB"},
    {"ue-count", "Print the total number of UEs connected the this gNB"},
};

static OrderedMap<std::string, std::string> g_gnbCmdToUsage = {
    {"status", ""}, {"info", ""}, {"amf-list", ""}, {"amf-info", "<amf-id>"}, {"ue-list", ""}, {"ue-count", ""},
};

static OrderedMap<std::string, bool> g_gnbCmdToHelpIfEmpty = {{"status", false},   {"info", false},
                                                              {"amf-list", false}, {"amf-info", true},
                                                              {"ue-list", false},  {"ue-count", false}};

static OrderedMap<std::string, DescType> g_ueCmdToDescFunc = {
    {"info", DefaultDesc},           {"status", DefaultDesc},       {"timers", DefaultDesc},
    {"deregister", DefaultDesc},     {"ps-establish", DefaultDesc}, {"ps-release", DefaultDesc},
    {"ps-release-all", DefaultDesc},
};

static OrderedMap<std::string, std::string> g_ueCmdToDescription = {
    {"info", "Show some information about the UE"},
    {"status", "Show some status information about the UE"},
    {"timers", "Dump current status of the timers in the UE"},
    {"deregister", "Perform a de-registration by the UE"},
    {"ps-establish", "Trigger a PDU session establishment procedure"},
    {"ps-release", "Trigger a PDU session release procedure"},
    {"ps-release-all", "Trigger PDU session release procedures for all active sessions"},
};

static std::map<std::string, std::string> g_ueCmdToUsage = {
    {"info", ""},           {"status", ""},
    {"timers", ""},         {"deregister", "<normal|disable-5g|switch-off|remove-sim>"},
    {"ps-establish", ""},   {"ps-release", "<pdu-session-id>..."},
    {"ps-release-all", ""},
};

static std::map<std::string, bool> g_ueCmdToHelpIfEmpty = {
    {"info", false},        {"status", false},    {"timers", false},         {"deregister", true},
    {"ps-establish", true}, {"ps-release", true}, {"ps-release-all", false},
};

std::unique_ptr<GnbCliCommand> ParseGnbCliCommand(std::vector<std::string> &&tokens, std::string &error,
                                                  std::string &output)
{
    if (tokens.empty())
    {
        error = "Empty command";
        return nullptr;
    }

    std::string &subCmd = tokens[0];

    if (subCmd == "commands")
    {
        output = DumpCommands(g_gnbCmdToDescription);
        return nullptr;
    }

    if (g_gnbCmdToDescription.count(subCmd) == 0 || g_gnbCmdToUsage.count(subCmd) == 0 ||
        g_gnbCmdToHelpIfEmpty.count(subCmd) == 0)
    {
        error = "Command not recognized: " + subCmd;
        return nullptr;
    }

    opt::OptionsDescription desc = g_gnbCmdToDescFunc[subCmd](subCmd, g_gnbCmdToDescription[subCmd],
                                                              g_gnbCmdToUsage[subCmd], g_gnbCmdToHelpIfEmpty[subCmd]);

    OptionsHandler handler{};

    opt::OptionsResult options{tokens, desc, &handler};

    error = handler.m_err.str();
    output = handler.m_output.str();
    utils::Trim(error);
    utils::Trim(output);

    if (!error.empty() || !output.empty())
        return nullptr;

    if (subCmd == "info")
    {
        return std::make_unique<GnbCliCommand>(GnbCliCommand::INFO);
    }
    if (subCmd == "status")
    {
        return std::make_unique<GnbCliCommand>(GnbCliCommand::STATUS);
    }
    else if (subCmd == "amf-list")
    {
        return std::make_unique<GnbCliCommand>(GnbCliCommand::AMF_LIST);
    }
    else if (subCmd == "amf-info")
    {
        auto cmd = std::make_unique<GnbCliCommand>(GnbCliCommand::AMF_INFO);
        if (options.positionalCount() == 0)
            CMD_ERR("AMF ID is expected")
        if (options.positionalCount() > 1)
            CMD_ERR("Only one AMF ID is expected")
        cmd->amfId = utils::ParseInt(options.getPositional(0));
        if (cmd->amfId <= 0)
            CMD_ERR("Invalid AMF ID")
        return cmd;
    }
    else if (subCmd == "ue-list")
    {
        return std::make_unique<GnbCliCommand>(GnbCliCommand::UE_LIST);
    }
    else if (subCmd == "ue-count")
    {
        return std::make_unique<GnbCliCommand>(GnbCliCommand::UE_COUNT);
    }

    return nullptr;
}

std::unique_ptr<UeCliCommand> ParseUeCliCommand(std::vector<std::string> &&tokens, std::string &error,
                                                std::string &output)
{
    if (tokens.empty())
    {
        error = "Empty command";
        return nullptr;
    }

    std::string &subCmd = tokens[0];

    if (subCmd == "commands")
    {
        output = DumpCommands(g_ueCmdToDescription);
        return nullptr;
    }

    if (g_ueCmdToDescription.count(subCmd) == 0 || g_ueCmdToUsage.count(subCmd) == 0 ||
        g_ueCmdToHelpIfEmpty.count(subCmd) == 0)
    {
        error = "Command not recognized: " + subCmd;
        return nullptr;
    }

    opt::OptionsDescription desc = g_ueCmdToDescFunc[subCmd](subCmd, g_ueCmdToDescription[subCmd],
                                                             g_ueCmdToUsage[subCmd], g_ueCmdToHelpIfEmpty[subCmd]);

    OptionsHandler handler{};

    opt::OptionsResult options{tokens, desc, &handler};

    error = handler.m_err.str();
    output = handler.m_output.str();
    utils::Trim(error);
    utils::Trim(output);

    if (!error.empty() || !output.empty())
        return nullptr;

    if (subCmd == "info")
    {
        return std::make_unique<UeCliCommand>(UeCliCommand::INFO);
    }
    else if (subCmd == "status")
    {
        return std::make_unique<UeCliCommand>(UeCliCommand::STATUS);
    }
    else if (subCmd == "timers")
    {
        return std::make_unique<UeCliCommand>(UeCliCommand::TIMERS);
    }
    else if (subCmd == "deregister")
    {
        auto cmd = std::make_unique<UeCliCommand>(UeCliCommand::DE_REGISTER);
        cmd->deregCause = EDeregCause::UNSPECIFIED;
        if (options.positionalCount() == 0)
            CMD_ERR("De-registration type is expected")
        if (options.positionalCount() > 1)
            CMD_ERR("Only one de-registration type is expected")
        auto type = options.getPositional(0);
        if (type == "switch-off")
            cmd->deregCause = EDeregCause::SWITCH_OFF;
        else if (type == "disable-5g")
            cmd->deregCause = EDeregCause::DISABLE_5G;
        else if (type == "remove-sim")
            cmd->deregCause = EDeregCause::USIM_REMOVAL;
        else if (type != "normal")
            CMD_ERR("Invalid de-registration type, possible values are: \"normal\", \"disable-5g\", \"switch-off\", "
                    "\"remove-sim\"")
        return cmd;
    }
    else if (subCmd == "ps-release")
    {
        auto cmd = std::make_unique<UeCliCommand>(UeCliCommand::PS_RELEASE);
        if (options.positionalCount() == 0)
            CMD_ERR("At least one PDU session ID is expected")
        if (options.positionalCount() > 15)
            CMD_ERR("Too many PDU session IDs")
        cmd->psCount = options.positionalCount();
        for (int i = 0; i < cmd->psCount; i++)
        {
            int n = 0;
            if (!utils::TryParseInt(options.getPositional(i), n))
                CMD_ERR("Invalid PDU session ID value")
            if (n <= 0)
                CMD_ERR("PDU session IDs must be positive integer")
            if (n > 15)
                CMD_ERR("PDU session IDs cannot be greater than 15")
            cmd->psIds[i] = static_cast<int8_t>(n);
        }
        return cmd;
    }
    else if (subCmd == "ps-release-all")
    {
        return std::make_unique<UeCliCommand>(UeCliCommand::PS_RELEASE_ALL);
    }

    return nullptr;
}

} // namespace app
