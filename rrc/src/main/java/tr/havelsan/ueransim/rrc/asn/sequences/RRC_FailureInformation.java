package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_FailureInformation extends AsnSequence {
    public RRC_criticalExtensions_23 criticalExtensions; // mandatory

    public static class RRC_criticalExtensions_23 extends AsnChoice {
        public RRC_FailureInformation_IEs failureInformation;
        public RRC_criticalExtensionsFuture_25 criticalExtensionsFuture;
    
        public static class RRC_criticalExtensionsFuture_25 extends AsnSequence {
        }
    }
}

