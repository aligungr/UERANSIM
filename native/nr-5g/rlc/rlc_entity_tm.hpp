#pragma once

#include "../utils/linked_list.hpp"
#include "rlc.hpp"
#include "rlc_utils.hpp"

namespace nr::rlc
{

class TmEntity : public IRlcEntity
{
    // Configurations
    int txMaxSize;

    // TX buffer
    int txCurrentSize;
    LinkedList<RlcSduSegment> txBuffer;

    // Timers
    long tCurrent; // Not a timer, but holds the current time in ms.

  public:
    TmEntity(IRlcConsumer *pConsumer, int txMaxSize);
    ~TmEntity() override;

  private:
    void clearEntity();

  public:
    void receivePdu(uint8_t *data, int size) override;
    void receiveSdu(uint8_t *data, int size, int sduId) override;
    int createPdu(uint8_t *buffer, int maxSize) override;
    void timerCycle(int64_t currentTime) override;
    void discardSdu(int sduId) override;
    void reestablishment() override;
    void calculateDataVolume(RlcDataVolume &volume) override;

    int64_t debug_getCurrentTime() override
    {
        return tCurrent;
    }
};

} // namespace nr::rlc
