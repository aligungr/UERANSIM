/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_ServCellIndex;

public class RRC_PDSCH_ServingCellConfig extends AsnSequence {
    public RRC_SetupRelease_PDSCH_CodeBlockGroupTransmission codeBlockGroupTransmission; // optional
    public RRC_xOverhead_2 xOverhead; // optional
    public RRC_nrofHARQ_ProcessesForPDSCH nrofHARQ_ProcessesForPDSCH; // optional
    public RRC_ServCellIndex pucch_Cell; // optional
    public RRC_ext1_32 ext1; // optional

    public static class RRC_ext1_32 extends AsnSequence {
        public AsnInteger maxMIMO_Layers; // optional, VALUE(1..8)
        public AsnBoolean processingType2Enabled; // optional
    }

    public static class RRC_nrofHARQ_ProcessesForPDSCH extends AsnEnumerated {
        public static final RRC_nrofHARQ_ProcessesForPDSCH N2 = new RRC_nrofHARQ_ProcessesForPDSCH(0);
        public static final RRC_nrofHARQ_ProcessesForPDSCH N4 = new RRC_nrofHARQ_ProcessesForPDSCH(1);
        public static final RRC_nrofHARQ_ProcessesForPDSCH N6 = new RRC_nrofHARQ_ProcessesForPDSCH(2);
        public static final RRC_nrofHARQ_ProcessesForPDSCH N10 = new RRC_nrofHARQ_ProcessesForPDSCH(3);
        public static final RRC_nrofHARQ_ProcessesForPDSCH N12 = new RRC_nrofHARQ_ProcessesForPDSCH(4);
        public static final RRC_nrofHARQ_ProcessesForPDSCH N16 = new RRC_nrofHARQ_ProcessesForPDSCH(5);
    
        private RRC_nrofHARQ_ProcessesForPDSCH(long value) {
            super(value);
        }
    }

    public static class RRC_SetupRelease_PDSCH_CodeBlockGroupTransmission extends AsnChoice {
        public AsnNull release;
        public RRC_PDSCH_CodeBlockGroupTransmission setup;
    }

    public static class RRC_xOverhead_2 extends AsnEnumerated {
        public static final RRC_xOverhead_2 XOH6 = new RRC_xOverhead_2(0);
        public static final RRC_xOverhead_2 XOH12 = new RRC_xOverhead_2(1);
        public static final RRC_xOverhead_2 XOH18 = new RRC_xOverhead_2(2);
    
        private RRC_xOverhead_2(long value) {
            super(value);
        }
    }
}

