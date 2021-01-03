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

public class RRC_SSB_MTC extends AsnSequence {
    public RRC_periodicityAndOffset_1 periodicityAndOffset; // mandatory
    public RRC_duration duration; // mandatory

    public static class RRC_duration extends AsnEnumerated {
        public static final RRC_duration SF1 = new RRC_duration(0);
        public static final RRC_duration SF2 = new RRC_duration(1);
        public static final RRC_duration SF3 = new RRC_duration(2);
        public static final RRC_duration SF4 = new RRC_duration(3);
        public static final RRC_duration SF5 = new RRC_duration(4);
    
        private RRC_duration(long value) {
            super(value);
        }
    }

    public static class RRC_periodicityAndOffset_1 extends AsnChoice {
        public AsnInteger sf5; // VALUE(0..4)
        public AsnInteger sf10; // VALUE(0..9)
        public AsnInteger sf20; // VALUE(0..19)
        public AsnInteger sf40; // VALUE(0..39)
        public AsnInteger sf80; // VALUE(0..79)
        public AsnInteger sf160; // VALUE(0..159)
    }
}

