/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SSB_MTC__periodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_SSB_MTC extends RRC_Sequence {

    public RRC_SSB_MTC__periodicityAndOffset periodicityAndOffset;
    public RRC_Integer duration;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "periodicityAndOffset","duration" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "periodicityAndOffset","duration" };
    }

    @Override
    public String getAsnName() {
        return "SSB-MTC";
    }

    @Override
    public String getXmlTagName() {
        return "SSB-MTC";
    }

}
