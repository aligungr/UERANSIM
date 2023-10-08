//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstddef>
#include <cstdint>
#include <stdexcept>

#include <utils/unique_buffer.hpp>

namespace sctp
{

enum class PayloadProtocolId
{
    NGAP = 60
};

class SctpError : public std::runtime_error
{
  public:
    explicit SctpError(const std::string &what) : std::runtime_error(what)
    {
    }
    explicit SctpError(const char *what) : std::runtime_error(what)
    {
    }
};

class ISctpHandler
{
  public:
    virtual ~ISctpHandler() = default;

    virtual void onAssociationSetup(int associationId, int inStreams, int outStreams) = 0;
    virtual void onAssociationShutdown() = 0;
    virtual void onConnectionReset() = 0;
    virtual void onMessage(const uint8_t *buffer, size_t length, uint16_t stream) = 0;
    virtual void onUnhandledNotification() = 0;
};

} // namespace sctp
