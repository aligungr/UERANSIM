/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_BandCombinationInfoList;

public class RRC_ConfigRestrictInfoSCG extends RRC_Sequence {

    public RRC_BandCombinationInfoList allowedBC_ListMRDC;
    public RRC_ConfigRestrictInfoSCG__powerCoordination_FR1 powerCoordination_FR1;
    public RRC_ConfigRestrictInfoSCG__servCellIndexRangeSCG servCellIndexRangeSCG;
    public RRC_Integer maxMeasFreqsSCG;
    public RRC_Integer maxMeasIdentitiesSCG_NR;
    public RRC_ConfigRestrictInfoSCG__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "allowedBC-ListMRDC","powerCoordination-FR1","servCellIndexRangeSCG","maxMeasFreqsSCG","maxMeasIdentitiesSCG-NR","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "allowedBC_ListMRDC","powerCoordination_FR1","servCellIndexRangeSCG","maxMeasFreqsSCG","maxMeasIdentitiesSCG_NR","ext1" };
    }

    @Override
    public String getAsnName() {
        return "ConfigRestrictInfoSCG";
    }

    @Override
    public String getXmlTagName() {
        return "ConfigRestrictInfoSCG";
    }

}
