package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.octet_strings.RRC_DedicatedNAS_Message;

public class RRC_ULInformationTransfer_IEs extends AsnSequence {
    public RRC_DedicatedNAS_Message dedicatedNAS_Message; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_15 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_15 extends AsnSequence {
    }
}

