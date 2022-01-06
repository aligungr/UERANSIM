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

FdBase::FdBase() : m_ids{}, m_fds{}, m_isSocket(), m_dice{}
{
    for (auto &fd : m_fds)
        fd = -1;
}

FdBase::~FdBase()
{
    for (int fd : m_fds)
        if (fd >= 0)
            ::close(fd);
}

void FdBase::allocate(int id, int fd, bool isSocket)
{
    if (id < 0)
        throw std::runtime_error{"FdBase bad id"};

    for (size_t i = 0; i < m_ids.size(); i++)
    {
        if (m_fds[i] < 0)
        {
            m_ids[i] = id;
            m_fds[i] = fd;
            m_isSocket[i] = isSocket;
            return;
        }
    }

    throw std::runtime_error{"FdBase allocation failure"};
}

void FdBase::release(int id)
{
    for (size_t i = 0; i < m_ids.size(); i++)
    {
        if (m_ids[i] == id)
        {
            ::close(m_fds[i]);
            m_ids[i] = -1;
            m_fds[i] = -1;
            m_isSocket[i] = false;
        }
    }
}

void FdBase::write(int id, uint8_t *buffer, size_t size)
{
    for (size_t i = 0; i < m_ids.size(); i++)
    {
        if (m_ids[i] == id)
        {
            ::write(m_fds[i], buffer, size);
            break;
        }
    }
}

size_t FdBase::read(uint8_t *buffer, size_t size, int timeout, int &outId, InetAddress &outAddress)
{
    int index = performSelect(timeout);
    if (index < 0)
        return 0;

    outId = m_ids[index];

    if (m_isSocket[index])
    {
        sockaddr_storage peerAddr{};
        socklen_t peerAddrLen = sizeof(struct sockaddr_storage);

        auto n = ::recvfrom(m_fds[index], buffer, size, 0, (struct sockaddr *)&peerAddr, &peerAddrLen);
        if (n < 0)
            throw std::runtime_error(GetErrorMessage("FD could not receive"));

        outAddress = InetAddress{peerAddr, peerAddrLen};
        return static_cast<size_t>(n);
    }
    else
    {
        outAddress = {};
        auto n = ::read(m_fds[index], buffer, size);
        if (n < 0)
            throw std::runtime_error(GetErrorMessage("FD could not read"));
        return static_cast<size_t>(n);
    }
}

bool FdBase::contains(int id) const
{
    for (int m_id : m_ids)
        if (m_id == id)
            return true;
    return false;
}

int FdBase::performSelect(int timeout)
{
    size_t dice = m_dice++;

    int max = 0;

    fd_set fdSet;
    FD_ZERO(&fdSet);

    for (size_t i = 0; i < m_ids.size(); i++)
    {
        int fd = m_fds[i];
        if (fd >= 0)
        {
            FD_SET(fd, &fdSet);
            max = std::max(max, fd);
        }
    }

    timeval to{};
    to.tv_sec = timeout / 1000;
    to.tv_usec = (timeout % 1000) * 1000;

    int ret = select(max + 1, &fdSet, nullptr, nullptr, timeout <= 0 ? nullptr : &to);
    if (ret < 0)
        return -1;

    for (size_t i = 0; i < m_ids.size(); i++)
    {
        size_t j = (dice + i) % m_ids.size();
        int fd = m_fds[j];
        if (fd >= 0 && FD_ISSET(fd, &fdSet))
            return static_cast<int>(j);
    }

    return -1;
}
