package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRP_RangeEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RSRQ_RangeEUTRA;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SINR_RangeEUTRA;

public class RRC_MeasTriggerQuantityEUTRA extends AsnChoice {
    public RRC_RSRP_RangeEUTRA rsrp;
    public RRC_RSRQ_RangeEUTRA rsrq;
    public RRC_SINR_RangeEUTRA sinr;
}

