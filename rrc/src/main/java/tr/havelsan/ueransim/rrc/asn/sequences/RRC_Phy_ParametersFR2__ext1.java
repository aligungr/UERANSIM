/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersFR2__ext1 extends RRC_Sequence {

    public RRC_Integer pCell_FR2;
    public RRC_Integer pdsch_RE_MappingFR2_PerSlot;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pCell-FR2","pdsch-RE-MappingFR2-PerSlot" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pCell_FR2","pdsch_RE_MappingFR2_PerSlot" };
    }

}
