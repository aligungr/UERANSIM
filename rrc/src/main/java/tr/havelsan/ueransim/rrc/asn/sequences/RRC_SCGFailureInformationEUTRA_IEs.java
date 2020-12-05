package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SCGFailureInformationEUTRA_IEs extends AsnSequence {
    public RRC_FailureReportSCG_EUTRA failureReportSCG_EUTRA; // optional
    public RRC_nonCriticalExtension_31 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_31 extends AsnSequence {
    }
}

