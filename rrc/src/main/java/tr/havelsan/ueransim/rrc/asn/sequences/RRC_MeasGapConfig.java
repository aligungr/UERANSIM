/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_GapConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasGapConfig extends RRC_Sequence {

    public RRC_SetupRelease_GapConfig gapFR2;
    public RRC_MeasGapConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "gapFR2","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "gapFR2","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MeasGapConfig";
    }

    @Override
    public String getXmlTagName() {
        return "MeasGapConfig";
    }

}
