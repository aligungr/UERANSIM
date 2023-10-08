//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "utils.hpp"
#include "encoder.hpp"

int rlc::StatusPdu::calculatedSize(bool isShortSn) const
{
    // TODO optimize. this is waay slow and just for POC
    uint8_t buffer[32768];
    return RlcEncoder::EncodeStatus(buffer, *this, isShortSn);
}
