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

public class RRC_NAICS_Capability_Entry extends AsnSequence {
    public AsnInteger numberOfNAICS_CapableCC; // mandatory, VALUE(1..5)
    public RRC_numberOfAggregatedPRB numberOfAggregatedPRB; // mandatory

    public static class RRC_numberOfAggregatedPRB extends AsnEnumerated {
        public static final RRC_numberOfAggregatedPRB N50 = new RRC_numberOfAggregatedPRB(0);
        public static final RRC_numberOfAggregatedPRB N75 = new RRC_numberOfAggregatedPRB(1);
        public static final RRC_numberOfAggregatedPRB N100 = new RRC_numberOfAggregatedPRB(2);
        public static final RRC_numberOfAggregatedPRB N125 = new RRC_numberOfAggregatedPRB(3);
        public static final RRC_numberOfAggregatedPRB N150 = new RRC_numberOfAggregatedPRB(4);
        public static final RRC_numberOfAggregatedPRB N175 = new RRC_numberOfAggregatedPRB(5);
        public static final RRC_numberOfAggregatedPRB N200 = new RRC_numberOfAggregatedPRB(6);
        public static final RRC_numberOfAggregatedPRB N225 = new RRC_numberOfAggregatedPRB(7);
        public static final RRC_numberOfAggregatedPRB N250 = new RRC_numberOfAggregatedPRB(8);
        public static final RRC_numberOfAggregatedPRB N275 = new RRC_numberOfAggregatedPRB(9);
        public static final RRC_numberOfAggregatedPRB N300 = new RRC_numberOfAggregatedPRB(10);
        public static final RRC_numberOfAggregatedPRB N350 = new RRC_numberOfAggregatedPRB(11);
        public static final RRC_numberOfAggregatedPRB N400 = new RRC_numberOfAggregatedPRB(12);
        public static final RRC_numberOfAggregatedPRB N450 = new RRC_numberOfAggregatedPRB(13);
        public static final RRC_numberOfAggregatedPRB N500 = new RRC_numberOfAggregatedPRB(14);
        public static final RRC_numberOfAggregatedPRB SPARE = new RRC_numberOfAggregatedPRB(15);
    
        private RRC_numberOfAggregatedPRB(long value) {
            super(value);
        }
    }
}

