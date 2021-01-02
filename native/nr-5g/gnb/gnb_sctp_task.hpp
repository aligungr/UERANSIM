#pragma once

#include <memory>
#include <nts.hpp>
#include <scoped_thread.hpp>
#include <sctp.hpp>
#include <thread>
#include <unordered_map>
#include <vector>

#include "gnb_sctp_nts.hpp"

namespace nr::gnb
{

class SctpTask : public NtsTask
{
  private:
    struct ClientEntry
    {
        int id;
        sctp::SctpClient *client;
        ScopedThread *receiverThread;
        sctp::ISctpHandler *handler;
    };

  private:
    std::unordered_map<int, ClientEntry *> clients;

  public:
    SctpTask();
    ~SctpTask() override;

  protected:
    void onStart() override;
    void onLoop() override;

  private:
    void receiveSctpConnectionSetupRequest(NwSctpConnectionRequest *msg);
    void receiveAssociationSetup(NwSctpAssociationSetup *msg);
    void receiveAssociationShutdown(NwSctpAssociationShutdown *msg);
    void receiveClientReceive(NwSctpClientReceive *msg);
    void receiveUnhandledNotification(NwSctpUnhandledNotificationReceive *msg);
};

} // namespace nr::gnb