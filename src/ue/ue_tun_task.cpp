//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_tun_task.hpp"
#include "ue_nts.hpp"
#include <libc_error.hpp>
#include <scoped_thread.hpp>

// TODO: May be reduced to MTU 1500
#define RECEIVER_BUFFER_SIZE 16000

struct ReceiverArgs
{
    int fd{};
    int psi{};
    NtsTask *targetTask{};
};

std::string GetErrorMessage(const std::string &cause)
{
    std::string what = cause;

    int errNo = errno;
    if (errNo != 0)
        what += " (" + std::string{strerror(errNo)} + ")";

    return what;
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
            targetTask->push(new nr::ue::NwTunError(GetErrorMessage("TUN device could not read")));
            return; // Abort receiver thread
        }

        if (n > 0)
            targetTask->push(new nr::ue::NwTunReceive(psi, OctetString::FromArray(buffer, static_cast<size_t>(n))));
    }
}

namespace nr::ue
{

ue::TunTask::TunTask(TaskBase *base, int psi, int fd) : base{base}, psi{psi}, fd{fd}, receiver{}
{
}

void TunTask::onStart()
{
    auto *receiverArgs = new ReceiverArgs();
    receiverArgs->fd = fd;
    receiverArgs->targetTask = this;
    receiverArgs->psi = psi;
    receiver =
        new ScopedThread([](void *args) { ReceiverThread(reinterpret_cast<ReceiverArgs *>(args)); }, receiverArgs);
}

void TunTask::onQuit()
{
    delete receiver;
    ::close(fd);
}

void TunTask::onLoop()
{
    NtsMessage *msg = take();
    if (!msg)
        return;

    switch (msg->msgType)
    {
    case NtsMessageType::UE_TUN_RECEIVE:
    case NtsMessageType::UE_TUN_ERROR:
        base->appTask->push(msg);
        break;
    case NtsMessageType::UE_MR_DOWNLINK_DATA: {
        auto *w = dynamic_cast<NwUeDownlinkData *>(msg);
        int res = ::write(fd, w->data.data(), w->data.length());
        if (res < 0)
            push(new nr::ue::NwTunError(GetErrorMessage("TUN device could not write")));
        else if (res != w->data.length())
            push(new nr::ue::NwTunError(GetErrorMessage("TUN device partially written")));
        delete w;
        break;
    }
    default:
        delete msg;
        break;
    }
}

} // namespace nr::ue