#include "rlc_entity_tm.hpp"
#include "rlc_func.hpp"

#include <cstring>

namespace nr::rlc
{

void TmEntity::clearEntity()
{
    txCurrentSize = 0;
    txBuffer.clearAndDelete();
}

void TmEntity::receivePdu(uint8_t *data, int size)
{
    consumer->deliverSdu(this, data, size);
}

void TmEntity::receiveSdu(uint8_t *data, int size, int sduId)
{
    if (txCurrentSize + size > txMaxSize)
        return;

    auto segment = new RlcSduSegment(RlcSdu::NewFromData(data, size, sduId));
    segment->si = ESegmentInfo::FULL;
    segment->so = 0;
    segment->size = size;

    txCurrentSize += segment->size;
    txBuffer.addLast(segment);
}

TmEntity::TmEntity(IRlcConsumer *pConsumer, int txMaxSize) : IRlcEntity(pConsumer)
{
    this->txMaxSize = txMaxSize;
    this->txCurrentSize = 0;
    this->tCurrent = 0;

    clearEntity();
}

int TmEntity::createPdu(uint8_t *buffer, int maxSize)
{
    auto segment = txBuffer.getFirst();
    if (segment == nullptr)
        return 0;

    if (segment->value->size > maxSize)
        return 0;

    size_t size = segment->value->size;
    std::memcpy(buffer, segment->value->sdu->data, size);

    delete txBuffer.remove(segment);
    txCurrentSize -= size;

    return size;
}

void TmEntity::discardSdu(int sduId)
{
    // nothing to do
}

void TmEntity::reestablishment()
{
    clearEntity();
}

TmEntity::~TmEntity()
{
    clearEntity();
}

void TmEntity::timerCycle(int64_t currentTime)
{
    tCurrent = currentTime;
}

void TmEntity::calculateDataVolume(RlcDataVolume &volume)
{
    volume.transmissionSize = func::ListSum<RlcSduSegment>(txBuffer, [](const RlcSduSegment *v) { return v->size; });
    volume.receptionSize = 0;
    volume.retransmissionSize = 0;
    volume.statusSize = 0;
}

} // namespace nr::rlc
