/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;

public class RRC_CSI_RS_ResourceMapping extends AsnSequence {
    public RRC_frequencyDomainAllocation_2 frequencyDomainAllocation; // mandatory
    public RRC_nrofPorts nrofPorts; // mandatory
    public AsnInteger firstOFDMSymbolInTimeDomain; // mandatory, VALUE(0..13)
    public AsnInteger firstOFDMSymbolInTimeDomain2; // optional, VALUE(2..12)
    public RRC_cdm_Type cdm_Type; // mandatory
    public RRC_density_2 density; // mandatory
    public RRC_CSI_FrequencyOccupation freqBand; // mandatory

    public static class RRC_density_2 extends AsnChoice {
        public RRC_dot5 dot5;
        public AsnNull one;
        public AsnNull three;
        public AsnNull spare;
    
        public static class RRC_dot5 extends AsnEnumerated {
            public static final RRC_dot5 EVENPRBS = new RRC_dot5(0);
            public static final RRC_dot5 ODDPRBS = new RRC_dot5(1);
        
            private RRC_dot5(long value) {
                super(value);
            }
        }
    }

    public static class RRC_frequencyDomainAllocation_2 extends AsnChoice {
        public AsnBitString row1; // SIZE(4)
        public AsnBitString row2; // SIZE(12)
        public AsnBitString row4; // SIZE(3)
        public AsnBitString other; // SIZE(6)
    }

    public static class RRC_nrofPorts extends AsnEnumerated {
        public static final RRC_nrofPorts P1 = new RRC_nrofPorts(0);
        public static final RRC_nrofPorts P2 = new RRC_nrofPorts(1);
        public static final RRC_nrofPorts P4 = new RRC_nrofPorts(2);
        public static final RRC_nrofPorts P8 = new RRC_nrofPorts(3);
        public static final RRC_nrofPorts P12 = new RRC_nrofPorts(4);
        public static final RRC_nrofPorts P16 = new RRC_nrofPorts(5);
        public static final RRC_nrofPorts P24 = new RRC_nrofPorts(6);
        public static final RRC_nrofPorts P32 = new RRC_nrofPorts(7);
    
        private RRC_nrofPorts(long value) {
            super(value);
        }
    }

    public static class RRC_cdm_Type extends AsnEnumerated {
        public static final RRC_cdm_Type NOCDM = new RRC_cdm_Type(0);
        public static final RRC_cdm_Type FD_CDM2 = new RRC_cdm_Type(1);
        public static final RRC_cdm_Type CDM4_FD2_TD2 = new RRC_cdm_Type(2);
        public static final RRC_cdm_Type CDM8_FD2_TD4 = new RRC_cdm_Type(3);
    
        private RRC_cdm_Type(long value) {
            super(value);
        }
    }
}

