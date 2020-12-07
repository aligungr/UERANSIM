/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_NZP_CSI_RS_ResourceId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUSCH_PathlossReferenceRS_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_PUSCH_PathlossReferenceRS extends AsnSequence {
    public RRC_PUSCH_PathlossReferenceRS_Id pusch_PathlossReferenceRS_Id; // mandatory
    public RRC_referenceSignal_3 referenceSignal; // mandatory

    public static class RRC_referenceSignal_3 extends AsnChoice {
        public RRC_SSB_Index ssb_Index;
        public RRC_NZP_CSI_RS_ResourceId csi_RS_Index;
    }
}

