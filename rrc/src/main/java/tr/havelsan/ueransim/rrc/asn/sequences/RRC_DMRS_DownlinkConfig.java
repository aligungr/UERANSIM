/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;

public class RRC_DMRS_DownlinkConfig extends AsnSequence {
    public RRC_dmrs_Type_2 dmrs_Type; // optional
    public RRC_dmrs_AdditionalPosition_2 dmrs_AdditionalPosition; // optional
    public RRC_maxLength_2 maxLength; // optional
    public AsnInteger scramblingID0; // optional, VALUE(0..65535)
    public AsnInteger scramblingID1; // optional, VALUE(0..65535)
    public RRC_SetupRelease_PTRS_DownlinkConfig phaseTrackingRS; // optional

    public static class RRC_dmrs_Type_2 extends AsnEnumerated {
        public static final RRC_dmrs_Type_2 TYPE2 = new RRC_dmrs_Type_2(0);
    
        private RRC_dmrs_Type_2(long value) {
            super(value);
        }
    }

    public static class RRC_dmrs_AdditionalPosition_2 extends AsnEnumerated {
        public static final RRC_dmrs_AdditionalPosition_2 POS0 = new RRC_dmrs_AdditionalPosition_2(0);
        public static final RRC_dmrs_AdditionalPosition_2 POS1 = new RRC_dmrs_AdditionalPosition_2(1);
        public static final RRC_dmrs_AdditionalPosition_2 POS3 = new RRC_dmrs_AdditionalPosition_2(2);
    
        private RRC_dmrs_AdditionalPosition_2(long value) {
            super(value);
        }
    }

    public static class RRC_SetupRelease_PTRS_DownlinkConfig extends AsnChoice {
        public AsnNull release;
        public RRC_PTRS_DownlinkConfig setup;
    }

    public static class RRC_maxLength_2 extends AsnEnumerated {
        public static final RRC_maxLength_2 LEN2 = new RRC_maxLength_2(0);
    
        private RRC_maxLength_2(long value) {
            super(value);
        }
    }
}

