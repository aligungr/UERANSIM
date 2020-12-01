/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasObjectToAddMod;

public class RRC_MeasObjectToAddModList extends RRC_SequenceOf<RRC_MeasObjectToAddMod> {

    @Override
    public String getAsnName() {
        return "MeasObjectToAddModList";
    }

    @Override
    public String getXmlTagName() {
        return "MeasObjectToAddModList";
    }

    @Override
    public Class<RRC_MeasObjectToAddMod> getItemType() {
        return RRC_MeasObjectToAddMod.class;
    }

}
