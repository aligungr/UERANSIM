/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.utils.octets.OctetString;

import java.util.UUID;

public class IwDownlinkData {
    public final UUID ueId;
    public final int pduSessionId;
    public final OctetString ipPacket;

    public IwDownlinkData(UUID ueId, int pduSessionId, OctetString ipPacket) {
        this.ueId = ueId;
        this.pduSessionId = pduSessionId;
        this.ipPacket = ipPacket;
    }
}
