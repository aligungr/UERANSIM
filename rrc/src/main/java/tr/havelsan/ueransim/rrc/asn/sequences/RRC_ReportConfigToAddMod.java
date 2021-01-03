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
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ReportConfigId;

public class RRC_ReportConfigToAddMod extends AsnSequence {
    public RRC_ReportConfigId reportConfigId; // mandatory
    public RRC_reportConfig reportConfig; // mandatory

    public static class RRC_reportConfig extends AsnChoice {
        public RRC_ReportConfigNR reportConfigNR;
        public RRC_ReportConfigInterRAT reportConfigInterRAT;
    }
}

