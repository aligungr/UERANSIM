/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.itms;

import tr.havelsan.ueransim.app.common.Guami;
import tr.havelsan.ueransim.sctp.SctpAssociation;

public class IwSctpAssociationSetup {
    public final Guami guami;
    public final SctpAssociation association;

    public IwSctpAssociationSetup(Guami guami, SctpAssociation association) {
        this.guami = guami;
        this.association = association;
    }
}
