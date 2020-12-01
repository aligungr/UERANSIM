/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_UCI_OnPUSCH__betaOffsets;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_UCI_OnPUSCH extends RRC_Sequence {

    public RRC_UCI_OnPUSCH__betaOffsets betaOffsets;
    public RRC_Integer scaling;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "betaOffsets","scaling" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "betaOffsets","scaling" };
    }

    @Override
    public String getAsnName() {
        return "UCI-OnPUSCH";
    }

    @Override
    public String getXmlTagName() {
        return "UCI-OnPUSCH";
    }

}
