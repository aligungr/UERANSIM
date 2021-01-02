#include "gnb_sctp_task.hpp"

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
        sctpTask->push(new NwSctpClientReceive(clientId, buffer, length, stream));
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

SctpTask::SctpTask() : clients{}
{
}

SctpTask::~SctpTask()
{
    for (auto &client : clients)
    {
        ClientEntry *entry = client.second;
        delete entry->receiverThread;
        delete entry->client;
        delete entry->handler;
        delete entry;
    }
    clients.clear();
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
    default:
        delete msg;
        break;
    }
}

void SctpTask::receiveSctpConnectionSetupRequest(NwSctpConnectionRequest *msg)
{
    auto *client = new sctp::SctpClient(msg->ppid);

    try
    {
        client->bind(msg->localAddress, msg->localPort);
        client->connect(msg->remoteAddress, msg->remotePort);
    }
    catch (const SctpError &exc)
    {
        // TODO: logging Log.Error();
        delete msg;
        delete client;
        return;
    }

    sctp::ISctpHandler *handler = new SctpHandler(this, msg->clientId);

    auto *entry = new ClientEntry;
    clients[entry->id] = entry;

    entry->id = msg->clientId;
    entry->client = client;
    entry->handler = handler;
    entry->receiverThread = new ScopedThread(
        [](void *arg) { ReceiverThread(reinterpret_cast<std::pair<sctp::SctpClient *, sctp::ISctpHandler *> *>(arg)); },
        new std::pair<sctp::SctpClient *, sctp::ISctpHandler *>(client, handler));

    delete msg;
}

void SctpTask::receiveAssociationSetup(NwSctpAssociationSetup *msg)
{
    // TODO: Notify NGAP task

    // delete? msg
}

void SctpTask::receiveAssociationShutdown(NwSctpAssociationShutdown *msg)
{
    // TODO: Notify NGAP task and delete client info

    // delete? msg
}

void SctpTask::receiveClientReceive(NwSctpClientReceive *msg)
{
    // TODO: Notify NGAP task

    // delete? msg
}

void SctpTask::receiveUnhandledNotification(NwSctpUnhandledNotificationReceive *msg)
{
    // TODO: Notify NGAP task and maybe log warnig or terminate?

    // delete? msg
}

} // namespace nr::gnb
