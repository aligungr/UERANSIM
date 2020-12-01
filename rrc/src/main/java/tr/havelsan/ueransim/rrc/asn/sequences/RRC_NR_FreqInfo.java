/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ARFCN_ValueNR;

public class RRC_NR_FreqInfo extends RRC_Sequence {

    public RRC_ARFCN_ValueNR measuredFrequency;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "measuredFrequency" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "measuredFrequency" };
    }

    @Override
    public String getAsnName() {
        return "NR-FreqInfo";
    }

    @Override
    public String getXmlTagName() {
        return "NR-FreqInfo";
    }

}
