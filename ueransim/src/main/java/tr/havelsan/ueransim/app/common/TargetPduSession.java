/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
