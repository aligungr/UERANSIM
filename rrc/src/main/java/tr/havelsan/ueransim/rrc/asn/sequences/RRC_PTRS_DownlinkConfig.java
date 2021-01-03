/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;

public class RRC_PTRS_DownlinkConfig extends AsnSequence {
    public RRC_frequencyDensity_1 frequencyDensity; // optional, SIZE(2)
    public RRC_timeDensity_1 timeDensity; // optional, SIZE(3)
    public AsnInteger epre_Ratio; // optional, VALUE(0..3)
    public RRC_resourceElementOffset_1 resourceElementOffset; // optional

    // SIZE(2)
    public static class RRC_frequencyDensity_1 extends AsnSequenceOf<AsnInteger> {
    }

    // SIZE(3)
    public static class RRC_timeDensity_1 extends AsnSequenceOf<AsnInteger> {
    }

    public static class RRC_resourceElementOffset_1 extends AsnEnumerated {
        public static final RRC_resourceElementOffset_1 OFFSET01 = new RRC_resourceElementOffset_1(0);
        public static final RRC_resourceElementOffset_1 OFFSET10 = new RRC_resourceElementOffset_1(1);
        public static final RRC_resourceElementOffset_1 OFFSET11 = new RRC_resourceElementOffset_1(2);
    
        private RRC_resourceElementOffset_1(long value) {
            super(value);
        }
    }
}

