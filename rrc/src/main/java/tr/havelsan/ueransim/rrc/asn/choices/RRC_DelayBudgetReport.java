/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;

public class RRC_DelayBudgetReport extends RRC_Choice {

    public RRC_Integer type1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "type1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "type1" };
    }

    @Override
    public String getAsnName() {
        return "DelayBudgetReport";
    }

    @Override
    public String getXmlTagName() {
        return "DelayBudgetReport";
    }

}
