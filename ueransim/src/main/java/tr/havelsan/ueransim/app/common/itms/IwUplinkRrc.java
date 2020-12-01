/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Value;

import java.util.UUID;

public class IwUplinkRrc {
    public final UUID ueId;
    public final RRC_Value rrcMessage;

    public IwUplinkRrc(UUID ueId, RRC_Value rrcMessage) {
        this.ueId = ueId;
        this.rrcMessage = rrcMessage;
    }
}
