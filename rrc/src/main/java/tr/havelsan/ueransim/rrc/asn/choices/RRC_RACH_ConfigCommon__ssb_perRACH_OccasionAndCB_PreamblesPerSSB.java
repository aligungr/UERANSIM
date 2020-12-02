/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_RACH_ConfigCommon__ssb_perRACH_OccasionAndCB_PreamblesPerSSB extends RRC_Choice {

    public RRC_Integer oneEighth;
    public RRC_Integer oneFourth;
    public RRC_Integer oneHalf;
    public RRC_Integer one;
    public RRC_Integer two;
    public RRC_Integer four;
    public RRC_Integer eight;
    public RRC_Integer sixteen;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "oneEighth","oneFourth","oneHalf","one","two","four","eight","sixteen" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "oneEighth","oneFourth","oneHalf","one","two","four","eight","sixteen" };
    }

}
