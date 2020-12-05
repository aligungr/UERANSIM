package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_PDSCH_TimeDomainResourceAllocationList;

public class RRC_PDSCH_ConfigCommon extends AsnSequence {
    public RRC_PDSCH_TimeDomainResourceAllocationList pdsch_TimeDomainAllocationList; // optional
}

