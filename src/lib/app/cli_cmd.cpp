//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "cli_cmd.hpp"

#include <optional>
#include <sstream>
#include <utility>

#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/options.hpp>
#include <utils/ordered_map.hpp>

#define CMD_ERR(x)                                                                                                     \
    {                                                                                                                  \
        error = x;                                                                                                     \
        return nullptr;                                                                                                \
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

struct CmdEntry
{
    using DescType = opt::OptionsDescription (*)(const std::string &, const CmdEntry &);

    std::string descriptionText;
    std::string usageText;
    DescType descriptionFunc;
    bool helpIfEmpty;

    CmdEntry() = delete;

    CmdEntry(std::string descriptionText, std::string usageText, DescType descriptionFunc, bool helpIfEmpty)
        : descriptionText(std::move(descriptionText)), usageText(std::move(usageText)),
          descriptionFunc(descriptionFunc), helpIfEmpty(helpIfEmpty)
    {
    }
};

static std::string DumpCommands(const OrderedMap<std::string, CmdEntry> &entryTable)
{
    size_t maxLength = 0;
    for (auto &item : entryTable)
        maxLength = std::max(maxLength, item.size());

    std::stringstream ss{};
    for (auto &item : entryTable)
        ss << item << std::string(maxLength - item.size(), ' ') << " | " << entryTable[item].descriptionText << "\n";
    std::string output = ss.str();

    utils::Trim(output);
    return output;
}

static std::optional<opt::OptionsResult> ParseCliCommandCommon(OrderedMap<std::string, CmdEntry> &cmdEntries,
                                                               std::vector<std::string> &&tokens, std::string &error,
                                                               std::string &output, std::string &subCmd)
{
    if (tokens.empty())
    {
        error = "Empty command";
        return std::nullopt;
    }

    subCmd = tokens[0];

    if (subCmd == "commands")
    {
        output = DumpCommands(cmdEntries);
        return std::nullopt;
    }

    if (cmdEntries.count(subCmd) == 0)
    {
        error = "Command not recognized: " + subCmd;
        return std::nullopt;
    }

    opt::OptionsDescription desc = cmdEntries[subCmd].descriptionFunc(subCmd, cmdEntries[subCmd]);

    OptionsHandler handler{};

    opt::OptionsResult options{tokens, desc, &handler};

    error = handler.m_err.str();
    output = handler.m_output.str();
    utils::Trim(error);
    utils::Trim(output);

    if (!error.empty() || !output.empty())
        return {};

    return options;
}

//======================================================================================================
//                                      IMPLEMENTATION
//======================================================================================================

static opt::OptionsDescription DefaultDesc(const std::string &subCommand, const CmdEntry &entry)
{
    return {{}, {}, entry.descriptionText, {}, subCommand, {entry.usageText}, {}, entry.helpIfEmpty, true};
}

static opt::OptionsDescription DescForPsEstablish(const std::string &subCommand, const CmdEntry &entry)
{
    std::string example1 = "IPv4 --sst 1 --sd 1 --dnn internet";
    std::string example2 = "IPv4 --emergency";

    auto res = opt::OptionsDescription{
        {},  {}, entry.descriptionText, {}, subCommand, {entry.usageText}, {example1, example2}, entry.helpIfEmpty,
        true};

    res.items.emplace_back(std::nullopt, "sst", "SST value of the PDU session", "value");
    res.items.emplace_back(std::nullopt, "sd", "SD value of the PDU session", "value");
    res.items.emplace_back('n', "dnn", "DNN/APN value of the PDU session", "apn");
    res.items.emplace_back('e', "emergency", "Request as an emergency session", std::nullopt);

    return res;
}

namespace app
{

static OrderedMap<std::string, CmdEntry> g_gnbCmdEntries = {
    {"info", {"Show some information about the gNB", "", DefaultDesc, false}},
    {"status", {"Show some status information about the gNB", "", DefaultDesc, false}},
    {"amf-list", {"List all AMFs associated with the gNB", "", DefaultDesc, false}},
    {"amf-info", {"Show some status information about the given AMF", "<amf-id>", DefaultDesc, true}},
    {"ue-list", {"List all UEs associated with the gNB", "", DefaultDesc, false}},
    {"ue-count", {"Print the total number of UEs connected the this gNB", "", DefaultDesc, false}},
    {"ue-release", {"Request a UE context release for the given UE", "<ue-id>", DefaultDesc, false}},
};

static OrderedMap<std::string, CmdEntry> g_ueCmdEntries = {
    {"info", {"Show some information about the UE", "", DefaultDesc, false}},
    {"status", {"Show some status information about the UE", "", DefaultDesc, false}},
    {"timers", {"Dump current status of the timers in the UE", "", DefaultDesc, false}},
    {"rls-state", {"Show status information about RLS", "", DefaultDesc, false}},
    {"coverage", {"Dump available cells and PLMNs in the coverage", "", DefaultDesc, false}},
    {"ps-establish",
     {"Trigger a PDU session establishment procedure", "<session-type> [options]", DescForPsEstablish, true}},
    {"ps-list", {"List all PDU sessions", "", DefaultDesc, false}},
    {"ps-release", {"Trigger a PDU session release procedure", "<pdu-session-id>...", DefaultDesc, true}},
    {"ps-release-all", {"Trigger PDU session release procedures for all active sessions", "", DefaultDesc, false}},
    {"deregister",
     {"Perform a de-registration by the UE", "<normal|disable-5g|switch-off|remove-sim>", DefaultDesc, true}},
};

static std::unique_ptr<GnbCliCommand> GnbCliParseImpl(const std::string &subCmd, const opt::OptionsResult &options,
                                                      std::string &error)
{
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
    else if (subCmd == "ue-release")
    {
        auto cmd = std::make_unique<GnbCliCommand>(GnbCliCommand::UE_RELEASE_REQ);
        if (options.positionalCount() == 0)
            CMD_ERR("UE ID is expected")
        if (options.positionalCount() > 1)
            CMD_ERR("Only one UE ID is expected")
        cmd->ueId = utils::ParseInt(options.getPositional(0));
        if (cmd->ueId <= 0)
            CMD_ERR("Invalid UE ID")
        return cmd;
    }

    return nullptr;
}

static std::unique_ptr<UeCliCommand> UeCliParseImpl(const std::string &subCmd, const opt::OptionsResult &options,
                                                    std::string &error)
{
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
        if (options.positionalCount() == 0)
            CMD_ERR("De-registration type is expected")
        if (options.positionalCount() > 1)
            CMD_ERR("Only one de-registration type is expected")
        auto type = options.getPositional(0);
        if (type == "normal")
            cmd->deregCause = EDeregCause::NORMAL;
        else if (type == "switch-off")
            cmd->deregCause = EDeregCause::SWITCH_OFF;
        else if (type == "disable-5g")
            cmd->deregCause = EDeregCause::DISABLE_5G;
        else if (type == "remove-sim")
            cmd->deregCause = EDeregCause::USIM_REMOVAL;
        else
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
    else if (subCmd == "ps-establish")
    {
        auto cmd = std::make_unique<UeCliCommand>(UeCliCommand::PS_ESTABLISH);
        if (options.positionalCount() == 0)
            CMD_ERR("PDU session type is expected")
        if (options.positionalCount() > 15)
            CMD_ERR("Only one PDU session type is expected")
        std::string type = options.getPositional(0);
        if (type != "IPv4" && type != "ipv4" && type != "IPV4" && type != "Ipv4" && type != "IpV4")
            CMD_ERR("Only IPv4 is supported for now")
        cmd->isEmergency = options.hasFlag('e', "emergency");
        if (cmd->isEmergency)
        {
            if (options.hasFlag(std::nullopt, "sst") || options.hasFlag(std::nullopt, "sd") ||
                options.hasFlag('n', "dnn"))
                CMD_ERR("SST, SD, and DNN parameters cannot be used for emergency PDU sessions")
        }
        if (options.hasFlag('n', "dnn"))
            cmd->apn = options.getOption('n', "dnn");
        if (options.hasFlag(std::nullopt, "sd") && !options.hasFlag(std::nullopt, "sst"))
            CMD_ERR("SST is also required in case of an SD is provided")
        if (options.hasFlag(std::nullopt, "sst"))
        {
            int n = 0;
            if (!utils::TryParseInt(options.getOption(std::nullopt, "sst"), n) || n <= 0 || n >= 256)
                CMD_ERR("Invalid SST value")
            cmd->sNssai = SingleSlice{};
            cmd->sNssai->sst = static_cast<uint8_t>(n);

            if (options.hasFlag(std::nullopt, "sd"))
            {
                if (!utils::TryParseInt(options.getOption(std::nullopt, "sd"), n) || n <= 0 || n > 0xFFFFFF)
                    CMD_ERR("Invalid SD value")
                cmd->sNssai->sd = octet3{n};
            }
        }
        return cmd;
    }
    else if (subCmd == "ps-list")
    {
        return std::make_unique<UeCliCommand>(UeCliCommand::PS_LIST);
    }
    else if (subCmd == "rls-state")
    {
        return std::make_unique<UeCliCommand>(UeCliCommand::RLS_STATE);
    }
    else if (subCmd == "coverage")
    {
        return std::make_unique<UeCliCommand>(UeCliCommand::COVERAGE);
    }

    return nullptr;
}

std::unique_ptr<GnbCliCommand> ParseGnbCliCommand(std::vector<std::string> &&tokens, std::string &error,
                                                  std::string &output)
{
    std::string subCmd{};
    auto options = ParseCliCommandCommon(g_gnbCmdEntries, std::move(tokens), error, output, subCmd);
    if (options.has_value())
        return GnbCliParseImpl(subCmd, *options, error);
    return nullptr;
}

std::unique_ptr<UeCliCommand> ParseUeCliCommand(std::vector<std::string> &&tokens, std::string &error,
                                                std::string &output)
{
    std::string subCmd{};
    auto options = ParseCliCommandCommon(g_ueCmdEntries, std::move(tokens), error, output, subCmd);
    if (options.has_value())
        return UeCliParseImpl(subCmd, *options, error);
    return nullptr;
}

} // namespace app
