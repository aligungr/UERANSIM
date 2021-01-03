/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
