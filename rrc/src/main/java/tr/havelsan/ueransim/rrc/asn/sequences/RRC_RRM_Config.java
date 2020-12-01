/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_Integer;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_MeasResultList2NR;

public class RRC_RRM_Config extends RRC_Sequence {

    public RRC_Integer ue_InactiveTime;
    public RRC_MeasResultList2NR candidateCellInfoList;
    public RRC_RRM_Config__ext1 ext1;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "ue-InactiveTime","candidateCellInfoList","ext1" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "ue_InactiveTime","candidateCellInfoList","ext1" };
    }

    @Override
    public String getAsnName() {
        return "RRM-Config";
    }

    @Override
    public String getXmlTagName() {
        return "RRM-Config";
    }

}
