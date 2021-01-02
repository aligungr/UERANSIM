#pragma once

#include <cstddef>
#include <cstdint>

namespace sctp
{

class ISctpHandler
{
  public:
    virtual ~ISctpHandler() = default;

    virtual void onAssociationSetup(int associationId, int inStreams, int outStreams) = 0;
    virtual void onAssociationShutdown() = 0;
    virtual void onMessage(uint8_t *buffer, size_t length, uint16_t stream) = 0;
    virtual void onUnhandledNotification() = 0;
};

} // namespace sctp
