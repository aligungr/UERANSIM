package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_SRS_TPC_PDCCH_Config extends AsnSequence {
    public RRC_srs_CC_SetIndexlist srs_CC_SetIndexlist; // optional, SIZE(1..4)

    // SIZE(1..4)
    public static class RRC_srs_CC_SetIndexlist extends AsnSequenceOf<RRC_SRS_CC_SetIndex> {
    }
}

