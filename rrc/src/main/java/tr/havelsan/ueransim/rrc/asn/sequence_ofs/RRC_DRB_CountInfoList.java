/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DRB_CountInfo;

public class RRC_DRB_CountInfoList extends RRC_SequenceOf<RRC_DRB_CountInfo> {

    @Override
    public String getAsnName() {
        return "DRB-CountInfoList";
    }

    @Override
    public String getXmlTagName() {
        return "DRB-CountInfoList";
    }

    @Override
    public Class<RRC_DRB_CountInfo> getItemType() {
        return RRC_DRB_CountInfo.class;
    }

}
