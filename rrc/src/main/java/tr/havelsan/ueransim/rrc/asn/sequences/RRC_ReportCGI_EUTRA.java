package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_EUTRA_PhysCellId;

public class RRC_ReportCGI_EUTRA extends AsnSequence {
    public RRC_EUTRA_PhysCellId cellForWhichToReportCGI; // mandatory
}

