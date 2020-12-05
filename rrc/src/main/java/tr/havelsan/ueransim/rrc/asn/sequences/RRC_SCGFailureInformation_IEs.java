package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SCGFailureInformation_IEs extends AsnSequence {
    public RRC_FailureReportSCG failureReportSCG; // optional
    public RRC_nonCriticalExtension_8 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_8 extends AsnSequence {
    }
}

