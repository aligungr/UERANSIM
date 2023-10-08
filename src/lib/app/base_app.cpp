//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "base_app.hpp"

#include <atomic>
#include <csignal>
#include <cstdio>
#include <cstdlib>
#include <ctime>
#include <exception>
#include <vector>

static std::atomic_int g_instanceCount{};
static std::vector<void (*)()> g_runAtExit{};
static std::vector<std::string> g_deleteAtExit{};

extern "C" void BaseSignalHandler(int num)
{
    for (auto &fun : g_runAtExit)
        fun();
    for (auto &file : g_deleteAtExit)
        std::remove(file.c_str());

    if (num == SIGTERM || num == SIGINT)
        exit(0);
}

namespace app
{

void Initialize()
{
    if (g_instanceCount++ != 0)
        std::terminate();

    srand(time(nullptr));

    std::signal(SIGTERM, BaseSignalHandler);
    std::signal(SIGINT, BaseSignalHandler);
}

void RunAtExit(void (*fun)())
{
    g_runAtExit.push_back(fun);
}

void DeleteAtExit(const std::string &file)
{
    g_deleteAtExit.push_back(file);
}

} // namespace app
