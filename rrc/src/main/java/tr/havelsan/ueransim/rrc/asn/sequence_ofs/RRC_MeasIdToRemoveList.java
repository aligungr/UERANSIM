/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasId;

public class RRC_MeasIdToRemoveList extends RRC_SequenceOf<RRC_MeasId> {

    @Override
    public String getAsnName() {
        return "MeasIdToRemoveList";
    }

    @Override
    public String getXmlTagName() {
        return "MeasIdToRemoveList";
    }

    @Override
    public Class<RRC_MeasId> getItemType() {
        return RRC_MeasId.class;
    }

}
