/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_BitString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;

public class RRC_SSB_ToMeasure extends RRC_Choice {

    public RRC_BitString shortBitmap;
    public RRC_BitString mediumBitmap;
    public RRC_BitString longBitmap;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "shortBitmap","mediumBitmap","longBitmap" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "shortBitmap","mediumBitmap","longBitmap" };
    }

    @Override
    public String getAsnName() {
        return "SSB-ToMeasure";
    }

    @Override
    public String getXmlTagName() {
        return "SSB-ToMeasure";
    }

}
