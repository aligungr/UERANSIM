package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_RangeEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRQ_RangeEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SINR_RangeEUTRA;

public class RRC_MeasQuantityResultsEUTRA extends AsnSequence {
    public RRC_RSRP_RangeEUTRA rsrp; // optional
    public RRC_RSRQ_RangeEUTRA rsrq; // optional
    public RRC_SINR_RangeEUTRA sinr; // optional
}

