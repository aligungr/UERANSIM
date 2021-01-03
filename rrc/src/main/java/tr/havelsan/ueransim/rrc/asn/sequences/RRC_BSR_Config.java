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

public class RRC_BSR_Config extends AsnSequence {
    public RRC_periodicBSR_Timer periodicBSR_Timer; // mandatory
    public RRC_retxBSR_Timer retxBSR_Timer; // mandatory
    public RRC_logicalChannelSR_DelayTimer_1 logicalChannelSR_DelayTimer; // optional

    public static class RRC_logicalChannelSR_DelayTimer_1 extends AsnEnumerated {
        public static final RRC_logicalChannelSR_DelayTimer_1 SF20 = new RRC_logicalChannelSR_DelayTimer_1(0);
        public static final RRC_logicalChannelSR_DelayTimer_1 SF40 = new RRC_logicalChannelSR_DelayTimer_1(1);
        public static final RRC_logicalChannelSR_DelayTimer_1 SF64 = new RRC_logicalChannelSR_DelayTimer_1(2);
        public static final RRC_logicalChannelSR_DelayTimer_1 SF128 = new RRC_logicalChannelSR_DelayTimer_1(3);
        public static final RRC_logicalChannelSR_DelayTimer_1 SF512 = new RRC_logicalChannelSR_DelayTimer_1(4);
        public static final RRC_logicalChannelSR_DelayTimer_1 SF1024 = new RRC_logicalChannelSR_DelayTimer_1(5);
        public static final RRC_logicalChannelSR_DelayTimer_1 SF2560 = new RRC_logicalChannelSR_DelayTimer_1(6);
        public static final RRC_logicalChannelSR_DelayTimer_1 SPARE1 = new RRC_logicalChannelSR_DelayTimer_1(7);
    
        private RRC_logicalChannelSR_DelayTimer_1(long value) {
            super(value);
        }
    }

    public static class RRC_retxBSR_Timer extends AsnEnumerated {
        public static final RRC_retxBSR_Timer SF10 = new RRC_retxBSR_Timer(0);
        public static final RRC_retxBSR_Timer SF20 = new RRC_retxBSR_Timer(1);
        public static final RRC_retxBSR_Timer SF40 = new RRC_retxBSR_Timer(2);
        public static final RRC_retxBSR_Timer SF80 = new RRC_retxBSR_Timer(3);
        public static final RRC_retxBSR_Timer SF160 = new RRC_retxBSR_Timer(4);
        public static final RRC_retxBSR_Timer SF320 = new RRC_retxBSR_Timer(5);
        public static final RRC_retxBSR_Timer SF640 = new RRC_retxBSR_Timer(6);
        public static final RRC_retxBSR_Timer SF1280 = new RRC_retxBSR_Timer(7);
        public static final RRC_retxBSR_Timer SF2560 = new RRC_retxBSR_Timer(8);
        public static final RRC_retxBSR_Timer SF5120 = new RRC_retxBSR_Timer(9);
        public static final RRC_retxBSR_Timer SF10240 = new RRC_retxBSR_Timer(10);
        public static final RRC_retxBSR_Timer SPARE5 = new RRC_retxBSR_Timer(11);
        public static final RRC_retxBSR_Timer SPARE4 = new RRC_retxBSR_Timer(12);
        public static final RRC_retxBSR_Timer SPARE3 = new RRC_retxBSR_Timer(13);
        public static final RRC_retxBSR_Timer SPARE2 = new RRC_retxBSR_Timer(14);
        public static final RRC_retxBSR_Timer SPARE1 = new RRC_retxBSR_Timer(15);
    
        private RRC_retxBSR_Timer(long value) {
            super(value);
        }
    }

    public static class RRC_periodicBSR_Timer extends AsnEnumerated {
        public static final RRC_periodicBSR_Timer SF1 = new RRC_periodicBSR_Timer(0);
        public static final RRC_periodicBSR_Timer SF5 = new RRC_periodicBSR_Timer(1);
        public static final RRC_periodicBSR_Timer SF10 = new RRC_periodicBSR_Timer(2);
        public static final RRC_periodicBSR_Timer SF16 = new RRC_periodicBSR_Timer(3);
        public static final RRC_periodicBSR_Timer SF20 = new RRC_periodicBSR_Timer(4);
        public static final RRC_periodicBSR_Timer SF32 = new RRC_periodicBSR_Timer(5);
        public static final RRC_periodicBSR_Timer SF40 = new RRC_periodicBSR_Timer(6);
        public static final RRC_periodicBSR_Timer SF64 = new RRC_periodicBSR_Timer(7);
        public static final RRC_periodicBSR_Timer SF80 = new RRC_periodicBSR_Timer(8);
        public static final RRC_periodicBSR_Timer SF128 = new RRC_periodicBSR_Timer(9);
        public static final RRC_periodicBSR_Timer SF160 = new RRC_periodicBSR_Timer(10);
        public static final RRC_periodicBSR_Timer SF320 = new RRC_periodicBSR_Timer(11);
        public static final RRC_periodicBSR_Timer SF640 = new RRC_periodicBSR_Timer(12);
        public static final RRC_periodicBSR_Timer SF1280 = new RRC_periodicBSR_Timer(13);
        public static final RRC_periodicBSR_Timer SF2560 = new RRC_periodicBSR_Timer(14);
        public static final RRC_periodicBSR_Timer INFINITY = new RRC_periodicBSR_Timer(15);
    
        private RRC_periodicBSR_Timer(long value) {
            super(value);
        }
    }
}

