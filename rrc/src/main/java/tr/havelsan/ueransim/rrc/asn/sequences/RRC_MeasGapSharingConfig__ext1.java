/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_MeasGapSharingScheme;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasGapSharingConfig__ext1 extends RRC_Sequence {

    public RRC_SetupRelease_MeasGapSharingScheme gapSharingFR1;
    public RRC_SetupRelease_MeasGapSharingScheme gapSharingUE;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "gapSharingFR1","gapSharingUE" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "gapSharingFR1","gapSharingUE" };
    }

}
