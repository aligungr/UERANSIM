//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_app_task.hpp"

namespace nr::gnb
{

GnbAppTask::GnbAppTask(logger::LogBase &loggerBase)
{
    logger = loggerBase.makeUniqueLogger("gnb-app");
}

void GnbAppTask::onStart()
{
}

void GnbAppTask::onLoop()
{
}

void GnbAppTask::onQuit()
{
}

} // namespace nr::gnb
