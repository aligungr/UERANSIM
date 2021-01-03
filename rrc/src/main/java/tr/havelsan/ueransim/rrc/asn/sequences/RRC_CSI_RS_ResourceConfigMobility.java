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
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_CSI_RS_ResourceConfigMobility extends AsnSequence {
    public RRC_SubcarrierSpacing subcarrierSpacing; // mandatory
    public RRC_csi_RS_CellList_Mobility csi_RS_CellList_Mobility; // mandatory, SIZE(1..96)
    public RRC_ext1_20 ext1; // optional

    public static class RRC_ext1_20 extends AsnSequence {
        public RRC_ServCellIndex refServCellIndex_v1530; // optional
    }

    // SIZE(1..96)
    public static class RRC_csi_RS_CellList_Mobility extends AsnSequenceOf<RRC_CSI_RS_CellMobility> {
    }
}

