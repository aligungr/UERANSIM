package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.sequence_ofs.RRC_FreqBandList;

public class RRC_UE_CapabilityRequestFilterNR extends AsnSequence {
    public RRC_FreqBandList frequencyBandListFilter; // optional
    public RRC_UE_CapabilityRequestFilterNR_v1540 nonCriticalExtension; // optional
}

