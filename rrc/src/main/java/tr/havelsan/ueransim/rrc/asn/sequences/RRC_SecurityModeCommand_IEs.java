package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SecurityModeCommand_IEs extends AsnSequence {
    public RRC_SecurityConfigSMC securityConfigSMC; // mandatory
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_nonCriticalExtension_16 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_16 extends AsnSequence {
    }
}

