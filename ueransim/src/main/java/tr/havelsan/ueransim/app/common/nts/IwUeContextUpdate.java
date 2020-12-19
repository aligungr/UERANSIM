/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.ngap0.ies.sequences.NGAP_UEAggregateMaximumBitRate;

import java.util.UUID;

public class IwUeContextUpdate {
    public final UUID ueId;
    public final NGAP_UEAggregateMaximumBitRate ambr;

    public IwUeContextUpdate(UUID ueId, NGAP_UEAggregateMaximumBitRate ambr) {
        this.ueId = ueId;
        this.ambr = ambr;
    }
}
