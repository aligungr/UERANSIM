/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
