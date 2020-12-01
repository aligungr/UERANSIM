/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_P0_PUSCH_AlphaSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PUSCH_PathlossReferenceRS_Id;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRI_PUSCH_PowerControlId;

public class RRC_SRI_PUSCH_PowerControl extends RRC_Sequence {

    public RRC_SRI_PUSCH_PowerControlId sri_PUSCH_PowerControlId;
    public RRC_PUSCH_PathlossReferenceRS_Id sri_PUSCH_PathlossReferenceRS_Id;
    public RRC_P0_PUSCH_AlphaSetId sri_P0_PUSCH_AlphaSetId;
    public RRC_Integer sri_PUSCH_ClosedLoopIndex;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "sri-PUSCH-PowerControlId","sri-PUSCH-PathlossReferenceRS-Id","sri-P0-PUSCH-AlphaSetId","sri-PUSCH-ClosedLoopIndex" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "sri_PUSCH_PowerControlId","sri_PUSCH_PathlossReferenceRS_Id","sri_P0_PUSCH_AlphaSetId","sri_PUSCH_ClosedLoopIndex" };
    }

    @Override
    public String getAsnName() {
        return "SRI-PUSCH-PowerControl";
    }

    @Override
    public String getXmlTagName() {
        return "SRI-PUSCH-PowerControl";
    }

}
