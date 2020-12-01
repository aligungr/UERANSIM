/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_DRB_ToAddMod;

public class RRC_DRB_ToAddModList extends RRC_SequenceOf<RRC_DRB_ToAddMod> {

    @Override
    public String getAsnName() {
        return "DRB-ToAddModList";
    }

    @Override
    public String getXmlTagName() {
        return "DRB-ToAddModList";
    }

    @Override
    public Class<RRC_DRB_ToAddMod> getItemType() {
        return RRC_DRB_ToAddMod.class;
    }

}
