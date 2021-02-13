//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <algorithm>
#include <app/node_cli.hpp>
#include <app/proc_table.hpp>
#include <filesystem>
#include <iostream>
#include <set>
#include <string>
#include <unordered_map>
#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/io.hpp>
#include <utils/network.hpp>
#include <utils/options.hpp>
#include <vector>

static struct Options
{
    bool dumpNodes{};
    std::string nodeName{};
    std::string directCmd{};
} g_options{};

static std::set<int> FindProcesses()
{
    std::set<int> res{};
    for (const auto &file : std::filesystem::directory_iterator(cons::PROCESS_DIR))
    {
        if (file.is_directory())
        {
            auto name = file.path().stem().string();
            if (!utils::IsNumeric(name))
                continue;
            int pid = utils::ParseInt(name);
            res.insert(pid);
        }
    }
    return res;
}

static uint16_t DiscoverNode(const std::string &node)
{
    if (!io::Exists(cons::PROC_TABLE_DIR))
        return 0;

    // Find all processes in the environment
    auto processes = FindProcesses();

    // Read and parse ProcTable entries.
    std::unordered_map<std::string, app::ProcTableEntry> entries{};
    for (const auto &file : std::filesystem::directory_iterator(cons::PROC_TABLE_DIR))
    {
        if (file.is_directory())
            continue;
        std::string path = file.path().string();
        std::string content = io::ReadAllText(path);
        entries[file.path()] = app::ProcTableEntry::Decode(content);
    }

    for (auto &e : entries)
    {
        // If no such process, it means that this ProcTable file is outdated
        // Therefore that file should be deleted
        if (processes.count(e.second.pid) == 0)
        {
            io::Remove(e.first);
            continue;
        }

        // If searching node exists in this file, extract port number from it.
        for (auto &n : e.second.nodes)
        {
            if (n == node) // TODO: version checking
                return e.second.port;
        }
    }

    // No process found for this node name
    return 0;
}

static std::vector<std::string> DumpNames()
{
    std::vector<std::string> v{};

    if (!io::Exists(cons::PROC_TABLE_DIR))
        return v;

    // Find all processes in the environment
    auto processes = FindProcesses();

    // Read and parse ProcTable entries.
    std::unordered_map<std::string, app::ProcTableEntry> entries{};
    for (const auto &file : std::filesystem::directory_iterator(cons::PROC_TABLE_DIR))
    {
        if (file.is_directory())
            continue;
        std::string path = file.path().string();
        std::string content = io::ReadAllText(path);
        entries[file.path()] = app::ProcTableEntry::Decode(content);
    }

    for (auto &e : entries)
    {
        // If no such process, it means that this ProcTable file is outdated
        // Therefore that file should be deleted
        if (processes.count(e.second.pid) == 0)
        {
            io::Remove(e.first);
            continue;
        }

        for (auto &n : e.second.nodes)
            v.push_back(n);
    }

    std::sort(v.begin(), v.end());
    return v;
}

static void ReadOptions(int argc, char **argv)
{
    opt::OptionsDescription desc{"UERANSIM",   cons::Tag, "Command Line Interface",
                                 "ALİ GÜNGÖR", "nr-cli",  {"<node-name> [option...]", "--dump"}};

    opt::OptionItem itemDump = {'d', "dump", "List all UE and gNBs in the environment", std::nullopt};
    opt::OptionItem itemExec = {'e', "exec", "Execute the given command directly without an interactive shell",
                                "command"};

    desc.items.push_back(itemDump);
    desc.items.push_back(itemExec);

    opt::OptionsResult opt{argc, argv, desc};

    g_options.dumpNodes = opt.hasFlag(itemDump);

    if (!g_options.dumpNodes)
    {
        if (opt.positionalCount() == 0)
            opt.error("Node name is expected");
        if (opt.positionalCount() > 1)
            opt.error("Only one node name is expected");

        g_options.nodeName = opt.getPositional(0);
        if (g_options.nodeName.size() < cons::MinNodeName)
            opt.error("Node name is too short");
        if (g_options.nodeName.size() > cons::MaxNodeName)
            opt.error("Node name is too long");

        g_options.directCmd = opt.getOption(itemExec);
        if (opt.hasFlag(itemExec) && g_options.directCmd.size() < 3)
            opt.error("Command is too short");
    }
}

static bool HandleMessage(const app::CliMessage &msg, bool isOneShot)
{
    if (msg.type == app::CliMessage::Type::ERROR)
    {
        std::cerr << "ERROR: " << msg.value << std::endl;
        if (isOneShot)
            exit(1);
        return true;
    }

    if (msg.type == app::CliMessage::Type::ECHO)
    {
        std::cout << msg.value << std::endl;
        return true;
    }

    if (msg.type == app::CliMessage::Type::RESULT)
    {
        std::cout << msg.value << std::endl;
        if (isOneShot)
            exit(0);
        return true;
    }

    return false;
}

[[noreturn]] static void SendCommand(uint16_t port)
{
    app::CliServer server{};

    if (g_options.directCmd.empty())
    {
        while (true)
        {
            std::string line{};
            std::vector<std::string> tokens{};
            if (!opt::ReadLine(std::cin, std::cout, line, tokens))
                exit(0);

            server.sendMessage(
                app::CliMessage::Command(InetAddress{cons::CMD_SERVER_IP, port}, line, g_options.nodeName));

            while (!HandleMessage(server.receiveMessage(), false))
            {
                // empty
            }
        }
    }
    else
    {
        server.sendMessage(
            app::CliMessage::Command(InetAddress{cons::CMD_SERVER_IP, port}, g_options.directCmd, g_options.nodeName));

        while (true)
            HandleMessage(server.receiveMessage(), true);
    }
}

int main(int argc, char **argv)
{
    ReadOptions(argc, argv);

    // NOTE: This does not guarantee showing the exact realtime status.
    if (g_options.dumpNodes)
    {
        for (auto &n : DumpNames())
            std::cout << n << "\n";
        std::cout.flush();
        exit(0);
    }

    if (g_options.nodeName.empty())
    {
        std::cerr << "ERROR: No node name is specified" << std::endl;
        exit(1);
    }

    if (g_options.nodeName.size() > cons::MaxNodeName)
    {
        std::cerr << "ERROR: Node name is too long" << std::endl;
        exit(1);
    }

    if (g_options.nodeName.size() < cons::MinNodeName)
    {
        std::cerr << "ERROR: Node name is too short" << std::endl;
        exit(1);
    }

    uint16_t cmdPort;

    try
    {
        cmdPort = DiscoverNode(g_options.nodeName);
    }
    catch (const std::runtime_error &e)
    {
        throw std::runtime_error("Node discovery failure: " + std::string{e.what()});
    }

    if (cmdPort == 0)
    {
        std::cerr << "ERROR: No node found with name: " << g_options.nodeName << std::endl;
        return 1;
    }

    SendCommand(cmdPort);
    return 0;
}