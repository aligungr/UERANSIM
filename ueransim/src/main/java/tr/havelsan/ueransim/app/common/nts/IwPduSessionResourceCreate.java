/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.app.common.PduSessionResource;

public class IwPduSessionResourceCreate {
    public final PduSessionResource pduSessionResource;

    public IwPduSessionResourceCreate(PduSessionResource pduSessionResource) {
        this.pduSessionResource = pduSessionResource;
    }
}
