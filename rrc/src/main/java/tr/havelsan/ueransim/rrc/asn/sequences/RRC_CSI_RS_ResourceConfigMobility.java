/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
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

