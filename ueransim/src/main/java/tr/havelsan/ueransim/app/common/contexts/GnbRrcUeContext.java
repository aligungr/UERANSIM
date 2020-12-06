/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.contexts;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_InitialUE_Identity;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_EstablishmentCause;

import java.util.UUID;

public class GnbRrcUeContext {

    public final UUID ctxId;

    public RRC_InitialUE_Identity initialId;
    public RRC_EstablishmentCause establishmentCause;

    public GnbRrcUeContext(UUID ctxId) {
        this.ctxId = ctxId;
    }
}
