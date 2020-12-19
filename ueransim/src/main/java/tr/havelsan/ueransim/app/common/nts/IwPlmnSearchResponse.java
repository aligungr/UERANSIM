/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import java.util.UUID;

public class IwPlmnSearchResponse {
    public final UUID gnbId;

    public IwPlmnSearchResponse(UUID gnbId) {
        this.gnbId = gnbId;
    }
}
