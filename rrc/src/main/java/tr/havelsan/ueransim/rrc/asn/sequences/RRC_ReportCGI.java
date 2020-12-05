package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_ReportCGI extends AsnSequence {
    public RRC_PhysCellId cellForWhichToReportCGI; // mandatory
}

