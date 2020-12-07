/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_ReportConfigInterRAT extends AsnSequence {
    public RRC_reportType_1 reportType; // mandatory

    public static class RRC_reportType_1 extends AsnChoice {
        public RRC_PeriodicalReportConfigInterRAT periodical;
        public RRC_EventTriggerConfigInterRAT eventTriggered;
        public RRC_ReportCGI_EUTRA reportCGI;
        public RRC_ReportSFTD_EUTRA reportSFTD;
    }
}

