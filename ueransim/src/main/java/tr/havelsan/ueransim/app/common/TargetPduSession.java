/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common;

import java.util.UUID;

public class TargetPduSession {
    public final UUID ueId;
    public final int psi;

    public TargetPduSession(UUID ueId, int psi) {
        this.ueId = ueId;
        this.psi = psi;
    }
}
