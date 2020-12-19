/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.rrc.RrcMessage;

import java.util.UUID;

public class IwUplinkRrc {
    public final UUID ueId;
    public final RrcMessage rrcMessage;

    public IwUplinkRrc(UUID ueId, RrcMessage rrcMessage) {
        this.ueId = ueId;
        this.rrcMessage = rrcMessage;
    }
}
