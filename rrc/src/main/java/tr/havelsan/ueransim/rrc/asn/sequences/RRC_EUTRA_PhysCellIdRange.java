/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;

public class RRC_EUTRA_PhysCellIdRange extends RRC_Sequence {

    public RRC_EUTRA_PhysCellId start;
    public RRC_Integer range;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "start","range" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "start","range" };
    }

    @Override
    public String getAsnName() {
        return "EUTRA-PhysCellIdRange";
    }

    @Override
    public String getXmlTagName() {
        return "EUTRA-PhysCellIdRange";
    }

}
