/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_Phy_ParametersXDD_Diff__ext1 extends RRC_Sequence {

    public RRC_Integer dl_SchedulingOffset_PDSCH_TypeA;
    public RRC_Integer dl_SchedulingOffset_PDSCH_TypeB;
    public RRC_Integer ul_SchedulingOffset;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "dl-SchedulingOffset-PDSCH-TypeA","dl-SchedulingOffset-PDSCH-TypeB","ul-SchedulingOffset" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "dl_SchedulingOffset_PDSCH_TypeA","dl_SchedulingOffset_PDSCH_TypeB","ul_SchedulingOffset" };
    }

}
