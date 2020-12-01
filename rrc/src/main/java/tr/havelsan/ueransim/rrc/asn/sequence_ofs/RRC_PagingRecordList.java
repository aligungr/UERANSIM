/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequence_ofs;

import tr.havelsan.ueransim.rrc.asn.core.RRC_SequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_PagingRecord;

public class RRC_PagingRecordList extends RRC_SequenceOf<RRC_PagingRecord> {

    @Override
    public String getAsnName() {
        return "PagingRecordList";
    }

    @Override
    public String getXmlTagName() {
        return "PagingRecordList";
    }

    @Override
    public Class<RRC_PagingRecord> getItemType() {
        return RRC_PagingRecord.class;
    }

}
