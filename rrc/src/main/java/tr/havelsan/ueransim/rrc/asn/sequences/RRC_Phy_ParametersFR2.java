/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersFR2 extends RRC_Sequence {

    public RRC_Integer dummy;
    public RRC_Integer pdsch_RE_MappingFR2_PerSymbol;
    public RRC_Phy_ParametersFR2__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dummy","pdsch-RE-MappingFR2-PerSymbol","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dummy","pdsch_RE_MappingFR2_PerSymbol","ext1" };
    }

    @Override
    public String getAsnName() {
        return "Phy-ParametersFR2";
    }

    @Override
    public String getXmlTagName() {
        return "Phy-ParametersFR2";
    }

}
