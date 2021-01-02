#pragma once

#include <pthread.h>

class ScopedThread
{
  private:
    pthread_t m_threadId;

  public:
    ScopedThread(void (*routine)(void *), void *arg);
    ~ScopedThread();
};
