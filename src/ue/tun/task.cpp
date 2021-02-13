//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <cstring>
#include <ue/app/task.hpp>
#include <ue/nts.hpp>
#include <unistd.h>
#include <utils/libc_error.hpp>
#include <utils/scoped_thread.hpp>

// TODO: May be reduced to MTU 1500
#define RECEIVER_BUFFER_SIZE 16000

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

static nr::ue::NwUeTunToApp *NwError(std::string &&error)
{
    auto *nw = new nr::ue::NwUeTunToApp(nr::ue::NwUeTunToApp::TUN_ERROR);
    nw->error = std::move(error);
    return nw;
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
        int n = ::read(fd, buffer, RECEIVER_BUFFER_SIZE);
        if (n < 0)
        {
            targetTask->push(NwError(GetErrorMessage("TUN device could not read")));
            return; // Abort receiver thread
        }

        if (n > 0)
        {
            auto *nw = new nr::ue::NwUeTunToApp(nr::ue::NwUeTunToApp::DATA_PDU_DELIVERY);
            nw->psi = psi;
            nw->data = OctetString::FromArray(buffer, static_cast<size_t>(n));
            targetTask->push(nw);
        }
    }
}

namespace nr::ue
{

ue::TunTask::TunTask(TaskBase *base, int psi, int fd) : m_base{base}, m_psi{psi}, m_fd{fd}, m_receiver{}
{
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
}

void TunTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_APP_TO_TUN: {
        auto *w = dynamic_cast<NwAppToTun *>(msg);
        int res = ::write(m_fd, w->data.data(), w->data.length());
        if (res < 0)
            push(NwError(GetErrorMessage("TUN device could not write")));
        else if (res != w->data.length())
            push(NwError(GetErrorMessage("TUN device partially written")));
        delete w;
        break;
    }
    case NtsMessageType::UE_TUN_TO_APP: {
        m_base->appTask->push(msg);
        break;
    }
    default:
        delete msg;
        break;
    }
}

} // namespace nr::ue