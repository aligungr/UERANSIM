//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
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
