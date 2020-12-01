/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_MeasTiming;

public class RRC_MeasTimingList extends RRC_SequenceOf<RRC_MeasTiming> {

    @Override
    public String getAsnName() {
        return "MeasTimingList";
    }

    @Override
    public String getXmlTagName() {
        return "MeasTimingList";
    }

    @Override
    public Class<RRC_MeasTiming> getItemType() {
        return RRC_MeasTiming.class;
    }

}
