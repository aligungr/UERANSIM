/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UEAggregateMaximumBitRate;

import java.util.UUID;

public class GtpUeContext {
    public final UUID ueId;
    public NGAP_UEAggregateMaximumBitRate ambr;

    public GtpUeContext(UUID ueId) {
        this.ueId = ueId;
    }
}
