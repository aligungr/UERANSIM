/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.*;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_CSI_RS_Index;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_SSB_Index;

public class RRC_CSI_RS_Resource_Mobility extends AsnSequence {
    public RRC_CSI_RS_Index csi_RS_Index; // mandatory
    public RRC_slotConfig slotConfig; // mandatory
    public RRC_associatedSSB associatedSSB; // optional
    public RRC_frequencyDomainAllocation_1 frequencyDomainAllocation; // mandatory
    public AsnInteger firstOFDMSymbolInTimeDomain; // mandatory, VALUE(0..13)
    public AsnInteger sequenceGenerationConfig; // mandatory, VALUE(0..1023)

    public static class RRC_frequencyDomainAllocation_1 extends AsnChoice {
        public AsnBitString row1; // SIZE(4)
        public AsnBitString row2; // SIZE(12)
    }

    public static class RRC_associatedSSB extends AsnSequence {
        public RRC_SSB_Index ssb_Index; // mandatory
        public AsnBoolean isQuasiColocated; // mandatory
    }

    public static class RRC_slotConfig extends AsnChoice {
        public AsnInteger ms4; // VALUE(0..31)
        public AsnInteger ms5; // VALUE(0..39)
        public AsnInteger ms10; // VALUE(0..79)
        public AsnInteger ms20; // VALUE(0..159)
        public AsnInteger ms40; // VALUE(0..319)
    }
}

