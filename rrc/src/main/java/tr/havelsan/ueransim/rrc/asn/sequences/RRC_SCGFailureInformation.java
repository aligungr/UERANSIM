package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SCGFailureInformation extends AsnSequence {
    public RRC_criticalExtensions_26 criticalExtensions; // mandatory

    public static class RRC_criticalExtensions_26 extends AsnChoice {
        public RRC_SCGFailureInformation_IEs scgFailureInformation;
        public RRC_criticalExtensionsFuture_5 criticalExtensionsFuture;
    
        public static class RRC_criticalExtensionsFuture_5 extends AsnSequence {
        }
    }
}

