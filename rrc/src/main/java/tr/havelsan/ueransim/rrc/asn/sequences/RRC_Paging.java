package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PagingRecordList;

public class RRC_Paging extends AsnSequence {
    public RRC_PagingRecordList pagingRecordList; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_11 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_11 extends AsnSequence {
    }
}

