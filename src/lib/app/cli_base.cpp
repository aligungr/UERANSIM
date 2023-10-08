//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "cli_base.hpp"

#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

#define CMD_BUFFER_SIZE 8192
#define CMD_RCV_TIMEOUT 2500
#define CMD_MIN_LENGTH (3 + 4 + 4 + 1)

namespace app
{

InetAddress CliServer::assignedAddress() const
{
    return m_socket.getAddress();
}

CliMessage CliServer::receiveMessage()
{
    uint8_t buffer[CMD_BUFFER_SIZE] = {0};
    InetAddress address;

    int size = m_socket.receive(buffer, CMD_BUFFER_SIZE, CMD_RCV_TIMEOUT, address);
    if (size < CMD_MIN_LENGTH || size >= CMD_BUFFER_SIZE)
        return {};

    OctetView v{buffer, static_cast<size_t>(size)};
    if (v.readI() != cons::Major)
        return {};
    if (v.readI() != cons::Minor)
        return {};
    if (v.readI() != cons::Patch)
        return {};

    CliMessage res{};
    res.type = static_cast<CliMessage::Type>(v.readI());
    int nodeNameLength = v.read4I();
    res.nodeName = v.readUtf8String(nodeNameLength);
    int valueLength = v.read4I();
    res.value = v.readUtf8String(valueLength);
    res.clientAddr = address;
    return res;
}

void CliServer::sendMessage(const CliMessage &msg)
{
    OctetString stream{};
    stream.appendOctet(cons::Major);
    stream.appendOctet(cons::Minor);
    stream.appendOctet(cons::Patch);
    stream.appendOctet(static_cast<int>(msg.type));
    stream.appendOctet4(static_cast<int>(msg.nodeName.size()));
    for (char c : msg.nodeName)
        stream.appendOctet(static_cast<uint8_t>(c));
    stream.appendOctet4(static_cast<int>(msg.value.size()));
    for (char c : msg.value)
        stream.appendOctet(static_cast<uint8_t>(c));

    m_socket.send(msg.clientAddr, stream.data(), static_cast<size_t>(stream.length()));
}

} // namespace app
