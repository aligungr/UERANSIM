package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnOctetString;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_RRCReconfiguration_IEs extends AsnSequence {
    public RRC_RadioBearerConfig radioBearerConfig; // optional
    public AsnOctetString secondaryCellGroup; // optional, SIZE(0..MAX)
    public RRC_MeasConfig measConfig; // optional
    public AsnOctetString lateNonCriticalExtension; // optional
    public RRC_RRCReconfiguration_v1530_IEs nonCriticalExtension; // optional
}

