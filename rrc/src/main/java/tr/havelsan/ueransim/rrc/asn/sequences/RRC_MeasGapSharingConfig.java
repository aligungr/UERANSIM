/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_MeasGapSharingScheme;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasGapSharingConfig extends RRC_Sequence {

    public RRC_SetupRelease_MeasGapSharingScheme gapSharingFR2;
    public RRC_MeasGapSharingConfig__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "gapSharingFR2","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "gapSharingFR2","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MeasGapSharingConfig";
    }

    @Override
    public String getXmlTagName() {
        return "MeasGapSharingConfig";
    }

}
