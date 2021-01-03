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

public class RRC_RACH_ConfigGeneric extends AsnSequence {
    public AsnInteger prach_ConfigurationIndex; // mandatory, VALUE(0..255)
    public RRC_msg1_FDM msg1_FDM; // mandatory
    public AsnInteger msg1_FrequencyStart; // mandatory, VALUE(0..274)
    public AsnInteger zeroCorrelationZoneConfig; // mandatory, VALUE(0..15)
    public AsnInteger preambleReceivedTargetPower; // mandatory, VALUE(-202..-60)
    public RRC_preambleTransMax preambleTransMax; // mandatory
    public RRC_powerRampingStep powerRampingStep; // mandatory
    public RRC_ra_ResponseWindow ra_ResponseWindow; // mandatory

    public static class RRC_ra_ResponseWindow extends AsnEnumerated {
        public static final RRC_ra_ResponseWindow SL1 = new RRC_ra_ResponseWindow(0);
        public static final RRC_ra_ResponseWindow SL2 = new RRC_ra_ResponseWindow(1);
        public static final RRC_ra_ResponseWindow SL4 = new RRC_ra_ResponseWindow(2);
        public static final RRC_ra_ResponseWindow SL8 = new RRC_ra_ResponseWindow(3);
        public static final RRC_ra_ResponseWindow SL10 = new RRC_ra_ResponseWindow(4);
        public static final RRC_ra_ResponseWindow SL20 = new RRC_ra_ResponseWindow(5);
        public static final RRC_ra_ResponseWindow SL40 = new RRC_ra_ResponseWindow(6);
        public static final RRC_ra_ResponseWindow SL80 = new RRC_ra_ResponseWindow(7);
    
        private RRC_ra_ResponseWindow(long value) {
            super(value);
        }
    }

    public static class RRC_preambleTransMax extends AsnEnumerated {
        public static final RRC_preambleTransMax N3 = new RRC_preambleTransMax(0);
        public static final RRC_preambleTransMax N4 = new RRC_preambleTransMax(1);
        public static final RRC_preambleTransMax N5 = new RRC_preambleTransMax(2);
        public static final RRC_preambleTransMax N6 = new RRC_preambleTransMax(3);
        public static final RRC_preambleTransMax N7 = new RRC_preambleTransMax(4);
        public static final RRC_preambleTransMax N8 = new RRC_preambleTransMax(5);
        public static final RRC_preambleTransMax N10 = new RRC_preambleTransMax(6);
        public static final RRC_preambleTransMax N20 = new RRC_preambleTransMax(7);
        public static final RRC_preambleTransMax N50 = new RRC_preambleTransMax(8);
        public static final RRC_preambleTransMax N100 = new RRC_preambleTransMax(9);
        public static final RRC_preambleTransMax N200 = new RRC_preambleTransMax(10);
    
        private RRC_preambleTransMax(long value) {
            super(value);
        }
    }

    public static class RRC_msg1_FDM extends AsnEnumerated {
        public static final RRC_msg1_FDM ONE = new RRC_msg1_FDM(0);
        public static final RRC_msg1_FDM TWO = new RRC_msg1_FDM(1);
        public static final RRC_msg1_FDM FOUR = new RRC_msg1_FDM(2);
        public static final RRC_msg1_FDM EIGHT = new RRC_msg1_FDM(3);
    
        private RRC_msg1_FDM(long value) {
            super(value);
        }
    }

    public static class RRC_powerRampingStep extends AsnEnumerated {
        public static final RRC_powerRampingStep DB0 = new RRC_powerRampingStep(0);
        public static final RRC_powerRampingStep DB2 = new RRC_powerRampingStep(1);
        public static final RRC_powerRampingStep DB4 = new RRC_powerRampingStep(2);
        public static final RRC_powerRampingStep DB6 = new RRC_powerRampingStep(3);
    
        private RRC_powerRampingStep(long value) {
            super(value);
        }
    }
}

