/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_SSB_MTC__periodicityAndOffset extends RRC_Choice {

    public RRC_Integer sf5;
    public RRC_Integer sf10;
    public RRC_Integer sf20;
    public RRC_Integer sf40;
    public RRC_Integer sf80;
    public RRC_Integer sf160;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sf5","sf10","sf20","sf40","sf80","sf160" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sf5","sf10","sf20","sf40","sf80","sf160" };
    }

}
