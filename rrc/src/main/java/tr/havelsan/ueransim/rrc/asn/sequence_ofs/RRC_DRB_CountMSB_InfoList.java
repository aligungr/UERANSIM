/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DRB_CountMSB_Info;

public class RRC_DRB_CountMSB_InfoList extends RRC_SequenceOf<RRC_DRB_CountMSB_Info> {

    @Override
    public String getAsnName() {
        return "DRB-CountMSB-InfoList";
    }

    @Override
    public String getXmlTagName() {
        return "DRB-CountMSB-InfoList";
    }

    @Override
    public Class<RRC_DRB_CountMSB_Info> getItemType() {
        return RRC_DRB_CountMSB_Info.class;
    }

}
