//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"
#include <cstring>
#include <ue/app/task.hpp>
#include <ue/nts.hpp>
#include <ue/tun/tun.hpp>
#include <unistd.h>
#include <utils/libc_error.hpp>
#include <utils/scoped_thread.hpp>

// TODO: May be reduced to MTU 1500
#define RECEIVER_BUFFER_SIZE 8000

struct ReceiverArgs
{
    int fd{};
    int psi{};
    NtsTask *targetTask{};
};

static std::string GetErrorMessage(const std::string &cause)
{
    std::string what = cause;

    int errNo = errno;
    if (errNo != 0)
        what += " (" + std::string{strerror(errNo)} + ")";

    return what;
}

static std::unique_ptr<nr::ue::NmUeTunToApp> NmError(std::string &&error)
{
    auto m = std::make_unique<nr::ue::NmUeTunToApp>(nr::ue::NmUeTunToApp::TUN_ERROR);
    m->error = std::move(error);
    return m;
}

static void ReceiverThread(ReceiverArgs *args)
{
    int fd = args->fd;
    int psi = args->psi;
    NtsTask *targetTask = args->targetTask;

    delete args;

    uint8_t buffer[RECEIVER_BUFFER_SIZE];

    while (true)
    {
        ssize_t n = ::read(fd, buffer, RECEIVER_BUFFER_SIZE);
        if (n < 0)
        {
            targetTask->push(NmError(GetErrorMessage("TUN device could not read")));
            return; // Abort receiver thread
        }

        if (n > 0)
        {
            auto m = std::make_unique<nr::ue::NmUeTunToApp>(nr::ue::NmUeTunToApp::DATA_PDU_DELIVERY);
            m->psi = psi;
            m->data = OctetString::FromArray(buffer, static_cast<size_t>(n));
            targetTask->push(std::move(m));
        }
    }
}

namespace nr::ue
{

ue::TunTask::TunTask(TaskBase *base, int psi, int fd, std::string tunName, std::string nsName, bool useNamespace)
        : m_base{base}, m_psi{psi}, m_fd{fd}, m_name{std::move(tunName)}, m_nsName{std::move(nsName)},
            m_useNamespace{useNamespace}, m_receiver{}
{
    m_logger = m_base->logBase->makeUniqueLogger(m_base->config->getLoggerPrefix() + "tun");
}

void TunTask::onStart()
{
    auto *receiverArgs = new ReceiverArgs();
    receiverArgs->fd = m_fd;
    receiverArgs->targetTask = this;
    receiverArgs->psi = m_psi;
    m_receiver =
        new ScopedThread([](void *args) { ReceiverThread(reinterpret_cast<ReceiverArgs *>(args)); }, receiverArgs);
}

void TunTask::onQuit()
{
    delete m_receiver;
    ::close(m_fd);

    if (m_useNamespace && !m_nsName.empty())
    {
        std::string error;
        if (!tun::TunCleanup(m_name, m_nsName, m_useNamespace, error))
            m_logger->warn("Namespace cleanup failed for [%s]: %s", m_nsName.c_str(), error.c_str());
    }
}

void TunTask::onLoop()
{
    auto msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_APP_TO_TUN: {
        auto &w = dynamic_cast<NmAppToTun &>(*msg);
        ssize_t res = ::write(m_fd, w.data.data(), w.data.length());
        if (res < 0)
            push(NmError(GetErrorMessage("TUN device could not write")));
        else if (res != w.data.length())
            push(NmError(GetErrorMessage("TUN device partially written")));
        break;
    }
    case NtsMessageType::UE_TUN_TO_APP: {
        m_base->appTask->push(std::move(msg));
        break;
    }
    default:
        break;
    }
}

} // namespace nr::ue