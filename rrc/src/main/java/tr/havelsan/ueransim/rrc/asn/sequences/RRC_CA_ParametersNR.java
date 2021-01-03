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

public class RRC_CA_ParametersNR extends AsnSequence {
    public RRC_dummy_6 dummy; // optional
    public RRC_parallelTxSRS_PUCCH_PUSCH parallelTxSRS_PUCCH_PUSCH; // optional
    public RRC_parallelTxPRACH_SRS_PUCCH_PUSCH parallelTxPRACH_SRS_PUCCH_PUSCH; // optional
    public RRC_simultaneousRxTxInterBandCA simultaneousRxTxInterBandCA; // optional
    public RRC_simultaneousRxTxSUL simultaneousRxTxSUL; // optional
    public RRC_diffNumerologyAcrossPUCCH_Group diffNumerologyAcrossPUCCH_Group; // optional
    public RRC_diffNumerologyWithinPUCCH_GroupSmallerSCS diffNumerologyWithinPUCCH_GroupSmallerSCS; // optional
    public RRC_supportedNumberTAG supportedNumberTAG; // optional

    public static class RRC_simultaneousRxTxSUL extends AsnEnumerated {
        public static final RRC_simultaneousRxTxSUL SUPPORTED = new RRC_simultaneousRxTxSUL(0);
    
        private RRC_simultaneousRxTxSUL(long value) {
            super(value);
        }
    }

    public static class RRC_dummy_6 extends AsnEnumerated {
        public static final RRC_dummy_6 SUPPORTED = new RRC_dummy_6(0);
    
        private RRC_dummy_6(long value) {
            super(value);
        }
    }

    public static class RRC_supportedNumberTAG extends AsnEnumerated {
        public static final RRC_supportedNumberTAG N2 = new RRC_supportedNumberTAG(0);
        public static final RRC_supportedNumberTAG N3 = new RRC_supportedNumberTAG(1);
        public static final RRC_supportedNumberTAG N4 = new RRC_supportedNumberTAG(2);
    
        private RRC_supportedNumberTAG(long value) {
            super(value);
        }
    }

    public static class RRC_diffNumerologyWithinPUCCH_GroupSmallerSCS extends AsnEnumerated {
        public static final RRC_diffNumerologyWithinPUCCH_GroupSmallerSCS SUPPORTED = new RRC_diffNumerologyWithinPUCCH_GroupSmallerSCS(0);
    
        private RRC_diffNumerologyWithinPUCCH_GroupSmallerSCS(long value) {
            super(value);
        }
    }

    public static class RRC_diffNumerologyAcrossPUCCH_Group extends AsnEnumerated {
        public static final RRC_diffNumerologyAcrossPUCCH_Group SUPPORTED = new RRC_diffNumerologyAcrossPUCCH_Group(0);
    
        private RRC_diffNumerologyAcrossPUCCH_Group(long value) {
            super(value);
        }
    }

    public static class RRC_simultaneousRxTxInterBandCA extends AsnEnumerated {
        public static final RRC_simultaneousRxTxInterBandCA SUPPORTED = new RRC_simultaneousRxTxInterBandCA(0);
    
        private RRC_simultaneousRxTxInterBandCA(long value) {
            super(value);
        }
    }

    public static class RRC_parallelTxPRACH_SRS_PUCCH_PUSCH extends AsnEnumerated {
        public static final RRC_parallelTxPRACH_SRS_PUCCH_PUSCH SUPPORTED = new RRC_parallelTxPRACH_SRS_PUCCH_PUSCH(0);
    
        private RRC_parallelTxPRACH_SRS_PUCCH_PUSCH(long value) {
            super(value);
        }
    }

    public static class RRC_parallelTxSRS_PUCCH_PUSCH extends AsnEnumerated {
        public static final RRC_parallelTxSRS_PUCCH_PUSCH SUPPORTED = new RRC_parallelTxSRS_PUCCH_PUSCH(0);
    
        private RRC_parallelTxSRS_PUCCH_PUSCH(long value) {
            super(value);
        }
    }
}

