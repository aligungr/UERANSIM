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
    std::stringstream ss{};
    for (auto &item : descTable)
        ss << item.first << " | " << item.second << "\n";
    std::string output = ss.str();
    utils::Trim(output);
    return output;
}

namespace app
{

static std::map<std::string, std::string> g_gnbCmdToDescription = {
    {"status", "Show some status information about the gNB"},
    {"amf-list", "List all AMFs associated with the gNB"},
    {"amf-status", "Show some status information about the given AMF"},
    {"info", "Show some information about the gNB"},
};

static std::map<std::string, std::string> g_gnbCmdToUsage = {
    {"status", "[option...]"},
    {"amf-list", "[option...]"},
    {"amf-status", "<amf-id> [option...]"},
    {"info", "[option...]"},
};

static std::map<std::string, bool> g_gnbCmdToHelpIfEmpty = {
    {"status", false},
    {"amf-list", false},
    {"amf-status", true},
    {"info", false},
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
    else if (subCmd == "amf-status")
    {
        auto cmd = std::make_unique<GnbCliCommand>(GnbCliCommand::AMF_STATUS);
        if (options.positionalCount() == 0)
            CMD_ERR("AMF ID is expected")
        if (options.positionalCount() > 1)
            CMD_ERR("Only one AMF ID is expected")
        cmd->amfId = utils::ParseInt(options.getPositional(0));
        if (cmd->amfId <= 0)
            CMD_ERR("Invalid AMF ID")
        return cmd;
    }

    return nullptr;
}

} // namespace app