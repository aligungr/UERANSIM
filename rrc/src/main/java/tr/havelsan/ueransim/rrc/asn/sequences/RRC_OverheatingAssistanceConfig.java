/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_OverheatingAssistanceConfig extends AsnSequence {
    public RRC_overheatingIndicationProhibitTimer overheatingIndicationProhibitTimer; // mandatory

    public static class RRC_overheatingIndicationProhibitTimer extends AsnEnumerated {
        public static final RRC_overheatingIndicationProhibitTimer S0 = new RRC_overheatingIndicationProhibitTimer(0);
        public static final RRC_overheatingIndicationProhibitTimer S0DOT5 = new RRC_overheatingIndicationProhibitTimer(1);
        public static final RRC_overheatingIndicationProhibitTimer S1 = new RRC_overheatingIndicationProhibitTimer(2);
        public static final RRC_overheatingIndicationProhibitTimer S2 = new RRC_overheatingIndicationProhibitTimer(3);
        public static final RRC_overheatingIndicationProhibitTimer S5 = new RRC_overheatingIndicationProhibitTimer(4);
        public static final RRC_overheatingIndicationProhibitTimer S10 = new RRC_overheatingIndicationProhibitTimer(5);
        public static final RRC_overheatingIndicationProhibitTimer S20 = new RRC_overheatingIndicationProhibitTimer(6);
        public static final RRC_overheatingIndicationProhibitTimer S30 = new RRC_overheatingIndicationProhibitTimer(7);
        public static final RRC_overheatingIndicationProhibitTimer S60 = new RRC_overheatingIndicationProhibitTimer(8);
        public static final RRC_overheatingIndicationProhibitTimer S90 = new RRC_overheatingIndicationProhibitTimer(9);
        public static final RRC_overheatingIndicationProhibitTimer S120 = new RRC_overheatingIndicationProhibitTimer(10);
        public static final RRC_overheatingIndicationProhibitTimer S300 = new RRC_overheatingIndicationProhibitTimer(11);
        public static final RRC_overheatingIndicationProhibitTimer S600 = new RRC_overheatingIndicationProhibitTimer(12);
        public static final RRC_overheatingIndicationProhibitTimer SPARE3 = new RRC_overheatingIndicationProhibitTimer(13);
        public static final RRC_overheatingIndicationProhibitTimer SPARE2 = new RRC_overheatingIndicationProhibitTimer(14);
        public static final RRC_overheatingIndicationProhibitTimer SPARE1 = new RRC_overheatingIndicationProhibitTimer(15);
    
        private RRC_overheatingIndicationProhibitTimer(long value) {
            super(value);
        }
    }
}

