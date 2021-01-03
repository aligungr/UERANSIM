/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
