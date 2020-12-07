/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_ReportConfigId;

public class RRC_CSI_SemiPersistentOnPUSCH_TriggerState extends AsnSequence {
    public RRC_CSI_ReportConfigId associatedReportConfigInfo; // mandatory
}

