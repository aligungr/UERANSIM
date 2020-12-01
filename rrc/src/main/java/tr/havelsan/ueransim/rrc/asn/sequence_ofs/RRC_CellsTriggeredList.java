/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.choices.RRC_CellsTriggeredList__Member;
import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;

public class RRC_CellsTriggeredList extends RRC_SequenceOf<RRC_CellsTriggeredList__Member> {

    @Override
    public String getAsnName() {
        return "CellsTriggeredList";
    }

    @Override
    public String getXmlTagName() {
        return "CellsTriggeredList";
    }

    @Override
    public Class<RRC_CellsTriggeredList__Member> getItemType() {
        return RRC_CellsTriggeredList__Member.class;
    }

}
