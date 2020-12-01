/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_DataInactivityTimer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Boolean;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MAC_CellGroupConfig__ext1 extends RRC_Sequence {

    public RRC_Boolean csi_Mask;
    public RRC_SetupRelease_DataInactivityTimer dataInactivityTimer;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "csi-Mask","dataInactivityTimer" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "csi_Mask","dataInactivityTimer" };
    }

    @Override
    public String getAsnName() {
        throw new IllegalStateException("ASN.1 name is treated null for anonymous types.");
    }

    @Override
    public String getXmlTagName() {
        throw new IllegalStateException("XML tag name is treated null for anonymous types.");
    }

}
