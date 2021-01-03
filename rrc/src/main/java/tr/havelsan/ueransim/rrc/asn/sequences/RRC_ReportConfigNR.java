/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_ReportConfigNR extends AsnSequence {
    public RRC_reportType_2 reportType; // mandatory

    public static class RRC_reportType_2 extends AsnChoice {
        public RRC_PeriodicalReportConfig periodical;
        public RRC_EventTriggerConfig eventTriggered;
        public RRC_ReportCGI reportCGI;
        public RRC_ext1_18 ext1;
    
        public static class RRC_ext1_18 extends AsnSequence {
            public RRC_ReportSFTD_NR reportSFTD; // mandatory
        }
    }
}

