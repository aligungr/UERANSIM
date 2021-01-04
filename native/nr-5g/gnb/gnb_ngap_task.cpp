//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "gnb_ngap_task.hpp"

namespace nr::gnb
{

NgapTask::NgapTask(GnbConfig *config, logger::LogBase &loggerBase) : config{config}, sctpTask{}
{
    logger = loggerBase.makeUniqueLogger("ngap");
}

void NgapTask::setExternalTasks(SctpTask *sctp)
{
    this->sctpTask = sctp;
}

void NgapTask::onStart()
{
    logger->debug("NGAP task has been started");
}

void NgapTask::onLoop()
{
}

void NgapTask::onQuit()
{
}

NgapAmfContext *NgapTask::findAmfContext(int ctxId)
{
    NgapAmfContext *ctx = nullptr;
    if (amfContexts.count(ctxId))
        ctx = amfContexts[ctxId];
    if (ctx == nullptr)
        logger->err("AMF context not found with id: %d", ctxId);
    return nullptr;
}

} // namespace nr::gnb
