//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "ue_tun_task.hpp"

namespace nr::ue
{

ue::TunTask::TunTask(NtsTask *appTask, int psi, int fd) : appTask{appTask}, psi{psi}, fd{fd}
{
}

void TunTask::onStart()
{
}

void TunTask::onQuit()
{
}

void TunTask::onLoop()
{
}

} // namespace nr::ue