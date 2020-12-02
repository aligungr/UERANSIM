/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetId;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_RateMatchPattern__patternType__bitmaps;

public class RRC_RateMatchPattern__patternType extends RRC_Choice {

    public RRC_RateMatchPattern__patternType__bitmaps bitmaps;
    public RRC_ControlResourceSetId controlResourceSet;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "bitmaps","controlResourceSet" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "bitmaps","controlResourceSet" };
    }

}
