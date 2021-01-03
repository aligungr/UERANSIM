/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
