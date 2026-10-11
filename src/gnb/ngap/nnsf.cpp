//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "task.hpp"

namespace nr::gnb
{

// Does this AMF serve the GUAMI that allocated the given 5G-S-TMSI? The AMF Set ID and
// AMF Pointer are exactly the part of the GUAMI a 5G-S-TMSI carries.
static bool servesTmsi(const NgapAmfContext *amf, const GutiMobileIdentity &sTmsi)
{
    for (const auto &served : amf->servedGuamiList)
    {
        if (served == nullptr)
            continue;

        if (served->guami.amfSetId == sTmsi.amfSetId && served->guami.amfPointer == sTmsi.amfPointer)
            return true;
    }

    return false;
}

static bool supportsSlice(const NgapAmfContext *amf, int32_t requestedSliceType)
{
    for (const auto &plmnSupport : amf->plmnSupportList)
    {
        if (plmnSupport == nullptr)
            continue;

        for (const auto &singleSlice : plmnSupport->sliceSupportList.slices)
        {
            if (static_cast<int32_t>(singleSlice.sst) == requestedSliceType)
                return true;
        }
    }

    return false;
}

NgapAmfContext *NgapTask::selectAmf(int ueId, int32_t requestedSliceType,
                                    const std::optional<GutiMobileIdentity> &sTmsi)
{
    // Only a Registration Request carries a requested NSSAI, so requestedSliceType is
    // unset (-1) for every other Initial NAS message -- a Service Request answering a
    // Paging, for one. Matching on slice alone finds nothing for those, and returning
    // nullptr makes the caller drop the NAS PDU, so the UE stays unreachable and neither
    // side is told why.
    //
    // Prefer the AMF that allocated the UE's 5G-S-TMSI, which is the one holding its
    // context; then an AMF supporting the requested slice; then any reachable AMF, since
    // a best-effort choice delivers the message and dropping it never can.
    NgapAmfContext *byTmsi = nullptr;
    NgapAmfContext *bySlice = nullptr;
    NgapAmfContext *anyConnected = nullptr;
    NgapAmfContext *anyKnown = nullptr;

    for (auto &entry : m_amfCtx)
    {
        auto *amf = entry.second;
        if (amf == nullptr)
            continue;

        if (anyKnown == nullptr)
            anyKnown = amf;

        if (amf->state != EAmfState::CONNECTED)
            continue;

        if (anyConnected == nullptr)
            anyConnected = amf;

        if (sTmsi.has_value() && byTmsi == nullptr && servesTmsi(amf, *sTmsi))
            byTmsi = amf;

        if (bySlice == nullptr && supportsSlice(amf, requestedSliceType))
            bySlice = amf;
    }

    if (byTmsi != nullptr)
        return byTmsi;

    if (bySlice != nullptr)
        return bySlice;

    if (anyConnected != nullptr)
    {
        m_logger->debug("No AMF matched UE[%d] by GUAMI or slice sst[%d], selecting AMF[%d]", ueId,
                        requestedSliceType, anyConnected->ctxId);
        return anyConnected;
    }

    if (anyKnown != nullptr)
        m_logger->warn("No AMF is connected, selecting AMF[%d] for UE[%d]", anyKnown->ctxId, ueId);

    return anyKnown;
}

NgapAmfContext *NgapTask::selectNewAmfForReAllocation(int ueId, int initiatedAmfId, int amfSetId)
{
    // TODO an arbitrary AMF is selected for now
    return findAmfContext(initiatedAmfId);
}

} // namespace nr::gnb
