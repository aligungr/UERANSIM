/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;

public class RRC_EUTRA_MBSFN_SubframeConfig extends AsnSequence {
    public RRC_radioframeAllocationPeriod radioframeAllocationPeriod; // mandatory
    public AsnInteger radioframeAllocationOffset; // mandatory, VALUE(0..7)
    public RRC_subframeAllocation1 subframeAllocation1; // mandatory
    public RRC_subframeAllocation2 subframeAllocation2; // optional

    public static class RRC_radioframeAllocationPeriod extends AsnEnumerated {
        public static final RRC_radioframeAllocationPeriod N1 = new RRC_radioframeAllocationPeriod(0);
        public static final RRC_radioframeAllocationPeriod N2 = new RRC_radioframeAllocationPeriod(1);
        public static final RRC_radioframeAllocationPeriod N4 = new RRC_radioframeAllocationPeriod(2);
        public static final RRC_radioframeAllocationPeriod N8 = new RRC_radioframeAllocationPeriod(3);
        public static final RRC_radioframeAllocationPeriod N16 = new RRC_radioframeAllocationPeriod(4);
        public static final RRC_radioframeAllocationPeriod N32 = new RRC_radioframeAllocationPeriod(5);
    
        private RRC_radioframeAllocationPeriod(long value) {
            super(value);
        }
    }

    public static class RRC_subframeAllocation1 extends AsnChoice {
        public AsnBitString oneFrame; // SIZE(6)
        public AsnBitString fourFrames; // SIZE(24)
    }

    public static class RRC_subframeAllocation2 extends AsnChoice {
        public AsnBitString oneFrame; // SIZE(2)
        public AsnBitString fourFrames; // SIZE(8)
    }
}

