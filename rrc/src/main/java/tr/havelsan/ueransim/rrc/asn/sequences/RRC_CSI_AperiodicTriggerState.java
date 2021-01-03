/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_CSI_AperiodicTriggerState extends AsnSequence {
    public RRC_associatedReportConfigInfoList associatedReportConfigInfoList; // mandatory, SIZE(1..16)

    // SIZE(1..16)
    public static class RRC_associatedReportConfigInfoList extends AsnSequenceOf<RRC_CSI_AssociatedReportConfigInfo> {
    }
}

