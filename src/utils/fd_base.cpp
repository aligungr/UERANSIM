//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "fd_base.hpp"

#include <algorithm>
#include <cstring>
#include <stdexcept>
#include <sys/socket.h>
#include <unistd.h>

static std::string GetErrorMessage(const std::string &cause)
{
    std::string what = cause;
    int errNo = errno;
    if (errNo != 0)
        what += " (" + std::string{strerror(errNo)} + ")";
    return what;
}

FdBase::FdBase(int timeout) : m_fd{}, m_dice{}, m_timeout{timeout}
{
    for (auto &fd : m_fd)
        fd = -1;
}

FdBase::~FdBase()
{
    for (auto &fd : m_fd)
    {
        if (fd >= 0)
            ::close(fd);
        fd = -1;
    }
}

void FdBase::allocate(int id, int fd)
{
    if (id < 0)
        throw std::runtime_error{"FdBase bad id"};

    if (m_fd[id] >= 0)
        throw std::runtime_error{"FdBase existing id"};

    m_fd[id] = fd;
}

void FdBase::release(int id)
{
    if (m_fd[id] >= 0)
        ::close(m_fd[id]);
    m_fd[id] = -1;
}

void FdBase::write(int id, uint8_t *buffer, size_t size)
{
    if (m_fd[id] < 0)
        return;

    ssize_t rc = ::write(m_fd[id], buffer, size);
    if (rc == -1)
    {
        int err = errno;
        if (err != EAGAIN)
            throw std::runtime_error(GetErrorMessage("FD could not send"));
    }
}

void FdBase::sendTo(int id, uint8_t *buffer, size_t size, const InetAddress &address)
{
    if (m_fd[id] < 0)
        return;

    ssize_t rc = sendto(m_fd[id], buffer, size, MSG_DONTWAIT, address.getSockAddr(), address.getSockLen());
    if (rc == -1)
    {
        int err = errno;
        if (err != EAGAIN)
            throw std::runtime_error(GetErrorMessage("FD could not send"));
    }
}

size_t FdBase::read(int id, uint8_t *buffer, size_t size)
{
    if (m_fd[id] < 0)
        return 0;

    auto n = ::read(m_fd[id], buffer, size);
    if (n < 0)
        throw std::runtime_error(GetErrorMessage("FD could not read"));
    return static_cast<size_t>(n);
}

size_t FdBase::receive(int id, uint8_t *buffer, size_t size, InetAddress &outAddress)
{
    if (m_fd[id] < 0)
        return 0;

    (*outAddress.getSockLenAddr()) = sizeof(struct sockaddr_storage);

    auto n = ::recvfrom(m_fd[id], buffer, size, 0, (struct sockaddr *)outAddress.getStorageAddr(),
                        outAddress.getSockLenAddr());
    if (n < 0)
        throw std::runtime_error(GetErrorMessage("FD could not receive"));

    return static_cast<size_t>(n);
}

bool FdBase::contains(int id) const
{
    return m_fd[id] >= 0;
}

int FdBase::performSelect()
{
    int max = 0;

    fd_set fdSet;
    FD_ZERO(&fdSet);

    for (size_t i = 0; i < SIZE; i++)
    {
        int fd = m_fd[i];
        if (fd >= 0)
        {
            FD_SET(fd, &fdSet);
            max = std::max(max, fd);
        }
    }

    timeval to{};
    to.tv_sec = m_timeout / 1000;
    to.tv_usec = (m_timeout % 1000) * 1000;

    int ret = select(max + 1, &fdSet, nullptr, nullptr, m_timeout <= 0 ? nullptr : &to);
    if (ret < 0)
        return -1;

    size_t dice = m_dice++;
    for (size_t i = 0; i < SIZE; i++)
    {
        size_t j = (dice + i) % SIZE;
        int fd = m_fd[j];
        if (fd >= 0 && FD_ISSET(fd, &fdSet))
            return static_cast<int>(j);
    }

    return -1;
}
