/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.app.common.PduSession;

import java.util.UUID;

public class IwPduSessionEstablishment {

    public final UUID ueId;
    public final PduSession pduSession;

    public IwPduSessionEstablishment(UUID ueId, PduSession pduSession) {
        this.ueId = ueId;
        this.pduSession = pduSession;
    }
}
