//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"
#include <ue/nts.hpp>
#include <ue/rrc/task.hpp>
#include <utils/common.hpp>

namespace nr::ue
{

void UeSasTask::receiveCellSelectionCommand(const GlobalNci &cellId, bool isSuitable)
{
    m_logger->err("TODO"); // TODO perform camp
}


} // namespace nr::ue
