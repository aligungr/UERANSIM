/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_RS_Index;

public class RRC_ResultsPerCSI_RS_Index extends AsnSequence {
    public RRC_CSI_RS_Index csi_RS_Index; // mandatory
    public RRC_MeasQuantityResults csi_RS_Results; // optional
}

