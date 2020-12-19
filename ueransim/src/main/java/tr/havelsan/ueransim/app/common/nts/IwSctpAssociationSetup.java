/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.nts;

import tr.havelsan.ueransim.sctp.SctpAssociation;

import java.util.UUID;

public class IwSctpAssociationSetup {
    public final UUID amfId;
    public final SctpAssociation association;

    public IwSctpAssociationSetup(UUID amfId, SctpAssociation association) {
        this.amfId = amfId;
        this.association = association;
    }
}
