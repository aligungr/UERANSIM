/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_CellsToAddMod;

public class RRC_CellsToAddModList extends RRC_SequenceOf<RRC_CellsToAddMod> {

    @Override
    public String getAsnName() {
        return "CellsToAddModList";
    }

    @Override
    public String getXmlTagName() {
        return "CellsToAddModList";
    }

    @Override
    public Class<RRC_CellsToAddMod> getItemType() {
        return RRC_CellsToAddMod.class;
    }

}
