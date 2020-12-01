/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasIdToAddMod;

public class RRC_MeasIdToAddModList extends RRC_SequenceOf<RRC_MeasIdToAddMod> {

    @Override
    public String getAsnName() {
        return "MeasIdToAddModList";
    }

    @Override
    public String getXmlTagName() {
        return "MeasIdToAddModList";
    }

    @Override
    public Class<RRC_MeasIdToAddMod> getItemType() {
        return RRC_MeasIdToAddMod.class;
    }

}
