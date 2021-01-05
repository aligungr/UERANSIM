//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "convert.hpp"
#include "gnb_ngap_task.hpp"

namespace nr::gnb
{

NgapAmfContext *NgapTask::findAmfContext(int ctxId)
{
    NgapAmfContext *ctx = nullptr;
    if (amfContexts.count(ctxId))
        ctx = amfContexts[ctxId];
    if (ctx == nullptr)
        logger->err("AMF context not found with id: %d", ctxId);
    return ctx;
}

void NgapTask::createAmfContext(const GnbAmfConfig &conf)
{
    auto *ctx = new NgapAmfContext();
    ctx->ctxId = utils::NextId();
    ctx->state = EAmfState::NOT_CONNECTED;
    ctx->address = conf.address;
    ctx->port = conf.port;
    amfContexts[ctx->ctxId] = ctx;
}

} // namespace nr::gnb