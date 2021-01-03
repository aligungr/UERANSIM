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

public class RRC_PTRS_UplinkConfig extends AsnSequence {
    public RRC_transformPrecoderDisabled transformPrecoderDisabled; // optional
    public RRC_transformPrecoderEnabled transformPrecoderEnabled; // optional

    public static class RRC_transformPrecoderEnabled extends AsnSequence {
        public RRC_sampleDensity sampleDensity; // mandatory, SIZE(5)
        public RRC_timeDensityTransformPrecoding timeDensityTransformPrecoding; // optional
    
        public static class RRC_timeDensityTransformPrecoding extends AsnEnumerated {
            public static final RRC_timeDensityTransformPrecoding D2 = new RRC_timeDensityTransformPrecoding(0);
        
            private RRC_timeDensityTransformPrecoding(long value) {
                super(value);
            }
        }
    
        // SIZE(5)
        public static class RRC_sampleDensity extends AsnSequenceOf<AsnInteger> {
        }
    }

    public static class RRC_transformPrecoderDisabled extends AsnSequence {
        public RRC_frequencyDensity_2 frequencyDensity; // optional, SIZE(2)
        public RRC_timeDensity_2 timeDensity; // optional, SIZE(3)
        public RRC_maxNrofPorts maxNrofPorts; // mandatory
        public RRC_resourceElementOffset_2 resourceElementOffset; // optional
        public RRC_ptrs_Power ptrs_Power; // mandatory
    
        public static class RRC_ptrs_Power extends AsnEnumerated {
            public static final RRC_ptrs_Power P00 = new RRC_ptrs_Power(0);
            public static final RRC_ptrs_Power P01 = new RRC_ptrs_Power(1);
            public static final RRC_ptrs_Power P10 = new RRC_ptrs_Power(2);
            public static final RRC_ptrs_Power P11 = new RRC_ptrs_Power(3);
        
            private RRC_ptrs_Power(long value) {
                super(value);
            }
        }
    
        public static class RRC_maxNrofPorts extends AsnEnumerated {
            public static final RRC_maxNrofPorts N1 = new RRC_maxNrofPorts(0);
            public static final RRC_maxNrofPorts N2 = new RRC_maxNrofPorts(1);
        
            private RRC_maxNrofPorts(long value) {
                super(value);
            }
        }
    
        public static class RRC_resourceElementOffset_2 extends AsnEnumerated {
            public static final RRC_resourceElementOffset_2 OFFSET01 = new RRC_resourceElementOffset_2(0);
            public static final RRC_resourceElementOffset_2 OFFSET10 = new RRC_resourceElementOffset_2(1);
            public static final RRC_resourceElementOffset_2 OFFSET11 = new RRC_resourceElementOffset_2(2);
        
            private RRC_resourceElementOffset_2(long value) {
                super(value);
            }
        }
    
        // SIZE(2)
        public static class RRC_frequencyDensity_2 extends AsnSequenceOf<AsnInteger> {
        }
    
        // SIZE(3)
        public static class RRC_timeDensity_2 extends AsnSequenceOf<AsnInteger> {
        }
    }
}

