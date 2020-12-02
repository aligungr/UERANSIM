/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_EventTriggerConfigInterRAT;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PeriodicalReportConfigInterRAT;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ReportCGI_EUTRA;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ReportSFTD_EUTRA;

public class RRC_ReportConfigInterRAT__reportType extends RRC_Choice {

    public RRC_PeriodicalReportConfigInterRAT periodical;
    public RRC_EventTriggerConfigInterRAT eventTriggered;
    public RRC_ReportCGI_EUTRA reportCGI;
    public RRC_ReportSFTD_EUTRA reportSFTD;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "periodical","eventTriggered","reportCGI","reportSFTD" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "periodical","eventTriggered","reportCGI","reportSFTD" };
    }

}
