package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.sequences.RRC_BetaOffsets;

public class RRC_CG_UCI_OnPUSCH extends AsnChoice {
    public RRC_dynamic_2 dynamic; // SIZE(1..4)
    public RRC_BetaOffsets semiStatic;

    // SIZE(1..4)
    public static class RRC_dynamic_2 extends AsnSequenceOf<RRC_BetaOffsets> {
    }
}

