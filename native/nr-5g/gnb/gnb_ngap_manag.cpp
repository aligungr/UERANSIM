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

void NgapTask::createUeContext(int ueId)
{
    auto *ctx = new NgapUeContext();
    ctx->ctxId = ueId;
    ctx->amfUeNgapId = -1;
    ctx->ranUeNgapId = ++ueNgapIdCounter;

    ueContexts[ctx->ctxId] = ctx;

    // Select an AMF for the UE (if any)
    for (auto &amf : amfContexts)
    {
        // todo: an arbitrary AMF is selected for now
        ctx->associatedAmfId = amf.second->ctxId;
        break;
    }
}

NgapUeContext *NgapTask::findUeContext(int ctxId)
{
    NgapUeContext *ctx = nullptr;
    if (ueContexts.count(ctxId))
        ctx = ueContexts[ctxId];
    if (ctx == nullptr)
        logger->err("UE context not found with id: %d", ctxId);
    return ctx;
}

NgapUeContext *NgapTask::findUeByRanId(long ranUeNgapId)
{
    if (ranUeNgapId <= 0)
        return nullptr;
    // TODO: optimize
    for (auto &ue : ueContexts)
        if (ue.second->ranUeNgapId == ranUeNgapId)
            return ue.second;
    return nullptr;
}

NgapUeContext *NgapTask::findUeByAmfId(long amfUeNgapId)
{
    if (amfUeNgapId <= 0)
        return nullptr;
    // TODO: optimize
    for (auto &ue : ueContexts)
        if (ue.second->amfUeNgapId == amfUeNgapId)
            return ue.second;
    return nullptr;
}

} // namespace nr::gnb