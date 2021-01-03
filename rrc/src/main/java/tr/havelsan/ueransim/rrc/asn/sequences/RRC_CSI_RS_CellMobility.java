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
import tr.havelsan.ueransim.asn.core.AsnSequenceOf;
import tr.havelsan.ueransim.rrc.asn.integers.RRC_PhysCellId;

public class RRC_CSI_RS_CellMobility extends AsnSequence {
    public RRC_PhysCellId cellId; // mandatory
    public RRC_csi_rs_MeasurementBW csi_rs_MeasurementBW; // mandatory
    public RRC_density_1 density; // optional
    public RRC_csi_rs_ResourceList_Mobility csi_rs_ResourceList_Mobility; // mandatory, SIZE(1..96)

    // SIZE(1..96)
    public static class RRC_csi_rs_ResourceList_Mobility extends AsnSequenceOf<RRC_CSI_RS_Resource_Mobility> {
    }

    public static class RRC_density_1 extends AsnEnumerated {
        public static final RRC_density_1 D1 = new RRC_density_1(0);
        public static final RRC_density_1 D3 = new RRC_density_1(1);
    
        private RRC_density_1(long value) {
            super(value);
        }
    }

    public static class RRC_csi_rs_MeasurementBW extends AsnSequence {
        public RRC_nrofPRBs nrofPRBs; // mandatory
        public AsnInteger startPRB; // mandatory, VALUE(0..2169)
    
        public static class RRC_nrofPRBs extends AsnEnumerated {
            public static final RRC_nrofPRBs SIZE24 = new RRC_nrofPRBs(0);
            public static final RRC_nrofPRBs SIZE48 = new RRC_nrofPRBs(1);
            public static final RRC_nrofPRBs SIZE96 = new RRC_nrofPRBs(2);
            public static final RRC_nrofPRBs SIZE192 = new RRC_nrofPRBs(3);
            public static final RRC_nrofPRBs SIZE264 = new RRC_nrofPRBs(4);
        
            private RRC_nrofPRBs(long value) {
                super(value);
            }
        }
    }
}

