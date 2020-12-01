/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_FeatureSetDownlink_v1540 extends RRC_Sequence {

    public RRC_Integer oneFL_DMRS_TwoAdditionalDMRS_DL;
    public RRC_Integer additionalDMRS_DL_Alt;
    public RRC_Integer twoFL_DMRS_TwoAdditionalDMRS_DL;
    public RRC_Integer oneFL_DMRS_ThreeAdditionalDMRS_DL;
    public RRC_FeatureSetDownlink_v1540__pdcch_MonitoringAnyOccasionsWithSpanGap pdcch_MonitoringAnyOccasionsWithSpanGap;
    public RRC_Integer pdsch_SeparationWithGap;
    public RRC_FeatureSetDownlink_v1540__pdsch_ProcessingType2 pdsch_ProcessingType2;
    public RRC_FeatureSetDownlink_v1540__pdsch_ProcessingType2_Limited pdsch_ProcessingType2_Limited;
    public RRC_Integer dl_MCS_TableAlt_DynamicIndication;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "oneFL-DMRS-TwoAdditionalDMRS-DL","additionalDMRS-DL-Alt","twoFL-DMRS-TwoAdditionalDMRS-DL","oneFL-DMRS-ThreeAdditionalDMRS-DL","pdcch-MonitoringAnyOccasionsWithSpanGap","pdsch-SeparationWithGap","pdsch-ProcessingType2","pdsch-ProcessingType2-Limited","dl-MCS-TableAlt-DynamicIndication" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "oneFL_DMRS_TwoAdditionalDMRS_DL","additionalDMRS_DL_Alt","twoFL_DMRS_TwoAdditionalDMRS_DL","oneFL_DMRS_ThreeAdditionalDMRS_DL","pdcch_MonitoringAnyOccasionsWithSpanGap","pdsch_SeparationWithGap","pdsch_ProcessingType2","pdsch_ProcessingType2_Limited","dl_MCS_TableAlt_DynamicIndication" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSetDownlink-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetDownlink-v1540";
    }

}
