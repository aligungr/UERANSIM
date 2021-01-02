#include "scoped_thread.hpp"

#include <iostream>
#include <pthread.h>

struct Arguments
{
    void *routine;
    void *arg;
};

static void *StartRoutine(void *pthreadArg)
{
    auto arguments = (Arguments *)pthreadArg;

    auto routine = (void (*)(void *))(arguments->arg);
    auto arg = arguments->arg;

    delete arguments;

    if (routine != nullptr)
        routine(arg);

    return nullptr;
}

ScopedThread::ScopedThread(void (*routine)(void *), void *arg)
{
    auto arguments = new Arguments;
    arguments->routine = (void *)routine;
    arguments->arg = arg;

    pthread_t threadId;

    if (pthread_create(&threadId, nullptr, StartRoutine, &arguments))
    {
        delete arguments;
        throw std::runtime_error("Thread could not be created.");
    }

    m_threadId = threadId;
}

ScopedThread::~ScopedThread()
{
    int r = pthread_cancel(m_threadId);
    if (r != 0)
    {
        std::cerr << "Thread stopping failure, pthread_cancel failed." << std::endl;
        std::terminate();
    }

    void *res = nullptr;

    r = pthread_join(m_threadId, &res);
    if (r != 0)
    {
        std::cerr << "Thread stopping failure, pthread_join failed." << std::endl;
        std::terminate();
    }
}
