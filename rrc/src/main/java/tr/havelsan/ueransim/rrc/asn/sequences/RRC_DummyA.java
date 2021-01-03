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

public class RRC_DummyA extends AsnSequence {
    public AsnInteger maxNumberNZP_CSI_RS_PerCC; // mandatory, VALUE(1..32)
    public RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC maxNumberPortsAcrossNZP_CSI_RS_PerCC; // mandatory
    public RRC_maxNumberCS_IM_PerCC maxNumberCS_IM_PerCC; // mandatory
    public RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC maxNumberSimultaneousCSI_RS_ActBWP_AllCC; // mandatory
    public RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC; // mandatory

    public static class RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC extends AsnEnumerated {
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P8 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(0);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P12 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(1);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P16 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(2);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P24 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(3);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P32 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(4);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P40 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(5);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P48 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(6);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P56 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(7);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P64 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(8);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P72 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(9);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P80 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(10);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P88 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(11);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P96 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(12);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P104 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(13);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P112 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(14);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P120 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(15);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P128 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(16);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P136 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(17);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P144 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(18);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P152 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(19);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P160 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(20);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P168 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(21);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P176 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(22);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P184 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(23);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P192 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(24);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P200 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(25);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P208 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(26);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P216 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(27);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P224 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(28);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P232 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(29);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P240 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(30);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P248 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(31);
        public static final RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC P256 = new RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(32);
    
        private RRC_totalNumberPortsSimultaneousCSI_RS_ActBWP_AllCC(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC extends AsnEnumerated {
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N5 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(0);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N6 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(1);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N7 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(2);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N8 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(3);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N9 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(4);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N10 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(5);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N12 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(6);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N14 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(7);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N16 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(8);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N18 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(9);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N20 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(10);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N22 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(11);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N24 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(12);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N26 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(13);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N28 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(14);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N30 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(15);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N32 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(16);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N34 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(17);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N36 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(18);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N38 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(19);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N40 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(20);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N42 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(21);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N44 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(22);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N46 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(23);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N48 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(24);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N50 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(25);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N52 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(26);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N54 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(27);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N56 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(28);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N58 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(29);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N60 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(30);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N62 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(31);
        public static final RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC N64 = new RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(32);
    
        private RRC_maxNumberSimultaneousCSI_RS_ActBWP_AllCC(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberCS_IM_PerCC extends AsnEnumerated {
        public static final RRC_maxNumberCS_IM_PerCC N1 = new RRC_maxNumberCS_IM_PerCC(0);
        public static final RRC_maxNumberCS_IM_PerCC N2 = new RRC_maxNumberCS_IM_PerCC(1);
        public static final RRC_maxNumberCS_IM_PerCC N4 = new RRC_maxNumberCS_IM_PerCC(2);
        public static final RRC_maxNumberCS_IM_PerCC N8 = new RRC_maxNumberCS_IM_PerCC(3);
        public static final RRC_maxNumberCS_IM_PerCC N16 = new RRC_maxNumberCS_IM_PerCC(4);
        public static final RRC_maxNumberCS_IM_PerCC N32 = new RRC_maxNumberCS_IM_PerCC(5);
    
        private RRC_maxNumberCS_IM_PerCC(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC extends AsnEnumerated {
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P2 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(0);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P4 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(1);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P8 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(2);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P12 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(3);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P16 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(4);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P24 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(5);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P32 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(6);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P40 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(7);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P48 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(8);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P56 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(9);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P64 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(10);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P72 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(11);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P80 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(12);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P88 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(13);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P96 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(14);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P104 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(15);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P112 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(16);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P120 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(17);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P128 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(18);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P136 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(19);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P144 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(20);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P152 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(21);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P160 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(22);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P168 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(23);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P176 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(24);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P184 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(25);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P192 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(26);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P200 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(27);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P208 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(28);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P216 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(29);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P224 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(30);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P232 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(31);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P240 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(32);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P248 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(33);
        public static final RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC P256 = new RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(34);
    
        private RRC_maxNumberPortsAcrossNZP_CSI_RS_PerCC(long value) {
            super(value);
        }
    }
}

