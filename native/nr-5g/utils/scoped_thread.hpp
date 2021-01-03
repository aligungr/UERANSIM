//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
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
