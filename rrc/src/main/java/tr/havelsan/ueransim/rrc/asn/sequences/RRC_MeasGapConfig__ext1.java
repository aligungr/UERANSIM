/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_GapConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasGapConfig__ext1 extends RRC_Sequence {

    public RRC_SetupRelease_GapConfig gapFR1;
    public RRC_SetupRelease_GapConfig gapUE;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "gapFR1","gapUE" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "gapFR1","gapUE" };
    }

}
