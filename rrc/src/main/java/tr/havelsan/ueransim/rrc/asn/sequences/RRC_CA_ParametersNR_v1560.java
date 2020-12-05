package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_CA_ParametersNR_v1560 extends AsnSequence {
    public RRC_diffNumerologyWithinPUCCH_GroupLargerSCS diffNumerologyWithinPUCCH_GroupLargerSCS; // optional

    public static class RRC_diffNumerologyWithinPUCCH_GroupLargerSCS extends AsnEnumerated {
        public static final RRC_diffNumerologyWithinPUCCH_GroupLargerSCS SUPPORTED = new RRC_diffNumerologyWithinPUCCH_GroupLargerSCS(0);
    
        private RRC_diffNumerologyWithinPUCCH_GroupLargerSCS(long value) {
            super(value);
        }
    }
}

