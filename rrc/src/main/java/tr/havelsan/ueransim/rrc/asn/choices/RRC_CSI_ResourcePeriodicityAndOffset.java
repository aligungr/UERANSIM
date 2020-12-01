/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_CSI_ResourcePeriodicityAndOffset extends RRC_Choice {

    public RRC_Integer slots4;
    public RRC_Integer slots5;
    public RRC_Integer slots8;
    public RRC_Integer slots10;
    public RRC_Integer slots16;
    public RRC_Integer slots20;
    public RRC_Integer slots32;
    public RRC_Integer slots40;
    public RRC_Integer slots64;
    public RRC_Integer slots80;
    public RRC_Integer slots160;
    public RRC_Integer slots320;
    public RRC_Integer slots640;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "slots4","slots5","slots8","slots10","slots16","slots20","slots32","slots40","slots64","slots80","slots160","slots320","slots640" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "slots4","slots5","slots8","slots10","slots16","slots20","slots32","slots40","slots64","slots80","slots160","slots320","slots640" };
    }

    @Override
    public String getAsnName() {
        return "CSI-ResourcePeriodicityAndOffset";
    }

    @Override
    public String getXmlTagName() {
        return "CSI-ResourcePeriodicityAndOffset";
    }

}
