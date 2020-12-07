/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_SpeedStateScaleFactors extends AsnSequence {
    public RRC_sf_Medium_2 sf_Medium; // mandatory
    public RRC_sf_High_2 sf_High; // mandatory

    public static class RRC_sf_High_2 extends AsnEnumerated {
        public static final RRC_sf_High_2 ODOT25 = new RRC_sf_High_2(0);
        public static final RRC_sf_High_2 ODOT5 = new RRC_sf_High_2(1);
        public static final RRC_sf_High_2 ODOT75 = new RRC_sf_High_2(2);
        public static final RRC_sf_High_2 LDOT0 = new RRC_sf_High_2(3);
    
        private RRC_sf_High_2(long value) {
            super(value);
        }
    }

    public static class RRC_sf_Medium_2 extends AsnEnumerated {
        public static final RRC_sf_Medium_2 ODOT25 = new RRC_sf_Medium_2(0);
        public static final RRC_sf_Medium_2 ODOT5 = new RRC_sf_Medium_2(1);
        public static final RRC_sf_Medium_2 ODOT75 = new RRC_sf_Medium_2(2);
        public static final RRC_sf_Medium_2 LDOT0 = new RRC_sf_Medium_2(3);
    
        private RRC_sf_Medium_2(long value) {
            super(value);
        }
    }
}

