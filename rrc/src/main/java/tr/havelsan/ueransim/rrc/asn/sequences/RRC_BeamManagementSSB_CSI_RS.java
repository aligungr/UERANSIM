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

public class RRC_BeamManagementSSB_CSI_RS extends AsnSequence {
    public RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2 maxNumberSSB_CSI_RS_ResourceOneTx; // mandatory
    public RRC_maxNumberCSI_RS_Resource maxNumberCSI_RS_Resource; // mandatory
    public RRC_maxNumberCSI_RS_ResourceTwoTx maxNumberCSI_RS_ResourceTwoTx; // mandatory
    public RRC_supportedCSI_RS_Density_1 supportedCSI_RS_Density; // optional
    public RRC_maxNumberAperiodicCSI_RS_Resource maxNumberAperiodicCSI_RS_Resource; // mandatory

    public static class RRC_supportedCSI_RS_Density_1 extends AsnEnumerated {
        public static final RRC_supportedCSI_RS_Density_1 ONE = new RRC_supportedCSI_RS_Density_1(0);
        public static final RRC_supportedCSI_RS_Density_1 THREE = new RRC_supportedCSI_RS_Density_1(1);
        public static final RRC_supportedCSI_RS_Density_1 ONEANDTHREE = new RRC_supportedCSI_RS_Density_1(2);
    
        private RRC_supportedCSI_RS_Density_1(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberCSI_RS_ResourceTwoTx extends AsnEnumerated {
        public static final RRC_maxNumberCSI_RS_ResourceTwoTx N0 = new RRC_maxNumberCSI_RS_ResourceTwoTx(0);
        public static final RRC_maxNumberCSI_RS_ResourceTwoTx N4 = new RRC_maxNumberCSI_RS_ResourceTwoTx(1);
        public static final RRC_maxNumberCSI_RS_ResourceTwoTx N8 = new RRC_maxNumberCSI_RS_ResourceTwoTx(2);
        public static final RRC_maxNumberCSI_RS_ResourceTwoTx N16 = new RRC_maxNumberCSI_RS_ResourceTwoTx(3);
        public static final RRC_maxNumberCSI_RS_ResourceTwoTx N32 = new RRC_maxNumberCSI_RS_ResourceTwoTx(4);
        public static final RRC_maxNumberCSI_RS_ResourceTwoTx N64 = new RRC_maxNumberCSI_RS_ResourceTwoTx(5);
    
        private RRC_maxNumberCSI_RS_ResourceTwoTx(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberCSI_RS_Resource extends AsnEnumerated {
        public static final RRC_maxNumberCSI_RS_Resource N0 = new RRC_maxNumberCSI_RS_Resource(0);
        public static final RRC_maxNumberCSI_RS_Resource N4 = new RRC_maxNumberCSI_RS_Resource(1);
        public static final RRC_maxNumberCSI_RS_Resource N8 = new RRC_maxNumberCSI_RS_Resource(2);
        public static final RRC_maxNumberCSI_RS_Resource N16 = new RRC_maxNumberCSI_RS_Resource(3);
        public static final RRC_maxNumberCSI_RS_Resource N32 = new RRC_maxNumberCSI_RS_Resource(4);
        public static final RRC_maxNumberCSI_RS_Resource N64 = new RRC_maxNumberCSI_RS_Resource(5);
    
        private RRC_maxNumberCSI_RS_Resource(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberAperiodicCSI_RS_Resource extends AsnEnumerated {
        public static final RRC_maxNumberAperiodicCSI_RS_Resource N0 = new RRC_maxNumberAperiodicCSI_RS_Resource(0);
        public static final RRC_maxNumberAperiodicCSI_RS_Resource N1 = new RRC_maxNumberAperiodicCSI_RS_Resource(1);
        public static final RRC_maxNumberAperiodicCSI_RS_Resource N4 = new RRC_maxNumberAperiodicCSI_RS_Resource(2);
        public static final RRC_maxNumberAperiodicCSI_RS_Resource N8 = new RRC_maxNumberAperiodicCSI_RS_Resource(3);
        public static final RRC_maxNumberAperiodicCSI_RS_Resource N16 = new RRC_maxNumberAperiodicCSI_RS_Resource(4);
        public static final RRC_maxNumberAperiodicCSI_RS_Resource N32 = new RRC_maxNumberAperiodicCSI_RS_Resource(5);
        public static final RRC_maxNumberAperiodicCSI_RS_Resource N64 = new RRC_maxNumberAperiodicCSI_RS_Resource(6);
    
        private RRC_maxNumberAperiodicCSI_RS_Resource(long value) {
            super(value);
        }
    }

    public static class RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2 extends AsnEnumerated {
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2 N0 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2(0);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2 N8 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2(1);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2 N16 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2(2);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2 N32 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2(3);
        public static final RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2 N64 = new RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2(4);
    
        private RRC_maxNumberSSB_CSI_RS_ResourceOneTx_2(long value) {
            super(value);
        }
    }
}

