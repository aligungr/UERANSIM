/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_OtherConfig__delayBudgetReportingConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_OtherConfig extends RRC_Sequence {

    public RRC_OtherConfig__delayBudgetReportingConfig delayBudgetReportingConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "delayBudgetReportingConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "delayBudgetReportingConfig" };
    }

    @Override
    public String getAsnName() {
        return "OtherConfig";
    }

    @Override
    public String getXmlTagName() {
        return "OtherConfig";
    }

}
