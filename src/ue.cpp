//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <app/base_app.hpp>
#include <app/cli_base.hpp>
#include <app/cli_cmd.hpp>
#include <app/proc_table.hpp>
#include <iostream>
#include <ue/ue.hpp>
#include <unistd.h>
#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/options.hpp>
#include <utils/yaml_utils.hpp>
#include <yaml-cpp/yaml.h>

static app::CliServer *g_cliServer = nullptr;
nr::ue::UeConfig *g_refConfig = nullptr;
static std::unordered_map<std::string, nr::ue::UserEquipment *> g_ueMap{};
static app::CliResponseTask *g_cliRespTask = nullptr;

static struct Options
{
    std::string configFile{};
    bool noRoutingConfigs{};
    bool disableCmd{};
    std::string imsi{};
    int count{};
} g_options{};

static nr::ue::UeConfig *ReadConfigYaml()
{
    auto *result = new nr::ue::UeConfig();
    auto config = YAML::LoadFile(g_options.configFile);

    result->plmn.mcc = yaml::GetInt32(config, "mcc", 1, 999);
    yaml::GetString(config, "mcc", 3, 3);
    result->plmn.mnc = yaml::GetInt32(config, "mnc", 0, 999);
    result->plmn.isLongMnc = yaml::GetString(config, "mnc", 2, 3).size() == 3;

    for (auto &gnbSearchItem : yaml::GetSequence(config, "gnbSearchList"))
        result->gnbSearchList.push_back(gnbSearchItem.as<std::string>());

    for (auto &nssai : yaml::GetSequence(config, "slices"))
    {
        SliceSupport s{};
        s.sst = yaml::GetInt32(nssai, "sst", 1, 0xFF);
        if (yaml::HasField(nssai, "sd"))
            s.sd = octet3{yaml::GetInt32(nssai, "sd", 1, 0xFFFFFF)};
        result->nssais.push_back(s);
    }

    result->key = OctetString::FromHex(yaml::GetString(config, "key", 32, 32));
    result->opC = OctetString::FromHex(yaml::GetString(config, "op", 32, 32));
    result->amf = OctetString::FromHex(yaml::GetString(config, "amf", 4, 4));

    result->emulationMode = true;
    result->configureRouting = !g_options.noRoutingConfigs;

    // If we have multiple UEs in the same process, then log names should be separated.
    result->prefixLogger = g_options.count > 1;

    if (yaml::HasField(config, "supi"))
        result->supi = Supi::Parse(yaml::GetString(config, "supi"));
    if (yaml::HasField(config, "imei"))
        result->imei = yaml::GetString(config, "imei", 15, 15);
    if (yaml::HasField(config, "imeiSv"))
        result->imeiSv = yaml::GetString(config, "imeiSv", 16, 16);

    yaml::AssertHasField(config, "integrity");
    yaml::AssertHasField(config, "ciphering");

    result->supportedAlgs.nia1 = yaml::GetBool(config["integrity"], "IA1");
    result->supportedAlgs.nia2 = yaml::GetBool(config["integrity"], "IA2");
    result->supportedAlgs.nia3 = yaml::GetBool(config["integrity"], "IA3");
    result->supportedAlgs.nea1 = yaml::GetBool(config["ciphering"], "EA1");
    result->supportedAlgs.nea2 = yaml::GetBool(config["ciphering"], "EA2");
    result->supportedAlgs.nea3 = yaml::GetBool(config["ciphering"], "EA3");

    std::string opType = yaml::GetString(config, "opType");
    if (opType == "OP")
        result->opType = nr::ue::OpType::OP;
    else if (opType == "OPC")
        result->opType = nr::ue::OpType::OPC;
    else
        throw std::runtime_error("Invalid OP type: " + opType);

    if (yaml::HasField(config, "sessions"))
    {
        for (auto &sess : yaml::GetSequence(config, "sessions"))
        {
            nr::ue::SessionConfig s{};

            if (yaml::HasField(sess, "apn"))
                s.apn = yaml::GetString(sess, "apn");
            if (yaml::HasField(sess, "slice"))
            {
                auto slice = sess["slice"];
                s.sNssai = SliceSupport{};
                s.sNssai->sst = yaml::GetInt32(slice, "sst", 1, 0xFF);
                if (yaml::HasField(slice, "sd"))
                    s.sNssai->sd = octet3{yaml::GetInt32(slice, "sd", 1, 0xFFFFFF)};
            }

            std::string type = yaml::GetString(sess, "type");
            if (type == "IPv4")
                s.type = nas::EPduSessionType::IPV4;
            else if (type == "IPv6")
                s.type = nas::EPduSessionType::IPV6;
            else if (type == "IPv4v6")
                s.type = nas::EPduSessionType::IPV4V6;
            else if (type == "Ethernet")
                s.type = nas::EPduSessionType::ETHERNET;
            else if (type == "Unstructured")
                s.type = nas::EPduSessionType::UNSTRUCTURED;
            else
                throw std::runtime_error("Invalid PDU session type: " + type);

            result->initSessions.push_back(s);
        }
    }

    return result;
}

static void ReadOptions(int argc, char **argv)
{
    opt::OptionsDescription desc{cons::Project, cons::Tag, "5G-SA UE implementation",
                                 cons::Owner,   "nr-ue",   {"-c <config-file> [option...]"},
                                 true};

    opt::OptionItem itemConfigFile = {'c', "config", "Use specified configuration file for UE", "config-file"};
    opt::OptionItem itemImsi = {'i', "imsi", "Use specified IMSI number instead of provided one", "imsi"};
    opt::OptionItem itemCount = {'n', "num-of-UE", "Generate specified number of UEs starting from the given IMSI",
                                 "num"};
    opt::OptionItem itemDisableCmd = {'l', "disable-cmd", "Disable command line functionality for this instance",
                                      std::nullopt};
    opt::OptionItem itemDisableRouting = {'r', "no-routing-config",
                                          "Do not auto configure routing for UE TUN interface", std::nullopt};

    desc.items.push_back(itemConfigFile);
    desc.items.push_back(itemImsi);
    desc.items.push_back(itemCount);
    desc.items.push_back(itemDisableCmd);
    desc.items.push_back(itemDisableRouting);

    opt::OptionsResult opt{argc, argv, desc, false, nullptr};

    g_options.configFile = opt.getOption(itemConfigFile);
    g_options.noRoutingConfigs = opt.hasFlag(itemDisableRouting);
    if (opt.hasFlag(itemCount))
    {
        g_options.count = utils::ParseInt(opt.getOption(itemCount));
        if (g_options.count <= 0)
            throw std::runtime_error("Invalid number of UEs");
        if (g_options.count > 512)
            throw std::runtime_error("Number of UEs is too big");
    }
    else
    {
        g_options.count = 1;
    }

    g_options.imsi = {};
    if (opt.hasFlag(itemImsi))
    {
        g_options.imsi = opt.getOption(itemImsi);
        Supi::Parse("imsi-" + g_options.imsi); // validate the string by parsing
    }

    g_options.disableCmd = opt.hasFlag(itemDisableCmd);
}

static std::string LargeSum(std::string a, std::string b)
{
    if (a.length() > b.length())
        std::swap(a, b);

    std::string str;
    int n1 = a.length(), n2 = b.length();

    reverse(a.begin(), a.end());
    reverse(b.begin(), b.end());

    int carry = 0;
    for (int i = 0; i < n1; i++)
    {
        int sum = ((a[i] - '0') + (b[i] - '0') + carry);
        str.push_back(sum % 10 + '0');
        carry = sum / 10;
    }
    for (int i = n1; i < n2; i++)
    {
        int sum = ((b[i] - '0') + carry);
        str.push_back(sum % 10 + '0');
        carry = sum / 10;
    }
    if (carry)
        throw std::runtime_error("UE serial number overflow");
    reverse(str.begin(), str.end());
    return str;
}

static void IncrementNumber(std::string &s, int delta)
{
    s = LargeSum(s, std::to_string(delta));
}

static nr::ue::UeConfig *GetConfigByUe(int ueIndex)
{
    auto *c = new nr::ue::UeConfig();
    c->emulationMode = g_refConfig->emulationMode;
    c->key = g_refConfig->key.copy();
    c->opC = g_refConfig->opC.copy();
    c->opType = g_refConfig->opType;
    c->amf = g_refConfig->amf.copy();
    c->imei = g_refConfig->imei;
    c->imeiSv = g_refConfig->imeiSv;
    c->supi = g_refConfig->supi;
    c->plmn = g_refConfig->plmn;
    c->nssais = g_refConfig->nssais;
    c->supportedAlgs = g_refConfig->supportedAlgs;
    c->gnbSearchList = g_refConfig->gnbSearchList;
    c->initSessions = g_refConfig->initSessions;
    c->configureRouting = g_refConfig->configureRouting;
    c->prefixLogger = g_refConfig->prefixLogger;

    if (c->supi.has_value())
        IncrementNumber(c->supi->value, ueIndex);
    if (c->imei.has_value())
        IncrementNumber(*c->imei, ueIndex);
    if (c->imeiSv.has_value())
        IncrementNumber(*c->imeiSv, ueIndex);

    return c;
}

static void ReceiveCommand(app::CliMessage &msg)
{
    if (msg.value.empty())
    {
        g_cliServer->sendMessage(app::CliMessage::Result(msg.clientAddr, ""));
        return;
    }

    std::vector<std::string> tokens{};

    auto exp = opt::PerformExpansion(msg.value, tokens);
    if (exp != opt::ExpansionResult::SUCCESS)
    {
        g_cliServer->sendMessage(app::CliMessage::Error(msg.clientAddr, "Invalid command: " + msg.value));
        return;
    }

    if (tokens.empty())
    {
        g_cliServer->sendMessage(app::CliMessage::Error(msg.clientAddr, "Empty command"));
        return;
    }

    std::string error{}, output{};
    auto cmd = app::ParseUeCliCommand(std::move(tokens), error, output);
    if (!error.empty())
    {
        g_cliServer->sendMessage(app::CliMessage::Error(msg.clientAddr, error));
        return;
    }
    if (!output.empty())
    {
        g_cliServer->sendMessage(app::CliMessage::Result(msg.clientAddr, output));
        return;
    }
    if (cmd == nullptr)
    {
        g_cliServer->sendMessage(app::CliMessage::Error(msg.clientAddr, ""));
        return;
    }

    if (g_ueMap.count(msg.nodeName) == 0)
    {
        g_cliServer->sendMessage(app::CliMessage::Error(msg.clientAddr, "Node not found: " + msg.nodeName));
        return;
    }

    auto *ue = g_ueMap[msg.nodeName];
    ue->pushCommand(std::move(cmd), msg.clientAddr, g_cliRespTask);
}

static void Loop()
{
    if (!g_cliServer)
    {
        ::pause();
        return;
    }

    auto msg = g_cliServer->receiveMessage();
    if (msg.type == app::CliMessage::Type::ECHO)
    {
        g_cliServer->sendMessage(msg);
        return;
    }

    if (msg.type != app::CliMessage::Type::COMMAND)
        return;

    if (msg.value.size() > 0xFFFF)
    {
        g_cliServer->sendMessage(app::CliMessage::Error(msg.clientAddr, "Command is too large"));
        return;
    }

    if (msg.nodeName.size() > 0xFFFF)
    {
        g_cliServer->sendMessage(app::CliMessage::Error(msg.clientAddr, "Node name is too large"));
        return;
    }

    ReceiveCommand(msg);
}

int main(int argc, char **argv)
{
    app::Initialize();

    try
    {
        ReadOptions(argc, argv);
        g_refConfig = ReadConfigYaml();
        if (g_options.imsi.length() > 0)
            g_refConfig->supi = Supi::Parse("imsi-" + g_options.imsi);
    }
    catch (const std::runtime_error &e)
    {
        std::cerr << "ERROR: " << e.what() << std::endl;
        return 1;
    }

    std::cout << cons::Name << std::endl;

    for (int i = 0; i < g_options.count; i++)
    {
        auto *config = GetConfigByUe(i);
        auto *ue = new nr::ue::UserEquipment(config, nullptr);
        g_ueMap[config->getNodeName()] = ue;
    }

    if (!g_options.disableCmd)
    {
        g_cliServer = new app::CliServer{};
        app::CreateProcTable(g_ueMap, g_cliServer->assignedAddress().getPort());

        g_cliRespTask = new app::CliResponseTask(g_cliServer);
        g_cliRespTask->start();
    }

    for (auto &ue : g_ueMap)
        ue.second->start();

    while (true)
        Loop();
}