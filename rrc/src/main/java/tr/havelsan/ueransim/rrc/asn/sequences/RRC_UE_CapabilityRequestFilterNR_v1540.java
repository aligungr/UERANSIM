/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UE_CapabilityRequestFilterNR_v1540 extends AsnSequence {
    public RRC_srs_SwitchingTimeRequest srs_SwitchingTimeRequest; // optional
    public RRC_nonCriticalExtension_4 nonCriticalExtension; // optional

    public static class RRC_nonCriticalExtension_4 extends AsnSequence {
    }

    public static class RRC_srs_SwitchingTimeRequest extends AsnEnumerated {
        public static final RRC_srs_SwitchingTimeRequest TRUE = new RRC_srs_SwitchingTimeRequest(0);
    
        private RRC_srs_SwitchingTimeRequest(long value) {
            super(value);
        }
    }
}

