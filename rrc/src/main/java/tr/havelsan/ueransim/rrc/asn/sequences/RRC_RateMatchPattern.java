/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.enums.RRC_SubcarrierSpacing;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ControlResourceSetId;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_RateMatchPatternId;

public class RRC_RateMatchPattern extends AsnSequence {
    public RRC_RateMatchPatternId rateMatchPatternId; // mandatory
    public RRC_patternType patternType; // mandatory
    public RRC_SubcarrierSpacing subcarrierSpacing; // optional
    public RRC_dummy_9 dummy; // mandatory

    public static class RRC_patternType extends AsnChoice {
        public RRC_bitmaps bitmaps;
        public RRC_ControlResourceSetId controlResourceSet;
    
        public static class RRC_bitmaps extends AsnSequence {
            public AsnBitString resourceBlocks; // mandatory, SIZE(275)
            public RRC_symbolsInResourceBlock symbolsInResourceBlock; // mandatory
            public RRC_periodicityAndPattern periodicityAndPattern; // optional
        
            public static class RRC_periodicityAndPattern extends AsnChoice {
                public AsnBitString n2; // SIZE(2)
                public AsnBitString n4; // SIZE(4)
                public AsnBitString n5; // SIZE(5)
                public AsnBitString n8; // SIZE(8)
                public AsnBitString n10; // SIZE(10)
                public AsnBitString n20; // SIZE(20)
                public AsnBitString n40; // SIZE(40)
            }
        
            public static class RRC_symbolsInResourceBlock extends AsnChoice {
                public AsnBitString oneSlot; // SIZE(14)
                public AsnBitString twoSlots; // SIZE(28)
            }
        }
    }

    public static class RRC_dummy_9 extends AsnEnumerated {
        public static final RRC_dummy_9 DYNAMIC = new RRC_dummy_9(0);
        public static final RRC_dummy_9 SEMISTATIC = new RRC_dummy_9(1);
    
        private RRC_dummy_9(long value) {
            super(value);
        }
    }
}

