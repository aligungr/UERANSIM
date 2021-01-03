/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnBitString;
import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_UAC_BarringInfoSet extends AsnSequence {
    public RRC_uac_BarringFactor uac_BarringFactor; // mandatory
    public RRC_uac_BarringTime uac_BarringTime; // mandatory
    public AsnBitString uac_BarringForAccessIdentity; // mandatory, SIZE(7)

    public static class RRC_uac_BarringFactor extends AsnEnumerated {
        public static final RRC_uac_BarringFactor P00 = new RRC_uac_BarringFactor(0);
        public static final RRC_uac_BarringFactor P05 = new RRC_uac_BarringFactor(1);
        public static final RRC_uac_BarringFactor P10 = new RRC_uac_BarringFactor(2);
        public static final RRC_uac_BarringFactor P15 = new RRC_uac_BarringFactor(3);
        public static final RRC_uac_BarringFactor P20 = new RRC_uac_BarringFactor(4);
        public static final RRC_uac_BarringFactor P25 = new RRC_uac_BarringFactor(5);
        public static final RRC_uac_BarringFactor P30 = new RRC_uac_BarringFactor(6);
        public static final RRC_uac_BarringFactor P40 = new RRC_uac_BarringFactor(7);
        public static final RRC_uac_BarringFactor P50 = new RRC_uac_BarringFactor(8);
        public static final RRC_uac_BarringFactor P60 = new RRC_uac_BarringFactor(9);
        public static final RRC_uac_BarringFactor P70 = new RRC_uac_BarringFactor(10);
        public static final RRC_uac_BarringFactor P75 = new RRC_uac_BarringFactor(11);
        public static final RRC_uac_BarringFactor P80 = new RRC_uac_BarringFactor(12);
        public static final RRC_uac_BarringFactor P85 = new RRC_uac_BarringFactor(13);
        public static final RRC_uac_BarringFactor P90 = new RRC_uac_BarringFactor(14);
        public static final RRC_uac_BarringFactor P95 = new RRC_uac_BarringFactor(15);
    
        private RRC_uac_BarringFactor(long value) {
            super(value);
        }
    }

    public static class RRC_uac_BarringTime extends AsnEnumerated {
        public static final RRC_uac_BarringTime S4 = new RRC_uac_BarringTime(0);
        public static final RRC_uac_BarringTime S8 = new RRC_uac_BarringTime(1);
        public static final RRC_uac_BarringTime S16 = new RRC_uac_BarringTime(2);
        public static final RRC_uac_BarringTime S32 = new RRC_uac_BarringTime(3);
        public static final RRC_uac_BarringTime S64 = new RRC_uac_BarringTime(4);
        public static final RRC_uac_BarringTime S128 = new RRC_uac_BarringTime(5);
        public static final RRC_uac_BarringTime S256 = new RRC_uac_BarringTime(6);
        public static final RRC_uac_BarringTime S512 = new RRC_uac_BarringTime(7);
    
        private RRC_uac_BarringTime(long value) {
            super(value);
        }
    }
}

