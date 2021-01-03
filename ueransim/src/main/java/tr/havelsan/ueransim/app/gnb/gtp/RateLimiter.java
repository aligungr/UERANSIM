/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.gtp;

import tr.havelsan.ueransim.app.common.PduSessionResource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class RateLimiter implements IRateLimiter {
    private final Map<UUID, TokenBucket> downlinkByUe;
    private final Map<UUID, TokenBucket> uplinkByUe;
    private final Map<PduSessionResource, TokenBucket> downlinkBySession;
    private final Map<PduSessionResource, TokenBucket> uplinkBySession;

    public RateLimiter() {
        downlinkByUe = new HashMap<>();
        uplinkByUe = new HashMap<>();
        downlinkBySession = new HashMap<>();
        uplinkBySession = new HashMap<>();
    }

    @Override
    public boolean allowDownlinkPacket(PduSessionResource pduSession, long packetSize) {
        var bucket = downlinkByUe.get(pduSession.ueId);
        if (bucket != null && !bucket.tryConsume(packetSize)) {
            return false;
        }

        bucket = downlinkBySession.get(pduSession);
        return bucket == null || bucket.tryConsume(packetSize);
    }

    @Override
    public boolean allowUplinkPacket(PduSessionResource pduSession, long packetSize) {
        var bucket = uplinkByUe.get(pduSession.ueId);
        if (bucket != null && !bucket.tryConsume(packetSize)) {
            return false;
        }

        bucket = uplinkBySession.get(pduSession);
        return bucket == null || bucket.tryConsume(packetSize);
    }

    @Override
    public void updateUeUplinkLimit(UUID ueId, long limit) {
        var bucket = uplinkByUe.get(ueId);
        if (bucket != null) bucket.updateCapacity(limit);
        else {
            bucket = new TokenBucket(limit);
            uplinkByUe.put(ueId, bucket);
        }
    }

    @Override
    public void updateUeDownlinkLimit(UUID ueId, long limit) {
        var bucket = downlinkByUe.get(ueId);
        if (bucket != null) bucket.updateCapacity(limit);
        else {
            bucket = new TokenBucket(limit);
            downlinkByUe.put(ueId, bucket);
        }
    }

    @Override
    public void updateSessionUplinkLimit(PduSessionResource pduSession, long limit) {
        var bucket = uplinkBySession.get(pduSession);
        if (bucket != null) bucket.updateCapacity(limit);
        else {
            bucket = new TokenBucket(limit);
            uplinkBySession.put(pduSession, bucket);
        }
    }

    @Override
    public void updateSessionDownlinkLimit(PduSessionResource pduSession, long limit) {
        var bucket = downlinkBySession.get(pduSession);
        if (bucket != null) bucket.updateCapacity(limit);
        else {
            bucket = new TokenBucket(limit);
            downlinkBySession.put(pduSession, bucket);
        }
    }
}
