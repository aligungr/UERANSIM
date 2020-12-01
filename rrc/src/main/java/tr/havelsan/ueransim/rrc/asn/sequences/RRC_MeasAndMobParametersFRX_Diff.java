/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;

public class RRC_MeasAndMobParametersFRX_Diff extends RRC_Sequence {

    public RRC_Integer ss_SINR_Meas;
    public RRC_Integer csi_RSRP_AndRSRQ_MeasWithSSB;
    public RRC_Integer csi_RSRP_AndRSRQ_MeasWithoutSSB;
    public RRC_Integer csi_SINR_Meas;
    public RRC_Integer csi_RS_RLM;
    public RRC_MeasAndMobParametersFRX_Diff__ext1 ext1;
    public RRC_MeasAndMobParametersFRX_Diff__ext2 ext2;
    public RRC_MeasAndMobParametersFRX_Diff__ext3 ext3;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ss-SINR-Meas","csi-RSRP-AndRSRQ-MeasWithSSB","csi-RSRP-AndRSRQ-MeasWithoutSSB","csi-SINR-Meas","csi-RS-RLM","ext1","ext2","ext3" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ss_SINR_Meas","csi_RSRP_AndRSRQ_MeasWithSSB","csi_RSRP_AndRSRQ_MeasWithoutSSB","csi_SINR_Meas","csi_RS_RLM","ext1","ext2","ext3" };
    }

    @Override
    public String getAsnName() {
        return "MeasAndMobParametersFRX-Diff";
    }

    @Override
    public String getXmlTagName() {
        return "MeasAndMobParametersFRX-Diff";
    }

}
