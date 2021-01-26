//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_sctp_task.hpp"

#include <cstring>
#include <thread>
#include <utility>

// #define MOCKED_PACKETS

#ifdef MOCKED_PACKETS
static std::string MOCK_LIST[] = {
    std::string(
        "201500320000040001000e05806f70656e3567732d616d663000600008000009f10702004000564001ff005000080009f10700000008"),

    std::string("0004403e000003000a000200060055000200010026002b2a7e005600020000215660aed893ca993801ad60ba50575cb020101c"
                "016820d4f980"
                "004ff8987b0bfcbbb8"),

    std::string("00044029000003000a0002000600550002000100260016157e03cbc05c7b007e005d020004f0f0f0f0e1360102"),

    std::string("000e00809e000009000a00020006005500020001006e000a0c3e800000303e800000001c00070009f107020040000000020001"
                "007700091c00"
                "0e000700038000005e002091f9908eefe559d08b9f958b4d7c1a94094918dedcf40bb57c55428a00d4dee90022400835693803"
                "56fffff00026"
                "402f2e7e02687a0bd3017e0042010177000bf209f107020040e700acbe54072009f10700000115020101210201005e0129"),

    std::string("0004403c000003000a0002000600550002000100260029287e02f41c3d78027e0054430f10004f00700065006e003500470053"
                "462147121062"
                "41547021490100"),
};
static int MOCK_INDEX = -1;
#endif

namespace nr::gnb
{

class SctpHandler : public sctp::ISctpHandler
{
  private:
    SctpTask *const sctpTask;
    int clientId;

  public:
    SctpHandler(SctpTask *const sctpTask, int clientId) : sctpTask(sctpTask), clientId(clientId)
    {
    }

  private:
    void onAssociationSetup(int associationId, int inStreams, int outStreams) override
    {
        sctpTask->push(new NwSctpAssociationSetup(clientId, associationId, inStreams, outStreams));
    }

    void onAssociationShutdown() override
    {
        sctpTask->push(new NwSctpAssociationShutdown(clientId));
    }

    void onMessage(uint8_t *buffer, size_t length, uint16_t stream) override
    {
        auto *data = new uint8_t[length];
        std::memcpy(data, buffer, length);
        sctpTask->push(new NwSctpClientReceive(clientId, data, length, stream));
    }

    void onUnhandledNotification() override
    {
        sctpTask->push(new NwSctpUnhandledNotificationReceive(clientId));
    }
};

[[noreturn]] static void ReceiverThread(std::pair<sctp::SctpClient *, sctp::ISctpHandler *> *args)
{
    sctp::SctpClient *client = args->first;
    sctp::ISctpHandler *handler = args->second;

    delete args;

    while (true)
        client->receive(handler);
}

SctpTask::SctpTask(TaskBase *base) : base{base}, clients{}
{
    logger = base->logBase->makeUniqueLogger("sctp");
}

void SctpTask::onStart()
{
}

void SctpTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::SCTP_CONNECTION_REQUEST:
        receiveSctpConnectionSetupRequest(dynamic_cast<NwSctpConnectionRequest *>(msg));
        break;
    case NtsMessageType::SCTP_ASSOCIATION_SETUP:
        receiveAssociationSetup(dynamic_cast<NwSctpAssociationSetup *>(msg));
        break;
    case NtsMessageType::SCTP_ASSOCIATION_SHUTDOWN:
        receiveAssociationShutdown(dynamic_cast<NwSctpAssociationShutdown *>(msg));
        break;
    case NtsMessageType::SCTP_CLIENT_RECEIVE:
        receiveClientReceive(dynamic_cast<NwSctpClientReceive *>(msg));
        break;
    case NtsMessageType::SCTP_UNHANDLED_NOTIFICATION_RECEIVE:
        receiveUnhandledNotification(dynamic_cast<NwSctpUnhandledNotificationReceive *>(msg));
        break;
    case NtsMessageType::SCTP_CONNECTION_CLOSE:
        receiveConnectionClose(dynamic_cast<NwSctpConnectionClose *>(msg));
        break;
    case NtsMessageType::SCTP_SEND_MESSAGE:
        receiveSendMessage(dynamic_cast<NwSctpSendMessage *>(msg));
        break;
    default:
        logger->err("Unhandled NTS message received with type %d", (int)msg->msgType);
        delete msg;
        break;
    }
}

void SctpTask::onQuit()
{
    logger->debug("SCTP task is quiting");
    logger->flush();

    for (auto &client : clients)
    {
        ClientEntry *entry = client.second;
        DeleteClientEntry(entry);
    }
    clients.clear();
}

void SctpTask::DeleteClientEntry(ClientEntry *entry)
{
    entry->associatedTask = nullptr;
    delete entry->receiverThread;
    delete entry->client;
    delete entry->handler;
    delete entry;
}

void SctpTask::receiveSctpConnectionSetupRequest(NwSctpConnectionRequest *msg)
{
    logger->info("Trying to establish SCTP connection... (%s:%d)", msg->remoteAddress.c_str(), msg->remotePort);

    auto *client = new sctp::SctpClient(msg->ppid);

    try
    {
        client->bind(msg->localAddress, msg->localPort);
    }
    catch (const SctpError &exc)
    {
        logger->err("Binding to %s:%d failed %s", msg->localAddress.c_str(), msg->localPort, exc.what());
        delete msg;
        delete client;
        return;
    }

    try
    {
        client->connect(msg->remoteAddress, msg->remotePort);
    }
    catch (const SctpError &exc)
    {
        logger->err("Connecting to %s:%d failed %s", msg->remoteAddress.c_str(), msg->remotePort, exc.what());
        delete msg;
        delete client;
        return;
    }

    logger->info("SCTP connection established (%s:%d)", msg->remoteAddress.c_str(), msg->remotePort);

    sctp::ISctpHandler *handler = new SctpHandler(this, msg->clientId);

    auto *entry = new ClientEntry;
    clients[msg->clientId] = entry;

    entry->id = msg->clientId;
    entry->client = client;
    entry->handler = handler;
    entry->associatedTask = msg->associatedTask;
    entry->receiverThread = new ScopedThread(
        [](void *arg) { ReceiverThread(reinterpret_cast<std::pair<sctp::SctpClient *, sctp::ISctpHandler *> *>(arg)); },
        new std::pair<sctp::SctpClient *, sctp::ISctpHandler *>(client, handler));

    delete msg;
}

void SctpTask::receiveAssociationSetup(NwSctpAssociationSetup *msg)
{
    logger->debug("SCTP association setup ascId[%d]", msg->associationId);

    ClientEntry *entry = clients[msg->clientId];
    if (entry == nullptr)
    {
        logger->warn("Client entry not found for id: %d", msg->clientId);
        return;
    }

    // Notify the relevant task
    entry->associatedTask->push(msg);
}

void SctpTask::receiveAssociationShutdown(NwSctpAssociationShutdown *msg)
{
    logger->debug("SCTP association shutdown (clientId: %d)", msg->clientId);

    ClientEntry *entry = clients[msg->clientId];
    if (entry == nullptr)
    {
        logger->warn("Client entry not found for id: %d", msg->clientId);
        return;
    }

    // Notify the relevant task
    entry->associatedTask->push(msg);
}

void SctpTask::receiveClientReceive(NwSctpClientReceive *msg)
{
    ClientEntry *entry = clients[msg->clientId];
    if (entry == nullptr)
    {
        logger->warn("Client entry not found for id: %d", msg->clientId);
        return;
    }

    // Notify the relevant task
    entry->associatedTask->push(msg);
}

void SctpTask::receiveUnhandledNotification(NwSctpUnhandledNotificationReceive *msg)
{
    logger->debug("Unhandled SCTP notification received");
    delete msg;
}

void SctpTask::receiveConnectionClose(NwSctpConnectionClose *msg)
{
    ClientEntry *entry = clients[msg->clientId];
    if (entry == nullptr)
    {
        logger->warn("Client entry not found for id: %d", msg->clientId);
        return;
    }

    DeleteClientEntry(entry);
}

void SctpTask::receiveSendMessage(NwSctpSendMessage *msg)
{
    ClientEntry *entry = clients[msg->clientId];
    if (entry == nullptr)
    {
        logger->warn("Client entry not found for id: %d", msg->clientId);
        return;
    }

#ifdef MOCKED_PACKETS
    {
        std::string ss = MOCK_LIST[++MOCK_INDEX];
        OctetString data = OctetString::FromHex(ss);
        auto *copy = new uint8_t[data.length()];
        std::memcpy(copy, data.data(), data.length());
        receiveClientReceive(new NwSctpClientReceive(msg->clientId, copy, data.length(), 0));
    }
#else
    entry->client->send(msg->stream, msg->buffer, msg->offset, msg->length);
#endif
}

} // namespace nr::gnb
