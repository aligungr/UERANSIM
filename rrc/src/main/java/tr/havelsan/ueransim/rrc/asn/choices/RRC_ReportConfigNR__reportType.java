/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_EventTriggerConfig;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PeriodicalReportConfig;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ReportCGI;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_ReportConfigNR__reportType__ext1;

public class RRC_ReportConfigNR__reportType extends RRC_Choice {

    public RRC_PeriodicalReportConfig periodical;
    public RRC_EventTriggerConfig eventTriggered;
    public RRC_ReportCGI reportCGI;
    public RRC_ReportConfigNR__reportType__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "periodical","eventTriggered","reportCGI","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "periodical","eventTriggered","reportCGI","ext1" };
    }

}
