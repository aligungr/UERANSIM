/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.app.common.PduSession;

public class IwUeConnectionSetup {
    public final PduSession pduSession;

    public IwUeConnectionSetup(PduSession pduSession) {
        this.pduSession = pduSession;
    }
}
