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

public class IwUplinkNas {
    public final UUID ue;
    public final OctetString nasPdu;

    public IwUplinkNas(UUID ue, OctetString nasPdu) {
        this.ue = ue;
        this.nasPdu = nasPdu;
    }
}
