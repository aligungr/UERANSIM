//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

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
