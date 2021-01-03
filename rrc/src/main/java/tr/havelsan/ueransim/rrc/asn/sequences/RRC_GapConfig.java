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

public class RRC_GapConfig extends AsnSequence {
    public AsnInteger gapOffset; // mandatory, VALUE(0..159)
    public RRC_mgl mgl; // mandatory
    public RRC_mgrp mgrp; // mandatory
    public RRC_mgta mgta; // mandatory
    public RRC_ext1_21 ext1; // optional

    public static class RRC_ext1_21 extends AsnSequence {
        public RRC_refServCellIndicator refServCellIndicator; // optional
    
        public static class RRC_refServCellIndicator extends AsnEnumerated {
            public static final RRC_refServCellIndicator PCELL = new RRC_refServCellIndicator(0);
            public static final RRC_refServCellIndicator PSCELL = new RRC_refServCellIndicator(1);
            public static final RRC_refServCellIndicator MCG_FR2 = new RRC_refServCellIndicator(2);
        
            private RRC_refServCellIndicator(long value) {
                super(value);
            }
        }
    }

    public static class RRC_mgta extends AsnEnumerated {
        public static final RRC_mgta MS0 = new RRC_mgta(0);
        public static final RRC_mgta MS0DOT25 = new RRC_mgta(1);
        public static final RRC_mgta MS0DOT5 = new RRC_mgta(2);
    
        private RRC_mgta(long value) {
            super(value);
        }
    }

    public static class RRC_mgrp extends AsnEnumerated {
        public static final RRC_mgrp MS20 = new RRC_mgrp(0);
        public static final RRC_mgrp MS40 = new RRC_mgrp(1);
        public static final RRC_mgrp MS80 = new RRC_mgrp(2);
        public static final RRC_mgrp MS160 = new RRC_mgrp(3);
    
        private RRC_mgrp(long value) {
            super(value);
        }
    }

    public static class RRC_mgl extends AsnEnumerated {
        public static final RRC_mgl MS1DOT5 = new RRC_mgl(0);
        public static final RRC_mgl MS3 = new RRC_mgl(1);
        public static final RRC_mgl MS3DOT5 = new RRC_mgl(2);
        public static final RRC_mgl MS4 = new RRC_mgl(3);
        public static final RRC_mgl MS5DOT5 = new RRC_mgl(4);
        public static final RRC_mgl MS6 = new RRC_mgl(5);
    
        private RRC_mgl(long value) {
            super(value);
        }
    }
}

