/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_BetaOffsets extends RRC_Sequence {

    public RRC_Integer betaOffsetACK_Index1;
    public RRC_Integer betaOffsetACK_Index2;
    public RRC_Integer betaOffsetACK_Index3;
    public RRC_Integer betaOffsetCSI_Part1_Index1;
    public RRC_Integer betaOffsetCSI_Part1_Index2;
    public RRC_Integer betaOffsetCSI_Part2_Index1;
    public RRC_Integer betaOffsetCSI_Part2_Index2;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "betaOffsetACK-Index1","betaOffsetACK-Index2","betaOffsetACK-Index3","betaOffsetCSI-Part1-Index1","betaOffsetCSI-Part1-Index2","betaOffsetCSI-Part2-Index1","betaOffsetCSI-Part2-Index2" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "betaOffsetACK_Index1","betaOffsetACK_Index2","betaOffsetACK_Index3","betaOffsetCSI_Part1_Index1","betaOffsetCSI_Part1_Index2","betaOffsetCSI_Part2_Index1","betaOffsetCSI_Part2_Index2" };
    }

    @Override
    public String getAsnName() {
        return "BetaOffsets";
    }

    @Override
    public String getXmlTagName() {
        return "BetaOffsets";
    }

}
