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

public class RRC_DRX_Config extends AsnSequence {
    public RRC_drx_onDurationTimer drx_onDurationTimer; // mandatory
    public RRC_drx_InactivityTimer drx_InactivityTimer; // mandatory
    public AsnInteger drx_HARQ_RTT_TimerDL; // mandatory, VALUE(0..56)
    public AsnInteger drx_HARQ_RTT_TimerUL; // mandatory, VALUE(0..56)
    public RRC_drx_RetransmissionTimerDL drx_RetransmissionTimerDL; // mandatory
    public RRC_drx_RetransmissionTimerUL drx_RetransmissionTimerUL; // mandatory
    public RRC_drx_LongCycleStartOffset_2 drx_LongCycleStartOffset; // mandatory
    public RRC_shortDRX_2 shortDRX; // optional
    public AsnInteger drx_SlotOffset; // mandatory, VALUE(0..31)

    public static class RRC_drx_RetransmissionTimerDL extends AsnEnumerated {
        public static final RRC_drx_RetransmissionTimerDL SL0 = new RRC_drx_RetransmissionTimerDL(0);
        public static final RRC_drx_RetransmissionTimerDL SL1 = new RRC_drx_RetransmissionTimerDL(1);
        public static final RRC_drx_RetransmissionTimerDL SL2 = new RRC_drx_RetransmissionTimerDL(2);
        public static final RRC_drx_RetransmissionTimerDL SL4 = new RRC_drx_RetransmissionTimerDL(3);
        public static final RRC_drx_RetransmissionTimerDL SL6 = new RRC_drx_RetransmissionTimerDL(4);
        public static final RRC_drx_RetransmissionTimerDL SL8 = new RRC_drx_RetransmissionTimerDL(5);
        public static final RRC_drx_RetransmissionTimerDL SL16 = new RRC_drx_RetransmissionTimerDL(6);
        public static final RRC_drx_RetransmissionTimerDL SL24 = new RRC_drx_RetransmissionTimerDL(7);
        public static final RRC_drx_RetransmissionTimerDL SL33 = new RRC_drx_RetransmissionTimerDL(8);
        public static final RRC_drx_RetransmissionTimerDL SL40 = new RRC_drx_RetransmissionTimerDL(9);
        public static final RRC_drx_RetransmissionTimerDL SL64 = new RRC_drx_RetransmissionTimerDL(10);
        public static final RRC_drx_RetransmissionTimerDL SL80 = new RRC_drx_RetransmissionTimerDL(11);
        public static final RRC_drx_RetransmissionTimerDL SL96 = new RRC_drx_RetransmissionTimerDL(12);
        public static final RRC_drx_RetransmissionTimerDL SL112 = new RRC_drx_RetransmissionTimerDL(13);
        public static final RRC_drx_RetransmissionTimerDL SL128 = new RRC_drx_RetransmissionTimerDL(14);
        public static final RRC_drx_RetransmissionTimerDL SL160 = new RRC_drx_RetransmissionTimerDL(15);
        public static final RRC_drx_RetransmissionTimerDL SL320 = new RRC_drx_RetransmissionTimerDL(16);
        public static final RRC_drx_RetransmissionTimerDL SPARE15 = new RRC_drx_RetransmissionTimerDL(17);
        public static final RRC_drx_RetransmissionTimerDL SPARE14 = new RRC_drx_RetransmissionTimerDL(18);
        public static final RRC_drx_RetransmissionTimerDL SPARE13 = new RRC_drx_RetransmissionTimerDL(19);
        public static final RRC_drx_RetransmissionTimerDL SPARE12 = new RRC_drx_RetransmissionTimerDL(20);
        public static final RRC_drx_RetransmissionTimerDL SPARE11 = new RRC_drx_RetransmissionTimerDL(21);
        public static final RRC_drx_RetransmissionTimerDL SPARE10 = new RRC_drx_RetransmissionTimerDL(22);
        public static final RRC_drx_RetransmissionTimerDL SPARE9 = new RRC_drx_RetransmissionTimerDL(23);
        public static final RRC_drx_RetransmissionTimerDL SPARE8 = new RRC_drx_RetransmissionTimerDL(24);
        public static final RRC_drx_RetransmissionTimerDL SPARE7 = new RRC_drx_RetransmissionTimerDL(25);
        public static final RRC_drx_RetransmissionTimerDL SPARE6 = new RRC_drx_RetransmissionTimerDL(26);
        public static final RRC_drx_RetransmissionTimerDL SPARE5 = new RRC_drx_RetransmissionTimerDL(27);
        public static final RRC_drx_RetransmissionTimerDL SPARE4 = new RRC_drx_RetransmissionTimerDL(28);
        public static final RRC_drx_RetransmissionTimerDL SPARE3 = new RRC_drx_RetransmissionTimerDL(29);
        public static final RRC_drx_RetransmissionTimerDL SPARE2 = new RRC_drx_RetransmissionTimerDL(30);
        public static final RRC_drx_RetransmissionTimerDL SPARE1 = new RRC_drx_RetransmissionTimerDL(31);
    
        private RRC_drx_RetransmissionTimerDL(long value) {
            super(value);
        }
    }

    public static class RRC_drx_onDurationTimer extends AsnChoice {
        public AsnInteger subMilliSeconds; // VALUE(1..31)
        public RRC_milliSeconds milliSeconds;
    
        public static class RRC_milliSeconds extends AsnEnumerated {
            public static final RRC_milliSeconds MS1 = new RRC_milliSeconds(0);
            public static final RRC_milliSeconds MS2 = new RRC_milliSeconds(1);
            public static final RRC_milliSeconds MS3 = new RRC_milliSeconds(2);
            public static final RRC_milliSeconds MS4 = new RRC_milliSeconds(3);
            public static final RRC_milliSeconds MS5 = new RRC_milliSeconds(4);
            public static final RRC_milliSeconds MS6 = new RRC_milliSeconds(5);
            public static final RRC_milliSeconds MS8 = new RRC_milliSeconds(6);
            public static final RRC_milliSeconds MS10 = new RRC_milliSeconds(7);
            public static final RRC_milliSeconds MS20 = new RRC_milliSeconds(8);
            public static final RRC_milliSeconds MS30 = new RRC_milliSeconds(9);
            public static final RRC_milliSeconds MS40 = new RRC_milliSeconds(10);
            public static final RRC_milliSeconds MS50 = new RRC_milliSeconds(11);
            public static final RRC_milliSeconds MS60 = new RRC_milliSeconds(12);
            public static final RRC_milliSeconds MS80 = new RRC_milliSeconds(13);
            public static final RRC_milliSeconds MS100 = new RRC_milliSeconds(14);
            public static final RRC_milliSeconds MS200 = new RRC_milliSeconds(15);
            public static final RRC_milliSeconds MS300 = new RRC_milliSeconds(16);
            public static final RRC_milliSeconds MS400 = new RRC_milliSeconds(17);
            public static final RRC_milliSeconds MS500 = new RRC_milliSeconds(18);
            public static final RRC_milliSeconds MS600 = new RRC_milliSeconds(19);
            public static final RRC_milliSeconds MS800 = new RRC_milliSeconds(20);
            public static final RRC_milliSeconds MS1000 = new RRC_milliSeconds(21);
            public static final RRC_milliSeconds MS1200 = new RRC_milliSeconds(22);
            public static final RRC_milliSeconds MS1600 = new RRC_milliSeconds(23);
            public static final RRC_milliSeconds SPARE8 = new RRC_milliSeconds(24);
            public static final RRC_milliSeconds SPARE7 = new RRC_milliSeconds(25);
            public static final RRC_milliSeconds SPARE6 = new RRC_milliSeconds(26);
            public static final RRC_milliSeconds SPARE5 = new RRC_milliSeconds(27);
            public static final RRC_milliSeconds SPARE4 = new RRC_milliSeconds(28);
            public static final RRC_milliSeconds SPARE3 = new RRC_milliSeconds(29);
            public static final RRC_milliSeconds SPARE2 = new RRC_milliSeconds(30);
            public static final RRC_milliSeconds SPARE1 = new RRC_milliSeconds(31);
        
            private RRC_milliSeconds(long value) {
                super(value);
            }
        }
    }

    public static class RRC_drx_InactivityTimer extends AsnEnumerated {
        public static final RRC_drx_InactivityTimer MS0 = new RRC_drx_InactivityTimer(0);
        public static final RRC_drx_InactivityTimer MS1 = new RRC_drx_InactivityTimer(1);
        public static final RRC_drx_InactivityTimer MS2 = new RRC_drx_InactivityTimer(2);
        public static final RRC_drx_InactivityTimer MS3 = new RRC_drx_InactivityTimer(3);
        public static final RRC_drx_InactivityTimer MS4 = new RRC_drx_InactivityTimer(4);
        public static final RRC_drx_InactivityTimer MS5 = new RRC_drx_InactivityTimer(5);
        public static final RRC_drx_InactivityTimer MS6 = new RRC_drx_InactivityTimer(6);
        public static final RRC_drx_InactivityTimer MS8 = new RRC_drx_InactivityTimer(7);
        public static final RRC_drx_InactivityTimer MS10 = new RRC_drx_InactivityTimer(8);
        public static final RRC_drx_InactivityTimer MS20 = new RRC_drx_InactivityTimer(9);
        public static final RRC_drx_InactivityTimer MS30 = new RRC_drx_InactivityTimer(10);
        public static final RRC_drx_InactivityTimer MS40 = new RRC_drx_InactivityTimer(11);
        public static final RRC_drx_InactivityTimer MS50 = new RRC_drx_InactivityTimer(12);
        public static final RRC_drx_InactivityTimer MS60 = new RRC_drx_InactivityTimer(13);
        public static final RRC_drx_InactivityTimer MS80 = new RRC_drx_InactivityTimer(14);
        public static final RRC_drx_InactivityTimer MS100 = new RRC_drx_InactivityTimer(15);
        public static final RRC_drx_InactivityTimer MS200 = new RRC_drx_InactivityTimer(16);
        public static final RRC_drx_InactivityTimer MS300 = new RRC_drx_InactivityTimer(17);
        public static final RRC_drx_InactivityTimer MS500 = new RRC_drx_InactivityTimer(18);
        public static final RRC_drx_InactivityTimer MS750 = new RRC_drx_InactivityTimer(19);
        public static final RRC_drx_InactivityTimer MS1280 = new RRC_drx_InactivityTimer(20);
        public static final RRC_drx_InactivityTimer MS1920 = new RRC_drx_InactivityTimer(21);
        public static final RRC_drx_InactivityTimer MS2560 = new RRC_drx_InactivityTimer(22);
        public static final RRC_drx_InactivityTimer SPARE9 = new RRC_drx_InactivityTimer(23);
        public static final RRC_drx_InactivityTimer SPARE8 = new RRC_drx_InactivityTimer(24);
        public static final RRC_drx_InactivityTimer SPARE7 = new RRC_drx_InactivityTimer(25);
        public static final RRC_drx_InactivityTimer SPARE6 = new RRC_drx_InactivityTimer(26);
        public static final RRC_drx_InactivityTimer SPARE5 = new RRC_drx_InactivityTimer(27);
        public static final RRC_drx_InactivityTimer SPARE4 = new RRC_drx_InactivityTimer(28);
        public static final RRC_drx_InactivityTimer SPARE3 = new RRC_drx_InactivityTimer(29);
        public static final RRC_drx_InactivityTimer SPARE2 = new RRC_drx_InactivityTimer(30);
        public static final RRC_drx_InactivityTimer SPARE1 = new RRC_drx_InactivityTimer(31);
    
        private RRC_drx_InactivityTimer(long value) {
            super(value);
        }
    }

    public static class RRC_drx_RetransmissionTimerUL extends AsnEnumerated {
        public static final RRC_drx_RetransmissionTimerUL SL0 = new RRC_drx_RetransmissionTimerUL(0);
        public static final RRC_drx_RetransmissionTimerUL SL1 = new RRC_drx_RetransmissionTimerUL(1);
        public static final RRC_drx_RetransmissionTimerUL SL2 = new RRC_drx_RetransmissionTimerUL(2);
        public static final RRC_drx_RetransmissionTimerUL SL4 = new RRC_drx_RetransmissionTimerUL(3);
        public static final RRC_drx_RetransmissionTimerUL SL6 = new RRC_drx_RetransmissionTimerUL(4);
        public static final RRC_drx_RetransmissionTimerUL SL8 = new RRC_drx_RetransmissionTimerUL(5);
        public static final RRC_drx_RetransmissionTimerUL SL16 = new RRC_drx_RetransmissionTimerUL(6);
        public static final RRC_drx_RetransmissionTimerUL SL24 = new RRC_drx_RetransmissionTimerUL(7);
        public static final RRC_drx_RetransmissionTimerUL SL33 = new RRC_drx_RetransmissionTimerUL(8);
        public static final RRC_drx_RetransmissionTimerUL SL40 = new RRC_drx_RetransmissionTimerUL(9);
        public static final RRC_drx_RetransmissionTimerUL SL64 = new RRC_drx_RetransmissionTimerUL(10);
        public static final RRC_drx_RetransmissionTimerUL SL80 = new RRC_drx_RetransmissionTimerUL(11);
        public static final RRC_drx_RetransmissionTimerUL SL96 = new RRC_drx_RetransmissionTimerUL(12);
        public static final RRC_drx_RetransmissionTimerUL SL112 = new RRC_drx_RetransmissionTimerUL(13);
        public static final RRC_drx_RetransmissionTimerUL SL128 = new RRC_drx_RetransmissionTimerUL(14);
        public static final RRC_drx_RetransmissionTimerUL SL160 = new RRC_drx_RetransmissionTimerUL(15);
        public static final RRC_drx_RetransmissionTimerUL SL320 = new RRC_drx_RetransmissionTimerUL(16);
        public static final RRC_drx_RetransmissionTimerUL SPARE15 = new RRC_drx_RetransmissionTimerUL(17);
        public static final RRC_drx_RetransmissionTimerUL SPARE14 = new RRC_drx_RetransmissionTimerUL(18);
        public static final RRC_drx_RetransmissionTimerUL SPARE13 = new RRC_drx_RetransmissionTimerUL(19);
        public static final RRC_drx_RetransmissionTimerUL SPARE12 = new RRC_drx_RetransmissionTimerUL(20);
        public static final RRC_drx_RetransmissionTimerUL SPARE11 = new RRC_drx_RetransmissionTimerUL(21);
        public static final RRC_drx_RetransmissionTimerUL SPARE10 = new RRC_drx_RetransmissionTimerUL(22);
        public static final RRC_drx_RetransmissionTimerUL SPARE9 = new RRC_drx_RetransmissionTimerUL(23);
        public static final RRC_drx_RetransmissionTimerUL SPARE8 = new RRC_drx_RetransmissionTimerUL(24);
        public static final RRC_drx_RetransmissionTimerUL SPARE7 = new RRC_drx_RetransmissionTimerUL(25);
        public static final RRC_drx_RetransmissionTimerUL SPARE6 = new RRC_drx_RetransmissionTimerUL(26);
        public static final RRC_drx_RetransmissionTimerUL SPARE5 = new RRC_drx_RetransmissionTimerUL(27);
        public static final RRC_drx_RetransmissionTimerUL SPARE4 = new RRC_drx_RetransmissionTimerUL(28);
        public static final RRC_drx_RetransmissionTimerUL SPARE3 = new RRC_drx_RetransmissionTimerUL(29);
        public static final RRC_drx_RetransmissionTimerUL SPARE2 = new RRC_drx_RetransmissionTimerUL(30);
        public static final RRC_drx_RetransmissionTimerUL SPARE1 = new RRC_drx_RetransmissionTimerUL(31);
    
        private RRC_drx_RetransmissionTimerUL(long value) {
            super(value);
        }
    }

    public static class RRC_drx_LongCycleStartOffset_2 extends AsnChoice {
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

    public static class RRC_shortDRX_2 extends AsnSequence {
        public RRC_drx_ShortCycle_2 drx_ShortCycle; // mandatory
        public AsnInteger drx_ShortCycleTimer; // mandatory, VALUE(1..16)
    
        public static class RRC_drx_ShortCycle_2 extends AsnEnumerated {
            public static final RRC_drx_ShortCycle_2 MS2 = new RRC_drx_ShortCycle_2(0);
            public static final RRC_drx_ShortCycle_2 MS3 = new RRC_drx_ShortCycle_2(1);
            public static final RRC_drx_ShortCycle_2 MS4 = new RRC_drx_ShortCycle_2(2);
            public static final RRC_drx_ShortCycle_2 MS5 = new RRC_drx_ShortCycle_2(3);
            public static final RRC_drx_ShortCycle_2 MS6 = new RRC_drx_ShortCycle_2(4);
            public static final RRC_drx_ShortCycle_2 MS7 = new RRC_drx_ShortCycle_2(5);
            public static final RRC_drx_ShortCycle_2 MS8 = new RRC_drx_ShortCycle_2(6);
            public static final RRC_drx_ShortCycle_2 MS10 = new RRC_drx_ShortCycle_2(7);
            public static final RRC_drx_ShortCycle_2 MS14 = new RRC_drx_ShortCycle_2(8);
            public static final RRC_drx_ShortCycle_2 MS16 = new RRC_drx_ShortCycle_2(9);
            public static final RRC_drx_ShortCycle_2 MS20 = new RRC_drx_ShortCycle_2(10);
            public static final RRC_drx_ShortCycle_2 MS30 = new RRC_drx_ShortCycle_2(11);
            public static final RRC_drx_ShortCycle_2 MS32 = new RRC_drx_ShortCycle_2(12);
            public static final RRC_drx_ShortCycle_2 MS35 = new RRC_drx_ShortCycle_2(13);
            public static final RRC_drx_ShortCycle_2 MS40 = new RRC_drx_ShortCycle_2(14);
            public static final RRC_drx_ShortCycle_2 MS64 = new RRC_drx_ShortCycle_2(15);
            public static final RRC_drx_ShortCycle_2 MS80 = new RRC_drx_ShortCycle_2(16);
            public static final RRC_drx_ShortCycle_2 MS128 = new RRC_drx_ShortCycle_2(17);
            public static final RRC_drx_ShortCycle_2 MS160 = new RRC_drx_ShortCycle_2(18);
            public static final RRC_drx_ShortCycle_2 MS256 = new RRC_drx_ShortCycle_2(19);
            public static final RRC_drx_ShortCycle_2 MS320 = new RRC_drx_ShortCycle_2(20);
            public static final RRC_drx_ShortCycle_2 MS512 = new RRC_drx_ShortCycle_2(21);
            public static final RRC_drx_ShortCycle_2 MS640 = new RRC_drx_ShortCycle_2(22);
            public static final RRC_drx_ShortCycle_2 SPARE9 = new RRC_drx_ShortCycle_2(23);
            public static final RRC_drx_ShortCycle_2 SPARE8 = new RRC_drx_ShortCycle_2(24);
            public static final RRC_drx_ShortCycle_2 SPARE7 = new RRC_drx_ShortCycle_2(25);
            public static final RRC_drx_ShortCycle_2 SPARE6 = new RRC_drx_ShortCycle_2(26);
            public static final RRC_drx_ShortCycle_2 SPARE5 = new RRC_drx_ShortCycle_2(27);
            public static final RRC_drx_ShortCycle_2 SPARE4 = new RRC_drx_ShortCycle_2(28);
            public static final RRC_drx_ShortCycle_2 SPARE3 = new RRC_drx_ShortCycle_2(29);
            public static final RRC_drx_ShortCycle_2 SPARE2 = new RRC_drx_ShortCycle_2(30);
            public static final RRC_drx_ShortCycle_2 SPARE1 = new RRC_drx_ShortCycle_2(31);
        
            private RRC_drx_ShortCycle_2(long value) {
                super(value);
            }
        }
    }
}

