package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SecurityModeFailure_IEs extends AsnSequence {
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_6 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_6 extends AsnSequence {
    }
}

