//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "rlc.hpp"
#include "entity_am.hpp"
#include "entity_tm.hpp"
#include "entity_um.hpp"

namespace rlc
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

} // namespace rlc
