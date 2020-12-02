/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ReportConfigInterRAT;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ReportConfigNR;

public class RRC_ReportConfigToAddMod__reportConfig extends RRC_Choice {

    public RRC_ReportConfigNR reportConfigNR;
    public RRC_ReportConfigInterRAT reportConfigInterRAT;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "reportConfigNR","reportConfigInterRAT" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "reportConfigNR","reportConfigInterRAT" };
    }

}
