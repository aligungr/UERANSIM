package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_PH_InfoMCG extends AsnSequence {
    public RRC_ServCellIndex servCellIndex; // mandatory
    public RRC_PH_UplinkCarrierMCG ph_Uplink; // mandatory
    public RRC_PH_UplinkCarrierMCG ph_SupplementaryUplink; // optional
}

