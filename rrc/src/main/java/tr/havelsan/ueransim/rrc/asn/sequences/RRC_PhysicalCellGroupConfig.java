/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_SetupRelease_RNTI_Value;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P_Max;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RNTI_Value;

public class RRC_PhysicalCellGroupConfig extends RRC_Sequence {

    public RRC_Integer harq_ACK_SpatialBundlingPUCCH;
    public RRC_Integer harq_ACK_SpatialBundlingPUSCH;
    public RRC_P_Max p_NR_FR1;
    public RRC_Integer pdsch_HARQ_ACK_Codebook;
    public RRC_RNTI_Value tpc_SRS_RNTI;
    public RRC_RNTI_Value tpc_PUCCH_RNTI;
    public RRC_RNTI_Value tpc_PUSCH_RNTI;
    public RRC_RNTI_Value sp_CSI_RNTI;
    public RRC_SetupRelease_RNTI_Value cs_RNTI;
    public RRC_PhysicalCellGroupConfig__ext1 ext1;
    public RRC_PhysicalCellGroupConfig__ext2 ext2;
    public RRC_PhysicalCellGroupConfig__ext3 ext3;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "harq-ACK-SpatialBundlingPUCCH","harq-ACK-SpatialBundlingPUSCH","p-NR-FR1","pdsch-HARQ-ACK-Codebook","tpc-SRS-RNTI","tpc-PUCCH-RNTI","tpc-PUSCH-RNTI","sp-CSI-RNTI","cs-RNTI","ext1","ext2","ext3" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "harq_ACK_SpatialBundlingPUCCH","harq_ACK_SpatialBundlingPUSCH","p_NR_FR1","pdsch_HARQ_ACK_Codebook","tpc_SRS_RNTI","tpc_PUCCH_RNTI","tpc_PUSCH_RNTI","sp_CSI_RNTI","cs_RNTI","ext1","ext2","ext3" };
    }

    @Override
    public String getAsnName() {
        return "PhysicalCellGroupConfig";
    }

    @Override
    public String getXmlTagName() {
        return "PhysicalCellGroupConfig";
    }

}
