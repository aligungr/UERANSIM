/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasTiming extends RRC_Sequence {

    public RRC_MeasTiming__frequencyAndTiming frequencyAndTiming;
    public RRC_MeasTiming__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "frequencyAndTiming","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "frequencyAndTiming","ext1" };
    }

    @Override
    public String getAsnName() {
        return "MeasTiming";
    }

    @Override
    public String getXmlTagName() {
        return "MeasTiming";
    }

}
