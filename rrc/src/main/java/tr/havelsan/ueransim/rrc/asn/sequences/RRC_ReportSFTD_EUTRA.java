package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_ReportSFTD_EUTRA extends AsnSequence {
    public AsnBoolean reportSFTD_Meas; // mandatory
    public AsnBoolean reportRSRP; // mandatory
}

