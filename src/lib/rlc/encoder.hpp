//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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