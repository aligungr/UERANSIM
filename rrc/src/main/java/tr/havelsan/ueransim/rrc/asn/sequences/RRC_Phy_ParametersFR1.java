/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersFR1 extends RRC_Sequence {

    public RRC_Integer pdcch_MonitoringSingleOccasion;
    public RRC_Integer scs_60kHz;
    public RRC_Integer pdsch_256QAM_FR1;
    public RRC_Integer pdsch_RE_MappingFR1_PerSymbol;
    public RRC_Phy_ParametersFR1__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pdcch-MonitoringSingleOccasion","scs-60kHz","pdsch-256QAM-FR1","pdsch-RE-MappingFR1-PerSymbol","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pdcch_MonitoringSingleOccasion","scs_60kHz","pdsch_256QAM_FR1","pdsch_RE_MappingFR1_PerSymbol","ext1" };
    }

    @Override
    public String getAsnName() {
        return "Phy-ParametersFR1";
    }

    @Override
    public String getXmlTagName() {
        return "Phy-ParametersFR1";
    }

}
