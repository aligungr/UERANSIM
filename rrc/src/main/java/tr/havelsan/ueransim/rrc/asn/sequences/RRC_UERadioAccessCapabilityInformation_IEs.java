package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UERadioAccessCapabilityInformation_IEs extends AsnSequence {
    public AsnOctetString ue_RadioAccessCapabilityInfo; // mandatory, SIZE(0..MAX)
    public RRC_nonCriticalExtension_19 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_19 extends AsnSequence {
    }
}

