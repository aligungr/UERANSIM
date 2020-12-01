/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_FR_Info;

public class RRC_FR_InfoList extends RRC_SequenceOf<RRC_FR_Info> {

    @Override
    public String getAsnName() {
        return "FR-InfoList";
    }

    @Override
    public String getXmlTagName() {
        return "FR-InfoList";
    }

    @Override
    public Class<RRC_FR_Info> getItemType() {
        return RRC_FR_Info.class;
    }

}
