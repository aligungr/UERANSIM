/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_RateMatchPattern__patternType__bitmaps__periodicityAndPattern;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_RateMatchPattern__patternType__bitmaps__symbolsInResourceBlock;
import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_RateMatchPattern__patternType__bitmaps extends RRC_Sequence {

    public RRC_BitString resourceBlocks;
    public RRC_RateMatchPattern__patternType__bitmaps__symbolsInResourceBlock symbolsInResourceBlock;
    public RRC_RateMatchPattern__patternType__bitmaps__periodicityAndPattern periodicityAndPattern;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "resourceBlocks","symbolsInResourceBlock","periodicityAndPattern" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "resourceBlocks","symbolsInResourceBlock","periodicityAndPattern" };
    }

}
