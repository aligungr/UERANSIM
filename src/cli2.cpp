#include <cstdio>
#include <cstdlib>
#include <fstream>
#include <iostream>
#include <set>
#include <string>
#include <vector>

#include <lib/app/cli_base.hpp>
#include <utils/common.hpp>
#include <utils/constants.hpp>
#include <utils/io.hpp>
#include <utils/libc_error.hpp>
#include <utils/network.hpp>
#include <utils/octet_view.hpp>
#include <utils/options.hpp>
#include <utils/random.hpp>

#include <sys/socket.h>
#include <sys/un.h>
#include <unistd.h>

struct Options
{
    bool dumpNodes{};
    std::string nodeName{};
    std::string directCmd{};
};

static uint8_t g_receiveBuffer[8192];

static void ReadOptions(int argc, char **argv, Options &output)
{
    opt::OptionsDescription desc{"UERANSIM",  cons::Tag, "Command Line Interface",
                                 cons::Owner, "nr-cli",  {"<node-name> [option...]", "--dump"},
                                 {},          true,      false};

    opt::OptionItem itemDump = {'d', "dump", "List all UE and gNBs in the environment", std::nullopt};
    opt::OptionItem itemExec = {'e', "exec", "Execute the given command directly without an interactive shell",
                                "command"};

    desc.items.push_back(itemDump);
    desc.items.push_back(itemExec);

    opt::OptionsResult opt{argc, argv, desc, false, nullptr};

    output.dumpNodes = opt.hasFlag(itemDump);

    if (!output.dumpNodes)
    {
        if (opt.positionalCount() == 0)
        {
            opt.showError("Node name is expected");
            return;
        }
        if (opt.positionalCount() > 1)
        {
            opt.showError("Only one node name is expected");
            return;
        }

        output.nodeName = opt.getPositional(0);
        if (output.nodeName.size() < cons::MinNodeName)
        {
            opt.showError("Node name is too short");
            return;
        }
        if (output.nodeName.size() > cons::MaxNodeName)
        {
            opt.showError("Node name is too long");
            return;
        }

        output.directCmd = opt.getOption(itemExec);
        if (opt.hasFlag(itemExec) && output.directCmd.size() < 3)
        {
            opt.showError("Command is too short");
            return;
        }
    }
}

static std::set<std::string> DumpNames()
{
    std::set<std::string> v{};

    std::ifstream ifs;
    ifs.open("/proc/net/unix");
    if (!ifs.is_open() || !ifs.good())
        throw LibError("Could not open '/proc/net/unix'");

    std::string line;
    while (std::getline(ifs, line))
    {
        auto index = line.find(cons::CLI_SOCKET_DIR);
        if (index == std::string::npos)
            continue;

        index += std::string{cons::CLI_SOCKET_DIR}.length();

        std::string nodeName;

        auto lastIndex = line.find(' ', index + 1);
        if (lastIndex == std::string::npos)
            lastIndex = line.find('\n', index + 1);
        if (lastIndex == std::string::npos)
            nodeName = line.substr(index);
        else
            nodeName = line.substr(index, lastIndex - index);

        v.insert(nodeName);
    }

    ifs.close();

    return v;
}

static void CleanUnusedFiles(const std::set<std::string> &nodes)
{
    for (const auto &path : io::GetEntries(cons::CLI_SOCKET_DIR))
    {
        auto file = io::GetStem(path);
        if (!nodes.count(file))
            io::Remove(path);
    }
}

static void SendCommand(int fd, struct sockaddr_un *remoteAddress, const app::CliMessage &msg)
{
    OctetString stream;
    app::CliMessage::Encode(msg, stream);

    if (sendto(fd, stream.data(), static_cast<size_t>(stream.length()), 0, (const struct sockaddr *)remoteAddress,
               sizeof(sockaddr_un)) < 0) {
        puts("ERROR: Command could not send to the node");
        exit(1);
    }
}

static void ReceiveResponse(int fd, bool isOneShot)
{
    InetAddress outAddress;

    auto size = recvfrom(fd, g_receiveBuffer, sizeof(g_receiveBuffer), 0,
                         (struct sockaddr *)outAddress.getStorageAddr(), outAddress.getSockLenAddr());

    if (size < 0)
        throw LibError{"recvfrom failure"};

    OctetView v{g_receiveBuffer, static_cast<size_t>(size)};
    if (v.readI() != cons::Major)
        return;
    if (v.readI() != cons::Minor)
        return;
    if (v.readI() != cons::Patch)
        return;

    app::CliMessage msg{};
    msg.type = static_cast<app::CliMessage::Type>(v.readI());
    int nodeNameLength = v.read4I();
    msg.nodeName = v.readUtf8String(nodeNameLength);
    int valueLength = v.read4I();
    msg.value = v.readUtf8String(valueLength);
    msg.clientAddr = outAddress;

    if (msg.type == app::CliMessage::Type::ERROR)
    {
        std::cerr << "ERROR: " << msg.value << std::endl;
        if (isOneShot)
            exit(1);
    }

    if (msg.type == app::CliMessage::Type::ECHO)
    {
        std::cout << msg.value << std::endl;
    }

    if (msg.type == app::CliMessage::Type::RESULT)
    {
        std::cout << msg.value << std::endl;
        if (isOneShot)
            exit(0);
    }
}

static void SendCommands(const Options &opt, int fd, struct sockaddr_un *remoteAddress)
{
    if (!opt.directCmd.empty())
    {
        SendCommand(fd, remoteAddress, app::CliMessage::Command(InetAddress{}, opt.directCmd, opt.nodeName));
        ReceiveResponse(fd, true);
    }
    else
    {
        while (true)
        {
            std::cout << "\x1b[1m";
            std::cout << std::string(92, '-') << std::endl;
            std::string line{};
            bool isEof{};
            std::vector<std::string> tokens{};
            if (!opt::ReadLine(std::cin, std::cout, line, tokens, isEof))
            {
                if (isEof)
                    exit(0);
                else
                    std::cout << "ERROR: Invalid command" << std::endl;
            }
            std::cout << "\x1b[0m";
            if (line.empty())
                continue;

            SendCommand(fd, remoteAddress, app::CliMessage::Command(InetAddress{}, line, opt.nodeName));

            ReceiveResponse(fd, false);
        }
    }
}

int main(int argc, char **argv)
{
    Options opt;
    ReadOptions(argc, argv, opt);

    auto nodeNames = DumpNames();
    CleanUnusedFiles(nodeNames);

    if (opt.dumpNodes)
    {
        for (auto &n : nodeNames)
            std::cout << n << "\n";
        std::cout.flush();
        return 0;
    }

    if (opt.nodeName.empty())
    {
        std::cerr << "ERROR: No node name is specified" << std::endl;
        return 1;
    }

    if (opt.nodeName.size() > cons::MaxNodeName)
    {
        std::cerr << "ERROR: Node name is too long" << std::endl;
        return 1;
    }

    if (opt.nodeName.size() < cons::MinNodeName)
    {
        std::cerr << "ERROR: Node name is too short" << std::endl;
        return 1;
    }

    if (!nodeNames.count(opt.nodeName))
    {
        std::cerr << "No node exists with name: " << opt.nodeName << std::endl;
        return 1;
    }

    int fd = socket(AF_UNIX, SOCK_DGRAM, 0);
    if (fd < 0)
        throw LibError("Socket open failure");

    Random r;

    std::string mySocketName =
        cons::CLI_SOCKET_DIR + std::string{"cli-client."} + utils::IntToHex(r.nextL()) + utils::IntToHex(r.nextL());
    std::string remoteSocketName = cons::CLI_SOCKET_DIR + opt.nodeName;

    unlink(mySocketName.c_str());

    struct sockaddr_un myAddress = {};
    myAddress.sun_family = AF_UNIX;
    strncpy(myAddress.sun_path, mySocketName.c_str(), sizeof(myAddress.sun_path) - 1);

    struct sockaddr_un remoteAddress = {};
    remoteAddress.sun_family = AF_UNIX;
    strncpy(remoteAddress.sun_path, remoteSocketName.c_str(), sizeof(remoteAddress.sun_path) - 1);

    if (bind(fd, (struct sockaddr *)&myAddress, sizeof(myAddress)) < 0)
        throw LibError("Socket bind failure");

    SendCommands(opt, fd, &remoteAddress);
    return 0;
}