/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

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
