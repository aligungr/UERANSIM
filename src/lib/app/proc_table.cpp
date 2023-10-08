//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "proc_table.hpp"
#include "base_app.hpp"

#include <fstream>
#include <ostream>
#include <sstream>
#include <stdexcept>

#include <sys/types.h>
#include <unistd.h>

#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/io.hpp>
#include <utils/random.hpp>

namespace app
{

static std::string RandomFileName(const std::vector<std::string> &nodes, int cmdPort)
{
    std::string s;
    for (auto &node : nodes)
    {
        s += node;
        s += "|";
    }
    s += std::to_string(cmdPort);

    Random rnd = Random::Mixed(s);
    return utils::IntToHex(rnd.nextL()) + utils::IntToHex(rnd.nextL());
}

void CreateProcTable(const std::vector<std::string> &nodes, int cmdPort)
{
    try
    {
        io::CreateDirectory(cons::PROC_TABLE_DIR);

        std::string filePath;
        while (true)
        {
            std::string fileName = RandomFileName(nodes, cmdPort);
            filePath = cons::PROC_TABLE_DIR + fileName;

            if (!io::Exists(filePath))
                break;
        }

        ProcTableEntry entry{};
        entry.major = cons::Major;
        entry.minor = cons::Minor;
        entry.patch = cons::Patch;
        entry.pid = static_cast<int>(::getpid());
        entry.port = cmdPort;
        entry.nodes = nodes;

        io::WriteAllText(filePath, ProcTableEntry::Encode(entry));

        DeleteAtExit(filePath);
    }
    catch (const std::runtime_error &e)
    {
        throw std::runtime_error("ProcTable could not be created: " + std::string(e.what()));
    }
}

std::string ProcTableEntry::Encode(const ProcTableEntry &e)
{
    std::stringstream ss{""};
    ss << e.major << " ";
    ss << e.minor << " ";
    ss << e.patch << " ";
    ss << e.pid << " ";
    ss << e.port << " ";
    ss << e.nodes.size() << " ";
    for (auto &n : e.nodes)
    {
        utils::AssertNodeName(n);
        ss << n << " ";
    }
    return ss.str();
}

ProcTableEntry ProcTableEntry::Decode(const std::string &s)
{
    ProcTableEntry e{};
    std::stringstream ss{s};

    ss >> e.major;
    ss >> e.minor;
    ss >> e.patch;
    ss >> e.pid;
    ss >> e.port;

    size_t nodeSize = 0;
    ss >> nodeSize;
    for (size_t i = 0; i < nodeSize; i++)
    {
        std::string n{};
        ss >> n;
        e.nodes.push_back(std::move(n));
    }

    return e;
}

} // namespace app