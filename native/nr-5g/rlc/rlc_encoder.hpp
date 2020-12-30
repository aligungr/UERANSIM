#pragma once

#include "rlc_utils.hpp"

namespace nr::rlc
{

class RlcEncoder
{
  public:
    static UmdPdu *DecodeUmd(uint8_t *data, int size, bool isShortSn);
    static int EncodeUmd(uint8_t *buffer, const UmdPdu &pdu, bool isShortSn);

    static AmdPdu *DecodeAmd(uint8_t *data, int size, bool isShortSn);
    static int EncodeAmd(uint8_t *buffer, const AmdPdu &pdu, bool isShortSn);

    static StatusPdu *DecodeStatus(uint8_t *data, int size, bool isShortSn);
    static int EncodeStatus(uint8_t *buffer, const StatusPdu &pdu, bool isShortSn);
};

} // namespace nr::rlc