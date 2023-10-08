//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <string>
#include <utility>
#include <vector>

#include <lib/udp/server.hpp>
#include <utils/constants.hpp>
#include <utils/network.hpp>
#include <utils/nts.hpp>

namespace app
{

struct CliMessage
{
    enum class Type
    {
        EMPTY = 0,
        ECHO,
        ERROR,
        RESULT,
        COMMAND
    } type{};

    std::string nodeName{};
    std::string value{};
    InetAddress clientAddr{};

    static CliMessage Error(InetAddress addr, std::string msg, std::string node = "")
    {
        CliMessage m{};
        m.type = Type::ERROR;
        m.value = std::move(msg);
        m.nodeName = std::move(node);
        m.clientAddr = addr;
        return m;
    }

    static CliMessage Result(InetAddress addr, std::string msg, std::string node = "")
    {
        CliMessage m{};
        m.type = Type::RESULT;
        m.value = std::move(msg);
        m.nodeName = std::move(node);
        m.clientAddr = addr;
        return m;
    }

    static CliMessage Echo(InetAddress addr, std::string msg)
    {
        CliMessage m{};
        m.type = Type::ECHO;
        m.value = std::move(msg);
        m.nodeName = "";
        m.clientAddr = addr;
        return m;
    }

    static CliMessage Command(InetAddress addr, std::string msg, std::string node = "")
    {
        CliMessage m{};
        m.type = Type::COMMAND;
        m.value = std::move(msg);
        m.nodeName = std::move(node);
        m.clientAddr = addr;
        return m;
    }
};

class CliServer
{
  private:
    Socket m_socket;

  public:
    explicit CliServer() : m_socket{Socket::CreateAndBindUdp({cons::CMD_SERVER_IP, 0})}
    {
    }

    ~CliServer()
    {
        m_socket.close();
    }

    [[nodiscard]] InetAddress assignedAddress() const;

    CliMessage receiveMessage();
    void sendMessage(const CliMessage &msg);
};

struct NwCliSendResponse : NtsMessage
{
    InetAddress address{};
    std::string output{};
    bool isError{};

    NwCliSendResponse(const InetAddress &address, std::string output, bool isError)
        : NtsMessage(NtsMessageType::CLI_SEND_RESPONSE), address(address), output(std::move(output)), isError(isError)
    {
    }
};

class CliResponseTask : public NtsTask
{
  private:
    app::CliServer *cliServer;

  public:
    explicit CliResponseTask(CliServer *cliServer) : cliServer(cliServer)
    {
    }

  protected:
    void onStart() override
    {
    }

    void onLoop() override
    {
        auto msg = take();
        if (msg == nullptr)
            return;
        if (msg->msgType == NtsMessageType::CLI_SEND_RESPONSE)
        {
            auto& w = dynamic_cast<NwCliSendResponse &>(*msg);
            cliServer->sendMessage(w.isError ? CliMessage::Error(w.address, w.output)
                                             : CliMessage::Result(w.address, w.output));
        }
    }

    void onQuit() override
    {
    }
};

} // namespace app
