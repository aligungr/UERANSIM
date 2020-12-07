/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReportConfigId;

public class RRC_ReportConfigToAddMod extends AsnSequence {
    public RRC_ReportConfigId reportConfigId; // mandatory
    public RRC_reportConfig reportConfig; // mandatory

    public static class RRC_reportConfig extends AsnChoice {
        public RRC_ReportConfigNR reportConfigNR;
        public RRC_ReportConfigInterRAT reportConfigInterRAT;
    }
}

