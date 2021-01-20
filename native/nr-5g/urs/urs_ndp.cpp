//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "urs_ndp.hpp"

void urs::NdpEntity::handleMessage(const InetAddress &address, uint8_t *data, size_t size)
{
    OctetBuffer buffer{data, size};
    if (size < 16 + 4 + 1)
        return;

    for (uint8_t i : Ndp::MAGIC_NUMBER)
        if (i != buffer.read())
            return;

    uint32_t remoteVersion = buffer.read4UI();
    if (remoteVersion == ~0U)
    {
        uint8_t errType = buffer.read();
        int errMessageLen = buffer.read2I();
        std::string errMessage{data + buffer.currentIndex(), data + buffer.currentIndex() + errMessageLen};

        onErrorReceived(errType, errMessage);
        return;
    }

    if (remoteVersion != appVersion)
    {
        onBroadcastVersionMismatch(address);
        return;
    }

    uint8_t msgType = buffer.read();
    if (msgType == Ndp::MSG_DISCOVERY_REQUEST)
    {
        uint8_t type = buffer.read();

        if (type != Ndp::NODE_UNSPECIFIED && type != nodeType)
            return;

        int nameLength = buffer.read2I();

        if (nameLength != 0)
        {
            if (nameLength != static_cast<int>(nodeName.length()))
                return;

            for (int i = 0; i < nameLength; i++)
                if (buffer.readI() != nodeName[i])
                    return;
        }

        onNodeMatches(address);
        return;
    }

    if (msgType == Ndp::MSG_DISCOVERY_RESPONSE)
    {
        uint8_t type = buffer.read();

        int nameSize = buffer.read2I();
        if (nameSize == 0)
            return;

        std::string name{data + buffer.currentIndex(), data + buffer.currentIndex() + nameSize};

        uint16_t portal = buffer.read2I();
        uint16_t mr = buffer.read2I();

        int portalIpSize = buffer.read2I();
        if (portalIpSize == 0)
            return;

        std::string portalIpAddr{data + buffer.currentIndex(), data + buffer.currentIndex() + portalIpSize};

        onNodeDiscover(type, name, portal, mr, portalIpAddr);
        return;
    }
}

void urs::NdpEntity::sendDiscoveryRequest(const InetAddress &address, uint8_t type, const std::string &name)
{
    uint8_t buffer[Ndp::PACKET_MAX] = {0};
    OctetBuffer buf{buffer};

    for (uint8_t i : Ndp::MAGIC_NUMBER)
        buf.write(i);

    buf.write4(appVersion);
    buf.write(Ndp::MSG_DISCOVERY_REQUEST);
    buf.write(type);
    buf.write2(name.length());
    for (char c : name)
        buf.write(c);

    onSendMessage(address, buffer, buf.currentIndex());
}

void urs::NdpEntity::onNodeMatches(const InetAddress &address)
{
    uint8_t buffer[Ndp::PACKET_MAX] = {0};
    OctetBuffer buf{buffer};

    for (uint8_t i : Ndp::MAGIC_NUMBER)
        buf.write(i);

    buf.write4(appVersion);
    buf.write(Ndp::MSG_DISCOVERY_RESPONSE);
    buf.write(nodeType);
    buf.write2(nodeName.size());
    for (char i : nodeName)
        buf.write(i);
    buf.write2(portalPort);
    buf.write2(mrPort);
    buf.write2(portalIp.size());
    for (char i : portalIp)
        buf.write(i);

    sendMessage(address, buffer, buf.currentIndex());
}

void urs::NdpEntity::onBroadcastVersionMismatch(const InetAddress &address)
{
    sendErrorIndication(address, Ndp::ERR_VERSION_MISMATCH, "");
}

void urs::NdpEntity::sendErrorIndication(const InetAddress &address, uint8_t errorType, const std::string &message)
{
    uint8_t buffer[Ndp::PACKET_MAX] = {0};
    OctetBuffer buf{buffer};

    for (uint8_t i : Ndp::MAGIC_NUMBER)
        buf.write(i);

    buf.write4(~0U);
    buf.write(errorType);
    buf.write2(message.size());
    for (char i : message)
        buf.write(i);

    sendMessage(address, buffer, buf.currentIndex());
}

void urs::NdpEntity::sendMessage(const InetAddress &address, uint8_t *buffer, size_t size)
{
    onSendMessage(address, buffer, size);
}
