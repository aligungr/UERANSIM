/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.rrc.asn.sequences;

import tr.havelsan.ueransim.asn.core.AsnEnumerated;
import tr.havelsan.ueransim.asn.core.AsnSequence;

public class RRC_DummyG extends AsnSequence {
    public RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1 maxNumberSSB_CSI_RS_ResourceOneTx; // mandatory
    public RRC_maxNumberSSB_CSI_RS_ResourceTwoTx maxNumberSSB_CSI_RS_ResourceTwoTx; // mandatory
    public RRC_supportedCSI_RS_Density_2 supportedCSI_RS_Density; // mandatory

    public static class RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1 extends AsnEnumerated {
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1 N8 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1(0);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1 N16 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1(1);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1 N32 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1(2);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1 N64 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1(3);
    
        private RRC_maxNumberSSB_CSI_RS_ResourceOneTx_1(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberSSB_CSI_RS_ResourceTwoTx extends AsnEnumerated {
        public static final RRC_maxNumberSSB_CSI_RS_ResourceTwoTx N0 = new RRC_maxNumberSSB_CSI_RS_ResourceTwoTx(0);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceTwoTx N4 = new RRC_maxNumberSSB_CSI_RS_ResourceTwoTx(1);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceTwoTx N8 = new RRC_maxNumberSSB_CSI_RS_ResourceTwoTx(2);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceTwoTx N16 = new RRC_maxNumberSSB_CSI_RS_ResourceTwoTx(3);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceTwoTx N32 = new RRC_maxNumberSSB_CSI_RS_ResourceTwoTx(4);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceTwoTx N64 = new RRC_maxNumberSSB_CSI_RS_ResourceTwoTx(5);
    
        private RRC_maxNumberSSB_CSI_RS_ResourceTwoTx(long value) {
            super(value);
        }
    }

    public static class RRC_supportedCSI_RS_Density_2 extends AsnEnumerated {
        public static final RRC_supportedCSI_RS_Density_2 ONE = new RRC_supportedCSI_RS_Density_2(0);
        public static final RRC_supportedCSI_RS_Density_2 THREE = new RRC_supportedCSI_RS_Density_2(1);
        public static final RRC_supportedCSI_RS_Density_2 ONEANDTHREE = new RRC_supportedCSI_RS_Density_2(2);
    
        private RRC_supportedCSI_RS_Density_2(long value) {
            super(value);
        }
    }
}

