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

public class RRC_DRX_Info extends AsnSequence {
    public RRC_drx_LongCycleStartOffset_1 drx_LongCycleStartOffset; // mandatory
    public RRC_shortDRX_1 shortDRX; // optional

    public static class RRC_shortDRX_1 extends AsnSequence {
        public RRC_drx_ShortCycle_1 drx_ShortCycle; // mandatory
        public AsnInteger drx_ShortCycleTimer; // mandatory, VALUE(1..16)
    
        public static class RRC_drx_ShortCycle_1 extends AsnEnumerated {
            public static final RRC_drx_ShortCycle_1 MS2 = new RRC_drx_ShortCycle_1(0);
            public static final RRC_drx_ShortCycle_1 MS3 = new RRC_drx_ShortCycle_1(1);
            public static final RRC_drx_ShortCycle_1 MS4 = new RRC_drx_ShortCycle_1(2);
            public static final RRC_drx_ShortCycle_1 MS5 = new RRC_drx_ShortCycle_1(3);
            public static final RRC_drx_ShortCycle_1 MS6 = new RRC_drx_ShortCycle_1(4);
            public static final RRC_drx_ShortCycle_1 MS7 = new RRC_drx_ShortCycle_1(5);
            public static final RRC_drx_ShortCycle_1 MS8 = new RRC_drx_ShortCycle_1(6);
            public static final RRC_drx_ShortCycle_1 MS10 = new RRC_drx_ShortCycle_1(7);
            public static final RRC_drx_ShortCycle_1 MS14 = new RRC_drx_ShortCycle_1(8);
            public static final RRC_drx_ShortCycle_1 MS16 = new RRC_drx_ShortCycle_1(9);
            public static final RRC_drx_ShortCycle_1 MS20 = new RRC_drx_ShortCycle_1(10);
            public static final RRC_drx_ShortCycle_1 MS30 = new RRC_drx_ShortCycle_1(11);
            public static final RRC_drx_ShortCycle_1 MS32 = new RRC_drx_ShortCycle_1(12);
            public static final RRC_drx_ShortCycle_1 MS35 = new RRC_drx_ShortCycle_1(13);
            public static final RRC_drx_ShortCycle_1 MS40 = new RRC_drx_ShortCycle_1(14);
            public static final RRC_drx_ShortCycle_1 MS64 = new RRC_drx_ShortCycle_1(15);
            public static final RRC_drx_ShortCycle_1 MS80 = new RRC_drx_ShortCycle_1(16);
            public static final RRC_drx_ShortCycle_1 MS128 = new RRC_drx_ShortCycle_1(17);
            public static final RRC_drx_ShortCycle_1 MS160 = new RRC_drx_ShortCycle_1(18);
            public static final RRC_drx_ShortCycle_1 MS256 = new RRC_drx_ShortCycle_1(19);
            public static final RRC_drx_ShortCycle_1 MS320 = new RRC_drx_ShortCycle_1(20);
            public static final RRC_drx_ShortCycle_1 MS512 = new RRC_drx_ShortCycle_1(21);
            public static final RRC_drx_ShortCycle_1 MS640 = new RRC_drx_ShortCycle_1(22);
            public static final RRC_drx_ShortCycle_1 SPARE9 = new RRC_drx_ShortCycle_1(23);
            public static final RRC_drx_ShortCycle_1 SPARE8 = new RRC_drx_ShortCycle_1(24);
            public static final RRC_drx_ShortCycle_1 SPARE7 = new RRC_drx_ShortCycle_1(25);
            public static final RRC_drx_ShortCycle_1 SPARE6 = new RRC_drx_ShortCycle_1(26);
            public static final RRC_drx_ShortCycle_1 SPARE5 = new RRC_drx_ShortCycle_1(27);
            public static final RRC_drx_ShortCycle_1 SPARE4 = new RRC_drx_ShortCycle_1(28);
            public static final RRC_drx_ShortCycle_1 SPARE3 = new RRC_drx_ShortCycle_1(29);
            public static final RRC_drx_ShortCycle_1 SPARE2 = new RRC_drx_ShortCycle_1(30);
            public static final RRC_drx_ShortCycle_1 SPARE1 = new RRC_drx_ShortCycle_1(31);
        
            private RRC_drx_ShortCycle_1(long value) {
                super(value);
            }
        }
    }

    public static class RRC_drx_LongCycleStartOffset_1 extends AsnChoice {
        public AsnInteger ms10; // VALUE(0..9)
        public AsnInteger ms20; // VALUE(0..19)
        public AsnInteger ms32; // VALUE(0..31)
        public AsnInteger ms40; // VALUE(0..39)
        public AsnInteger ms60; // VALUE(0..59)
        public AsnInteger ms64; // VALUE(0..63)
        public AsnInteger ms70; // VALUE(0..69)
        public AsnInteger ms80; // VALUE(0..79)
        public AsnInteger ms128; // VALUE(0..127)
        public AsnInteger ms160; // VALUE(0..159)
        public AsnInteger ms256; // VALUE(0..255)
        public AsnInteger ms320; // VALUE(0..319)
        public AsnInteger ms512; // VALUE(0..511)
        public AsnInteger ms640; // VALUE(0..639)
        public AsnInteger ms1024; // VALUE(0..1023)
        public AsnInteger ms1280; // VALUE(0..1279)
        public AsnInteger ms2048; // VALUE(0..2047)
        public AsnInteger ms2560; // VALUE(0..2559)
        public AsnInteger ms5120; // VALUE(0..5119)
        public AsnInteger ms10240; // VALUE(0..10239)
    }
}

