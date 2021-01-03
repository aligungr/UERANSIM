/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.gnb.gtp;

import tr.havelsan.ueransim.app.common.PduSessionResource;

import java.util.UUID;

/**
 * Interface for RateLimiter.
 */
interface IRateLimiter {

    /**
     * Handles downlink packets.
     *
     * @param pduSession the PDU session of which packets must be managed
     * @param packetSize size of the relevant packet in bytes
     */
    boolean allowDownlinkPacket(PduSessionResource pduSession, long packetSize);

    /**
     * Handles uplink packets.
     *
     * @param pduSession the PDU session of which packets must be managed
     * @param packetSize size of the relevant packet in bytes
     */
    boolean allowUplinkPacket(PduSessionResource pduSession, long packetSize);

    /**
     * Insert or update uplink limits based on the UE.
     *
     * @param ueId  the new PDU session.
     * @param limit uplink limit in bytes for the relevant UE. (-1 for infinite)
     */
    void updateUeUplinkLimit(UUID ueId, long limit);

    /**
     * Insert or update downlink limits based on the UE.
     *
     * @param ueId  the new PDU session.
     * @param limit downlink limit in bytes for the relevant UE. (-1 for infinite)
     */
    void updateUeDownlinkLimit(UUID ueId, long limit);

    /**
     * Insert or update uplink limits based on the PDU Session.
     *
     * @param pduSession the new PDU session.
     * @param limit      uplink limit in bytes for the relevant UE. (-1 for infinite)
     */
    void updateSessionUplinkLimit(PduSessionResource pduSession, long limit);

    /**
     * Insert or update downlink limits based on the PDU Session.
     *
     * @param pduSession the new PDU session.
     * @param limit      downlink limit in bytes for the relevant UE. (-1 for infinite)
     */
    void updateSessionDownlinkLimit(PduSessionResource pduSession, long limit);
}
