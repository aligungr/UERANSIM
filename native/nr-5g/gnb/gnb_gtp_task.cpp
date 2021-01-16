//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_gtp_task.hpp"

namespace nr::gnb
{

GtpTask::GtpTask(logger::LogBase &loggerBase)
{
    logger = loggerBase.makeUniqueLogger("gtp");

}

void GtpTask::onStart()
{
    logger->debug("GTP layer has been started");
}

void GtpTask::onLoop()
{
}

void GtpTask::onQuit()
{
}

} // namespace nr::gnb
