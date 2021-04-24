//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include "utils.hpp"

namespace rlc
{

class RlcEncoder
{
  public:
    static UmdPdu *DecodeUmd(uint8_t *data, int size, bool isShortSn);
    static int EncodeUmd(uint8_t *buffer, bool isShortSn, ESegmentInfo si, int so, int sn, uint8_t *data, int size);

    static AmdPdu *DecodeAmd(uint8_t *data, int size, bool isShortSn);
    static int EncodeAmd(uint8_t *buffer, bool isShortSn, ESegmentInfo si, int so, int sn, uint8_t *data, int size,
                         bool p);

    static StatusPdu *DecodeStatus(uint8_t *data, int size, bool isShortSn);
    static int EncodeStatus(uint8_t *buffer, const StatusPdu &pdu, bool isShortSn);
};

} // namespace rlc