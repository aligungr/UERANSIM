//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "utils.hpp"
#include "encoder.hpp"

int rlc::StatusPdu::calculatedSize(bool isShortSn) const
{
    // TODO optimize. this is waay slow and just for POC
    uint8_t buffer[32768];
    return RlcEncoder::EncodeStatus(buffer, *this, isShortSn);
}
