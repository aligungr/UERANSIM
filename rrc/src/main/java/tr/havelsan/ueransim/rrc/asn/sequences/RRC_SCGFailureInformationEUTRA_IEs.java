/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SCGFailureInformationEUTRA_IEs extends AsnSequence {
    public RRC_FailureReportSCG_EUTRA failureReportSCG_EUTRA; // optional
    public RRC_nonCriticalExtension_31 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_31 extends AsnSequence {
    }
}

