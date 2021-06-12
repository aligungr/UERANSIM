//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "task.hpp"

#include <lib/rrc/encode.hpp>
#include <ue/nas/task.hpp>

namespace nr::ue
{

void UeRrcTask::performUac(std::shared_ptr<LightSync<UacInput, UacOutput>> &uacCtl)
{
    auto &input = uacCtl->input();

    m_establishmentCause = input.establishmentCause;

    auto output = std::make_unique<UacOutput>();
    output->res = EUacResult::ALLOWED; // TODO

    uacCtl->notifyProcessed(std::move(output));
}

} // namespace nr::ue
