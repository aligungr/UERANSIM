/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_FeatureSetUplink_v1540 extends RRC_Sequence {

    public RRC_Integer zeroSlotOffsetAperiodicSRS;
    public RRC_Integer pa_PhaseDiscontinuityImpacts;
    public RRC_Integer pusch_SeparationWithGap;
    public RRC_FeatureSetUplink_v1540__pusch_ProcessingType2 pusch_ProcessingType2;
    public RRC_Integer ul_MCS_TableAlt_DynamicIndication;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "zeroSlotOffsetAperiodicSRS","pa-PhaseDiscontinuityImpacts","pusch-SeparationWithGap","pusch-ProcessingType2","ul-MCS-TableAlt-DynamicIndication" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "zeroSlotOffsetAperiodicSRS","pa_PhaseDiscontinuityImpacts","pusch_SeparationWithGap","pusch_ProcessingType2","ul_MCS_TableAlt_DynamicIndication" };
    }

    @Override
    public String getAsnName() {
        return "FeatureSetUplink-v1540";
    }

    @Override
    public String getXmlTagName() {
        return "FeatureSetUplink-v1540";
    }

}
