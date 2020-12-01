/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_Alpha;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.*;

public class RRC_PUSCH_PowerControl extends RRC_Sequence {

    public RRC_Integer tpc_Accumulation;
    public RRC_Alpha msg3_Alpha;
    public RRC_Integer p0_NominalWithoutGrant;
    public RRC_PUSCH_PowerControl__p0_AlphaSets p0_AlphaSets;
    public RRC_PUSCH_PowerControl__pathlossReferenceRSToAddModList pathlossReferenceRSToAddModList;
    public RRC_PUSCH_PowerControl__pathlossReferenceRSToReleaseList pathlossReferenceRSToReleaseList;
    public RRC_Integer twoPUSCH_PC_AdjustmentStates;
    public RRC_Integer deltaMCS;
    public RRC_PUSCH_PowerControl__sri_PUSCH_MappingToAddModList sri_PUSCH_MappingToAddModList;
    public RRC_PUSCH_PowerControl__sri_PUSCH_MappingToReleaseList sri_PUSCH_MappingToReleaseList;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "tpc-Accumulation","msg3-Alpha","p0-NominalWithoutGrant","p0-AlphaSets","pathlossReferenceRSToAddModList","pathlossReferenceRSToReleaseList","twoPUSCH-PC-AdjustmentStates","deltaMCS","sri-PUSCH-MappingToAddModList","sri-PUSCH-MappingToReleaseList" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "tpc_Accumulation","msg3_Alpha","p0_NominalWithoutGrant","p0_AlphaSets","pathlossReferenceRSToAddModList","pathlossReferenceRSToReleaseList","twoPUSCH_PC_AdjustmentStates","deltaMCS","sri_PUSCH_MappingToAddModList","sri_PUSCH_MappingToReleaseList" };
    }

    @Override
    public String getAsnName() {
        return "PUSCH-PowerControl";
    }

    @Override
    public String getXmlTagName() {
        return "PUSCH-PowerControl";
    }

}
