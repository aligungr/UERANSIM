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

public class RRC_CodebookParameters extends AsnSequence {
    public RRC_type1_3 type1; // mandatory
    public RRC_type2_2 type2; // optional
    public RRC_type2_PortSelection type2_PortSelection; // optional

    public static class RRC_type2_PortSelection extends AsnSequence {
        public RRC_supportedCSI_RS_ResourceList_3 supportedCSI_RS_ResourceList; // mandatory, SIZE(1..7)
        public AsnInteger parameterLx; // mandatory, VALUE(2..4)
        public RRC_amplitudeScalingType_2 amplitudeScalingType; // mandatory
    
        public static class RRC_amplitudeScalingType_2 extends AsnEnumerated {
            public static final RRC_amplitudeScalingType_2 WIDEBAND = new RRC_amplitudeScalingType_2(0);
            public static final RRC_amplitudeScalingType_2 WIDEBANDANDSUBBAND = new RRC_amplitudeScalingType_2(1);
        
            private RRC_amplitudeScalingType_2(long value) {
                super(value);
            }
        }
    
        // SIZE(1..7)
        public static class RRC_supportedCSI_RS_ResourceList_3 extends AsnSequenceOf<RRC_SupportedCSI_RS_Resource> {
        }
    }

    public static class RRC_type2_2 extends AsnSequence {
        public RRC_supportedCSI_RS_ResourceList_4 supportedCSI_RS_ResourceList; // mandatory, SIZE(1..7)
        public AsnInteger parameterLx; // mandatory, VALUE(2..4)
        public RRC_amplitudeScalingType_3 amplitudeScalingType; // mandatory
        public RRC_amplitudeSubsetRestriction_2 amplitudeSubsetRestriction; // optional
    
        public static class RRC_amplitudeSubsetRestriction_2 extends AsnEnumerated {
            public static final RRC_amplitudeSubsetRestriction_2 SUPPORTED = new RRC_amplitudeSubsetRestriction_2(0);
        
            private RRC_amplitudeSubsetRestriction_2(long value) {
                super(value);
            }
        }
    
        public static class RRC_amplitudeScalingType_3 extends AsnEnumerated {
            public static final RRC_amplitudeScalingType_3 WIDEBAND = new RRC_amplitudeScalingType_3(0);
            public static final RRC_amplitudeScalingType_3 WIDEBANDANDSUBBAND = new RRC_amplitudeScalingType_3(1);
        
            private RRC_amplitudeScalingType_3(long value) {
                super(value);
            }
        }
    
        // SIZE(1..7)
        public static class RRC_supportedCSI_RS_ResourceList_4 extends AsnSequenceOf<RRC_SupportedCSI_RS_Resource> {
        }
    }

    public static class RRC_type1_3 extends AsnSequence {
        public RRC_singlePanel singlePanel; // mandatory
        public RRC_multiPanel multiPanel; // optional
    
        public static class RRC_singlePanel extends AsnSequence {
            public RRC_supportedCSI_RS_ResourceList_1 supportedCSI_RS_ResourceList; // mandatory, SIZE(1..7)
            public RRC_modes_2 modes; // mandatory
            public AsnInteger maxNumberCSI_RS_PerResourceSet; // mandatory, VALUE(1..8)
        
            // SIZE(1..7)
            public static class RRC_supportedCSI_RS_ResourceList_1 extends AsnSequenceOf<RRC_SupportedCSI_RS_Resource> {
            }
        
            public static class RRC_modes_2 extends AsnEnumerated {
                public static final RRC_modes_2 MODE1 = new RRC_modes_2(0);
                public static final RRC_modes_2 MODE1ANDMODE2 = new RRC_modes_2(1);
            
                private RRC_modes_2(long value) {
                    super(value);
                }
            }
        }
    
        public static class RRC_multiPanel extends AsnSequence {
            public RRC_supportedCSI_RS_ResourceList_2 supportedCSI_RS_ResourceList; // mandatory, SIZE(1..7)
            public RRC_modes_1 modes; // mandatory
            public RRC_nrofPanels nrofPanels; // mandatory
            public AsnInteger maxNumberCSI_RS_PerResourceSet; // mandatory, VALUE(1..8)
        
            public static class RRC_modes_1 extends AsnEnumerated {
                public static final RRC_modes_1 MODE1 = new RRC_modes_1(0);
                public static final RRC_modes_1 MODE2 = new RRC_modes_1(1);
                public static final RRC_modes_1 BOTH = new RRC_modes_1(2);
            
                private RRC_modes_1(long value) {
                    super(value);
                }
            }
        
            // SIZE(1..7)
            public static class RRC_supportedCSI_RS_ResourceList_2 extends AsnSequenceOf<RRC_SupportedCSI_RS_Resource> {
            }
        
            public static class RRC_nrofPanels extends AsnEnumerated {
                public static final RRC_nrofPanels N2 = new RRC_nrofPanels(0);
                public static final RRC_nrofPanels N4 = new RRC_nrofPanels(1);
            
                private RRC_nrofPanels(long value) {
                    super(value);
                }
            }
        }
    }
}

