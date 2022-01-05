//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "layer.hpp"
#include "config.hpp"

#include <cstring>
#include <unistd.h>

#include <ue/l3/task.hpp>
#include <ue/nts.hpp>
#include <utils/libc_error.hpp>

#define RECEIVER_BUFFER_SIZE 8000 // TODO: May be reduced to MTU 1500
#define READ_TIMEOUT 500

static std::string GetErrorMessage(const std::string &cause)
{
    std::string what = cause;
    int errNo = errno;
    if (errNo != 0)
        what += " (" + std::string{strerror(errNo)} + ")";
    return what;
}

static void ReceiverThread(void *args)
{
    auto *base = reinterpret_cast<nr::ue::TaskBase *>(args);

    auto *buffer = new uint8_t[RECEIVER_BUFFER_SIZE];

    while (true)
    {
        int psi;
        size_t s = base->tunLayer->read(buffer, RECEIVER_BUFFER_SIZE, psi);

        if (s > 0)
        {
            auto m = std::make_unique<nr::ue::NmUeTunToApp>(nr::ue::NmUeTunToApp::DATA_PDU_DELIVERY);
            m->psi = psi;
            m->data = OctetString::FromArray(buffer, s);
            base->l3Task->push(std::move(m));
        }
    }
}

namespace nr::ue
{

TunLayer::TunLayer(TaskBase *base) : m_base{base}, m_fd{}, m_dice{}
{
    for (auto &fd : m_fd)
        fd = -1;

    m_receiverThread = std::make_unique<ScopedThread>(ReceiverThread, base);
}

TunLayer::~TunLayer()
{
    m_receiverThread.reset();

    for (auto &fd : m_fd)
    {
        if (fd >= 0)
        {
            ::close(fd);
            fd = -1;
        }
    }
}

std::string TunLayer::allocate(int psi, const std::string &ipAddress, bool configureRouting, std::string &outError)
{
    if (!utils::IsRoot())
    {
        outError = "TUN interface could not be setup. Permission denied. Please run the UE with 'sudo'";
        return {};
    }

    if (psi == 0 || psi > 15)
    {
        outError = "Connection could not setup. Invalid PSI.";
        return {};
    }

    if (m_fd[psi] >= 0)
    {
        outError = "Connection could not setup. PSI already exists in TUN layer";
        return {};
    }

    std::string error{}, allocatedName{};
    int fd = tun::TunAllocate(cons::TunNamePrefix, allocatedName, error);
    if (fd == 0 || error.length() > 0)
    {
        outError = "TUN allocation failure [" + error + "]";
        return {};
    }

    bool r = tun::TunConfigure(allocatedName, ipAddress, cons::TunMtu, configureRouting, error);
    if (!r || error.length() > 0)
    {
        outError = "TUN configuration failure [" + error + "]";
        return {};
    }

    m_fd[psi] = fd;

    return allocatedName;
}

void TunLayer::release(int psi)
{
    if (m_fd[psi] >= 0)
    {
        ::close(m_fd[psi]);
        m_fd[psi] = -1;
    }
}

void TunLayer::write(int psi, const uint8_t *buffer, size_t size)
{
    ssize_t res = ::write(m_fd[psi], buffer, size);
    if (res < 0)
        throw std::runtime_error(GetErrorMessage("TUN device could not write"));
    if (res != static_cast<ssize_t>(size))
        throw std::runtime_error(GetErrorMessage("TUN device partially written"));
}

size_t TunLayer::read(uint8_t *buffer, size_t size, int &outPsi)
{
    int psi, fd;
    if (!performSelect(psi, fd))
        return 0;

    ssize_t n = ::read(fd, buffer, size);
    if (n < 0)
        throw std::runtime_error(GetErrorMessage("TUN device could not read"));

    outPsi = psi;
    return static_cast<size_t>(n);
}

bool TunLayer::performSelect(int &outPsi, int &outFd)
{
    const int timeout = READ_TIMEOUT;

    int dice = m_dice++;

    int max = 0;

    fd_set fdSet;
    FD_ZERO(&fdSet);

    for (size_t i = 0; i < 16; i++)
    {
        int fd = m_fd[i];
        if (fd >= 0)
        {
            FD_SET(fd, &fdSet);
            max = std::max(max, fd);
        }
    }

    timeval to{};
    to.tv_sec = timeout / 1000;
    to.tv_usec = (timeout % 1000) * 1000;

    int ret = select(max + 1, &fdSet, nullptr, nullptr, &to);
    if (ret < 0)
        return false;

    for (int i = 0; i < 16; i++)
    {
        int j = (dice + i) % 16;

        int fd = m_fd[j];
        if (fd >= 0)
        {
            if (FD_ISSET(fd, &fdSet))
            {
                outPsi = j;
                outFd = fd;
                return true;
            }
        }
    }

    return false;
}

} // namespace nr::ue
