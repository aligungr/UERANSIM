/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_GapConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasConfigMN__measuredFrequenciesMN;

public class RRC_MeasConfigMN extends RRC_Sequence {

    public RRC_MeasConfigMN__measuredFrequenciesMN measuredFrequenciesMN;
    public RRC_SetupRelease_GapConfig measGapConfig;
    public RRC_Integer gapPurpose;
    public RRC_MeasConfigMN__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measuredFrequenciesMN","measGapConfig","gapPurpose","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measuredFrequenciesMN","measGapConfig","gapPurpose","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MeasConfigMN";
    }

    @Override
    public String getXmlTagName() {
        return "MeasConfigMN";
    }

}
