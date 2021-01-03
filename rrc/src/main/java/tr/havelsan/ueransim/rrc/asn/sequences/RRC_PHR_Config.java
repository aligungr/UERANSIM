/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBoolean;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_PHR_Config extends AsnSequence {
    public RRC_phr_PeriodicTimer phr_PeriodicTimer; // mandatory
    public RRC_phr_ProhibitTimer phr_ProhibitTimer; // mandatory
    public RRC_phr_Tx_PowerFactorChange phr_Tx_PowerFactorChange; // mandatory
    public AsnBoolean multiplePHR; // mandatory
    public AsnBoolean dummy; // mandatory
    public AsnBoolean phr_Type2OtherCell; // mandatory
    public RRC_phr_ModeOtherCG phr_ModeOtherCG; // mandatory

    public static class RRC_phr_ModeOtherCG extends AsnEnumerated {
        public static final RRC_phr_ModeOtherCG REAL = new RRC_phr_ModeOtherCG(0);
        public static final RRC_phr_ModeOtherCG VIRTUAL = new RRC_phr_ModeOtherCG(1);
    
        private RRC_phr_ModeOtherCG(long value) {
            super(value);
        }
    }

    public static class RRC_phr_PeriodicTimer extends AsnEnumerated {
        public static final RRC_phr_PeriodicTimer SF10 = new RRC_phr_PeriodicTimer(0);
        public static final RRC_phr_PeriodicTimer SF20 = new RRC_phr_PeriodicTimer(1);
        public static final RRC_phr_PeriodicTimer SF50 = new RRC_phr_PeriodicTimer(2);
        public static final RRC_phr_PeriodicTimer SF100 = new RRC_phr_PeriodicTimer(3);
        public static final RRC_phr_PeriodicTimer SF200 = new RRC_phr_PeriodicTimer(4);
        public static final RRC_phr_PeriodicTimer SF500 = new RRC_phr_PeriodicTimer(5);
        public static final RRC_phr_PeriodicTimer SF1000 = new RRC_phr_PeriodicTimer(6);
        public static final RRC_phr_PeriodicTimer INFINITY = new RRC_phr_PeriodicTimer(7);
    
        private RRC_phr_PeriodicTimer(long value) {
            super(value);
        }
    }

    public static class RRC_phr_ProhibitTimer extends AsnEnumerated {
        public static final RRC_phr_ProhibitTimer SF0 = new RRC_phr_ProhibitTimer(0);
        public static final RRC_phr_ProhibitTimer SF10 = new RRC_phr_ProhibitTimer(1);
        public static final RRC_phr_ProhibitTimer SF20 = new RRC_phr_ProhibitTimer(2);
        public static final RRC_phr_ProhibitTimer SF50 = new RRC_phr_ProhibitTimer(3);
        public static final RRC_phr_ProhibitTimer SF100 = new RRC_phr_ProhibitTimer(4);
        public static final RRC_phr_ProhibitTimer SF200 = new RRC_phr_ProhibitTimer(5);
        public static final RRC_phr_ProhibitTimer SF500 = new RRC_phr_ProhibitTimer(6);
        public static final RRC_phr_ProhibitTimer SF1000 = new RRC_phr_ProhibitTimer(7);
    
        private RRC_phr_ProhibitTimer(long value) {
            super(value);
        }
    }

    public static class RRC_phr_Tx_PowerFactorChange extends AsnEnumerated {
        public static final RRC_phr_Tx_PowerFactorChange DB1 = new RRC_phr_Tx_PowerFactorChange(0);
        public static final RRC_phr_Tx_PowerFactorChange DB3 = new RRC_phr_Tx_PowerFactorChange(1);
        public static final RRC_phr_Tx_PowerFactorChange DB6 = new RRC_phr_Tx_PowerFactorChange(2);
        public static final RRC_phr_Tx_PowerFactorChange INFINITY = new RRC_phr_Tx_PowerFactorChange(3);
    
        private RRC_phr_Tx_PowerFactorChange(long value) {
            super(value);
        }
    }
}

