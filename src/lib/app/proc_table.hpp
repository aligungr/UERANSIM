//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <string>
#include <unordered_map>
#include <vector>

#include <utils/concurrent_map.hpp>

namespace app
{

struct ProcTableEntry
{
    int major{};
    int minor{};
    int patch{};
    int pid{};
    uint16_t port{};
    std::vector<std::string> nodes{};

    // ... (New members must always be added to the end)

    static std::string Encode(const ProcTableEntry &e);
    static ProcTableEntry Decode(const std::string &s);
};

void CreateProcTable(const std::vector<std::string> &nodes, int cmdPort);

template <typename T>
inline void CreateProcTable(const std::unordered_map<std::string, T> &nodeMap, int cmdPort)
{
    std::vector<std::string> nodes{};
    for (auto &node : nodeMap)
        nodes.push_back(node.first);
    CreateProcTable(nodes, cmdPort);
}

template <typename T>
inline void CreateProcTable(const ConcurrentMap<std::string, T> &nodeMap, int cmdPort)
{
    std::vector<std::string> nodes{};
    nodeMap.invokeForeach([&nodes](const auto &node) { nodes.push_back(node.first); });
    CreateProcTable(nodes, cmdPort);
}

} // namespace app
