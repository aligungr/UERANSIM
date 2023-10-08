//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "rls_pdu.hpp"

#include <lib/rrc/rrc.hpp>

namespace rls
{

struct PduInfo
{
    uint32_t id{};
    OctetString pdu;
    rrc::RrcChannel rrcChannel{};
    int64_t sentTime{};
    int endPointId{};
};

enum class ERlfCause
{
    PDU_ID_EXISTS,
    PDU_ID_FULL,
    SIGNAL_LOST_TO_CONNECTED_CELL
};

} // namespace rls