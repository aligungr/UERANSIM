/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.rrc.asn.core.RRC_OctetString;
import tr.havelsan.ueransim.rrc.asn.core.RRC_Sequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PagingRecordList;

public class RRC_Paging extends RRC_Sequence {

    public RRC_PagingRecordList pagingRecordList;
    public RRC_OctetString lateNonCriticalExtension;
    public RRC_Paging__nonCriticalExtension nonCriticalExtension;

    @Override
    public String[] getMemberNames() {
        return new String[]{ "pagingRecordList","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String[] getMemberIdentifiers() {
        return new String[]{ "pagingRecordList","lateNonCriticalExtension","nonCriticalExtension" };
    }

    @Override
    public String getAsnName() {
        return "Paging";
    }

    @Override
    public String getXmlTagName() {
        return "Paging";
    }

}
