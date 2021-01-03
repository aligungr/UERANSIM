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
import tr.havelsan.ueransim.asn.core.AsnInteger;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_MIB extends AsnSequence {
    public AsnBitString systemFrameNumber; // mandatory, SIZE(6)
    public RRC_subCarrierSpacingCommon subCarrierSpacingCommon; // mandatory
    public AsnInteger ssb_SubcarrierOffset; // mandatory, VALUE(0..15)
    public RRC_dmrs_TypeA_Position_1 dmrs_TypeA_Position; // mandatory
    public RRC_PDCCH_ConfigSIB1 pdcch_ConfigSIB1; // mandatory
    public RRC_cellBarred cellBarred; // mandatory
    public RRC_intraFreqReselection intraFreqReselection; // mandatory
    public AsnBitString spare; // mandatory, SIZE(1)

    public static class RRC_subCarrierSpacingCommon extends AsnEnumerated {
        public static final RRC_subCarrierSpacingCommon SCS15OR60 = new RRC_subCarrierSpacingCommon(0);
        public static final RRC_subCarrierSpacingCommon SCS30OR120 = new RRC_subCarrierSpacingCommon(1);
    
        private RRC_subCarrierSpacingCommon(long value) {
            super(value);
        }
    }

    public static class RRC_cellBarred extends AsnEnumerated {
        public static final RRC_cellBarred BARRED = new RRC_cellBarred(0);
        public static final RRC_cellBarred NOTBARRED = new RRC_cellBarred(1);
    
        private RRC_cellBarred(long value) {
            super(value);
        }
    }

    public static class RRC_dmrs_TypeA_Position_1 extends AsnEnumerated {
        public static final RRC_dmrs_TypeA_Position_1 POS2 = new RRC_dmrs_TypeA_Position_1(0);
        public static final RRC_dmrs_TypeA_Position_1 POS3 = new RRC_dmrs_TypeA_Position_1(1);
    
        private RRC_dmrs_TypeA_Position_1(long value) {
            super(value);
        }
    }

    public static class RRC_intraFreqReselection extends AsnEnumerated {
        public static final RRC_intraFreqReselection ALLOWED = new RRC_intraFreqReselection(0);
        public static final RRC_intraFreqReselection NOTALLOWED = new RRC_intraFreqReselection(1);
    
        private RRC_intraFreqReselection(long value) {
            super(value);
        }
    }
}

