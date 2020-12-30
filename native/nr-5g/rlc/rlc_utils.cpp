#include "rlc_utils.hpp"
#include "rlc_encoder.hpp"

int nr::rlc::StatusPdu::calculatedSize(bool isShortSn) const
{
    // TODO optimize. this is waay slow and just for POC
    uint8_t buffer[32768];
    return RlcEncoder::EncodeStatus(buffer, *this, isShortSn);
}
