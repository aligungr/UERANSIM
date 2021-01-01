#include "rlc.hpp"
#include "rlc_entity_am.hpp"
#include "rlc_entity_tm.hpp"
#include "rlc_entity_um.hpp"

namespace nr::rlc
{

IRlcEntity *NewTmEntity(IRlcConsumer *consumer, int txMaxSize)
{
    return new TmEntity(consumer, txMaxSize);
}

IRlcEntity *NewUmEntity(IRlcConsumer *consumer, int snLength, int tReassemblyPeriod, int txMaxSize, int rxMaxSize)
{
    return new UmEntity(consumer, snLength, tReassemblyPeriod, txMaxSize, rxMaxSize);
}

IRlcEntity *NewAmEntity(IRlcConsumer *consumer, int snLength, int txMaxSize, int rxMaxSize, int pollPdu, int pollByte,
                        int maxRetThreshold, int pollRetransmitPeriod, int reassemblyPeriod, int statusProhibitPeriod)
{
    return new AmEntity(consumer, snLength, txMaxSize, rxMaxSize, pollPdu, pollByte, maxRetThreshold,
                        pollRetransmitPeriod, reassemblyPeriod, statusProhibitPeriod);
}

} // namespace nr::rlc
