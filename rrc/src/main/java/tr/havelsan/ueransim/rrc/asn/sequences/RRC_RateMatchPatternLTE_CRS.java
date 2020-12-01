/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_EUTRA_MBSFN_SubframeConfigList;

public class RRC_RateMatchPatternLTE_CRS extends RRC_Sequence {

    public RRC_Integer carrierFreqDL;
    public RRC_Integer carrierBandwidthDL;
    public RRC_EUTRA_MBSFN_SubframeConfigList mbsfn_SubframeConfigList;
    public RRC_Integer nrofCRS_Ports;
    public RRC_Integer v_Shift;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "carrierFreqDL","carrierBandwidthDL","mbsfn-SubframeConfigList","nrofCRS-Ports","v-Shift" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "carrierFreqDL","carrierBandwidthDL","mbsfn_SubframeConfigList","nrofCRS_Ports","v_Shift" };
    }

    @Override
    public String getAsnName() {
        return "RateMatchPatternLTE-CRS";
    }

    @Override
    public String getXmlTagName() {
        return "RateMatchPatternLTE-CRS";
    }

}
