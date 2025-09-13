#pragma once

#include <gnb/nts.hpp>

namespace nr::gnb
{

class GNBSocket
{
    private:
        TaskBase *m_base;

  public:
    explicit GNBSocket(TaskBase *base) : m_base(base)
    {
    }

    void startThread();
    void notify_response(std::string msg);


    volatile int connfd;
};

extern GNBSocket *gnb_socket;

void* start_socket(void* arg);

} // namespace nr::gnb

