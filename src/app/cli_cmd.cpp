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

#define CMD_ERR(x)                                                                                                     \
    {                                                                                                                  \
        error = x;                                                                                                     \
        return nullptr;                                                                                                \
    }

static opt::OptionsDescription Desc(const std::string &subCommand, const std::string &desc, const std::string &usage,
                                    bool helpIfEmpty)
{
    return {subCommand, cons::Tag, desc, {}, subCommand, {usage}, helpIfEmpty};
}

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

static std::string DumpCommands(const std::map<std::string, std::string> &descTable)
{
    size_t maxLength = 0;
    for (auto &item : descTable)
        maxLength = std::max(maxLength, item.first.size());

    std::stringstream ss{};
    for (auto &item : descTable)
        ss << item.first << std::string(maxLength - item.first.size(), ' ') << " | " << item.second << "\n";
    std::string output = ss.str();
    utils::Trim(output);
    return output;
}

namespace app
{

static std::map<std::string, std::string> g_gnbCmdToDescription = {
    {"status", "Show some status information about the gNB"},
    {"info", "Show some information about the gNB"},
    {"amf-list", "List all AMFs associated with the gNB"},
    {"amf-info", "Show some status information about the given AMF"},
    {"ue-list", "List all UEs associated with the gNB"},
    {"ue-count", "Print the total number of UEs connected the this gNB"},
};

static std::map<std::string, std::string> g_gnbCmdToUsage = {
    {"status", "[option...]"},   {"info", "[option...]"},
    {"amf-list", "[option...]"}, {"amf-info", "<amf-id> [option...]"},
    {"ue-list", "[option...]"},  {"ue-count", "[option...]"},
};

static std::map<std::string, bool> g_gnbCmdToHelpIfEmpty = {{"status", false},   {"info", false},
                                                            {"amf-list", false}, {"amf-info", true},
                                                            {"ue-list", false},  {"ue-count", false}};

static std::map<std::string, std::string> g_ueCmdToDescription = {
    {"info", "Show some information about the UE"},
    {"status", "Show some status information about the UE"},
    {"timers", "Dump current status of the timers in the UE"},
};

static std::map<std::string, std::string> g_ueCmdToUsage = {
    {"info", "[option...]"},
    {"status", "[option...]"},
    {"timers", "[option...]"},
};

static std::map<std::string, bool> g_ueCmdToHelpIfEmpty = {
    {"info", false},
    {"status", false},
    {"timers", false},
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

    opt::OptionsDescription desc =
        Desc(subCmd, g_gnbCmdToDescription[subCmd], g_gnbCmdToUsage[subCmd], g_gnbCmdToHelpIfEmpty[subCmd]);

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

    opt::OptionsDescription desc =
        Desc(subCmd, g_ueCmdToDescription[subCmd], g_ueCmdToUsage[subCmd], g_ueCmdToHelpIfEmpty[subCmd]);

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

    return nullptr;
}

} // namespace app
