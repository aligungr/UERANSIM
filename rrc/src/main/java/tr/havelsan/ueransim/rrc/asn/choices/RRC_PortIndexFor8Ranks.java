/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.choices;

import tr.havelsan.ueransim.asn.core.AsnChoice;
import tr.havelsan.ueransim.asn.core.AsnNull;
import tr.havelsan.ueransim.asn.core.AsnSequence;
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PortIndex2;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PortIndex4;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PortIndex8;

public class RRC_PortIndexFor8Ranks extends AsnChoice {
    public RRC_portIndex8 portIndex8;
    public RRC_portIndex4 portIndex4;
    public RRC_portIndex2 portIndex2;
    public AsnNull portIndex1;

    public static class RRC_portIndex2 extends AsnSequence {
        public RRC_PortIndex2 rank1_2; // optional
        public RRC_rank2_2 rank2_2; // optional, SIZE(2)
    
        // SIZE(2)
        public static class RRC_rank2_2 extends AsnSequenceOf<RRC_PortIndex2> {
        }
    }

    public static class RRC_portIndex8 extends AsnSequence {
        public RRC_PortIndex8 rank1_8; // optional
        public RRC_rank2_8 rank2_8; // optional, SIZE(2)
        public RRC_rank3_8 rank3_8; // optional, SIZE(3)
        public RRC_rank4_8 rank4_8; // optional, SIZE(4)
        public RRC_rank5_8 rank5_8; // optional, SIZE(5)
        public RRC_rank6_8 rank6_8; // optional, SIZE(6)
        public RRC_rank7_8 rank7_8; // optional, SIZE(7)
        public RRC_rank8_8 rank8_8; // optional, SIZE(8)
    
        // SIZE(2)
        public static class RRC_rank2_8 extends AsnSequenceOf<RRC_PortIndex8> {
        }
    
        // SIZE(8)
        public static class RRC_rank8_8 extends AsnSequenceOf<RRC_PortIndex8> {
        }
    
        // SIZE(4)
        public static class RRC_rank4_8 extends AsnSequenceOf<RRC_PortIndex8> {
        }
    
        // SIZE(7)
        public static class RRC_rank7_8 extends AsnSequenceOf<RRC_PortIndex8> {
        }
    
        // SIZE(3)
        public static class RRC_rank3_8 extends AsnSequenceOf<RRC_PortIndex8> {
        }
    
        // SIZE(5)
        public static class RRC_rank5_8 extends AsnSequenceOf<RRC_PortIndex8> {
        }
    
        // SIZE(6)
        public static class RRC_rank6_8 extends AsnSequenceOf<RRC_PortIndex8> {
        }
    }

    public static class RRC_portIndex4 extends AsnSequence {
        public RRC_PortIndex4 rank1_4; // optional
        public RRC_rank2_4 rank2_4; // optional, SIZE(2)
        public RRC_rank3_4 rank3_4; // optional, SIZE(3)
        public RRC_rank4_4 rank4_4; // optional, SIZE(4)
    
        // SIZE(3)
        public static class RRC_rank3_4 extends AsnSequenceOf<RRC_PortIndex4> {
        }
    
        // SIZE(2)
        public static class RRC_rank2_4 extends AsnSequenceOf<RRC_PortIndex4> {
        }
    
        // SIZE(4)
        public static class RRC_rank4_4 extends AsnSequenceOf<RRC_PortIndex4> {
        }
    }
}

