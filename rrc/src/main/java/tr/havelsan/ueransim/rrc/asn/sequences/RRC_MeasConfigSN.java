/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasConfigSN__measuredFrequenciesSN;

public class RRC_MeasConfigSN extends RRC_Sequence {

    public RRC_MeasConfigSN__measuredFrequenciesSN measuredFrequenciesSN;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measuredFrequenciesSN" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measuredFrequenciesSN" };
    }

    @Override
    public String getAsnName() {
        return "MeasConfigSN";
    }

    @Override
    public String getXmlTagName() {
        return "MeasConfigSN";
    }

}
