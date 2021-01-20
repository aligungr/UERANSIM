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
    logger->debug("SCTP association setup (ascId: %d)", msg->associationId);

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

    entry->client->send(msg->stream, msg->buffer, msg->offset, msg->length);
}

} // namespace nr::gnb
