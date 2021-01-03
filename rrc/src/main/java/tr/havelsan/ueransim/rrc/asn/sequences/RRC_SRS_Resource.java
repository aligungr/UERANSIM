/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.rrc.asn.choices.RRC_SRS_PeriodicityAndOffset;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SRS_ResourceId;

public class RRC_SRS_Resource extends AsnSequence {
    public RRC_SRS_ResourceId srs_ResourceId; // mandatory
    public RRC_nrofSRS_Ports nrofSRS_Ports; // mandatory
    public RRC_ptrs_PortIndex ptrs_PortIndex; // optional
    public RRC_transmissionComb transmissionComb; // mandatory
    public RRC_resourceMapping resourceMapping; // mandatory
    public AsnInteger freqDomainPosition; // mandatory, VALUE(0..67)
    public AsnInteger freqDomainShift; // mandatory, VALUE(0..268)
    public RRC_freqHopping freqHopping; // mandatory
    public RRC_groupOrSequenceHopping groupOrSequenceHopping; // mandatory
    public RRC_resourceType_1 resourceType; // mandatory
    public AsnInteger sequenceId; // mandatory, VALUE(0..1023)
    public RRC_SRS_SpatialRelationInfo spatialRelationInfo; // optional

    public static class RRC_transmissionComb extends AsnChoice {
        public RRC_n2 n2;
        public RRC_n4 n4;
    
        public static class RRC_n2 extends AsnSequence {
            public AsnInteger combOffset_n2; // mandatory, VALUE(0..1)
            public AsnInteger cyclicShift_n2; // mandatory, VALUE(0..7)
        }
    
        public static class RRC_n4 extends AsnSequence {
            public AsnInteger combOffset_n4; // mandatory, VALUE(0..3)
            public AsnInteger cyclicShift_n4; // mandatory, VALUE(0..11)
        }
    }

    public static class RRC_resourceType_1 extends AsnChoice {
        public RRC_aperiodic_2 aperiodic;
        public RRC_semi_persistent_2 semi_persistent;
        public RRC_periodic_1 periodic;
    
        public static class RRC_periodic_1 extends AsnSequence {
            public RRC_SRS_PeriodicityAndOffset periodicityAndOffset_p; // mandatory
        }
    
        public static class RRC_aperiodic_2 extends AsnSequence {
        }
    
        public static class RRC_semi_persistent_2 extends AsnSequence {
            public RRC_SRS_PeriodicityAndOffset periodicityAndOffset_sp; // mandatory
        }
    }

    public static class RRC_freqHopping extends AsnSequence {
        public AsnInteger c_SRS; // mandatory, VALUE(0..63)
        public AsnInteger b_SRS; // mandatory, VALUE(0..3)
        public AsnInteger b_hop; // mandatory, VALUE(0..3)
    }

    public static class RRC_resourceMapping extends AsnSequence {
        public AsnInteger startPosition; // mandatory, VALUE(0..5)
        public RRC_nrofSymbols nrofSymbols; // mandatory
        public RRC_repetitionFactor repetitionFactor; // mandatory
    
        public static class RRC_nrofSymbols extends AsnEnumerated {
            public static final RRC_nrofSymbols N1 = new RRC_nrofSymbols(0);
            public static final RRC_nrofSymbols N2 = new RRC_nrofSymbols(1);
            public static final RRC_nrofSymbols N4 = new RRC_nrofSymbols(2);
        
            private RRC_nrofSymbols(long value) {
                super(value);
            }
        }
    
        public static class RRC_repetitionFactor extends AsnEnumerated {
            public static final RRC_repetitionFactor N1 = new RRC_repetitionFactor(0);
            public static final RRC_repetitionFactor N2 = new RRC_repetitionFactor(1);
            public static final RRC_repetitionFactor N4 = new RRC_repetitionFactor(2);
        
            private RRC_repetitionFactor(long value) {
                super(value);
            }
        }
    }

    public static class RRC_nrofSRS_Ports extends AsnEnumerated {
        public static final RRC_nrofSRS_Ports PORT1 = new RRC_nrofSRS_Ports(0);
        public static final RRC_nrofSRS_Ports PORTS2 = new RRC_nrofSRS_Ports(1);
        public static final RRC_nrofSRS_Ports PORTS4 = new RRC_nrofSRS_Ports(2);
    
        private RRC_nrofSRS_Ports(long value) {
            super(value);
        }
    }

    public static class RRC_ptrs_PortIndex extends AsnEnumerated {
        public static final RRC_ptrs_PortIndex N0 = new RRC_ptrs_PortIndex(0);
        public static final RRC_ptrs_PortIndex N1 = new RRC_ptrs_PortIndex(1);
    
        private RRC_ptrs_PortIndex(long value) {
            super(value);
        }
    }

    public static class RRC_groupOrSequenceHopping extends AsnEnumerated {
        public static final RRC_groupOrSequenceHopping NEITHER = new RRC_groupOrSequenceHopping(0);
        public static final RRC_groupOrSequenceHopping GROUPHOPPING = new RRC_groupOrSequenceHopping(1);
        public static final RRC_groupOrSequenceHopping SEQUENCEHOPPING = new RRC_groupOrSequenceHopping(2);
    
        private RRC_groupOrSequenceHopping(long value) {
            super(value);
        }
    }
}

