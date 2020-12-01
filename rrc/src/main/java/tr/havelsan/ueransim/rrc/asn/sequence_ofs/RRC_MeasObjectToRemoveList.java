/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_MeasObjectId;

public class RRC_MeasObjectToRemoveList extends RRC_SequenceOf<RRC_MeasObjectId> {

    @Override
    public String getAsnName() {
        return "MeasObjectToRemoveList";
    }

    @Override
    public String getXmlTagName() {
        return "MeasObjectToRemoveList";
    }

    @Override
    public Class<RRC_MeasObjectId> getItemType() {
        return RRC_MeasObjectId.class;
    }

}
