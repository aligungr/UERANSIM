/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Choice;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Null;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CSI_ReportConfig__reportQuantity__cri_RI_i1_CQI;

public class RRC_CSI_ReportConfig__reportQuantity extends RRC_Choice {

    public RRC_Null none;
    public RRC_Null cri_RI_PMI_CQI;
    public RRC_Null cri_RI_i1;
    public RRC_CSI_ReportConfig__reportQuantity__cri_RI_i1_CQI cri_RI_i1_CQI;
    public RRC_Null cri_RI_CQI;
    public RRC_Null cri_RSRP;
    public RRC_Null ssb_Index_RSRP;
    public RRC_Null cri_RI_LI_PMI_CQI;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "none","cri-RI-PMI-CQI","cri-RI-i1","cri-RI-i1-CQI","cri-RI-CQI","cri-RSRP","ssb-Index-RSRP","cri-RI-LI-PMI-CQI" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "none","cri_RI_PMI_CQI","cri_RI_i1","cri_RI_i1_CQI","cri_RI_CQI","cri_RSRP","ssb_Index_RSRP","cri_RI_LI_PMI_CQI" };
    }

}
