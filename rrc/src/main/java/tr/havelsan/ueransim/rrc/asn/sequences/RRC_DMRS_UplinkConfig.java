/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;

public class RRC_DMRS_UplinkConfig extends AsnSequence {
    public RRC_dmrs_Type_1 dmrs_Type; // optional
    public RRC_dmrs_AdditionalPosition_1 dmrs_AdditionalPosition; // optional
    public RRC_SetupRelease_PTRS_UplinkConfig phaseTrackingRS; // optional
    public RRC_maxLength_1 maxLength; // optional
    public RRC_transformPrecodingDisabled transformPrecodingDisabled; // optional
    public RRC_transformPrecodingEnabled transformPrecodingEnabled; // optional

    public static class RRC_SetupRelease_PTRS_UplinkConfig extends AsnChoice {
        public AsnNull release;
        public RRC_PTRS_UplinkConfig setup;
    }

    public static class RRC_transformPrecodingDisabled extends AsnSequence {
        public AsnInteger scramblingID0; // optional, VALUE(0..65535)
        public AsnInteger scramblingID1; // optional, VALUE(0..65535)
    }

    public static class RRC_transformPrecodingEnabled extends AsnSequence {
        public AsnInteger nPUSCH_Identity; // optional, VALUE(0..1007)
        public RRC_sequenceGroupHopping sequenceGroupHopping; // optional
        public RRC_sequenceHopping sequenceHopping; // optional
    
        public static class RRC_sequenceGroupHopping extends AsnEnumerated {
            public static final RRC_sequenceGroupHopping DISABLED = new RRC_sequenceGroupHopping(0);
        
            private RRC_sequenceGroupHopping(long value) {
                super(value);
            }
        }
    
        public static class RRC_sequenceHopping extends AsnEnumerated {
            public static final RRC_sequenceHopping ENABLED = new RRC_sequenceHopping(0);
        
            private RRC_sequenceHopping(long value) {
                super(value);
            }
        }
    }

    public static class RRC_maxLength_1 extends AsnEnumerated {
        public static final RRC_maxLength_1 LEN2 = new RRC_maxLength_1(0);
    
        private RRC_maxLength_1(long value) {
            super(value);
        }
    }

    public static class RRC_dmrs_Type_1 extends AsnEnumerated {
        public static final RRC_dmrs_Type_1 TYPE2 = new RRC_dmrs_Type_1(0);
    
        private RRC_dmrs_Type_1(long value) {
            super(value);
        }
    }

    public static class RRC_dmrs_AdditionalPosition_1 extends AsnEnumerated {
        public static final RRC_dmrs_AdditionalPosition_1 POS0 = new RRC_dmrs_AdditionalPosition_1(0);
        public static final RRC_dmrs_AdditionalPosition_1 POS1 = new RRC_dmrs_AdditionalPosition_1(1);
        public static final RRC_dmrs_AdditionalPosition_1 POS3 = new RRC_dmrs_AdditionalPosition_1(2);
    
        private RRC_dmrs_AdditionalPosition_1(long value) {
            super(value);
        }
    }
}

