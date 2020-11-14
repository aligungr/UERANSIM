/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import java.util.UUID;

public class IwConnectionRelease {
    public final UUID ue;

    public IwConnectionRelease(UUID ue) {
        this.ue = ue;
    }
}
