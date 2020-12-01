/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PUCCH_PowerControl__p0_Set;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PUCCH_PowerControl__pathlossReferenceRSs;

public class RRC_PUCCH_PowerControl extends RRC_Sequence {

    public RRC_Integer deltaF_PUCCH_f0;
    public RRC_Integer deltaF_PUCCH_f1;
    public RRC_Integer deltaF_PUCCH_f2;
    public RRC_Integer deltaF_PUCCH_f3;
    public RRC_Integer deltaF_PUCCH_f4;
    public RRC_PUCCH_PowerControl__p0_Set p0_Set;
    public RRC_PUCCH_PowerControl__pathlossReferenceRSs pathlossReferenceRSs;
    public RRC_Integer twoPUCCH_PC_AdjustmentStates;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "deltaF-PUCCH-f0","deltaF-PUCCH-f1","deltaF-PUCCH-f2","deltaF-PUCCH-f3","deltaF-PUCCH-f4","p0-Set","pathlossReferenceRSs","twoPUCCH-PC-AdjustmentStates" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "deltaF_PUCCH_f0","deltaF_PUCCH_f1","deltaF_PUCCH_f2","deltaF_PUCCH_f3","deltaF_PUCCH_f4","p0_Set","pathlossReferenceRSs","twoPUCCH_PC_AdjustmentStates" };
    }

    @Override
    public String getAsnName() {
        return "PUCCH-PowerControl";
    }

    @Override
    public String getXmlTagName() {
        return "PUCCH-PowerControl";
    }

}
