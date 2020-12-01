/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_ReportConfigToAddMod__reportConfig;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReportConfigId;

public class RRC_ReportConfigToAddMod extends RRC_Sequence {

    public RRC_ReportConfigId reportConfigId;
    public RRC_ReportConfigToAddMod__reportConfig reportConfig;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportConfigId","reportConfig" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportConfigId","reportConfig" };
    }

    @Override
    public String getAsnName() {
        return "ReportConfigToAddMod";
    }

    @Override
    public String getXmlTagName() {
        return "ReportConfigToAddMod";
    }

}
